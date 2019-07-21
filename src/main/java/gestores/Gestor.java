package gestores;

import java.util.ArrayList;

public abstract interface Gestor<E> {

	public E crear(String nombre);

	public void borrar(Integer id);

	public ArrayList<E> buscar(String busqueda);

}
