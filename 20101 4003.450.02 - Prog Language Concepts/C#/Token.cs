
using System;

/**
 * 
 * Author: Gabriel Smith (ges7506@rit.edu)
 * PLC Project 1 09.26.10
 * 
 * This class defines all the required tokens to scan
 * and parse through a defined DFA. It also has the ability
 * to store and retreive Strings with specific tokens.
 * 
 */ 
public class Token {
  /* terminals */
  public const int EOF = 0;
  public const int error = 1;
  public const int SEMI = 2;
  public const int STAR = 3;
  public const int COLON = 4;
  public const int AT = 5;
  public const int COMMA = 6;
  public const int END = 7;
  public const int STRING = 8;
  public const int ID = 9;
  public const int QUOTE = 10;

  private int type;
  private String text;
  private int value;

  public Token(int type, String text) {
      this.type = type;
      this.text = text;
      value = 0;
  }

  public int getType() {
      return type;
  }

  public String getText() {
      return text;
  }

  public int getValue() {
      return value;
  }

  public String toString() {
      return text;
  }
}
