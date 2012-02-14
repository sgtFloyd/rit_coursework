/*
 * Solver.h	
 *
 * Description: Solver class to find the solution to the given puzzles
 *
 * Version:
 *      $Id: Solver.h,v 1.3 2008/02/02 09:14:21 gjm7082 Exp $
 * 
 * Revisions:
 *      $Log: Solver.h,v $
 *      Revision 1.3  2008/02/02 09:14:21  gjm7082
 *      *** empty log message ***
 *
 *      Revision 1.2  2008/01/13 02:35:17  gjm7082
 *      removed un-needed struct definitino
 *
 *      Revision 1.1  2008/01/13 02:30:21  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#ifndef SOLVER_H
#define SOLVER_H

#include "Puzzle.h"
#include <vector>
#include <map>
#include <list>


class Solver{
  
 public:
  Solver(Puzzle &p);
  
 public:	
  list<vector<int> > solve(vector<int> s);
  list<vector<char> > solve(vector<char> c);

 private: 
  Puzzle &puzz;
  int init;
  int count;
  
  typedef std::map< vector<int>, vector<int> > vectMap;
  vectMap vMap;
  vectMap::iterator vMapIter;
};

#endif

