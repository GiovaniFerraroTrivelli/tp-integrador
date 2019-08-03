package dominio;

import java.util.Arrays;

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
	
	 public Boolean[] resolver(Float[] peso, Float[] valor) {
		    
		    int N = peso.length;
		    int W = capacidad + 1;
		    
		    Float[][] opt = new Float[N][W];
		    for(Float[] fila : opt) {
		    	Arrays.fill(fila,0f);
		    }
		    
		    boolean[][] sol = new boolean[N][W];
		    
		    for (int n = 0; n < N; n++) {
		        for (int w = 0; w < W; w++) {
		        	Float option1 = n < 1 ? 0 : opt[n-1][w];
		            Float option2 = - Float.MAX_VALUE;
		            if (peso[n] <= w) {
		            	if(n != 0) {
		            		option2 = valor[n] + opt[n-1][(int)(w-peso[n])];
		                } 
		                else {
		                	option2 = valor[n] + opt[n][(int)(w-peso[n])];
		                }
		            }
		            opt[n][w] = Math.max(option1, option2);
		            sol[n][w] = (option2 > option1);
		        }
		    }

		    Boolean[] esSolucion= new Boolean[N];
		    
		    for (int n = N-1, w = W-1; n >= 0; n--) {
		        if (sol[n][w]) {
		            esSolucion [n] = true;
		            w = (int)(w-peso[n]);
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
