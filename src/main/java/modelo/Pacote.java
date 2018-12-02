package modelo;

public class Pacote {
	
	private int idPacote;
	private int qtdPessoas;
	private String nome;
	private Park park;
	private float valor;
	
	
	
	
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdPacote() {
		return idPacote;
	}
	public void setIdPacote(int idPacote) {
		this.idPacote = idPacote;
	}
	public int getQtdPessoas() {
		return qtdPessoas;
	}
	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}
	public Park getPark() {
		return park;
	}
	public void setPark(Park park) {
		this.park = park;
	}
	
	
}
