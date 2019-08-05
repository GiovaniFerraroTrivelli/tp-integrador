package gestores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dominio.Ruta;
import dominio.Planta;

public class GestorRuta {
	private static final GestorRuta INSTANCE = new GestorRuta();
	private ArrayList<Ruta> listaCaminos = new ArrayList<Ruta>();

	private GestorRuta() {
	}

	public static GestorRuta getInstance() {
		return INSTANCE;
	}

	public ArrayList<Planta> getEndingPlantas(Planta planta) {
		ArrayList<Planta> resultado = new ArrayList<Planta>();

		for (Ruta camino : listaCaminos) {
			if (camino.getOrigen() == planta) {
				resultado.add(camino.getDestino());
			}
		}

		return resultado;
	}

	public ArrayList<Planta> getStartingPlantas(Planta planta) {
		ArrayList<Planta> resultado = new ArrayList<Planta>();

		for (Ruta camino : listaCaminos) {
			if (camino.getDestino() == planta) {
				resultado.add(camino.getOrigen());
			}
		}

		return resultado;
	}

	public void deleteRuta(Planta origen, Planta destino) {
		for (Ruta camino : listaCaminos) {
			if (camino.getOrigen() == origen && camino.getDestino() == destino) {
				listaCaminos.remove(camino);
				return;
			}
		}
	}

	public ArrayList<Ruta> getListaRutas() {
		return this.listaCaminos;
	}

	public Ruta crearRuta(Planta origen, Planta destino) {
		Ruta camino = new Ruta(origen, destino);
		listaCaminos.add(camino);
		return camino;
	}

	public int getPageRank(Planta p) {
		int pageRank = 0;

		for (Ruta r : listaCaminos) {
			if (r.getDestino() == p /* || r.getOrigen() == p1 */) {
				pageRank++;
			}
		}

		return pageRank;
	}

	public Ruta getRuta(Planta origen, Planta destino) {
		for (Ruta r : this.getListaRutas()) {
			if (r.getOrigen() == origen && r.getDestino() == destino) {
				return r;
			}
		}
		return null;
	}

	public List<List<Planta>> caminos(Planta origen, Planta destino) {
		List<List<Planta>> listaCaminos = new ArrayList<List<Planta>>();
		List<Planta> marcados = new ArrayList<Planta>();
		marcados.add(origen);
		buscarCaminosAux(origen, destino, marcados, listaCaminos);
		return listaCaminos;
	}

	public ArrayList<Integer> getInfoRuta(List<Planta> listaPlantas) {
		ArrayList<Integer> infoRuta = new ArrayList<Integer>();

		Integer distanciaTotal = 0;
		Integer tiempoTotal = 0;
		Integer pesoMax = this.getRuta(listaPlantas.get(0), listaPlantas.get(1)).getPesoMaximo();

		for (int i = 0; i < listaPlantas.size() - 1; i++) {
			Ruta r = this.getRuta(listaPlantas.get(i), listaPlantas.get(i + 1));
			distanciaTotal += r.getDistancia();
			tiempoTotal += r.getDuracion();

			if (r.getPesoMaximo() < pesoMax)
				pesoMax = r.getPesoMaximo();
		}

		infoRuta.add(distanciaTotal);
		infoRuta.add(tiempoTotal);
		infoRuta.add(pesoMax);

		// infoRuta[0]: distancia total del camino
		// infoRuta[1]: duracion total del viaje
		// infoRuta[2]: peso maximo admitido
		return infoRuta;
	}

	public List<Planta> mejorCaminoConInsumoDistancia(Planta origen, Planta destino,
			ArrayList<Planta> necesitanInsumo) {
		GestorRuta g = GestorRuta.getInstance();

		if (g.getListaRutas().size() == 0)
			return null;

		List<List<Planta>> listaCaminos = this.caminos(origen, destino);
		ArrayList<Planta> verticesInsumo = new ArrayList<Planta>();

		for (Planta p : necesitanInsumo) {
			verticesInsumo.add(p);
		}

		for (List<Planta> lista : listaCaminos) {
			if (!lista.containsAll(verticesInsumo)) {
				listaCaminos.remove(lista);
			}
		}

		Comparator<List<Planta>> distanciaComparator = new Comparator<List<Planta>>() {

			@Override
			public int compare(List<Planta> camino1, List<Planta> camino2) {
				return (g.getInfoRuta(camino1).get(0) - g.getInfoRuta(camino2).get(0));
			}

		};

		Collections.sort(listaCaminos, distanciaComparator);
		return listaCaminos.get(listaCaminos.size() - 1);
	}

	public List<Planta> mejorCaminoConInsumoTiempo(Planta origen, Planta destino, ArrayList<Planta> necesitanInsumo) {
		GestorRuta g = GestorRuta.getInstance();

		if (g.getListaRutas().size() == 0)
			return null;

		List<List<Planta>> listaCaminos = this.caminos(origen, destino);
		ArrayList<Planta> verticesInsumo = new ArrayList<Planta>();

		for (Planta p : necesitanInsumo) {
			verticesInsumo.add(p);
		}

		for (List<Planta> lista : listaCaminos) {
			if (!lista.containsAll(verticesInsumo)) {
				listaCaminos.remove(lista);
			}
		}

		Comparator<List<Planta>> tiempoComparator = new Comparator<List<Planta>>() {

			@Override
			public int compare(List<Planta> camino1, List<Planta> camino2) {
				return (g.getInfoRuta(camino1).get(1) - g.getInfoRuta(camino2).get(1));
			}

		};

		Collections.sort(listaCaminos, tiempoComparator);
		return listaCaminos.get(listaCaminos.size() - 1);
	}

	private List<Planta> getPlantasAdyacentes(Planta p) {
		List<Planta> plantasAdyacentes = new ArrayList<Planta>();
		for (Ruta r : this.getListaRutas()) {
			if (r.getOrigen() == p) {
				plantasAdyacentes.add(r.getDestino());
			}
		}
		return plantasAdyacentes;
	}

	private void buscarCaminosAux(Planta origen, Planta destino, List<Planta> marcados, List<List<Planta>> todos) {
		List<Planta> plantasAdyacentes = this.getPlantasAdyacentes(origen);
		List<Planta> copiaMarcados = null;

		for (Planta p : plantasAdyacentes) {
			System.out.println(">> " + p);
			copiaMarcados = marcados.stream().collect(Collectors.toList());

			if (p == destino) {
				copiaMarcados.add(destino);

				todos.add(new ArrayList<Planta>(copiaMarcados));
				System.out.println("ENCONTRO CAMINO " + todos.toString());
			} else {
				if (!copiaMarcados.contains(p)) {
					copiaMarcados.add(p);
					this.buscarCaminosAux(p, destino, copiaMarcados, todos);
				}
			}
		}
	}

	public int flujoMaximo() {
		for(Ruta r : this.getListaRutas()) {
			r.setBackupPesoMaximo(r.getPesoMaximo());
		}
		
		int flujoMaximo = 0;
		GestorPlanta gestorPlanta = GestorPlanta.getInstance();
		
		List<List<Planta>> listaCaminos = this.caminos(gestorPlanta.getAcopioInicial(), gestorPlanta.getAcopioFinal());

		Comparator<List<Planta>> flujoResidualMaximo = new Comparator<List<Planta>>() {
			@Override
			public int compare(List<Planta> camino1, List<Planta> camino2) {
				GestorRuta gestorRuta = GestorRuta.getInstance();
				return (gestorRuta.getRuta(camino1.get(0), camino1.get(1)).getPesoMaximo()
						- gestorRuta.getRuta(camino2.get(0), camino2.get(1)).getPesoMaximo());
			}

		};

		Collections.sort(listaCaminos, flujoResidualMaximo);

		for (List<Planta> camino : listaCaminos) {
			int flujo = this.flujoResidual(camino);
			flujoMaximo += flujo;
			this.modificarPesoMaximo(camino, flujo);
		}

		for(Ruta r: this.getListaRutas()) {
			r.restaurarPesoMaximo();
		}

		return flujoMaximo;
	}

	private int flujoResidual(List<Planta> camino) {
		Integer flujoResidual = this.getRuta(camino.get(0), camino.get(1)).getPesoMaximo();

		for (int i = 1; i < camino.size() - 1; i++) {
			Ruta r = this.getRuta(camino.get(i), camino.get(i + 1));
			if (r.getPesoMaximo() < flujoResidual)
				flujoResidual = r.getPesoMaximo();
		}

		return flujoResidual;
	}

	private void modificarPesoMaximo(List<Planta> camino, int pesoMaximo) {
		for (int i = 0; i < camino.size() - 1; i++) {
			Ruta r = this.getRuta(camino.get(i), camino.get(i + 1));
			r.setPesoMaximo(r.getPesoMaximo() - pesoMaximo);
		}
	}
}
