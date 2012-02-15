#!/usr/bin/perl -w
#
# Version:
# $Id$
# 
# author name: Gabriel Smith
# description:  
#   Write a program that processes command line arguments as an array of strings
#   that may represent file names. For each string, print the file name on a
#   line by itself, ending the line with a colon, open the file, and print each
#   line of the file with a leading '/* 00n */' character sequence. That is,
#   each line output starts with a c-style comment that encloses a 3-digit-wide,
#   0-filled line number. After printing each file's content, print a line of 55
#   '=' characters to separate one file from the next. If any string in the
#   array is not a valid file name, print a warning message and proceed to
#   process the next string. (the program must still print a line of dashes
#   after an invalid name.) 
# ###################################################################

foreach my $file (@ARGV) {
  print $file,":\n" ;
  if (open( FILE, $file )) {
    # if file exists and opens
    my $counter = "000";
    while (<FILE>) {
      print "/* ", ++$counter, " */ ", $_ ;
    }
    &printline;
    close( FILE ) ;
	} else {
    # if file doesn't exist or doesn't open
    print "can't open file $!";
    &printline;
  }
}

sub printline {
  print "\n=======================================================\n";
}

# ###################################################################
# Revision History:
# $Log$
# 
__END__

=head1 NAME
cat.pl

=head1 SYNOPSIS
cat.pl

=head1 DESCRIPTION
   Write a program that processes command line arguments as an array of strings
   that may represent file names. For each string, print the file name on a
   line by itself, ending the line with a colon, open the file, and print each
   line of the file with a leading '/* 00n */' character sequence. That is,
   each line output starts with a c-style comment that encloses a 3-digit-wide,
   0-filled line number. After printing each file's content, print a line of 55
   '=' characters to separate one file from the next. If any string in the
   array is not a valid file name, print a warning message and proceed to
   process the next string. (the program must still print a line of dashes
   after an invalid name.) 

=head1 AUTHOR
Gabriel Smith (ges7506@cs.rit.edu)

=head1 README
This is for PLC.

=head1 PREREQUISITES
none.

