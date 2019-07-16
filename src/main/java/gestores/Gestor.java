package gestores;

import java.util.List;

public interface Gestor<E> {
	
	public E crear(String nombre);
	public void borrar(Integer id);
	public List<String> buscar(String nombre, String busqueda);
	
}
