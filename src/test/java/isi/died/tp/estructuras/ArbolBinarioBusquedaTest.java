package isi.died.tp.estructuras;

import static org.junit.Assert.*;

import org.junit.Test;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.UnidadDeMedida;
import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioBusquedaTest {

	@Test
	public void test() {
		Insumo val0 = new Insumo(2, UnidadDeMedida.KILO, 1, 7.5f);
		ArbolBinarioBusqueda<Insumo> abb = new ArbolBinarioBusqueda<Insumo>(val0);
		List<Insumo> val1 = new ArrayList<Insumo>();
		List<Insumo> val2 = new ArrayList<Insumo>();

		val0 = new Insumo(1, UnidadDeMedida.KILO, 5, 3.0f);
		abb.agregar(val0);
		val1.add(val0);

		val0 = new Insumo(3, UnidadDeMedida.KILO, 12, 545.0f);
		abb.agregar(val0);
		val1.add(val0);

		val0 = new Insumo(4, UnidadDeMedida.KILO, 85, 2.0f);
		abb.agregar(val0);

		val0 = new Insumo(6, UnidadDeMedida.KILO, 42, 1.0f);
		abb.agregar(val0);

		val0 = new Insumo(7, UnidadDeMedida.KILO, 33, 23.0f);
		abb.agregar(val0);
		val1.add(val0);

		val0 = new Insumo(8, UnidadDeMedida.KILO, 90, 90.0f);
		abb.agregar(val0);

		val0 = new Insumo(9, UnidadDeMedida.KILO, 249, 1.0f);
		abb.agregar(val0);

		val2 = abb.rango(new Insumo(1, UnidadDeMedida.KILO, 5, 3.0f), new Insumo(7, UnidadDeMedida.KILO, 33, 23.0f));

		assertEquals(val1, val2);
	}

}
