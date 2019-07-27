package layout;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import gestores.GestorPlanta;
import dominio.Planta;

public class GrafoController {

	private GrafoPanel panel;
	
	/*private TareaLogica logicaTareas;
	private ProyectoLogica logicaProyecto;*/
		
	public GrafoController(GrafoPanel listener) {
		this.panel = listener;
		/*this.logicaTareas = new TareaLogicaDefault();
		this.logicaProyecto = new ProyectoLogicaDefault(); */
	}
	
	public void inicalizarVertices() {
		Runnable r = () -> {
			
			GestorPlanta gestorPlanta = GestorPlanta.getInstance();
			
			ArrayList<Planta> listaPlantas = new ArrayList<Planta>(gestorPlanta.getListaPlantas().values());
			
			int posicionY = 100;
			int posicionX = 0;
			int i = 0;
			//Color c = null;
			
			for(Planta p : listaPlantas){
				i++;
				posicionX +=30; 
				
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
				panel.agregar(v);
			}
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.repaint();
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
}
