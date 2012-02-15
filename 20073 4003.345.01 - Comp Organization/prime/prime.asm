# FILE:         $File$
# AUTHOR:       P. White
# CONTRIBUTORS: M. Reek
#               Gabe Smith
#
# DESCRIPTION:
#   This is a simple program to find the prime numbers between 3 - 101
#   inclusive.  This is done by using the simple algorithm where a 
#   number n is prime if no number between 2 and n-1 divides evenly 
#   into n
#
# ARGUMENTS:
#       None
#
# INPUT:
#   none
#
# OUTPUT:
#   the prime numbers printed 1 to a line
#
# REVISION HISTORY:
#       Dec  03         - P. White, created program
#       Mar  04         - M. Reek, added named constants
#

#
# CONSTANT DECLARATIONS
#
PRINT_INT   = 1     # code for syscall to print integer
PRINT_STRING    = 4     # code for syscall to print a string
MIN     = 3     # minimum value to check
MAX     = 102       # max value to check

#
# DATA DECLARATIONS
#
    .data
newline:
    .asciiz "\n"
#
# MAIN PROGRAM
#
    .text
    .align  2
    .globl  main
main:
        addi    $sp,$sp,-8      # space for return address/doubleword aligned
        sw      $ra, 0($sp)     # store the ra on the stack

    jal find_primes

        #
        # Now exit the program.
    #
        lw      $ra, 0($sp) # clean up stack
        addi    $sp,$sp,8
        jr      $ra

#
# Name:     find_primes 
#
# Description:  find the prime numbers between 3 and 101 inclusive
# Arguments:    none
# Returns:  nothing
#

find_primes:
        addi    $sp,$sp,-40     # allocate stack frame (on doubleword boundary)
        sw      $ra, 32($sp)    # store the ra & s regs on the stack
        sw      $s7, 28($sp)
        sw      $s6, 24($sp)
        sw      $s5, 20($sp)
        sw      $s4, 16($sp)
        sw      $s3, 12($sp)
        sw      $s2, 8($sp)
        sw      $s1, 4($sp)
        sw      $s0, 0($sp)

# ######################################
# ##### BEGIN STUDENT CODE BLOCK 1 #####

        addi    $s0, $zero, 3       # s0 = 3, beginning of loop
        addi    $s1, $zero, 101     # s1 = 101, end of loop
    loop_a:
        addi    $a0, $s0, 0         # a0 = s0, num to test
        jal     is_prime            # test s0. if prime, v0=1
        beq     $v0, $zero, next_a  # if s0 isnt prime, check next number
        addi    $a0, $s0, 0         # a0 = s0, num to print
        jal     print_number        # if s0 is prime, print it
    next_a:
        beq     $s0, $s1, done_a    # if s0 = 101, jump out of loop
        addi    $s0, $s0, 2         # s0 = s0 + 2, skips all even numbers
        j       loop_a              # loop with next odd number
    done_a:

# ###### END STUDENT CODE BLOCK 1 ######
# ######################################


        lw      $ra, 32($sp)    # restore the ra & s regs from the stack
        lw      $s7, 28($sp)
        lw      $s6, 24($sp)
        lw      $s5, 20($sp)
        lw      $s4, 16($sp)
        lw      $s3, 12($sp)
        lw      $s2, 8($sp)
        lw      $s1, 4($sp)
        lw      $s0, 0($sp)
        addi    $sp,$sp,40      # clean up stack
    jr  $ra

#
# Name:     is_prime 
#
# Description:  checks to see if the num passed in is prime
# Arguments:    a0  The number to test to see if prime
# Returns:  v0  a value of 1 if the number in a0 is prime
#           a value of 0 otherwise
#

is_prime:
        addi    $sp,$sp,-40     # allocate stackframe (doubleword aligned)
        sw      $ra, 32($sp)    # store the ra & s regs on the stack
        sw      $s7, 28($sp)
        sw      $s6, 24($sp)
        sw      $s5, 20($sp)
        sw      $s4, 16($sp)
        sw      $s3, 12($sp)
        sw      $s2, 8($sp)
        sw      $s1, 4($sp)
        sw      $s0, 0($sp)

# ######################################
# ##### BEGIN STUDENT CODE BLOCK 2 #####

# n = number to test
# for ( s1 = 3; s1 != (n-1)/2; s1++ ) {
#   if ( ( n % s1 ) == 0 ) { n is not_prime }
# }

# since this algorithm only works for primes greater than 6,
# special cases must be added for when n = 3 or n = 5

        # t0 = n
        addi    $t0, $a0, 0         # t0 = a0
        
        # special cases if n = 3 or n = 5
        addi    $t3, $zero, 3       # t3 = 3
        beq     $t0, $t3, prime     # if t0 = 3, prime
        addi    $t5, $zero, 5       # t5 = 5
        beq     $t0, $t5, prime     # if t0 = 5, prime
        
        # s1 = 3
        addi    $s1, $zero, 3       # s1 = 3, beginning of loop
        
        # s0 = (n-1) / 2
        addi    $s0, $t0, 0         # s0 = t0 (guaranteed to be odd)
        addi    $s0, $s0, -1        # s0 = t0-1
        addi    $t2, $zero, 2       # t2 = 2
        div     $s0, $s0, $t2       # s0 = t0-1 / 2, end of loop

    loop_b:
        div     $t0, $s1
        mfhi    $t1                 # t1 = t0 % s1
        beq     $t1, $zero, not_prime   # if t1 = 0, not prime
        beq     $s1, $s0, prime     # if s1 = s0, loop finished, num is prime
        addi    $s1, $s1, 1         # s1++
        j       loop_b              # test next number
    prime:
        addi    $v0, $zero, 1       # number is prime, v0 = 1
        j       done_b              # exit loop and return
    not_prime:
        addi    $v0, $zero, 0       # number not prime, v0 = 0
        j       done_b              # exit loop and return
    done_b:

# ###### END STUDENT CODE BLOCK 2 ######
# ######################################

        lw      $ra, 32($sp)    # restore the ra & s regs from the stack
        lw      $s7, 28($sp)
        lw      $s6, 24($sp)
        lw      $s5, 20($sp)
        lw      $s4, 16($sp)
        lw      $s3, 12($sp)
        lw      $s2, 8($sp)
        lw      $s1, 4($sp)
        lw      $s0, 0($sp)
        addi    $sp,$sp,40      # clean up the stack
    jr  $ra

#
# Name;     print_number 
#
# Description:  This routine reads a number then a newline to stdout
# Arguments:    a0,the number to print
# Returns:  nothing
#
print_number:

        li  $v0,PRINT_INT
        syscall         #print a0

        la  $a0, newline
        li      $v0,PRINT_STRING
        syscall         #print a newline

        jr      $ra
