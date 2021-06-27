import string
import sys

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
	file = open("weights"+"_"+xxx,"r")
	Texto = file.readlines()
	for linea in Texto:
		W.append(list_char_to_int(linea.replace("\n", '')))
except:
	W =[0 for i in range(len(X[0]))]

################################################################
################################################################
############### aqui esta la red neural ########################
################################################################

f = open(("Perceptron_{0:2f}_"+xxx).format(n),"w")
Iteracion = 0
Datos = []
while(True):  ## Condicion de Parada
	error = 0;
	for i in range(len(t)):
		err = float(t[i] - O(X[i],W))
		error += (err * err)
		if(err != 0):
			for j in range(len(W)):
				W[j] += n*err*X[i][j]
	error = error / len(t)
	f.write ('{0:5d} {1:5f}\n'.format(Iteracion, error))
	if(error == 0 or Iteracion == Max_Itt):
		break
	Iteracion += 1

f.close()