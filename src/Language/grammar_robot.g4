grammar grammar_robot;

AND : 'and' ;
OR : 'or' ;
NOT : 'not' ;
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

main: MAIN '{' definition* '}';

definition: assignment
    | loop
    | checker
    | output
    ;

input: IN '(' ')' ;

output: OUT '(' expr ')' ';' ;

assignment: ID '=' expr ';';

loop: WHILE '(' conditions ')' '{' definition* '}';

checker: IF '(' conditions ')' '{' definition* '}' (ELSE '{' definition* '}')?;

conditions: condition (logical_operator condition)*;

logical_operator: AND | OR;

condition: expr comparison_operator expr
         | NOT '(' expr comparison_operator expr ')'
         ;

comparison_operator: EQ | LT | GT | LE | GE | NE;

expr: ID
    | FLOAT
    | INT
    | boolean
    | expr MINUS expr
    | expr DIVISION expr
    | expr MULTIPLY expr
    | expr MODULO expr
    | input
    ;

boolean: TRUE | FALSE;