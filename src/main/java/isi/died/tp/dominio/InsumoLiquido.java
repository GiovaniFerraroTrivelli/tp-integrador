package isi.died.tp.dominio;

public class InsumoLiquido extends Insumo
{
	public InsumoLiquido(Integer id, UnidadDeMedida udm, Integer stock, Float peso) {
		super(id, udm, stock, peso);
		// TODO Auto-generated constructor stub
	}

	private Float densidad;
	
	public Float calcularPeso()
	{
		return (this.densidad * this.stock);
	}
	
	public Float calcularPeso(Float volumen)
	{
		return (this.densidad * volumen);
	}
}
