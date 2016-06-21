import br.unirio.pm.academicxmlreader.controller.ConversorXML;
import org.junit.Test;
import static org.junit.Assert.*;


public class ConversorXMLTest {
    
    
    @Test
    public void testRetornoConexao() throws Exception 
    {
        ConversorXML conversor = new ConversorXML();
        
        assertNotNull(conversor.acessarUrl("http://junit.sourceforge.net/javadoc/org/junit/Assert.html"));
        assertNotNull(conversor.acessarUrl("http://www.unirio.br/"));
        assertNull(conversor.acessarUrl("www2.uniriotec.br/ppgi"));  // teste de url sem protocolo indicado (deve retornar nulo)
        assertNull(conversor.acessarUrl("qualquer coisa"));
        assertNotNull(conversor.acessarUrl("https://www.google.com/docs/about/")); // teste de conexão com protocolo https
    }
}
