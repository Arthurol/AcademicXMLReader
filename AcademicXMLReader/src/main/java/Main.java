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
        
        EscritorDados escritor = new EscritorDados();
        escritor.preencheArquivoTxt("PPGI-UNIRIO", 2010, 2016);
                
     }   
}