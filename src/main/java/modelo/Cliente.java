package modelo;

public class Cliente {
	
	private int idCliente;
	private String cpf;
	private Pessoa pessoa;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cnpj) {
		this.cpf = cnpj;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

}
