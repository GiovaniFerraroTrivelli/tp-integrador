package isi.died.tp.dominio;

public class UnidadDeMedida {
	public Double valor;
	public Unidades unidad;
	
	public UnidadDeMedida() {
		this.valor = null;
		this.unidad = null;
	}
	
	public UnidadDeMedida(Double valor) {
		this.valor = valor;
		this.unidad = null;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Unidades getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidades unidad) {
		this.unidad = unidad;
	}
	
}
