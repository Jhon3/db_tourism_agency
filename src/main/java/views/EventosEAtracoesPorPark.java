package views;

public class EventosEAtracoesPorPark {
	
	private String nome;
	private String cidade;
	private String pais;
	private String moeda;
	private int num_atracoes;
	private int num_eventos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getNum_atracoes() {
		return num_atracoes;
	}
	public void setNum_atracoes(int num_atracoes) {
		this.num_atracoes = num_atracoes;
	}
	
	public int getNum_eventos() {
		return num_eventos;
	}
	public void setNum_eventos(int num_eventos) {
		this.num_eventos = num_eventos;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}		
}
