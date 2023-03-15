package org.adria;

import java.io.IOException;
import java.util.List;

public interface Armazenamento {


     String salvarPontosDoUsuario(Usuario usuario);
     String buscarPontosPorUsuario(String nome) throws IOException;

     Usuario buscarPontosDeUsuarioPorTipoPonto(String nome,TipoPonto tipoPontoo) throws IOException;

     List<Usuario> dadosDosUsuarios() throws IOException;


}
