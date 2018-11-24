package modelo;

import java.util.Date;

public class Voo {
	
	private int idVoo;
	private String agenciaVoo;
	private String tempoEstimado;
	private String modeloAviao;
	private Date dataIda;
	private Date dataVolta;
	private Pacote pacote;
	public int getIdVoo() {
		return idVoo;
	}
	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}
	public String getAgenciaVoo() {
		return agenciaVoo;
	}
	public void setAgenciaVoo(String agenciaVoo) {
		this.agenciaVoo = agenciaVoo;
	}
	public String getTempoEstimado() {
		return tempoEstimado;
	}
	public void setTempoEstimado(String tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}
	public String getModeloAviao() {
		return modeloAviao;
	}
	public void setModeloAviao(String modeloAviao) {
		this.modeloAviao = modeloAviao;
	}
	public Date getDataIda() {
		return dataIda;
	}
	public void setDataIda(Date dataIda) {
		this.dataIda = dataIda;
	}
	public Date getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(Date dataVolta) {
		this.dataVolta = dataVolta;
	}
	public Pacote getPacote() {
		return pacote;
	}
	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
	
	

}
