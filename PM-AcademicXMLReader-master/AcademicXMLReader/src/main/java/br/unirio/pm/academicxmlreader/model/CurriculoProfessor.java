package br.unirio.pm.academicxmlreader.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Classe que representa um Curriculo de professor, com artigos publicados, orientações dadas e participações em bancas
 */
public @Getter @Setter class CurriculoProfessor 
{
    private List<Artigo> artigosRevista;
    private List<Artigo> artigosEvento;
    
    private List<Orientacao> orientacoesGraduacaoConcluidas;
    private List<Orientacao> orientacoesGraduacaoAndamento;

    private List<Orientacao> orientacoesMestradoConcluidas;
    private List<Orientacao> orientacoesMestradoAndamento; 

    private List<Orientacao> orientacoesDoutoradoConcluidas;
    private List<Orientacao> orientacoesDoutoradoAndamento; 
    
    private List<ParticipacaoBanca> bancasGraduacao;
    private List<ParticipacaoBanca> bancasMestrado;
    private List<ParticipacaoBanca> bancasDoutorado;
    
    public CurriculoProfessor()
    {
        artigosRevista = new ArrayList<>();
        artigosEvento = new ArrayList<>();
        
        orientacoesGraduacaoConcluidas = new ArrayList<>();
        orientacoesGraduacaoAndamento = new ArrayList<>();
        
        orientacoesMestradoConcluidas = new ArrayList<>();
        orientacoesMestradoAndamento = new ArrayList<>();
        
        orientacoesDoutoradoConcluidas = new ArrayList<>();
        orientacoesDoutoradoAndamento = new ArrayList<>();
        
        bancasGraduacao = new ArrayList<>();
        bancasMestrado = new ArrayList<>();
        bancasDoutorado = new ArrayList<>();
        
    }
}
