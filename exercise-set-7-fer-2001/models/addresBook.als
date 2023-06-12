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
	all b: AddressBook | all n: b.names | one n.(b.addr) // Como maximo, un nombre y una dirrecion aparecen una vez en un book. No puede
// Aparecer dos veces el mismo nombre en el mismo book, lo mismo con la dirrecion
}


pred addAddres(b, b': AddressBook, n:Name, a:Address){
!(n in b.names) implies
	b'.names = b.names + n
	b'.addr = b'.addr ++ (n->a) 
}

assert Add{
all b,b': AddressBook, n:Name, a:Address|
!(n in b.names and (n->a in b.addr)) and
addAddres[b,b',n,a] implies 
(n->a in b'.addr and n in b'.names)
// Checkeo que primero todos los n de Names esten en Book, lo mismo para la relacion de dichos n con las dirreciones
// Llamo a addAddres y si nuestro agregar es correcto
// Todos lo n que se relacionan con a están en el campo addr de b' y todos los nombres estan en b'.names
}

fun lookup (b: AddressBook, n: Name): set Address{
n.(b.addr)
}

pred delAddres(b, b': AddressBook, n:Name, a:Address){
(n in b.names and (n->a) in b.addr) implies
	b'.names = b.names - n
  	b'.addr = b.addr - (n->a) 
}

assert Del{
all b,b': AddressBook, n:Name, a:Address|
(n in b.names and (n->a in b.addr)) and
delAddres[b,b',n,a] implies 
!(n->a in b'.addr) and !(n in b'.names)
}

assert delAdd{
all b,b': AddressBook, n:Name, a:Address|
!n in b.names  and !(n->a) in b.addr and
addAddres[b,b',n,a] and delAddres[b,b',n,a] implies
b = b'

}

assert checkOverWriteAddress{
	all b,b':AddressBook, n:Name, a,a':Address  | n in b.names and
	(n->a) in b.addr and addAddres[b,b',n,a'] implies !(n->a) in b.addr and
	(n->a') in b.addr
}

check delAdd


pred show[] { 
}

check Del
run lookup
run show
run delAddres
