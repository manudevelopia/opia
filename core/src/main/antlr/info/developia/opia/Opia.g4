grammar Opia;

@header {
package info.developia.opia;
}

program : statement+;

statement : let | show ;

let : 'var' VAR '=' INT ;
show : 'show' (INT | VAR) ;

VAR : ('a'..'z'|'A'..'Z')+ ;
INT : '0'..'9'+ ;
WS : [ \n\t]+ -> skip;
