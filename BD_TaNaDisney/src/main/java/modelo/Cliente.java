package modelo;

public class Cliente {
	
	private int idAgente;
	private String cpf;
	private Pessoa pessoa;
	public int getIdAgente() {
		return idAgente;
	}
	public void setIdAgente(int idAgente) {
		this.idAgente = idAgente;
	}
	public String getCnpj() {
		return cpf;
	}
	public void setCnpj(String cnpj) {
		this.cpf = cnpj;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

}
