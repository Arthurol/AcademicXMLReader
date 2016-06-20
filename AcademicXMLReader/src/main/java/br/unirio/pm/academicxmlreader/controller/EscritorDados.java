/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unirio.pm.academicxmlreader.controller;

import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.CurriculoProfessor;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Professor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * Classe responsável por escrever os dados dos professores e das linhas de pesquisa no arquivo txt
 */
public class EscritorDados {
    
    int somaEventosA1 = 0, somaRevistasA1 = 0, somaEventosA2 = 0, somaRevistasA2 = 0, somaEventosB1 = 0, somaRevistasB1 = 0, 
            somaEventosB2 = 0, somaRevistasB2 = 0, somaEventosB3 = 0, somaRevistasB3 = 0, somaEventosB4 = 0, somaRevistasB4 = 0, 
                somaEventosC = 0, somaRevistasC = 0, somaEventosNC = 0, somaRevistasNC = 0, somaBancasDout = 0, somaBancasMest = 0, somaBancasGrad = 0, 
                    somaOrientConcDout = 0, somaOrientConcMest = 0,  somaOrientConcGrad = 0,  somaOrientAndamDout = 0, somaOrientAndamMest = 0, somaOrientAndamGrad = 0;

    int revistasA1 = 0, revistasA2 = 0, revistasB1 = 0, revistasB2 = 0, revistasB3 = 0, revistasB4 = 0, revistasC = 0, revistasNC = 0;
        int eventosA1 = 0, eventosA2 = 0, eventosB1 = 0, eventosB2 = 0, eventosB3 = 0, eventosB4 = 0, eventosC = 0, eventosNC = 0;
            int bancasDout = 0, bancasMest = 0, bancasGrad = 0; int orientConcDout = 0, orientConcMest = 0, orientConcGrad = 0;
                int orientAndamDout = 0, orientAndamMest = 0, orientAndamGrad = 0;

    
    private static final String LEGENDA_TXT = "Nome do Professor\t\t\t\t\t" + "RevA1\tRevA2\tRevB1\tRevB2\tRevB3\tRevB4\tRevC\tRevN/C\t" + "EveA1\tEveA2\tEveB1\tEveB2\tEveB3\tEveB4\tEveC\tEveN/C\t" 
                                                + "bancasD\tbancasM\tbancasG\t" + "OriCoD\tOriCoM\tOriCoG\tOriAnD\tOriAnM\tOriAnG";
    
    /**
     * Método que cria um arquivo .txt e imprime o nome de cada coluna
     */
    public void preencheArquivoTxt(String nomeProgramaPosGraduacao, int anoInicial, int anoFinal) throws IOException, SAXException, ParserConfigurationException
    {
        File file = new File("Relatório " + nomeProgramaPosGraduacao + ".txt");

        if (!file.exists())
            file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter buffer = new BufferedWriter(fw);
        buffer.write(LEGENDA_TXT);
        buffer.newLine();
        buffer.newLine();
        escreveLinhasPesquisa(buffer, nomeProgramaPosGraduacao, anoInicial, anoFinal);

        buffer.close();

        System.out.println("Done");

    }
    
    /**
     * Método que escreve no .txt todos os dados referentes as Linhas de Pesquisa e Professores, inclusive seu somatório
     */
    public void escreveLinhasPesquisa(BufferedWriter buffer, String nomeProgramaPosGraduacao, int anoInicial, int anoFinal) throws IOException, SAXException, ParserConfigurationException
    {
        CentralDeProcessamento central = new CentralDeProcessamento();
        List<LinhaDePesquisa> linhasPesquisa = central.filtraCurriculosPorIntervalo(nomeProgramaPosGraduacao, anoInicial, anoFinal);
        
        
        
        for (int i = 0; i < linhasPesquisa.size(); i++)
        {
            zeraSomasLinhaPesquisa();
                
            List<Professor> professores = linhasPesquisa.get(i).getProfessores();
            
            for (int j = 0; j < professores.size(); j++)
            {
                Professor professor = professores.get(j);
                CurriculoProfessor curriculo = professor.getCurriculo();
                
                eventosA1 = getArtigosA1(curriculo, 1);
                revistasA1 = getArtigosA1(curriculo, 0);
                eventosA2 = getArtigosA2(curriculo, 1);
                revistasA2 = getArtigosA2(curriculo, 0); 
                eventosB1 = getArtigosB1(curriculo, 1); 
                revistasB1 = getArtigosB1(curriculo, 0);
                eventosB2 = getArtigosB2(curriculo, 1);
                revistasB2 = getArtigosB2(curriculo, 0); 
                eventosB3 = getArtigosB3(curriculo, 1);
                revistasB3 = getArtigosB3(curriculo, 0);
                eventosB4 = getArtigosB4(curriculo, 1);
                revistasB4 = getArtigosB4(curriculo, 0);
                eventosC = getArtigosC(curriculo, 1);
                revistasC = getArtigosC(curriculo, 0);
                eventosNC = getArtigosNC(curriculo, 1); 
                revistasNC = getArtigosNC(curriculo, 0);
                
                bancasDout = curriculo.getBancasDoutorado().size();
                bancasMest = curriculo.getBancasMestrado().size();
                bancasGrad = curriculo.getBancasMestrado().size();

                orientConcDout = curriculo.getOrientacoesDoutoradoConcluidas().size();
                orientConcMest = curriculo.getOrientacoesMestradoConcluidas().size();
                orientConcGrad = curriculo.getOrientacoesGraduacaoConcluidas().size();

                orientAndamDout = curriculo.getOrientacoesDoutoradoAndamento().size();
                orientAndamMest = curriculo.getOrientacoesMestradoAndamento().size();
                orientAndamGrad = curriculo.getOrientacoesGraduacaoAndamento().size();

                String concat = getStringSeparadaTab( professor.getNome(), revistasA1, revistasA2, revistasB1, revistasB2, revistasB3, revistasB4, revistasC, revistasNC,
                                                        eventosA1, eventosA2, eventosB1, eventosB2, eventosB3, eventosB4, eventosC, eventosNC, bancasDout, bancasMest, bancasGrad,
                                                            orientConcDout, orientConcMest, orientConcGrad, orientAndamDout, orientAndamMest, orientAndamGrad );
                buffer.write(concat);
                buffer.newLine();
                
                somaEventosA1 += eventosA1;
                somaRevistasA1 += revistasA1;
                somaEventosA2 += eventosA2;
                somaRevistasA2 += revistasA2; 
                somaEventosB1 += eventosB1;
                somaRevistasB1 += revistasB1;
                somaEventosB2 += eventosB2;
                somaRevistasB2 += revistasB2;
                somaEventosB3 += eventosB3;
                somaRevistasB3 += revistasB3;
                somaEventosB4 += eventosB4;
                somaRevistasB4 += revistasB4;
                somaEventosC += eventosC;
                somaRevistasC += revistasC;
                somaEventosNC += eventosNC;
                somaRevistasNC += revistasNC;
                
                somaBancasDout += bancasDout;
                somaBancasMest += bancasMest;
                somaBancasGrad += bancasGrad;

                somaOrientConcDout += orientConcDout;
                somaOrientConcMest += orientConcMest;
                somaOrientConcGrad += orientConcGrad;

                somaOrientAndamDout += orientAndamDout;
                somaOrientAndamMest += orientAndamMest;
                somaOrientAndamGrad += orientAndamGrad;
            }

            String concatSoma = getStringSeparadaTab(("Total Linha " + linhasPesquisa.get(i).getNome()), somaRevistasA1, somaRevistasA2, somaRevistasB1, somaRevistasB2, 
                                                        somaRevistasB3, somaRevistasB4, somaRevistasC, somaRevistasNC, somaEventosA1, somaEventosA2, somaEventosB1, somaEventosB2, 
                                                            somaEventosB3, somaEventosB4, somaEventosC, somaEventosNC, somaBancasDout, somaBancasMest, somaBancasGrad,somaOrientConcDout, 
                                                                somaOrientConcMest, somaOrientConcGrad, somaOrientAndamDout, somaOrientAndamMest, somaOrientAndamGrad);
                buffer.write(concatSoma);
                buffer.newLine();
                buffer.newLine();
        }
    }
    
    /**
    *
    * Concatena as variáveis que guardam os dados de cada Professor em uma só String, separadas pelo caracter <\t>
    */
    private String getStringSeparadaTab(String nomeProf, int revA1, int revA2, int revB1, int revB2, int revB3, int revB4, int revC, int revNC, int eveA1, 
                                            int eveA2, int eveB1, int eveB2, int eveB3, int eveB4, int eveC, int eveNC, int bancasDout, int bancasMest, int bancasGrad, 
                                                int OrientConcDout, int OrientConcMest, int OrientConcGrad, int OrientAndamDout,int OrientAndamMest, int OrientAndamGrad)
    {
        String s = "\t"; //separador
        String tabInicial = "\t\t\t\t\t";
        
        if (nomeProf.length() >= 48)
            tabInicial = "\t";
        else if (nomeProf.length() >= 40)
            tabInicial = "\t\t";
        else if(nomeProf.length() >= 32)
            tabInicial = "\t\t\t";
        else if (nomeProf.length() >= 24 && nomeProf.length() <32)
            tabInicial = "\t\t\t\t";
        
        String concat = nomeProf + tabInicial + revA1 + s + revA2 + s + revB1 + s + revB2 + s + revB3 + s + revB4 + s + revC + s + revNC + s 
                + eveA1 + s + eveA2 + s + eveB1 + s + eveB2 + s + eveB3 + s + eveB4 + s + eveC + s + eveNC + s + bancasDout + s + bancasMest 
                    + s + bancasGrad + s + OrientConcDout + s + OrientConcMest + s + OrientConcGrad + s + OrientAndamDout + s + OrientAndamMest + s + OrientAndamGrad;
        
        return concat;
    }
    
    /**
    * Métodos getArtigos, que variam de A1 a NC, e têm como função retornar a contagem de artigos publicados em revistas 
    * e artigos publicados em eventos, para cada classificação (A1-NC).
    * Legenda: tipo == 0 --> artigos publicados em revistas / tipo == 1 --> artigos publicados em eventos
    */
    
    private int getArtigosA1(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("A1"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("A1"))
                    contador++;
            }
        }
        return contador;
    }

    
    private int getArtigosA2(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("A2"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("A2"))
                    contador++;
            }
        }
        return contador;
    }
    
    private int getArtigosB1(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("B1"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("B1"))
                    contador++;
            }
        }
        return contador;
    }
    
    private int getArtigosB2(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("B2"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("B2"))
                    contador++;
            }
        }
        return contador;
    }
    
    private int getArtigosB3(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("B3"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("B3"))
                    contador++;
            }
        }
        return contador;
    }
    
    private int getArtigosB4(CurriculoProfessor curriculo, int tipo)
    {
       int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("B4"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("B4"))
                    contador++;
            }
        }
        return contador;
    }
    
    private int getArtigosC(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("C"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("C"))
                    contador++;
            }
        }
        return contador;
    }
    
    private int getArtigosNC(CurriculoProfessor curriculo, int tipo)
    {
        int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("NC"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("NC"))
                    contador++;
            }
        }
        return contador;
    }
    private void zeraSomasLinhaPesquisa()
    {
        somaEventosA1 = 0;
        somaRevistasA1 = 0;
        somaEventosA2 = 0;
        somaRevistasA2 = 0;
        somaEventosB1 = 0;
        somaRevistasB1 = 0;
        somaEventosB2 = 0;
        somaRevistasB2 = 0;
        somaEventosB3 = 0;
        somaRevistasB3 = 0;
        somaEventosB4 = 0;
        somaRevistasB4 = 0;
        somaEventosC = 0;
        somaRevistasC = 0;
        somaEventosNC = 0;
        somaRevistasNC = 0;
        
        somaBancasDout = 0;
        somaBancasMest = 0;
        somaBancasGrad = 0;

        somaOrientConcDout = 0;
        somaOrientConcMest = 0;
        somaOrientConcGrad = 0;

        somaOrientAndamDout = 0;
        somaOrientAndamMest = 0;
        somaOrientAndamGrad = 0;
        
    }
}
