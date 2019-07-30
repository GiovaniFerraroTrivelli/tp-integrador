package gestores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import dominio.Insumo;
import dominio.Planta;
import dominio.Planta.TipoPlanta;
import dominio.Ruta;
import dominio.Stock;
import estructurasAuxiliares.ArbolBinarioBusqueda;

public class GestorPlanta {
	private static Integer lastPlantaId = 0;
	private static final GestorPlanta INSTANCE = new GestorPlanta();

	private GestorPlanta() {
	}

	public static GestorPlanta getInstance() {
		return INSTANCE;
	}

	private HashMap<Integer, Planta> listaPlantas = new HashMap<Integer, Planta>();

	public Planta crear(String nombre) {
		Planta planta = new Planta(++lastPlantaId, nombre);
		listaPlantas.put(planta.getId(), planta);

		return planta;
	}

	public HashMap<Integer, Planta> getListaPlantas() {
		return listaPlantas;
	}

	public void borrar(Integer id) {
		listaPlantas.remove(id);
	}

	public Planta obtenerPlanta(Integer id) {
		return listaPlantas.get(id);
	}

	public Planta obtenerPlanta(String nombre) {
		for (Planta planta : listaPlantas.values()) {
			if (planta.getNombre().equals(nombre)) {
				return planta;
			}
		}
		return null;
	}

	public Planta getFirstPlantaWithTipo(TipoPlanta tipo) {
		for (Planta planta : listaPlantas.values()) {
			if (planta.getTipo() == tipo)
				return planta;
		}

		return null;
	}

	public ArrayList<Planta> buscar(String busqueda) {
		if (listaPlantas.isEmpty())
			return new ArrayList<Planta>();

		ArrayList<Planta> arrListaPlantas = new ArrayList<>(this.getListaPlantas().values());

		if (arrListaPlantas.isEmpty())
			return new ArrayList<Planta>();

		String primElem = arrListaPlantas.get(0).getNombre();
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>(primElem);

		for (int i = 1; i < arrListaPlantas.size(); i++) {
			arbol.agregar(arrListaPlantas.get(i).getNombre());
		}

		return arbol.buscar(busqueda).stream().map(s -> this.obtenerPlanta(s))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<Planta> necesitanInsumo(Insumo i) {
		ArrayList<Planta> arrListaPlantas = new ArrayList<>(this.getListaPlantas().values());
		ArrayList<Planta> plantasNecesitadas = new ArrayList<Planta>();

		for (Planta p : arrListaPlantas) {
			if (p.necesitaInsumo(i)) {
				plantasNecesitadas.add(p);
			}
		}

		return plantasNecesitadas;
	}

	public HashMap<Integer, Stock> getStockByPlantaId(Integer id) {
		return listaPlantas.get(id).getListaStock();
	}

	public ArrayList<Planta> pageRankSort() {
		ArrayList<Planta> listaPlantas = new ArrayList<Planta>(this.getListaPlantas().values());

		Comparator<Planta> pageRankCompare = new Comparator<Planta>() {
			@Override
			public int compare(Planta p1, Planta p2) {
				return (this.getPageRank(p1) - this.getPageRank(p2));
			}

			private int getPageRank(Planta p1) {
				int pageRank = 0;
				GestorRuta gestorRuta = GestorRuta.getInstance();
				ArrayList<Ruta> listaRutas = new ArrayList<Ruta>(gestorRuta.getListaRutas());

				for (Ruta r : listaRutas) {
					if (r.getDestino() == p1 /* || r.getOrigen() == p1 */) {
						pageRank++;
					}
				}

				return pageRank;
			}

		};

		Collections.sort(listaPlantas, pageRankCompare);
		return listaPlantas;
	}
}
