#include "Lights.h"
#include <vector>
#include <iostream>

Lights::Lights(vector<int> s , int c, int r, int e ) : 
  start(s), col(c), row(r), end(e) {
  fail.push_back(-1);
 }

vector<vector<int> > Lights::getNeighbors(vector<int> conf) {
  vector<vector<int> > retv;
  for( int i = 0; i < row*col; i++ ) {
    vector<int> tmp = pushButton(conf,i);
    if (tmp != fail)
      retv.push_back(tmp);
  }
  return retv;
}

vector<int> Lights::pushButton(vector<int> conf, int i) {
  vector<int> rval = conf;
  int r = i+1; // right square
  int l = i-1; // left square
  int u = i-col; // up square
  int d = i+col; // down square
  int ub = row * col; // upper bound
  
  if (conf[i] == 2)
    rval[i] = 3;
  if (conf[i] == 3)
    rval[i] = 2;
  if (conf[i] == 1)
    return fail;
  if (conf[i] == 0)
    return fail;

  // check right
  if (!(i+1 % col) == 0) {
    if (conf[r] == 2)
      rval[r] = 3;
    if (conf[r] == 3)
      rval[r] = 2;
    if (conf[r] == 1)
      return fail;
    if (conf[r] == 0)
      return fail;
  }

  // check left
  if ((i+1 % col) != 1) {
    if (conf[l] == 2)
      rval[l] = 3;
    if (conf[l] == 3)
      rval[l] = 2;
    if (conf[l] == 1)
      return fail;
    if (conf[l] == 0)
      return fail;
  }

  // check up
  if (!(u < 0)) {
    if (conf[u] == 2)
      rval[u] = 3;
    if (conf[u] == 3)
      rval[u] = 2;
    if (conf[u] == 1)
      return fail;
    if (conf[u] == 0)
      return fail;
  }

  // check down
  if (!(d >= ub)) {
    if (conf[d] == 2)
      rval[d] = 3;
    if (conf[d] == 3)
      rval[d] = 2;
    if (conf[d] == 1)
      return fail;
    if (conf[d] == 0)
      return fail;
  }

  return rval;
}

void Lights::display(list<vector<int> > disp) {
}

void Lights::dispF(list<vector<int> > disp, ostream &os) {
  while(!disp.empty()) {
    vector<int> v1 = disp.front();
    for( int i = 1; i <= v1.size(); i++ ) {
      if (v1[i-1] == 2) {
        os << '.';
      } else if (v1[i-1] == 3) {
        os << '*';
      } else {
        os << v1[i-1];
      }
      if( (i % col) == 0 )
        os << endl;
    }
    disp.pop_front();
    os << endl;
  }
}

bool Lights::isSol(vector<int> test) {
  int count = 0;
  for( int i = 0; i < row * col; i++ ) {
    if( test[i] == 1 || test[i] == 3 )
      count++;
  }

  if( count == end )
    return true;
  return false;
}

