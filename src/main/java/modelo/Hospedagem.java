package modelo;

public class Hospedagem {
	
	private int idHospedagem;
	private String nome;
	private float precoNoite;
	private String tipoHotel;
	private String transporteAcesso;
	private Park park;
	public int getIdHospedagem() {
		return idHospedagem;
	}
	public void setIdHospedagem(int idHospedagem) {
		this.idHospedagem = idHospedagem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrecoNoite() {
		return precoNoite;
	}
	public void setPrecoNoite(float precoNoite) {
		this.precoNoite = precoNoite;
	}
	public String getTipoHotel() {
		return tipoHotel;
	}
	public void setTipoHotel(String tipoHotel) {
		this.tipoHotel = tipoHotel;
	}
	public String getTransporteAcesso() {
		return transporteAcesso;
	}
	public void setTransporteAcesso(String transporteAcesso) {
		this.transporteAcesso = transporteAcesso;
	}
	public Park getPark() {
		return park;
	}
	public void setPark(Park park) {
		this.park = park;
	}
	
	

}
