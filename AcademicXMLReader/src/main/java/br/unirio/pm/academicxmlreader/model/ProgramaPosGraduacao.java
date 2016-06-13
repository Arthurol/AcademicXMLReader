package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe que representa um programa de pós-graduação sob avaliação do CAPES
 */
public @Getter @Setter class ProgramaPosGraduacao 
{
    private String nome;
    
    public ProgramaPosGraduacao()
    {
        nome = "";
    }
}


