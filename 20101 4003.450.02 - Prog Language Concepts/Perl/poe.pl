#!/usr/bin/perl -w
#
# Version:
# $Id$
# 
# author name: Gabriel Smith
# description:  
#   Edgar Allan Poe wrote The Raven, a sombre poem about love and a raven who
#   taunts him. This progam must take the text of the poem,
#   http://www.cs.rit.edu/usr/local/pub/bks/plc/perl/raven.txt , and mutilate
#   the text as follows IN ORDER:
#   - change every occurrence of 'nore' to 'Nore';
#   - change all occurrences of a lower case alphabetic character followed by
#     the text 'ore' to repeat the 4 letter sequence twice. For example, 'core'
#     input would change to 'corecore' output.
#   The program reads the text from a filename on the command line and outputs a
#   mutilated version of the text on the standard output. 
# ###################################################################

use strict ;

open( FILE, $ARGV[0] ) || die( "cannot open file. $!\n" );
print $ARGV[0],":\n" ;
while ( my $ln = <FILE> ) {
    $ln =~ s/nore/Nore/g;
    $ln =~ s/([a-z])ore/\1ore\1ore/g;
    print $ln;
}
close( FILE );

# ###################################################################
# Revision History:
# $Log$
# 
__END__

=head1 NAME
poe.pl

=head1 SYNOPSIS
poe.pl

=head1 DESCRIPTION
   Edgar Allan Poe wrote The Raven, a sombre poem about love and a raven who
   taunts him. This progam must take the text of the poem,
   http://www.cs.rit.edu/usr/local/pub/bks/plc/perl/raven.txt , and mutilate
   the text as follows IN ORDER:
   - change every occurrence of 'nore' to 'Nore';
   - change all occurrences of a lower case alphabetic character followed by
     the text 'ore' to repeat the 4 letter sequence twice. For example, 'core'
     input would change to 'corecore' output.
   The program reads the text from a filename on the command line and outputs a
   mutilated version of the text on the standard output. 

=head1 AUTHOR
Gabriel Smith (ges7506@cs.rit.edu)

=head1 README
PLC Lab1-0

=head1 PREREQUISITES
none.

