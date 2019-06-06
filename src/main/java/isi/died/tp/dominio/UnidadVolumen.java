package isi.died.tp.dominio;

public class UnidadVolumen extends UnidadDeMedida{
	public UnidadVolumen(){
		this.unidad = Unidades.Litro;
	}
	public void cambiarUnidad(){
		if(this.unidad == Unidades.Litro) {
			this.unidad = Unidades.M3;
			this.valor = this.valor * 0.001;
			}
		else{
			this.unidad = Unidades.Litro;
			this.valor = this.valor * 1000;
		}
	}
}
