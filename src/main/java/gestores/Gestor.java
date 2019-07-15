package gestores;

public interface Gestor<T> {
	
	public T crear(String nombre);
	public T buscar(String[] nombre);
	public void borrar(Integer id);
	
}
