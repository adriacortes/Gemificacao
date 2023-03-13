package org.adria;

import java.io.IOException;

public interface Armazenamento {


    public String salvarPontosDoUsuario(Usuario usuario);
    public String buscarPontosPorUsuario(String nome) throws IOException;

    public Usuario buscarPontosDeUsuarioPorTipoPonto(String nome,TipoPonto tipoPontoo) throws IOException;

}
