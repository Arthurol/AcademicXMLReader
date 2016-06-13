import br.unirio.pm.academicxmlreader.controller.ConversorXML;
import br.unirio.pm.academicxmlreader.controller.Filtro;
import br.unirio.pm.academicxmlreader.controller.LeitorCurriculoProfessor;
import br.unirio.pm.academicxmlreader.controller.LeitorProfessoresPrograma;
import br.unirio.pm.academicxmlreader.controller.LeitorProgramaPosGraduacao;
import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Orientacao;
import br.unirio.pm.academicxmlreader.model.ParticipacaoBanca;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * Main Apenas para testar alguns retornos
 */
public class NovoMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException
    { 
   Filtro filtro=new Filtro();
        ConversorXML conversor = new ConversorXML();    
        
//      LeitorProgramaPosGraduacao leitor = new LeitorProgramaPosGraduacao();

        LeitorProfessoresPrograma leitorProfs = new LeitorProfessoresPrograma();
        
        List<LinhaDePesquisa> lista = leitorProfs.procuraProfessoresPrograma("PPGI-UNIRIO");
        
        if (leitorProfs.procuraProfessoresPrograma("PPGI-UNIRIO") != null)
        {
            for (int i = 0; i < lista.size(); i++)
            {
                LinhaDePesquisa linha = lista.get(i);
                System.out.println("Linha de pesquisa: " + linha.getNome() + "\n");
                
                for(int j = 0; j < linha.getProfessores().size(); j++)
                {
                    System.out.println("Nome do professor: " + linha.getProfessores().get(j).getNome() + "  código: " + linha.getProfessores().get(j).getCodigoCurriculo());
                }
                System.out.println("\n");
            }
        }
        else
            System.out.println("FALHA");
        
        Document doc;
        doc = conversor.zipToDocument("https://s3.amazonaws.com/posgraduacao/PPGI-UNIRIO/1920411639358905.zip");
        
        
        /*List<Artigo> listaArt = leitorCurr.getArtigosPublicados(conversor.zipToDocument("https://s3.amazonaws.com/posgraduacao/PPGI-UNIRIO/1415781875529432.zip"));
        
        for (int i = 0; i < listaArt.size(); i++)
        {
            listaArt.get(i).print();
        }*/
        
        
        LeitorCurriculoProfessor leitorCurr = new LeitorCurriculoProfessor();
        
        List<Orientacao> listaOriGradConc = leitorCurr.buscaOrientacoesGraduacaoConcluidas(doc);
            if (listaOriGradConc == null)
                System.out.println("Numero de orientações de graduação concluidas pelo professor: 0");
            else
                System.out.println("Numero de orientações de graduação concluidas pelo professor: " + listaOriGradConc.size());
        
        List<Orientacao> listaOriGradAndam = leitorCurr.buscaOrientacoesGraduacaoAndamento(doc);
             if (listaOriGradAndam == null)
                System.out.println("Numero de orientações de graduação em andamento pelo professor: 0");
             else
                System.out.println("Numero de orientações de graduação em andamento pelo professor: " + listaOriGradAndam.size());
             
        List<Orientacao> listaOriMestConc = leitorCurr.buscaOrientacoesMestradoConcluidas(doc);
             if (listaOriMestConc == null)
                System.out.println("Numero de orientações de mestrado concluidas pelo professor: 0");
             else
                System.out.println("Numero de orientações de mestrado concluidas pelo professor: " + listaOriMestConc.size());
             
        List<Orientacao> listaOriMestAndam = leitorCurr.buscaOrientacoesMestradoAndamento(doc);
            if (listaOriMestAndam == null)
               System.out.println("Numero de orientações de mestrado em andamento pelo professor: 0");
            else
               System.out.println("Numero de orientações de mestrado em andamento pelo professor: " + listaOriMestAndam.size());

        List<Orientacao> listaOriDoutConc = leitorCurr.buscaOrientacoesDoutoradoConcluidas(doc);
            if (listaOriDoutConc == null)
               System.out.println("Numero de orientações de doutorado concluidas pelo professor: 0");
            else
               System.out.println("Numero de orientações de doutorado concluidas pelo professor: " + listaOriDoutConc.size());

        List<Orientacao> listaOriDoutAndam = leitorCurr.buscaOrientacoesDoutoradoAndamento(doc);
            if (listaOriDoutAndam == null)
               System.out.println("Numero de orientações de doutorado em andamento pelo professor: 0");
            else
               System.out.println("Numero de orientações de doutorado em andamento pelo professor: " + listaOriDoutAndam.size());
            
        List<ParticipacaoBanca> listaBancaGrad = leitorCurr.buscaParticipacoesBancaGraduacao(doc);
            if (listaBancaGrad == null)
               System.out.println("\nNumero de participações do professor em bancas de graduacao: 0");
            else
               System.out.println("\nNumero de participações do professor em bancas de graduacao: " + listaBancaGrad.size());

        List<ParticipacaoBanca> listaBancaMestr = leitorCurr.buscaParticipacoesBancaMestrado(doc);
            if (listaBancaMestr == null)
               System.out.println("Numero de participações do professor em bancas de mestrado: 0");
            else
               System.out.println("Numero de participações do professor em bancas de mestrado: " + listaBancaMestr.size());
            
        List<ParticipacaoBanca> listaBancaDout = leitorCurr.buscaParticipacoesBancaDoutorado(doc);
            if (listaBancaDout == null)
               System.out.println("Numero de participações do professor em bancas de doutorado: 0");
            else
               System.out.println("Numero de participações do professor em bancas de doutorado: " + listaBancaDout.size());
            
            CurriculoProfessor curr = leitorCurr.montaCurriculoProfessor("PPGI-UNIRIO", "8020803376969953");
            if (curr.getBancasGraduacao().size() > 0 || curr.getBancasMestrado().size() > 0 || curr.getBancasDoutorado().size() > 0)
                System.out.println("\nTeste de funcionamento do montaCurriculoProfessor:\nBeto ja participou de " + (curr.getBancasGraduacao().size() + 
                                        curr.getBancasMestrado().size() + curr.getBancasDoutorado().size()) + " bancas no total (graduação + mestrado + doutorado)");
        System.out.println("\n\n\n\n"); 
        filtro.filtroTeste();
    }
    
//TIRAR ISSO DEPOIS    
    /*
    public static void testeLeitura() throws SAXException, IOException, ParserConfigurationException
    {
        ConversorXML conversor = new ConversorXML();
     
        Document doc;
        doc = conversor.zipToDocument("https://s3.amazonaws.com/posgraduacao/PPGI-UNIRIO/0821562324429813.zip");
        NodeList nodeList = doc.getElementsByTagName("ARTIGO-PUBLICADO");
        System.out.println("\nNumero de artigos publicados pelo professor: " + nodeList.getLength());
        
    }*/
    
}