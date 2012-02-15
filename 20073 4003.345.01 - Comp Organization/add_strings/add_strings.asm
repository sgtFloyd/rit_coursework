# File:     add_strings.asm
# Author:   K. Reek
# Contributors: P. White
#       <<YOUR NAME HERE>>
#
# Decription:   Experiment 2
#       This program adds variable length of numbers by
#       performing arithmetic on the ASCII digits themselves.
#
# Updates:
#       3/2004  M. Reek, named constants
#       10/2007 W. Carithers, alignment

#
# Name:     Constant definitions
#
# Description:  These constants define values used for system calls,
#       and some other miscellaneous values.
#

# Constants for system calls
#
PRINT_INT       = 1             # code for syscall to print integer
PRINT_STRING    = 4             # code for syscall to print a string
READ_INT        = 5             # code for syscall to read an integer

#
# Name:     Data areas
#
# Description:  Much of the data for the program is here, including
#       the ASCII digit strings to be added, the buffers
#       into which the results are stored, and some of the
#       strings used to identify the output.
#

#
# The ASCII digit strings you will be adding
#
    .data
    .align 0

str_a1: .asciiz "1"

str_a2: .asciiz "5"

#        ----+----1
str_b1: .asciiz "0000000000"

str_b2: .asciiz "1234567890"

str_b3: .asciiz "5555555555"

#        ----+----1----+----2----+----3----+----4----+----5----+----6
str_c1: .asciiz "459487674739209198487516885011886385488413584897812587984532"

str_c2: .asciiz "177898124879752312578975138488674345349797549878432168798748"

#
# Buffers into which the sums will be stored.  These have text
# defined both before and after them to assist in printing the
# sum and to ensure that it does not spill out of the alotted space.
#
out_a:  .ascii  "Result..........."
buf_a:  .ascii  " ."
    .asciiz "\n \n"

out_b:  .ascii  "Result..........."
#        ----+----1
buf_b:  .ascii  "          ."
    .asciiz "\n \n"

out_b2: .ascii  "Result..........."
#        ----+----1
buf_b2: .ascii  "          ."
    .asciiz "\n \n"

out_c:  .ascii  "Result..........."
#        ----+----1----+----2----+----3----+----4----+----5----+----6
buf_c:  .ascii  "                                                            ."
    .asciiz "\n \n"

#
# Strings used to identify the results when they are printed
#
result1:
    .asciiz "First operand...."

result2:
#   .ascii  "."
#   .ascii  "\n"
    .asciiz ".\nSecond operand..."

result3:
#   .ascii  "."
    .asciiz ".\n"

#
# Name:     Main program
#
# Description:  Main controlling logic for the program.  The
#       parameter block for each problem is passed,
#       one by one, to a routine that does the work.
#
    .text           # this is program code
    .align 2        # instructions must be on word boundaries
    .globl main     # main is a global label

main:
M_FRAMESIZE = 8
    addi    $sp, $sp,-M_FRAMESIZE   # allocate space for the return address
    sw  $ra, -4+M_FRAMESIZE($sp)# store the ra on the stack
    
    la  $a0, problem1   #Address of parameters for 
    jal do_problem  # problem 1, and do it.

    la  $a0, problem2a
    jal do_problem

    la  $a0, problem2b
    jal do_problem

    la  $t9, buf_b  #fixing up buf_b to use as input
    sb  $zero, 10($t9)  #put a <null> at end of buf_b

    la  $a0, problem2c
    jal do_problem

    la  $a0, problem3
    jal do_problem

#
# All done -- exit the program!
#
    lw  $ra, -4+M_FRAMESIZE($sp)# restore the ra
    addi    $sp, $sp, M_FRAMESIZE   # deallocate stack space 
    jr  $ra         # return from main and exit spim
    

#
# Name:     Parameter blocks defining the addition problems
#
# Description:  These are the parameter blocks that define each of the
#       addition problems.  Each block includes addresses of
#       the two strings and the output buffer, and the length
#       of these three areas (they are all the same length).
#       Each block then contains the address and length needed
#       to print the result, though these last two values are
#       unused by the addition routine itself.
#
    .data
    .align  2

problem1:
    .word   str_a1      #First number
    .word   str_a2      #Second number
    .word   buf_a       #Place to store the result
    .word   1       #Length of both numbers and result buf
    .word   out_a       #Where to start printing the answer

problem2a:
    .word   str_b1, str_b2, buf_b, 10, out_b

problem2b:
    .word   str_b2, str_b3, buf_b, 10, out_b

problem2c:
    .word   str_b3, buf_b, buf_b2, 10, out_b2

problem3:
    .word   str_c1, str_c2, buf_c, 60, out_c

#
# Name:     do_problem
#
# Description:  Main logic for doing one individual problem.
# Arguments:    a0: address of the parameter block describing the problem.
#

    .text           #begin subroutine code
do_problem:

    DO_FRAMESIZE = 16
    addi    $sp,$sp,-DO_FRAMESIZE 
    sw  $ra, -4+DO_FRAMESIZE($sp)   # store the ra on the stack
    sw  $s0, -8+DO_FRAMESIZE($sp)   # store the s0 on the stack
    
#
# Print the original values
#

    move    $s0,$a0         #copy the location of the parm block to s0

    li  $v0,PRINT_STRING    # print 1st number label    
    la  $a0,result1
    syscall

    li  $v0,PRINT_STRING    # print 1st number  
    lw  $a0,0($s0)
    syscall

    li  $v0,PRINT_STRING    # print 2nd number label    
    la  $a0,result2
    syscall

    li  $v0,PRINT_STRING    # print 2nd number  
    lw  $a0,4($s0)
    syscall


    li  $v0,PRINT_STRING    # print newline at end  
    la  $a0,result3
    syscall

#
# Now, call the add_ascii_numbers subroutine to do the addition.
# The address of the parameter block is still in r8.
#

    move    $a0,$s0     #set up argument for function
    jal add_ascii_numbers

#
# Print the result
#

    li  $v0,PRINT_STRING    # print answer  
    lw  $a0,16($s0)
    syscall

#
# Return to the calling program.
#
    lw  $s0, -8+DO_FRAMESIZE($sp)   # restore the s0
    lw  $ra, -4+DO_FRAMESIZE($sp)   # restore the ra
    addi    $sp,$sp,DO_FRAMESIZE        # deallocate stack space 
    jr  $ra

#
# Parameter blocks for writing the strings.
#

#param_out:
#   .word   3       #3 parameters
#   .word   STDOUT      #write to the standard output
#param_out_buf:
#   .blkl   1       #address of buffer to write
#param_out_len:
#   .blkl   1       ## of chars to write

#
# Name:     add_ascii_numbers
#
# Description:  Add two ASCII numbers and store the result in ASCII.
# Arguments:    a0: address of parameter block.  The block is contains
#       four words that specify (in this order):
#           address of first input string
#           address of second input string
#           address where result should be stored
#           length of the strings and result buffer
#       (There is actually other data after this in the
#       parameter block, but it is not relevant to this routine.)
# Returns:  The result of the addition, in the buffer specified by
#       the parameter block.
#

add_ascii_numbers:
    A_FRAMESIZE = 40

#
# Save registers ra and s0 - s7 on the stack.
#
    addi    $sp,$sp,-A_FRAMESIZE
    sw  $ra, -4+A_FRAMESIZE($sp)
    sw  $s7, 28($sp)
    sw  $s6, 24($sp)
    sw  $s5, 20($sp)
    sw  $s4, 16($sp)
    sw  $s3, 12($sp)
    sw  $s2, 8($sp)
    sw  $s1, 4($sp)
    sw  $s0, 0($sp)
    
# ##### BEGIN STUDENT CODE BLOCK 1 #####

    add $t0, $zero, $zero   # t0 = 0, set when carrying a 1
    lw  $s1, 0($a0)         # s1 = address of input_1
    lw  $s2, 4($a0)         # s2 = address of input_2
    lw  $s0, 8($a0)         # s0 = address of result
    lw  $s7, 12($a0)        # s7 = length of each string
    addi    $s7, $s7, -1    # s7--, to zero-base it
    
loop:
    add $t1, $s1, $s7       # t1 = address of next digit of input_1
    add $t2, $s2, $s7       # t2 = address of next digit of input_2
    add $t6, $s0, $s7       # t6 = address of next digit of result
    
    lb  $t3, 0($t1)         # t3 = next digit of input_1 in ascii
    addi    $t3, $t3, -48   # t3 -= 48, converted to integer
    lb  $t4, 0($t2)         # t4 = next digit of input_2 in ascii
    addi    $t4, $t4, -48   # t4 -= 48, converted to integer

    add $t5, $t3, $t4       # t5 = t3 + t4
    add $t5, $t5, $t0       # t5 += remainder (0 if nothing carried)
    
    addi    $t9, $zero, 9   # t9 = 9
    bgt $t5, $t9, else      # if t5 > 9, goto else
    add $t0, $zero, $zero   # if nothing to carry, t0 = 0
    j   endif
else:
    addi    $t5, $t5, -10   # if carrying a 1, t5 -= 10
    addi    $t0, $zero, 1   # $t0 = 1 (carry the 1)
endif:
    addi    $t5, $t5, 48    # t5 += 48, convert back to ascii
    sb  $t5, 0($t6)         # set next digit of result = t5
    
    addi    $s7, $s7, -1    # s7--, move to next digit in strings
    blt $s7, $zero, done    # if s7 < 0, no more digits
    j loop
done:

# ###### END STUDENT CODE BLOCK 1 ######

#
# Restore registers ra and s0 - s7 from the stack.
#
    lw  $ra, -4+A_FRAMESIZE($sp)
    lw  $s7, 28($sp)
    lw  $s6, 24($sp)
    lw  $s5, 20($sp)
    lw  $s4, 16($sp)
    lw  $s3, 12($sp)
    lw  $s2, 8($sp)
    lw  $s1, 4($sp)
    lw  $s0, 0($sp)
    addi    $sp,$sp,A_FRAMESIZE

    jr  $ra         #return to the caller.
