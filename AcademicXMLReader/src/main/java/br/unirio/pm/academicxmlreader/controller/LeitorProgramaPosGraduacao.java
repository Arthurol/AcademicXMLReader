package br.unirio.pm.academicxmlreader.controller;

import java.io.IOException;
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
    private final static String URL_PROGRAMAS = "https://s3.amazonaws.com/posgraduacao/programas.xml";
    /**
    * Transforma a InputStream de entrada em um Document
    */
    public boolean procuraProgramaPosGraduacao(String nomeProgramaPosGraduacao) throws IOException, SAXException, ParserConfigurationException
    {
        ConversorXML conversor = new ConversorXML();
        Document doc = conversor.xmlToDocument(URL_PROGRAMAS);
        
        if (doc == null)
        {
            System.out.println("Url de programas de pós-graduação inacessível, favor tentar o acesso em outro momento.");
            return false;
        }
        
        // Transforma todas as tags 'programa' em nós
        NodeList listaProgramas = doc.getElementsByTagName("programa"); 
        
        if (listaProgramas == null || listaProgramas.getLength() == 0)
        {
            System.out.println("Não existem programas de pós-graduação no XML acessado.");
            return false;
        }
        
                // Para cada nó 'programa' é feita a busca pelo atributo com o mesmo nome da String de entrada do método 
                for(int i = 0; i < listaProgramas.getLength(); i++)
                {
                     Node node = listaProgramas.item(i);
//TESTE (VAI SAIR DAQUI DEPOIS)
System.out.println("No " + i + ": " + node.getNodeName());

                    // Para cada no 'i' o item 'j' assumira o nome de cada um dos atributos existentes
                    for (int j = 0; j < listaProgramas.item(i).getAttributes().getLength(); j++) 
                    {
//TESTE (VAI SAIR DAQUI DEPOIS)
System.out.println("Atributo " + i + ": " + node.getAttributes().item(j).toString());
                        if (node.getAttributes().item(j).getNodeValue().equals(nomeProgramaPosGraduacao))
                            return true;
                    }
                }  
    return false;
    }
}
