import br.unirio.pm.academicxmlreader.controller.ConversorXML;
import br.unirio.pm.academicxmlreader.controller.LeitorCurriculoProfessor;
import br.unirio.pm.academicxmlreader.controller.LeitorProfessoresPrograma;
import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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
   
        ConversorXML conversor = new ConversorXML();    
        
//      LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();

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
            System.out.println("FALHA");
        
        
        
        LeitorCurriculoProfessor leitorCurr = new LeitorCurriculoProfessor();
        List<Artigo> listaArt = leitorCurr.getArtigosPublicados(conversor.zipToDocument("https://s3.amazonaws.com/posgraduacao/PPGI-UNIRIO/1415781875529432.zip"));
        
        for (int i = 0; i < listaArt.size(); i++)
        {
            listaArt.get(i).print();
        }
    }
    
//TIRAR ISSO DEPOIS    
    /*
    public static void testeLeitura() throws SAXException, IOException, ParserConfigurationException
    {
        ConversorXML conversor = new ConversorXML();
     
        Document doc;
        doc = conversor.zipToDocument("https://s3.amazonaws.com/posgraduacao/PPGI-UNIRIO/0821562324429813.zip");
        NodeList nodeList = doc.getElementsByTagName("ARTIGO-PUBLICADO");
        System.out.println("\nNumero de artigos publicados pelo professor: " + nodeList.getLength());
        
    }*/
    
}
