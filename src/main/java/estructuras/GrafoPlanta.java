package estructuras;

import java.util.List;

import dominio.Insumo;
import dominio.Planta;

public class GrafoPlanta extends Grafo<Planta> {
	public void imprimirDistanciaAdyacentes(Planta inicial) {
		List<Planta> adyacentes = super.getAdyacentes(inicial);

		for (Planta unAdyacente : adyacentes) {
			Arista<Planta> camino = super.buscarArista(inicial, unAdyacente);
			System.out.println("camino de " + inicial.getNombre() + "a " + unAdyacente.getNombre() + "tiene valor de "
					+ camino.getValor());
		}
	}

	public Planta buscarPlanta(Planta inicial, Insumo i, Integer saltos) {

		if (saltos < 0) {
			return null;
		} else if (inicial.necesitaInsumo(i)) {
			return inicial;
		} else {
			List<Planta> plantas = this.getAdyacentes(inicial);
			for (Planta p : plantas) {
				return buscarPlanta(p, i, saltos - 1);
			}
		}

		return null;
	}

}
