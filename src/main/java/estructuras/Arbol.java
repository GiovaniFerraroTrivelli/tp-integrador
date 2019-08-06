package estructuras;

import java.util.Collection;
import java.util.List;

public abstract class Arbol<E extends Comparable<E>> {

	protected E valor;

	public abstract List<E> preOrden();

	public abstract List<E> inOrden();

	public abstract List<E> posOrden();

	public abstract boolean esVacio();

	public abstract E valor();

	public abstract Arbol<E> izquierdo();

	public abstract Arbol<E> derecho();

	public abstract boolean contiene(E unValor);

	public abstract boolean equals(Arbol<E> unArbol);

	public abstract void agregar(E a);

	public abstract int profundidad();

	public abstract int cuentaNodosDeNivel(int nivel);

	public abstract boolean esCompleto();

	public abstract boolean esLleno();

	protected abstract int cuentaNodosDeNivelAux(int nivel, int i);

	protected abstract List<E> buscar(E busqueda);

}