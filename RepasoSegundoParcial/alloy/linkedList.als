module linkedList

/*
* Modelo que caracteriza la definición de listas simplemente encadenadas
* Cada lista corresponde a una lista enlazada de nodos, accedida desde un 
* nodo ‘header’ definido en la lista, y enlaces entre nodos. 
* El modelo cuenta con los siguientes predicados:
* 	- Sorted, captura las listas ordenadas crecientemente
* 	- Count, operación que cuenta la cantidad de repeticiones de un elemento en 
* 	una lista
* 	- Contains, operación de pertenencia, dada una lista y un elemento decide si 
* 	el elemento pertenece o no a la lista.
* También se incluye una aserción que expresa la consistencia entre Contains y 
* Count. Y un comando run {Sorted} para explorar listas ordenadas crecientemente.
**/


/*
* Define una lista capturando el primer elemento de la misma
*/
sig List {
    header: lone Node
}

/*
* Representa un nodo de la lista con la información del siguiente nodo (si tiene) 
* en el campo ‘link’ y el valor del nodo en el campo ‘elem
*/
sig Node {
    link: lone Node,
    elem: one Int
}

/*
* Para una lista ‘This’ chequea si la misma está ordenada de manera ascendente
*/
pred Sorted[This: List] {
    all n: This.header.*link |one n.link and n.elem <= n.link.elem 
}

/* Busca instancias de listas que cumplen con el predicado sorted */
run Sorted for 3 but exactly 1 List, exactly 3 Node

/*
* Operación que cuenta la cantidad de apariciones de un elemento ‘x’ en la lista 
* ‘This’. 
* ‘result’ almacena esa cantidad
*/
pred Count[This: List, x: Int, result: Int] {
  result = #{n: This.header.*link | n.elem = x}
}

run Count for 3 but exactly 1 List, exactly 3 Node

/*
* Operación de pertenencia. Para una lista ‘This’ y un elemento ‘x’, chequea si
‘x’ pertenece a ‘This’. 
*/
pred Contains[This: List, x: Int] {
  all n: This.header.*link | n.elem = x
}

run Contains for 3 but exactly 1 List, exactly 3 Node

/*
* Un elemento pertenece a una lista si y sólo si
* su “count” en la lista es positivo 
*/
assert ContainsCountConsistency {
  all l: List, k: Int {
    Contains[l, k] iff
    (some count: Int {
      count >= 1 and
      Count[l, k, count]
    })
  }
}

pred show[]{}
run show for 3 but exactly 1 List, 3 Node

check ContainsCountConsistency
