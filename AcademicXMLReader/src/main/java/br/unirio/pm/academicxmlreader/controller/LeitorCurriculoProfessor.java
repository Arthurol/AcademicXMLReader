package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.Orientacao;
import br.unirio.pm.academicxmlreader.model.ParticipacaoBanca;
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
 * Classe responsavel por ler os curriculos dos professores de um arquivo XML, compactados em formato ZIP
 */
public class LeitorCurriculoProfessor 
{
    //Declaração de constantes que serão utilizadas no processo de busca no XML
    
    //-->Constantes Gerais
    private static final String NATUREZA = "NATUREZA";
    private static final String ANO = "ANO";
    private static final String TITULO = "TITULO";
    private static final String TITULO_TRABALHO = "TITULO-DO-TRABALHO";
    private static final String ORIENTADO = "NOME-DO-ORIENTADO";
    private static final String ORIENTANDO = "NOME-DO-ORIENTANDO"; 
    private static final String CANDIDATO = "NOME-DO-CANDIDATO"; 
    
    //--> Artigos Revista
    private static final String ARTIGO_PUBLICADO = "ARTIGO-PUBLICADO";
    private static final String TITULO_ARTIGO = "TITULO-DO-ARTIGO";
    private static final String ANO_ARTIGO = "ANO-DO-ARTIGO";
    private static final String DADOS_BASICOS_ARTIGO_REVISTA = "DADOS-BASICOS-DO-ARTIGO";
    private static final String DADOS_DETALHADOS_ARTIGO_REVISTA = "DETALHAMENTO-DO-ARTIGO";
    private static final String REVISTA_EVENTO = "TITULO-DO-PERIODICO-OU-REVISTA";
    private static final String CODIGO_REVISTA_EVENTO = "ISSN";
    
    //--> Artigos Evento
    private static final String ARTIGO_EVENTO = "TRABALHO-EM-EVENTOS";
    private static final String ANO_TRABALHO = "ANO-DO-TRABALHO";
    private static final String DADOS_BASICOS_ARTIGO_EVENTO = "DADOS-BASICOS-DO-TRABALHO";
    private static final String DADOS_DETALHADOS_ARTIGO_EVENTO = "DETALHAMENTO-DO-TRABALHO";
    private static final String NOME_EVENTO = "NOME-DO-EVENTO";
    private static final String CODIGO_EVENTO = "ISBN";
    
    //--> Orientações Graduação
    private static final String ORIENTACAO_GRADUACAO_CONCLUIDA = "OUTRAS-ORIENTACOES-CONCLUIDAS";
    private static final String DADOS_BASICOS_ORIENTACAO_GRADUACAO_CONCLUIDA = "DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS";
    private static final String DETALHAMENTO_ORIENTACAO_GRADUACAO_CONCLUIDA = "DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS";
        
    private static final String ORIENTACAO_GRADUACAO_ANDAMENTO = "ORIENTACAO-EM-ANDAMENTO-DE-GRADUACAO";
    private static final String DADOS_BASICOS_ORIENTACAO_GRADUACAO_ANDAMENTO = "DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-GRADUACAO";
    private static final String DETALHAMENTO_ORIENTACAO_GRADUACAO_ANDAMENTO = "DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-GRADUACAO";
    
    private static final String TCC_CONCLUIDO = "TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO";
    private static final String TCC_ANDAMENTO = "Trabalho de conclusão de curso de graduação";
    
    //--> Orientações Mestrado
    private static final String ORIENTACAO_MESTRADO_CONCLUIDA = "ORIENTACOES-CONCLUIDAS-PARA-MESTRADO";
    private static final String DADOS_BASICOS_ORIENTACAO_MESTRADO_CONCLUIDA = "DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO";
    private static final String DETALHAMENTO_ORIENTACAO_MESTRADO_CONCLUIDA = "DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO";
                    
    private static final String ORIENTACAO_MESTRADO_ANDAMENTO = "ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO";
    private static final String DADOS_BASICOS_ORIENTACAO_MESTRADO_ANDAMENTO = "DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO";
    private static final String DETALHAMENTO_ORIENTACAO_MESTRADO_ANDAMENTO = "DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO";
    
    //--> Orientações Doutorado
    private static final String ORIENTACAO_DOUTORADO_CONCLUIDA = "ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO";
    private static final String DADOS_BASICOS_ORIENTACAO_DOUTORADO_CONCLUIDA = "DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO";
    private static final String DETALHAMENTO_ORIENTACAO_DOUTORADO_CONCLUIDA = "DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO";
    
    private static final String ORIENTACAO_DOUTORADO_ANDAMENTO = "ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO";
    private static final String DADOS_BASICOS_ORIENTACAO_DOUTORADO_ANDAMENTO = "DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO";
    private static final String DETALHAMENTO_ORIENTACAO_DOUTORADO_ANDAMENTO = "DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO";
    
    //--> Participações Banca Graduação       
    private static final String BANCA_GRADUACAO = "PARTICIPACAO-EM-BANCA-DE-GRADUACAO";
    private static final String DADOS_BASICOS_BANCA_GRADUACAO = "DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-GRADUACAO";
    private static final String DETALHAMENTO_BANCA_GRADUACAO = "DETALHAMENTO-DA-PARTICIPACAO-EM-BANCA-DE-GRADUACAO";
    
    //--> Participações Banca Mestrado
    private static final String BANCA_MESTRADO = "PARTICIPACAO-EM-BANCA-DE-MESTRADO";
    private static final String DADOS_BASICOS_BANCA_MESTRADO = "DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-MESTRADO";
    private static final String DETALHAMENTO_BANCA_MESTRADO = "DETALHAMENTO-DA-PARTICIPACAO-EM-BANCA-DE-MESTRADO";
    
    //--> Participações Banca Doutorado
    private static final String BANCA_DOUTORADO = "PARTICIPACAO-EM-BANCA-DE-DOUTORADO";
    private static final String DADOS_BASICOS_BANCA_DOUTORADO = "DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-DOUTORADO";
    private static final String DETALHAMENTO_BANCA_DOUTORADO = "DETALHAMENTO-DA-PARTICIPACAO-EM-BANCA-DE-DOUTORADO";
    
    
    /**
    * Retorna todos os artigos publicados em revistas ou periódicos pelo professor
    */
    private List<Artigo> buscaArtigosRevista(Document doc)
    {
        NodeList nodeListArtigos = doc.getElementsByTagName(ARTIGO_PUBLICADO);
        
        if (nodeListArtigos.getLength() == 0)
            return null;
        
        List<Artigo> listaArtigos = new ArrayList<>();
        
        // Inicio da busca nos artigos publicados
        for (int i = 0; i < nodeListArtigos.getLength(); i++)
        {
            Artigo artigo = new Artigo();

            // pega o artigo de index i da NodeList
            NodeList nodeArtigo = nodeListArtigos.item(i).getChildNodes();

            for (int j = 0; j < nodeArtigo.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos do artigo atual e neles buscar dados
                Node buscador = nodeArtigo.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ARTIGO_REVISTA))
                {
                    // recupera o titulo e o ano de publicação do artigo
                    artigo.setTitulo(buscador.getAttributes().getNamedItem(TITULO_ARTIGO).getNodeValue());
                    artigo.setAnoPublicacao(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO_ARTIGO).getNodeValue()));
                }

                if (buscador.getNodeName().equals(DADOS_DETALHADOS_ARTIGO_REVISTA))
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
    * Retorna todos os artigos publicados em eventos pelo professor
    */
    private List<Artigo> buscaArtigosEvento(Document doc)
    {
        NodeList nodeListArtigos = doc.getElementsByTagName(ARTIGO_EVENTO);
        
        if (nodeListArtigos.getLength() == 0)
            return null;
        
        List<Artigo> listaArtigos = new ArrayList<>();
        
        // Inicio da busca nos artigos publicados
        for (int i = 0; i < nodeListArtigos.getLength(); i++)
        {
            Artigo artigo = new Artigo();

            // pega o artigo de index i da NodeList
            NodeList nodeArtigo = nodeListArtigos.item(i).getChildNodes();
            
           for (int j = 0; j < nodeArtigo.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos do artigo atual e neles buscar dados
                Node buscador = nodeArtigo.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ARTIGO_EVENTO))
                {
                    // recupera o titulo e o ano de publicação do artigo
                    artigo.setTitulo(buscador.getAttributes().getNamedItem(TITULO_TRABALHO).getNodeValue());
                    artigo.setAnoPublicacao(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO_TRABALHO).getNodeValue()));
                }

                if (buscador.getNodeName().equals(DADOS_DETALHADOS_ARTIGO_EVENTO))
                {
                    // recupera o titulo e código da revista/evento onde o artigo foi publicado
                    artigo.setTituloLocalPublicacao(buscador.getAttributes().getNamedItem(NOME_EVENTO).getNodeValue());
                    artigo.setCodigoLocalPublicacao(buscador.getAttributes().getNamedItem(CODIGO_EVENTO).getNodeValue());
                }
            }
            listaArtigos.add(artigo);
        }
                
        return listaArtigos;
    }
    
    /**
    * Retorna todas as orientações de projeto final de graduação concluídas que o professor 
    */
    private List<Orientacao> buscaOrientacoesGraduacaoConcluidas(Document doc)
    {
        NodeList nodeListOrientacoes = doc.getElementsByTagName(ORIENTACAO_GRADUACAO_CONCLUIDA);
        
        if (nodeListOrientacoes.getLength() == 0)
            return null;
        
        List<Orientacao> listaOrientacoes = new ArrayList<>();
        
        // Inicio da busca nas orientações de graduacao concluidas
        for (int i = 0; i < nodeListOrientacoes.getLength(); i++)
        {
            Orientacao orientacao = new Orientacao();

            // pega a orientação de index i da NodeList
            NodeList nodeOrientacao = nodeListOrientacoes.item(i).getChildNodes();

            for (int j = 0; j < nodeOrientacao.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da orientacao e neles buscar dados
                Node buscador = nodeOrientacao.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ORIENTACAO_GRADUACAO_CONCLUIDA))
                { 
                    if (buscador.getAttributes().getNamedItem(NATUREZA).getNodeValue().equalsIgnoreCase(TCC_CONCLUIDO))
                    {           
                        // recupera o ano da orientação e o título do projeto
                        orientacao.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                        orientacao.setTituloProjeto(buscador.getAttributes().getNamedItem(TITULO).getNodeValue());
                    }
                    else
                        orientacao = null;
                        break;

                }

                if (buscador.getNodeName().equals(DETALHAMENTO_ORIENTACAO_GRADUACAO_CONCLUIDA))
                    // recupera o nome do orientando
                    orientacao.setNomeOrientado(buscador.getAttributes().getNamedItem(ORIENTADO).getNodeValue());
            }

            if (orientacao != null)
                listaOrientacoes.add(orientacao);
        }
        return listaOrientacoes;
    }
    
    /**
    * Retorna todas as orientações de projeto final de graduação que estão em andamento dadas pelo professor
    */
    private List<Orientacao> buscaOrientacoesGraduacaoAndamento(Document doc)
    {
        NodeList nodeListOrientacoes = doc.getElementsByTagName(ORIENTACAO_GRADUACAO_ANDAMENTO);
        
        if (nodeListOrientacoes.getLength() == 0)
            return null;
        
        List<Orientacao> listaOrientacoes = new ArrayList<>();
        
        // Inicio da busca nas orientações de graduacao em andamento
        for (int i = 0; i < nodeListOrientacoes.getLength(); i++)
        {
            Orientacao orientacao = new Orientacao();

            // pega a orientação de index i da NodeList
            NodeList nodeOrientacao = nodeListOrientacoes.item(i).getChildNodes();

            for (int j = 0; j < nodeOrientacao.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da orientacao e neles buscar dados
                Node buscador = nodeOrientacao.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ORIENTACAO_GRADUACAO_ANDAMENTO))
                { 
                    if (buscador.getAttributes().getNamedItem(NATUREZA).getNodeValue().equalsIgnoreCase(TCC_ANDAMENTO))
                    { 
                        // recupera o ano da orientação e o título do projeto
                        orientacao.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                        orientacao.setTituloProjeto(buscador.getAttributes().getNamedItem(TITULO_TRABALHO).getNodeValue());
                    }
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_ORIENTACAO_GRADUACAO_ANDAMENTO))
                    // recupera o nome do orientando
                    orientacao.setNomeOrientado(buscador.getAttributes().getNamedItem(ORIENTANDO).getNodeValue());

            }
            listaOrientacoes.add(orientacao);
        }
        return listaOrientacoes;
    }
    
    /**
    * Retorna todas as orientações de mestrado concluídas que o professor deu
    */
    private List<Orientacao> buscaOrientacoesMestradoConcluidas(Document doc)
    {
        NodeList nodeListOrientacoes = doc.getElementsByTagName(ORIENTACAO_MESTRADO_CONCLUIDA);
        
        if (nodeListOrientacoes.getLength() == 0)
            return null;
        
        List<Orientacao> listaOrientacoes = new ArrayList<>();
        
        // Inicio da busca nas orientações de graduacao concluidas
        for (int i = 0; i < nodeListOrientacoes.getLength(); i++)
        {
            Orientacao orientacao = new Orientacao();

            // pega a orientação de index i da NodeList
            NodeList nodeOrientacao = nodeListOrientacoes.item(i).getChildNodes();

            for (int j = 0; j < nodeOrientacao.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da orientação e neles buscar dados
                Node buscador = nodeOrientacao.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ORIENTACAO_MESTRADO_CONCLUIDA))
                {                          
                    // recupera o ano da orientação e o título do projeto
                    orientacao.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    orientacao.setTituloProjeto(buscador.getAttributes().getNamedItem(TITULO).getNodeValue());
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_ORIENTACAO_MESTRADO_CONCLUIDA))
                    // recupera o nome do orientado
                    orientacao.setNomeOrientado(buscador.getAttributes().getNamedItem(ORIENTADO).getNodeValue());
            }
            listaOrientacoes.add(orientacao);
        }
        return listaOrientacoes;
    }
    
    /**
    * Retorna todas as orientações de mestrado que estão em andamento dadas pelo professor
    */
    private List<Orientacao> buscaOrientacoesMestradoAndamento(Document doc)
    {
        NodeList nodeListOrientacoes = doc.getElementsByTagName(ORIENTACAO_MESTRADO_ANDAMENTO);
        
        if (nodeListOrientacoes.getLength() == 0)
            return null;
        
        List<Orientacao> listaOrientacoes = new ArrayList<>();
        
        // Inicio da busca nas orientações de graduacao concluidas
        for (int i = 0; i < nodeListOrientacoes.getLength(); i++)
        {
            Orientacao orientacao = new Orientacao();

            // pega a orientação de index i da NodeList
            NodeList nodeOrientacao = nodeListOrientacoes.item(i).getChildNodes();

            for (int j = 0; j < nodeOrientacao.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da orientação e neles buscar dados
                Node buscador = nodeOrientacao.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ORIENTACAO_MESTRADO_ANDAMENTO))
                {                          
                    // recupera o ano da orientação e o título do projeto
                    orientacao.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    orientacao.setTituloProjeto(buscador.getAttributes().getNamedItem(TITULO_TRABALHO).getNodeValue());
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_ORIENTACAO_MESTRADO_ANDAMENTO))
                    // recupera o nome do orientado
                    orientacao.setNomeOrientado(buscador.getAttributes().getNamedItem(ORIENTANDO).getNodeValue());
            }
            listaOrientacoes.add(orientacao);
        }
        return listaOrientacoes;
    }
    
    /**
    * Retorna todas as orientações de doutorado concluídas que o professor deu
    */
    private List<Orientacao> buscaOrientacoesDoutoradoConcluidas(Document doc)
    {
        NodeList nodeListOrientacoes = doc.getElementsByTagName(ORIENTACAO_DOUTORADO_CONCLUIDA);
        
        if (nodeListOrientacoes.getLength() == 0)
            return null;
        
        List<Orientacao> listaOrientacoes = new ArrayList<>();
        
        // Inicio da busca nas orientações de graduacao concluidas
        for (int i = 0; i < nodeListOrientacoes.getLength(); i++)
        {
            Orientacao orientacao = new Orientacao();

            // pega a orientação de index i da NodeList
            NodeList nodeOrientacao = nodeListOrientacoes.item(i).getChildNodes();

            for (int j = 0; j < nodeOrientacao.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da orientação e neles buscar dados
                Node buscador = nodeOrientacao.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ORIENTACAO_DOUTORADO_CONCLUIDA))
                {                          
                    // recupera o ano da orientação e o título do projeto
                    orientacao.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    orientacao.setTituloProjeto(buscador.getAttributes().getNamedItem(TITULO).getNodeValue());
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_ORIENTACAO_DOUTORADO_CONCLUIDA))
                    // recupera o nome do orientado
                    orientacao.setNomeOrientado(buscador.getAttributes().getNamedItem(ORIENTADO).getNodeValue());
            }
            listaOrientacoes.add(orientacao);
        }
        return listaOrientacoes;
    }
    
    /**
    * Retorna todas as orientações de doutorado que estão em andamento dadas pelo professor
    */
    private List<Orientacao> buscaOrientacoesDoutoradoAndamento(Document doc)
    {
        NodeList nodeListOrientacoes = doc.getElementsByTagName(ORIENTACAO_DOUTORADO_ANDAMENTO);
        
        if (nodeListOrientacoes.getLength() == 0)
            return null;
        
        List<Orientacao> listaOrientacoes = new ArrayList<>();
        
        // Inicio da busca nas orientações de graduacao concluidas
        for (int i = 0; i < nodeListOrientacoes.getLength(); i++)
        {
            Orientacao orientacao = new Orientacao();

            // pega a orientação de index i da NodeList
            NodeList nodeOrientacao = nodeListOrientacoes.item(i).getChildNodes();

            for (int j = 0; j < nodeOrientacao.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da orientação e neles buscar dados
                Node buscador = nodeOrientacao.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_ORIENTACAO_DOUTORADO_ANDAMENTO))
                {                          
                    // recupera o ano da orientação e o título do projeto
                    orientacao.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    orientacao.setTituloProjeto(buscador.getAttributes().getNamedItem(TITULO_TRABALHO).getNodeValue());
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_ORIENTACAO_DOUTORADO_ANDAMENTO))
                    // recupera o nome do orientado
                    orientacao.setNomeOrientado(buscador.getAttributes().getNamedItem(ORIENTANDO).getNodeValue());
            }
            listaOrientacoes.add(orientacao);
        }
        return listaOrientacoes;
    }
    
    /**
    * Retorna todas as participações que o professor teve em bancas avaliadoras de trabalhos finais de graduação
    */
    private List<ParticipacaoBanca> buscaParticipacoesBancaGraduacao(Document doc)
    {
        NodeList nodeListBancas = doc.getElementsByTagName(BANCA_GRADUACAO);
        
        if (nodeListBancas.getLength() == 0)
            return null;
        
        List<ParticipacaoBanca> listaBancas = new ArrayList<>();
        
        // Inicio da busca nas participacoes em bancas
        for (int i = 0; i < nodeListBancas.getLength(); i++)
        {
            ParticipacaoBanca banca = new ParticipacaoBanca();

            // pega a participacao em banca de index i da NodeList
            NodeList nodeBanca = nodeListBancas.item(i).getChildNodes();

            for (int j = 0; j < nodeBanca.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da participacao em banca e neles buscar dados
                Node buscador = nodeBanca.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_BANCA_GRADUACAO))
                {                          
                    // recupera o ano da banca e o título do trabalho
                    banca.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    banca.setTituloTrabalho(buscador.getAttributes().getNamedItem(TITULO).getNodeValue());
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_BANCA_GRADUACAO))
                    // recupera o nome do candidato
                    banca.setNomeCandidato(buscador.getAttributes().getNamedItem(CANDIDATO).getNodeValue());
            }
            listaBancas.add(banca);
        }
        return listaBancas;
    }
    
    /**
    * Retorna todas as participações que o professor teve em bancas avaliadoras de dissertações de mestrado
    */
    private List<ParticipacaoBanca> buscaParticipacoesBancaMestrado(Document doc)
    {
        NodeList nodeListBancas = doc.getElementsByTagName(BANCA_MESTRADO);
        
        if (nodeListBancas.getLength() == 0)
            return null;
        
        List<ParticipacaoBanca> listaBancas = new ArrayList<>();
        
        // Inicio da busca nas participacoes em bancas
        for (int i = 0; i < nodeListBancas.getLength(); i++)
        {
            ParticipacaoBanca banca = new ParticipacaoBanca();

            // pega a participacao em banca de index i da NodeList
            NodeList nodeBanca = nodeListBancas.item(i).getChildNodes();

            for (int j = 0; j < nodeBanca.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da participacao em banca e neles buscar dados
                Node buscador = nodeBanca.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_BANCA_MESTRADO))
                {                          
                    // recupera o ano da banca e o título do trabalho
                    banca.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    banca.setTituloTrabalho(buscador.getAttributes().getNamedItem(TITULO).getNodeValue());  
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_BANCA_MESTRADO))
                    // recupera o nome do candidato
                    banca.setNomeCandidato(buscador.getAttributes().getNamedItem(CANDIDATO).getNodeValue());
            }
            listaBancas.add(banca);
        }
        return listaBancas;
    }
    
    /**
    * Retorna todas as participações que o professor teve em bancas avaliadoras de defesas de tese de doutorado
    */
    private List<ParticipacaoBanca> buscaParticipacoesBancaDoutorado(Document doc)
    {
        NodeList nodeListBancas = doc.getElementsByTagName(BANCA_DOUTORADO);
        
        if (nodeListBancas.getLength() == 0)
            return null;
        
        List<ParticipacaoBanca> listaBancas = new ArrayList<>();
        
        // Inicio da busca nas participacoes em bancas
        for (int i = 0; i < nodeListBancas.getLength(); i++)
        {
            ParticipacaoBanca banca = new ParticipacaoBanca();

            // pega a participacao em banca de index i da NodeList
            NodeList nodeBanca = nodeListBancas.item(i).getChildNodes();

            for (int j = 0; j < nodeBanca.getLength(); j++)
            {
                // nó utilizado para percorrer os nós filhos da participacao em banca e neles buscar dados
                Node buscador = nodeBanca.item(j);

                if (buscador.getNodeName().equals(DADOS_BASICOS_BANCA_DOUTORADO))
                {                          
                    // recupera o ano da banca e o título do trabalho
                    banca.setAno(Integer.parseInt(buscador.getAttributes().getNamedItem(ANO).getNodeValue()));
                    banca.setTituloTrabalho(buscador.getAttributes().getNamedItem(TITULO).getNodeValue());
                }

                if (buscador.getNodeName().equals(DETALHAMENTO_BANCA_DOUTORADO))
                    // recupera o nome do candidato
                    banca.setNomeCandidato(buscador.getAttributes().getNamedItem(CANDIDATO).getNodeValue());
            }
            listaBancas.add(banca);
        }
        return listaBancas;
    }
    
    /**
    * Faz a chamada de todos os métodos de busca no curriculo.xml e retorna um curriculo de professor
    */
    public CurriculoProfessor montaCurriculoProfessor(String nomeProgramaPosGraduacao, String codigoProfessor) throws SAXException, IOException, ParserConfigurationException
    {
        String urlCurriculoProfessor = "https://s3.amazonaws.com/posgraduacao/" + nomeProgramaPosGraduacao + "/" + codigoProfessor + ".zip";
        ConversorXML conversor = new ConversorXML();
        Document docCurriculo = conversor.zipToDocument(urlCurriculoProfessor);
        
        if (docCurriculo == null)
        {
            System.out.println("Erro: O Programa de pós-graduação informado não existe ou está indisponível no momento.");
            return null;
        }
        
        CurriculoProfessor curriculo = new CurriculoProfessor();
        
        curriculo.setArtigosRevista(buscaArtigosRevista(docCurriculo));
        curriculo.setArtigosEvento(buscaArtigosEvento(docCurriculo)); 
        
        curriculo.setOrientacoesGraduacaoConcluidas(buscaOrientacoesGraduacaoConcluidas(docCurriculo));
        curriculo.setOrientacoesGraduacaoAndamento(buscaOrientacoesGraduacaoAndamento(docCurriculo));
        curriculo.setOrientacoesMestradoConcluidas(buscaOrientacoesMestradoConcluidas(docCurriculo));
        curriculo.setOrientacoesMestradoAndamento(buscaOrientacoesMestradoAndamento(docCurriculo));
        curriculo.setOrientacoesDoutoradoConcluidas(buscaOrientacoesDoutoradoConcluidas(docCurriculo));
        curriculo.setOrientacoesDoutoradoAndamento(buscaOrientacoesDoutoradoAndamento(docCurriculo));
        
        curriculo.setBancasGraduacao(buscaParticipacoesBancaGraduacao(docCurriculo));
        curriculo.setBancasMestrado(buscaParticipacoesBancaMestrado(docCurriculo));
        curriculo.setBancasDoutorado(buscaParticipacoesBancaDoutorado(docCurriculo));
        
        return curriculo;
    }
}
