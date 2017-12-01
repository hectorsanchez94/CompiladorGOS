%{
#include <stdio.h>
#include <conio.h>
#include "C:\Users\Ivan\Desktop\Analizador/AnalizadorLexico.h";
%}

%option noyywrap
%option yylineno

ignora " "|\t|\n
caracter [A-Za-z]
digito [0-9]
boleano "true"|"false"

%%

{ignora}*							{;}
"."									{return ('.');}
"{"									{return ('{');}
"}"                             	{return ('}');}
";"                             	{return (';');}
","                             	{return (',');}
"("                             	{return ('(');}
")"                             	{return (')');}
"["                             	{return ('[');}
"]"                             	{return (']');}
"si"								{return IF;}
"otra_condi"                    	{return IFELSE;}
"sino"                          	{return ELSE;}
"inicio"                        	{return INICIO;}
"fin"                        		{return FIN;}
"entero"                        	{return T_ENTERO;}
"decimal"                       	{return T_DECIMAL;}
"boleano"                          	{return T_BOLEANO;}
"cadena"                          	{return T_CADENA;}
"para"                          	{return FOR;}
"mientras"                      	{return WHILE;}
"circulo"							{return CIRCULO;}
"rectangulo"						{return RECTANGULO;}
"triangulo"							{return TRIANGULO;}
"pause"								{return PAUSE;}
"iniciar"							{return INICIAR;}
"parar"								{return DETENER;}
"+"                            		{return SUMA;}
"-"                            		{return RESTA;}
"*"                            		{return MULTIPLICACION;}
"/"                             	{return DIVISION;}
"++"                            	{return AUMENTAR;}
"--"                            	{return DISMINUIR;}
":="                            	{return ASIGNADOR;}
">"                             	{return MAYOR;}
"<"                             	{return MENOR;}
"=="                            	{return IGUAL;}
">="                            	{return MAYORIGUAL;}
"<="                            	{return MENORIGUAL;}
"<>"                            	{return DIFERENTE;}
{digito}+				{return ENTERO;}
{digito}+"."{digito}			{return DECIMAL;}
{boleano}				{return BOLEANO;}
"\""({digito}|{caracter}|" ")*"\""	{return CADENA;}
{caracter}({caracter}|(digito))*	{return NOMBRECAMPO;}


%%

yyerror(char * msg)
{
printf("%s\n",msg);
}

int main(int argc, char *argv[])
{
//char NombreArchivo[50];
//printf("Ruta del archivo: ");
//gets(NombreArchivo);
yyin=fopen(argv[1],"rt");
if(yyin==NULL)
printf("\nNo se puede leer el archivo");
else
{
if(!yyparse()){
	printf("Exito-%i",yylineno);
}else{printf("Error-%i",yylineno-2);}
}
fclose(yyin);
//getch();
}

