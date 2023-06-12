module secureLab

/*
* Modelo que caracteriza el protocolo de ingreso a salas, por parte de personal
* de un instituto de investigación. El personal consta de investigadores y
* administrativos. Existe una sala especial, un laboratorio seguro, cuyo acceso
* está vedado a administrativos. El acceso a salas se realiza mediante
* llaves, asociadas a miembros del personal, y que abren específicamente ciertas
* salas.
*/

/*
* Salas del laboratorio.
*/
sig Room { }

/*
* secure_lab es una sala específica del laboratorio.
*/
one sig secure_lab extends Room { }

/*
* Cada persona posee un conjunto de llaves.
*/
abstract sig Person {
  owns : set Key
}

/*
* Las personas pueden ser empleados administrativos (Admin) o 
* investigadores (Researcher) del instituto.
*/
sig Admin extends Person {}
sig Researcher extends Person {}

/*
* Cada llave (Key) cuenta con la información de qué miembro del personal está 
* autorizado a utilizarla (authorizes) y qué salas (Room) puede abrir dicha
* llave (opens). 
*/
sig Key {
  authorizes : one Person,
  opens: set Room
}

/* 
* Ninguna persona tiene llaves que autorizan a otras personas.
*/
fact PersonOwnsOwnKeys {
  all p: Person, k: Key | k in p.owns iff p = k.authorizes 
}

/*Sólo los investigadores tienen llave del secure_lab */
fact OnlyReseachersHaveSecureLabKeys {
	secure_lab in Researcher.owns.opens and !secure_lab in Admin.owns.opens
}

/*
* CanEnter determina si una persona p puede ingresar a una sala r 
*/
pred CanEnter(p: Person, r: Room) {
	r in p.owns.opens
}

run CanEnter

/*
* Sólo investigadores pueden acceder a secure_lab.
*/
assert OnlyResearchersInSecureLab {
  all p : Person | CanEnter[p, secure_lab] implies p in Researcher
}

check OnlyResearchersInSecureLab 
pred show[]{}
run show
