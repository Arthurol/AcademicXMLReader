/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesBuscaXML;

import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Arthur
 */
public class LeitoresTest {
    
    @Test
    public void testProcuraNomePrograma() throws IOException, SAXException, ParserConfigurationException 
    {
        
        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
        
        assertTrue(leitor.procuraProgramaPosGraduacao("PPGI-UNIRIO"));
        assertFalse(leitor.procuraProgramaPosGraduacao("PPGI"));
        
    }
    
    
}
