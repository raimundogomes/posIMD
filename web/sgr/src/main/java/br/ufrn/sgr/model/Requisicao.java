package br.ufrn.sgr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by thiago on 28/05/16.
 */
public class Requisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String FORMATO_NUMERO = "%08d";

	private Date dataRequisicao;

	private Paciente paciente;

	private StatusRequisicao status;

	private Long id;
	
	private Integer crm;

	private long numero = 1;

	private Laboratorio laboratorio;

	private List<Exame> exames;

	private Date dataUltimaModificacao;

	public Requisicao() {
		numero++;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public Requisicao(Date dataRequisicao, Paciente paciente, StatusRequisicao status) {
		this.dataRequisicao = dataRequisicao;
		this.paciente = paciente;
		this.status = status;
		this.numero = numero++;
	}

	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}


	public Integer getCrm() {
		return crm;
	}

	public void setCrm(Integer crm) {
		this.crm = crm;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public StatusRequisicao getStatus() {
		return status;
	}

	public void setStatus(StatusRequisicao status) {
		this.status = status;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public Date getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	public void setDataUltimaModificacao(Date dataFim) {
		this.dataUltimaModificacao = dataFim;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getNumeroFormatado() {
		return String.format(FORMATO_NUMERO, this.numero);
	}

	public String getExamesFormatados() {
		return exames.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSituacao(int situacao) {

		this.setStatus(StatusRequisicao.getStatusRequisicaoByCodigo(situacao));
	}

	@Override
	public String toString() {
		return "Requisicao [dataRequisicao=" + dataRequisicao + ", paciente=" + paciente + ", status=" + status
				+ ", numero=" + numero + ", laboratorio=" + laboratorio + ", exames=" + exames + ", dataFim=" + dataUltimaModificacao
				+ "]";
	}

}
