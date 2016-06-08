package br.unirio.pm.academicxmlreader.controller;

import java.io.IOException;
import java.io.InputStream;

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
public class ConversorXML {
         
    /**
    * Transforma a InputStream de entrada em um Document
    */
    //DUVIDA: Porque ele te pede para criar uma SAXException?
    public Document xmlToDocument(InputStream stream) throws SAXException, IOException, ParserConfigurationException 
    {
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
