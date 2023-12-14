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

LEFT: 'left';
RIGHT: 'right';
UP: 'up';
DOWN: 'down';
TOP_LEFT: 'top_left';
TOP_RIGHT: 'top_right';
BOTTOM_LEFT: 'bottom_left';
BOTTOM_RIGHT: 'bottom_right';

BOX_X: 'box_x';
BOX_Y: 'box_y';
SELF_X: 'self_x';
SELF_Y: 'self_y';

GET_COORDINATE: 'get_coordinate';

LOOK: 'look';
MOVE: 'move';

MAIN: 'main()';
WHILE: 'while';
IF: 'if';
ELSE: 'else';

FLOAT: [-]?([0-9]*[.])[0-9]+;
ID: [a-zA-Z_][a-zA-Z_0-9]* ;
WS: [ \t\n\r\f]+ -> skip ;


program: main EOF ;

main: MAIN '{' definition* '}';

definition: assignment
    | loop
    | checker
    | directive
    ;

directive: MOVE '(' direction ')' ';';

observation: LOOK '(' direction ')' | GET_COORDINATE '(' command_target ')';

assignment: ID '=' expr ';';

loop: WHILE '(' condition ')' '{' definition* '}';

checker: IF '(' condition ')' '{' definition* '}' (ELSE '{' definition* '}')?;

condition: expr comparison_operator expr;

comparison_operator: EQ | LT | GT | LE | GE | NE;

expr: ID
    | FLOAT
    | NOT expr
    | expr AND expr
    | expr OR expr
    | expr PLUS expr
    | expr MINUS expr
    | expr DIVISION expr
    | expr MULTIPLY expr
    | expr MODULO expr
    | observation
    ;

direction: LEFT
    | RIGHT
    | UP
    | DOWN
    | TOP_LEFT
    | TOP_RIGHT
    | BOTTOM_LEFT
    | BOTTOM_RIGHT
    ;

command_target: BOX_X
    | BOX_Y
    | SELF_X
    | SELF_Y
    ;