package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominio.Planta;
import dominio.Ruta;

public class Grafo {
	List<Planta> vertices;
	List<Ruta> aristas;
	
	public Grafo(HashMap<Integer, Planta> vertices, ArrayList<Ruta> aristas) {
		this.vertices = (List<Planta>) vertices.values();
		this.aristas = (List<Ruta>) aristas;
	}
}
