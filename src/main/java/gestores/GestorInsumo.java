package gestores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.Planta;
import dominio.Stock;
import estructuras.ArbolBinarioBusqueda;

public class GestorInsumo {
	private static final GestorInsumo INSTANCE = new GestorInsumo();
	private HashMap<Integer, Insumo> listaInsumos = new HashMap<Integer, Insumo>();

	private GestorInsumo() {
	}

	public static GestorInsumo getInstance() {
		return INSTANCE;
	}

	public ArrayList<Insumo> getListaInsumos() {
		if(listaInsumos.isEmpty())
			return new ArrayList<Insumo>();
		
		return new ArrayList<Insumo>(listaInsumos.values());
	}

	public Insumo crear(String descripcion) {
		Insumo insumo = new Insumo(descripcion);
		listaInsumos.put(insumo.getId(), insumo);

		return insumo;
	}

	public InsumoLiquido crearLiquido(String descripcion) {
		InsumoLiquido insumo = new InsumoLiquido(descripcion);
		listaInsumos.put(insumo.getId(), insumo);

		return insumo;
	}

	public Insumo getInsumoById(Integer id) {
		for (Insumo insumo : getListaInsumos())
		{
			if (insumo.getId() == id) {
				return insumo;
			}
		}
		return null;
	}

	public Insumo getInsumoByStr(String nombre) {
		for(Insumo i : getListaInsumos())
		{
			if(i.getDescripcion().equals(nombre))
				return i;
		}

		return null;
	}
	public Insumo getInsumoByStock(Integer stock) {
		for(Insumo i : getListaInsumos())
		{
			if(i.getStock() == stock)
				return i;
		}
		
		return null;
	}
	
	public Insumo getInsumoByStock(Integer stock, ArrayList<Insumo> listInsumosCopy) {	
	
		for(Insumo i : listInsumosCopy)
		{
			if(i.getStock() == stock)
				return i;
		}
		
		return null;
	}
	
	public Insumo getInsumoByCosto(Float costo) {
		for(Insumo i : getListaInsumos())
		{
			if(i.getCosto() == costo)
				return i;
		}
		
		return null;
	}

	public Integer getCantidadStockById(Integer id)
	{
		HashMap<Integer, Planta> listaPlantas = GestorPlanta.getInstance().getListaPlantas();
		
		if(listaPlantas.isEmpty())
			return 0;
		
		Integer cantidad = 0;
		
		Collection<Planta> listaPlantasCol = listaPlantas.values();
		
		HashMap<Integer, Stock> listaStock;
		
		for (Planta planta : listaPlantasCol)
		{
			listaStock = planta.getListaStock();
			
			if(listaStock.get(id) != null)
				cantidad += listaStock.get(id).getCantidad();
		}
		
		return cantidad;
	}
	
	public void borrar(Integer id) {
		listaInsumos.remove(id);
		
		HashMap<Integer, Planta> listaPlantas = GestorPlanta.getInstance().getListaPlantas();
		
		if(listaPlantas.isEmpty())
			return;
		
		Collection<Planta> listaPlantasCol = listaPlantas.values();
		
		for (Planta planta : listaPlantasCol)
			planta.getListaStock().remove(id);
	}

	public ArrayList<Insumo> buscar(String busqueda) {
		String primElem = this.getListaInsumos().get(0).getDescripcion();
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>(primElem);
		for (int i = 1; i < this.getListaInsumos().size(); i++) {
			arbol.agregar(this.getListaInsumos().get(i).getDescripcion());
		}
		return arbol.buscar(busqueda).stream().map(s -> this.getInsumoByStr(s)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<Insumo> buscarPorStock(Integer busqueda, boolean tipo) {
		ArrayList<Insumo> listInsumosCopy = new ArrayList<Insumo>(getListaInsumos());
		Integer primElem = this.getListaInsumos().get(0).getStock();
		List<Integer> resultado = new ArrayList<Integer>();
		ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<Integer>(primElem);

		for(int i = 1; i < this.getListaInsumos().size(); i++) {
			arbol.agregar(this.getListaInsumos().get(i).getStock());
		}
		
		if(tipo)
			resultado = arbol.rango(0, busqueda);
		else
			resultado = arbol.rango(busqueda, Integer.MAX_VALUE);

		ArrayList<Insumo> listaInsumosAux = new ArrayList<Insumo>();
		Insumo a = this.getInsumoByStock(resultado.get(0));	
		listaInsumosAux.add(a);	
		
		Insumo b;
		Insumo c;
		
		for(int i=1; i < resultado.size() ; i++) {
			b = this.getInsumoByStock(resultado.get(i), listInsumosCopy);
			
			if(listaInsumosAux.contains(b))
			{
				listInsumosCopy.remove(b);
				c = this.getInsumoByStock(resultado.get(i), listInsumosCopy);
				
				listaInsumosAux.add(c);
			}
			else listaInsumosAux.add(b);
		}
		
		return listaInsumosAux;		
	}
	
	public ArrayList<Insumo> buscarPorCosto(Float busqueda, boolean tipo) {
		Float primElem = this.getListaInsumos().get(0).getCosto();
		List<Float> resultado = new ArrayList<Float>();
		ArbolBinarioBusqueda<Float> arbol = new ArbolBinarioBusqueda<Float>(primElem);
		for(int i = 1; i < this.getListaInsumos().size(); i++) {
			arbol.agregar(this.getListaInsumos().get(i).getCosto());
		}
		if(tipo) resultado = arbol.rango((float)0, busqueda); //buscar por maximo
		else resultado = arbol.rango(busqueda, Float.MAX_VALUE); //buscar por minimo
		return resultado.stream()
						.map(r -> this.getInsumoByCosto(r))
						.collect(Collectors.toCollection(ArrayList::new));
	}

}