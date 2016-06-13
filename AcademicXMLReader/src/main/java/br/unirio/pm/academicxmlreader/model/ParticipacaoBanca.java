package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Classe que representa uma participação em banca julgadora do professor
 */
public @Getter @Setter class ParticipacaoBanca 
{
    private String ano;
    private String tituloTrabalho;
    private String nomeCandidato;
    
    public ParticipacaoBanca()
    {
        ano = "";
        tituloTrabalho = "";
        nomeCandidato = "";
    }
}



