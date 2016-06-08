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
    
    public ConexaoTest() {
    }

    @Test
    public void testGetConexao() throws Exception 
    {
        Conexao conn = new Conexao();
        
        assertNotNull(conn.getConexao("http://junit.sourceforge.net/javadoc/org/junit/Assert.html"));
        assertNotNull(conn.getConexao("http://www.unirio.br/"));
        assertNull(conn.getConexao("www2.uniriotec.br/ppgi"));  // teste de url sem protocolo indicado (deve retornar nulo)
        assertNull(conn.getConexao("qualquer coisa"));
        assertNotNull(conn.getConexao("https://www.google.com/docs/about/")); // teste de conexão com protocolo https
       
    }
}