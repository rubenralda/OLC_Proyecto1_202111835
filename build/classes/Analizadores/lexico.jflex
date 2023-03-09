package Analizadores;
import java_cup.runtime.*;

%%

%class Lexico
%unicode
%public
%cup
%line
%char
%column
%ignorecase	

%{
  StringBuffer expreEntrada = new StringBuffer();
  private Symbol symbol(int type) {
    System.out.println(yytext()); 
    return new Symbol(type, yyline, yycolumn, yytext());
  }
  private Symbol symbol(int type, Object value) {
    System.out.println(expreEntrada);
    return new Symbol(type, yyline, yycolumn, value);
  }
%}



LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

comentarioCompuesto   = "<!" [^*] ~"!>" | "<!" "!"+ ">"
comentarioSimple     = "//" {InputCharacter}* {LineTerminator}?
comentario = {comentarioCompuesto} | {comentarioSimple}

letraMayus = [A-Z]
    
letraMinus = [a-z]
    
digito = [0-9]

letras = {letraMayus} | {letraMinus} | "_"
identificador = {letras}({letras} | {digito})*

conjuntoASCII = [\x32-\x47\x58-\x64\x91-\x96]// FALTA VERIFICAR

%state STRING
    
%%

<YYINITIAL> "CONJ"                 { return symbol(sym.CONJ); }
    
<YYINITIAL> {
  {letraMayus}                    { return symbol(sym.LETRA_MAYUS); }
  {letraMinus}                    { return symbol(sym.LETRA_MINUS); }
  {identificador}                 { return symbol(sym.IDENTIFICADOR); }
  {digito}                        { return symbol(sym.DIGITO); }
  \"                              { expreEntrada.setLength(0);expreEntrada.append('\"'); yybegin(STRING); }
  \\n                             { return symbol(sym.SALTO); }
  \\\"                            { return symbol(sym.COMILLA_DOBLE); }
  \\\'                            { return symbol(sym.COMILLA_SIMPLE); } 

  "%%"                            { return symbol(sym.SEPARADOR); }
  "{"                             { return symbol(sym.LLAVE_ABRE); }
  "}"                             { return symbol(sym.LLAVE_CIERRA); }
  "+"                             { return symbol(sym.CERRADURA_POSITIVA); }
  ";"                             { return symbol(sym.PUNTO_COMA); }
  "."                             { return symbol(sym.CONCATENACION); }
  "*"                             { return symbol(sym.CERRADURA_KLENEE); }
  "|"                             { return symbol(sym.ALTERNANCIA); }
  "?"                             { return symbol(sym.OPCIONAL); }
  ":"                             { return symbol(sym.DOS_PUNTOS); }
  ">"                           { return symbol(sym.ASIGNACION); }
  ","                             { return symbol(sym.COMA); }
  "~"                             { return symbol(sym.CONJUNTO); }
  "-"                           { return symbol(sym.MENOS); }
  {conjuntoASCII}                 { return symbol(sym.CONJ_ASCII); }

  {comentario}                    { /* ignore */ }
  {WhiteSpace}                    { /* ignore */ }

}

<STRING> {
  \"                             { yybegin(YYINITIAL); expreEntrada.append('\"');
                                return symbol(sym.ENTRADA, expreEntrada.toString()); }
  [^\n\r\"\\]+                   { expreEntrada.append( yytext() ); }
  \\t                            { expreEntrada.append('\t'); }
  \\n                            { expreEntrada.append('\n'); }
  \\r                            { expreEntrada.append('\r'); }
  \\\"                           { expreEntrada.append('\"'); }
  \\                             { expreEntrada.append('\\'); }
  \\\'                           { expreEntrada.append('\''); }
}

/* error fallback */
<YYINITIAL> . {
  String errLex = "Error Lexico: <" + yytext() + "> En la linea: " + (yyline) + " y columna: " + (yycolumn); 
  System.out.println(errLex); 
}