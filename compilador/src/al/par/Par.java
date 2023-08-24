/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.par;

/**
 *
 * @author Madalena Makiesse
 */
public class Par {
    private String token;
    private String lexema;
    private int linha;

    public Par() {
    }
    
    public Par(String token, String Lexema, int linha) {
        this.token = token;
        this.lexema = Lexema;
        this.linha = linha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String Lexema) {
        this.lexema = Lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    @Override
    public String toString() {
        return linha+" "+token+" "+lexema;
    }
    
    
}
