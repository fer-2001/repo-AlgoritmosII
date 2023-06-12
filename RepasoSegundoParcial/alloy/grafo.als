module grafo

/*
* Modelo que describe grafos no dirigidos, es decir, grafos en los cuales las 
* transiciones no tienen dirección, o equivalentemente, en los cuales la 
* relación de adyacencia es simétrica. El modelo contiene predicados que 
* capturan las siguientes características:
* - Transitividad (Transitive): un grafo no dirigido es transitivo si su 
* relación de adyacencias es transitiva. 
* Conectividad fuerte (StronglyConnected): un grafo no dirigido es fuertemente
* conexo si existe un camino (no dirigido) entre cada par de nodos distintos 
* del grafo.
* Completitud (Complete): un grafo no dirigido es completo si existe un arco 
* entre cada par de nodos distintos del grafo.
* El modelo contiene además una aserción para comprobar que todo grafo
* no dirigido transitivo y fuertemente conexo es necesariamente completo. 
*/

/* Captura los nodos del grafo. Las adyacencias entre nodos se modelan con un
 * atributo adj de Node, que representa los nodos adyacentes a cada nodo.
 * (Nótese la ausencia de una signatura Graph; ésta es implícita, todos los 
 * átomos de Node son los nodos del grafo. 
 */
sig Node {
	adj : set Node
}

/* El conjunto de nodos del grafo es no vacío */
fact { 
	some Node 
}

/* Fact Undirected: el grafo es no dirigido: su relación de adyacencias es 
 * simétrica. bien
 */
fact Undirected {
	adj = ~adj 
}


/* Transitive: El grafo es transitivo si su relación de adyacencias es 
 * transitiva. bien
 */
pred Transitive {
    all n1, n2, n3: Node | n1->n2 in adj and n2->n3 in adj implies n1-> n3 in adj
}

run Transitive for 3 but exactly 4 Node

/* StronglyConnected: El grafo es fuertemente conexo si existe un camino entre
 * cada par de nodos distintos del grafo.  bien
 */
pred StronglyConnected {
         all n1, n2: Node | n1->n2 in *adj
}

run StronglyConnected for 3 but exactly 8 Node

/* Complete: El grafo es completo si cada par de nodos distintos tiene un arco
 *  entre ellos. 
 */
pred Complete {
	all disj n1, n2: Node | n1->n2 in adj 
}

run Complete for 3 but exactly 6 Node

/* Predicado vacío (true). Útil para observar instancias del modelo. */
run { }

/* CheckComplete: aserción que comprueba que todo grafo no dirigido, transitivo
 * y fuertemente conexo es necesariamente completo.
 */
assert CheckComplete {
  (Transitive and StronglyConnected) implies Complete
}

check CheckComplete
