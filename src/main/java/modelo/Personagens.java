package modelo;

public class Personagens {
	
	private int idPersonagens;
	private String nome;
	private String animacao;
	private Eventos eventos;
	private Atracoes atracoes;
	public int getIdPersonagens() {
		return idPersonagens;
	}
	public void setIdPersonagens(int idPersonagens) {
		this.idPersonagens = idPersonagens;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAnimacao() {
		return animacao;
	}
	public void setAnimacao(String animacao) {
		this.animacao = animacao;
	}
	public Eventos getEventos() {
		return eventos;
	}
	public void setEventos(Eventos eventos) {
		this.eventos = eventos;
	}
	public Atracoes getAtracoes() {
		return atracoes;
	}
	public void setAtracoes(Atracoes atracoes) {
		this.atracoes = atracoes;
	}
	
	
}
