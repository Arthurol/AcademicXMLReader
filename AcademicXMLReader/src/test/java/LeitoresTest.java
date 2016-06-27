import br.unirio.pm.academicxmlreader.controller.CentralDeProcessamento;
import br.unirio.pm.academicxmlreader.controller.EscritorDados;
import br.unirio.pm.academicxmlreader.controller.LeitorClassificacaoQualis;
import br.unirio.pm.academicxmlreader.controller.LeitorCurriculoProfessor;
import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Professor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        CurriculoProfessor currPimentel = getCurriculo("1920411639358905");
        EscritorDados escritor = new EscritorDados();
        
        
        assertEquals(1, escritor.getArtigosA1(currPimentel, 0));
        assertEquals(1, escritor.getArtigosA2(currPimentel, 1));
        assertEquals(2, currPimentel.getOrientacoesMestradoAndamento().size());
        
        LeitorClassificacaoQualis qualis = new LeitorClassificacaoQualis();
        assertEquals(3510, qualis.buscaEntradasEvento().size() + qualis.buscaEntradasRevista().size());
    }
    
    public CurriculoProfessor getCurriculo(String codigoProfessor) throws IOException, SAXException, ParserConfigurationException
    {
        CentralDeProcessamento central = new CentralDeProcessamento();
        
        List<LinhaDePesquisa> listaLinha = central.preenchimentoLinhas("PPGI-UNIRIO");
        
        for (int i = 0; i < listaLinha.size(); i++)
        {
            LinhaDePesquisa linha = listaLinha.get(i);
            for (int j = 0; j < linha.getProfessores().size(); j++)
            {
                Professor prof = linha.getProfessores().get(j);
                if (prof.getCodigoCurriculo().equals(codigoProfessor))
                    return prof.getCurriculo();
            }
        }
        return null;
    }
}
