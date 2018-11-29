package modelo;

public class Personagem {
	
	private int idPersonagem;
	private String nome;
	private String animacao;
	private Evento eventos;
	private Atracao atracoes;
	public int getIdPersonagem() {
		return idPersonagem;
	}
	public void setIdPersonagem(int idPersonagem) {
		this.idPersonagem = idPersonagem;
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
	public Evento getEventos() {
		return eventos;
	}
	public void setEventos(Evento eventos) {
		this.eventos = eventos;
	}
	public Atracao getAtracoes() {
		return atracoes;
	}
	public void setAtracoes(Atracao atracoes) {
		this.atracoes = atracoes;
	}
	
	
}
