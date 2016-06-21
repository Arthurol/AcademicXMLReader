package br.unirio.pm.academicxmlreader.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe que representa uma entrada do arquivo qualis.xml, contendo a regex do evento/revista, a classificação e a diferenciação revista/evento.
 */
public @Getter @Setter class EntradaQualis {
    private String regex;
    private String classificacao;
    private Tipo tipoEntrada;
    
    public EntradaQualis()
    {
        regex = "";
        classificacao = "";
        tipoEntrada = null;
    }
}
