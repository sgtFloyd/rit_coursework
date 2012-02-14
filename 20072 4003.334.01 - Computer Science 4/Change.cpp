#include "Change.h"
#include <iostream>

Change::Change(int desiredChange, vector<int> denominations) {
	win = desiredChange;
	coins = denominations;
}

vector<vector<int> > Change::getNeighbors(vector<int> config) {
	vector<vector<int> > retVal;
	for (int i=0; i<config.size(); i++) {
		vector<int> temp = config;
		temp[i]++;
		if (!((temp[i] * coins[i]) > win)) {
			retVal.push_back(temp);
		}
	}
	return retVal;
}

bool Change::isSol(vector<int> config) {
	int total(0);
	for (int i=0; i<config.size(); i++) {
		total += ( coins[i] * config[i] );
	}
	if (total == win)
		return true;
	return false;
}

void Change::display(list<vector<int> > disp) {
	while (!disp.empty()) {
		vector<int> config = disp.front();
		disp.pop_front();
		if (disp.empty()) {
			for (int i=0; i<config.size(); i++) {
				cout << coins[i] << " unit coins: " << config[i] << endl;
			}
			cout << endl;
		}
	}
}
