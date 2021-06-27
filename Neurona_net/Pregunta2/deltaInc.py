import string
import sys
import random

def list_char_to_int(Chars):
	tmp = []
	for c in Chars:
		tmp.append(int(c))
	return tmp

def O(X,W):
	acum = 0
	for i in range(len(W)):
		acum += X[i]*W[i]
	if( acum > 0):
		return 1
	else:
		return -1

def Bin(int):
	if(int==1):
		return 1
	else:
		return 0

def and_lista(lista):
	acum = True
	for l in lista:
		acum = acum and l
	return acum
		
# Archivo
try:
	indxF = sys.argv.index('-f') + 1
	xxx = sys.argv[indxF]
except:
	xxx = input('Introduce el nombre del archivo de entrada\n')
	
file = open(xxx)

# Tasa de Aprendizaje
try:
	indxN = sys.argv.index('-n') + 1
	n = float(sys.argv[indxN])
except:
	n = 0.1
	
try:
	indxT = sys.argv.index('-i') + 1
	Max_Itt = int(sys.argv[indxT])	
except:
	Max_Itt = 300000
	
Texto = file.readlines()

tmp =[]
t =[]
X = []

for linea in Texto:
    tmp.append(linea.replace("\n", '').split(' '))

for linea in tmp:
	t.append(int(linea.pop()))
	aux = list_char_to_int(linea)
	aux.insert(0,1)
	X.append(aux)
	
try:
	W =[]
	file = open("weights"+"_"+xxx,r)
	Texto = file.readlines()
	for linea in Texto:
		W.append(list_char_to_int(linea.replace("\n", '')))
except:
	W =[random.uniform(0.0,0.15) for i in range(len(X[0]))] ##Inicilizar W con valores al azar pequenos
	
		
################################################################
################################################################
############### aqui esta la red neural ########################
################################################################

f = open(("deltaInc_{0:2f}_"+xxx).format(n),"w")
Iteracion = 0
Datos = []
act_Pesos = 0

while(True):  ## Condicion de Parada
	error = 0;
	dW = [0 for i in range(len(W))] ## Inicializo Delta Weight en 0	
	for i in range(len(t)): ##Para Cada training_examples
		out = O(X[i],W); ##Computar el output	
		err = float(t[i] - out) ## Verificamos Error
		error += (err * err)
		if(err != 0): 
			for j in range(len(W)): ## Para Cada unidad de weight
				dW[j] += n*err*X[i][j]
		for j in range(len(W)): ##Para cada unidad de weight actualizar
			W[j] += dW[j]
			act_Pesos += 1
			
	error /= len(t)
	f.write  ('{0:5d} {1:5f}\n'.format(Iteracion, error))
		
	if(error == 0 or Iteracion == Max_Itt):
		break
	Iteracion += 1
f.write  ("# De Actualizaciones de Pesos (W): {0:d}".format(act_Pesos))
f.close()
