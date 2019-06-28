package isi.died.tp.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class Planta {
	private Integer id;
	private String nombre;
	
	
	
	private ArrayList<Stock> listaStock;

	public Double costoTotal(){
		return listaStock.stream().mapToDouble((i) -> (i.getInsumo().getStock() * i.getInsumo().getCosto())).sum();
	}
	
	public List<Insumo> stockEntre(Integer s1, Integer s2)
	{
		Predicate<Stock> filtro1 = ((i)-> (i.getCantidad() >= s1));
		Predicate<Stock> filtro2 = ((i)-> (i.getCantidad() <= s2));
		
		return listaStock.stream().filter(filtro1.and(filtro2)).map((s)->s.getInsumo()).collect(Collectors.toList());
	}
	
	public Boolean necesitaInsumo(Insumo i)
	{
		Predicate<Stock> filtro = ((e)-> (e.getCantidad().equals(i)));

			//TODO ESTO
		return listaStock.stream().flatMap((e) -> (e.getListaInsumos().stream())).filter(filtro).count();
	}

	public ArrayList<Stock> getListaStock() {
		return listaStock;
	}

	public void setListaStock(ArrayList<Stock> listaStock) {
		this.listaStock = listaStock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

