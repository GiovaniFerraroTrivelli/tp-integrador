package estructuras;

import dominio.Planta;

public class Vertice {

	private Planta valor;
	
	public Vertice(){	}
	 
	public Vertice(Planta v){
		this.valor = v;
	}
	
	public void setValor(Planta v){
		this.valor = v;
	}
	
	public Planta getValor(){
		return this.valor;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return valor.toString();
	}
	
	
}
