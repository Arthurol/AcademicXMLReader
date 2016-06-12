package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * Classe responsavel por ler os curriculos dos professores, salvos em formato ZIP
 */
public class LeitorCurriculoProfessor 
{
    LeitorClassificacaoQualis leitorQualis = new LeitorClassificacaoQualis();
    
    //Declaração de constantes que serão utilizadas no processo de busca no XML
    private static final String ARTIGO_PUBLICADO = "ARTIGO-PUBLICADO";
    private static final String TITULO_ARTIGO = "TITULO-DO-ARTIGO";
    private static final String ANO_ARTIGO = "ANO-DO-ARTIGO";
    private static final String DADOS_BASICOS_ARTIGO = "DADOS-BASICOS-DO-ARTIGO";
    private static final String DADOS_DETALHADOS_ARTIGO = "DETALHAMENTO-DO-ARTIGO";
    private static final String REVISTA_EVENTO = "TITULO-DO-PERIODICO-OU-REVISTA";
    private static final String CODIGO_REVISTA_EVENTO = "ISSN";
    
    /**
    * Retorna uma lista com todos os artigos publicados pelo professor
    */
    public List<Artigo> getArtigosPublicados(Document doc)
    {
        NodeList nodeListArtigos = doc.getElementsByTagName(ARTIGO_PUBLICADO);
        
        if (nodeListArtigos.getLength() == 0)
        {
            System.out.println("Erro: Não existem linhas de pesquisa no XML acessado.");
            return null;
        }
        
        List<Artigo> listaArtigos = new ArrayList<>();
        
        
                // Inicio da busca nos artigos publicados
                for (int i = 0; i < nodeListArtigos.getLength(); i++)
                {
                    Artigo artigo = new Artigo();
                    
                    // pega o artigo de index i da NodeList
                    NodeList nodeArtigo = nodeListArtigos.item(i).getChildNodes();
                    
                            for (int j = 0; j < nodeArtigo.getLength(); j++)
                            {
                                // nó utilizado para percorrer os nós filhos do artigo atual da iteração e neles buscar dados
                                Node buscador = nodeArtigo.item(j);
                                
                                if (buscador.getNodeName().equals(DADOS_BASICOS_ARTIGO))
                                {
                                    // recupera o titulo e o ano de publicação do artigo
                                    artigo.setTitulo(buscador.getAttributes().getNamedItem(TITULO_ARTIGO).getNodeValue());
                                    artigo.setAnoPublicacao(buscador.getAttributes().getNamedItem(ANO_ARTIGO).getNodeValue());
                                    continue;
                                }
                                
                                if (buscador.getNodeName().equals(DADOS_DETALHADOS_ARTIGO))
                                {
                                    // recupera o titulo e código da revista/evento onde o artigo foi publicado
                                    artigo.setTituloLocalPublicacao(buscador.getAttributes().getNamedItem(REVISTA_EVENTO).getNodeValue());
                                    artigo.setCodigoLocalPublicacao(buscador.getAttributes().getNamedItem(CODIGO_REVISTA_EVENTO).getNodeValue());
                                }
                            }
                    listaArtigos.add(artigo);
                }
                
        return listaArtigos;
    }
    
    
    /**
    * Faz a chamada de todos os métodos de busca no curriculo.xml e retorna um curriculo de professor
    */
    public CurriculoProfessor montaCurriculoProfessor(String nomeProgramaPosGraduacao, String codigoProfessor) throws SAXException, IOException, ParserConfigurationException
    {
        String urlCurriculoProfessor = "https://s3.amazonaws.com/posgraduacao/" + nomeProgramaPosGraduacao + "/" + codigoProfessor + ".zip";
        ConversorXML conversor = new ConversorXML();
        Document doc = conversor.zipToDocument(urlCurriculoProfessor);
        
        if (doc == null)
        {
            System.out.println("Erro: O Programa de pós-graduação informado não existe ou está indisponível no momento.");
            return null;
        }
        
        return null;
    }
}
