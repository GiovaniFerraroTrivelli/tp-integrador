package isi.died.tp.dominio;

public class UnidadLong extends UnidadDeMedida{
	public UnidadLong(){
		this.unidad = Unidades.Metro;
	}
	public void cambiarUnidad(){
		if(this.unidad == Unidades.Metro) {
			this.unidad = Unidades.CM;
			this.valor = this.valor * 100;
			}
		else{
			this.unidad = Unidades.Metro;
			this.valor = this.valor / 100;
		}
	}
}
