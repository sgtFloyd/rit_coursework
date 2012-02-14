/*
 * Solver.cpp	
 *
 * Description: The solver program to find a solution to the clock puzzle
 *
 * Version:
 *      $Id: Solver.cpp,v 1.2 2008/02/02 09:14:37 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: Solver.cpp,v $
 *      Revision 1.2  2008/02/02 09:14:37  gjm7082
 *      *** empty log message ***
 *
 *      Revision 1.1  2008/01/13 02:30:27  gjm7082
 *      Initial revision
 *
 *
 *
 * Author: Gordon Mascarenhas
 * Author: Gabriel Smith
 */

#include "Solver.h"
#include <iostream>
#include <vector>
#include <list>
#include <queue>
#include <map>

using namespace std;

Solver::Solver(Puzzle &p) : 
  puzz(p){
  count = 0;
}

list<vector<int> > Solver::solve(vector<int> s){

  queue<vector<int> > q;
  list<vector<int> > solution;
  
  q.push(s);
  vMap[s] = s;

  while(!q.empty()){

    vector<int> c = q.front();
    q.pop();
    
    vector<vector<int> > ncons = puzz.getNeighbors(c);

    for(int i = 0; i < ncons.size(); i++){
      if( vMap.end() == vMap.find( ncons[i] )){
        vMap[ncons[i]] = c; 
        q.push(ncons[i]);
        
        if( puzz.isSol(ncons[i]) ){
          vMapIter = vMap.find(ncons[i]);
          solution.push_front( vMapIter->first );
          
          while( (vMapIter->first) != s){
            vMapIter = vMap.find( vMapIter->second );
            solution.push_front( vMapIter->first );
          }
          return solution;
        }

      }
    }
  }
}
  


 //Clock fucntions
