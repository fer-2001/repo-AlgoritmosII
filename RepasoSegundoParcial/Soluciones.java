// Suponiendo que nuestro objetivo es incorporar al procesador la mayor cantididad de procesos
// y además, recuperar o reutilizar el tiempo limite de procesos que no utilicen la totalidad del mismo
// para poder incorporar procesos los cuales no podrian ejecutarse ya que su tiempo limite es menor a la duracion del proceso 


// Se construye una clase proceso
public class proceso{

	int duracion;
	int tiempoLimite;
	info informacionProceso // Se agrega este atributo para darse a entender que en un proceso es más que un tiempo y duracion


	public proceso(){
		duracion = 0;
		tiempoLimite = 0;
	}

	public proceso (int duracion, int tiempoLimite){
		this.duracion = duracion;
		this.tiempoLimite = tiempoLimite;
	}

	public int getTiempo(){
		return tiempoLimite;
	}
	public int getDuracion(){
		return duracion;
	}

	public void setTiempoExtra(int tiempoAgregado){
		tiempoLimite = tiempoLimite + tiempoAgregado;
	}

	public int getDiferenciaTiempo(){
		return (duracion - tiempoLimite) ;
	}

	public boolean esEjecutable(){
		return (duracion < tiempoLimite);
	}


    public int compareTo(Objeto o) {
        if (this.getTiempo() > o.getTiempo()) {
            return 1;
        }else if (this.getTiempo() < o.getTiempo())
        {
            return  -1 ;
        } else { return 0 ; }
    }

}

// A continuacion se define la clase que contiene los metodos para maximizar los procesos 
// Se asume que el procesador y procesow son un arraylist y ademas los procesos ya vienen dados.

public class optimizador{

	ArrayList<proceso> procesos;
	ArrayList<proceso> procesador;

	public optimizador(proceso[] procesos, proceso[] procesador){
		this.procesos = procesos;
		this.procesador = procesador;
	}

	public proceso[] maximizadorProcesos(proceso[] procesos, proceso[] procesador){
		procesos.sort();
		int tiempoSobrante = 0;
		ArrayList<proceso> procesosNoEjecutados = new ArrayList<>();
		for(int i = 0; i < procesos.size() ; i++){
			if( (procesos.get(i)).esEjecutable()){
				procesador.add(procesos.get(i));
				tiempoSobrante += (procesos.get(i)).getTiempo() - (procesos.get(i)).getDuracion();
			}
			else{
				procesosNoEjecutados.add(procesos.get(i)) ;
			}
		}

		if(tiempoSobrante > 0){
			for(int i = 0; i < procesosNoEjecutados.size() ; i++){
				proceso aux = procesosNoEjecutados.get(i);
				int incrementadorTiempo = aux.getDiferenciaTiempo();
				if(tiempoSobrante > incrementadorTiempo){
					aux.setTiempoExtra(incrementadorTiempo);
					tiempoSobrante = tiempoSobrante - incrementadorTiempo;
					procesador.add(aux);
				}
			}
		}

	}



}
 // Esta solucion no es optima, ya que puede elgir un proceso con mayor necesidad de tiempo, que dos procesos con la misma necesidad de tiempo
// Demostracion de que no es optima hecha en papel. 
// Tiempo de ejecucion O(n^2)