
public class busqueda{

	S s; // State
	P sp; // StateProblem


	public S dfs(){
		Stack<S> s = new Stack<>();
		LinkedList<S> repetidos = new Linkedlist<>();
		S goal = null;
		boolean success = false;
		s.add(sp.initialState());
		repetidos.add(sp.initialState())

		while(!s.isEmpty() && !success)
		S current = s.peek();
		if(aux.isSucces()){
			goal = current;
			success = True;
		} else{
			List<S> succesors = sp.getSuccesors(current);
			for(S se : succesors){
				if(! repetidos.contains(se)){
					s.add(se);
					repetidos.add(se);
				}
			}
		}
	}
	return goal;
}