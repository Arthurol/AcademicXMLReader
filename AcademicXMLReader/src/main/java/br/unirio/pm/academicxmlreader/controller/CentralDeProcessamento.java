package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Professor;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * Classe que centraliza a chamada dos métodos das classes de leitura de XML 
 */
public class CentralDeProcessamento {
    
    public List<LinhaDePesquisa> preenchimentoLinhas(String nomeProgramaPosGraduacao) throws IOException, SAXException, ParserConfigurationException
    {
        LeitorProfessoresPrograma leitorProfessores = new LeitorProfessoresPrograma();
        LeitorCurriculoProfessor leitorCurriculo = new LeitorCurriculoProfessor();
        LeitorClassificacaoQualis leitorQualis = new LeitorClassificacaoQualis();
        
        
        List<LinhaDePesquisa> listaLinhas = leitorProfessores.procuraProfessoresPrograma(nomeProgramaPosGraduacao);
        
        for(int i = 0; i < listaLinhas.size(); i++)
        {
            List<Professor> professores = listaLinhas.get(i).getProfessores();
            
            for (int j = 0; j < professores.size(); j++)
            {
                Professor professor = professores.get(j);

                CurriculoProfessor curriculo = leitorCurriculo.montaCurriculoProfessor(nomeProgramaPosGraduacao, professor.getCodigoCurriculo());
                
                curriculo.setArtigosEvento(leitorQualis.classificadorEventos(curriculo.getArtigosEvento()));
                curriculo.setArtigosRevista(leitorQualis.classificadorRevistas(curriculo.getArtigosRevista()));
                
                professor.setCurriculo(curriculo);
            } 
        }
        return listaLinhas;
    }
    
}
