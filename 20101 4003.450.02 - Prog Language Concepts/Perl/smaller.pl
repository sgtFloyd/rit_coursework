#!/usr/bin/perl -w
#
# Version:
# $Id$
# 
# author name: Gabriel Smith
# description:  
#   Write a program with subroutines, that determines whether a number is less
# than the target number.
#
# - The first parameter of the 'smaller' subroutine is the number to compare
#   against all the others. The rest of the parameters are the numbers to
#   process.
# - The subroutine must process an input parameter list: the target number and
#   the list of numbers to process. The subroutine must RETURN the result; it
#   must not set a global variable (that would be a 'side effect').
# - If there are command line arguments, the script should take its input from
#   the command line and expect each argument to be a number; don't worry about
#   type-checking these inputs.
# - If there are no command line arguments, then read standard input for numbers
#   until the user hits control-d to end the input stream, and assemble the list
#   from these numbers. Here is the prompt when reading from standard input:
#     'Enter list of numbers on one or more lines (control-D to end):'
# - The output should be similar to the provided example, which shows the input
#   number sequence and the found numbers collection. 
# ###################################################################

sub main {
  my($base, $num);
  my(@numlist, @smlist);
  # build rawlist, an array of all numbers entered
  if (!defined($ARGV[0])) {
    # input from stdin
    print "Enter list of numbers on one or more lines (control-D to end):\n";
    while (<>) {
      my @split = split(/\s+/, $_);
      push(@numlist, @split);
    }
  } else {
    # input from cmd line
    @numlist = @ARGV;
  }
  
  # start output
  print "result of smaller ";
  foreach $num (@numlist) {
    print $num, " ";
  }

  #define base and remove it from numlist
  $base = $numlist[0];
  shift(@numlist);
  
  @smlist = smaller($base, @numlist);
  print "is: [ ";
  foreach $num (@smlist) {
    print $num, " ";
  }
  print "]\n";
}

sub smaller {
  my($base, @numlist) = @_ ;
  my(@smlist);
  foreach my $num (@numlist) {
    if ($num < $base) {
      push(@smlist, $num);
    }
  }
  return @smlist;
}

main;

# ###################################################################
# Revision History:
# $Log$
#
# 
__END__

=head1 NAME
smaller.pl

=head1 SYNOPSIS
smaller.pl

=head1 DESCRIPTION
   Write a program with subroutines, that determines whether a number is less
 than the target number.

 - The first parameter of the 'smaller' subroutine is the number to compare
   against all the others. The rest of the parameters are the numbers to
   process.
 - The subroutine must process an input parameter list: the target number and
   the list of numbers to process. The subroutine must RETURN the result; it
   must not set a global variable (that would be a 'side effect').
 - If there are command line arguments, the script should take its input from
   the command line and expect each argument to be a number; don't worry about
   type-checking these inputs.
 - If there are no command line arguments, then read standard input for numbers
   until the user hits control-d to end the input stream, and assemble the list
   from these numbers. Here is the prompt when reading from standard input:
     'Enter list of numbers on one or more lines (control-D to end):'
 - The output should be similar to the provided example, which shows the input
   number sequence and the found numbers collection. 

=head1 AUTHOR
Gabriel Smith (ges7506@cs.rit.edu)

=head1 README
This is for PLC lab 1-1

=head1 PREREQUISITES
none.

