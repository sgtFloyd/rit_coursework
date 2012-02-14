#include "Solver.h"
#include "Change.h"
#include <iostream>
#include <vector>

using namespace std;

int main( int argc, const char *argv[] ) {

	if (argc < 3) {
		cerr << "Proper Usage: change <change_desired> <denomination_1> ... <denomination_n>" << endl;
		return -1;
	}
	
	int desiredChange = atoi(argv[1]);
	vector<int> denominations;
	vector<int> init; //initial configuration
	for (int i=2; i<argc; i++) {
		int tmp = atoi(argv[i]);
		if (!(tmp < 1)) {
			denominations.push_back(tmp);
			init.push_back(0);
		}
	}
	
	Change puzz(desiredChange, denominations);
	Solver sol(puzz);
	
	list<vector<int> > answer = sol.solve(init);
	if (!answer.empty()) {
		puzz.display(answer);
	} else {
		cout << "No Solution Found" << endl;
	}
}
