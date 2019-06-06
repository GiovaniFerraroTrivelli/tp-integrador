package isi.died.tp.dominio;

public class UnidadSup extends UnidadDeMedida{
	public UnidadSup(){
		this.unidad = Unidades.M2;
	}
	public void cambiarUnidad(){
		if(this.unidad == Unidades.M2) {
			this.unidad = Unidades.CM2;
			this.valor = this.valor * 10000;
			}
		else{
			this.unidad = Unidades.M2;
			this.valor = this.valor / 10000;
		}
	}
}
