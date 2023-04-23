

minMonedas :: [Int] -> Int -> Int 
minMonedas xs 0 = 0
minMonedas xs 1 = 1
minMonedas (x:xs) n = if (n>x) then (minMonedas (x:xs) (n-x)) +1 else (minMonedas xs n)