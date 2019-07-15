package gestores;

import java.util.ArrayList;
import dominio.Planta;

public class GestorPlanta extends Gestor {

	private static ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
	
	public static Planta crearPlanta(String nombre)
	{
		Planta planta = new Planta(nombre);
		listaPlantas.add(planta);
		
		return planta;
	}
	
	public static ArrayList<Planta> getListaPlantas()
	{
		return listaPlantas;
	}
	
	public static void borrarPlanta(Integer id)
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
}
