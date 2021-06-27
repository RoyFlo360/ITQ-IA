import string
import sys
import random

def Tendencia(Lista):
	b = True
	for i in range(len(Lista)-1):
		b = b and (Lista[i] == Lista[i+1])
	return b


def filtrar(lista, propiedad):
	tmpX = []
	tmpt = []
	tmptestX = []
	tmptestt = []
	for aux in lista:
		if (aux[propiedad] <= 0.5):
			auxt = aux.pop()
			tmpX.append(aux)
			tmpt.append(auxt)
		else:
			auxt = aux.pop()
			tmptestX.append(aux)
			tmptestt.append(auxt)
	return (tmpX, tmpt, tmptestX, tmptestt)

def setCasos(lista):
	Training = []
	Trainingt = []
	Test = []
	Testt = []
	
	tmp = random.sample(lista,int(len(lista)*0.7))
	aux = 0
	for linea in lista:
		if(linea in tmp):
			aux = linea.pop()
			Training.append(linea)
			Trainingt.append(aux)
		else:
			aux = linea.pop()
			Test.append(linea)
			Testt.append(aux)
	
	return [Training,Trainingt,Test,Testt]
	
def list_char_to_int(Chars):
	tmp = []
	for c in Chars:
		tmp.append(eval(c))
	return tmp

def O(X,W):
	acum = 0
	for i in range(len(W)):
		acum += X[i]*W[i]
	if( acum > 0):
		return 2
	else:
		return 1

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
X = []

for linea in Texto:
    tmp.append(linea.replace("\n", '').split(','))

for linea in tmp:
	aux = list_char_to_int(linea)
	aux.insert(0,1)
	X.append(aux)

	
resultados = setCasos(X)
X = resultados[0]
t = resultados[1]
testX = resultados[2]
testt = resultados[3]

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

f = open("Adaline_Training_{0:d}".format(Max_Itt),"w")

Iteracion = 0
Datos = []
epsilon = 0.002

index = 0
Muestras = [0,0,0,0,0]

while(True):  ## Condicion de Parada
	error = 0;
	dW = [0 for i in range(len(W))] ## Inicializo Delta Weight en 0
	for i in range(len(t)): ##Para Cada training_examples
		out = O(X[i],W); ##Computar el output	
		err = t[i] - out ## Verificamos Error
		error += (err * err)
		if(err != 0):
			for j in range(len(W)): ## Para Cada unidad de weight
				dW[j] += n*err*X[i][j]
	for j in range(len(W)): ##Para cada unidad de weight actualizar
		W[j] += dW[j]
	
	error = float(error) / float(len(t))
	
	Muestras[i%5] = error
	i += 1
	
	f.write ('{0:10d} {1:3f}\n'.format(Iteracion, error))
	if(error <= epsilon or Iteracion == Max_Itt or Tendencia(Muestras)):
		break
	Iteracion += 1

f.close()
f = open("Adaline_Test_{0:d}".format(Max_Itt),"w")


errorPrueba = 0
i = 0
for entrada in testX:
	errorPrueba = testt[i] - O(entrada,W)
	f.write ('{0:5d} {1:5d}\n'.format(i, errorPrueba))
	i += 1

