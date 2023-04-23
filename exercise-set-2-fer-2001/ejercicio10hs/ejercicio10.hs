distHamming :: [Char] -> [Char] -> Int
distHamming [] [] = 0
distHamming (x:xs) (y:ys) = if (x/=y) then 1 + distHamming xs ys else  distHamming xs ys
