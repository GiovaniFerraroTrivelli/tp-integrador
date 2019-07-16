package gestores;

import java.util.ArrayList;
import java.util.List;

import dominio.Insumo;
import dominio.InsumoLiquido;

public class GestorInsumo implements Gestor<Object>{
    private static final GestorInsumo INSTANCE = new GestorInsumo();
    private ArrayList<Insumo> listaInsumos = new ArrayList<Insumo>();
    private GestorInsumo() {}

    public static GestorInsumo getInstance() {
        return INSTANCE;
    }
    
    public ArrayList<Insumo> getListaInsumos(){
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
    
    public Insumo getInsumoById(Integer id)
    {
    	for(int i = 0; i < listaInsumos.size(); i++)
		{
			if(listaInsumos.get(i).getId() == id)
			{
				return listaInsumos.get(i);		
			}
		}
		return null;
    }
    
	@Override
	public void borrar(Integer id) {
		for(int i = 0; i < listaInsumos.size(); i++)
		{
			if(listaInsumos.get(i).getId() == id)
			{
				listaInsumos.remove(i);
				break;
			}
		}
	}
	
	@Override
	public List<String> buscar(String busqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}