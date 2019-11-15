grammar CardStack;

//@header {
//package com.dermacon.antlr;
//}

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

TEXT: [a-zA-Z0-9]*;
UL_OPENING_TAG: '<ul>';
UL_CLOSING_TAG: '</ul>';
OL_OPENING_TAG: '<ol>';
OL_CLOSING_TAG: '</ol>';


//WS  :   [ \t\r\n] -> channel(HIDDEN);
