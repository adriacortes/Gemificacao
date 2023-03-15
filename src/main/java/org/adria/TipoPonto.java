package org.adria;

public enum TipoPonto {

    ESTRELA("estrela"),
    MOEDA("Moeda"),

    TOPICO("topico"),
    COMENTARIO("comentario"),
    CURTIDA("curtida");

    private String descricao;

    TipoPonto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
