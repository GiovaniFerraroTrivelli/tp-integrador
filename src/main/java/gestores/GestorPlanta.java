package gestores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import dominio.Insumo;
import dominio.Planta;
import dominio.Planta.TipoPlanta;
import dominio.Stock;
import estructurasAuxiliares.ArbolBinarioBusqueda;

public class GestorPlanta implements Gestor<Object> {
	private static Integer lastPlantaId = 0;
	private static final GestorPlanta INSTANCE = new GestorPlanta();

	private GestorPlanta() {}

	public static GestorPlanta getInstance() {
		return INSTANCE;
	}

	private HashMap<Integer, Planta> listaPlantas = new HashMap<Integer, Planta>();

	@Override
	public Planta crear(String nombre) {
		Planta planta = new Planta(++lastPlantaId, nombre);
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
	
	public Planta getFirstPlantaWithTipo(TipoPlanta tipo)
	{
		for(Planta planta : listaPlantas.values())
		{
			if(planta.getTipo() == tipo)
				return planta;
		}
		
		return null;
	}

	@Override
	// TODO: Consultar a martin
	public ArrayList buscar(String busqueda) {
		if(listaPlantas.isEmpty())
			return new ArrayList<Planta>();
		
		ArrayList<Planta> arrListaPlantas = new ArrayList<>(this.getListaPlantas().values());
		
		if(arrListaPlantas.isEmpty())
			return new ArrayList<Planta>();
		
		String primElem = arrListaPlantas.get(0).getNombre();
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>(primElem);
		
		for (int i = 1; i < arrListaPlantas.size(); i++) {
			arbol.agregar(arrListaPlantas.get(i).getNombre());
		}
		
		return arbol.buscar(busqueda).stream().map(s -> this.obtenerPlantaStr(s))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<Planta> necesitanInsumo(Insumo i) {
		ArrayList<Planta> arrListaPlantas = new ArrayList<>(this.getListaPlantas().values());
		for(Planta p : arrListaPlantas) {
			if(p.necesitaInsumo(i)) {
				arrListaPlantas.add(p);
			}
		}
		return arrListaPlantas;
	}
	
	public HashMap<Integer, Stock> getStockByPlantaId(Integer id) {
		return listaPlantas.get(id).getListaStock();
	}

}
