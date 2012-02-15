#!/usr/bin/perl -w 

sub main {
	# get path
	my($path);
	if (!defined($ARGV[0])) {
		print "Enter path to scan and press Enter: ";
		$path = <>;
	} else {
		$path = $ARGV[0];
	}
	# build filelist
	opendir(DIR,$path)
		or die "Unable to open the path: $!\n";
	@filelist = readdir(DIR)
		or die "Unable to read path: $!\n";
	closedir(DIR)
		or die "Unable to close path: $!\n";
	
	# output
	foreach $file (@filelist) {
		next if (-d $file);
		next if ($file eq ".");
		next if ($file eq "..");
		print $file, "\n";
	}
}

main();