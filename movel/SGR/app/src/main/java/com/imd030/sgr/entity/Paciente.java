package com.imd030.sgr.entity;

import java.io.Serializable;


public class Paciente  implements Serializable {

    private Long prontuario;

    private String nome;

    private String nomeMae;

    private String cpf;

    private String cns;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    private String dataNascimento;

    private String email;

    private String telefone;

    /**
     *
     * @param prontuario
     * @param nome
     * @param dataNascimento
     * @param nomeMae
     * @param cpf
     * @param cns
     */
    public Paciente(Long prontuario,String nome, String dataNascimento, String nomeMae, String cpf, String cns) {
        super();
        this.prontuario = prontuario;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.nome = nome;
        this.cpf = cpf;
        this.cns = cns;
    }

    /**
     * Construtor
     * @param cns carteira nacional de saúde - cns.
     * @param nome Nome do Paciente.
     * @param prontuario Número do paciente.
     * @param email e-mail paciente.
     */
    public Paciente(String cns, String nome, long prontuario, String email, String telefone) {
        this.cns = cns;
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
        this.telefone = telefone;
    }

    public Paciente(){
        super();
    }

    public Long getProntuario() {
        return prontuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setProntuario(Long title) {
        this.prontuario = title;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String singer) {
        this.nome = singer;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    @Override
    public String toString() {
        return "Paciente [prontuario=" + prontuario + ", nome=" + nome + ", nomeMae=" + nomeMae + ", cpf=" + cpf
                + ", cns=" + cns + ", dataNascimento=" + dataNascimento + "]";
    }



}
