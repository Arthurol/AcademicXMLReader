package br.unirio.pm.academicxmlreader.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe que representa uma Linha de Pesquisa, que contém um conjunto de professores
 */
public @Getter @Setter class LinhaDePesquisa 
{
    private String nome;
    private String programaPosGraduacao;
    private List<Professor> professores;
    
    public LinhaDePesquisa()
    {
        nome = "";
        programaPosGraduacao = "";
        professores = new ArrayList<>();
    }
}