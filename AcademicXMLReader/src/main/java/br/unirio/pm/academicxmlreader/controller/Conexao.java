package br.unirio.pm.academicxmlreader.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * Classe responsável por retornar uma conexão feita a uma URL
 */
public class Conexao 
{
    
    public URLConnection acessarUrl(String urlXml) throws MalformedURLException, IOException
    {
        try
        {
            URL url = new URL(urlXml);
            URLConnection conexao = url.openConnection();
            return conexao;
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
}
