package isi.died.tp.dominio;

public class Insumo implements Comparable<Insumo>
{
	protected Integer id;
	protected String descripcion;
	protected UnidadDeMedida unidadDeMedida;
	protected Float costo;
	protected Integer stock;
	protected Float peso; /*peso en kg*/
	protected Boolean esRefrigerado;
	
	public Insumo(Integer id, UnidadDeMedida udm, Integer stock, Float peso)
	{
		this.id = id;
		this.unidadDeMedida = udm;
		this.stock = stock;
		this.peso = peso;
	}
	
	@Override
	public int compareTo(Insumo otro)
	{
		return (this.stock - otro.stock);
	}
}