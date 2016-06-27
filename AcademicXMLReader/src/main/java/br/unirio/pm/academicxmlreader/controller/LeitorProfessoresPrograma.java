package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Professor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * Classe responsavel por ler arquivo XML e obter a lista das linhas de pesquisa de um determinado programa de pos-graduacao, onde cada linha de pesquisa contem um conjunto de professores
 */
public class LeitorProfessoresPrograma 
{

    /**
    * Transforma todo o XML em estrutura de árvore e retorna a lista com todas as linhas de pesquisa do programa, cada uma com seus professores
    */
    public List<LinhaDePesquisa> procuraProfessoresPrograma(String nomeProgramaPosGraduacao) throws IOException, SAXException, ParserConfigurationException
    {
        LeitorProgramaPosGraduacao leitorPrograma = new LeitorProgramaPosGraduacao();
                
        //lista que armazenará todas as linhas de pesquisa, incluindo seus professores, do XML
        List<LinhaDePesquisa> listaLinhasPesquisa = new ArrayList<>(); 
   
        if (leitorPrograma.procuraProgramaPosGraduacao(nomeProgramaPosGraduacao))
        {
            String urlProfessoresPrograma = "https://s3.amazonaws.com/posgraduacao/" + nomeProgramaPosGraduacao + "/contents.xml";
            ConversorXML conversor = new ConversorXML();
            Document doc = conversor.xmlToDocument(urlProfessoresPrograma);

            if (doc == null)
            {
                System.out.println("Erro: O Programa de pós-graduação informado não existe ou está indisponível no momento.");
                return null;
            }

            // Transforma toda as tags 'linha' em nós
            NodeList nosLinha = doc.getElementsByTagName("linha");  

            if (nosLinha.getLength() == 0)
            {
                System.out.println("Erro: Não existem linhas de pesquisa no XML acessado.");
                return null;
            }

            // Inicia a busca por professores em cada linha de pesquisa
            for (int i = 0; i < nosLinha.getLength(); i++)
            {
                Node nodeLinhaPesquisa = nosLinha.item(i);
                String atributoLinha = nodeLinhaPesquisa.getAttributes().item(0).getNodeName();
                if (atributoLinha.equals("nome"))
                {                          
                    // cria a linha de pesquisa, preenchendo nome e programa de pós-graduação
                    LinhaDePesquisa linhaDePesquisa = new LinhaDePesquisa();
                    linhaDePesquisa.setNome(nodeLinhaPesquisa.getAttributes().item(0).getNodeValue());
                    linhaDePesquisa.setProgramaPosGraduacao(nomeProgramaPosGraduacao);

                    NodeList nosProfessor = nodeLinhaPesquisa.getChildNodes();

                    if (nosProfessor.getLength() > 0)
                    {
                        for (int j = 0; j < nosProfessor.getLength(); j++)
                        {
                            Node nodeProf = nosProfessor.item(j);

                            if (nodeProf.getNodeName().equals("professor"))
                            {
                                //Constroi um objeto professor a cada iteração e o coloca na lista de professores da Linha de pesquisa criada anteriormente
                                Professor professor = new Professor();
                                professor.setCodigoCurriculo(nodeProf.getAttributes().getNamedItem("codigo").getNodeValue());
                                professor.setNome(nodeProf.getAttributes().getNamedItem("nome").getNodeValue());

                                linhaDePesquisa.getProfessores().add(professor);
                            }  
                        }
                        // Após adicionar todos os professores à linha, a mesma é adicionada à lista de Linhas de pesquisa
                        listaLinhasPesquisa.add(linhaDePesquisa);   
                    }
                }
            }     
        }
        else 
        {
             System.out.println("O programa não foi encontrado na lista dos programas de pós-graduação");
             return null;
        }

        return listaLinhasPesquisa;
    }
}
