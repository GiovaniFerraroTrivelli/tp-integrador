package dominio;

public class InsumoLiquido extends Insumo {
	private Float densidad;
	private Float volumen;

	public InsumoLiquido(Integer id, UnidadDeMedida udm, Float peso) {
		super(id, udm, peso);
	}

	public InsumoLiquido(String descripcion) {
		super(descripcion);
	}

	public void setDensidad(float densidad) {
		this.densidad = densidad;
	}

	public void setVolumen(Float volumen)
	{
		this.volumen = volumen;
	}
	
	public Float calcularPeso()
	{
		return (this.densidad * this.volumen / 1000);
	}
	
	public Float calcularPeso(Float volumen) {
		return (this.densidad * volumen / 1000);
	}
}
