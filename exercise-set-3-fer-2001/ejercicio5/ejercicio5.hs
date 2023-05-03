subconjuntos :: [a] -> [[a]]
subconjuntos []     = [[]]
subconjuntos (x:xs) = [x:ys | ys <- sub] ++ sub
    where sub = subconjuntos xs


pertenece :: (Eq a) => a -> [a] -> Bool
pertenece n [] = False
pertenece n (x:xs) = if (n==x) then True else pertenece n xs 