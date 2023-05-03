
subBags :: [Int] -> [[Int]]
subBags [] = [[]]
subBags (x:xs) = sinRepetidos ([x:ys | ys <- subBags xs] ++ subBags xs ) 

sinRepetidos :: (Eq a) => [a] -> [a]
sinRepetidos [] = []
sinRepetidos (x:xs) = if (pertenece x xs) then sinRepetidos xs else ([x] ++ sinRepetidos xs)

-- Tiempo de ejecucion en el peor de los casos O(n)
pertenece :: (Eq a) => a -> [a] -> Bool
pertenece n [] = False
pertenece n (x:xs) = if (n==x) then True else pertenece n xs 