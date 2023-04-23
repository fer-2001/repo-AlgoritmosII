
-- Precondicion: Las listas de enteros no tienen repetidos (para garantizar prop de conjuntos y simplificar el problema)

sumaParticion :: [Int] -> [Int] -> Bool
sumaParticion [] ys = False
sumaParticion (x:xs) ys = if ((sum xs) == (sum (x:ys))) then True else sumaParticion xs (x:ys)



-- Tambien puede llamarse subSecuencia, subConjunto == subSecuencia    
subconjuntos :: [a] -> [[a]]
subconjuntos []     = [[]]
subconjuntos (x:xs) = [x:ys | ys <- sub] ++ sub
    where sub = subconjuntos xs


{-
La diferencia entre sublista y subsecuencia radica en que 
la sublista es una subsecuencia que se obtiene eliminando uno o más elementos contiguos de una secuencia original, 
mientras que una subsecuencia es cualquier subconjunto que se obtiene eliminando cero o más elementos de la secuencia original,
pero manteniendo el orden relativo de los elementos restantes.

-}


{-
-- Versiones incorrectas de subLista 

--subListas :: [a] -> [[a]]
--subListas [] = [[]]
--subListas (x:xs) =  [[x]]++ subListas (xs) ++ [x:xs]

subListas :: [a] -> [[a]]
--subListas [] = [[]]
subListas [x] = [[]] ++ [[x]] 
subListas (x:xs) = [(x:ys)] ++ subListas xs ++ [[x]] 
                 where ys = iniciales xs

iniciales :: [a] -> [a]
iniciales [] = []
iniciales (x:xs) = [x] 
-}


sublista :: [a] -> [[a]] 
sublista [] = [[]]
sublista (x:xs) = sublista xs ++ [x:ys | ys <- iniciales xs]

iniciales :: [a] -> [[a]]
iniciales [] = [[]]
iniciales (x:xs) = [] : [ x:ys | ys <- iniciales xs ]

intercala :: a -> [a] -> [[a]]
intercala x [] = [[x]]
intercala x (y:ys) = (x:y:ys) : [y:zs | zs <- intercala x ys]

permutaciones :: [a] -> [[a]]
permutaciones []     = [[]]
permutaciones (x:xs) = 
    concat [intercala x ys | ys <- permutaciones xs]

-- Tiempo de ejecucion en el peor de los casos O(n^2) 
sinRepetidos :: (Eq a) => [a] -> [a]
sinRepetidos [] = []
sinRepetidos (x:xs) = if (pertenece x xs) then sinRepetidos xs else ([x] ++ sinRepetidos xs)

-- Tiempo de ejecucion en el peor de los casos O(n)
pertenece :: (Eq a) => a -> [a] -> Bool
pertenece n [] = False
pertenece n (x:xs) = if (n==x) then True else pertenece n xs 


-- 5a) Decidir si dos secuencias son anagramas.
sonAnagramas :: (Eq a) => [a] -> [a] -> Bool
sonAnagramas []  ys = False
sonAnagramas xs  ys = pertenece xs (permutaciones ys)

-- 5b) Dado un conjunto s y un valor n, decidir si existe un subconjunto de s cuya suma sea n.

sumaSubConjunto ::  [Int] -> Int -> Bool
sumaSubConjunto [] n = False
sumaSubConjunto xs n = sumaSubConjuntoAux (subconjuntos xs) n  

sumaSubConjuntoAux :: [[Int]] -> Int -> Bool
sumaSubConjuntoAux [[]] n = False
sumaSubConjuntoAux (x:xs) n = if ((sum x) == n) then True else sumaSubConjuntoAux xs n


-- 5c) Dadas dos cadenas p y s, decida si p es subcadena de s.

subCadenas :: String -> String -> Bool
subCadenas x y = pertenece x (sublista y)

-- 5d )Dadas dos cadenas p y s, decida si p es subsecuencia de s (los elementos no necesariamente tienen que aparecer contiguos en s).
subSecuencias :: String -> String -> Bool
subSecuencias x y = pertenece x (subconjuntos y)