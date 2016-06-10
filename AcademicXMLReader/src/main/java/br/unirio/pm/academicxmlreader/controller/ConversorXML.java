package br.unirio.pm.academicxmlreader.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * Classe responsavel pela transformacao de um InputStream(obtida da conexao com a url) em Document
 */
//DESCRICAO SUJEITA A MUDANCA CASO ESSA CLASSE VA ABRIR O ARQUIVO ZIP (Curriculo dos profs)
public class ConversorXML 
{       
    /**
    *
    * Metodo responsável por retornar uma InputStream, obtida da conexão feita a uma URL
    */
    public InputStream acessarUrl(String urlXml) throws MalformedURLException, IOException
    {
        try
        {
            URL url = new URL(urlXml);
            URLConnection conexao = url.openConnection();
            InputStream stream = conexao.getInputStream();
            
            return stream;
        }
        catch (MalformedURLException e)
        {
            System.out.println("Erro de formato na url: " + urlXml );
            System.out.println( e ); 
            return null;
        }
        catch (IOException f)
        {
            System.out.println("Erro na abertura da url: " + urlXml );
            System.out.println( f ); 
            return null;
        }
    }
    
    
    /**
    * Transforma a InputStream de entrada em um Document
    */
    //DUVIDA: Porque ele te pede para criar uma SAXException?
    public Document xmlToDocument(String urlXml) throws SAXException, IOException, ParserConfigurationException 
    {
        InputStream stream = acessarUrl(urlXml);
        DocumentBuilderFactory documentBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document documento = null;
        
        try
        {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

            documento = documentBuilder.parse(stream);
        }
        catch(SAXException e)
        {
            System.out.println("Exceção SAX: \n" + e);
        }
        catch(IOException e)
        {
            System.out.println("Exceção IO: \n" + e);
        }
        catch(ParserConfigurationException e)
        {
            System.out.println("Exceção de conversão de XML para Document: \n " + e);
        }
        
        return documento;
    }
}
