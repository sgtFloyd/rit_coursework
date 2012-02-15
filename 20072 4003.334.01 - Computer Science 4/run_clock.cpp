/*
 * clock.cpp
 *
 * Description: Main program for the clock puzzle
 *
 * Version:
 *      $Id: clock.cpp,v 1.1 2008/01/13 02:30:34 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: clock.cpp,v $
 *      Revision 1.1  2008/01/13 02:30:34  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#include "Clock.h"
#include "Solver.h"
#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

int main( int argc, char *argv[] ) {
  
  if( argc != 4 ) {
    cerr << "Usage: clock hours_on_dial clock_time true_time" << endl;
    return 1;
  }     

  int hours = atoi(argv[1]);
  int curTime = atoi(argv[2]);
  int trueTime = atoi(argv[3]);
  
  Clock cl(hours, curTime, trueTime);
  Solver s(cl);
  vector<int> values;
  values.push_back(curTime);
  list<vector<int> > answer = s.solve(values);
  cl.display(answer);
    
};
