import cv2

inputImagePath = './Assets/test1.png'

imageOriginal = cv2.imread(inputImagePath)

print imageOriginal[0]