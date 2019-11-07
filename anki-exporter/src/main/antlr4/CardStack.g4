grammar CardStack;

stack
    : (card '\n')* EOF
    ;

card
    : front=TEXT '\t' back=TEXT
    ;

TEXT
    : ~[\])]+
    ;

//WS  :   [ \t\r\n] -> channel(HIDDEN);
