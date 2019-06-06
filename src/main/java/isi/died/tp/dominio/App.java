package isi.died.tp.dominio;

import isi.died.tp.estructuras.*;

public class App {

	public static void main(String[] args) {
		ArbolBinarioBusqueda<Insumo> abb = new ArbolBinarioBusqueda<Insumo>(new Insumo(2, new UnidadMasa(), 6, 7.5f));
		
		abb.agregar(new Insumo(1, new UnidadMasa(), 5, 3.0f));
		abb.agregar(new Insumo(3, new UnidadMasa(), 85, 10.0f));
	}

}
