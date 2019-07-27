package layout;

import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

public class AristaLayout {
    
	private VerticeLayout origen;
    private VerticeLayout destino;
    private Shape linea;
    private Stroke formatoLinea;
    private Paint color;

    public AristaLayout() {
    }
    
    public Paint getColor() {
        if(this.color==null) this.color = new GradientPaint(origen.getCoordenadaX() + 10,origen.getCoordenadaY() + 10,destino.getColorBase(),destino.getCoordenadaX() + 10, destino.getCoordenadaY() + 10,origen.getColorBase());
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
    
    

}
