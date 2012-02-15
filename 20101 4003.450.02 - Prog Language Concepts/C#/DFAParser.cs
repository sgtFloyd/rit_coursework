using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

/**
 * 
 * Author: Gabriel Smith (ges7506@rit.edu)
 * PLC Project 1 09.26.10
 * 
 * This class takes a scanner and uses it to parse through a
 * given file using the grammar as defined in class. It also has
 * the ability to take the parsed file and generate a new class
 * which will test whether a given string matches the given
 * DFA definition.
 * 
 */ 
class DFAParser {
    private DFAScanner s;   // Where to get the next token from
    private Token cur;      // The current token
    private Boolean debug;  // Are we in debug mode?

    // Used for output file generation
    String startState = "";
    List<String> states = new List<String>();
    List<String> terminalStates = new List<String>();
    List<Transition> transitions = new List<Transition>();

    // Temporary state storage while parsing
    String curState = "";

    public void outputToFile( String className ) {
        TextWriter writer = null;
        try {
            writer = new StreamWriter( className+".cs" );
        } catch (Exception e) {
            Console.WriteLine("File Error:  " + e.Message);
            return;
        }
        //WRITE FILE
        writer.WriteLine("/**");
        writer.WriteLine(" * Author: Gabriel Smith (ges7506@rit.edu)");
        writer.WriteLine(" * PLC Project 1 09.26.10");
        writer.WriteLine(" * Generated from DFAParser.cs");
        writer.WriteLine(" */");


        // imports
        writer.WriteLine("using System;");
        writer.WriteLine("using System.Collections.Generic;");
        writer.WriteLine("using System.Linq;");
        writer.WriteLine("using System.Text;");
        writer.WriteLine("");

        writer.WriteLine("class "+ className +" {");

        // storage
        writer.WriteLine("    Boolean debug = false;");
        writer.WriteLine("    int startState = "+ states.IndexOf(startState) +";");
        writer.WriteLine("    List<iTransition> transitions = new List<iTransition>();");
        writer.WriteLine("    List<int> terminalStates = new List<int>();");

        // constructor
        writer.WriteLine("    public "+className+"() {");
        writer.WriteLine("        buildTransitions();");
        writer.WriteLine("        buildTerminalStates();");
        writer.WriteLine("    }");

        // build transitions
        writer.WriteLine("    private void buildTransitions() {");
            foreach ( Transition t in transitions ) {
                int baseState = states.IndexOf(t.getBaseState());
                int nextState = states.IndexOf(t.getNextState());
                Char[] inputArray = t.getInput().ToCharArray();
                foreach ( Char c in inputArray ) {
                    if ( c != '\'' )
                        writer.WriteLine("      transitions.Add( new iTransition("+ baseState + ", '" + c + "' ,"+ nextState + ") );");
                }
            }
        writer.WriteLine("    }");

        // build terminal states
        writer.WriteLine("    private void buildTerminalStates() {");
        foreach( String s in terminalStates ) {
            writer.WriteLine("        terminalStates.Add(" + states.IndexOf(s) + ");");
        }
        writer.WriteLine("    }");


        writer.WriteLine("    public Boolean scan( String dfaInput ) {");
        writer.WriteLine("        Char[] charArray = dfaInput.ToCharArray(); int curState = startState; log(\"{Start State\"+curState+\"}\");");
        writer.WriteLine("        foreach ( Char c in charArray ) {");
        writer.WriteLine("            bool transitioned = false; log(\"\"+c);");
        writer.WriteLine("            foreach ( iTransition trans in transitions ) {");
        writer.WriteLine("                if ( !transitioned && trans.getBaseState() == curState ) {");
        writer.WriteLine("                    if ( !transitioned && trans.getInput().Equals(c) ) {");
        writer.WriteLine("                        curState = trans.getNextState();");
        writer.WriteLine("                        transitioned = true; log(\"{State\"+curState+\"}\");");
        writer.WriteLine("                    }}}if ( !transitioned ) return false;}");
        writer.WriteLine("        if ( terminalStates.Contains( curState ) ) { return true;");
        writer.WriteLine("        } else { return false;");
        writer.WriteLine("    }}");

        writer.WriteLine("    private void log( String msg ) { if ( debug ) { Console.WriteLine( msg ); } }");
        writer.WriteLine("    public void setDebug( Boolean debug ) { this.debug = debug; }");
        writer.WriteLine("}");


        // iTransition internal utility class
        writer.WriteLine("class iTransition {");
        writer.WriteLine("    int baseState; char input; int nextState;");
        writer.WriteLine("    public iTransition( int baseState, char input, int nextState ) { this.baseState = baseState; this.input = input; this.nextState = nextState; }");
        writer.WriteLine("    public int getBaseState() { return baseState; }");
        writer.WriteLine("    public char getInput() { return input; }");
        writer.WriteLine("    public int getNextState() { return nextState; }\n}");
        writer.Close();
    }


    /**
     * Create a new parser that reads from the specified scanner.
     *
     * @param s the scanner from which tokens are to be read.
     */
    public DFAParser( DFAScanner s ) {
	    this.s = s;
	    debug = false;
    }

    /**
     * Set debug mode for the parser.
     *
     * @param newDebug if true the parser is put into debug mode.
     */
    public void setDebug( Boolean debug ){
        this.debug = debug;
    }

    /**
     * Print a message to standard output if in debug mode.
     *
     * @param msg the message to print.
     */
    private void log( String msg ) {
        if ( debug ) {
            Console.WriteLine( msg );
        }
    }

    /**
     * Parse the stream of tokens read from the scanner until an
     * error occurs or the EOF token is seen.
     *
     * @throws ParseException if a parse error occurs.
     * @throws IOException if an error occurs reading a token.
     */
    public void parse() {
        cur = s.nextToken();
        program();
    }

    /**
     * Match the current token to the one given.  If they match read
     * the next token.  If they do not match throw an exception.
     *
     * @param type the type of token to match.
     * @throws ParseError if the type specified and the type of the current
     * token do not match.
     * @throws IOException if an error occurs reading the next token.
     */
    private void match( int type ) {
        if ( cur.getType() == type ) {
            cur = s.nextToken();
        } else {
            throw new Exception("Parse Exception, tokens do not match.\n"
                +" Found: " + cur.getType() + ", Expected: " + type );
        }
    }

    /* The following methods represent each of the productions in the grammar:
     *   program -> stmt_list stmt_term $$
     *   stmt_term -> * ID
     *   stmt_list -> stmt stmt_list | e
     *   stmt -> state trans_list ;
     *   trans_list -> trans trans_tail
     *   trans_tail -> , trans trans_tail | e
     *   trans -> string id
     *   state -> state_reg | state_term
     *   state_reg -> id
     *   state_term -> id @
     *
     * The switch statements are derived from the predict sets for each of
     * the productions.  See the parsing notes for details.
     */

    private void program() {
        switch( cur.getType() ) {
            case Token.ID:
            case Token.EOF:
                log( "program -> stmt_list stmt_term $$" );
                stmt_list();
                stmt_term();
                match( Token.EOF );
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void stmt_term() {
        switch( cur.getType() ) {
            case Token.STAR:
                log( "stmt_term -> * ID" );
                match( Token.STAR );
                curState = cur.getText();
                match( Token.ID );
                startState = curState;
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void stmt_list() {
        switch( cur.getType() ) {
            case Token.ID:
                log( "stmt_list -> stmt stmt_list" );
                stmt();
                stmt_list();
                break;
            case Token.STAR:
            case Token.EOF:
                log( "stmt_list ->  e" );
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void stmt() {
        switch ( cur.getType() ) {
            case Token.ID:
                log( "stmt -> state trans_list ;" );
                state();
                trans_list();
                match( Token.SEMI );
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void trans_list() {
        switch ( cur.getType() ) {
            case Token.STRING:
                log( "trans_list -> trans trans_tail" );
                trans();
                trans_tail();
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void trans_tail() {
        switch ( cur.getType() ) { 
            case Token.COMMA:
            case Token.STRING:
                log( "trans_tail -> , trans trans_tail" );
                match( Token.COMMA );
                trans();
                trans_tail();
                break;
            case Token.SEMI:
            case Token.EOF:
                log( "trans_tail -> e" );
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void trans() {
        switch ( cur.getType() ) {
            case Token.STRING:
                log( "trans -> string id" );
                String curInput = cur.getText();
                match( Token.STRING );
                String curNextState = cur.getText();
                match( Token.ID );
                transitions.Add( new Transition( curState, curInput, curNextState ) );
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }

    private void state() {
        switch ( cur.getType() ) {
            case Token.ID:
                log( "state -> state_reg | state_term" );
                curState = cur.getText();
                match( Token.ID );
                switch ( cur.getType() ) {
                    case Token.COLON:
                        log( "state_reg -> id :" );
                        match( Token.COLON );
                        states.Add( curState );
                        break;
                    case Token.AT:
                        log( "state_term -> id @" );
                        match( Token.AT );
                        states.Add( curState );
                        terminalStates.Add( curState );
                        break;
                    default:
                        throw new Exception("Parse Exception");
                }
                break;
            default:
                throw new Exception("Parse Exception");
        }
    }
}