#
# Created by makemake (Sparc Jul 27 2005) on Mon Oct 12 22:45:01 2009
#

#
# Definitions
#

.SUFFIXES:
.SUFFIXES:	.a .o .c .C .cpp
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

CC =		cc
CXX =		CC

RM = rm -f
AR = ar
LINK.c = $(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS)
LINK.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS)
COMPILE.c = $(CC) $(CFLAGS) $(CPPFLAGS) -c
COMPILE.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) -c

########## Flags from header.mak

#
# This header.mak file will set up all necessary options for compiling
# and linking C and C++ programs which use OpenGL and/or GLUT.
#
# To use the GNU C or C++ compiler, you will need to add one of the
# following lines:
#
#   CC = gcc
#   CCC = g++
#
# Also, if you want to take advantage of GDB's extra debugging features,
# change "-g" in the CFLAGS and LIBFLAGS macro definitions to "-ggdb".
#
OWGLUTINC=/opt/csw/include
OWGLUTLIB=/opt/csw/lib
WRCINC=/usr/local/pub/wrc/courses/cg1/project2

INCLUDE = -I$(OWGLUTINC) -I$(WRCINC)
LIBDIRS = -L$(OWGLUTLIB)

LDLIBS = -lglut -lGLU -lGL -lGLw -lXmu -lXext -lX11 -lm

CFLAGS = -g $(INCLUDE)
CCFLAGS =  $(CFLAGS)
CXXFLAGS = $(CFLAGS)

LIBFLAGS = -g $(LIBDIRS) $(LDLIBS)
CLIBFLAGS = $(LIBFLAGS)
CCLIBFLAGS = $(LIBFLAGS)

########## End of flags from header.mak


CPP_FILES =	
C_FILES =	 drawLine.c support.c testline1.c testline2.c
H_FILES =	
SOURCEFILES =	$(H_FILES) $(CPP_FILES) $(C_FILES)
.PRECIOUS:	$(SOURCEFILES)
OBJFILES =	 drawLine.o support.o

#
# Main targets
#

all:	  testline1 testline2

testline1:	testline1.o $(OBJFILES)
	$(CC) $(CFLAGS) -o testline1 testline1.o $(OBJFILES) $(CLIBFLAGS)

testline2:	testline2.o $(OBJFILES)
	$(CC) $(CFLAGS) -o testline2 testline2.o $(OBJFILES) $(CLIBFLAGS)

#
# Dependencies
#

drawLine.o:	
support.o:	
testline1.o:	
testline2.o:	

#
# Housekeeping
#

Archive:	archive.tgz

archive.tgz:	$(SOURCEFILES) Makefile
	tar cf - $(SOURCEFILES) Makefile | gzip > archive.tgz

clean:
	-/bin/rm -r $(OBJFILES) testline1.o testline2.o ptrepository SunWS_cache .sb ii_files core 2> /dev/null

realclean:        clean
	/bin/rm -rf   testline1 testline2
