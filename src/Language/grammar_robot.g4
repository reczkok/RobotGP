grammar grammar_robot;

AND : 'AND' ;
OR : 'OR' ;
NOT : 'NOT' ;
AS : '=' ;
COMMA : ',' ;
SEMI : ';' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;

EQ : '==' ;
LT : '<' ;
GT : '>' ;
LE : '<=' ;
GE : '>=' ;
NE : '!=' ;

PLUS: '+';
MINUS: '-';
DIVISION: '/';
MULTIPLY: '*';
MODULO: '%';

TRUE: 'true';
FALSE: 'false';

OUT: 'out';
IN: 'in';

MAIN: 'main()';
WHILE: 'while';
IF: 'if';
ELSE: 'else';

FLOAT: [-]?([0-9]*[.])[0-9]+;
INT: [-]?([0]|[1-9][0-9]*);
ID: [a-zA-Z_][a-zA-Z_0-9]* ;
WS: [ \t\n\r\f]+ -> skip ;


program: main EOF ;

main: MAIN LCURLY definition* RCURLY;

definition: assignment
    | loop
    | checker
    | output
    ;

input: IN LPAREN RPAREN;

output: OUT LPAREN expr RPAREN SEMI;

assignment: ID AS expr SEMI;

loop: WHILE LPAREN conditions RPAREN LCURLY definition* RCURLY;

checker: IF LPAREN conditions RPAREN LCURLY definition* RCURLY (else_block)?;

else_block: ELSE LCURLY definition* RCURLY;

conditions: condition
           |LPAREN conditions RPAREN logical_operator LPAREN conditions RPAREN
           |NOT LPAREN conditions RPAREN;

logical_operator: AND | OR;

condition: expr comparison_operator expr
         | LPAREN expr comparison_operator expr RPAREN
         ;

comparison_operator: EQ | LT | GT | LE | GE | NE;



expr: LPAREN expr RPAREN
    | ID
    | FLOAT
    | INT
    | boolean
    | expr MINUS expr
    | expr DIVISION expr
    | expr MULTIPLY expr
    | expr MODULO expr
    | expr PLUS expr
    | input
    ;

boolean: TRUE | FALSE;