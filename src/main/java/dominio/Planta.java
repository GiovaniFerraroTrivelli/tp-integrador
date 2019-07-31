package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import layout.VerticeLayout;

public class Planta {

	public enum TipoPlanta {
		PLANTA_PRODUCCION("Planta de producción"), PLANTA_ACOPIO_INICIAL("Planta de acopio inicial"),
		PLANTA_ACOPIO_FINAL("Planta de acopio final");

		private String desc;

		public String getDesc() {
			return this.desc;
		}

		private TipoPlanta(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.desc;
		}
	};

	private Integer id;
	private String nombre;
	private TipoPlanta tipo;

	private HashMap<Integer, Stock> listaStock = new HashMap<Integer, Stock>();

	public Planta(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = TipoPlanta.PLANTA_PRODUCCION;
	}

	public Double costoTotal() {
		return new ArrayList<>(listaStock.values()).stream()
				.mapToDouble((i) -> (i.getCantidad() * i.getInsumo().getCosto())).sum();
	}

	public List<Insumo> stockEntre(Integer s1, Integer s2) {
		Predicate<Stock> filtro1 = ((i) -> (i.getCantidad() >= s1));
		Predicate<Stock> filtro2 = ((i) -> (i.getCantidad() <= s2));

		return new ArrayList<>(listaStock.values()).stream().filter(filtro1.and(filtro2)).map((s) -> s.getInsumo())
				.collect(Collectors.toList());
	}

	public Boolean necesitaInsumo(Insumo insumo) {
		Stock stockInsumo = listaStock.values().stream().filter(s -> s.getInsumo().getId() == insumo.getId()).findFirst().get();
		return stockInsumo.getCantidad() <= stockInsumo.getPuntoPedido();
	}

	public HashMap<Integer, Stock> getListaStock() {
		return listaStock;
	}

	public void setListaStock(HashMap<Integer, Stock> listaStock) {
		this.listaStock = listaStock;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoPlanta getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoPlanta tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
