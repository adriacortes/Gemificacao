import org.adria.Armazenamento;
import org.adria.TipoPonto;
import org.adria.Usuario;

import java.io.IOException;

public class Mockarmazenamento implements Armazenamento {


    Arquivo arquivo;

    public Mockarmazenamento() throws IOException {
        arquivo = new Arquivo();
    }

    @Override
    public String salvarPontosDoUsuario(Usuario usuario) {
        return arquivo.gravarDadosEmArquivo(usuario);
    }

    @Override
    public String buscarPontosPorUsuario(String nome) throws IOException {
        return arquivo.buscarUsuario(nome);

    }

    @Override
    public Usuario buscarPontosDeUsuarioPorTipoPonto(String nome, TipoPonto tipoPontoo) throws IOException {
        return arquivo.buscarPontosDoUsuarioPorTipo(nome,tipoPontoo);
    }


}
