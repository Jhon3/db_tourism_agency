package modelo;

public class Evento {
	
	private int idEvento;
	private String nome;
	private String descricao;
	private int idadeIndicacao;
	private String tipoEvento;
	private Park park;
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEventos) {
		this.idEvento = idEventos;
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
