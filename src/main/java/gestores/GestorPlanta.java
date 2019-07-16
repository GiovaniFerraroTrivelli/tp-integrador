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
	public List<String> buscar(String busqueda) {
		String primElem = this.getListaPlantas().get(0).getNombre();
		ArbolBinarioBusqueda<String> arbol = new ArbolBinarioBusqueda<String>(primElem);
		for(int i = 1; i < this.getListaPlantas().size(); i++){
			arbol.agregar(this.getListaPlantas().get(i).getNombre());
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
