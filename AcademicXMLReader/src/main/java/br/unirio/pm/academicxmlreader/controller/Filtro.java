package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Orientacao;
import br.unirio.pm.academicxmlreader.model.Professor;
import br.unirio.pm.academicxmlreader.model.ParticipacaoBanca;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Filtro {

//    List<LinhaDePesquisa> linhaPesquisa;
//    List<Professor> professor;
//    List<Artigo> revista;
//    List<Artigo> evento;
//    List<ParticipacaoBanca> mestrado;
//    List<ParticipacaoBanca> doutorado;
//    List<Orientacao> andamento;
//    List<Orientacao> concluido;

    public void filtroTeste() throws IOException, SAXException, ParserConfigurationException {
        LeitorProfessoresPrograma linhaPesquisa = new LeitorProfessoresPrograma();
//while (linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").isEmpty()==false){
        for (int i = 0; i < linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").size(); i++) {
            System.out.println(linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getNome());
            System.out.println("entrou1");
        
            for (int j = 0; j < linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().size(); j++) {
                System.out.println(linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().get(j).getNome());
                System.out.println("entrou2");
                for (int k = 0; k < linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().size(); k++) {
                    System.out.println(linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().get(j).getCurriculo().getArtigosEvento());
                    System.out.println(linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().get(j).getCurriculo().getArtigosRevista());
                    System.out.println("entrou3");
                }
            }
        }
}
    }


//lista linhas de pesquisa
//	linha
//		lista professores
//			professor
//				curriculo
//					listas artigos
//					listas orientaçoes
//					listas participacoesBanca
