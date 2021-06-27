from itertools import permutations 
import re 
s = str(input("Introduce la cadena a permutar: \n"))
p = set(permutations(s))  
c = 0
for i in p:
	c += 1
	print("Solucion " + str(c) + ": " + str(i))