package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	private final List<Vertice> vertices;
	private final List<Arista> aristas;
	private Set<Vertice> verticesMarcados;
	private Set<Vertice> verticesDesmarcados;
	private Map<Vertice, Vertice> predecesores;
	private Map<Vertice, Integer> distancia;

	public Dijkstra(Grafo g) {
        // create a copy of the array so that we can operate on this array
        this.vertices = new ArrayList<Vertice>(g.getVertices());
        this.aristas = new ArrayList<Arista>(g.getAristas());
    }

	public void execute(Vertice origen) {
		verticesMarcados = new HashSet<Vertice>();
		verticesDesmarcados = new HashSet<>();
		distancia = new HashMap<Vertice, Integer>();
		predecesores = new HashMap<Vertice, Vertice>();
		distancia.put(origen, 0);
		verticesDesmarcados.add(origen);
		while (verticesDesmarcados.size() > 0) {
			Vertice v = getMinimo(verticesDesmarcados);
			verticesMarcados.add(v);
			verticesDesmarcados.remove(v);
			encontrarDistanciaMinima(v);
		}
	}

	private void encontrarDistanciaMinima(Vertice v) {
		List<Vertice> verticesAdyacentes = getVecinos(v);
		for (Vertice objetivo : verticesAdyacentes) {
			if (getDistanciaMasCorta(objetivo) > getDistanciaMasCorta(v) + getDistancia(v, objetivo)) {
				distancia.put(objetivo, getDistanciaMasCorta(v) + getDistancia(v, objetivo));
				predecesores.put(objetivo, v);
				verticesDesmarcados.add(objetivo);
			}
		}
	}

	private int getDistancia(Vertice node, Vertice target) {
		for (Arista a : aristas) {
			if (a.getOrigen().equals(node) && a.getDestino().equals(target)) {
				return a.getPeso();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Vertice> getVecinos(Vertice node) {
		List<Vertice> vecinos = new ArrayList<Vertice>();
		for (Arista a : aristas) {
			if (a.getOrigen().equals(node) && !estaMarcado(a.getDestino())) {
				vecinos.add(a.getDestino());
			}
		}
		return vecinos;
	}

	private Vertice getMinimo(Set<Vertice> vertices) {
		Vertice minimo = null;
		for (Vertice vertex : vertices) {
			if (minimo == null) {
				minimo = vertex;
			} else {
				if (getDistanciaMasCorta(vertex) < getDistanciaMasCorta(minimo)) {
					minimo = vertex;
				}
			}
		}
		return minimo;
	}

	private boolean estaMarcado(Vertice v) {
		return verticesMarcados.contains(v);
	}

	private int getDistanciaMasCorta(Vertice destino) {
		Integer d = distancia.get(destino);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and NULL
	 * if no path exists
	 */
	public LinkedList<Vertice> getCamino(Vertice target) {
		LinkedList<Vertice> camino = new LinkedList<Vertice>();
		Vertice paso = target;
		// check if a path exists
		if (predecesores.get(paso) == null) {
			return null;
		}
		camino.add(paso);
		while (predecesores.get(paso) != null) {
			paso = predecesores.get(paso);
			camino.add(paso);
		}
		// Put it into the correct order
		Collections.reverse(camino);
		return camino;
	}

}