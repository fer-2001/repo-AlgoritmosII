module addressBook


sig Name{}
sig Address{}

sig AddressBook{
	names: set Name, 
    addr:names -> Address
}

fact  domAddrIsNames{
	all b:AddressBook | b.addr.Address = b.names
}

fact addrIsFuncional{
	all b: AddressBook | all n: b.names | one n.(b.addr)
}
//para todo nomre y direccion existe un book a la q pertenecen 
/*fact{
all n:Name,a:Address | some b:AddressBook | n in b.names and (n->a) in b.addr
}*/

//all n,a name adres some b | n in b.names and (n->a) in b.addr
//fact allNameAddressInBook{
	//all b: AddressBook , one n:Name, one a: Address | n in b.names and (n->a) in b.addr	
//}

fact bookNotEmpty{
	all b:AddressBook | #b.names >0 and #b.addr > 0
}

pred addresAdd(b,b':AddressBook, n:Name, a:Address){
	b'.names = b.names + n
	b'.addr = b.addr ++ (n->a)
}
	
assert checkAdd{
	all b,b':AddressBook, n:Name, a:Address | !n in b.names 
	and !(n->a) in b.addr and
      addresAdd[b,b',n,a] implies n in b'.names
	and (n->a) in b'.addr
}

pred addressElim(b,b':AddressBook, n:Name, a:Address){
	b'.addr = b.addr - (n->a)
}
pred ElimName(b,b':AddressBook, n:Name){
	addressElim[]
	b'.names = b.names - n
}

assert checkElimName{
	all b,b':AddressBook, n:Name| n in b.names and 
	addressElimName[b,b',n,a] implies
	!(n in b'.names )
}

assert checkElim{
	all b,b':AddressBook, n:Name, a:Address | n in b.names and 
	(n->a) in b.addr and addressElim[b,b',n,a] implies
	(n in b'.names and (n->a) !in b'.addr)
}

assert checkAddRm{
	all b,b':AddressBook, n:Name, a:Address | !n in b.names 
	and !(n->a) in b.addr and
      addressAdd[b,b',n,a] and addressElim[b,b',n,a] implies b = b'
}

fun addressConsult(n:Name, b:AddressBook) : Address{
	n.(b.addr)
}

assert checkOverWriteAddress{
	all b,b':AddressBook, n:Name, a,a':Address  | n in b.names and
	(n->a) in b.addr and addressAdd[b,b',n,a'] implies !(n->a) in b.addr and
	(n->a') in b.addr
}

run addressConsult for 3 but exactly 1 AddressBook
check checkOverWriteAddress for 3 but exactly 1 AddressBook
check checkAdd for 3 but exactly 1 AddressBook
check checkElim for 3 but exactly 1 AddressBook
check checkAddRm for 3 but exactly 1 AddressBook 

run show for 3 but exactly 1 AddressBook
pred show[] {}

//check busca un caso que viole lo q me escribi en el assert
//run me da todas las instancias que cumple lo escrito en el predicado especifico
//predicado para todo esto, analizo la validez de ese predicado
//una fuction me da una instancia que cumple lo q escribi pero no me muestra lo que devuelve
//reestricciones sobre el modelo son con hechos/facts
//implies = entonces implica

