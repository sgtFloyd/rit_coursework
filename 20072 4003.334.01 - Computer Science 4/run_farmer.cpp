/*
 * farmer.cpp
 *
 * Description: Main program for the farmer puzzle
 *
 * Version:
 *      $Id: farmer.cpp,v 1.1 2008/02/02 09:14:55 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: farmer.cpp,v $
 *      Revision 1.1  2008/02/02 09:14:55  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#include "Solver.h"
#include "Farmer.h"
#include <vector>

using namespace std;

int main( int aggc, char* argv[] ) {

  vector<int> values;
  values.push_back(1);
  values.push_back(1);
  values.push_back(1);
  Farmer f(values);
  Solver s(f);

  list<vector<int> > answer = s.solve(values);
  f.display(answer);

};
