poem = open ('poema.txt','r')
message = poem.read()
print(mensaje)
poem.close()

def word_count(ruta):
   poem = open(ruta, "r")
   dic = dict()
   for linea in poem:
      linea = linea.strip()
      linea = linea.lower()
      linea = linea.replace("(", "").replace(")", "")
      words = linea.split(" ")
      for word in words:
         if word in dic:
            dic[word] += 1
         else:
            dic[word] = 1

   return dic

def see_words(dic):
   for word in list(dic.keys()):
      print(word, ":", dic[word])
dic = word_count('poema.txt')
see_words(dic)
word = 'dos'
ocur = []
with open('poema.txt') as lineas:
    for linea in lineas:
        if word in linea:
            ocur.append(linea)
print (ocur)
