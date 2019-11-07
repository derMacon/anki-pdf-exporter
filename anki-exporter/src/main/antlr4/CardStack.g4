grammar CardStack;

stack
    : (card '\n')* EOF
    ;

card
    : front=TEXT '\t' back=TEXT
    ;

TEXT
    : [a-zA-Z0-9]*
    ;

//WS  :   [ \t\r\n] -> channel(HIDDEN);
