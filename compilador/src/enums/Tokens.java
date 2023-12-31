/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author Madalena Makiesse
 */
public enum Tokens {
    /*
        Usei a classe enum pois os tokens definidos são constantes
        E também criou-se uma constante token para que retorne uma string do valor desejado
    */
    AUTO("TOK_AUTO"),
    BREAK("TOK_BREAK"),
    CASE("TOK_CASE"),
    CLASS("TOK_CLASS"),
    CHAR("TOK_CHAR"),
    UNSIGNED("TOK_UNSIGNED"),
    CONST("TOK_CONST"),
    CONTINUE("TOK_CONTINUE"),
    DEFAULT("TOK_DEFAULT"),
    DO("TOK_DO"),
    DOUBLE("TOK_DOUBLE"),
    ELSE("TOK_ELSE"),
    ENUM("TOK_ENUM"),
    EXTERN("TOK_EXTERN"),
    SHORT("TOK_SHORT"),
    FOR("TOK_FOR"),
    GOTO("TOK_GOTO"),
    IF("TOK_IF"),
    INT("TOK_INT"),
    LONG("TOK_LONG"),
    REGISTER("TOK_REGISTER"),
    RETURN("TOK_RETURN"),
    FLOAT("TOK_FLOAT"),
    SIGNED("TOK_SIGNED"),
    SIZEOF("TOK_SIZEOF"),
    STATIC("TOK_STATIC"),
    STRUCT("TOK_STRUCT"),
    SWITCH("TOK_SWITCH"),
    TYPEDEF("TOK_TYPEDEF"),
    UNION("TOK_UNION"),
    VOID("TOK_VOID"),
    VOTATILE("TOK_VOTATILE"),
    WHILE("TOK_WHILE"),
    ID("TOK_ID"),
    NUMINT("TOK_NUMINT"),
    NUMFLOAT("TOK_NUMFLOAT"),
    OPRELACIONAL("TOK_OPRELACIONAL"),
    VIRGULA("TOK_VIRGULA"),
    PONTO("TOK_PONTO"),
    PONTOVIRGULA("TOK_PONTOVIRGULA"),
    PONTODEINTERROGACAO("TOK_PONTODEINTERROGACAO"),
    DOISPONTOS("TOK_DOISPONTOS"),
    PICLAS("TOK_PICLAS"),
    PONTODEEXCLAMACAO("TOK_PONTODEEXCLAMACAO"),
    SEPARADOR("TOK_SEPARADOR"),
    DIVISOR("TOK_DIVISOR"),
    BARRA("TOK_BARRA"),
    TIL("TOK_TIL"),
    UNDERSCORE("TOK_UNDERSCORE"),
    SINALDEDOLAR("TOK_SINALDEDOLAR"),
    PERCENTUAL("TOK_PERCENTUAL"),
    CHAVEE("TOK_CHAVEE"),
    CHAVED("TOK_CHAVED"),
    PARENTESESRECTOE("TOK_PARENTESESRECTOE"),
    PARENTESESRECTOD("TOK_PARENTESESRECTOD"),
    PARENTESESCURVOE("TOK_PARENTESESCURVOE"),
    PARENTESESCURVOD("TOK_PARENTESESCURVOD"),
    ECOMERCIAL("TOK_ECOMERCIAL"),
    ACENTOCIRCUNFLEXO("TOK_ACENTOCIRCUNFLEXO"),
    MAIS("TOK_MAIS"),
    MENOS("TOK_MENOS"),
    INCREMENTO("TOK_INCREMENTO"),
    DECREMENTO("TOK_DECREMENTO"),
    MULTIPLICACAO("TOK_MULTIPLICACAO"),
    EXPOENCIACAO("TOK_EXPOENCIACAO"),
    CARDINAL("TOK_CARDINAL"),
    DESLOCAMENTOE("TOK_DESLOCAMENTOE"),
    DESLOCAMENTOD("TOK_DESLOCAMENTOD"),
    ATRIBUICAO("TOK_ATRIBUICAO"),
    ATRIBUICAOSOMA("TOK_ATRIBUICAO"),
    ATRIBUICAOSUB("TOK_ATRIBUICAO"),
    ATRIBUICAODIV("TOK_ATRIBUICAO"),
    ATRIBUICAOMULT("TOK_ATRIBUICAO"),
    ATRIBUICAOEXP("TOK_ATRIBUICAO"),
    ATRIBUICAODD("TOK_ATRIBUICAO"),
    ATRIBUICAODE("TOK_ATRIBUICAO"),
    ATRIBUICAOPERCENTUAL("TOK_ATRIBUICAO"),
    STRING("TOK_STRING"),
    PREPROCESSAMENTO("TOK_PREPROCESSAMENTO"),
    DESCONHECIDO("TOK_DESCONHECIDO"),
    ELLIPSIS("TOK_ELLIPSIS"),
    COMENTARIO("TOK_COMENTARIO"),
    FINALARQUIVO("TOK_END");
    
    private final String token;

    private Tokens(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
      
}
