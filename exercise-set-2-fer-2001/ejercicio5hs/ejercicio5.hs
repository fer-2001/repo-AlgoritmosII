sublista :: [a] -> [[a]] 
sublista [] = [[]]
sublista (x:xs) = sublista xs ++ [x:ys | ys <- iniciales xs]

iniciales :: [a] -> [[a]]
iniciales [] = [[]]
iniciales (x:xs) = [] : [ x:ys | ys <- iniciales xs ]

secuenciasComunes :: (Eq a) => [[a]] -> [[a]] -> [[a]]
secuenciasComunes [[]] ys = [[]]
secuenciasComunes xs [[]] = [[]]
secuenciasComunes (x:xs) ys = if (pertenece x ys) then x:(secuenciasComunes xs ys) else secuenciasComunes xs ys


maximaSubSec :: (Eq a) => [a] -> [a] -> [a]
maximaSubSec xs ys = subListaMasLarga (secuenciasComunes (sublista xs) (sublista ys))


subListaMasLarga:: [[a]] -> [a]
subListaMasLarga [[]] = []
subListaMasLarga [x] = x
subListaMasLarga (x:y:xs) = if(length x < length y) then subListaMasLarga(y:xs) else subListaMasLarga(x:xs)


pertenece :: (Eq a) => a -> [a] -> Bool
pertenece n [] = False
pertenece n (x:xs) = if (n==x) then True else pertenece n xs 

{-Subsequencias contiguas con divide and conquer-}
subsequences :: [a] -> [[a]]
subsequences [] = [[]]
subsequences (x:xs) = let subs = subsequences xs
                      in subs ++ map (x:) subs
