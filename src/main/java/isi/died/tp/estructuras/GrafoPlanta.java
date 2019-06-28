package isi.died.tp.estructuras;

import java.util.List;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;

public class GrafoPlanta extends Grafo<Planta> {
	public void imprimirDistanciaAdyacentes(Planta inicial) {
		List<Planta> adyacentes = super.getAdyacentes(inicial);
		
		for(Planta unAdyacente:adyacentes){
			Arista<Planta>camino=super.buscarArista(inicial, unAdyacente);
			System.out.println("camino de "+inicial.getNombre()+"a "+unAdyacente.getNombre()+"tiene valor de "+camino.getValor());
		}
	}
	
	// a
	public Planta buscarPlanta(Planta inicial, Insumo i, Integer saltos)
	{
        
        if(saltos < 0)
        {
            return null;
        }
        else if(inicial.necesitaInsumo(i))
        {
            return inicial;
        }
        else
        {
            List<Planta> plantas = this.getAdyacentes(inicial);
            for(Planta p: plantas)
            {
                return buscarPlanta(p,i,saltos-1);
            }
        }
        
        return null;
    }
	
	// b
	//public Planta buscarPlanta(Planta inicial, Insumo i) {
		
	//}
	// c
	//public Planta buscarPlanta (Insumo i)
	//{
	//}
}
