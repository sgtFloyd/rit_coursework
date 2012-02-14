#ifndef LIGHTS_H
#define LIGHTS_H

#include "Puzzle.h"
#include <fstream>

class Lights: public Puzzle {

 public:
  Lights(vector<int> s, int c, int r, int e);
  
 public:
  vector<vector<int> > getNeighbors(vector<int> config );
  vector<int> pushButton(vector<int> conf, int i);
  bool isSol(vector<int> test);

 public:
  void display(list<vector<int> > disp);
  void dispF(list<vector<int> > disp, ostream &os );

 private:
  vector<int> start;
  vector<int> fail;
  int end;
  int row;
  int col;
  
};

#endif
