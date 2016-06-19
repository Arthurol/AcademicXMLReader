package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import lombok.Getter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public @Getter
class Filtro {

    String need;
    ConversorXML conversor = new ConversorXML();
    int artRevistas, artEventos, bancasDr, bancasMs, bancasGrad, orientDrConcluidas,
            orientMsConcluidas, orientGradConcluidas, orientDrAndamento, orientMsAndamento, orientGradAndamento;
    Document doc;
    int x;
    String nomeProf, nomeLinha, codProf;
    LeitorCurriculoProfessor leitorCurr = new LeitorCurriculoProfessor();
    LeitorProfessoresPrograma linhaPesquisa = new LeitorProfessoresPrograma();
    CurriculoProfessor curr = new CurriculoProfessor();

    public void filtroTeste() throws IOException, SAXException, ParserConfigurationException {

        for (int i = 0; i < linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").size(); i++) {
            nomeLinha = (linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getNome());

            System.out.println(nomeLinha);
            // System.out.println("entrou1");

            for (int j = 0; j < linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().size(); j++) {
                nomeProf = (linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().get(j).getNome());
                codProf = (linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().get(j).getCodigoCurriculo());
                doc = conversor.zipToDocument("https://s3.amazonaws.com/posgraduacao/PPGI-UNIRIO/" + codProf + ".zip");

                curr = leitorCurr.montaCurriculoProfessor("PPGI-UNIRIO", codProf);

//    System.out.println(nomeProf);
                // System.out.println("entrou2");
                for (int k = 0; k < linhaPesquisa.procuraProfessoresPrograma("PPGI-UNIRIO").get(i).getProfessores().size(); k++) {

                    artRevistas = curr.getArtigosRevista().size();
                    artEventos = curr.getArtigosEvento().size();
                    bancasDr = curr.getBancasDoutorado().size();
                    bancasMs = curr.getBancasMestrado().size();
                    bancasGrad = curr.getBancasGraduacao().size();
                    orientDrConcluidas = curr.getOrientacoesDoutoradoConcluidas().size();
                    orientMsConcluidas = curr.getOrientacoesMestradoConcluidas().size();
                    orientGradConcluidas = curr.getOrientacoesGraduacaoConcluidas().size();
                    orientDrAndamento = curr.getOrientacoesDoutoradoAndamento().size();
                    orientMsAndamento = curr.getOrientacoesMestradoAndamento().size();
                    orientGradAndamento = curr.getOrientacoesGraduacaoAndamento().size();
                }
                    need = nomeProf + "\t" + artRevistas + "\t" + artEventos;/* + "\t" + bancasDr + "\t" + bancasMs + "\t" + bancasGrad;
                /*+"\t" +   orientDrConcluidas;/*+"\t" +orientMsConcluidas +"\t" + orientGradConcluidas +"\t"+orientDrAndamento+"\t"+orientMsAndamento+"\t"+orientGradAndamento;
                     */
                    System.out.println(need);

                

            }

        }

    }
}
//public void escrever() throws IOException{
//
//FileWriter writer = new FileWriter("MyFile.txt", true);
//            BufferedWriter escritor = new BufferedWriter(writer);
//
//
//escritor.write(this.getNomeProf());
//}
//    

//lista linhas de pesquisa
//	linha
//		lista professores
//			professor
//				curriculo
//					listas artigos
//					listas orientaçoes
//					listas participacoesBanca
