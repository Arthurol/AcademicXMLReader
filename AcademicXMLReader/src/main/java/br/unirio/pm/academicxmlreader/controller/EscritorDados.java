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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * Classe responsável por escrever os dados dos professores e das linhas de pesquisa no arquivo txt
 */
public class EscritorDados {
    
    int revistasA1 = 0, revistasA2 = 0, revistasB1 = 0, revistasB2 = 0, revistasB3 = 0, revistasB4 = 0, revistasB5 = 0, revistasC = 0, revistasNC = 0;
        int eventosA1 = 0, eventosA2 = 0, eventosB1 = 0, eventosB2 = 0, eventosB3 = 0, eventosB4 = 0, eventosB5 = 0, eventosC = 0, eventosNC = 0;
            int bancasDout = 0, bancasMest = 0, bancasGrad = 0; int orientConcDout = 0, orientConcMest = 0, orientConcGrad = 0;
                int orientAndamDout = 0, orientAndamMest = 0, orientAndamGrad = 0;

    
    private static final String LEGENDA_TXT = "Nome do Professor\t\t\t\t\t" + "RevA1\tRevA2\tRevB1\tRevB2\tRevB3\tRevB4\tRevB5\tRevC\tRevN/C\t" + "EveA1\tEveA2\tEveB1\tEveB2\tEveB3\tEveB4\tEveB5\tEveC\tEveN/C\t" 
                                                + "bancasD\tbancasM\tbancasG\t" + "OriCoD\tOriCoM\tOriCoG\tOriAnD\tOriAnM\tOriAnG";
    
    /**
    *
    * Prenchimento inicial do arquivo txt. Adiciona a legenda/titulo e cria o BufferedWriter que sera passado para outras funções, que continuarão a escrita.
    */
    public void preencheArquivoTxt(String nomeProgramaPosGraduacao, int anoInicial, int anoFinal) throws IOException, SAXException, ParserConfigurationException
    {
        File file = new File(nomeProgramaPosGraduacao + ".txt");

        if (!file.exists())
            file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter buffer = new BufferedWriter(fw);
        buffer.write(LEGENDA_TXT);
        buffer.newLine();
        buffer.newLine();
        escreveLinhasPesquisa(buffer, nomeProgramaPosGraduacao, anoInicial, anoFinal);

        buffer.close();

        System.out.println("Fim.");
    }
    
    /**
    *
    * principal método da classe, chama a filtragem das linhas de pesquisa, faz a busca quantitativa nos curriculos e escreve no txt os resultados encontrados.
    */
    public void escreveLinhasPesquisa(BufferedWriter buffer, String nomeProgramaPosGraduacao, int anoInicial, int anoFinal) throws IOException, SAXException, ParserConfigurationException
    {
        CentralDeProcessamento central = new CentralDeProcessamento();
        List<LinhaDePesquisa> linhasPesquisa = central.filtraCurriculosPorIntervalo(nomeProgramaPosGraduacao, anoInicial, anoFinal);
        List<Integer> somaTotal = new ArrayList<>();
        List<Float> dividePorProfs = new ArrayList<>();
        List<Integer> somaContagens = new ArrayList<>();
        List<Integer> contagens = new ArrayList<>();
        
        for (int i = 0; i < linhasPesquisa.size(); i++)
        {
            List<Professor> professores = linhasPesquisa.get(i).getProfessores();
            
            for (int j = 0; j < professores.size(); j++)
            {
                Professor professor = professores.get(j);
                CurriculoProfessor curriculo = professor.getCurriculo();
                
                revistasA1 = getArtigosA1(curriculo, 0); contagens.add(0, revistasA1);
                revistasA2 = getArtigosA2(curriculo, 0); contagens.add(1, revistasA2);
                revistasB1 = getArtigosB1(curriculo, 0); contagens.add(2, revistasB1);
                revistasB2 = getArtigosB2(curriculo, 0); contagens.add(3, revistasB2);
                revistasB3 = getArtigosB3(curriculo, 0); contagens.add(4, revistasB3);
                revistasB4 = getArtigosB4(curriculo, 0); contagens.add(5, revistasB4);
                revistasB5 = getArtigosB5(curriculo, 0); contagens.add(6, revistasB5);
                revistasC = getArtigosC(curriculo, 0);   contagens.add(7, revistasC);
                revistasNC = getArtigosNC(curriculo, 0); contagens.add(8, revistasNC);
                eventosA1 = getArtigosA1(curriculo, 1); contagens.add(9, eventosA1);
                eventosA2 = getArtigosA2(curriculo, 1); contagens.add(10, eventosA2);
                eventosB1 = getArtigosB1(curriculo, 1); contagens.add(11, eventosB1);
                eventosB2 = getArtigosB2(curriculo, 1); contagens.add(12, eventosB2);
                eventosB3 = getArtigosB3(curriculo, 1); contagens.add(13, eventosB3);
                eventosB4 = getArtigosB4(curriculo, 1); contagens.add(14, eventosB4);
                eventosB5 = getArtigosB5(curriculo, 1); contagens.add(15, eventosB5);
                eventosC = getArtigosC(curriculo, 1);   contagens.add(16, eventosC);
                eventosNC = getArtigosNC(curriculo, 1); contagens.add(17, eventosNC);
                
                bancasDout = curriculo.getBancasDoutorado().size(); contagens.add(18, bancasDout);
                bancasMest = curriculo.getBancasMestrado().size();  contagens.add(19, bancasMest);
                bancasGrad = curriculo.getBancasGraduacao().size(); contagens.add(20, bancasGrad);

                orientConcDout = curriculo.getOrientacoesDoutoradoConcluidas().size(); contagens.add(21, orientConcDout);
                orientConcMest = curriculo.getOrientacoesMestradoConcluidas().size();  contagens.add(22, orientConcMest);
                orientConcGrad = curriculo.getOrientacoesGraduacaoConcluidas().size(); contagens.add(23, orientConcGrad);

                orientAndamDout = curriculo.getOrientacoesDoutoradoAndamento().size(); contagens.add(24, orientAndamDout);
                orientAndamMest = curriculo.getOrientacoesMestradoAndamento().size();  contagens.add(25, orientAndamMest);
                orientAndamGrad = curriculo.getOrientacoesGraduacaoAndamento().size(); contagens.add(26, orientAndamGrad);

                String concat = getStringSeparadaTab (professor.getNome(), contagens);
                buffer.write(concat);
                buffer.newLine();

                if (somaContagens.isEmpty())
                {
                    for (int k = 0; k < contagens.size(); k++)
                    {
                        somaContagens.add(k, contagens.get(k));
                    }
                }
                else
                {
                    for (int k = 0; k < contagens.size(); k++)
                    {
                        int somatorio = somaContagens.get(k);
                        somatorio += contagens.get(k);
                        somaContagens.set(k, somatorio);
                        
                    }
                }
                
                contagens.clear();
            }
  
            for (int l = 0; l < somaContagens.size(); l++)
            {
                int numProfsLinha = linhasPesquisa.get(i).getProfessores().size();
                float divisao = somaContagens.get(l) / (float) numProfsLinha;
                dividePorProfs.add(l, divisao);

            }
            String concatSoma = getStringSeparadaTabFloat(("Total Linha " + linhasPesquisa.get(i).getNome()), dividePorProfs );
            buffer.write(concatSoma);
            buffer.newLine();
            buffer.newLine();
            
            if (somaTotal.isEmpty())
            {
                for (int k = 0; k < somaContagens.size(); k++)
                {
                    somaTotal.add(somaContagens.get(k));
                }
            }
            else
            {
                for (int k = 0; k < somaContagens.size(); k++)
                {
                    int somatorio = somaContagens.get(k);
                    somatorio += somaTotal.get(k);
                    somaTotal.set(k, somatorio);

                }
            }
            
            dividePorProfs.clear();    
            somaContagens.clear();
        }
        int numprof = 0;
        for(int n = 0; n < linhasPesquisa.size(); n++)
        {
            LinhaDePesquisa linha = linhasPesquisa.get(n);
            numprof += linha.getProfessores().size();
            
        }
        
        for (int l = 0; l < somaTotal.size(); l++)
        {
            float divisao = somaTotal.get(l).floatValue() / (float)numprof;
            dividePorProfs.add(divisao);
            
        }
        String concatSoma = getStringSeparadaTabFloat(("Total programa " + nomeProgramaPosGraduacao), dividePorProfs );
        buffer.write(concatSoma);
        buffer.newLine();

    }
    
    /**
    *
    * Concatena diversas variáveis em uma só String, separadas pelo caracter '\t'
    */
    private String getStringSeparadaTab(String nomeProf, List<Integer> listaContagens)
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
        
        String concat = nomeProf + tabInicial;
        
        for (int i = 0; i < listaContagens.size(); i++)
        {
            concat += listaContagens.get(i) + s;
        }
        return concat;
    }
    
    /**
    *
    * Concatena diversos floats em uma só String, separadas pelo caracter <\t>
    */
    private String getStringSeparadaTabFloat(String nomeProf, List<Float> listaDivisoes)
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
        
        String concat = nomeProf + tabInicial;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);   
        
        for (int i = 0; i < listaDivisoes.size(); i++)
        {
            concat += df.format(listaDivisoes.get(i).doubleValue()) + s;
        }
        return concat;
    }
    
    /**
    * Métodos getArtigos, que variam de A1 a NC, e têm como função retornar a contagem de artigos publicados em revistas 
    * e artigos publicados em eventos, para cada classificação (A1-NC).
    * Legenda: tipo == 0 --> artigos publicados em revistas / tipo == 1 --> artigos publicados em eventos
    */
    
    public int getArtigosA1(CurriculoProfessor curriculo, int tipo)
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

    public int getArtigosA2(CurriculoProfessor curriculo, int tipo)
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
    
    private int getArtigosB5(CurriculoProfessor curriculo, int tipo)
    {
       int contador = 0;
        if (tipo == 0)
        {
            List<Artigo> artigosRevista = curriculo.getArtigosRevista();
            for (int i = 0; i < artigosRevista.size(); i++)
            {
                if (artigosRevista.get(i).getClassificacao().equalsIgnoreCase("B5"))
                    contador++;
            }
        }
        else
        {
            List<Artigo> artigosEvento = curriculo.getArtigosEvento();
            for (int i = 0; i < artigosEvento.size(); i++)
            {
                if (artigosEvento.get(i).getClassificacao().equalsIgnoreCase("B5"))
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
    
}
