package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.controller.EscritorDados;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * Classe utilizada para a interpretação de comandos, ao transformar o projeto em jar
 */
public class Main {

    
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException
    { 
        String nomeProgramaPosGraduacao = "PPGI-UNIRIO";
        int anoInicio = 1990;
        int anoFim = 2016;
       
        //Checa se parâmetros foram passados na execução e preenche respectivas variáveis de execução
        if (args.length == 3) {
 
            nomeProgramaPosGraduacao = args[0];
            anoInicio = Integer.parseInt(args[1]);
            anoFim = Integer.parseInt(args[2]);
        }
       // else
        //    System.exit(1);
            
        EscritorDados escritor = new EscritorDados();
        escritor.preencheArquivoTxt(nomeProgramaPosGraduacao, anoInicio, anoFim);
        }          
}   