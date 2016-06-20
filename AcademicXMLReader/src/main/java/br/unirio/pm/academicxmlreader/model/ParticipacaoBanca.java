package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Classe que representa uma participação em banca julgadora do professor
 */
public @Getter @Setter class ParticipacaoBanca 
{
    private int ano;
    private String tituloTrabalho;
    private String nomeCandidato;
    
    public ParticipacaoBanca()
    {
        ano = -1;
        tituloTrabalho = "";
        nomeCandidato = "";
    }
    
    public void print()
    {
        System.out.println("Ano do candidato: " + nomeCandidato);
        System.out.println("Titulo do trabalho apresentado: " + tituloTrabalho);
        System.out.println("Ano de Publicação: " + ano);
    }
}



