/*
 * Farmer.cpp
 *
 * Description: Farmer object created by the main program to solve the
 *              farmer puzzle.
 *
 * Version:
 *      $Id: Farmer.cpp,v 1.1 2008/02/02 09:13:56 gjm7082 Exp gjm7082 $
 * 
 * Revisions:
 *      $Log: Farmer.cpp,v $
 *      Revision 1.1  2008/02/02 09:13:56  gjm7082
 *      Initial revision
 *
 *
 * Author: Gabriel Smith
 * Author: Gordon Mascarenhas
 */

#include "Farmer.h"
#include <vector>
#include <queue>
#include <iostream>

Farmer::Farmer(vector<int> s ) :
  start(s) 
{ 
  end.push_back(3);
  end.push_back(3);
  end.push_back(3);
}


vector<vector<int> > Farmer::getNeighbors(vector<int> config ){

  vector<vector<int> > moves; //all possible configs
  vector<vector<int> > retv; //all legal configs to return

  vector<int> temp;
  for (int i=0; i<3; i++) {
    if (config[i] < 3) {
      temp = config;
      temp[i]++;
      moves.push_back(temp);
    }
  }

  for (int i=0; i<3; i++) {
    if( config[i] > 1 ) {
      temp = config;
      temp[i]--;
      moves.push_back(temp);
    }
  }

  for (int i=0; i<2; i++) {
    for (int j=i+1; j<3; j++) {
      temp = config;
      if (config[i] == 2) {
        if (config[j] == 1 || config[j] == 3) {
          temp[i] = config[j];
          temp[j] = config[i];
          moves.push_back(temp);
        }
      }
      if (config[j] == 2) {
        if (config[i] == 1 || config[i] == 3) {
          temp[i] = config[j];
          temp[j] = config[i];
          moves.push_back(temp);
        }
      }
     
    }
  }

  for( int i = 0; i < moves.size(); i++ ) { 
    if(isSol(moves[i]) ) {
      retv.push_back( moves[i] );
    } else if(isLegal(moves[i]) ) {
      retv.push_back(moves[i]);
    }
  }
       

  return retv;

}

bool Farmer::isLegal( vector<int> v) {
  if( v.at(0) == v.at(2) ) {
    return false;
  }
  if( v.at(1) == v.at(2) ) {
    return false;
  }
  return true;

}

bool Farmer::isSol( vector<int> v) {
  if( v[0] == end[0] )
    if( v[1] == end[1] )
      if( v[2] == end[2] )
        return true;
  return false;
}

void Farmer::display( list<vector<int> > disp) {
  
  cout << "\nThe solution is:\n " << endl;
  while(!disp.empty()) {
    vector<int> v1 = disp.front();
    for(int i = 0; i < 3; i++ ) {
      if( i==0 ) {
        cout << "Fox";
      } else if( i == 1 ) {
        cout << "Corn";
      } else cout << "Goose";
      
      if( v1[i] == 1 ) {
        cout << " at Start ";
      } else if( v1[i] == 2 ) {
        cout << " on Ferry ";
      } else cout << " at End ";
      
      cout << endl;
        
    }
    disp.pop_front();
    cout << endl;
  }

}


