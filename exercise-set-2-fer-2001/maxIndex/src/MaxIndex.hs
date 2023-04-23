module MaxIndex where

{-
Ejercicio 9 - Practica N°2
Utilizando la tecnica Decrease & Conquer, diseñe un algoritmo para encontrar el indice del mayor
elemento de una secuencia de n enteros positivos. Piense en una alternativa a este algoritmo diseñado
utilizando Fuerza Bruta, y compare implementaciones para estos dos algoritmos en Haskell
Realice ademas el analisis correspondiente para calcular cuantas comparaciones de elementos son
realizadas por ambos algoritmos en el peor caso.
-}

-- maxindex retorna la posicion del maximo elemento de la lista
-- -- Solucion Decrease 
maxindexDec :: [Int] -> Int
maxindexDec xs = index (head (indexOfMax xs)) xs - 1 

-- Solucion fuerza bruta
maxindexFB :: [Int] -> Int
maxindexFB xs =  index (fuerzaBrutaVersion xs) xs - 1

indexOfMax :: [Int] -> [Int]
indexOfMax [] = []
indexOfMax [x] = [x]
indexOfMax (x:xs) = maximumAux [x] (indexOfMax xs)

maximumAux :: [Int] -> [Int] -> [Int]
maximumAux xs ys = [maximum (xs++ys)]

index :: Int -> [Int] -> Int  
index n [] = 0
index n (x:xs) = if (n == x) then 1 else 1 + index n xs

fuerzaBrutaVersion :: [Int] -> Int
fuerzaBrutaVersion [x] = x
fuerzaBrutaVersion (x:y:xs) = if x>y then fuerzaBrutaVersion (x:xs) else fuerzaBrutaVersion (y:xs)


{-Ambos algoritmos resuelve el problema realizando n comparaciones en el peor de los casos-}

