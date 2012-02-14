/*
 * Farmer.h
 *
 * Description: Farmer class for the puzzle
 *
 * Version:
 *      $Id: Farmer.h,v 1.1 2008/02/02 09:13:34 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: Farmer.h,v $
 *      Revision 1.1  2008/02/02 09:13:34  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#ifndef FARMER_H
#define FARMER_H

#include "Puzzle.h"

class Farmer: public Puzzle {

 public:
  Farmer(vector<int> s);

 public:
  vector<vector<int> > getNeighbors(vector<int> config);
  bool isLegal(vector<int> check);
  bool isSol(vector<int> test);

 public:
  void display(list<vector<int> > dispList);

 private:
  vector<int> start;
  vector<int> end;

};

#endif
