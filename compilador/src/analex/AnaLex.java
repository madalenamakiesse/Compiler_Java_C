/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;

import al.par.Par;
import enums.Tokens;
import java.util.ArrayList;

/**
 *
 * @author Madalena Makiesse
 */
public class AnaLex {
    /*
        numLinha: guarda o número de linha de cada Par.
        pos: guarda a posição do caractere da string que se quer que retorne.
        posP: guarda a posição da palavra em estudo.
        ch: é para guardar o caractere para facilitar a comparacao dos caminhos.
        palavraR: guarda todo lexema.
        t: permite guardar o token, lexema e a linha resultado da análise do automato. 
        pares: guarda um conjunto de t.
    */
    private int numLinha;
    private int pos;
    private int posP;
    private int estado;
    private char ch;
    private String linha;
    private String palavraR;
    private Par t;
    private ArrayList<Par> pares;
    private ArrayList<String> contFich;

    public AnaLex() {
        numLinha = 1;
        pos=0;
        posP=0;
        pares = new ArrayList<Par>();
        contFich = new ArrayList<String>();
    }
    //Recebe o conteúdo do ficheiro
    public void setContFich(ArrayList<String> contFich) {
        this.contFich = contFich;
    }
    
    //ler() é uma funcao que entrega um caractere a funcao analex()
    private char ler(){
        char l='\0' ;
        if(pos==linha.length()){
            posP++;
            numLinha++;
            pos=0;
        }
        else{
            if(pos!=-1){
               l = linha.charAt(pos);
            }
            pos++;
        }
        return l;
    }
    
    //voltacaractere() é uma função que é usada sempre que for lido outro no diagrama de transição
    private void voltacaractere(){
        pos--;
    }
    
    //analex() é uma função que executa o automato
    private Par analex(){
        Par aux= new Par();
        estado = 0;
        palavraR ="";
        aux.setLinha(numLinha);
        if(estado==0){
            ch = ler();
            if((ch=='_')|| (ch>='A'&& ch<='Z')||(ch>='a'&&ch<='z')){
                palavraR = Character.toString(ch);
                estado = 1;
            }
            if(ch=='+'){
                  palavraR = Character.toString(ch);
                  estado = 2;
            }
            if(ch=='-'){
                palavraR = Character.toString(ch);
                estado = 5;
            }
            if(ch>='0'&&ch<='9'){
                palavraR = Character.toString(ch);
                estado = 3;
            }
            if(ch=='>'){
                palavraR = Character.toString(ch);
                estado = 6;
            }
            if(ch=='<'){
                palavraR = Character.toString(ch);
                estado = 12;
            }
            else if(ch=='='){
                palavraR = Character.toString(ch);
                estado = 18;
            }
            if(ch=='!'){
                palavraR = Character.toString(ch);
                estado = 21;
            }
            if(ch=='*'){
                palavraR = Character.toString(ch);
                estado = 24;
            }
            if(ch=='%'){
                palavraR = Character.toString(ch);
                estado = 30;
            }
            if(ch=='"'){
                palavraR = Character.toString(ch);
                estado = 39;
            }
            if(ch=='/'){
                palavraR = Character.toString(ch);
                estado = 41;
            }
            else if(ch=='#'){
                palavraR = Character.toString(ch);
                estado = 50;
            }
            if(ch==',' || ch=='^' || ch==')' || ch=='&' || ch=='(' || ch=='_' || ch==']' || ch=='{' || ch=='[' || ch=='.' || ch=='}' || ch==';' || ch=='?' || ch==',' || ch==':' || ch=='|' || ch=='~' || ch=='$'){
                palavraR = Character.toString(ch);
                estado = 49;
            }
        }
        if(estado==1){
            ch = ler();
            if(ch=='_'||(ch >='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>='a'&&ch<='z')||(ch>='0'&&ch<='9')){
                    while(ch=='_'||(ch >='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>='a'&&ch<='z')||(ch>='0'&&ch<='9')){
                          palavraR = palavraR + ch;  
                            ch = ler();
                    }
                    aux.setLexema(palavraR);
                    if(palavraR.compareTo("auto")==0)
                        aux.setToken(Tokens.AUTO.toString());
                    else if(palavraR.compareTo("break")==0)
                        aux.setToken(Tokens.BREAK.toString());
                    else if(palavraR.compareTo("case")==0)
                        aux.setToken(Tokens.CASE.toString());
                    else if(palavraR.compareTo("char")==0)
                        aux.setToken(Tokens.CHAR.toString());
                    else if(palavraR.compareTo("const")==0)
                        aux.setToken(Tokens.CONST.toString());
                    else if(palavraR.compareTo("continue")==0)
                        aux.setToken(Tokens.CONTINUE.toString());    
                    else if(palavraR.compareTo("double")==0)
                        aux.setToken(Tokens.DOUBLE.toString());  
                    else if(palavraR.compareTo("default")==0)
                        aux.setToken(Tokens.DEFAULT.toString());  
                    else if(palavraR.compareTo("do")==0)
                        aux.setToken(Tokens.DO.toString());  
                    else if(palavraR.compareTo("else")==0)
                        aux.setToken(Tokens.ELSE.toString());  
                    else if(palavraR.compareTo("enum")==0)
                        aux.setToken(Tokens.ENUM.toString());  
                    else if(palavraR.compareTo("extern")==0)
                        aux.setToken(Tokens.EXTERN.toString());  
                    else if(palavraR.compareTo("short")==0)
                        aux.setToken(Tokens.SHORT.toString());  
                    else if(palavraR.compareTo("for")==0)
                        aux.setToken(Tokens.FOR.toString());  
                    else if(palavraR.compareTo("goto")==0)
                        aux.setToken(Tokens.GOTO.toString());  
                    else if(palavraR.compareTo("if")==0)
                        aux.setToken(Tokens.IF.toString());  
                    else if(palavraR.compareTo("int")==0)
                        aux.setToken(Tokens.INT.toString());  
                    else if(palavraR.compareTo("long")==0)
                        aux.setToken(Tokens.LONG.toString());  
                    else if(palavraR.compareTo("register")==0)
                        aux.setToken(Tokens.REGISTER.toString());  
                    else if(palavraR.compareTo("return")==0)
                        aux.setToken(Tokens.RETURN.toString());  
                    else if(palavraR.compareTo("float")==0)
                        aux.setToken(Tokens.FLOAT.toString());  
                    else if(palavraR.compareTo("signed")==0)
                        aux.setToken(Tokens.SIGNED.toString());  
                    else if(palavraR.compareTo("sizeof")==0)
                        aux.setToken(Tokens.SIZEOF.toString());  
                    else if(palavraR.compareTo("static")==0)
                        aux.setToken(Tokens.STATIC.toString());  
                    else if(palavraR.compareTo("struct")==0)
                        aux.setToken(Tokens.STRUCT.toString());  
                    else if(palavraR.compareTo("switch")==0)
                        aux.setToken(Tokens.SWITCH.toString());  
                    else if(palavraR.compareTo("typedef")==0)
                        aux.setToken(Tokens.TYPEDEF.toString());  
                    else if(palavraR.compareTo("union")==0)
                        aux.setToken(Tokens.UNION.toString());  
                    else if(palavraR.compareTo("unsigned")==0)
                        aux.setToken(Tokens.UNSIGNED.toString());  
                    else if(palavraR.compareTo("void")==0)
                        aux.setToken(Tokens.VOID.toString());  
                    else if(palavraR.compareTo("votatile")==0)
                        aux.setToken(Tokens.VOTATILE.toString());  
                    else if(palavraR.compareTo("while")==0)
                        aux.setToken(Tokens.WHILE.toString());  
                    
                    else{
                        aux.setToken(Tokens.ID.toString()); 
                    }
                    voltacaractere();
                    return aux;	
            }
            else{
                aux.setLexema(palavraR);
                aux.setToken(Tokens.ID.toString());
                voltacaractere();
                return aux;	
            }
        }

                if(estado==2){
                    ch = ler();
                    
                        if(ch>='0'&&ch<='9'){
                            palavraR = palavraR + ch;  
                            estado = 3;
                        }
                switch (ch) {
                    case '+':
                        palavraR = palavraR + ch;  
                        estado = 33;
                        break;
                    case '=':
                        palavraR = palavraR + ch;  
                        estado = 34;
                        break;
                    default:
                        this.voltacaractere();
                        estado = 35;
                        break;
                }
                    }
                if(estado==3){
                    ch = ler();
                    
                        while(ch>='0'&&ch<='9'){
                            palavraR = palavraR + ch;  
                            ch = ler();
                        }
                        if(ch=='.' || ch=='E'){
                            palavraR = palavraR + ch;  
                            estado = 4;
                        }
                        else{
                            this.voltacaractere();
                            aux.setLexema(palavraR); 
                            aux.setToken(Tokens.NUMINT.toString()); 
                            return aux;
                        }
                        
                    }
                    if(estado==4){
                    ch = ler();
                    
                            if(ch<'0'||ch>'9'){
                                    aux.setLexema(palavraR); 
                                    aux.setToken(Tokens.DESCONHECIDO.toString()); 
                                    return aux;
                            }
                            else{
                                while(ch>='0'&&ch<='9'){
                                            palavraR = palavraR + ch;  
                                            ch = ler();
                                    }
                                    aux.setLexema(palavraR); 
                                    aux.setToken(Tokens.NUMFLOAT.toString()); 
                                    this.voltacaractere();
                                    return aux;
                            }
                    }
                    if(estado==5){
                        ch = ler();
                            
                                if(ch>='0'&&ch<='9'){
                                    palavraR = palavraR + ch;  
                                    estado = 3;
                                }
                switch (ch) {
                    case '-':
                       palavraR = palavraR + ch;  
                        estado = 36;
                        break;
                    case '=':
                        palavraR = palavraR + ch;  
                        estado = 37;
                        break;
                    default:
                        this.voltacaractere();
                        estado = 38;
                        break;
                }	
                            }
                 if(estado==6){
                    ch = ler();
                    
                switch (ch) {
                    case '=':
                        palavraR = palavraR + ch;  
                        estado = 7;
                        break;
                    case '>':
                        palavraR = palavraR + ch;  
                        estado = 8;
                        break;
                    default:
                        this.voltacaractere();
                        estado = 9;
                        break;
                }
                    }
                   if(estado==7)
                        {
                            aux.setLexema(palavraR); 
                            aux.setToken(Tokens.OPRELACIONAL.toString()); 
                            return aux;
                        }
                    if(estado==8){
                    ch = ler();
                    
                        if(ch=='='){
                            palavraR = palavraR + ch;  
                            estado = 10;
                        }
                        else{
                            this.voltacaractere();
                            estado = 11;
                        }

                    }
                    if(estado==10){
                    ch = ler();
                        
                            palavraR = palavraR + ch;  
                            aux.setLexema(palavraR); 
                            aux.setToken(Tokens.ATRIBUICAODD.toString());
                            return aux;
                        }
                    if(estado==9)
                        {
                            aux.setLexema(palavraR); 
                            aux.setToken(Tokens.OPRELACIONAL.toString());
                            return aux;
                        }	
                   if(estado==11)
                        {
                            aux.setLexema(palavraR); 
                            aux.setToken(Tokens.DESLOCAMENTOD.toString());
                            return aux;
                        }
                    if(estado==12){
                        ch = ler();
                        
                            switch (ch) {
                                case '=':
                                    palavraR = palavraR + ch;  
                                    estado = 13;
                                    break;
                                case '<':
                                    palavraR = palavraR + ch;  
                                    estado = 14;
                                    break;
                                default:
                                    this.voltacaractere();
                                    estado = 15;
                                    break;
                            }
                            }
                    if(estado==13)
                        {
                            aux.setLexema(palavraR); 
                            aux.setToken(Tokens.OPRELACIONAL.toString()); 
                            return aux;
                        }
                    if(estado==14){
                        ch = ler();
                        
                            if(ch=='='){
                                palavraR = palavraR + ch;  
                                estado = 16;
                            }
                            else{
                                this.voltacaractere();
                                estado = 17;
                            }

                        }
                    if(estado==15)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.OPRELACIONAL.toString()); 
                                    return aux;
                            }
                    if(estado==16)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAODE.toString()); 
                                    return aux;
                            }	
                    if(estado==17)
                            {
                                    aux.setLexema(palavraR); 
                                aux.setToken(Tokens.DESLOCAMENTOE.toString()); 
                                    return aux;
                            }
                   if(estado==18){
                            ch = ler();
                            
                                    if(ch=='='){
                                            palavraR = palavraR + ch;  
                                            estado = 19;
                                    }
                                    else{
                                        this.voltacaractere();
                                        estado = 20;
                                    }

                            }
                    if(estado==19)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.OPRELACIONAL.toString()); 
                                    return aux;
                            }	
                    if(estado==20)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAO.toString()); 
                                    return aux;
                            }
                    if(estado==21){
                            ch = ler();
                            
                                    if(ch=='='){
                                            palavraR = palavraR + ch;  
                                            estado = 22;
                                    }
                                    else{
                                        this.voltacaractere();
                                        estado = 23;
                                    }
                            }
                   if(estado==22)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.OPRELACIONAL.toString()); 
                                    return aux;
                            }	
                    if(estado==23)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.PONTODEEXCLAMACAO.toString()); 
                                    return aux;
                            }
                    if(estado==24){
                            ch = ler();
                            
                switch (ch) {
                    case '*':
                        palavraR = palavraR + ch;  
                        estado = 25;
                        break;
                    case '=':
                        palavraR = palavraR + ch;  
                        estado = 26;
                        break;
                    default:
                        this.voltacaractere();
                        estado = 27;
                        break;
                }

                            }
                    if(estado==25) {
                            ch = ler();
                           
                                    if(ch=='='){
                                            palavraR = palavraR + ch;  
                                            estado = 28;
                                    }
                                    else{
                                        this.voltacaractere();
                                        estado = 29;
                                    }
                            }
                    if(estado==26)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAOMULT.toString()); 
                                    return aux;
                            }
                    if(estado==27)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.MULTIPLICACAO.toString()); 
                                    return aux;
                            }
                    if(estado==28)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAOEXP.toString()); 
                                    return aux;
                            }	
                    if(estado==29)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.EXPOENCIACAO.toString()); 
                                    return aux;
                            }
                    if(estado==30){
                            ch = ler();
                            
                                    if(ch=='='){
                                            palavraR = palavraR + ch;  
                                            estado = 31;
                                    }
                                    else{
                                        this.voltacaractere();
                                        estado = 32;
                                    }

                            }
                    if(estado==31)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAOPERCENTUAL.toString());
                                    return aux;
                            }	
                    if(estado==32)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.PONTODEEXCLAMACAO.toString()); 
                                    return aux;
                            }
                    if(estado==33)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.INCREMENTO.toString()); 
                                    return aux;
                            }
                    if(estado==34)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAOSOMA.toString()); 
                                    return aux;
                            }
                    if(estado==35)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.MAIS.toString()); 
                                    return aux;
                            }
                    if(estado==36)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.DECREMENTO.toString()); 
                                    return aux;
                            }
                    if(estado==37)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAOSUB.toString()); 
                                    return aux;
                            }
                    if(estado==38)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.MENOS.toString()); 
                                    return aux;

                            }
                    if(estado==39)
                            {
                                ch = ler();
                                while(ch!='"'){
                                    palavraR = palavraR + ch;
                                    ch = ler();
                                }
                                palavraR = palavraR + ch;
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.STRING.toString()); 
                                    return aux;
                            }
                    if(estado==40)
                            {
                                    palavraR = palavraR + ch;  
                                    aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAODE.toString()); 
                                    return aux;
                            }
                    if(estado==41){
                        ch = ler();
            switch (ch) {
                case '/':
                    palavraR = palavraR + ch;
                    estado = 42;
                    break;
                case '*':
                    palavraR = palavraR + ch;
                    estado = 43;
                    break;
                case '=':
                    palavraR = palavraR + ch;
                    estado = 44;
                    break;
                default:
                    this.voltacaractere();
                    estado = 45;
                    break;
            }
                
                            }
                    if(estado==42){
                        ch = ler();
                        
                        while(ch!='\0'){
                            palavraR = palavraR + ch;
                            ch = ler();
                        }
                        palavraR = palavraR + ch; 
                        estado = 46;
                    }
                    if(estado==43){
                       ch = ler();
                       while(ch!='*'){
                            palavraR = palavraR + ch;  
                            ch = ler();
                        }
                        palavraR = palavraR + ch;
                        estado = 47;
                       }
                    if(estado==44)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.ATRIBUICAODIV.toString()); 
                                    return aux;
                            }
                   if(estado==45)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.DIVISOR.toString());
                                    return aux;
                            }
                    if(estado==46)
                            {
                                aux.setLexema(palavraR); 
                                aux.setToken(Tokens.COMENTARIO.toString()); 
                                    return aux;
                            }
                    if(estado==47){
                            ch = ler();
                            
                            while(ch=='*'){
                                palavraR = palavraR + ch;  
                                ch = ler();
                            }
                            if(ch!='/'){
                                palavraR = palavraR + ch;  
                                estado = 43;
                            }
                            else {
                                //voltacaractere();
                                palavraR = palavraR + ch;  
                                estado = 48;
                            }
                            }
                    if(estado==48)
                            {
                                aux.setLexema(palavraR);
                                aux.setToken(Tokens.COMENTARIO.toString()); 
                                    return aux;
                            }
                    if(estado==49)
                            {
                                    aux.setLexema(palavraR); 

                                    if(ch==','){
                                        aux.setToken(Tokens.VIRGULA.toString()); 
                                    }
                                    if(ch=='.'){
                                        aux.setToken(Tokens.PONTO.toString()); 

                                    }
                                    if(ch==';'){
                                            aux.setToken(Tokens.PONTOVIRGULA.toString()); 
                                    }
                                    if(ch=='?'){
                                        aux.setToken(Tokens.PONTODEINTERROGACAO.toString()); 
                                    }
                                    if(ch==':'){
                                        aux.setToken(Tokens.DOISPONTOS.toString()); 
                                    }
                                    if(ch=='|'){
                                        aux.setToken(Tokens.SEPARADOR.toString()); 
                                    }
                                    if(ch=='~'){
                                            aux.setToken(Tokens.TIL.toString()); 
                                    }
                                    if(ch=='$'){
                                            aux.setToken(Tokens.SINALDEDOLAR.toString()); 
                                    }
                                    if(ch=='_'){
                                            aux.setToken(Tokens.UNDERSCORE.toString()); 
                                    }
                                    if(ch=='{'){
                                            aux.setToken(Tokens.CHAVEE.toString()); 
                                    }
                                    if(ch=='}'){
                                            aux.setToken(Tokens.CHAVED.toString()); 
                                    }
                                    if(ch=='['){
                                            aux.setToken(Tokens.PARENTESESRECTOE.toString()); 
                                    }
                                    if(ch==']'){
                                            aux.setToken(Tokens.PARENTESESRECTOD.toString()); 
                                    }
                                    if(ch=='('){
                                            aux.setToken(Tokens.PARENTESESCURVOE.toString()); 
                                    }
                                    if(ch==')'){
                                            aux.setToken(Tokens.PARENTESESCURVOD.toString()); 
                                    }
                                    if(ch=='&'){
                                            aux.setToken(Tokens.ECOMERCIAL.toString()); 
                                    }
                                    if(ch=='^'){
                                            aux.setToken(Tokens.ACENTOCIRCUNFLEXO.toString()); 
                                    }
                                    return aux;
                            }
                    if(estado==50){
                        ch = ler();
                        if((ch>='A'&& ch<='Z')||(ch>='a'&&ch<='z')){
                            palavraR = palavraR + ch;  
                            estado = 51;
                        }
                        else{
                            this.voltacaractere();
                            estado = 52;
                        }
                    }
                    if(estado==51){
                        ch = ler();
                    
                        while(ch!='\0'){
                            palavraR = palavraR + ch;  
                            ch = ler();
                        }
                        aux.setLexema(palavraR); 
                        aux.setToken(Tokens.PREPROCESSAMENTO.toString()); 
                        return aux;
                    }
                    if(estado==52)
                    {
                        aux.setLexema(palavraR); 
                        aux.setToken(Tokens.CARDINAL.toString()); 
                    }
             
    //aux.setToken(Tokens.FINALARQUIVO.toString());

        return aux;
    }
    
    //Função que dá início ao analex e retorna o vetor de Par
    public ArrayList<Par> inicializar(ArrayList<String> contFich){
        this.setContFich(contFich);
        linha = contFich.get(posP);
        while(posP<contFich.size()){
            t = this.analex();
            if(t.getLexema()!=null && t.getToken().compareTo("TOK_COMENTARIO")!=0){
                pares.add(t);
            }
            if(posP<contFich.size())
                linha = contFich.get(posP);
        }
        return pares;
    }

}
