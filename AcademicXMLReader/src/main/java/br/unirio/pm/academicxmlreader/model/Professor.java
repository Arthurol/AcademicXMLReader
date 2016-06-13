package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe responsavel por instanciar um objeto do tipo Professor, este que tem atributos baseados nas caracteristicas obtidas dos curriculos da plataforma Lattes
 */
public @Getter @Setter class  Professor 
{
    private String codigoCurriculo;
    private String nome;
    private String linhaDePesquisa;
    private CurriculoProfessor curriculo;

    Professor(String nome, String codigoCurriculo, String linhaDePesquisa)
    {
        this.nome = nome;
        this.codigoCurriculo = codigoCurriculo;
        this.linhaDePesquisa = linhaDePesquisa;
    }
    
    public Professor()
    {
        nome = "";
        codigoCurriculo = "";
        linhaDePesquisa = "";
        curriculo = new CurriculoProfessor();
    }
    
    
// Atributos vão ser usados depois ou passados para o curriculo do professor    
/*    private int artigosRevistaA1, artigosRevistaA2, artigosRevistaB1, artigosRevistaB2, artigosRevistaB3, artigosRevistaB4, artigosRevistaC, artigosRevistaNC;
    private int artigosEventoA1, artigosEventoA2, artigosEventoB1, artigosEventoB2, artigosEventoB3, artigosEventoB4, artigosEventoC, artigosEventoNC;
    
    private int participacoesBancaDoutorado;
    private int participacoesBancaMestrado;
    private int participacoesBancaGraduacao;
    
    private int orientacoesDoutorado;
    private int orientacoesMestrado;
    private int orientacoesGraduacao;
    
    private int orientacoesDoutoradoEmAndamento;
    private int orientacoesMestradoEmAndamento;
    private int orientacoesGraduacaoEmAndamento;
*/
    
}
