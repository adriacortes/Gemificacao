import org.adria.Placar;
import org.adria.TipoPonto;
import org.adria.Usuario;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PlacarTest {

    /**
    *  Armazenar que um usuário recebeu uma quantidade de um tipo de ponto.
     * Por exemplo: o usuário "guerra" recebeu "10" pontos do tipo "estrela"
    * */
    @Test
    public void deverRetornarGravadoComSucessoAoSalvarPontosDoUsuarioEmArquivo() throws IOException {
        Mockarmazenamento armazenamento = new Mockarmazenamento();

        TipoPonto pontoMoeda = TipoPonto.MOEDA;
        TipoPonto pontoEstrela = TipoPonto.ESTRELA;
        TipoPonto pontoComentario = TipoPonto.COMENTARIO;

        Usuario usuario = new Usuario("Antonio");
        usuario.gravaPontosNoMap(pontoMoeda,15);
        usuario.gravaPontosNoMap(pontoEstrela,05);
        usuario.gravaPontosNoMap(pontoComentario,05);

        Placar placar = new Placar(armazenamento);

        String msg = placar.salvarPontosDoUsuario(usuario);

        assertEquals("Gravado com sucesso",msg);

    }

    /**
     * Recuperar quantos pontos de um tipo tem um usuário.
     * Por exemplo: retornar quantos pontos do tipo "estrela" tem o usuário "guerra"
     */
    @Test
    public void quantosPontosMoedaTemUsuarioLianaDeveRetornar20() throws IOException {

        Mockarmazenamento armazenamento = new Mockarmazenamento();
        Placar placar = new Placar(armazenamento);
        Usuario usuario ;

        TipoPonto pontoMoeda = TipoPonto.MOEDA;

        usuario  = placar.buscarPontosPorTipo("Liana",pontoMoeda);
        int ponto = usuario.retornaMapComPontos().get(pontoMoeda);
        assertEquals(20,ponto);
    }


    /**
     * Retornar todos os usuários que já receberam algum tipo de ponto.
     */
    @Test
    public void retornarTodosUsuariosComPontuacao() throws IOException {

    }

    /**
     * Retornar todos os tipos de ponto que já foram registrados para algum usuário.
     *
     */
    @Test
    public void deveRetornarLianaESTRELA30MOEDA20COMENTARIO30() throws IOException {
        Mockarmazenamento armazenamento = new Mockarmazenamento();
        Placar placar = new Placar(armazenamento);

        String resposta = placar.buscarPontosPorUsuario("Liana");

        assertEquals("Liana;ESTRELA;30;MOEDA;20;COMENTARIO;30;",resposta);

    }
    
    

}
