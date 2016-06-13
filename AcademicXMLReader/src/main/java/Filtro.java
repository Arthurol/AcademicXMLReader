
import br.unirio.pm.academicxmlreader.model.Artigo;
import br.unirio.pm.academicxmlreader.model.LinhaDePesquisa;
import br.unirio.pm.academicxmlreader.model.Orientacao;
import br.unirio.pm.academicxmlreader.model.Professor;
import br.unirio.pm.academicxmlreader.model.ParticipacoesBanca;
import java.util.List;

public class Filtro {
    List<LinhaDePesquisa> linhaPesquisa;
    List<Professor> professor;
    List<Artigo> revista;
    List<Artigo> evento;
    List<ParticipacoesBanca> mestrado;
    List<ParticipacoesBanca> doutorado;
    List<Orientacao> orientacao;
    
    public float filtroTeste()
    {
        for (int i = 0; i< linhaPesquisa.size(); i++)
        {
            linhaPesquisa.get(i).getNome();
            for (int j = 0; j < professor.size(); j++)
            {
                professor.get(i).getNome();
                for (int k = 0; k < )
            }
        }
        
        return 0;
    }
    
    
    
}

lista linhas de pesquisa
	linha
		lista professores
			professor
				curriculo
					listas artigos
					listas orientaçoes
					listas participacoesBanca
							