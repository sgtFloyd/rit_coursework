using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

/**
 * 
 * Author: Gabriel Smith (ges7506@rit.edu)
 * PLC Project 1 09.26.10
 * 
 * This class acts as a Triplet to associate a
 * start state, valid input, and the correct following
 * state.
 * 
 */ 
class Transition {
    String baseState;
    String input;
    String nextState;

    public Transition( String baseState, String input, String nextState ) {
        this.baseState = baseState;
        this.input = input;
        this.nextState = nextState;
    }

    public String getBaseState() {
        return baseState;
    }

    public String getInput() {
        return input;
    }

    public String getNextState() {
        return nextState;
    }
}
