{-
Ejercicio 9 - Practica N°2
Utilizando la tecnica Decrease & Conquer, diseñe un algoritmo para encontrar el indice del mayor
elemento de una secuencia de n enteros positivos. Piense en una alternativa a este algoritmo diseñado
utilizando Fuerza Bruta, y compare implementaciones para estos dos algoritmos en Haskell
Realice ademas el analisis correspondiente para calcular cuantas comparaciones de elementos son
realizadas por ambos algoritmos en el peor caso.
-}


indexOfMax :: [Int] -> [Int]
indexOfMax [] = []
indexOfMax [x] = [x]
indexOfMax (x:xs) = maximumAux [x] (indexOfMax xs)

maximumAux :: [Int] -> [Int] -> [Int]
maximumAux xs ys = [maximum (xs++ys)]

index :: Int -> [Int] -> Int  
index n [] = 0
index n (x:xs) = if (n == x) then 1 else 1 + index n xs

mainFuncion :: [Int] -> Int
mainFuncion xs = index (head (indexOfMax xs)) xs

fuerzaBrutaVersion :: [Int] -> Int
fuerzaBrutaVersion [x] = x
fuerzaBrutaVersion (x:y:xs) = if x>y then fuerzaBrutaVersion (x:xs) else fuerzaBrutaVersion (y:xs)

mainFuerzaBruta :: [Int] -> Int
mainFuerzaBruta xs = index (fuerzaBrutaVersion xs) xs

merge :: (Ord a, Eq a) => [a] -> [a] -> [a]
merge xs [] = xs
merge [] ys = ys
merge (x:xs) (y:ys) = if x < y then x : merge xs (y:ys) else y : merge (x:xs) ys

mergeSort :: (Ord a,Eq a) => [a] -> [a]
mergeSort [] = []
mergeSort [x] = [x]
mergeSort (x:xs) = merge [x] (mergeSort xs)   

{-Ambos algoritmos resuelve el problema realizando n comparaciones en el peor de los casos-}