


merge :: (Ord a, Eq a) => [a] -> [a] -> [a]
merge xs [] = xs
merge [] ys = ys
merge (x:xs) (y:ys) = if x < y then x : merge xs (y:ys) else y : merge (x:xs) ys

mergeSort :: (Ord a,Eq a) => [a] -> [a]
mergeSort [] = []
mergeSort [x] = [x]
mergeSort (x:xs) = merge [x] (mergeSort xs)   


shortPairs :: (Ord a) => [a] -> [a]
shortPairs xs = mergeSort xs 


tomaLaMitad :: [a] -> [a]
tomaLaMitad [] = []
tomaLaMitad xs = take (div (length xs)  2) xs


menorDist3Puntos :: [(Double,Double)] -> Double
menorDist3Puntos (x:y:z) = min (min (distPuntos x y) (distPuntos x (head z))) (distPuntos y (head z))


distPuntos :: (Double,Double) -> (Double,Double) -> Double
distPuntos (x1,y1) (x2,y2) = (sqrt ( (x2 - x1)^2 + (y2-y1)^2 ) ) 