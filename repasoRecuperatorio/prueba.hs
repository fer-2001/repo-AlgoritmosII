
data Tree a = Nil | N (Tree a) a (Tree a)
    deriving (Show,Eq) 


esHoja :: (Eq a) => Tree a -> Bool
esHoja (N hi r hd) = if (hi == Nil && hd == Nil) then True else False

esIgual :: Tree Int -> Int -> Int -> Bool
esIgual Nil k n = False
esIgual (N Nil r Nil) k n = if (n+r == k) then True else False
esIgual (N hi r hd) k n | ( (n+r) == k) && esHoja xs = True
                        | ( (n+r) /= k) && not (esHoja xs) = (esIgual hi k (n+r)) || (esIgual hd k (n+r)) 
                         where xs = (N hi r hd)


-- (N (N (N Nil 20 Nil) 40 (N Nil 50 Nil)) 60 (N (Nil) 90 (N (Nil) 100 (Nil)))) 


logs :: [Char] -> [Char] -> [Char] -> Bool
logs [] [] [] = True
logs [] [y] [z] = if (y==z) then True else False
logs [x] [] [z] = if (x==z) then True else False
logs (x:xs) (y:ys) (z:zs) | (z /= x) && (z/=y) = False
                          | (z == x) = logs xs (y:ys) zs
                          | (z == y) = logs (x:xs) ys zs


f :: Int -> Int
f 0 = 0
f n = f(n-1) + g(n-1)

g :: Int -> Int
g 0 = 1
g n = f(n-1)