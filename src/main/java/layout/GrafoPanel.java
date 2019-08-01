package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import dominio.Planta;
import dominio.Ruta;
import estructuras.Arista;
import estructuras.Vertice;
import gestores.GestorPlanta;
import gestores.GestorRuta;

public class GrafoPanel extends JPanel {

	private int xRepintado = 0;
	private int yRepintado = 0;
	private VerticeLayout verticeSeleccionado = null;
	private Boolean arrastrando = false;

	private List<VerticeLayout> vertices = new ArrayList<>();
	private List<AristaLayout> aristas = new ArrayList<>();

	private static final GrafoPanel INSTANCE = new GrafoPanel();

	public static GrafoPanel getInstance() {
		return INSTANCE;
	}

	GrafoPanel() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {

				VerticeLayout v = clicEnUnNodo(event.getPoint());
				if (v != null) {
					verticeSeleccionado = v;

					if (verticeSeleccionado.getColor() == Color.BLUE) {
						verticeSeleccionado.setColor(Color.CYAN);
					} else {
						verticeSeleccionado.setColor(new Color(0XEBDE34));
					}

					actualizarPosicionVertice(verticeSeleccionado, event.getPoint());
				}

			}

			public void mouseReleased(MouseEvent event) {
				if (verticeSeleccionado != null) {

					verticeSeleccionado.setColor(verticeSeleccionado.getColorBase());
					actualizarPosicionVertice(verticeSeleccionado, event.getPoint());
					actualizarAristas();
					//inicializarAristas();
				}
				verticeSeleccionado = null;
				arrastrando = false;
			}

		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent event) {
				if (verticeSeleccionado != null) {
					actualizarPosicionVertice(verticeSeleccionado, event.getPoint());
					actualizarAristas();
					//inicializarAristas();
				}
			}
		});
	}

	public List<VerticeLayout> getVertices() {
		return vertices;
	}

	public List<AristaLayout> getAristas() {
		return aristas;
	}

	public void inicializarVertices() {
		vertices.clear();

		GestorPlanta gestorPlanta = GestorPlanta.getInstance();

		ArrayList<Planta> listaPlantas = new ArrayList<Planta>(gestorPlanta.getListaPlantas().values());

		int posicionY = 100;
		int posicionX = 0;
		int i = 0;
		// Color c = null;

		for (Planta p : listaPlantas) {
			i++;
			posicionX += 60;

			/* SETEA LA POSICION EN Y */
			if (i % 2 == 0) {
				posicionY = 100;
				// c = Color.BLUE;
			} else {
				posicionY = 200;
				// c = Color.RED;
			}

			VerticeLayout v = new VerticeLayout(posicionX, posicionY, p);
			v.setId(p.getId());
			v.setNombre(p.getNombre());
			vertices.add(v);
		}

		getInstance().repaint();
	}

	public void inicializarAristas() {
		aristas.clear();

		GestorRuta gestorRuta = GestorRuta.getInstance();

		ArrayList<Ruta> listaRutas = new ArrayList<Ruta>(gestorRuta.getListaRutas());

		for (Ruta rut : listaRutas) {

			VerticeLayout verticeOrigen = vertices.stream().filter(v -> v.getNombre() == rut.getOrigen().getNombre())
					.findFirst().get();
			VerticeLayout verticeDestino = vertices.stream().filter(v -> v.getNombre() == rut.getDestino().getNombre())
					.findFirst().get();

			AristaLayout a = new AristaLayout(verticeOrigen, verticeDestino, rut, Color.RED);
			aristas.add(a);
		}

		getInstance().repaint();
	}
	
	public void actualizarAristas() {
		for(AristaLayout a: aristas) {
			a.update();
		}
		
		getInstance().repaint();
	}

	private void actualizarPosicionVertice(VerticeLayout v, Point puntoNuevo) {
		repaint();

		xRepintado = puntoNuevo.x;
		yRepintado = puntoNuevo.y;

		v.setCoordenadaX(xRepintado);
		v.setCoordenadaY(yRepintado);
		v.update();

		repaint();
	}

	private void actualizarColorVertice(VerticeLayout v, Color c) {
		v.setColor(c);
		v.setColorBase(c);
		v.update();
	}

	private void actualizarColorArista(AristaLayout a, Color c) {
		a.setColor(c);
		a.update();
	}

	private VerticeLayout clicEnUnNodo(Point p) {
		for (VerticeLayout v : this.getVertices()) {
			if (v.getNodo().contains(p)) {
				return v;
			}
		}
		return null;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.dibujarAristas(g2d);
		this.dibujarVertices(g2d);
	}

	private void dibujarVertices(Graphics2D g2d) {
		for (VerticeLayout v : this.getVertices()) {
			g2d.setPaint(v.getColor());
			g2d.drawString(v.etiqueta(), v.getCoordenadaX() + 25, v.getCoordenadaY() + 25);
			g2d.setPaint(v.getColor());
			g2d.fill(v.getNodo());
		}
	}

	private void dibujarAristas(Graphics2D g2d) {
		for (AristaLayout a : this.getAristas()) {
			int puntoMedioX = (int) (a.getOrigen().getCoordenadaX() + a.getDestino().getCoordenadaX()) / 2;
			int puntoMedioY = (int) (a.getOrigen().getCoordenadaY() + a.getDestino().getCoordenadaY()) / 2;
			Ruta ruta = a.getRutaAsociada();

			g2d.setPaint(a.getColor());
			g2d.setStroke(a.getFormatoLinea());
			g2d.drawString(ruta.getDistancia() + " [km]", puntoMedioX + 20, puntoMedioY + 20);
			g2d.drawString(ruta.getPesoMaximo() + " [Tn]", puntoMedioX + 20, puntoMedioY + 33);
			g2d.drawString(ruta.getDuracion() + " [min]", puntoMedioX + 20, puntoMedioY + 46);
			g2d.draw(a.getLinea());
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(900, 400);
	}

	public void nodoNecesitaInsumo(Planta p) {
		for (VerticeLayout v : vertices) {
			if (v.getPlantaAsociada().getId() == p.getId()) {
				this.actualizarColorVertice(v, Color.ORANGE);
			}
		}
	}

	public void repintarVertices() {
		for (VerticeLayout v : vertices) {
			this.actualizarColorVertice(v, Color.BLUE);
		}
	}
	
	public void repintarAristas() {
		for (AristaLayout a : aristas) {
			this.actualizarColorArista(a, Color.RED);
		}
	}
	
	public AristaLayout getArista(Ruta r) {
		for(AristaLayout a: this.aristas) {
			if(a.getRutaAsociada() == r) {
				return a;
			}
		}
		return null;
	}
	
	public void pintarRuta(List<Vertice> listaVertices) {
		this.repintarAristas();
		GestorRuta gr = GestorRuta.getInstance();
		
		System.out.println("Listavertices size:" + listaVertices.size());
		
		for(int i = 0; i< listaVertices.size()-1 ; i++) {
			Ruta r = gr.getRuta(listaVertices.get(i).getValor(), listaVertices.get(i+1).getValor());
			this.actualizarColorArista(this.getArista(r), Color.GREEN);
			System.out.println("Pintar ruta i = " + i);
		}
	}
}
