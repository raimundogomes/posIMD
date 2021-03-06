package com.imd030.sgr.entity;

/**
 * Created by thiago on 29/05/16.
 */
public enum TipoExame {

    SANGUE (0, "Sangue"),
    URINA   (1, "Urina"),
    SECRECAO (2, "Secrecao");

    private int codigo;

    private String descricao;

    TipoExame(int codigo, String descricao) {
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
