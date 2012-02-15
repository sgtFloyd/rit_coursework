/*
 * Puzzle.h 
 *
 * Description: Puzzle interface that all puzzles implement.
 *
 * Version:
 *      $Id: Puzzle.h,v 1.2 2008/02/02 09:14:07 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: Puzzle.h,v $
 *      Revision 1.2  2008/02/02 09:14:07  gjm7082
 *      removed getSol method
 *
 *      Revision 1.1  2008/01/13 02:30:14  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#ifndef PUZZLE_H
#define PUZZLE_H

#include <vector>
#include <list>

using namespace std;

class Puzzle {
 
public:
  virtual vector<vector<int> > getNeighbors(vector<int> config) = 0;
  virtual bool isSol(vector<int> test) = 0;
  virtual void display(list<vector<int> >) = 0;
};

#endif

