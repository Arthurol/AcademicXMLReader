package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe que representa os artigos cientificos publicados pelos professores dos programas de pós-graduação
 */
public @Getter @Setter class Artigo 
{
    private String titulo;
    private int anoPublicacao;
    private String tituloLocalPublicacao; // Titulo da revista ou evento onde o artigo foi publicado
    private String codigoLocalPublicacao;
    private String classificacao; // Classificação do evento ou revista onde o artigo foi publicado
    
    public Artigo()
    {
        titulo = "";
        anoPublicacao = -1;
        tituloLocalPublicacao = "";
        codigoLocalPublicacao = "";
        classificacao = "NC";
    }
    
    /**
    * Imprime a descrição do artigo
    */
    public void print()
    {
        System.out.println("Titulo: " + titulo);
        System.out.println("Ano de Publicação: " + anoPublicacao);
        System.out.println("Publicado em: " + tituloLocalPublicacao + ", de código: " + codigoLocalPublicacao);
        System.out.println("Classificação: " + classificacao + "\n");
    }
       
}
