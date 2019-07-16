package dominio;

public class Insumo implements Comparable<Insumo> {
	private static Integer lastId = 0;
	
	protected Integer id;
	protected String descripcion;
	protected UnidadDeMedida unidadDeMedida;
	protected Float costo;
	protected Integer stock;
	protected Float peso; /* peso en kg */
	protected Boolean esRefrigerado;

	
	public Insumo(String descripcion) {
		this.descripcion = descripcion;
		this.id = ++lastId;
	}
	
	
	public Insumo(Integer id, UnidadDeMedida udm, Integer stock, Float peso) {
		this.id = ++lastId;
		this.unidadDeMedida = udm;
		this.stock = stock;
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

	@Override
	public String toString() {
		return "Insumo [id=" + id + ", descripcion=" + descripcion + ", unidadDeMedida=" + unidadDeMedida + ", costo="
				+ costo + ", stock=" + stock + ", peso=" + peso + ", esRefrigerado=" + esRefrigerado + "]";
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
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public int compareTo(Insumo otro) {
		return (this.stock - otro.stock);
	}


	public Integer getId() {
		return id;
	}
}
