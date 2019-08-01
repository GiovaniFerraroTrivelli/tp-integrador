package dijkstra;

import java.util.ArrayList;
import java.util.List;

import dominio.Planta;
import dominio.Ruta;
import gestores.GestorPlanta;
import gestores.GestorRuta;

public class Grafo{

	private final List<Vertice> vertices;
	private final List<Arista> aristas;
	
	public Grafo() {
		GestorPlanta gestorPlanta = GestorPlanta.getInstance();
		GestorRuta gestorRuta = GestorRuta.getInstance();
		
		ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
		ArrayList<Arista> listaAristas = new ArrayList<Arista>();
		
		for(Planta p : gestorPlanta.getListaPlantas().values()) {
			Vertice v = new Vertice(p.getId());
			listaVertices.add(v);
		}
		
		for(Ruta r : gestorRuta.getListaRutas()) {
			Vertice origen = listaVertices.stream().filter(v -> v.getId() == r.getOrigen().getId()).findFirst().get();
			Vertice destino = listaVertices.stream().filter(v -> v.getId() == r.getDestino().getId()).findFirst().get();
			
			if(origen != null && destino != null) {
				Arista a = new Arista(origen, destino, r.getDistancia());
				listaAristas.add(a);
			}
		}
		
		this.vertices = listaVertices;
		this.aristas = listaAristas;
	}
	
	public void dijkstra(Planta origen, Planta destino) {
		Vertice verticeOrigen = vertices.stream().filter(v -> v.getId() == origen.getId()).findFirst().get();
		Vertice verticeDestino = vertices.stream().filter(v -> v.getId() == destino.getId()).findFirst().get();
		
		Dijkstra d = new Dijkstra(this);
		d.execute(verticeOrigen);
		d.getCamino(verticeDestino);	
	}
	
	public List<Vertice> getVertices() {
		return vertices;
	}
	public List<Arista> getAristas() {
		return aristas;
	}
}