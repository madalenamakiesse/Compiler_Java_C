/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas;

/**
 *
 * @author Madalena Makiesse
 */
public class TabelaOperacoes {
    private String t1;
    private String tT1;
    private String op;
    private String t2;
    private String tT2;
    private String escopo;
    private int linha;

    public TabelaOperacoes(String t1, String tT1, String op, String t2, String tT2, String escopo, int linha) {
        this.t1 = t1;
        this.tT1=tT1;
        this.op = op;
        this.t2 = t2;
        this.tT2=tT2;
        this.escopo = escopo;
        this.linha = linha;
    }

    public TabelaOperacoes(String t1, String tT1, String op, String escopo, int linha) {
        this.t1 = t1;
        this.tT1=tT1;
        this.op = op;
        this.t2 = null;
        this.tT2=null;
        this.escopo = escopo;
        this.linha = linha;
    }
    
    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String gettT2() {
        return tT2;
    }

    public void settT2(String tT2) {
        this.tT2 = tT2;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public String gettT1() {
        return tT1;
    }

    public void settT1(String tT1) {
        this.tT1 = tT1;
    }

    @Override
    public String toString() {
        return "TabelaAtribuicao{" + "t1=" + t1 + ", tT1=" + tT1 + ", op=" + op + ", t2=" + t2 + ", tT2=" + tT2 + ", escopo=" + escopo + ", linha=" + linha + '}';
    }
}
