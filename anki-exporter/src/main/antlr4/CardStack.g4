grammar CardStack;

stack
    : (card '\n')* EOF
    ;

card
    : front=sideContainer '\t' back=sideContainer
    ;

sideContainer
    : elems+=sideNode*
    ;

sideNode
    : orderedList
    | unorderedList
    | boldItem
    | plainText
    ;

boldItem
    : '<b>' sideContainer '</b>'
    ;

orderedList
    : '<ol>' elems+=listItem '</ol>'
    ;

unorderedList
    : '<ul>' elems+=listItem '</ul>'
    ;

listItem
    : '<li>' sideContainer '</li>'
    ;

plainText
    : TEXT
    ;

TEXT
    : [a-zA-Z0-9]*
    ;

//WS  :   [ \t\r\n] -> channel(HIDDEN);
