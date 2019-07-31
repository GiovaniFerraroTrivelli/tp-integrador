package layout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;

import dominio.Ruta;

public class AristaLayout {

	private VerticeLayout origen;
	private VerticeLayout destino;
	private Shape linea;
	private Stroke formatoLinea;
	private Paint color;
	private int offset;
	private Ruta rutaAsociada;

	public AristaLayout() {
	}

	public AristaLayout(VerticeLayout origen, VerticeLayout destino, Ruta rutaAsociada, Color color) {
		this.offset = origen.DIAMETRO / 2;

		this.rutaAsociada = rutaAsociada;
		this.formatoLinea = new BasicStroke();
		this.color = color;
		this.origen = origen;
		this.destino = destino;
		this.linea = new Line2D.Double(origen.getCoordenadaX() + offset, origen.getCoordenadaY() + offset,
				destino.getCoordenadaX() + offset, destino.getCoordenadaY() + offset);
	}

	public Ruta getRutaAsociada() {
		return rutaAsociada;
	}

	public void setRutaAsociada(Ruta rutaAsociada) {
		this.rutaAsociada = rutaAsociada;
	}

	public Paint getColor() {
		if (this.color == null)
			this.color = new GradientPaint((float) origen.getCoordenadaX() + 10, (float) origen.getCoordenadaY() + 10,
					(Color) destino.getColorBase(), (float) destino.getCoordenadaX() + 10,
					(float) destino.getCoordenadaY() + 10, (Color) origen.getColorBase());
		return color;
	}

	public VerticeLayout getOrigen() {
		return origen;
	}

	public void setOrigen(VerticeLayout origen) {
		this.origen = origen;
	}

	public VerticeLayout getDestino() {
		return destino;
	}

	public void setDestino(VerticeLayout destino) {
		this.destino = destino;
	}

	public Shape getLinea() {
		return linea;
	}

	public void setLinea(Shape linea) {
		this.linea = linea;
	}

	public Stroke getFormatoLinea() {
		return formatoLinea;
	}

	public void setFormatoLinea(Stroke formatoLinea) {
		this.formatoLinea = formatoLinea;
	}

	public void setColor(Paint color) {
		this.color = color;
	}

	public void update() {
		this.linea = new Line2D.Double(origen.getCoordenadaX() + offset, origen.getCoordenadaY() + offset,
				destino.getCoordenadaX() + offset, destino.getCoordenadaY() + offset);
	}

}
