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
public class TabelaDecl {
    private String nome;
    private String tipo;
    private String escopo;
    private int tam;
    private int linha;

public TabelaDecl(String nome, String tipo, String escopo, int tam, int linha) {
        this.nome = nome;
        this.tipo = tipo;
        this.escopo = escopo;
        this.tam = tam;
        this.linha = linha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    @Override
    public String toString() {
        return "TabelaDecl{" + "nome=" + nome + ", tipo=" + tipo + ", escopo=" + escopo + ", tam=" + tam + ", linha=" + linha + '}';
    }
    
    public boolean equals(TabelaDecl obj) {
        return obj.escopo.compareTo(this.escopo)==0 && obj.nome.compareTo(this.nome)==0 ; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
