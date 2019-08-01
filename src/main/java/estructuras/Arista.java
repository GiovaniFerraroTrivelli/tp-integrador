package estructuras;

public class Arista {
	private Vertice inicio;
	private Vertice fin;
	private Number valor;

	public Arista(){
		valor=1.0;
	} 
	
	public Arista(Vertice ini,Vertice fin){
		this();
		this.inicio = ini;
		this.fin = fin;
	}

	public Arista(Vertice ini,Vertice fin,Number val){
		this(ini,fin);
		this.valor= val;
	}
	
	public Vertice getInicio() {
		return inicio;
	}
	
	public void setInicio(Vertice inicio) {
		this.inicio = inicio;
	}
	
	public Vertice getFin() {
		return fin;
	}
	
	public void setFin(Vertice fin) {
		this.fin = fin;
	}

	public Number getValor() {
		return valor;
	}

	public void setValor(Number valor) {
		this.valor = valor;
	}
	
	
	@Override
	public String toString() {
		return "( "+this.inicio.getValor()+" --> "+this.fin.getValor()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Arista) && ((Arista)obj).getValor().equals(this.valor); 
	}
}
