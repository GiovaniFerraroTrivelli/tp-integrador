package dominio;

public class Ruta {
	
	private Planta origen;
	private Planta destino;
	private Float distancia;
	private Integer duracion;
	private Integer pesoMaximo;
	
	Ruta() { }
	
	public Ruta(Planta origen, Planta destino)
	{
		this.origen = origen;
		this.destino = destino;
	}

	public Planta getOrigen() {
		return origen;
	}

	public void setOrigen(Planta origen) {
		this.origen = origen;
	}

	public Planta getDestino() {
		return destino;
	}

	public void setDestino(Planta destino) {
		this.destino = destino;
	}

	public Float getDistancia() {
		return distancia;
	}

	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(Integer pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}
	
	@Override
	public String toString()
	{
		return "Origen -> " + origen + " ; Destino -> " + destino;
	}
	
}
