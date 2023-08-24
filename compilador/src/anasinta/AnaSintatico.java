/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasinta;

import al.par.Par;
import java.util.ArrayList;
import tabelas.TabelaAtribuicao;
import tabelas.TabelaDecl;
import tabelas.TabelaId;
import tabelas.TabelaOperacoes;
import tabelas.TabelaRelop;
import tabelas.TabelaRetorno;

/**
 *
 * @author Madalena Makiesse
 */
public class AnaSintatico {
    /* 
    pares: guarda um conjunto de t.
    pos: permite guardar as posições do array.
    estado: permite saber qual posição deve ir.
    */
    private final ArrayList<Par> pares;
    private int pos;
    private Par aux;
    private int a;
    private ArrayList<TabelaRetorno> retornos;
    private ArrayList<TabelaDecl> tabDel;
    private ArrayList<TabelaId> tabId;
    private ArrayList<TabelaAtribuicao> tabAt;
    private ArrayList<TabelaOperacoes> tabOp;
    private ArrayList<TabelaRelop> tabRelop;
    private String nome;
    private String tipo;
    //0 significa que o escopo é global
    private String escopo="0";
    private ArrayList<String> pilhaEscopo;
    private int tam;
    private ArrayList<String> r;

    public AnaSintatico(ArrayList<Par> pares) {
        this.pares = pares;
    }
    /*
        Função que dá início ao analisador sintático e retorna um array de string
        de mensagens de erros!
    */
    public ArrayList<String> inicializar(){
        r = new ArrayList<String>();
        pilhaEscopo = new ArrayList<String>();
        pilhaEscopo.add(escopo);
        tabDel= new ArrayList<TabelaDecl>();
        tabId = new ArrayList<TabelaId>();
        tabAt = new ArrayList<TabelaAtribuicao>();
        retornos = new ArrayList<TabelaRetorno>();
        tabOp = new ArrayList<TabelaOperacoes> ();
        tabRelop = new ArrayList<TabelaRelop>();
        pos=0;
        aux=pares.get(pos);
        sinAnax();
        return r;
    }

    public ArrayList<TabelaDecl> getTabDel() {
        return tabDel;
    }

    public ArrayList<TabelaRelop> getTabRelop() {
        return tabRelop;
    }

    public ArrayList<TabelaId> getTabId() {
        for(int i =0; i<tabId.size();i++){
            if(tabId.get(i).getNome().compareTo("printf")==0 || tabId.get(i).getNome().compareTo("scanf")==0){
                tabId.remove(i);
            }
            /*for(int j =tabId.size()-1; j>i;j--)
                if((tabId.get(i).getNome().compareTo(tabId.get(j).getNome())==0 && tabId.get(i).getEscopo().compareTo(tabId.get(j).getEscopo())==0)){
                    //System.out.println(tabId.get(i).getNome()+", "+tabId.get(i).getEscopo()+"   "+tabId.get(j).getNome()+", "+tabId.get(j).getEscopo());
                    tabId.remove(j);
                }*/
        }
        return tabId;
    }

    public ArrayList<TabelaAtribuicao> getTabAt() {
        return tabAt;
    }

    public ArrayList<TabelaRetorno> getRetornos() {
        return retornos;
    }

    public ArrayList<TabelaOperacoes> getTabOp() {
        return tabOp;
    }
    
    
    
    //
    private void proxT(){
        if(pos<pares.size()-1){
            pos++;
            aux=pares.get(pos);
        }
    }
    //sinAnax(}: é uma função que verifica os tokens sintaticamente a gramática
    private void sinAnax(){
        while(aux.getToken().equalsIgnoreCase("TOK_PREPROCESSAMENTO") && aux!=null){
            proxT();
        }
        r.add("Início da análise sintática!");
        program();
        r.add("Fim da análise sintática!");
    }
    
    private void program(){
        declList();
    }
    
    private void declList(){
        decl();
        decList1();
    }
    
    private void decList1(){
        if(isTipo() || aux.getToken().equalsIgnoreCase("TOK_ID")){
            decl();  
            declList(); 
        }
    }
    
    private boolean isTipo(){
        if(aux.getToken().compareTo("TOK_CHAR")==0){
            this.tipo = "char";
            this.tam=1;
        }
        if(aux.getToken().compareTo("TOK_INT")==0){
            this.tipo = "int";
            this.tam=4;
        }
        if(aux.getToken().compareTo("TOK_FLOAT")==0){
            this.tipo = "float";
            this.tam=4;
        }
        if(aux.getToken().compareTo("TOK_DOUBLE")==0){
            this.tipo = "double";
            this.tam=8;
        }
        return aux.getToken().compareTo("TOK_INT")==0 || aux.getToken().compareTo("TOK_CHAR")==0 || aux.getToken().compareTo("TOK_DOUBLE")==0 || aux.getToken().compareTo("TOK_FLOAT")==0;
    }
    
    private void decl(){
        if(isTipo()){
            tipo();
            decl0();
            decl1();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_CHAVED")){
            
        }
        else{
            this.tipo="void";
            this.tam=0;
            id();
            if(this.nome.compareTo("")!=0 && this.tam>=0){
                tabDel.add(new TabelaDecl(this.nome, this.tipo, pilhaEscopo.get(pilhaEscopo.size()-1), this.tam, aux.getLinha()));
                this.escopo=this.nome;
                pilhaEscopo.add(escopo);
            }
            cumpre("(");
            parms();
            cumpre(")");
            if(aux.getToken().equalsIgnoreCase("TOK_PONTOVIRGULA")){
                cumpre(";");
            }
            else{
                block();
            }
            pilhaEscopo.remove(pilhaEscopo.size()-1);
        }
    }
    
    private void decl0(){
        if(aux.getToken().equalsIgnoreCase("TOK_ID")){
             id();
             if(this.nome.compareTo("")!=0 && this.tam>=0){
                tabDel.add(new TabelaDecl(this.nome, this.tipo, pilhaEscopo.get(pilhaEscopo.size()-1), this.tam, aux.getLinha()));;
            }
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_MULTIPLICACAO")){
            while(aux.getToken().equalsIgnoreCase("TOK_MULTIPLICACAO")){
                cumpre("*");
            }
            id();
            /*if(this.nome.compareTo("")!=0 && this.tam>=0){
//                tabDel.add(new TabelaDecl(this.nome, this.tipo, pilhaEscopo.get(pilhaEscopo.size()-1), this.tam, aux.getLinha()));
            }*/
        }
        else if(aux.getLexema().equalsIgnoreCase(")")){
            
        }
        else{
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se um * ou um identificador.");
        }
    }
    
    private void decl1(){
        if(aux.getLexema().equalsIgnoreCase("(")){      
            this.escopo=this.nome;
            pilhaEscopo.add(escopo);
            cumpre("(");
            parms();
            cumpre(")");
            stmt();
            pilhaEscopo.remove(pilhaEscopo.size()-1);
        }
        else{
            varDeclList();
            cumpre(";");
            decl();
        } 
        
    }
    
    private void varDeclList(){
        varDeclId1();
        varDeclList1();    
    } 
    
    private void varDeclList1(){
        if(aux.getLexema().compareTo(",")==0){
            cumpre(",");
            decl0();
            varDeclId1();
            varDeclList();
        }
        else{
            expr();
        }
    }
    
    private void varDeclId1(){
        if(aux.getToken().compareTo("TOK_PARENTESESRECTOE")==0){
            cumpre("[");
            numInt();
            cumpre("]");
        }
    }
     
    private void numInt(){
        int nu=Integer.parseInt(aux.getLexema());
        if(aux.getToken().compareTo("TOK_NUMINT")!=0 || aux.getToken().compareTo("TOK_ID")!=0){
            tabDel.get(tabDel.size()-1).setTam(0);
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se um número intero.");
        }
        else{
            if(aux.getToken().compareTo("TOK_NUMINT")==0){
                tabDel.get(tabDel.size()-1).setTam(tabDel.get(tabDel.size()-1).getTam()*nu);
            }
            else{
                tabDel.get(tabDel.size()-1).setTam(tam*(-1));
            }
            proxT();
        }
    }
    
    private void id(){
        if(aux.getToken().compareTo("TOK_ID")!=0){
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se um identificador.");
            this.nome="";
        }
        else{
            this.nome=aux.getLexema();
            tabId.add(new TabelaId(nome, pilhaEscopo.get(pilhaEscopo.size()-1), aux.getLinha()));
            proxT();
        }
    }
    
    private void cumpre(String t){
        if(t.compareTo(aux.getLexema())!=0){
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se "+t+".");
        }
        else{
           proxT(); 
        }   
    }
    
    private void tipo(){
        if(isTipo()!=true){
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se um tipo de dados.");
        }
        else{
            proxT();
        }
    }
    
    private void parms(){
        if(isTipo() || aux.getToken().equalsIgnoreCase("TOK_ID")){
            parmList();
        }
    }
    
    private void parmList(){
        if(isTipo()){
            parmTypeList();
            parmList1(); 
        }
        else{
            this.tipo="void";
            this.tam=0;
            parmIdList();
        } 
    }
    
    private void parmTypeList(){
        tipo();
        parmIdList();
    }
    
    private void parmList1(){
        if(aux.getLexema().equalsIgnoreCase(";")){
            cumpre(";");
            parmTypeList();
            parmList();
        }
        else if(isTipo()){
            parmTypeList();
            parmList();
        } 
        else if(aux.getLexema().equalsIgnoreCase(")")){
            
        }
        else{
            r.add("Erro de sintaxe na linha "+aux.getLinha()+", esperava-se um tipo de dados ou ;.");
        }
    }
    
    private void parmIdList(){
        parmId();
        parmIdList1();    
    }
    
    private void parmIdList1(){
        if(aux.getToken().equalsIgnoreCase("TOK_VIRGULA")){
            cumpre(",");
            parmIdList2();
        }
    }
    
    private void parmIdList2(){
        if(isTipo()){
            tipo();
        }
        parmId();
        parmIdList();
    }
    
    private void parmId(){
        decl0();
        parmId1();
    }
    
    private void parmId1(){
        if(aux.getToken().equalsIgnoreCase("TOK_PARENTESESRECTOE")){
            cumpre("[");
            cumpre("]");
        }
    }
    
    private void stmt(){
        stmt1();
    }
    
    private void stmt1(){
        if(isExpr()){
            expr();
            cumpre(";");
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_IF")){
            cumpre("if");
            cumpre("(");
            expr0();
            cumpre(")");
            stmt();
            select1();  
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_FOR")|| aux.getToken().equalsIgnoreCase("TOK_WHILE")){
            stmt0();
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_DO")){
            cumpre("do");
            block();
            cumpre("while");
            cumpre("(");
            expr0();
            cumpre(")");
            cumpre(";");
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_RETURN")){
            cumpre("return");
            return1();
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_BREAK")){
            cumpre("break");
            cumpre(";");
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_CONTINUE")){
            cumpre("continue");
            cumpre(";");
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_CHAVEE")){
            block();
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_PONTOVIRGULA")){
            cumpre(";");
            stmt();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_CHAVED")){
            
        }
        else if(isTipo()){
            tipo();
            tipo0();
            cumpre(";");
            stmt();
        }
        else{
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se uma expressão, if, do, while, for, return, break, continue, { ou ;.");
        }
    }
    
    private void tipo0(){
        expr();
        tipo1(); 
    }
    
    private void tipo1(){
        if(aux.getToken().equalsIgnoreCase("TOK_VIRGULA")){
            cumpre(",");
            tipo0();
        }
    }
    
    private void stmt0(){
        if(aux.getToken().equalsIgnoreCase("TOK_WHILE")){
            cumpre("while");
            cumpre("(");
            expr0();
            cumpre(")");
        }
        else{
            cumpre("for");
            cumpre("(");
            expr0();
            cumpre(";");
            expr0();
            cumpre(";");
            expr0();
            cumpre(")");
        }
    }
    
    private void expr0(){
        if(isExpr()){
            expr();
        }
    }
    
    private void select1(){
        if(aux.getToken().equalsIgnoreCase("TOK_ELSE")){
            cumpre("else");
            stmt();
        }
    }
    
    private void return1(){
        if(aux.getToken().equalsIgnoreCase("TOK_PONTOVIRGULA")){
            retornos.add(new TabelaRetorno(pilhaEscopo.get(pilhaEscopo.size()-1),"" ,"void" ));
        }
        else{
            retornos.add(new TabelaRetorno(pilhaEscopo.get(pilhaEscopo.size()-1),aux.getLexema() ,aux.getToken() ));
            expr();
        }
    }
    
    private void block(){
        if(aux.getToken().equalsIgnoreCase("TOK_CHAVEE")){
            cumpre("{");
            stmt();
            cumpre("}");
        }
    }  
    
    private void expr(){
        or();
        expr1();
    }
    
    private void expr1(){
        if(aux.getToken().equalsIgnoreCase("TOK_ATRIBUICAO")){
            if(pos+1<pares.size()-1 && pares.get(pos-1).getToken().equalsIgnoreCase("TOK_ID")){
                if(pares.get(pos+1).getToken().equalsIgnoreCase("TOK_ID") || pares.get(pos+1).getToken().equalsIgnoreCase("TOK_NUMINT") || pares.get(pos+1).getToken().equalsIgnoreCase("TOK_NUMFLOAT") || pares.get(pos+1).getToken().equalsIgnoreCase("TOK_STRING") || pares.get(pos+1).getToken().equalsIgnoreCase("TOK_ECOMERCIAL")){
                    if(pares.get(pos+1).getToken().equalsIgnoreCase("TOK_ECOMERCIAL") && pos+2<pares.size()-1){
                        tabAt.add(new TabelaAtribuicao(pares.get(pos-1).getLexema(),pares.get(pos-1).getToken(), aux.getLexema(), pares.get(pos+2).getLexema(),pares.get(pos+1).getToken(),pilhaEscopo.get(pilhaEscopo.size()-1) ,aux.getLinha()));
                    }
                    else{
                        tabAt.add(new TabelaAtribuicao(pares.get(pos-1).getLexema(), pares.get(pos-1).getToken(), aux.getLexema(), pares.get(pos+1).getLexema(),pares.get(pos+1).getToken(),pilhaEscopo.get(pilhaEscopo.size()-1),aux.getLinha()));
                    }
                }
            }
            proxT();
            expr();
        }
    }
    private void or(){
        and();
        or1();
    }
    
    private void or1(){
        if(aux.getToken().equalsIgnoreCase("TOK_SEPARADOR")){
            cumpre("|");
            cumpre("|");
            or();
        }
    }
    
    private void and(){
        not();
        and1();
    }
    
    private void and1(){
        if(aux.getToken().equalsIgnoreCase("TOK_ECOMERCIAL")){
            cumpre("&");
            cumpre("&");
            and();
        }
    }
    
    private void not(){
        if(aux.getToken().equalsIgnoreCase("TOK_PONTODEEXCLAMACAO")){
            cumpre("!");
            not();
        }
        else{
            relExp();
        }
    }
    
    private void relExp(){
        orbin();
        relExp1();
    }
    
    private void relExp1(){
        if(aux.getToken().equalsIgnoreCase("TOK_OPRELACIONAL")){
            tabRelop.add(new TabelaRelop(pares.get(pos-1).getLexema(),pares.get(pos-1).getToken(), aux.getLexema(), pares.get(pos+2).getLexema(),pares.get(pos+1).getToken(),pilhaEscopo.get(pilhaEscopo.size()-1) ,aux.getLinha()));
            proxT();
            orbin();
        }
    }
    
    private void orbin(){
        xorbin();
        orbin1();
    }
    
    private void orbin1(){
        if(aux.getToken().equalsIgnoreCase("TOK_SEPARADOR")){
            cumpre("|");
            if(!aux.getToken().equalsIgnoreCase("TOK_SEPARADOR")){
                xorbin();
                orbin1();
            }
            else{
                pos--;
            }
            
        }
    }
    
    private void xorbin(){
        andExp();
        xorbin1();
    }
    
    private void xorbin1(){
        if(aux.getToken().equalsIgnoreCase("TOK_ACENTOCIRCUNFLEXO")){
            cumpre("^");
            andExp();
            xorbin1();
        }
    }
    
    private void andExp(){
        relaExp();
        andExp1();
    }
    private void andExp1(){
        if(aux.getToken().equalsIgnoreCase("TOK_ECOMERCIAL")){
            cumpre("&");
            if(!aux.getToken().equalsIgnoreCase("TOK_ECOMERCIAL")){
                relaExp();
                andExp1();
            }
            else{
                pos--;
            }
        }
    }
    private void relaExp(){
        addExp();
        relaExp0();
    }
    private void relaExp0(){
        if(aux.getToken().equalsIgnoreCase("TOK_DESLOCAMENTOE") || aux.getToken().equalsIgnoreCase("TOK_DESLOCAMENTOD")){
            proxT();
            addExp();
            relaExp0();
        }
    }
    private void addExp(){
        mult();
        addExp0();
    }
    private void addExp0(){
        if(aux.getToken().equalsIgnoreCase("TOK_MAIS") || aux.getToken().equalsIgnoreCase("TOK_MENOS")){
            proxT();
            mult();
            addExp0();
        }
    }
    private void mult(){
        priExp();
        mult0();
    }
    private void mult0(){
        if(aux.getToken().equalsIgnoreCase("TOK_PERCENTUAL") || aux.getToken().equalsIgnoreCase("TOK_MULTIPLICACAO") || aux.getToken().equalsIgnoreCase("TOK_DIVISOR")){
            tabOp.add(new TabelaOperacoes(pares.get(pos-1).getLexema(), pares.get(pos-1).getToken(), aux.getLexema(), pares.get(pos+1).getLexema(),pares.get(pos+1).getToken(),pilhaEscopo.get(pilhaEscopo.size()-1),aux.getLinha()));
            priExp();
            mult0();
        }
    }
    private void priExp(){
        if(aux.getToken().equalsIgnoreCase("TOK_ECOMERCIAL")){
            cumpre("&");
            iD();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_MULTIPLICACAO")){
            cumpre("*");
            priExp();
        }
        else{
            uno();
        }
    }
    private void uno(){
        if(aux.getToken().equalsIgnoreCase("TOK_MAIS") || aux.getToken().equalsIgnoreCase("TOK_MENOS")){
            tabOp.add(new TabelaOperacoes(pares.get(pos-1).getLexema(), pares.get(pos-1).getToken(), aux.getLexema(), pares.get(pos+1).getLexema(),pares.get(pos+1).getToken(),pilhaEscopo.get(pilhaEscopo.size()-1),aux.getLinha()));
            proxT();
            uno();
        }
        else{
            notbin();
        }
    }
    private void notbin(){
        if(aux.getToken().equalsIgnoreCase("TOK_TIL")){
            proxT();
            notbin();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_INCREMENTO") || aux.getToken().equalsIgnoreCase("TOK_DECREMENTO")){
            incpre();
        }
        else{
            incpos();
        }
    }
    private void incpre(){
        if(aux.getToken().equalsIgnoreCase("TOK_INCREMENTO") || aux.getToken().equalsIgnoreCase("TOK_DECREMENTO")){
            proxT();
            if(aux.getToken().equalsIgnoreCase("TOK_ID")){
                tabOp.add(new TabelaOperacoes(aux.getLexema(), aux.getToken(), pares.get(pos-1).getLexema(),pilhaEscopo.get(pilhaEscopo.size()-1),aux.getLinha()));
            }
            iD();
        }
    }
    private void iD(){
        if(aux.getToken().equalsIgnoreCase("TOK_MULTIPLICACAO")){
            cumpre("*");
            id();
            
        }
        else{
           id();
           iD1();
        }
    }
    private void iD1(){
        if(aux.getToken().compareTo("TOK_PARENTESESRECTOE")==0){
            cumpre("[");
            expr();
            cumpre("]");
        }
    }
    private void incpos(){
        fator();
        incpos1();
    }
    private void incpos1(){
        if(aux.getToken().equalsIgnoreCase("TOK_INCREMENTO") || aux.getToken().equalsIgnoreCase("TOK_DECREMENTO")){
            if(pares.get(pos-1).getToken().equalsIgnoreCase("TOK_ID")){
                tabOp.add(new TabelaOperacoes(pares.get(pos-1).getLexema(), pares.get(pos-1).getToken(), aux.getLexema(),pilhaEscopo.get(pilhaEscopo.size()-1),aux.getLinha()));
            }
            proxT();
        }
    }
    private void fator(){
        if(aux.getToken().equalsIgnoreCase("TOK_ID")){
            id(); 
            if(this.nome.compareTo("")!=0 && this.tam>=0){
                if((pares.get(pos-2).getToken().compareTo("TOK_CHAR")==0 || pares.get(pos-2).getToken().compareTo("TOK_INT")==0 || pares.get(pos-2).getToken().compareTo("TOK_FLOAT")==0 || pares.get(pos-2).getToken().compareTo("TOK_DOUBLE")==0) || (pares.get(pos-2).getToken().compareTo("TOK_VIRGULA")==0 && (pares.get(pos-2).getToken().compareTo("TOK_VIRGULA")==0 && (pares.get(pos-3).getToken().compareTo("TOK_ID")==0) || pares.get(pos-3).getToken().equalsIgnoreCase("TOK_NUMINT") || pares.get(pos-3).getToken().equalsIgnoreCase("TOK_NUMFLOAT")))){
                    tabDel.add(new TabelaDecl(this.nome, this.tipo, pilhaEscopo.get(pilhaEscopo.size()-1), tam, aux.getLinha()));
                    if(aux.getToken().compareTo("TOK_PARENTESESRECTOE")==0)
                        tabDel.get(tabDel.size()-1).setTam(tam*(-1));
                }        
            }
            fator1();
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_PARENTESESCURVOE")){
            cumpre("(");
            expr();
            cumpre(")");
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_NUMINT") || aux.getToken().equalsIgnoreCase("TOK_NUMDOUBLE") ||  aux.getToken().equalsIgnoreCase("TOK_NUMFLOAT") || aux.getToken().equalsIgnoreCase("TOK_STRING")){
           if(aux.getToken().equalsIgnoreCase("TOK_NUMINT") && pares.get(pos-1).getLexema().equalsIgnoreCase("[")){
               tabDel.get(tabDel.size()-1).setTam(tam*(Integer.parseInt(aux.getLexema())));
            }
            proxT();
        }
        else if(aux.getLexema().equalsIgnoreCase(";")){
            
        }
        else{
            r.add("Erro de sintaxe: na linha "+aux.getLinha()+" esperava-se um identificador, '(' ou constante.");
        }
    }
    private void fator1(){
        if(aux.getToken().compareTo("TOK_PARENTESESRECTOE")==0){
            cumpre("[");
            expr();
            cumpre("]");
        }
        else if(aux.getToken().equalsIgnoreCase("TOK_PARENTESESCURVOE")){
            cumpre("(");
            fator2();
        }
        else{
            
        }
    }
    private void fator2(){
        if(aux.getToken().equalsIgnoreCase("TOK_PARENTESESCURVOD")){
            cumpre(")");
        }
        else{
            exprs();
            cumpre(")");
        }
    }
    private void exprs(){
        expr();
        exprs1();
    }
    private void exprs1(){
         if(aux.getToken().equalsIgnoreCase("TOK_VIRGULA")){
            cumpre(",");
            exprs();
        }
    }
    private boolean isExpr(){
        return aux.getToken().equalsIgnoreCase("TOK_PONTODEEXCLAMACAO")|| aux.getToken().equalsIgnoreCase("TOK_ECOMERCIAL") || aux.getToken().equalsIgnoreCase("TOK_MULTIPLICACAO") || aux.getToken().equalsIgnoreCase("TOK_TIL") || aux.getToken().equalsIgnoreCase("TOK_MAIS") || aux.getToken().equalsIgnoreCase("TOK_MENOS") || aux.getToken().equalsIgnoreCase("TOK_INCREMENTO") || aux.getToken().equalsIgnoreCase("TOK_DECREMENTO") || aux.getToken().equalsIgnoreCase("TOK_ID") || aux.getToken().equalsIgnoreCase("TOK_PARENTESESCURVOE") || aux.getToken().equalsIgnoreCase("TOK_STRING") || aux.getToken().equalsIgnoreCase("TOK_NUMINT") || aux.getToken().equalsIgnoreCase("TOK_NUMFLOAT");
    } 
}