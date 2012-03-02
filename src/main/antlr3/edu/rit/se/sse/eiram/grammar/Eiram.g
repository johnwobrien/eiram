grammar Eiram;

@lexer::header  { package edu.rit.se.sse.eiram.grammar; }
@parser::header { package edu.rit.se.sse.eiram.grammar; }

program	:	NL * line ( NL + line ) * NL ?
	;
	
line	:	( WS * label WS * ',' ) ? 
		WS * instruction
		( WS + ( literal | label ) ) ?
		WS *
	|	WS + // For garbage
	;
	
instruction
	:	'load'
	|	'store'
	;
	
literal	:	HEX_PREFIX HEX_NUMBER
	;
	
label	:	ID
	;
	
ID
	:	'_' * ALPHA ( ALPHA | NUMBER | '_' ) *
	;
	
HEX_NUMBER
	:	( NUMBER | 'a'..'f' | 'A'..'F' ) +
	;
	
HEX_PREFIX
	:	'0x'
	|	'0X'
	;
	
fragment ALPHA
	:	'a'..'z'
	|	'A'..'Z'
	;
	
fragment NUMBER
	:	'0'..'9'
	;
	
COMMENT	:	( '//' ~( '\r' | '\n' ) * ( '\r' ? ) '\n' ) { skip(); }
	|	( '/*' ( options { greedy = false; } : . ) * '*/' ) { skip(); }
	;
	
NL	:	'\r' | '\n'
	;
	
WS	:	' ' | '\t'
	;