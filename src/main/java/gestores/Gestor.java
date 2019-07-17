package gestores;

import java.util.ArrayList;
import java.util.List;

public abstract interface Gestor<E> {

	public E crear(String nombre);

	public void borrar(Integer id);

	public ArrayList<E> buscar(String busqueda);

}
