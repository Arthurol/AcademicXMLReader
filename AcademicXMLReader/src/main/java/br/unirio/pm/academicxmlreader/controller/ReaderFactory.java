package br.unirio.pm.academicxmlreader.controller;

/**
 *
 * Concentracao do Acesso as classes responsaveis pela leitura dos arquivos xml
 */
public class ReaderFactory 
{
    private static LeitorProgramaPosGraduacao leitorProgramaPosGraduacao;
    private static LeitorProfessoresPrograma leitorProfessoresPrograma;
    private static LeitorCurriculosProfessor leitorCurriculosProfessor;
    
    /**
    * Retorna uma instância de LeitorProgramaPosGraduacao
    */
    public static LeitorProgramaPosGraduacao getLeitorProgramaPosGraduacao()
    {
        if (leitorProgramaPosGraduacao == null)
            leitorProgramaPosGraduacao = new LeitorProgramaPosGraduacao();

        return leitorProgramaPosGraduacao;
    }
    
    /**
    * Retorna uma instância de LeitorProfessoresPrograma
    */
    public static LeitorProfessoresPrograma getLeitorProfessoresPrograma()
    {
        if (leitorProfessoresPrograma == null)
            leitorProfessoresPrograma = new LeitorProfessoresPrograma();

        return leitorProfessoresPrograma;
    }
    
    /**
    * Retorna uma instância de LeitorCurriculosProfessor
    */
    public static LeitorCurriculosProfessor getLeitorCurriculosProfessor()
    {
        if (leitorProfessoresPrograma == null)
            leitorCurriculosProfessor = new LeitorCurriculosProfessor();

        return leitorCurriculosProfessor;
    }
}
