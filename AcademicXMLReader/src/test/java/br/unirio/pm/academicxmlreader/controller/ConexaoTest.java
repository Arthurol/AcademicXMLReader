package br.unirio.pm.academicxmlreader.controller;
import org.junit.Test;
import static org.junit.Assert.*;

            /**
             * SESSAO DE DUVIDAS:
             *  Porque da erro se tentarmos importar a classe Conexao ao inves de colocar o teste no mesmo pacote da classe?
             *  Ao criar o teste o pacote é adicionado automaticamente
             * 
             */


/**
 *
 * Testes de conexao a urls informadas
 */
public class ConexaoTest {
    
    @Test
    public void testRetornoConexao() throws Exception 
    {
        Conexao conn = new Conexao();
        
        assertNotNull(conn.acessarUrl("http://junit.sourceforge.net/javadoc/org/junit/Assert.html"));
        assertNotNull(conn.acessarUrl("http://www.unirio.br/"));
        assertNull(conn.acessarUrl("www2.uniriotec.br/ppgi"));  // teste de url sem protocolo indicado (deve retornar nulo)
        assertNull(conn.acessarUrl("qualquer coisa"));
        assertNotNull(conn.acessarUrl("https://www.google.com/docs/about/")); // teste de conexão com protocolo https
    }
}