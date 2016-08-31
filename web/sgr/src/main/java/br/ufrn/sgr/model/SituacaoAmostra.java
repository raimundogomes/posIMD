package br.ufrn.sgr.model;

/**
 * Created by thiago on 29/05/16.
 */
public enum SituacaoAmostra {

    COLETADA(1, "Coletada"),

    EM_ANALISE(2, "Em análise"),

    LIBERADA(3, "Liberada"),

    CANCELADA(4, "Cancelada");

    private int codigo;
    private String descricao;

    SituacaoAmostra(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
