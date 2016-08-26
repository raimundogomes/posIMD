package com.imd030.sgr.comparator;

public class Paciente {

    private Integer prontuario;

    private String nome;

    private String nomeMae;

    private String cpf;

    private String cns;

    private String dataNascimento;

    /**
     *
     * @param prontuario
     * @param nome
     * @param dataNascimento
     * @param nomeMae
     * @param cpf
     * @param cns
     */
    public Paciente(Integer prontuario,String nome, String dataNascimento, String nomeMae, String cpf, String cns) {
        super();
        this.prontuario = prontuario;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.nome = nome;
        this.cpf = cpf;
        this.cns = cns;
    }

    public Paciente(){
        super();
    }

    public Integer getProntuario() {
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

    public void setProntuario(Integer title) {
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
