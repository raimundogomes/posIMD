package br.ufrn.sgr.model;

import java.io.Serializable;

public class Laboratorio implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

    private String telefone;

    public Laboratorio(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nome;
    }
}
