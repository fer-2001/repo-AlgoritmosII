module herenciaJava

/*
* Modelo que caracteriza la definición de clases, y la jerarquía de herencia en
* Java. Cada clase en Java, con excepción de Object, hereda de exactamente una
* clase, y la relación de herencia debe cumplir con algunas restricciones
* adicionales: debe ser acíclica, y la raíz de la jerarquía debe ser la clase
* Object.  Este modelo captura la jerarquía de herencia de programas Java, con
* las restricciones correspondientes. La caracterización de clases sólo describe
* la información de herencia (clase padre de cada clase), y ésta se restringe
* para modelar jerarquías de herencia válidas. El modelo incluye algunas
* propiedades esperadas de la jerarquía de herencia en Java, a través de
* aserciones. 
*/

/*
* Captura clases Java. Una clase Java puede heredar de otra clase. 
*/
sig Class {
  ext: lone Class
}


/*
* Object es una clase Java particular.
*/ 
one sig Object extends Class { }


/*
* Object no hereda de ninguna clase.  
*/
fact ObjectNoExt {
    no Object.ext
}

/*
* Ninguna clase es sub-clase de sí misma.
*/
fact Acyclic {
    all c: Class | c !in c.^ext}


/*
*  Toda clase, excepto Object, tiene a Object como ancestro. 
*/
fact AllExtObject { 
 all c:Object, c1:Class - Object | c in c1.*ext
}

/*
* Object es la única clase que no hereda de otras clases.
*/
assert NoExtIsObject {
    all c: Class | no c.ext implies c = Object
}

check NoExtIsObject for 10 


/*
* Todo par de clases excepto Object tienen al menos un ancestro común.
*/
assert AtLeastOneInCommon {
	all disj a, b: Class - Object | some (a.^ext & b.^ext)
}

check AtLeastOneInCommon for 10  
pred show[]{}
run show for 3 but exactly 6 Class

