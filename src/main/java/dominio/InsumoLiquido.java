package dominio;

public class InsumoLiquido extends Insumo {
	private Float densidad;

	public InsumoLiquido(Integer id, UnidadDeMedida udm, Integer stock, Float peso) {
		super(id, udm, stock, peso);
	}
	
	public InsumoLiquido(String descripcion) {
		super(descripcion);
	}

	public void setDensidad(float densidad) {
		this.densidad = densidad;
	}

	public Float calcularPeso() {
		return (this.densidad * this.stock / 1000);
	}

	public Float calcularPeso(Float volumen) {
		return (this.densidad * volumen / 1000);
	}
}
