package gestores;

import java.util.ArrayList;
import java.util.List;

import dominio.Planta;
import dominio.Stock;
import estructuras.ArbolBinarioBusqueda;

public class GestorPlanta implements Gestor<Object> {
    private static final GestorPlanta INSTANCE = new GestorPlanta();

    private GestorPlanta() {}

    public static GestorPlanta getInstance() {
        return INSTANCE;
    }
    
    private ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
	
	@Override
	public Planta crear(String nombre)
	{
		Planta planta = new Planta(nombre);
		listaPlantas.add(planta);
		
		return planta;
	}
	
	public ArrayList<Planta> getListaPlantas()
	{
		return listaPlantas;
	}
	@Override
	public void borrar(Integer id)
	{
		for(int i = 0; i < listaPlantas.size(); i++)
		{
			if(listaPlantas.get(i).getId() == id)
			{
				listaPlantas.remove(i);
				break;
			}
		}
	}
	
	public Planta obtenerPlanta(Integer id)
	{
		for(int i = 0; i < listaPlantas.size(); i++)
		{
			if(listaPlantas.get(i).getId() == id)
			{
				return listaPlantas.get(i);		
			}
		}
		return null;
	}

	@Override
	public List<String> buscar(String nombre, String busqueda) {
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>();
		for(Planta p : this.getListaPlantas()){
			arbol.agregar(p.getNombre());
		}
		return arbol.buscar(busqueda);
	}
    
	public ArrayList<Stock> getStockByPlantaId(Integer id)
	{
		for(int i = 0; i < listaPlantas.size(); i++)
		{
			if(listaPlantas.get(i).getId() == id)
			{
				return listaPlantas.get(i).getListaStock();
			}
		}
		
		return null;
	}
    
}
