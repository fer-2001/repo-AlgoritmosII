sublista :: [a] -> [[a]] 
sublista [] = [[]]
sublista (x:xs) = sublista xs ++ [x:ys | ys <- iniciales xs]

iniciales :: [a] -> [[a]]
iniciales [] = [[]]
iniciales (x:xs) = [] : [ x:ys | ys <- iniciales xs ]

esSublista :: (Eq a) => [a] -> [a] -> Bool
esSublista zs []  = False
esSublista zs (x:xs)  = if ((xs == zs) || pertenece zs  aux) then True else (esSublista zs xs)
                      where aux = [x:ys | ys <- iniciales xs]

pertenece :: (Eq a) => a -> [a] -> Bool
pertenece n [] = False
pertenece n (x:xs) = if (n==x) then True else pertenece n xs 