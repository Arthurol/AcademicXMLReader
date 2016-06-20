package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Classe que representa uma orientação de graduação, mestrado ou doutorado realizada pelo professor
 */
public @Getter @Setter class Orientacao 
{
    private int ano;
    private String nomeOrientado;
    private String tituloProjeto;
    
    public Orientacao()
    {
        ano = -1;
        nomeOrientado = "";
        tituloProjeto = "";
    }
    
    /**
    * Imprime os dados das orientações realizadas pelo Professor
    */
    public void print()
    {
        System.out.println("Nome do orientado/orientando: " + nomeOrientado);
        System.out.println("Titulo do projeto: " + tituloProjeto);
        System.out.println("Ano da Orientação: " + ano);
    }
}
