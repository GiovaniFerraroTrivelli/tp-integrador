package isi.died.tp.dominio;

public class Stock {
	private Integer id;
	private Integer cantidad;
	private Integer puntoPedido;
	
	private Insumo insumo;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getPuntoPedido() {
		return puntoPedido;
	}
	public void setPuntoPedido(Integer puntoPedido) {
		this.puntoPedido = puntoPedido;
	}
	public Insumo getInsumo() {
		return this.insumo;
	}
	
	
}
