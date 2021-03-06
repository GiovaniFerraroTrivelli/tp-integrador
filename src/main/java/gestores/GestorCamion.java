package gestores;

import java.util.ArrayList;

import dominio.Camion;

public class GestorCamion {
	private static Integer lastCamionId = 0;
	private static final GestorCamion INSTANCE = new GestorCamion();
	private ArrayList<Camion> listaCamiones = new ArrayList<Camion>();

	private GestorCamion() {
	}

	public static GestorCamion getInstance() {
		return INSTANCE;
	}

	public ArrayList<Camion> getListaCamiones() {
		return this.listaCamiones;
	}

	public Camion getCamionById(Integer id) {
		for (Camion camion : listaCamiones) {
			if (camion.getId() == id)
				return camion;
		}

		return null;
	}

	public Camion crear(String nombre) {
		return new Camion(++lastCamionId);
	}

	public Camion crearCamion() {
		Camion camion = new Camion(++lastCamionId);
		listaCamiones.add(camion);

		return camion;
	}

	public void borrar(Integer id) {
		for (Camion camion : listaCamiones) {
			if (camion.getId() == id) {
				listaCamiones.remove(camion);
				break;
			}
		}
	}
}
