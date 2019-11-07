grammar CardStack;

stack
    : (card '\n')* EOF
    ;

card
    : front+=side '\t' back+=side
    ;

side
    : htmlTag
    | plainText
    ;

htmlTag
    : '<' () '>'
    ;

unorderedList
    : elems+=listItem
    ;

listItem
    : '<li>' side '</li>'
    ;

plainText
    : TEXT
    ;

TEXT
    : [a-zA-Z0-9]*
    ;

//WS  :   [ \t\r\n] -> channel(HIDDEN);
