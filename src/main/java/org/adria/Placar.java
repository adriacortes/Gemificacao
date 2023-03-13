package org.adria;

import java.io.IOException;

public class Placar {

    Armazenamento armazenamento;

    public Placar(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }


    public String salvarPontosDoUsuario(Usuario usuario) {
        return armazenamento.salvarPontosDoUsuario(usuario);
    }

    public String buscarPontosPorUsuario(String nome) throws IOException {
        return armazenamento.buscarPontosPorUsuario(nome);
    }

    public Usuario buscarPontosPorTipo(String liana, TipoPonto tipoPonto) throws IOException {
        return armazenamento.buscarPontosDeUsuarioPorTipoPonto(liana,tipoPonto);
    }
}
