package gestores;

import java.util.ArrayList;

import dominio.Insumo;

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
		return (new Insumo(descripcion));
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
	public Object buscar(String[] nombre) {
		// TODO Auto-generated method stub
		return null;
	}
}