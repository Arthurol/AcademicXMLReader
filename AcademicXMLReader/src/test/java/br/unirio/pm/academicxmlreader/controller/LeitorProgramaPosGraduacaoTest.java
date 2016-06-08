/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unirio.pm.academicxmlreader.controller;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Arthur
 */
public class LeitorProgramaPosGraduacaoTest {  

    @Test
    public void testProcuraNomePrograma() throws IOException, SAXException, ParserConfigurationException {
        
        LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();
        
        assertTrue(leitor.procuraProgramaPosGraduacao("https://s3.amazonaws.com/posgraduacao/programas.xml", "PPGI-UNIRIO"));
        assertFalse(leitor.procuraProgramaPosGraduacao("https://s3.amazonaws.com/posgraduacao/programas.xml", "PPGI"));
        
    }    
}
