using System;

%%

%class DFAScanner
%function nextToken
%type Token

%eofval{ 
return (new Token(Token.EOF, "")); 
%eofval} 

%%

";"							{ Console.WriteLine("SEMI"); return new Token(Token.SEMI, ""); }
"*"							{ Console.WriteLine("STAR"); return new Token(Token.STAR, ""); }
":"							{ Console.WriteLine("COLON"); return new Token(Token.COLON, ""); }
"@"							{ Console.WriteLine("AT"); return new Token(Token.AT, ""); }
","							{ Console.WriteLine("COMMA"); return new Token(Token.COMMA, ""); }
"$$"						{ Console.WriteLine("EOF"); return new Token(Token.END, ""); }

"//"[^\n]*                  { Console.WriteLine("COMMENT"); break; /* Ignore Comments */ }


[A-Za-z][0-9A-Za-z]*		{ Console.WriteLine("ID: "+ yytext()); return new Token(Token.ID, yytext()); }
[\'][^\']*[\']				{ Console.WriteLine("STRING: "+ yytext()); return new Token(Token.STRING, yytext()); }

[ \t\b\f\r\n]+				{  break; /* Ignore whitespace */ }
.                           { Console.WriteLine( "Unexpected character: " + yytext() ); break; }