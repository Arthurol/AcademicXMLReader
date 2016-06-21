import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * Testes das classes de leitura de xml;
 */
public class LeitoresTest {
    
    @Test
    public void testProcuraPrograma() throws IOException, SAXException, ParserConfigurationException 
    {
        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
        assertTrue(leitor.procuraProgramaPosGraduacao("PPGI-UNIRIO"));
        assertFalse(leitor.procuraProgramaPosGraduacao("PPGI"));
        
    }
    
    @Test
    public void testLeituraCurriculo() throws IOException, SAXException, ParserConfigurationException 
    {
        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
        assertTrue(leitor.procuraProgramaPosGraduacao("PPGI-UNIRIO"));
        assertFalse(leitor.procuraProgramaPosGraduacao("PPGI"));
        
    }
    
    @Test
    public void testLeituraQualis() throws IOException, SAXException, ParserConfigurationException 
    {
        
        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
        
        assertTrue(leitor.procuraProgramaPosGraduacao("PPGI-UNIRIO"));
        assertFalse(leitor.procuraProgramaPosGraduacao("PPGI"));
        
    }
    
}
