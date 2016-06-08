package br.unirio.pm.academicxmlreader.model;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * FALTA COMENTAR
 */
public @Getter @Setter class LinhaDePesquisa 
{
    private String programaPosGraduacao;
    private int numeroProfessores;
    
    private int artigosRevistaA1, artigosRevistaA2, artigosRevistaB1, artigosRevistaB2, artigosRevistaB3, artigosRevistaB4, artigosRevistaC, artigosRevistaNC;
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
    
}
