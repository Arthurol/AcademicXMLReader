package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe que representa um professor de um programa de pós-graduação
 */
public @Getter @Setter class  Professor 
{
    private String codigoCurriculo;
    private String nome;
    private CurriculoProfessor curriculo;

    Professor(String nome, String codigoCurriculo)
    {
        this.nome = nome;
        this.codigoCurriculo = codigoCurriculo;
    }
    
    public Professor()
    {
        nome = "";
        codigoCurriculo = "";
        curriculo = new CurriculoProfessor();
    }  
}
