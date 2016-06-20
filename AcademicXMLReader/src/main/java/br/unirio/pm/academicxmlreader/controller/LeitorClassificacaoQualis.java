package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.EntradaQualis;
import br.unirio.pm.academicxmlreader.model.Tipo;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe responsável pela verificação das entradas no arquivo xml qualis 
 */
public class LeitorClassificacaoQualis 
{
    private final static String URL_QUALIS = "https://s3.amazonaws.com/posgraduacao/qualis.xml";
    private final static String ENTRADA = "entry";
    private final static String REGEX = "regex";
    private final static String CLASSE = "class";
    private final static String TIPO = "type";
    
    
    /**
     * Método que usa expressão regular para buscar entradas de Eventos no arquivo xml qualis
     */
    public List<EntradaQualis> buscaEntradasEvento() throws SAXException, IOException, ParserConfigurationException
    {
        ConversorXML conversor = new ConversorXML();
        Document doc = conversor.xmlToDocument(URL_QUALIS);

        List<EntradaQualis> listaQualis = new ArrayList<>();
        
        NodeList nosEntrada = doc.getElementsByTagName(ENTRADA);
        
        if (nosEntrada.getLength() == 0)
            return null;
        
        //inicio da busca nas entradas do qualis.xml
        for (int i = 0; i < nosEntrada.getLength(); i++)
        {
            EntradaQualis entradaQualis = new EntradaQualis();

            // pega a entrada de index i da NodeList
            NamedNodeMap entrada = nosEntrada.item(i).getAttributes();
            
            // transforma a string retirada da entrada do xml em uma expressão regular (ponto + asterisco nos seus inicio e fim),
            // e com esta preenche o atributo regex da EntradaQualis
            if (entrada.getNamedItem(REGEX).getNodeValue() == null || entrada.getNamedItem(REGEX).getNodeValue().isEmpty())
                continue;
            else
            {
                String regex = entrada.getNamedItem(REGEX).getNodeValue();
                entradaQualis.setRegex(".*" + regex + ".*"); 
            }
            
            // 
            if (entrada.getNamedItem(TIPO).getNodeValue() == null || entrada.getNamedItem(TIPO).getNodeValue().isEmpty())
                continue;
            else
            {
                String tipo = entrada.getNamedItem(TIPO).getNodeValue();
                if (tipo.equalsIgnoreCase("Conferência") || tipo.equalsIgnoreCase("Conferencia"))
                    entradaQualis.setTipoEntrada(Tipo.Evento);
                else
                    continue;
            }
            
            if (entrada.getNamedItem(CLASSE).getNodeValue() == null || entrada.getNamedItem(CLASSE).getNodeValue().isEmpty())
                entradaQualis.setClassificacao("NC");
            else
            {
                entradaQualis.setClassificacao(entrada.getNamedItem(CLASSE).getNodeValue());
            }
            
            listaQualis.add(entradaQualis);
        }
        return listaQualis;
    }
    
     /**
     * Método que usa expressão regular para buscar entradas de Revistas no arquivo xml qualis
     */
    public List<EntradaQualis> buscaEntradasRevista() throws SAXException, IOException, ParserConfigurationException
    {
        ConversorXML conversor = new ConversorXML();
        Document doc = conversor.xmlToDocument(URL_QUALIS);

        List<EntradaQualis> listaQualis = new ArrayList<>();
        
        NodeList nosEntrada = doc.getElementsByTagName(ENTRADA);
        
        if (nosEntrada.getLength() == 0)
            return null;
        
        //inicio da busca nas entradas do qualis.xml
        for (int i = 0; i < nosEntrada.getLength(); i++)
        {
            EntradaQualis entradaQualis = new EntradaQualis();

            // pega a entrada de index i da NodeList
            NamedNodeMap entrada = nosEntrada.item(i).getAttributes();
            
            // transforma a string retirada da entrada do xml em uma expressão regular (ponto + asterisco nos seus inicio e fim),
            // e com esta preenche o atributo regex da EntradaQualis
            if (entrada.getNamedItem(REGEX).getNodeValue() == null || entrada.getNamedItem(REGEX).getNodeValue().isEmpty())
                continue;
            else
            {
                String regex = entrada.getNamedItem(REGEX).getNodeValue();
                entradaQualis.setRegex(".*" + regex + ".*"); 
            }
            
            if (entrada.getNamedItem(TIPO).getNodeValue() == null || entrada.getNamedItem(TIPO).getNodeValue().isEmpty())
                continue;
            else
            {
                String tipo = entrada.getNamedItem(TIPO).getNodeValue();
                if (tipo.equalsIgnoreCase("periódico") || tipo.equalsIgnoreCase("periodico"))
                    entradaQualis.setTipoEntrada(Tipo.Revista);
                else
                    continue;
            }
            
            if (entrada.getNamedItem(CLASSE).getNodeValue() == null || entrada.getNamedItem(CLASSE).getNodeValue().isEmpty())
                entradaQualis.setClassificacao("NC");
            else
            {
                entradaQualis.setClassificacao(entrada.getNamedItem(CLASSE).getNodeValue());
            }
            
            listaQualis.add(entradaQualis);
        }
        return listaQualis;
        
    }

     /**
     * 
     */
    public List<Artigo> classificadorEventos(List<Artigo> artigosEntrada) throws SAXException, IOException, ParserConfigurationException
    {
        List<Artigo> artigos = artigosEntrada;
        List<EntradaQualis> listaQualis = buscaEntradasEvento();
        Pattern limpezaString = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");
        
        for (int i = 0; i < artigos.size(); i++)
        {
            String eventoArtigo = artigos.get(i).getTituloLocalPublicacao().toLowerCase();
            eventoArtigo = Normalizer.normalize(eventoArtigo, Normalizer.Form.NFD); 
            eventoArtigo = limpezaString.matcher(eventoArtigo).replaceAll("");
            
            int bestMatch = 0;
            for(int j = 0; j < listaQualis.size(); j++)
            {
                EntradaQualis entradaQualis = listaQualis.get(j);
                
                String regexQualis = entradaQualis.getRegex().toUpperCase();
                regexQualis = Normalizer.normalize(regexQualis, Normalizer.Form.NFD); 
                regexQualis = limpezaString.matcher(regexQualis).replaceAll("");
                
                Pattern pattern = Pattern.compile(regexQualis);
                Matcher matcher = pattern.matcher(eventoArtigo);
                
                if (matcher.find())
                {                
                    if (regexQualis.length() > bestMatch)
                    {
                    bestMatch = regexQualis.length();
                    artigos.get(i).setClassificacao(entradaQualis.getClassificacao());
                    }
                }
            }
        }
        return artigos;
    }
    
    /**
     * 
     */
    public List<Artigo> classificadorRevistas(List<Artigo> artigosEntrada) throws SAXException, IOException, ParserConfigurationException
    {
        List<Artigo> artigos = artigosEntrada;
        List<EntradaQualis> listaQualis = buscaEntradasRevista();
        Pattern limpezaString = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");
        
        for (int i = 0; i < artigos.size(); i++)
        {
            String eventoArtigo = artigos.get(i).getTituloLocalPublicacao().toUpperCase();
            eventoArtigo = Normalizer.normalize(eventoArtigo, Normalizer.Form.NFD); 
            eventoArtigo = limpezaString.matcher(eventoArtigo).replaceAll("");
            
            int bestMatch = 0;
            for(int j = 0; j < listaQualis.size(); j++)
            {
                EntradaQualis entradaQualis = listaQualis.get(j);
                
                String regexQualis = entradaQualis.getRegex().toUpperCase();
                regexQualis = Normalizer.normalize(regexQualis, Normalizer.Form.NFD); 
                regexQualis = limpezaString.matcher(regexQualis).replaceAll("");
                
                Pattern pattern = Pattern.compile(regexQualis);
                Matcher matcher = pattern.matcher(eventoArtigo);
                
                if (matcher.find())
                {
                    if (regexQualis.length() > bestMatch)
                    {
                    bestMatch = regexQualis.length();
                    artigos.get(i).setClassificacao(entradaQualis.getClassificacao());
                    }
                }
            }
        }
        return artigos;
    }

}