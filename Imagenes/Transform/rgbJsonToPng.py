import cv2
import json
import sys
import numpy

inputJsonPath = './Exec/image.json'
outputImagePath = './Exec/image.png'


with open(inputJsonPath) as json_file:
    matrix = json.load(json_file)

    lenY = len(matrix)
    lenX = len(matrix[0])
    lenZ = 3

    numpyMatrix = numpy.arange(lenY*lenX*lenZ).reshape(lenY,lenX,lenZ)
    

    for y in range(lenY):
        for x in range(lenX):
            for z in range(lenZ):
                numpyMatrix[y][x][z] = matrix[y][x][z]
    
    #print numpyMatrix
    
    cv2.imwrite(outputImagePath, numpyMatrix)

            