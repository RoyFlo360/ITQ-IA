import cv2
import json
import sys

inputImagePath = './Exec/image.png'
outputJsonPath = './Exec/image.json'

imageOriginal = cv2.imread(inputImagePath)

#print type(imageOriginal[0][0][0])
#print imageOriginal[0][0][0]

sys.exit(0)

imageMatrix = []

for i,row in enumerate(imageOriginal):
    imageMatrix.append([])
    for j,cell in enumerate(row):
        imageMatrix[i].append([])
        for k,color in enumerate(cell):
            imageMatrix[i][j].append(int(color))

#print json.dumps(imageMatrix)
f = open(outputJsonPath, "w")
f.write(json.dumps(imageMatrix))
f.close()