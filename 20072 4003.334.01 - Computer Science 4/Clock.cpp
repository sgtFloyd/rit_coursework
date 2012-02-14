/*
 * Clock.cpp	
 *
 * Description: Clock object created by the main program that is passed to
 *              the solver to find the solution
 *
 * Version:
 *      $Id: Clock.cpp,v 1.2 2008/02/02 09:12:33 gjm7082 Exp $
 * 
 * Revisions:
 *      $Log: Clock.cpp,v $
 *      Revision 1.2  2008/02/02 09:12:33  gjm7082
 *      removed getSol method
 *
 *      Revision 1.1  2008/01/13 02:29:55  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#include <vector>
#include <queue> 
#include <iostream>
#include "Clock.h"

Clock::Clock(int h, int s, int e) :
  hours(h), start(s), end(e) {
}

vector<vector<int> > Clock::getNeighbors(vector<int> config){
  
  vector<vector<int> > retv;
  vector<int> fwd;
  vector<int> rev;
  
  int n1 = (config.front() + 1)%hours;
  int n2 = (config.front() - 1)%hours;
  
  if(n1 == 0){
    n1 = hours;
  }
  if(n2 == 0){
    n2 = hours;
  }

  fwd.push_back(n1);
  rev.push_back(n2);
  
  
  retv.push_back(fwd);
  retv.push_back(rev);
  return retv;
}

bool Clock::isSol(vector<int> test) {
  for(int i = 0; i < test.size(); i++) {
    if(test[i] == end) {
      return true;
    }
  }
  return false;
}

void Clock::display(list<vector<int> > disp){
  
  cout << "The solution is: " << endl;
  while(!disp.empty()) {
    vector<int> v1 = disp.front();
    cout << v1[0] << endl;
    disp.pop_front();
    cout << endl;
  }

}
