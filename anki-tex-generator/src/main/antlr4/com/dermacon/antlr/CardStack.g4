grammar CardStack;

//@metaHeader {
//package com.dermacon.antlr;
//}

stack
    : (card '\n')* EOF
    ;

card
    : front=sideContainer DELIMITER back=sideContainer
    (DELIMITER tags=IDENTIFIER)?
    ;

sideContainer
    : elems+=sideNode*
    ;

sideNode
    : divBlock
    | orderedList
    | unorderedList
    | boldItem
    | italicItem
    | underlinedItem
    | plainText
    | imageItem
    ;

divBlock
    : DIV_OPENING_TAG sideContainer DIV_CLOSING_TAG
    ;

boldItem
    : B_OPENING_TAG sideContainer B_CLOSING_TAG
    ;

italicItem
    : I_OPENING_TAG sideContainer I_CLOSING_TAG
    ;
underlinedItem
    : U_OPENING_TAG sideContainer U_CLOSING_TAG
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
    : paragraph+=word+
    ;

word: ADDITIONAL_CHARS* IDENTIFIER ADDITIONAL_CHARS*
    ;

imageItem
    : IMG_OPENING_TAG IDENTIFIER IMG_TYPE IMG_CLOSING_TAG
    ;

IMG_TYPE: '.png' | '.jpg';
IDENTIFIER: [„“/[\]_ ():äÄöÖüÜßa-zA-Z0-9-]*;
ADDITIONAL_CHARS: [/,\-&;!?.];

DELIMITER: '\t' | '\n';

IMG_OPENING_TAG: '<img src=' (' ')*;
IMG_CLOSING_TAG: (' ')? ('/')? '>';

DIV_OPENING_TAG: '<div' (' class=' IDENTIFIER)? '>';
DIV_CLOSING_TAG: '</div>';

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

U_OPENING_TAG: '<u>';
U_CLOSING_TAG: '</u>';

//WS  :   [ ] -> channel(HIDDEN);
