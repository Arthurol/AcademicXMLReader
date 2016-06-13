package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Classe que representa uma orientação de graduação, mestrado ou doutorado realizada pelo professor
 */
public @Getter @Setter class Orientacao 
{
    private String ano;
    private String nomeOrientado;
    private String tituloProjeto;
    
    public Orientacao()
    {
        ano = "";
        nomeOrientado = "";
        tituloProjeto = "";
    }
}
