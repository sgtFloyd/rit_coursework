# File:         connect4.asm
# Author:       Gabriel Smith, Section 01
#
# Description:
#   A simple Connect 4 game. The first player to create a set of 4 pieces in a
#   row wins. The row can be vertical, horizontal, or diagonal. If the board
#   fills up before somebody wins, then the game is a tie.
#
# Arguments:
#   none

# CONSTANT DECLARATIONS
# syscall codes
PRINT_STRING = 4
READ_INT = 5
EXIT = 10

# DATA DECLARATIONS
    .data
    .align 2

# board initialized to empty. set cells to 1 or 2 to use a pre-built board
board:
            
    .word   0,0,0,0,0,0,0
    .word   0,0,0,0,0,0,0
    .word   0,0,0,0,0,0,0
    .word   0,0,0,0,0,0,0
    .word   0,0,0,0,0,0,0
    .word   0,0,0,0,0,0,0
    
# output messages
welcome:
    .asciiz "   ************************\n   **    Connect Four    **\n   ************************\n"
p1_msg:
    .asciiz "Player 1: select a row to place your coin (0-6 or -1 to quit):"
p2_msg:
    .asciiz "Player 2: select a row to place your coin (0-6 or -1 to quit):"
p1_quit:
    .asciiz "Player 1 quit.\n"
p2_quit:
    .asciiz "Player 2 quit.\n"
p1_win:
    .asciiz "Player 1 wins!\n"
p2_win:
    .asciiz "Player 2 wins!\n"
game_tie:
    .asciiz "The game ends in a tie.\n"
bad_col:
    .asciiz "Illegal column number.\n"
col_full:
    .asciiz "Illegal move, no more room in that column.\n"
newline:
    .asciiz "\n"

# characters used for drawing game board
board_num:
    .asciiz "   0   1   2   3   4   5   6\n"
board_line1:
    .asciiz "+-----------------------------+\n"
board_line2:
    .asciiz "|+---+---+---+---+---+---+---+|\n"
line_start:
    .asciiz "|| "
line_end:
    .asciiz " ||\n"
line_spacer:
    .asciiz " | "
p1_coin:
    .asciiz "X"
p2_coin:
    .asciiz "O"
no_coin:
    .asciiz " "

    .text
    .align 2
    .globl  main
#
# Name:     main
#
# Description:  Main logic for the program. Calls all the functions in the
# correct order. Also prints then end of game win/tie message.
#

main:
    addi    $sp, $sp, -8
    sw      $ra, 0($sp)             # store $ra on the stack
    sw      $s7, 4($sp)             # store $s7 on the stack
    li      $v0, PRINT_STRING       # display welcome message
    la      $a0, welcome
    syscall
    move    $s7, $zero              # global variable for current player
game_loop:
    jal     print_board             # display game board
    jal     get_input               # get user input
    move    $a0, $v0
    jal     make_move               # return value from input used to make_move
    jal     check_for_win           # check board for a winner
    jal     check_full_board        # check board for a tie
    jal     toggle_player           # switch players
    j       game_loop
end_game:
    beq     $v0, $zero, tie_game    # if v0 = 0, board is full and game is a tie
    beq     $s7, $zero, win_p1      # else, get current player, they are winner
    jal     print_board
    li      $v0, PRINT_STRING
    la      $a0, p2_win
    syscall                         # print player 2s win message
    j       exit_program
win_p1:
    jal     print_board
    li      $v0, PRINT_STRING
    la      $a0, p1_win
    syscall                         # print player 1s win message
    j       exit_program
tie_game:
    jal     print_board
    li      $v0, PRINT_STRING
    la      $a0, game_tie
    syscall                         # print tie game message
exit_program:
    lw      $ra, 0($sp)             # restore $ra
    lw      $s7, 4($sp)             # restore $s7
    addi    $sp, $sp, 8
    jr      $ra                     # exit program

#
# Name:     print_board
#
# Description:  Loops through all 42 spaces and prints the piece contained
# within each space, along with the borders and spacers that make up the board.
#
print_board:
    addi    $sp, $sp, -4
    sw      $ra, 0($sp)             # store $ra on the stack
    li      $v0, PRINT_STRING
    la      $a0, newline            # print the top rows of the board
    syscall
    la      $a0, board_num
    syscall
    la      $a0, board_line1
    syscall
    la      $a0, board_line2
    syscall
    
    la      $t0, board              # t0 = address of board
    addi    $t3, $zero, 6           # t3 = 6, value to jump out of loops
    addi    $t4, $zero, 1           # t4 = value for player1 coin
    addi    $t5, $zero, 2           # t5 = value for player2 coin
    move    $t6, $zero              # t6 = row counter
    move    $t7, $zero              # t7 = col counter
row_loop:
    la      $a0, line_start         # print chars at beginning of each row
    syscall
    lw      $t1, 0($t0)             # t1 = coin at t0
    beq     $t1, $t4, p1_coinA      # check for player1 coin
    beq     $t1, $t5, p2_coinA      # check for player2 coin
    la      $a0, no_coin            # prepare to print no coin
    j       print_coinA
p1_coinA:
    la      $a0, p1_coin            # prepare to print p1 coin
    j       print_coinA
p2_coinA:
    la      $a0, p2_coin            # prepare to print p2 coin
print_coinA:
    syscall                         # print correct coin
    addi    $t0, $t0, 4             # move to next piece on board
    move    $t7, $zero
col_loop:
    la      $a0, line_spacer        # print a spacer between coins
    syscall
    lw      $t1, 0($t0)             # t1 = coin at t0
    beq     $t1, $t4, p1_coinB      # check for player1 coin
    beq     $t1, $t5, p2_coinB      # check for player2 coin
    la      $a0, no_coin            # prepare to print no coin
    j       print_coinB
p1_coinB:
    la      $a0, p1_coin            # prepare to print p1 coin
    j       print_coinB
p2_coinB:
    la      $a0, p2_coin            # prepare to print p2 coin
print_coinB:
    syscall                         # print correct coin
    addi    $t0, $t0, 4             # move to next piece on board
    addi    $t7, $t7, 1
    beq     $t7, $t3, end_col
    j       col_loop
end_col:
    la      $a0, line_end           # print chars at end of row
    syscall
    la      $a0, board_line2        # print line between rows
    syscall
    addi    $t6, $t6, 1
    beq     $t6, $t3, end_row       # if t6 = 5, end row_loop
    j       row_loop
end_row:
    la      $a0, board_line1        # print the bottom rows of the board
    syscall
    la      $a0, board_num
    syscall
    la      $a0, newline
    syscall
    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    jr      $ra                     # return

#
# Name:     get_input
#
# Description:  Gets the next move from the player. Checks if the move is valid.
# If the move is valid, it returns the move in $v0. If the move isnt valid, it
# loops until a valid move is entered. Uses check_input to check validity. Calls
# force_quit if -1 is entered.
#
get_input:
    addi    $sp, $sp, -4
    sw      $ra, 0($sp)             # store $ra on the stack
get_input_loop:
    li      $v0, PRINT_STRING
    beq     $s7, $zero, p1_in       # find whos turn it is
    la      $a0, p2_msg
    j       end_input_msg
p1_in:
    la      $a0, p1_msg
end_input_msg:
    syscall                         # display message for proper player
    li      $v0, READ_INT
    syscall
    move    $t0, $v0                # t0 = player input
    addi    $t1, $zero, -1
    beq     $t0, $t1, force_quit    # if input = -1, force quit
    blt     $t0, $t1, bad_input     # if input < -1, illegal move
    addi    $t1, $zero, 6
    bgt     $t0, $t1, bad_input     # if input > 6, illegal move
    addi    $t1, $zero, 4
    mul     $t2, $t1, $t0           # t2 = column * 4
    la      $t1, board
    add     $t1, $t1, $t2           # t1 = address of top piece in column
    lw      $t2, 0($t1)             # t2 = piece in t1
    addi    $t1, $zero, 1
    beq     $t2, $t1, full_column   # if piece at t2 = 1, column full
    addi    $t1, $zero, 2
    beq     $t2, $t1, full_column   # if piece at t2 = 2, column full
    j       good_input
bad_input:
    li      $v0, PRINT_STRING
    la      $a0, bad_col            # prepare error for illegal column number
    j       print_error
full_column:
    li      $v0, PRINT_STRING
    la      $a0, col_full           # prepare error for full column
    j       print_error
print_error:
    syscall                         # print correct error
    j       get_input_loop
good_input:
    move    $v0, $t0                # return players legal move
    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    jr      $ra                     # return


#
# Name:     force_quit
#
# Description:  Quits the game with a message when -1 is entered as a move.
#
force_quit:
    li      $v0, PRINT_STRING
    beq     $s7, $zero, p1_exit     # check which player quit
    la      $a0, p2_quit            # prepare player 2s quit message
    j       end_exit
p1_exit:
    la      $a0, p1_quit            # prepare player 1s quit message
end_exit:
    syscall                         # print exit message for correct player
    li      $v0, EXIT
    syscall                         # exit program
    
#
# Name:     make_move
#
# Description:  Takes the players input in $a0 and drops a piece in that
# column. X for player 1 and O for player 2. Begins at the bottom of selected
# column and works up until an empty cell is found.
#
make_move:
    addi    $sp, $sp, -4
    sw      $ra, 0($sp)             # store $ra on the stack
    move    $t0, $a0                # t0 = column to drop in
    addi    $t0, $t0, 35            # t0 = bottom cell of column
find_empty_cell:
    la      $t1, board              # t1 = address of board
    addi    $t4, $zero, 4
    mul     $t5, $t0, $t4
    add     $t3, $t5, $t1           # t3 = address of next lowest cell
    lw      $t1, 0($t3)             # t1 = piece at next lowest cell
    beq     $t1, $zero, cell_empty  # found empty cell, drop piece
    addi    $t0, $t0, -7            # move up one cell in same column
    j       find_empty_cell
cell_empty:
    beq     $s7, $zero, drop_X      # drop the correct piece for this player
    addi    $t5, $zero, 2           # drop player 2s piece
    j       end_drop
drop_X:
    addi    $t5, $zero, 1           # drop player 1s piece
end_drop:
    sw      $t5, 0($t3)             # add the piece to the board
    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    jr      $ra                     # return
    
#
# Name:     check_for_win
#
# Description:  Checks for 4 of the same pieces in a row in 4 different
# directions: Horizontal, Vertical, Diagonal (top-left to bottom-right) and
# Diagonal (top-right to bottom-left). If a win is found, redirects program
# flow directly to end_game with a return value of 1.
#
check_for_win:
    addi    $sp, $sp, -4
    sw      $ra, 0($sp)             # store $ra on the stack
    la      $t0, board              # t0 = address of board
    
# check vertical, top to bottom
# need to check cells 0-20
    addi    $t6, $zero, -1          # outer loop initialization (i)
    addi    $t3, $zero, 21          # outer loop break condition
vert_o_loop:
    addi    $t6, $t6, 1             # i++
    beq     $t6, $t3, end_vert      # loop while i < 21 (for cells 0-20)
    mul     $t1, $t6, 4
    add     $t1, $t1, $t0           # t1 = address of cell t6
    lw      $t1, 0($t1)             # t1 = piece at cell t6
    beq     $t1, $zero, vert_o_loop # if no piece at t1, move on to next cell
    addi    $t7, $t6, 7             # t7 = cell below t6
    addi    $t5, $t6, 28            # inner loop break condition
vert_i_loop:
    mul     $t2, $t7, 4
    add     $t2, $t2, $t0           # t2 = address of cell t7
    lw      $t2, 0($t2)             # t2 = piece at cell t7
    bne     $t1, $t2, vert_o_loop   # if t1 != t2, move onto next cell
    addi    $t7, $t7, 7
    beq     $t7, $t5, found_winner  # if 4 cells match, winner found
    j       vert_i_loop
end_vert:

# check horizontal, left to right.
# need to check cells 0-3, 7-10, 14-17, 21-24, 28-31, 35-38
    addi    $t8, $zero, -1          # initial condition for outter loop
    addi    $t9, $zero, 4           # initial breaking condition for outer loop
    addi    $t4, $zero, 46          # final breaking condition for outer loop
hor_o_loop:
    beq     $t9, $t4, end_hor       # if were off the board, break
    move    $t6, $t8                # update initial condition for inner loop
    move    $t3, $t9                # update breaking condition for inner loop
hor_i_loopA:
    addi    $t6, $t6, 1
    beq     $t6, $t3, end_i_horiz
    mul     $t1, $t6, 4
    add     $t1, $t1, $t0           # t1 = address of cell t6
    lw      $t1, 0($t1)             # t1 = piece at cell t6
    beq     $t1, $zero, hor_i_loopA # if no piece at t1, move on
    addi    $t7, $t6, 1             # t7 = cell next to t6
    addi    $t5, $t6, 4             # inner loop break condition
hor_i_loopB:
    mul     $t2, $t7, 4
    add     $t2, $t2, $t0           # t2 = address of cell t7
    lw      $t2, 0($t2)             # t2 = piece at t7
    bne     $t1, $t2, hor_i_loopA   # if t1 != t2, move on
    addi    $t7, $t7, 1
    beq     $t7, $t5, found_winner  # if 4 cells match, winner
    j       hor_i_loopB
end_i_horiz:
    addi    $t8, $t8, 7             # update breaking conditions
    addi    $t9, $t9, 7             # (move onto next group of cells)
    j       hor_o_loop
end_hor:

# check diagonal (top-left to bottom-right)
# need to check cells 0-3, 7-10, 14-17
    addi    $t8, $zero, -1
    addi    $t9, $zero, 4
    addi    $t4, $zero, 25
dg1_o_loop:
    beq     $t9, $t4, end_dg1 
    move    $t6, $t8
    move    $t3, $t9
dg1_i_loopA:
    addi    $t6, $t6, 1
    beq     $t6, $t3, end_i_dg1
    mul     $t1, $t6, 4
    add     $t1, $t1, $t0
    lw      $t1, 0($t1)             # t1 = piece at cell t6
    beq     $t1, $zero, dg1_i_loopA # if no piece at t1, move on
    addi    $t7, $t6, 8             # t7 = cell diagonal to t6
    addi    $t5, $t6, 32            # inner loop break condition
dg1_i_loopB:
    mul     $t2, $t7, 4
    add     $t2, $t2, $t0
    lw      $t2, 0($t2)             # t2 = piece at t7
    bne     $t1, $t2, dg1_i_loopA   # if t1 != t2, move on
    addi    $t7, $t7, 8
    beq     $t7, $t5, found_winner  # if 4 cells match, winner
    j       dg1_i_loopB
end_i_dg1:
    addi    $t8, $t8, 7
    addi    $t9, $t9, 7
    j       dg1_o_loop
end_dg1:

# check diagonal (top-right to bottom-left)
# need to check cells 3-6, 10-13, 17-20
    addi    $t8, $zero, 2
    addi    $t9, $zero, 7
    addi    $t4, $zero, 28
dg2_o_loop:
    beq     $t9, $t4, end_dg2
    move    $t6, $t8
    move    $t3, $t9
dg2_i_loopA:
    addi    $t6, $t6, 1
    beq     $t6, $t3, end_i_dg2
    mul     $t1, $t6, 4
    add     $t1, $t1, $t0
    lw      $t1, 0($t1)             # t1 = piece at cell t6
    beq     $t1, $zero, dg2_i_loopA # if no piece at t1, move on
    addi    $t7, $t6, 6             # t7 = cell diagonal to t6
    addi    $t5, $t6, 24            # inner loop break condition
dg2_i_loopB:
    mul     $t2, $t7, 4
    add     $t2, $t2, $t0
    lw      $t2, 0($t2)             # t2 = piece at t7
    bne     $t1, $t2, dg2_i_loopA   # if t1 != t2, move on
    addi    $t7, $t7, 6
    beq     $t7, $t5, found_winner  # if 4 cells match, winner
    j       dg2_i_loopB
end_i_dg2:
    addi    $t8, $t8, 7
    addi    $t9, $t9, 7
    j       dg2_o_loop
end_dg2:

    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    jr      $ra                     # return
found_winner:
    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    addi    $v0, $zero, 1           # return 1
    j       end_game                # jump to end_game
    
#
# Name:     check_full_board
#
# Description: Checks the top cell of each column. If all top cells are filled,
# then then board is full, and no more moves can be made. Game ends in a tie.
#
check_full_board:
    addi    $sp, $sp, -4
    sw      $ra, 0($sp)             # store $ra on the stack
    la      $t1, board              # t1 = address of board
    addi    $t4, $zero, 4           # t4 = 4, constant for multiplication
    addi    $t7, $zero, 7           # t7 = 7, break condition
    addi    $t0, $zero, 0           # t0 = 0, current column
check_board_loop:
    mul     $t3, $t4, $t0
    add     $t3, $t3, $t1           # t3 = address of top cell in current column
    lw      $t5, 0($t3)             # t5 = piece in t3
    beq     $t5, $zero, not_full    # if t5 = 0, board isnt full
    addi    $t0, $t0, 1             # t0++, move onto next column
    beq     $t0, $t7, is_full       # if we run out of columns, board is full
    j       check_board_loop
not_full:
    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    jr      $ra                     # return
is_full:
    lw      $ra, 0($sp)             # restore $ra
    addi    $sp, $sp, 4
    move    $v0, $zero              # v0 = 0, game tied
    j       end_game

#
# Name:     toggle_player
#
# Description:  Switches players.
#
toggle_player:
    beq     $s7, $zero, make_p2
    addi    $s7, $s7, -1            # if its p2s turn, make it p1s
    j       end_toggle
make_p2:
    addi    $s7, $s7, 1             # if its p1s turn, make it p2s
end_toggle:
    jr      $ra                     # return
    
