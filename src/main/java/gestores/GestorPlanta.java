package gestores;

import java.util.ArrayList;
import dominio.Planta;

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

	@Override
	public Planta buscar(String[] nombre) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
