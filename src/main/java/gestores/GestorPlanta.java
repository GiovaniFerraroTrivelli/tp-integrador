package gestores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import dominio.Planta;
import dominio.Stock;
import estructuras.ArbolBinarioBusqueda;

public class GestorPlanta implements Gestor<Object> {
	private static final GestorPlanta INSTANCE = new GestorPlanta();

	private GestorPlanta() {
	}

	public static GestorPlanta getInstance() {
		return INSTANCE;
	}

	private HashMap<Integer, Planta> listaPlantas = new HashMap<Integer, Planta>();

	@Override
	public Planta crear(String nombre) {
		Planta planta = new Planta(nombre);
		listaPlantas.put(planta.getId(), planta);

		return planta;
	}

	public HashMap<Integer, Planta> getListaPlantas() {
		return listaPlantas;
	}

	@Override
	public void borrar(Integer id) {
		listaPlantas.remove(id);
	}

	public Planta obtenerPlanta(Integer id) {
		return listaPlantas.get(id);
	}

	public Planta obtenerPlantaStr(String nombre) {
		for (Planta planta : listaPlantas.values()) {
			if (planta.getNombre().equals(nombre)) {
				return planta;
			}
		}
		return null;
	}

	@Override
	// TODO: Consultar a martin
	public ArrayList buscar(String busqueda) {
		if(listaPlantas.isEmpty())
			return new ArrayList<Planta>();
		
		ArrayList<Planta> listaPlantas = new ArrayList<>(this.getListaPlantas().values());
		
		if(listaPlantas.isEmpty())
			return new ArrayList<Planta>();
		
		String primElem = listaPlantas.get(0).getNombre();
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>(primElem);
		for (int i = 1; i < this.getListaPlantas().size(); i++) {
			arbol.agregar(this.getListaPlantas().get(i).getNombre());
		}
		return arbol.buscar(busqueda).stream().map(s -> this.obtenerPlantaStr(s))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public HashMap<Integer, Stock> getStockByPlantaId(Integer id) {
		return listaPlantas.get(id).getListaStock();
	}

}
