import br.unirio.pm.academicxmlreader.controller.LeitorProfessoresPrograma;
import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import java.io.IOException;
import java.util.List;
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
    /*   
        Scanner scan = new Scanner(System.in);
        String entrada;
        
        System.out.println("Entre com a url do XML a ser printado na tela:");
        entrada = scan.next();
    */
        
//        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
        
      
/*       
        if(leitor.procuraProgramaPosGraduacao("PPGI-UNIRIO"))
            System.out.println("É HORA DO SHOW PORRA");
        else
            System.out.println("FAIÔ");
*/
        LeitorProfessoresPrograma leitorProfs = new LeitorProfessoresPrograma();
        
        List<LinhaDePesquisa> lista = leitorProfs.procuraProfessoresPrograma("PPGI-UNIRIO");
        
        if (leitorProfs.procuraProfessoresPrograma("PPGI-UNIRIO") != null)
        {
            for (int i = 0; i < lista.size(); i++)
            {
                LinhaDePesquisa linha = lista.get(i);
                System.out.println("Linha de pesquisa: " + linha.getNome() + "\n");
                
                for(int j = 0; j < linha.getProfessores().size(); j++)
                {
                    System.out.println("Nome do professor: " + linha.getProfessores().get(j).getNome() + "  código: " + linha.getProfessores().get(j).getCodigoCurriculo());
                }
                
                System.out.println("\n");
            }
        }
        else
            System.out.println("FAIÔ");
    }
    
}
