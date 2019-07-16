package gestores;

import java.util.List;


public interface Gestor<E> {
	
	public E crear(String nombre);
	public void borrar(Integer id);
	public List<E> buscar(String busqueda);
	
}
