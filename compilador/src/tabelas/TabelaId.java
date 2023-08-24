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
public class TabelaId {
    private String nome;
    private String escopo;
    private int linha;

    public TabelaId(String nome, String escopo, int linha) {
        this.nome = nome;
        this.escopo = escopo;
        this.linha = linha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    @Override
    public String toString() {
        return "TabelaId{" + "nome=" + nome + ", escopo=" + escopo + ", linha=" + linha + '}';
    }

    
    
}
