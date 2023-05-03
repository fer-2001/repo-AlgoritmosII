
maxValorVinos :: [Int] -> Int 
maxValorVinos xs = maxValorVinosAux xs 1


maxValorVinosAux :: [Int] -> Int -> Int
maxValorVinosAux [] n = 0
maxValorVinosAux (x:xs) n = max (n*x + (maxValorVinosAux xs (n+1))) ( (last (x:xs)*n) + ( maxValorVinosAux (init (x:xs)) (n+1) )) 