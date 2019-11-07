grammar CardStack;

stack
    : card EOF
    ;

card
    : front=TEXT '\t' back=TEXT
    ;

TEXT
    : ~[\])]+
    ;

WS  :   [ \t\r\n] -> channel(HIDDEN);
