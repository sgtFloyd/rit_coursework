#
# Created by gmakemake (Sparc Jul 27 2005) on Fri Feb 22 17:57:19 2008
#

#
# Definitions
#

.SUFFIXES:
.SUFFIXES:  .a .o .c .C .cpp
.c.o:
        $(COMPILE.c) $<
.C.o:
        $(COMPILE.cc) $<
.cpp.o:
        $(COMPILE.cc) $<
.c.a:
        $(COMPILE.c) -o $% $<
        $(AR) $(ARFLAGS) $@ $%
        $(RM) $%
.C.a:
        $(COMPILE.cc) -o $% $<
        $(AR) $(ARFLAGS) $@ $%
        $(RM) $%
.cpp.a:
        $(COMPILE.cc) -o $% $<
        $(AR) $(ARFLAGS) $@ $%
        $(RM) $%

CC =        gcc
CXX =       g++

RM = rm -f
AR = ar
LINK.c = $(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS)
LINK.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS)
COMPILE.c = $(CC) $(CFLAGS) $(CPPFLAGS) -c
COMPILE.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) -c

########## Default flags (redefine these with a header.mak file if desired)
CXXFLAGS =  -ggdb
CFLAGS =    -ggdb
CLIBFLAGS = -lm
CCLIBFLAGS =    
########## End of default flags


CPP_FILES =  Change.cpp Clock.cpp Farmer.cpp Lights.cpp Solver.cpp run_change.cpp run_clock.cpp run_farmer.cpp run_lights.cpp
C_FILES =   
H_FILES =    Change.h Clock.h Farmer.h Lights.h Puzzle.h Solver.h
SOURCEFILES =   $(H_FILES) $(CPP_FILES) $(C_FILES)
.PRECIOUS:  $(SOURCEFILES)
OBJFILES =   Change.o Clock.o Farmer.o Lights.o Solver.o

#
# Main targets
#

all:     change clock farmer lights 

change: run_change.o $(OBJFILES)
    $(CXX) $(CXXFLAGS) -o change run_change.o $(OBJFILES) $(CCLIBFLAGS)

clock:  run_clock.o $(OBJFILES)
    $(CXX) $(CXXFLAGS) -o clock run_clock.o $(OBJFILES) $(CCLIBFLAGS)

farmer: run_farmer.o $(OBJFILES)
    $(CXX) $(CXXFLAGS) -o farmer run_farmer.o $(OBJFILES) $(CCLIBFLAGS)

lights: run_lights.o $(OBJFILES)
    $(CXX) $(CXXFLAGS) -o lights run_lights.o $(OBJFILES) $(CCLIBFLAGS)

#
# Dependencies
#

Change.o:    Change.h Puzzle.h
Clock.o:     Clock.h Puzzle.h
Farmer.o:    Farmer.h Puzzle.h
Lights.o:    Lights.h Puzzle.h
Solver.o:    Puzzle.h Solver.h
change.o:    Change.h Puzzle.h Solver.h
clock.o:     Clock.h Puzzle.h Solver.h
farmer.o:    Farmer.h Puzzle.h Solver.h
lights.o:    Lights.h Puzzle.h Solver.h

#
# Housekeeping
#

Archive:    archive.tgz

archive.tgz:    $(SOURCEFILES) Makefile
    tar cf - $(SOURCEFILES) Makefile | gzip > archive.tgz

clean:
    -/bin/rm -r $(OBJFILES) run_change.o run_clock.o run_farmer.o run_lights.o core 2> /dev/null

realclean:        clean
    /bin/rm -rf  change clock farmer lights 
