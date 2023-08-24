/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anaSem;

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
public class AnaSemantico {
    /* ids das tabelas*/
    private ArrayList<String> r;
    private ArrayList<TabelaDecl> decls;
    private ArrayList<TabelaId> ids;
    private ArrayList<TabelaAtribuicao> atrb;
    private ArrayList<TabelaOperacoes> ops;
    private ArrayList<TabelaRetorno> rtns;
    private ArrayList<TabelaRelop> rels;
    //Recebe as tabelas
    public AnaSemantico(ArrayList<TabelaDecl> decls, ArrayList<TabelaId> ids, ArrayList<TabelaAtribuicao> atrb, ArrayList<TabelaOperacoes> ops, ArrayList<TabelaRetorno> rtns,ArrayList<TabelaRelop> rels) {
        this.decls = decls;
        this.ids = ids;
        this.atrb = atrb;
        this.ops = ops;
        this.rtns=rtns;
        this.rels=rels;
    }
    
    public ArrayList<String> Inicio(){
        r = new ArrayList<String>();
        r.add("Início da análise semântica!");
        veriMultDecl();
        veriIds();
        veriAtrb();
        veriOp();
        veriRt();
        veriRelop();
        r.add("Fim da análise semântica!");
        return r;
    }
    
    //Avaliar múltiplas declarações
    private void veriMultDecl(){
        for(int i =0; i<decls.size();i++){
            for(int j =decls.size()-1; j>i;j--)
                if((decls.get(i).getNome().compareTo(decls.get(j).getNome())==0 && decls.get(i).getEscopo().compareTo(decls.get(j).getEscopo())==0)){
                    //System.out.println(decls.get(i).getNome()+", "+decls.get(i).getEscopo()+"   "+decls.get(j).getNome()+", "+decls.get(j).getEscopo());
                    r.add("Erro semântico: na linha "+decls.get(i).getLinha()+", "+decls.get(i).getNome()+" já foi declarada.");
                }
        }
    }
    
    //Avaliar variáveis não declaradas
    private void veriIds(){
        for(int i=0; i<ids.size(); i++){
            if(!isOnDecls(ids.get(i).getNome(), ids.get(i).getEscopo())){
                r.add("Erro semântico: na linha "+ids.get(i).getLinha()+", "+ids.get(i).getNome()+" não foi declarada.");
            }
        }
    }
    
    //Verifica se o identificador está na tabela de declarações
    private boolean isOnDecls(String nome, String escopo){
        if(nome!=null && escopo!=null){
            for(TabelaDecl d : decls){
                if(d.getNome().compareTo(nome)==0 && (d.getEscopo().compareTo(escopo)==0 || d.getEscopo().compareTo("0")==0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    //Verifica se o identificador está na tabela de declarações e retorna o tipo
    private String isOnDecls1(String nome, String escopo){
        if(nome!=null && escopo!=null){
            for(TabelaDecl d : decls){
                if(d.getNome().compareTo(nome)==0 && (d.getEscopo().compareTo(escopo)==0 || d.getEscopo().compareTo("0")==0)){
                    return d.getTipo();
                }
            }
        }
        return null;
    }
    //Verifica se o identificador está na tabela de declarações e retorna o tamanho
    private int isOnDecls2(String nome, String escopo){
        if(nome!=null && escopo!=null){
            for(TabelaDecl d : decls){
                if(d.getNome().compareTo(nome)==0 && (d.getEscopo().compareTo(escopo)==0 || d.getEscopo().compareTo("0")==0)){
                    return d.getTam();
                }
            }
        }
        return 0;
    }
    
    //Verificar as atribuicoes
    private void veriAtrb(){
        for(int i=0; i<atrb.size(); i++){
            int tam = isOnDecls2(atrb.get(i).getT2(), atrb.get(i).getEscopo());
            String tipo1 = isOnDecls1(atrb.get(i).getT2(), atrb.get(i).getEscopo());
            String tipo = isOnDecls1(atrb.get(i).getT1(), atrb.get(i).getEscopo());
            if(tipo!=null){
                if(tipo.equalsIgnoreCase("float") || (tipo.equalsIgnoreCase("double"))){
                    if(atrb.get(i).gettT2().equalsIgnoreCase("TOK_STRING")){
                        r.add("Erro semântico: na linha "+atrb.get(i).getLinha()+", quando é iniclializado um "+tipo+" passando um tipo char*");
                    }
                    if(tipo1!=null){
                        if(tipo1.compareTo(tipo)!=0){
                            if(tipo1.compareTo("char")==0 && tam!=1){
                                r.add("Erro semântico: na linha "+atrb.get(i).getLinha()+", quando é iniclializado um "+tipo+" passando um tipo char*");
                            }
                            else if(tipo1.compareTo("int")==0 && tam!=4){
                                r.add("Erro semântico: na linha "+atrb.get(i).getLinha()+", quando é iniclializado um "+tipo+" passando um tipo int*");
                            }
                            else if(tipo1.compareTo("float")==0 && tam!=4){
                                r.add("Erro semântico: na linha "+atrb.get(i).getLinha()+", quando é iniclializado um "+tipo+" passando um tipo float*");
                            }
                            else if(tipo1.compareTo("double")==0 && tam!=8){
                                r.add("Erro semântico: na linha "+atrb.get(i).getLinha()+", quando é iniclializado um "+tipo+" passando um tipo double*");
                            }
                            else{

                            }
                        } } 
                }
                if(atrb.get(i).gettT2().compareTo("TOK_ID")==0){
                    if(!vAtr(atrb.get(i).getT1(), atrb.get(i).getT2())){
                        r.add("Erro semântico: na linha "+atrb.get(i).getLinha()+", "+atrb.get(i).getT2()+" não foi inicializado.");
                    }
                }
                
            }
            else{
               
            }
        }
    }
    
    //Verificar se a variável foi inicializada
    private boolean vAtr(String nome1, String nome2){
        if(nome1!=null && nome2!=null){
            for(TabelaAtribuicao d : atrb){
                if(d.getT1().compareTo(nome2)==0){
                    return true;
                }
                if(d.getT1().compareTo(nome1)==0){
                    return false;
                }
            }
        }
        return false;
    }
    
    //Verificar as operaçoes binárias
    private void veriOp(){
        for(int i=0; i<ops.size(); i++){
            int tam = isOnDecls2(ops.get(i).getT2(), ops.get(i).getEscopo());
            String tipo1 = isOnDecls1(ops.get(i).getT2(), ops.get(i).getEscopo());
            String tipo = isOnDecls1(ops.get(i).getT1(), ops.get(i).getEscopo());
            if(tipo!=null && ops.get(i).gettT2()!=null){
                if((tipo.equalsIgnoreCase("int") || (tipo.equalsIgnoreCase("char"))) && ops.get(i).getOp().compareTo("+")!=0  && ops.get(i).gettT2().compareTo("TOK_STRING")==0){
                    r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com char*).");
                }
                if((tipo.equalsIgnoreCase("float") || (tipo.equalsIgnoreCase("double"))) && ops.get(i).gettT2().compareTo("TOK_STRING")==0){
                   r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com char*).");
                }
                else{
                    if(tipo1!=null){
                    if(tipo.equalsIgnoreCase("char")){
                        if(tipo1.compareTo("char")==0 && tam!=1){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com char*).");
                        }
                        else if(tipo1.compareTo("int")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com int*).");
                        }
                        else if(tipo1.compareTo("float")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com float*).");
                        }
                        else if(tipo1.compareTo("double")==0 && tam!=8){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com double*).");
                        }
                        else{
                            
                        }
                    }
                    else if(tipo.equalsIgnoreCase("int")){
                        if(tipo1.compareTo("char")==0 && tam!=1){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com char*).");
                        }
                        else if(tipo1.compareTo("int")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com int*).");
                        }
                        else if(tipo1.compareTo("float")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com float*).");
                        }
                        else if(tipo1.compareTo("double")==0 && tam!=8){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com double*).");
                        }
                        else{
                            
                        }
                    }
                    else if(tipo.equalsIgnoreCase("float")){
                        if(tipo1.compareTo("char")==0 && tam!=1){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com char*).");
                        }
                        else if(tipo1.compareTo("int")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com int*).");
                        }
                        else if(tipo1.compareTo("float")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com float*).");
                        }
                        else if(tipo1.compareTo("double")==0 && tam!=8){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com double*).");
                        }
                        else{
                            
                        }
                    }
                    else if(tipo.equalsIgnoreCase("double")){
                        if(tipo1.compareTo("char")==0 && tam!=1){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com char*).");
                        }
                        else if(tipo1.compareTo("int")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com int*).");
                        }
                        else if(tipo1.compareTo("float")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com float*).");
                        }
                        else if(tipo1.compareTo("double")==0 && tam!=8){
                            r.add("Erro semântico: na linha "+ops.get(i).getLinha()+", operadores binários inválidos ("+tipo+" com double*).");
                        }
                        else{
                            
                        }
                    }
                    else{
                        
                    }
                }}
            }
        }
    }
    
    //Verificar os retornos
    private void veriRt(){
        for(int i=0; i<rtns.size(); i++){
            String tipo = isOnDecls1(rtns.get(i).getEscopo(), "0");
            String tipo2;
            if(tipo!=null){
                if(rtns.get(i).getTipo().compareTo("void")==0 && !tipo.equalsIgnoreCase("void")){
                    r.add("Erro semântico: "+rtns.get(i).getEscopo()+" é do tipo "+tipo+" mas retorna void.");
                }
                else if(rtns.get(i).getTipo().compareTo("TOK_ID")!=0 && !tipo.equalsIgnoreCase(convertTipo(rtns.get(i).getTipo()))){
                    r.add("Erro semântico: "+rtns.get(i).getEscopo()+" é do tipo "+tipo+" mas retorna "+convertTipo(rtns.get(i).getTipo())+".");
                }
                else{
                    if(rtns.get(i).getTipo().compareTo("TOK_ID")==0){
                        tipo2 = isOnDecls1(rtns.get(i).getValor(), rtns.get(i).getEscopo());
                        System.out.println(rtns.get(i).getValor());
                        if(!tipo.equalsIgnoreCase(tipo2)){
                            r.add("Erro semântico: "+rtns.get(i).getEscopo()+" é do tipo "+tipo+" mas retorna "+tipo2+".");
                        }  
                    }
                    
                }
            }
        }
    }
    
    private void veriRelop(){
        for(int i=0; i<rels.size(); i++){
            int tam = isOnDecls2(rels.get(i).getT2(), rels.get(i).getEscopo());
            String tipo1 = isOnDecls1(rels.get(i).getT2(), rels.get(i).getEscopo());
            String tipo = isOnDecls1(rels.get(i).getT1(), rels.get(i).getEscopo());
            if(tipo!=null){
                if(tipo.equalsIgnoreCase("float") || (tipo.equalsIgnoreCase("double"))){
                    if(rels.get(i).gettT2().equalsIgnoreCase("TOK_STRING")){
                        r.add("Erro semântico: na linha "+rels.get(i).getLinha()+", quando é comparado um "+tipo+" com char*");
                    }
                    else if(tipo1.compareTo(tipo)!=0){
                        if(tipo1.compareTo("char")==0 && tam!=1){
                            r.add("Erro semântico: na linha "+rels.get(i).getLinha()+", quando é comparado um "+tipo+" com char*");
                        }
                        else if(tipo1.compareTo("int")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+rels.get(i).getLinha()+", quando é comparado um "+tipo+" com int*");
                        }
                        else if(tipo1.compareTo("float")==0 && tam!=4){
                            r.add("Erro semântico: na linha "+rels.get(i).getLinha()+", quando é comparado um "+tipo+" com float*");
                        }
                        else if(tipo1.compareTo("double")==0 && tam!=8){
                            r.add("Erro semântico: na linha "+rels.get(i).getLinha()+", quando é comparado um "+tipo+" com double*");
                        }
                        else{
                            
                        }
                    }
                        
                }
            }
        }
    }
    
    private String convertTipo(String t){
        if(t.equalsIgnoreCase("TOK_NUMINT")){
            return "int";
        }
        else if(t.equalsIgnoreCase("TOK_FLOAT")){
            return "float";
        }
        else{
            return "char*";
        }
    }
}
