{-

Ejercicio 8 - Practica N°2: Utilizando la tecnica Decrease & Conquer, diseñe un algoritmo para encontrar los elementos mayor
y menor de una secuencia de n enteros positivos. Implemente su algoritmo en Haskell.

-}

mayorMenorSeq :: [Int] -> (Int,Int)
mayorMenorSeq [x] = (x,x)
mayorMenorSeq (x:xs) = ( (min x (fst (aux))),(max x (snd (aux)))) 
                   where aux = mayorMenorSeq xs