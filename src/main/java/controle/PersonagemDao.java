package controle;

public class PersonagemDao {
	
	private int idPersonagens;
	private String nome;
	private String animacao;
	private EventosDao eventos;
	private AtracoesDao atracoes;
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
	public EventosDao getEventos() {
		return eventos;
	}
	public void setEventos(EventosDao eventos) {
		this.eventos = eventos;
	}
	public AtracoesDao getAtracoes() {
		return atracoes;
	}
	public void setAtracoes(AtracoesDao atracoes) {
		this.atracoes = atracoes;
	}
	
	
}
