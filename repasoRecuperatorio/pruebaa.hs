
subConjuntos :: [Int] -> [[Int]]
subConjuntos [] = [[]]
subConjuntos (x:xs) =  [x : ys | ys <- subConjuntos xs] ++ subConjuntos xs


sublista :: [a] -> [[a]] 
sublista [] = [[]]
sublista (x:xs) = sublista xs ++ [x:ys | ys <- iniciales xs]

iniciales :: [a] -> [[a]]
iniciales [] = [[]]
iniciales (x:xs) = [] : [ x:ys | ys <- iniciales xs ]


sumaSubConj :: [[Int]] -> Bool
sumaSubConj [] = False
sumaSubConj (x:xs) = if (sum x) == 0 then True else sumaSubConj xs   



subconjuntosContiguos :: [a] -> [[a]]
subconjuntosContiguos [] = [[]]
subconjuntosContiguos [x] = [[x]]
subconjuntosContiguos xs = 
    let n = length xs `div` 2
        (left, right) = splitAt n xs
        subleft = subconjuntosContiguos left
        subright = subconjuntosContiguos right
    in subleft ++ subright ++ combinacionesContiguas subleft subright

combinacionesContiguas :: [[a]] -> [[a]] -> [[a]]
combinacionesContiguas [] _ = []
combinacionesContiguas _ [] = []
combinacionesContiguas xs ys = 
    let (x:xs') = reverse xs
        (y:ys') = ys
    in (x ++ y) : (combinacionesContiguas (reverse xs') ys')
