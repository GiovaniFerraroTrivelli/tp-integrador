package gestores;
import java.util.ArrayList;

import dominio.Ruta;
import dominio.Planta;

public class GestorRuta {
	private static final GestorRuta INSTANCE = new GestorRuta();
	private ArrayList<Ruta> listaCaminos = new ArrayList<Ruta>();

	private GestorRuta() {
	}

	public static GestorRuta getInstance() {
		return INSTANCE;
	}

	public ArrayList<Planta> getEndingPlantas(Planta planta)
	{
		ArrayList<Planta> resultado = new ArrayList<Planta>();
		
		for(Ruta camino : listaCaminos)
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
		
		for(Ruta camino : listaCaminos)
		{
			if(camino.getDestino() == planta)
			{
				resultado.add(camino.getOrigen());
			}
		}
		
		return resultado;
	}
	
	public void deleteRuta(Planta origen, Planta destino)
	{
		for(Ruta camino : listaCaminos)
		{
			if(camino.getOrigen() == origen && camino.getDestino() == destino)
			{
				listaCaminos.remove(camino);
			}
		}
	}
	
	public ArrayList<Ruta> getListaRutas()
	{
		return this.listaCaminos;
	}
	
	public Ruta createRuta(Planta origen, Planta destino)
	{
		Ruta camino = new Ruta(origen, destino);
		listaCaminos.add(camino);
		return camino;
	}
	
	public Object crear(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Object> buscar(String busqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}
