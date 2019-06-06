package isi.died.tp.dominio;

public class UnidadMasa extends UnidadDeMedida{
	public UnidadMasa(){
		this.unidad = Unidades.Kilo;
	}
	public void cambiarUnidad(){
		if(this.unidad == Unidades.Kilo) {
			this.unidad = Unidades.Gramo;
			this.valor = this.valor * 1000;
		}
		else{
			this.unidad = Unidades.Kilo;
			this.valor = this.valor / 1000;
		}
	}
}
