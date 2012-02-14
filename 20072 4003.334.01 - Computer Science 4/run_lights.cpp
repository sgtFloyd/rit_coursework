#include "Solver.h"
#include "Lights.h"
#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

//function to process the input and return the vector
vector<int> process(istream &is, int c, int r) {
  char s;
  vector<int> init;
  for( int i = 0; i < r; i++ ) {
    for( int j = 0; j < c; j++ ) {
      is >> s;
      if( s != '.' && s!= '*' && s != '0' && s != '1' ) {
        vector<int> bad;
        bad.push_back(-1);
        return bad;
      } else {
        if( s == '.' ) 
          init.push_back(2);
        if( s == '*' )
          init.push_back(3);
        if( s == '0' )
          init.push_back(0);
        if( s == '1' )
          init.push_back(1);
      }
    }
  }
  cout << endl;
  return init;
}

int main( int argc, const char *argv[] ) {
  int r;
  int c;
  char s;
  vector<int> init;
  filebuf inbuf;

  if( argc != 4 ) {
    cerr << "Usage: lights input-method output-dest #-lights-remaining" << endl;
    return 1;
  }
  
  if (*argv[1] != '-' ) {
    if (!inbuf.open(argv[1], ios_base::in)) {
      cerr << "Error opening file " << argv[1] << endl;
      return -1;
    }
  }

  // create input stream from input file or cin
  istream is(inbuf.is_open() ? &inbuf : cin.rdbuf());

  //get dimensions for puzzle and initialize the configuration vector
  is >> c >> r;
  if( r < 1 || c < 1 ) {
    cerr << "Invalid dimensions for puzzle" << endl;
    return -1;
  }
  init = process(is, c, r);

  if( init[0] == -1 ) {
    cerr << "Illegal input character" << endl;
    return -1;
  }

  //solving functions
  int win = atoi(argv[3]);
  Lights l( init, c, r, win);
  Solver sol(l);
  list<vector<int> > answer = sol.solve(init);
  
  //create ostream
  filebuf obuff;  
  if (*argv[2] != '-' ) {
    if (!obuff.open(argv[2], ios_base::out)) {
      cerr << "Error opening output file " << argv[2] << endl;
      return -1;
    }
  }
  ostream os(obuff.is_open() ? &obuff : cout.rdbuf());
  
  //output functions
  if( answer.empty() ) {
    os << "No Solution Possible" << endl;
  } else {
    l.dispF(answer, os);
  }


}
