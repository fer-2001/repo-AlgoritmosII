
sumaIgual :: [Float] -> Float -> Bool
sumaIgual [] z = False
sumaIgual [x] z = False
sumaIgual [x,y] z = if (x+y) == z then True else False
sumaIgual xs z = (sumaIgual (take (div (length xs) 2) xs) z)  || (sumaIgual (drop (div (length xs) 2) xs)  z) 


sumaIgual2 :: [Float] -> Float -> Bool
sumaIgual2 [] z = False
sumaIgual2 xs z | ((head xs + last xs) == z) = True  
                | (head xs + last xs) > z = sumaIgual (init xs) z
                | (head xs + last xs) < z = sumaIgual (tail xs) z



