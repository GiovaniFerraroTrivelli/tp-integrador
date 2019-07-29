package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dominio.Planta;
import dominio.Ruta;
import gestores.GestorPlanta;
import gestores.GestorRuta;

public class GrafoPanel extends JPanel {

	// private Queue<Color> colaColores;
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
		/*
		 * this.colaColores = new LinkedList<Color>(); this.colaColores.add(Color.RED);
		 * this.colaColores.add(Color.BLUE); this.colaColores.add(Color.ORANGE);
		 * this.colaColores.add(Color.CYAN);
		 */

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {

				VerticeLayout v = clicEnUnNodo(event.getPoint());
				if (v != null) {
					verticeSeleccionado = v;
					verticeSeleccionado.setColor(Color.CYAN);
					actualizarVertice(verticeSeleccionado, event.getPoint());
				}

			}

			public void mouseReleased(MouseEvent event) {
				System.out.println("mouseReleased: " + event.getPoint());
				if (verticeSeleccionado != null) {
					verticeSeleccionado.setColor(Color.BLUE);
					actualizarVertice(verticeSeleccionado, event.getPoint());
					inicializarAristas();
				}
				verticeSeleccionado = null;
				arrastrando = false;
			}

		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent event) {
				if (verticeSeleccionado != null) {
					actualizarVertice(verticeSeleccionado, event.getPoint());
					inicializarAristas();
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

		Runnable r = () -> {

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

				VerticeLayout v = new VerticeLayout(posicionX, posicionY, Color.BLUE);
				v.setId(p.getId());
				v.setNombre(p.getNombre());
				vertices.add(v);
			}
			try {
				SwingUtilities.invokeAndWait(() -> {
					getInstance().repaint();
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread hilo = new Thread(r);

		hilo.start();
	}

	public void inicializarAristas() {
		aristas.clear();

		Runnable r = () -> {
			GestorRuta gestorRuta = GestorRuta.getInstance();

			ArrayList<Ruta> listaRutas = new ArrayList<Ruta>(gestorRuta.getListaRutas());

			for (Ruta rut : listaRutas) {

				VerticeLayout verticeOrigen = vertices.stream()
						.filter(v -> v.getNombre() == rut.getOrigen().getNombre()).findFirst().get();
				VerticeLayout verticeDestino = vertices.stream()
						.filter(v -> v.getNombre() == rut.getDestino().getNombre()).findFirst().get();

				AristaLayout a = new AristaLayout(verticeOrigen, verticeDestino, rut.getDistancia(), Color.RED);
				aristas.add(a);
			}

			/* sacado del codigo de martin, no se bien que hace aun */
			try {
				SwingUtilities.invokeAndWait(() -> {
					getInstance().repaint();
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}

		};

		Thread hilo = new Thread(r);
		hilo.start();
	}

	private void actualizarVertice(VerticeLayout v, Point puntoNuevo) {
		int OFFSET_X = v.getNombre().length() * 20;
		int OFFSET_Y = 31;

		repaint(xRepintado, yRepintado, v.DIAMETRO + OFFSET_X, v.DIAMETRO + OFFSET_Y);

		xRepintado = puntoNuevo.x;
		yRepintado = puntoNuevo.y;

		v.setCoordenadaX(xRepintado);
		v.setCoordenadaY(yRepintado);
		v.update();

		repaint(xRepintado, yRepintado, v.DIAMETRO + OFFSET_X, v.DIAMETRO + OFFSET_Y);
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
		dibujarAristas(g2d);
		dibujarVertices(g2d);
	}

	private void dibujarVertices(Graphics2D g2d) {
		System.out.println(this.getVertices());
		for (VerticeLayout v : this.getVertices()) {
			g2d.setPaint(Color.BLUE);
			g2d.drawString(v.etiqueta(), v.getCoordenadaX() + 25, v.getCoordenadaY() + 25);
			g2d.drawString("[" + v.getCoordenadaX().toString() + ", " + v.getCoordenadaY().toString() + "]",
					v.getCoordenadaX() + 35, v.getCoordenadaY() + 35);
			g2d.setPaint(v.getColor());
			g2d.fill(v.getNodo());
		}
	}

	private void dibujarAristas(Graphics2D g2d) {
		for (AristaLayout a : this.getAristas()) {
			g2d.setPaint(a.getColor());
			g2d.setStroke(a.getFormatoLinea());
			// g2d.drawString("[km]", a.getDestino().getCoordenadaX() -
			// a.getOrigen().getCoordenadaX(),
			// a.getDestino().getCoordenadaY()-a.getOrigen().getCoordenadaY());
			g2d.draw(a.getLinea());
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(900, 400);
	}
}
