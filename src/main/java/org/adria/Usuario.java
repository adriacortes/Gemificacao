package org.adria;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Usuario {

    String nome;

    Integer pontuacao;

    Enum tipoPonto;
    Map<Enum,Integer> pontosDoUsuario = new HashMap<>() ;

    public Usuario() {

    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public static Usuario criarUsuariAPartirDeUmRegistroDoArquivo(String[] registroDePontos, TipoPonto tipoPonto)
    {
        Usuario usuario = new Usuario();
        usuario.setNome(registroDePontos[0]);
        String tipoPontoD = tipoPonto.getDescricao();

        for (int i = 1; i < registroDePontos.length; i+=2)
        {
            if(registroDePontos[i].equalsIgnoreCase(tipoPontoD)){
                usuario.gravaPontosNoMap(tipoPonto,Integer.parseInt(registroDePontos[i+1]));
            }
        }
        return usuario;
    }

    public Usuario(String nome, Integer pontuacao, Enum tipoPonto) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.tipoPonto = tipoPonto;
    }

    public static Usuario criarUsuariAPartirDeUmRegistroDoArquivo(String[] registroDePontos) {
        Usuario usuario = new Usuario();
        usuario.setNome(registroDePontos[0]);


        for (int i = 1; i < registroDePontos.length; i+=2)
        {
            if(registroDePontos[i].equalsIgnoreCase(TipoPonto.MOEDA.getDescricao())){
                usuario.gravaPontosNoMap(TipoPonto.MOEDA,Integer.parseInt(registroDePontos[i+1]));
            }
            if(registroDePontos[i].equalsIgnoreCase(TipoPonto.ESTRELA.getDescricao())){
                usuario.gravaPontosNoMap(TipoPonto.ESTRELA,Integer.parseInt(registroDePontos[i+1]));
            }
            if(registroDePontos[i].equalsIgnoreCase(TipoPonto.COMENTARIO.getDescricao())){
                usuario.gravaPontosNoMap(TipoPonto.COMENTARIO,Integer.parseInt(registroDePontos[i+1]));
            }
            if(registroDePontos[i].equalsIgnoreCase(TipoPonto.TOPICO.getDescricao())){
                usuario.gravaPontosNoMap(TipoPonto.TOPICO,Integer.parseInt(registroDePontos[i+1]));
            }
            if(registroDePontos[i].equalsIgnoreCase(TipoPonto.CURTIDA.getDescricao())){
                usuario.gravaPontosNoMap(TipoPonto.CURTIDA,Integer.parseInt(registroDePontos[i+1]));
            }
        }
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void gravaPontosNoMap(Map<Enum, Integer> pontosDoUsuario) {
        this.pontosDoUsuario = pontosDoUsuario;
    }

    public Map<Enum, Integer> retornaMapComPontos() {
       return Collections.unmodifiableMap(pontosDoUsuario);
    }

    public void gravaPontosNoMap(TipoPonto tipoPontu, int pontuacao) {
        this.pontosDoUsuario.put(tipoPontu,pontuacao);
    }

    public Enum getTipoPonto() {
        return tipoPonto;
    }
}
