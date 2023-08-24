/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import al.par.Par;
import anaSem.AnaSemantico;
import analex.AnaLex;
import anasinta.AnaSintatico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class CompiladorMain {

    /**
     * @param args the command line arguments
     */
    /*
            Declaração das variáveis globais
        linha: guarda os dados de uma linha de um ficheiro.
        pares: guarda uma lista de Par do ficheiro em análise.
        f: guarda o caminho do ficheiro.
        leitura: permite a manipulação do ficheiro.
        contFich: guarda o conteúdo do ficheiro.
    */
    
    //Atributos
    public static String linha;
    public static File f;
    public static BufferedReader leitura;
    public static ArrayList<String> contFich = new ArrayList<String>();
    
    //aberturaFicheiro(): função que trada da abertura e inicialização do objecto para a manipulação do ficheiro requerido
    public static void aberturaFicheiro() throws IOException{
        String nomeFich = "teste.txt";
        f = new File (nomeFich);
        try{
            leitura = new BufferedReader (new FileReader(nomeFich));
        }catch(IOException e){
            System.out.println(e);
            return;
        }
        //Guarda-se todos os dados do ficheiro num array de string para facilitar a manipulação
        linha = leitura.readLine();
        while(linha!=null){
            contFich.add(linha);
            linha = leitura.readLine();
        }
        nComent();
    }
    //Resolver Problemas de comentários com n linhas
    public static void nComent(){
        int k=0,i=0;
        ArrayList<String> aux = new ArrayList<String>();
        
        while(i<contFich.size()){
            if(contFich.get(i).startsWith("/*")){
                k=i;
                linha=contFich.get(i);
                while((k+1)<contFich.size()){
                    linha=linha.concat(contFich.get(k+1));
                    k++;
                }
                aux.add(linha);
                i=k;
            }
            else{
                aux.add(contFich.get(i));
            } 
            i++;
        }
        contFich=aux;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        ArrayList<Par> pares = new ArrayList<Par>();
        aberturaFicheiro(); 
        //Análise Léxica
        System.out.println("******Análise Léxica****");
        AnaLex analex = new AnaLex();
        pares = analex.inicializar(contFich);
        for(Par p : pares){
            System.out.println(p.getLinha()+" "+p.getToken()+" "+p.getLexema());
        }
        
        //Análise Sintática
        System.out.println("\n******Análise Sintática****");
        AnaSintatico anasintx = new AnaSintatico(pares);
        for(String p : anasintx.inicializar()){
            System.out.println(p);
        }
        
        //Análise Semântica
        System.out.println("\n******Análise Semântica****");
        AnaSemantico anasem = new AnaSemantico(anasintx.getTabDel(), anasintx.getTabId(), anasintx.getTabAt(), anasintx.getTabOp(),anasintx.getRetornos(), anasintx.getTabRelop());
        for(String p : anasem.Inicio()){
            System.out.println(p);
        }
        }
}
