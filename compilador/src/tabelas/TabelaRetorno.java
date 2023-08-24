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
public class TabelaRetorno {
    private String escopo;
    private String valor;
    private String tipo;

    public TabelaRetorno(String escopo, String valor, String tipo) {
        this.escopo = escopo;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TabelaRetorno{" + "escopo=" + escopo + ", valor=" + valor + ", tipo=" + tipo + '}';
    }
    
}
