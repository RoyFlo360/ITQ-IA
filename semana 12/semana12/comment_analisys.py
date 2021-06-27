import os.path as path
import sys

if len(sys.argv) != 4:
    print("Syntax error analysis <archivo> <diccionario + > <diccionario ->")
    exit(1)

name, pos, neg = sys.argv[1], sys.argv[2], sys.argv[3]

if not path.exists(name):
    print(name, " not found")
    exit(1)
if not path.exists(pos):
    print(pos, " not found")
    exit(1)
if not path.exists(neg):
    print(neg, " not found")
    exit(1)
file = open(name, 'r')
pos = open(pos, 'r')
neg = open(neg, 'r')

positives, negatives = [], []

for word in pos:
    positives.append(word.rstrip('\n'))

for word in neg:
    negatives.append(word.rstrip('\n'))

counter, cont, cpos, cneg, valuecom, yes_comments, destinated_comments, no_class_comments = 0, 0, 0, 0, 0, 0, 0, 0
row, value, wrd = "", "", ""
negated = ["not", "doesnt", "no"]
negate = False
for linea in file:
    chain = linea.split()
    for word in chain:
        for c in word:
            if c.isalpha():
                wrd += c
        if cont < (len(chain) - 1):
            row += wrd.lower() + " "
        else:
            value = word
        if wrd in negated:
            negate = True
        if negate:
            if wrd.lower() in positives:
                cneg += 1
            if wrd.lower() in negatives:
                cpos += 1
        else:
            if wrd.lower() in positives:
                cpos += 1
            if wrd.lower() in negatives:
                cneg += 1
        cont += 1
        wrd = ""
    print(row, " ", "Positive word: ", cpos, " Negative word: ", cneg, " valuer:", value)
    negate = False
    if cpos > cneg:
        valuecom = 1
        print("Positive")
    elif cneg > cpos:
        valuecom = 0
        print("Negative comment")
    else:
        print("No classified comment")
        valuecom = -1
    if valuecom == int(value):
        print("Accepted Valor")
        yes_comments += 1
    elif valuecom == -1:
        print("No defined Valor")
        no_class_comments += 1
    else:
        print("No certain Valor")
        destinated_comments += 1
    row = ""
    value = ""
    cont = 0
    cpos = 0
    cneg = 0
    counter += 1
counter += 1
print("Analyzed: ", counter)
print("Guessed", yes_comments)
print("Destined", destinated_comments)
print("No classified", no_class_comments)
poracp = (yes_comments * 100) / counter
porneg = (destinated_comments * 100) / counter
pornc = (no_class_comments * 100) / counter
print("% Guessed", poracp)
print("% Destined", porneg)
print("%No classified", pornc)
