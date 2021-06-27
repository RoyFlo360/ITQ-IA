import numpy as np
total, p = 0, 0
n = int(input("n: "))
a = []
m = np.empty(shape = (n,n), dtype= str)
def posic(r, c):
    global n
    return r * n + c

def imprime():
    global total
    global n
    global a
    total += 1
    print("Solucion " + str(total) + ":")
    for i in range(n):
        for j in range (n):
            m[(i),(j)]= (chr(64 + a[posic(i,j)]))
    print(m)
    print("\n")


def noRepiteRen(valor):
    global p
    global n
    columna = int(p % n)
    for i in range(int(p/n)):
        if(a[posic(i,columna)] == valor):
            return False
    return True

def noRepiteCol(valor):
    global p
    global n
    renglon = int(p / n)
    for i in range(int(p%n)):
        if(a[posic(renglon,i)] == valor):
            return False
    return True


def resuelve():
    global p
    global n
    global a
    for i in range(1, n+1):
        if(noRepiteRen(i) and noRepiteCol(i)):
            p += 1
            a.append(i)
            if(p == n*n):
                print(a)
                imprime() 
            else:
                resuelve()
            p -= 1
print(resuelve())

"""def permute(a, l, r):
    if l==r:
        print(a)
    else:
        for i in range(l,r+1):
            a[l], a[i] = a[i], a[l]
            permute(a, l+1, r)
            a[l], a[i] = a[i], a[l] # backtrack

# Driver program to test the above function
string = "ABC"
n = len(string)
a = list(string)
permute(a, 0, n-1)"""