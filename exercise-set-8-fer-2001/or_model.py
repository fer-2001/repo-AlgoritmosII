# Import library 
import numpy as np
from sklearn import linear_model
from sklearn.neural_network import MLPClassifier
#from sklearn.metrics import accuracy_score


# Logical OR  / Data loading 

X = [[0, 0], [0, 1], [1, 0], [1, 1]]

Y = [[0], [1], [1], [1]] 

#train our algorithm with Perceptron
p = linear_model.Perceptron(max_iter=200, tol=False)
p.fit(X,np.ravel(Y))
print('Perceptron:',p.score(X,Y))

#train our algorithm with LinearRegression
lr = linear_model.LinearRegression()
lr.fit(X,Y)
print('Regression score:',lr.score(X,Y)) 


#train our algorithm with MLPClassifier
mlpC = MLPClassifier(max_iter=2000, activation='identity', solver='sgd')
mlpC.fit(X,np.ravel(Y))
print('MLPClassifier score:', mlpC.score(X,Y))


# Testing final prediction 
print('Perceptron  Vs. LinearRegression Vs MLPClassifier')
for index in range(len(X)):
  print('')
  print("OR(" + str(X[index]) + ") = " + str(p.predict([X[index]])))
  print("OR(" + str(X[index]) + ") = " + str(lr.predict([X[index]])))
  print("OR(" + str(X[index]) + ") = " + str(mlpC.predict([X[index]])))    