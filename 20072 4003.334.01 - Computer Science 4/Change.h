#ifndef CHANGE_H
#define CHANGE_H

#include "Puzzle.h"

class Change: public Puzzle {

public:
  Change(int desiredChange, vector<int> denominations);

  vector<vector<int> > getNeighbors(vector<int> config);
  bool isSol(vector<int> config);
  void display(list<vector<int> > config);

private:
  int win;
  vector<int> coins;

};

#endif
