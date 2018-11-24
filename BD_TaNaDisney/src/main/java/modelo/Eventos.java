package modelo;

public class Eventos {
	
	private int idEventos;
	private String nome;
	private int idadeIndicacao;
	private String tipoEvento;
	private Park park;
	public int getIdEventos() {
		return idEventos;
	}
	public void setIdEventos(int idEventos) {
		this.idEventos = idEventos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdadeIndicacao() {
		return idadeIndicacao;
	}
	public void setIdadeIndicacao(int idadeIndicacao) {
		this.idadeIndicacao = idadeIndicacao;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public Park getPark() {
		return park;
	}
	public void setPark(Park park) {
		this.park = park;
	}
	
	
}
