grammar CardStack;

//@header {
//package com.dermacon.antlr;
//}

stack
    : (card '\n')* EOF
    ;

card
    : front=sideContainer DELIMITER back=sideContainer
    ;

sideContainer
    : elems+=sideNode*
    ;

sideNode
    : orderedList
    | unorderedList
    | boldItem
    | recursiveItem
    | plainText
    ;

boldItem
    : B_OPENING_TAG sideContainer B_CLOSING_TAG
    ;

recursiveItem
    : I_OPENING_TAG sideContainer I_CLOSING_TAG
    ;

orderedList
    : OL_OPENING_TAG elems+=listItem* OL_CLOSING_TAG
    ;

unorderedList
    : UL_OPENING_TAG elems+=listItem* UL_CLOSING_TAG
    ;

listItem
    : LI_OPENING_TAG sideContainer LI_CLOSING_TAG
    ;

plainText
    : TEXT
    ;

TEXT: [a-zA-Z0-9]*;

DELIMITER: '\t' | '\n';

UL_OPENING_TAG: '<ul>';
UL_CLOSING_TAG: '</ul>';

OL_OPENING_TAG: '<ol>';
OL_CLOSING_TAG: '</ol>';

LI_OPENING_TAG: '<li>';
LI_CLOSING_TAG: '</li>';

B_OPENING_TAG: '<b>';
B_CLOSING_TAG: '</b>';

I_OPENING_TAG: '<i>';
I_CLOSING_TAG: '</i>';


//WS  :   [ \t\r\n] -> channel(HIDDEN);
