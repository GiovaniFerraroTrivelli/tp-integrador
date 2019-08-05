package dominio;

public class Ruta {
	
	private Planta origen;
	private Planta destino;
	private Integer distancia;
	private Integer duracion;
	private Integer pesoMaximo;
	private Integer backupPesoMaximo;
	
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

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
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
	
	public void setBackupPesoMaximo(Integer n) {
		this.backupPesoMaximo = n;
	}
	
	public void restaurarPesoMaximo() {
		this.pesoMaximo = this.backupPesoMaximo;
	}
	
	@Override
	public String toString(){
		return "Origen -> " + origen + " ; Destino -> " + destino;
	}
	
}
