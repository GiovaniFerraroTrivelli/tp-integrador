package dominio;

public class Camion {
	private Integer id;
	private String marca;
	private String modelo;
	private Integer anio;
	private String dominio;
	private Float costoKm;
	private Boolean transportaLiq;
	private Float capacidad;
	
	public Camion(Integer id){
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public Integer getAnio() {
		return anio;
	}
	
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	public String getDominio() {
		return dominio;
	}
	
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public Float getCostoKm() {
		return costoKm;
	}
	
	public void setCostoKm(Float costoKm) {
		this.costoKm = costoKm;
	}
	public Boolean getTransportaLiq() {
		return transportaLiq;
	}
	
	public void setTransportaLiq(Boolean transportaLiq) {
		this.transportaLiq = transportaLiq;
	}
	
	public Float getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(Float capacidad) {
		this.capacidad = capacidad;
	}
}
