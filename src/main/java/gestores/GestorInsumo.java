package gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dominio.Insumo;
import dominio.InsumoLiquido;
import estructuras.ArbolBinarioBusqueda;

public class GestorInsumo implements Gestor<Object> {
	private static final GestorInsumo INSTANCE = new GestorInsumo();
	private ArrayList<Insumo> listaInsumos = new ArrayList<Insumo>();

	private GestorInsumo() {
	}

	public static GestorInsumo getInstance() {
		return INSTANCE;
	}

	public ArrayList<Insumo> getListaInsumos() {
		return listaInsumos;
	}

	@Override
	public Insumo crear(String descripcion) {
		Insumo insumo = new Insumo(descripcion);
		listaInsumos.add(insumo);

		return insumo;
	}

	public InsumoLiquido crearLiquido(String descripcion) {
		InsumoLiquido insumo = new InsumoLiquido(descripcion);
		listaInsumos.add(insumo);

		return insumo;
	}

	public Insumo getInsumoById(Integer id) {
		for (int i = 0; i < listaInsumos.size(); i++) {
			if (listaInsumos.get(i).getId() == id) {
				return listaInsumos.get(i);
			}
		}
		return null;
	}

	public Insumo getInsumoByStr(String nombre) {
		for (int i = 0; i < listaInsumos.size(); i++) {
			if (listaInsumos.get(i).getDescripcion().equals(nombre)) {
				return listaInsumos.get(i);
			}
		}
		return null;
	}
	public Insumo getInsumoByStock(Integer stock) {
		for (int i = 0; i < listaInsumos.size(); i++) {
			if (listaInsumos.get(i).getStock() == stock) {
				return listaInsumos.get(i);
			}
		}
		return null;
	}

	@Override
	public void borrar(Integer id) {
		for (int i = 0; i < listaInsumos.size(); i++) {
			if (listaInsumos.get(i).getId() == id) {
				listaInsumos.remove(i);
				break;
			}
		}
	}

	@Override
	public ArrayList buscar(String busqueda) {
		String primElem = this.getListaInsumos().get(0).getDescripcion();
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>(primElem);
		for (int i = 1; i < this.getListaInsumos().size(); i++) {
			arbol.agregar(this.getListaInsumos().get(i).getDescripcion());
		}
		return arbol.buscar(busqueda).stream().map(s -> this.getInsumoByStr(s)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList buscarPorStock(Integer busqueda, boolean tipo) {
		Integer primElem = this.getListaInsumos().get(0).getStock();
		List<Integer> resultado = new ArrayList<Integer>();
		ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<Integer>(primElem);
		for(int i = 1; i < this.getListaInsumos().size(); i++) {
			arbol.agregar(this.getListaInsumos().get(i).getStock());
		}
		if(tipo) resultado = arbol.rango(0, busqueda);
		else resultado = arbol.rango(busqueda, Integer.MAX_VALUE);
		return (ArrayList) resultado;
	}
	
	public ArrayList buscarPorCosto(Float busqueda, boolean tipo) {
		Float primElem = this.getListaInsumos().get(0).getCosto();
		List<Float> resultado = new ArrayList<Float>();
		ArbolBinarioBusqueda<Float> arbol = new ArbolBinarioBusqueda<Float>(primElem);
		for(int i = 1; i < this.getListaInsumos().size(); i++) {
			arbol.agregar(this.getListaInsumos().get(i).getCosto());
		}
		if(tipo) resultado = arbol.rango((float)0, busqueda); //buscar por maximo
		else resultado = arbol.rango(busqueda, Float.MAX_VALUE); //buscar por minimo
		return (ArrayList) resultado;
	}

}