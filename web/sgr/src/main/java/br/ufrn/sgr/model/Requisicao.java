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

	private SituacaoRequisicao status;

	private Long id;
	
	private String emailSolicitante;
	
	private long numero = 1;

	private Laboratorio laboratorio;

	private List<Exame> exames;

	private Date dataUltimaModificacao;
	
	private Date dataEntrega;
	
    private Boolean internadoUltimas72Horas;
    private Boolean submetidoProcedimentoInvasivo;
    private Boolean fezUsoAntibiotico;
    
    private boolean temHemocultura = false;
    private boolean temUrocultura = false;
    private boolean temSecrecao = false;


	public Requisicao() {
		numero++;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public Requisicao(Date dataRequisicao, Paciente paciente, SituacaoRequisicao status) {
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public SituacaoRequisicao getStatus() {
		return status;
	}

	public void setStatus(SituacaoRequisicao status) {
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

		this.setStatus(SituacaoRequisicao.getStatusRequisicaoByCodigo(situacao));
	}

	@Override
	public String toString() {
		return "Requisicao [dataRequisicao=" + dataRequisicao + ", paciente=" + paciente + ", status=" + status
				+ ", numero=" + numero + ", laboratorio=" + laboratorio + ", exames=" + exames + ", dataFim=" + dataUltimaModificacao
				+ "]";
	}
	
	public String getEmailSolicitante() {
		return emailSolicitante;
	}

	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	public Boolean getInternadoUltimas72Horas() {
		return internadoUltimas72Horas;
	}

	public void setInternadoUltimas72Horas(Boolean internadoUltimas72Horas) {
		this.internadoUltimas72Horas = internadoUltimas72Horas;
	}

	public Boolean getSubmetidoProcedimentoInvasivo() {
		return submetidoProcedimentoInvasivo;
	}

	public void setSubmetidoProcedimentoInvasivo(Boolean submetidoProcedimentoInvasivo) {
		this.submetidoProcedimentoInvasivo = submetidoProcedimentoInvasivo;
	}

	public Boolean getFezUsoAntibiotico() {
		return fezUsoAntibiotico;
	}

	public void setFezUsoAntibiotico(Boolean fezUsoAntibiotico) {
		this.fezUsoAntibiotico = fezUsoAntibiotico;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	 public boolean getTemHemocultura() {
	        return temHemocultura;
	    }


	    public void setTemHemocultura(boolean temHemocultura) {
	        this.temHemocultura = temHemocultura;
	    }

	    public boolean getTemUrocultura() {
	        return temUrocultura;
	    }

	    public boolean getTemSecrecao() {
	        return temSecrecao;
	    }

	    public void setTemUrocultura(boolean temUrocultura) {
	        this.temUrocultura = temUrocultura;
	    }

	    public void setTemSecrecao(boolean temSecrecao) {
	        this.temSecrecao = temSecrecao;
	    }

}
