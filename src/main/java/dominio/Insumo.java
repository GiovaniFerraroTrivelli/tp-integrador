package dominio;

import gestores.GestorInsumo;

public class Insumo implements Comparable<Insumo> {
	private static Integer lastId = 0;

	protected Integer id;
	protected String descripcion;
	protected UnidadDeMedida unidadDeMedida;
	protected Float costo;
	protected Float peso; /* peso en kg */

	public Insumo(String descripcion) {
		this.descripcion = descripcion;
		this.id = ++lastId;
	}

	public Insumo(Integer id, UnidadDeMedida udm, Float peso) {
		this.id = ++lastId;
		this.unidadDeMedida = udm;
		this.peso = peso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	
	public Boolean getEsRefrigerado() {
		return false;
	}

	@Override
	public String toString() {
		return descripcion + " (ID: " + id + ")";
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Integer getStock() {
		return GestorInsumo.getInstance().getCantidadStockById(this.id);
	}
	
	@Override
	public int compareTo(Insumo otro) {
		return (this.getStock() - otro.getStock());
	}

	public Integer getId() {
		return id;
	}
}
