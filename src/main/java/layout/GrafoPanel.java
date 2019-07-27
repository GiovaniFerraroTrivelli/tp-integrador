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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dominio.Planta;
import dominio.Ruta;
import gestores.GestorPlanta;
import gestores.GestorRuta;

public class GrafoPanel extends JPanel{
	
	//private Queue<Color> colaColores;
    private int xRepintado = 0;
    private int yRepintado = 0;
    private VerticeLayout aristaSeleccionada = null;
    private Boolean arrastrando = false;
    
    private static List<VerticeLayout> vertices = new ArrayList<>();
    private static List<AristaLayout> aristas = new ArrayList<>();
    
    private static final GrafoPanel INSTANCE = new GrafoPanel();
    
    public static GrafoPanel getInstance()
    {
    	return INSTANCE;
    }
    
    GrafoPanel()
    { 
        /*this.colaColores = new LinkedList<Color>();
        this.colaColores.add(Color.RED);
        this.colaColores.add(Color.BLUE);
        this.colaColores.add(Color.ORANGE);
        this.colaColores.add(Color.CYAN);*/

        refreshVertices();
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && !event.isConsumed()) {
                    event.consume();
                    VerticeLayout v = clicEnUnNodo(event.getPoint());
                    if(v != null) {
                    	aristaSeleccionada = v; 
                    	aristaSeleccionada.setColor(Color.CYAN);
                    	actualizarVertice(aristaSeleccionada, event.getPoint());
                    }
                    System.out.println("DOBLE CLICK");
                }
            }

            public void mouseReleased(MouseEvent event) {
               System.out.println("mouseReleased: "+event.getPoint());
               if(aristaSeleccionada != null) {
            	   aristaSeleccionada.setColor(Color.BLUE);
            	   actualizarVertice(aristaSeleccionada, event.getPoint());
               }
               aristaSeleccionada = null;
               arrastrando = false;
            }
            

        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                if(aristaSeleccionada != null) {
                	actualizarVertice(aristaSeleccionada ,event.getPoint());
                } 
            }
        });
    }

	public static void refreshVertices() {
		vertices.clear();
		
		Runnable r = () -> {
			
			GestorPlanta gestorPlanta = GestorPlanta.getInstance();
			
			ArrayList<Planta> listaPlantas = new ArrayList<Planta>(gestorPlanta.getListaPlantas().values());
			
			int posicionY = 100;
			int posicionX = 0;
			int i = 0;
			//Color c = null;
			
			for(Planta p : listaPlantas){
				i++;
				posicionX +=60; 
				
				/*SETEA LA POSICION EN Y*/
				if( i % 2 == 0 ) {
					posicionY = 100;
					//c = Color.BLUE;
				} else {
					posicionY = 200;
					//c = Color.RED;
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
	
	public static void refreshAristas() {
		aristas.clear();
		
		Runnable r = () -> {
			GestorRuta gestorRuta = GestorRuta.getInstance();
			
			ArrayList<Ruta> listaRutas = new ArrayList<Ruta>(gestorRuta.getListaRutas());
			
			for(Ruta rut: listaRutas) {
				
				VerticeLayout verticeOrigen = vertices.stream().filter(v -> v.getNombre() == rut.getOrigen().getNombre()).findFirst().get(); 
				VerticeLayout verticeDestino = vertices.stream().filter(v -> v.getNombre() == rut.getDestino().getNombre()).findFirst().get(); 
				
				AristaLayout a = new AristaLayout();
				a.setOrigen(verticeOrigen);
				a.setDestino(verticeDestino);
				aristas.add(a);
			}
			
			/*sacado del codigo de martin, no se bien que hace aun*/
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
        int OFFSET_X = v.getNombre().length()*20;
        int OFFSET_Y = 31;
        
        repaint(xRepintado-5,yRepintado-5,v.RADIO+OFFSET_X, v.RADIO + OFFSET_Y);
        
        xRepintado = puntoNuevo.x;
        yRepintado = puntoNuevo.y;
        
        v.setCoordenadaX(xRepintado);
        v.setCoordenadaY(yRepintado);
        v.update();
        
        repaint(xRepintado-5,yRepintado-5,v.RADIO+OFFSET_X, v.RADIO + OFFSET_Y);
    }
    
    private VerticeLayout clicEnUnNodo(Point p) {
        for (VerticeLayout v : this.vertices) {
            if (v.getNodo().contains(p)) {
                return v;
            }
        }
        return null;
    }
    
    private void dibujarVertices(Graphics2D g2d) {
    	System.out.println(this.vertices);
        for (VerticeLayout v : this.vertices) {
            g2d.setPaint(Color.BLUE);
            g2d.drawString(v.etiqueta(),v.getCoordenadaX()+25,v.getCoordenadaY()+25);
            g2d.setPaint(v.getColor());
            g2d.fill(v.getNodo());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        for (AristaLayout a : this.aristas) {
            g2d.setPaint(a.getColor());
            g2d.setStroke ( a.getFormatoLinea());
            g2d.draw(a.getLinea());
            //dibujo una flecha al final
            // con el color del origen para que se note
            g2d.setPaint(Color.BLACK);
            Polygon flecha = new Polygon();  
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+7);
            flecha.addPoint(a.getDestino().getCoordenadaX()+20, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+18);
            g2d.fillPolygon(flecha);
            //dibujarFlecha(g2d, ) ????????????????????
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarVertices(g2d);
        dibujarAristas(g2d);
    }

    public Dimension getPreferredSize() {
        return new Dimension(900, 400);
    }
    
    private void dibujarFlecha(Graphics2D g2, Point tip, Point tail, Color color){
        double phi;
        int barb;      
        phi = Math.toRadians(40);
        barb = 20;
        
        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        //System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        for(int j = 0; j < 2; j++){
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - phi;
        }
    }
}
