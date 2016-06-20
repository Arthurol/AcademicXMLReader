package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Orientacao;
import br.unirio.pm.academicxmlreader.model.ParticipacaoBanca;
import br.unirio.pm.academicxmlreader.model.Professor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * Classe que centraliza a chamada dos métodos das classes de leitura de XML 
 */
public class CentralDeProcessamento {
    
    /**
     * Método que retorna as Linhas de Pesquisa com seus professores e Artigos de Revista e Evento
     */
    public List<LinhaDePesquisa> preenchimentoLinhas(String nomeProgramaPosGraduacao) throws IOException, SAXException, ParserConfigurationException
    {
        LeitorProfessoresPrograma leitorProfessores = new LeitorProfessoresPrograma();
        LeitorCurriculoProfessor leitorCurriculo = new LeitorCurriculoProfessor();
        LeitorClassificacaoQualis leitorQualis = new LeitorClassificacaoQualis();
        
        
        List<LinhaDePesquisa> listaLinhas = leitorProfessores.procuraProfessoresPrograma(nomeProgramaPosGraduacao);
        
        for(int i = 0; i < listaLinhas.size(); i++)
        {
            List<Professor> professores = listaLinhas.get(i).getProfessores();
            
            for (int j = 0; j < professores.size(); j++)
            {
                Professor professor = professores.get(j);

                CurriculoProfessor curriculo = leitorCurriculo.montaCurriculoProfessor(nomeProgramaPosGraduacao, professor.getCodigoCurriculo());
                
                curriculo.setArtigosEvento(leitorQualis.classificadorEventos(curriculo.getArtigosEvento()));
                curriculo.setArtigosRevista(leitorQualis.classificadorRevistas(curriculo.getArtigosRevista()));
                
                professor.setCurriculo(curriculo);
            } 
        }
        return listaLinhas;
    }
    
    /**
     * Método que seleciona Artigos, participações em Bancas e Orientações dentro do intervalo requerido
     */
    
    public List<LinhaDePesquisa> filtraCurriculosPorIntervalo(String nomeProgramaPosGraduacao, int anoInicial, int anoFinal) throws IOException, SAXException, ParserConfigurationException
    {
        LeitorProfessoresPrograma leitorProfessores = new LeitorProfessoresPrograma();
        LeitorCurriculoProfessor leitorCurriculo = new LeitorCurriculoProfessor();
        LeitorClassificacaoQualis leitorQualis = new LeitorClassificacaoQualis();
        
        
        List<LinhaDePesquisa> listaLinhas = leitorProfessores.procuraProfessoresPrograma(nomeProgramaPosGraduacao);
        
        for(int i = 0; i < listaLinhas.size(); i++)
        {
            List<Professor> professores = listaLinhas.get(i).getProfessores();
            
            for (int j = 0; j < professores.size(); j++)
            {
                Professor professor = professores.get(j);

                CurriculoProfessor curriculo = leitorCurriculo.montaCurriculoProfessor(nomeProgramaPosGraduacao, professor.getCodigoCurriculo());
                
                //--> Filtragem de artigos publicados
                List<Artigo> listaArtigosEvento = filtraArtigos(curriculo.getArtigosEvento(), anoInicial, anoFinal);
                List<Artigo> listaArtigosRevista = filtraArtigos(curriculo.getArtigosRevista(), anoInicial, anoFinal);
                
                //Roda a classificação com base no qualis para as listas já filtradas (melhor desempenho)
                curriculo.setArtigosEvento(leitorQualis.classificadorEventos(listaArtigosEvento));
                curriculo.setArtigosRevista(leitorQualis.classificadorRevistas(listaArtigosRevista));
                
                //Filtragem de participações em bancas
                curriculo.setBancasGraduacao(filtraBancas(curriculo.getBancasGraduacao(), anoInicial, anoFinal));
                curriculo.setBancasMestrado(filtraBancas(curriculo.getBancasMestrado(), anoInicial, anoFinal));
                curriculo.setBancasDoutorado(filtraBancas(curriculo.getBancasDoutorado(), anoInicial, anoFinal));
                
                //Filtragem de orientações
                    //Graduação
                curriculo.setOrientacoesGraduacaoConcluidas(filtraOrientacoes(curriculo.getOrientacoesGraduacaoConcluidas(), anoInicial, anoFinal));
                curriculo.setOrientacoesGraduacaoAndamento(filtraOrientacoes(curriculo.getOrientacoesGraduacaoAndamento(), anoInicial, anoFinal));
                
                    //Mestrado
                curriculo.setOrientacoesMestradoConcluidas(filtraOrientacoes(curriculo.getOrientacoesMestradoConcluidas(), anoInicial, anoFinal));
                curriculo.setOrientacoesMestradoAndamento(filtraOrientacoes(curriculo.getOrientacoesMestradoAndamento(), anoInicial, anoFinal));
                    //Doutorado
                curriculo.setOrientacoesDoutoradoConcluidas(filtraOrientacoes(curriculo.getOrientacoesDoutoradoConcluidas(), anoInicial, anoFinal));
                curriculo.setOrientacoesDoutoradoAndamento(filtraOrientacoes(curriculo.getOrientacoesDoutoradoAndamento(), anoInicial, anoFinal));
                
                professor.setCurriculo(curriculo);
            } 
        }
        return listaLinhas;
    }
    
    
    /**
     * Método seleciona Artigos dentro do intervalo requerido e retorna uma lista com os Artigos
     */
    
    public List<Artigo> filtraArtigos(List<Artigo> listaArtigos, int anoInicial, int anoFinal)
    {
        List<Artigo> listaFiltrada = new ArrayList<>();
        
        if (listaArtigos != null)
        {
            for(int i = 0; i < listaArtigos.size(); i++)
            {
                Artigo artigo = listaArtigos.get(i);
                if(artigo.getAnoPublicacao() >= anoInicial && artigo.getAnoPublicacao() <= anoFinal)
                    listaFiltrada.add(artigo);
            }
        }
        return listaFiltrada;
    }
    
    
    /**
     * Método seleciona Orientações dentro do intervalo requerido e retorna uma lista com as Orientações
     */
    
    public List<Orientacao> filtraOrientacoes(List<Orientacao> listaOrientacoes, int anoInicial, int anoFinal)
    {
        List<Orientacao> listaFiltrada = new ArrayList<>();
        
        if (listaOrientacoes != null)
        {
            for(int i = 0; i < listaOrientacoes.size(); i++)
            {
                Orientacao orientacao = listaOrientacoes.get(i);
                if(orientacao.getAno() >= anoInicial && orientacao.getAno() <= anoFinal)
                    listaFiltrada.add(orientacao);
            }
        }
        return listaFiltrada;
    }
    
    
    /**
     * Método seleciona participações em Bancas dentro do intervalo requerido e retorna uma lista com as participações em Bancas
     */
    
    public List<ParticipacaoBanca> filtraBancas(List<ParticipacaoBanca> listaParticipacaoBanca, int anoInicial, int anoFinal)
    {
        List<ParticipacaoBanca> listaFiltrada = new ArrayList<>();
        
        if (listaParticipacaoBanca != null)
        {
            for(int i = 0; i < listaParticipacaoBanca.size(); i++)
            {
                ParticipacaoBanca banca = listaParticipacaoBanca.get(i);
                if(banca.getAno() >= anoInicial && banca.getAno() <= anoFinal)
                    listaFiltrada.add(banca);
            }
        }
       return listaFiltrada;
    }
}