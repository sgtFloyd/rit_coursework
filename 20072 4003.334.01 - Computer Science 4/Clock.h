/*
 * Clock.h	
 *
 * Description: Clock class for the puzzle
 *
 * Version:
 *      $Id: Clock.h,v 1.2 2008/02/02 09:13:02 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: Clock.h,v $
 *      Revision 1.2  2008/02/02 09:13:02  gjm7082
 *      removed getSol method
 *
 *      Revision 1.1  2008/01/13 02:30:06  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#ifndef CLOCK_H
#define CLOCK_H

#include "Puzzle.h"
#include <vector>

class Clock: public Puzzle {
  
 public:
  Clock(int h, int s, int e);
  
 public: 
  vector<vector<int> > getNeighbors(vector<int> config);
  bool isSol(vector<int> test);
  
 public:
  void display(list<vector<int> > dispList);
  
 private:
  int hours;
  int start;
  int end;

};

#endif

