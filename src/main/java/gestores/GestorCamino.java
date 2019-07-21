package gestores;
import java.util.ArrayList;

import dominio.Camino;
import dominio.Planta;

public class GestorCamino implements Gestor<Object> {
	private static final GestorCamino INSTANCE = new GestorCamino();
	private ArrayList<Camino> listaCaminos = new ArrayList<Camino>();

	private GestorCamino() {
	}

	public static GestorCamino getInstance() {
		return INSTANCE;
	}

	public ArrayList<Planta> getEndingPlantas(Planta planta)
	{
		ArrayList<Planta> resultado = new ArrayList<Planta>();
		
		for(Camino camino : listaCaminos)
		{
			if(camino.getOrigen() == planta)
			{
				resultado.add(camino.getDestino());
			}
		}
		
		return resultado;
	}
	
	public ArrayList<Planta> getStartingPlantas(Planta planta)
	{
		ArrayList<Planta> resultado = new ArrayList<Planta>();
		
		for(Camino camino : listaCaminos)
		{
			if(camino.getDestino() == planta)
			{
				resultado.add(camino.getOrigen());
			}
		}
		
		return resultado;
	}
	
	public void deleteCamino(Planta origen, Planta destino)
	{
		for(Camino camino : listaCaminos)
		{
			if(camino.getOrigen() == origen && camino.getDestino() == destino)
			{
				listaCaminos.remove(camino);
			}
		}
	}
	
	public ArrayList<Camino> getListaCaminos()
	{
		return this.listaCaminos;
	}
	
	public Camino createCamino(Planta origen, Planta destino)
	{
		Camino camino = new Camino(origen, destino);
		listaCaminos.add(camino);
		return camino;
	}
	
	@Override
	public Object crear(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> buscar(String busqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}
