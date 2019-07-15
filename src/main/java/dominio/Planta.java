package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Planta {
	private static Integer lastId = 0;
	
	private Integer id;
	private String nombre;

	private ArrayList<Stock> listaStock;

	public Planta(String nombre)
	{
		this.id = ++lastId;
		this.nombre = nombre;
	}
	
	public Double costoTotal() {
		return listaStock.stream().mapToDouble((i) -> (i.getInsumo().getStock() * i.getInsumo().getCosto())).sum();
	}

	public List<Insumo> stockEntre(Integer s1, Integer s2) {
		Predicate<Stock> filtro1 = ((i) -> (i.getCantidad() >= s1));
		Predicate<Stock> filtro2 = ((i) -> (i.getCantidad() <= s2));

		return listaStock.stream().filter(filtro1.and(filtro2)).map((s) -> s.getInsumo()).collect(Collectors.toList());
	}

	public Boolean necesitaInsumo(Insumo i) {
		Predicate<Stock> filtro = ((e) -> (e.getCantidad() < e.getPuntoPedido()));

		List<Stock> stock = listaStock.stream().filter(filtro).collect(Collectors.toList());

		return (stock.size() == 0) ? false : true;
	}

	public ArrayList<Stock> getListaStock() {
		return listaStock;
	}

	public void setListaStock(ArrayList<Stock> listaStock) {
		this.listaStock = listaStock;
	}
	
	public Integer getId()
	{
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Planta [id=" + id + ", nombre=" + nombre + ", listaStock=" + listaStock + "]";
	}
}
