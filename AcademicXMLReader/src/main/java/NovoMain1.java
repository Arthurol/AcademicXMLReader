
import br.unirio.pm.academicxmlreader.controller.ConversorXML;
import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import br.unirio.pm.academicxmlreader.controller.ReaderFactory;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Main Apenas para testar alguns retornos
 */
public class NovoMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException
    { 
   
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        String entrada;
        
        System.out.println("Entre com a url do XML a ser printado na tela:");
        entrada = scan.next();
        
        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
      
        if(ReaderFactory.getLeitorProgramaPosGraduacao().procuraProgramaPosGraduacao(entrada, "PPGI-UNIRIO"))
            System.out.println("É HORA DO SHOW PORRA");
        else
            System.out.println("FAIÔ");
     
    }
    
}
