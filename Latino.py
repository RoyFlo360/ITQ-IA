orden = int(input("Ingrese el Orden del Cuadrado : "))
nInicial = int(input("Ingrese el Número Inicial : "))
while (nInicial > orden):
    print("El numero superior izquierdo debe de ser menor o igual al orden del cuadrado")
    nInicial = int(input("Ingrese el Número Inicial : "))
arreglo = []
nInicialF = nInicial
for fil in range(0, orden):
    aAux = []
    nInicialC = nInicialF
    for col in range(0, orden):
        aAux.append(nInicialC)
        nInicialC += 1
        if (nInicialC > orden):
             nInicialC = 1
    arreglo.append(aAux)
    nInicialF += 1
    if (nInicialF > orden):
        nInicialF = 1
print()
for fil in range(0, orden):
    for col in range(0, orden):
        print(arreglo[fil][col], end=" ")
    print()