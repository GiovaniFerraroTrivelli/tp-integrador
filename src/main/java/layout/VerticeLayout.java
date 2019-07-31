package layout;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import dominio.Planta;

public class VerticeLayout {

	private Paint color;


	private Paint colorBase;

	private Integer coordenadaX;
	private Integer coordenadaY;
	public final Integer DIAMETRO = 20;
	private Shape nodo;

	private String nombre;
	private Integer id;
	private Planta plantaAsociada;

	public VerticeLayout() {
	}

	public VerticeLayout(Integer coordenadaX, Integer coordenadaY, Planta p) {
		this.plantaAsociada = p;
		this.color = Color.BLUE;
		this.colorBase = Color.BLUE;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.nodo = new Ellipse2D.Double(coordenadaX, coordenadaY, DIAMETRO, DIAMETRO);
	}

	public Paint getColor() {
		return color;
	}

	public void setColor(Paint color) {
		this.color = color;
	}

	public Paint getColorBase() {
		return colorBase;
	}

	public void setColorBase(Paint colorBase) {
		this.colorBase = colorBase;
	}

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public Shape getNodo() {
		return nodo;
	}

	public void setNodo(Shape nodo) {
		this.nodo = nodo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String etiqueta() {
		return "[" + id + "]" + this.nombre;
	}

	public void update() {
		this.nodo = new Ellipse2D.Double(coordenadaX, coordenadaY, DIAMETRO, DIAMETRO);
	}
	
	public Planta getPlantaAsociada() {
		return plantaAsociada;
	}
}
