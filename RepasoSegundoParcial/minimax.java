
public class busqueda{


	private int maxCantNodos; // Atributo que limita la cantidad de nodos a visitar
	private P sp // State problem

	public int minMax(S state, int alfa, int beta, int nodosVisitados){
		if (state.isEnd() || nodosVisitados >= maxCantNodos) {
			return state.value();
		} else{
			List<S> succ = sp.getSuccesors(state);
			int i = 0;
			if(s.isMax()){
				for(S se : succ){
					nodosVisitados++;
					if(alfa >= beta){
						break
					}
					alfa = Math.max(se, alfa, beta, nodosVisitados);
				}
				return alfa;
			} else {
				for(S se : succ){
					nodosVisitados++;
					if(alfa >= beta){
						break
					}
					beta = Math.min(se, alfa, beta, nodosVisitados);
				}
				return beta;
			}
		}
	}


}