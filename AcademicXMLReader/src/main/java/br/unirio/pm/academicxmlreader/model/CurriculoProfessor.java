package br.unirio.pm.academicxmlreader.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Arthur
 */
public @Getter @Setter class CurriculoProfessor 
{
    private List<Artigo> artigosRevista;
    private List<Artigo> artigosEvento;
    private List<Orientacao> orientacoesConcluidas;
    private List<Orientacao> orientacoesEmAndamento;    
    private List<ParticipacaoBanca> bancasGraduacao;
    private List<ParticipacaoBanca> bancasMestrado;
    private List<ParticipacaoBanca> bancasDoutorado;
    
    
}
