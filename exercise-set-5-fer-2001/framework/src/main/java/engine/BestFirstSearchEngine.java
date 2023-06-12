package engine;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearchEngine <S extends Comparable<S> & State, P extends StateProblem<S>> implements Engine<S,P> {
    private P sp;
    private List<S> path;

    public BestFirstSearchEngine() {
        path = new LinkedList<S>();
    }


    public BestFirstSearchEngine(P sp) {
        this.sp = sp;
        path = new LinkedList<S>();

    }
    @Override
    public S performSearch() {
        PriorityQueue<S> queue = new PriorityQueue<>();
        LinkedList<S> contenidos = new LinkedList<>();
        contenidos.add(sp.initialState());
        queue.add(sp.initialState());
        boolean found = false;
        S goal = null;
        while (!queue.isEmpty() && !found) {
            S current = queue.poll();
            if (current.isSuccess()) {
                found = true;
                goal = current;
            } else {
                List<S> succs = sp.getSuccessors(current);
                for (S s: succs) {
                    if (!contenidos.contains(s)) { // Control para evitar repetidos
                        queue.add(s);
                        contenidos.add(s);
                    }
                }
            }
        }
        if (!(goal == null)) {
            S s = goal;
            while (!(s == null)) {
                path.add(0,s);
                s = (S)s.getParent();
            }
        }
        return goal;
    }

    @Override
    public List<S> getPath() {
        return path;
    }

    @Override
    public void report() {
        System.out.println("Length of path to state when search finished: " + path.size());
    }

    private int valoration(){
        return 0;
    }
}
