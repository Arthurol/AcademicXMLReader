package br.unirio.pm.academicxmlreader.controller;

import java.io.IOException;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * Classe responsavel pela leitura de um arquivo XML, para fazer a checagem da existencia de um determinado programa de pos-graduacao
 */
public class LeitorProgramaPosGraduacao 
{
    /**
    * Transforma a InputStream de entrada em um Document
    */
    public boolean procuraProgramaPosGraduacao(String urlXml, String nomeProgramaPosGraduacao) throws IOException, SAXException, ParserConfigurationException
    {
        Conexao conexao = new Conexao();
        ConversorXML conversor = new ConversorXML();
        
        URLConnection conn = conexao.acessarUrl(urlXml);
        
        if (conn == null)
            return false;
        
        Document doc = conversor.xmlToDocument(conn.getInputStream());
        
        if (doc == null)
            return false;
        
        // Transforma em nó qualquer tag que tenha 'programa' como nome
        NodeList descNodes = doc.getElementsByTagName("programa"); 
        
        // Para cada nó 'programa' é feita a busca pelo atributo com o mesmo nome da String de entrada do método 
        for(int i = 0; i < descNodes.getLength(); i++)
        {
             Node node = descNodes.item(i);
            //TESTE 
            System.out.println("No " + i + ": " + node.getNodeName());
            
            // Para cada no 'i' o item 'j' assumira o nome de cada um dos atributos existentes
            for (int j = 0; j < descNodes.item(i).getAttributes().getLength(); j++) 
            {
                //TESTE
               System.out.println("Atributo " + i + ": " + node.getAttributes().item(j).toString());
                if (node.getAttributes().item(j).getNodeValue().equals(nomeProgramaPosGraduacao))
                    return true;
            }
        }  
        return false;
    }
}
