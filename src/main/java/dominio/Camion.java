package dominio;

public class Camion {
	private Integer id;
	private String marca;
	private String modelo;
	private Integer anio;
	private String dominio;
	private Float costoKm;
	private Boolean transportaLiq;
	private int capacidad;
	
	public Camion(Integer id){
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public Integer getAnio() {
		return anio;
	}
	
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	public String getDominio() {
		return dominio;
	}
	
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public Float getCostoKm() {
		return costoKm;
	}
	
	public void setCostoKm(Float costoKm) {
		this.costoKm = costoKm;
	}
	public Boolean getTransportaLiq() {
		return transportaLiq;
	}
	
	public void setTransportaLiq(Boolean transportaLiq) {
		this.transportaLiq = transportaLiq;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public boolean[] resolver(Float[] peso, Float[] valor) {  // valor = costo insumo
		Integer N = peso.length; // items
		Integer W = this.capacidad + 1; // max peso
		  
		Float[][] opt = new Float[N][W]; //matriz que guarda el valor de cada escenario
		boolean[][] sol = new boolean[N][W]; // matriz que guarda si el elemento esta en el escenario
		    
		for (int n = 0; n < N; n++) {
			for (int w = 0; w < W; w++) {
				Float option1 = n < 1 ? 0 : opt[n -1][w]; //completar
				Float option2 = Float.MIN_VALUE;
		        if (peso[n] <= w) { //Hay espacio en la mochila?
		        	if(n != 0)
		        		option2 = valor[n] + opt[n-1][(w-(Math.round(peso[n])))]; //actualizar el valor de agregar a la mochila el elemento
		         	else
		            	option2 = valor[n] + opt[n][w-(Math.round(peso[n]))];
		         }
		                
		            opt[n][w] = Math.max(option1, option2);
		            sol[n][w] = (option2 > option1);
		     }
		}
		boolean[] esSolucion= new boolean[N];
		for (int n = N-1, w = N-1; n >= 0; n--) {
			if (sol[n][w]) {
		    	esSolucion [n] = true;
		    	w = w - (Math.round(peso[n]));
		    } else {
		            esSolucion [n] = false;
		    }
		}
		System.out.println("Pares peso valor en solucion optima");
		boolean b=false;
		for(int i=0;i<N;i++){
			if(esSolucion[i]){
		    	if(b) System.out.print(" - ");
		        System.out.print("("+peso[i]+" "+valor[i]+")");
		         b=true;
			}
		}
		System.out.println("\n");
		return esSolucion;
	 }
}
