package org.adria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Placar {

    private Armazenamento armazenamento;

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

    public List<Usuario> rankingPorPontuacao(TipoPonto pontoMoeda) throws IOException {

        List<Usuario> listTodosOsPontosDoUsuario = armazenamento.dadosDosUsuarios();

        List<Usuario> listaUsuarioComRankingPorTipoPonto = new ArrayList<>();
        Usuario usuario;

        for (Usuario us2: listTodosOsPontosDoUsuario)
        {
            Map<Enum, Integer> enumIntegerMap = us2.retornaMapComPontos();
            for (Enum tipoEnum: enumIntegerMap.keySet()) {
                if (tipoEnum.equals(pontoMoeda)){
                    Usuario user = extracted(pontoMoeda, listaUsuarioComRankingPorTipoPonto, us2, enumIntegerMap, tipoEnum);
                    listaUsuarioComRankingPorTipoPonto.add(user);
                }

            }

        }

        List<Usuario> collect1 = listaUsuarioComRankingPorTipoPonto.stream().sorted((it, it1) ->
                it.pontosDoUsuario.get(pontoMoeda).compareTo(it1.pontosDoUsuario.get(pontoMoeda))
        ).collect(Collectors.toList());

        Collections.reverse(collect1);

        return collect1;
    }

    private Usuario extracted(TipoPonto pontoMoeda, List<Usuario> usuarioRanking, Usuario us2, Map<Enum, Integer> enumIntegerMap, Enum tipoEnum) {
        Usuario usuario = new Usuario();
        usuario.setNome(us2.getNome());
        usuario.gravaPontosNoMap(pontoMoeda, enumIntegerMap.get(tipoEnum));
        return usuario;
    }
}
