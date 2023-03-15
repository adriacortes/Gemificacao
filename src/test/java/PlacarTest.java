import org.adria.Placar;
import org.adria.TipoPonto;
import org.adria.Usuario;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PlacarTest {


    public List<Usuario> inicializandoListaComDadosUsuario() throws IOException {
        List<Usuario> listUsuarios = new ArrayList<>();

        Mockarmazenamento armazenamento = new Mockarmazenamento();
        Placar placar = new Placar(armazenamento);

        return  listUsuarios = placar.dadosDosUsuarios();
    }

    /**
     *  Pontua dados para usuario gravando em arquivo
     * Por exemplo: o usuário "guerra" recebeu "10" pontos do tipo "estrela"
     * */
    @Test
    public void deverRetornarGravadoComSucessoAoSalvarPontosDoUsuarioEmArquivo() throws IOException {

        Mockarmazenamento armazenamento = new Mockarmazenamento();
        TipoPonto pontoMoeda = TipoPonto.MOEDA;
        TipoPonto pontoEstrela = TipoPonto.ESTRELA;
        TipoPonto pontoComentario = TipoPonto.COMENTARIO;
        Usuario usuario = new Usuario("Lucas");
        Placar placar = new Placar(armazenamento);

        usuario.gravaPontosNoMap(pontoMoeda,25);
        usuario.gravaPontosNoMap(pontoEstrela,05);
        usuario.gravaPontosNoMap(pontoComentario,05);


        String msg = placar.salvarPontosDoUsuario(usuario);

        assertEquals("Gravado com sucesso",msg);

    }

    /**
     * Retorna todos os pontos de todos os usuarios(Ler do arquivo setando em um List de Usuarios)
     * */
    @Test
    public void pegaRegistroDePontosDoArquivoESetaNoObjetoRetornandoUmaListaDeUsuariosESeusPontos() throws IOException {
        Arquivo arq = new Arquivo();
        List<Usuario> usuarios = arq.retonarUsuariosComPontuacao();
        boolean encontrouDados = true;
        if (usuarios.isEmpty()){
            encontrouDados=false;
        }
        TipoPonto pontoMoeda = TipoPonto.MOEDA;
        TipoPonto pontoEstrela= TipoPonto.ESTRELA;
        TipoPonto pontoComentario = TipoPonto.COMENTARIO;

        for (Usuario us:usuarios) {
            System.out.printf("\n"+us.getNome());
            Map<Enum, Integer> enumIntegerMap = us.retornaMapComPontos();
            for (Enum tipoEnum: enumIntegerMap.keySet()) {
                System.out.printf("\n"+tipoEnum+":"+enumIntegerMap.get(tipoEnum));
            }
        }
        assertEquals(true,encontrouDados);
    }

    /**
     * Recuperar quantos pontos de um TIPO X tem um USUARIO X.
     * Por exemplo: retornar quantos pontos do tipo "estrela" tem o usuário "guerra"
     */
    @Test
    public void deveRetornar20PontosDoTipoMoedaParaUsuarioLiana() throws IOException {

        TipoPonto tipoMoeda = TipoPonto.MOEDA;
        int ponto=0;
        String nomeBuscado = "Liana";

        List<Usuario> usuarios = inicializandoListaComDadosUsuario();
        for (Usuario us: usuarios) {
            if (us.getNome().equalsIgnoreCase(nomeBuscado))
                ponto = us.retornaMapComPontos().get(tipoMoeda);
        }

        assertEquals(20,ponto);
    }


    /**
     * Retornar ranking de um tipo de ponto
     */
    @Test
    public void rankingtPorTipoPontuacao() throws IOException
    {

        Mockarmazenamento armazenamento = new Mockarmazenamento();
        Placar placar = new Placar(armazenamento);
        Usuario usuario ;

        TipoPonto pontoMoeda = TipoPonto.ESTRELA;

        List<Usuario> ranking = placar.rankingPorPontuacao(pontoMoeda);
        for (Usuario us: ranking)
        {
            System.out.printf("\n"+us.getNome());
            Map<Enum, Integer> enumIntegerMap = us.retornaMapComPontos();
            for (Enum tipoEnum: enumIntegerMap.keySet()) {
                System.out.printf("\n" + tipoEnum + ":" + enumIntegerMap.get(tipoEnum));
            }

        }

        String resultado =  (ranking.get(0).getNome()+" com "+ranking.get(0).retornaMapComPontos().get(pontoMoeda)+" pontos do tipo "+pontoMoeda);
        String resultado1 = (ranking.get(1).getNome()+" com "+ranking.get(1).retornaMapComPontos().get(pontoMoeda)+" pontos do tipo "+pontoMoeda);
        String resultado2 = (ranking.get(2).getNome()+" com "+ranking.get(2).retornaMapComPontos().get(pontoMoeda)+" pontos do tipo "+pontoMoeda);

        String registro =  ("Ana com 53 pontos do tipo ESTRELA");
        String registro1 = ("Diogenes com 51 pontos do tipo ESTRELA");
        String registro2 = ("Antonio com 50 pontos do tipo ESTRELA");

        assertEquals(registro,resultado);
        assertEquals(registro1,resultado1);
        assertEquals(registro2,resultado2);

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
