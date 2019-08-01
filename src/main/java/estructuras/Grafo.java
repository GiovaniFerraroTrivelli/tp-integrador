package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import dominio.Planta;
import dominio.Ruta;
import gestores.GestorPlanta;
import gestores.GestorRuta;

public class Grafo<T> {
	private List<Arista> aristas;
	private List<Vertice> vertices;

	public Grafo() {
		this.aristas = new ArrayList<Arista>();
		this.vertices = new ArrayList<Vertice>();
		
		GestorPlanta gp = GestorPlanta.getInstance();
		for(Planta p : gp.getListaPlantas().values()) {
			Vertice v = new Vertice(p);
			this.addNodo(v);
		}
		
		GestorRuta gr = GestorRuta.getInstance();
		for(Ruta r : gr.getListaRutas()) {
			this.conectar(r.getOrigen(), r.getDestino(), r.getDistancia());
		}
	}

	public void addNodo(Planta nodo) {
		this.addNodo(new Vertice(nodo));
	}

	private void addNodo(Vertice nodo) {
		this.vertices.add(nodo);
	}

	public void conectar(Planta n1, Planta n2) {
		this.conectar(getNodo(n1), getNodo(n2), 1.0);
	}

	public void conectar(Planta n1, Planta n2, Number valor) {
		this.conectar(getNodo(n1), getNodo(n2), valor);
	}

	private void conectar(Vertice nodo1, Vertice nodo2, Number valor) {
		this.aristas.add(new Arista(nodo1, nodo2, valor));
	}

	public Vertice getNodo(Planta valor) {
		return this.vertices.get(this.vertices.indexOf(new Vertice(valor)));
	}

	public List<Planta> getAdyacentes(Planta valor) {
		Vertice unNodo = this.getNodo(valor);
		List<Planta> salida = new ArrayList<Planta>();
		for (Arista enlace : this.aristas) {
			if (enlace.getInicio().equals(unNodo)) {
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}

	private List<Vertice> getAdyacentes(Vertice unNodo) {
		List<Vertice> salida = new ArrayList<Vertice>();
		for (Arista enlace : this.aristas) {
			if (enlace.getInicio().equals(unNodo)) {
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}

	public void imprimirAristas() {
		System.out.println(this.aristas.toString());
	}

	public Integer gradoEntrada(Vertice vertice) {
		Integer res = 0;
		for (Arista arista : this.aristas) {
			if (arista.getFin().equals(vertice))
				++res;
		}
		return res;
	}

	public Integer gradoSalida(Vertice vertice) {
		Integer res = 0;
		for (Arista arista : this.aristas) {
			if (arista.getInicio().equals(vertice))
				++res;
		}
		return res;
	}

	public List<Planta> recorridoAnchura(Vertice inicio) {
		List<Planta> resultado = new ArrayList<Planta>();
		// estructuras auxiliares
		Queue<Vertice> pendientes = new LinkedList<Vertice>();
		HashSet<Vertice> marcados = new HashSet<Vertice>();
		marcados.add(inicio);
		pendientes.add(inicio);

		while (!pendientes.isEmpty()) {
			Vertice actual = pendientes.poll();
			List<Vertice> adyacentes = this.getAdyacentes(actual);
			resultado.add(actual.getValor());
			for (Vertice v : adyacentes) {
				if (!marcados.contains(v)) {
					pendientes.add(v);
					marcados.add(v);
				}
			}
		}
		// System.out.println(resultado);
		return resultado;
	}

	public List<Planta> recorridoProfundidad(Vertice inicio) {
		List<Planta> resultado = new ArrayList<Planta>();
		Stack<Vertice> pendientes = new Stack<Vertice>();
		HashSet<Vertice> marcados = new HashSet<Vertice>();
		marcados.add(inicio);
		pendientes.push(inicio);

		while (!pendientes.isEmpty()) {
			Vertice actual = pendientes.pop();
			List<Vertice> adyacentes = this.getAdyacentes(actual);
			resultado.add(actual.getValor());
			for (Vertice v : adyacentes) {
				if (!marcados.contains(v)) {
					pendientes.push(v);
					marcados.add(v);
				}
			}
		}
		// System.out.println(resultado);
		return resultado;
	}

	public List<Planta> recorridoTopologico() {
		List<Planta> resultado = new ArrayList<Planta>();
		Map<Vertice, Integer> gradosVertice = new HashMap<Vertice, Integer>();
		for (Vertice vert : this.vertices) {
			gradosVertice.put(vert, this.gradoEntrada(vert));
		}
		while (!gradosVertice.isEmpty()) {

			List<Vertice> nodosSinEntradas = gradosVertice.entrySet().stream().filter(x -> x.getValue() == 0)
					.map(p -> p.getKey()).collect(Collectors.toList());

			if (nodosSinEntradas.isEmpty())
				System.out.println("TIENE CICLOS");

			for (Vertice v : nodosSinEntradas) {
				resultado.add(v.getValor());
				gradosVertice.remove(v);
				for (Vertice ady : this.getAdyacentes(v)) {
					gradosVertice.put(ady, gradosVertice.get(ady) - 1);
				}
			}
		}

		System.out.println(resultado);
		return resultado;
	}

	private boolean esAdyacente(Vertice v1, Vertice v2) {
		List<Vertice> ady = this.getAdyacentes(v1);
		for (Vertice unAdy : ady) {
			if (unAdy.equals(v2))
				return true;
		}
		return false;
	}

	private void buscarCaminosAux(Vertice v1, Vertice v2, List<Vertice> marcados,
			List<List<Vertice>> todos) {
		List<Vertice> adyacentes = this.getAdyacentes(v1);
		// Vector copiaMarcados;
		List<Vertice> copiaMarcados = null;
		;

		for (Vertice ady : adyacentes) {
			System.out.println(">> " + ady);
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			if (ady.equals(v2)) {
				copiaMarcados.add(v2);
				todos.add(new ArrayList<Vertice>(copiaMarcados));
				System.out.println("ENCONTRO CAMINO " + todos.toString());
			} else {
				if (!copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.buscarCaminosAux(ady, v2, copiaMarcados, todos);
				}
			}
		}

	}

	public List<List<Vertice>> caminos(Planta origen, Planta destino) {
		return this.caminos(new Vertice(origen), new Vertice(destino));
	}

	public List<List<Vertice>> caminos(Vertice v1, Vertice v2) {
		List<List<Vertice>> salida = new ArrayList<List<Vertice>>();
		List<Vertice> marcados = new ArrayList<Vertice>();
		marcados.add(v1);
		buscarCaminosAux(v1, v2, marcados, salida);
		return salida;
	}

	public Map<Planta, Integer> caminosMinimoDikstra(Planta valorOrigen) {
		Vertice vOrigen = new Vertice(valorOrigen);
		Map<Vertice, Integer> caminosResultado = this.caminosMinimoDikstra(vOrigen);
		Map<Planta, Integer> resultado = new LinkedHashMap<Planta, Integer>();
		for (Entry<Vertice, Integer> unNodo : caminosResultado.entrySet()) {
			resultado.put(unNodo.getKey().getValor(), unNodo.getValue());
		}
		return resultado;
	}

	private Map<Vertice, Integer> caminosMinimoDikstra(Vertice origen) {

		// inicializo todas las distancias a INFINITO
		Map<Vertice, Integer> distancias = new HashMap<Vertice, Integer>();
		for (Vertice unVertice : this.vertices) {
			distancias.put(unVertice, Integer.MAX_VALUE);
		}
		distancias.put(origen, 0);

		// guardo visitados y pendientes de visitar
		Set<Vertice> visitados = new HashSet<Vertice>();
		TreeMap<Integer, Vertice> aVisitar = new TreeMap<Integer, Vertice>();

		aVisitar.put(0, origen);

		while (!aVisitar.isEmpty()) {
			Entry<Integer, Vertice> nodo = aVisitar.pollFirstEntry();
			visitados.add(nodo.getValue());

			int nuevaDistancia = Integer.MIN_VALUE;
			List<Vertice> adyacentes = this.getAdyacentes(nodo.getValue());

			for (Vertice unAdy : adyacentes) {
				if (!visitados.contains(unAdy)) {
					Arista enlace = this.buscarArista(nodo.getValue(), unAdy);
					if (enlace != null) {
						nuevaDistancia = enlace.getValor().intValue();
					}
					int distanciaHastaAdy = distancias.get(nodo.getValue()).intValue();
					int distanciaAnterior = distancias.get(unAdy).intValue();
					if (distanciaHastaAdy + nuevaDistancia < distanciaAnterior) {
						distancias.put(unAdy, distanciaHastaAdy + nuevaDistancia);
						aVisitar.put(distanciaHastaAdy + nuevaDistancia, unAdy);
					}
				}
			}
		}
		System.out.println("DISTANCIAS DESDE " + origen);
		System.out.println("Resultado: " + distancias);
		return distancias;
	}

	private Arista buscarArista(Vertice v1, Vertice v2) {
		for (Arista unaArista : this.aristas) {

			if (unaArista.getInicio().equals(v1) && unaArista.getFin().equals(v2))
				return unaArista;
		}
		return null;
	}

	public void floydWarshall() {
		int cantVertices = this.vertices.size();
		int[][] matrizDistancias = new int[cantVertices][cantVertices];

		for (int i = 0; i < cantVertices; i++) {
			for (int j = 0; j < cantVertices; j++) {
				if (i == j) {
					matrizDistancias[i][j] = 0;
				} else {
					Arista arista = this.buscarArista(this.vertices.get(i), this.vertices.get(j));
					if (arista != null) {
						matrizDistancias[i][j] = arista.getValor().intValue();
					} else {
						matrizDistancias[i][j] = 9999;
					}
				}
			}
		}
		imprimirMatriz(matrizDistancias);

		for (int k = 0; k < cantVertices; k++) {
			// Pick all vertices as source one by one
			for (int i = 0; i < cantVertices; i++) {
				// Pick all vertices as destination for the
				// above picked source
				for (int j = 0; j < cantVertices; j++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (matrizDistancias[i][k] + matrizDistancias[k][j] < matrizDistancias[i][j])
						matrizDistancias[i][j] = matrizDistancias[i][k] + matrizDistancias[k][j];
				}
			}
			System.out.println("MATRIZ " + k);
			imprimirMatriz(matrizDistancias);
		}

	}

	public void imprimirMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			System.out.print("[ ");
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(i + ":" + j + " = " + matriz[i][j] + ",   ");
			}
			System.out.println(" ]");
		}

	}

	public Boolean existeCamino(Vertice v1, Vertice v2, Integer n) {

		Stack<Vertice> visitar = new Stack<Vertice>();
		HashSet<Vertice> visitados = new HashSet<Vertice>();

		visitar.push(v1);
		int saltos = 0;

		while (!visitar.empty()) {
			saltos++;
			Vertice vInicio = visitar.pop();
			for (Vertice unAdya : this.getAdyacentes(vInicio)) {
				if (saltos <= n && unAdya.equals(v2))
					return true;
				if (!visitados.contains(unAdya)) {
					visitar.push(unAdya);
					visitados.add(unAdya);
				}
			}
		}
		return false;
	}

	public Boolean existeCaminoRec(Vertice v1, Vertice v2, Integer n) {
		HashSet<Vertice> visitados = new HashSet<Vertice>();
		visitados.add(v1);
		return existeCaminoRec(v1, v2, n, visitados);
	}

	private Boolean existeCaminoRec(Vertice v1, Vertice v2, Integer n, HashSet<Vertice> visitados) {
		if (n < 0)
			return false;
		for (Vertice unAdya : this.getAdyacentes(v1)) {
			if (n >= 0 && unAdya.equals(v2))
				return true;
			if (!visitados.contains(unAdya)) {
				visitados.add(unAdya);
				Boolean existe = existeCaminoRec(unAdya, v2, n - 1, visitados);
				if (existe)
					return true;
			}

		}
		return false;
	}

}
