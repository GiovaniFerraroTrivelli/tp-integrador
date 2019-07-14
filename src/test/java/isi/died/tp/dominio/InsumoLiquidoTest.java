package isi.died.tp.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsumoLiquidoTest {

	@Test
	public void test() {
		InsumoLiquido insumoLiquido = new InsumoLiquido(2, UnidadDeMedida.LITRO, 23, 0.9f);

		insumoLiquido.setDensidad(1577.0f);

		Float val1 = insumoLiquido.calcularPeso(1000.0f);
		Float val2 = 1577.0f;

		assertEquals(val1, val2);
	}
}
