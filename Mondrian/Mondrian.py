import turtle
import random
from PIL import Image

def drawSquare(ttl, color, xlow, ylow, xhigh, yhigh):
	ttl.color('black', color)
	ttl.penup()
	ttl.goto(xlow, ylow)
	ttl.begin_fill()
	ttl.pendown()
	ttl.goto(xlow, yhigh)
	ttl.goto(xhigh, yhigh)
	ttl.goto(xhigh, ylow)
	ttl.goto(xlow, ylow)
	ttl.end_fill()
	ttl.color('black')

def drawLine(ttl, x1, y1, x2, y2):
	ttl.penup()
	ttl.goto(x1, y1)
	ttl.pendown()
	ttl.goto(x2, y2)

def pickDirection(ttl, level):
	if level < 2:
		pick = random.randint(1, 2)
		return pick
	else:
		randFlt = random.random()
		if level == 2:
			if randFlt <= .146:
				pick = 3
			else:
				pick = random.randint(1, 2)
		elif level == 3:
			if randFlt <= .192:
				pick = 3
			else:
				pick = random.randint(1, 2)
		elif level == 4:
			if randFlt <= .238:
				pick = 3
			else:
				pick = random.randint(1, 2)
		elif level == 5:
			if randFlt <= .284:
				pick = 3
			else:
				pick = random.randint(1, 2)
		elif level >= 6:
			if randFlt <= .33:
				pick = 3
			else:
				pick = random.randint(1, 2)
	return pick

def pickColor(ttl, level, pick, xlow, ylow, xhigh, yhigh):
	color = random.randint(1, 5)
	if color == 1:
		#salmon
		color = '#fe0000'
	elif color == 2:
		#mint
		color = 'white'
	elif color == 3:
		color = 'white'
	elif color == 4:
		#pale yellow
		color = '#f7ff00'
	elif color == 5:
		#light blue
		color = '#0061ff'

	drawSquare(ttl, color, xlow, ylow, xhigh, yhigh)

def mondrian(ttl, level, xlow, xhigh, ylow, yhigh):

	if level == 0:
		return
	else:
		pick = pickDirection(ttl, level)

		if pick == 1:
			if not ((xhigh - (50 // level)) - (xlow + 50 // level)) < 0:
				x = random.randint(xlow + 50 // level, xhigh - 50 // level)
			else:
				x = (xlow + xhigh) // 2
			drawLine(ttl, x, ylow, x, yhigh)

			if level == 1:
				pickColor(ttl, level, pick, x, ylow, xhigh, yhigh)

			mondrian(ttl, level - 1, x, xhigh, ylow, yhigh)
			mondrian(ttl, level - 1, xlow, x, ylow, yhigh)

		#horitzontal pick
		elif pick == 2:
			if not ((yhigh - 50 // level) - (ylow + 50 // level)) < 0:
				y = random.randint(ylow + 50 // level, yhigh - 50 // level)
			else:
				y = (ylow + yhigh) // 2
			drawLine(ttl, xlow, y, xhigh, y)

			if level == 1:
				pickColor(ttl, level, pick, xlow, y, xhigh, yhigh)

			mondrian(ttl, level - 1, xlow, xhigh, y, yhigh)
			mondrian(ttl, level - 1, xlow, xhigh, ylow, y)

		#blank pick
		elif pick == 3:
			mondrian(ttl, level - 1, xlow, xhigh, ylow, yhigh)


def main():
  #create turtle object
  ttl = turtle.Turtle()

  # put label on top of page
  turtle.title('Mondrian')

  # setup screen size
  turtle.setup (900, 900, 0, 0)

  #have use enter level of recursion
  level = int(input('Ingrese un nivel de recursividad entre 1 y 10: '))

  ttl.speed(0)
  ttl.pensize(4)
  ttl.color('black')
  turtle.hideturtle()

  mondrian(ttl, level, -400, 400, -400, 400)

  ttl.penup()
  ttl.goto(-400, -400)
  ttl.pendown()
  ttl.goto(-400, 400)
  ttl.goto(400, 400)
  ttl.goto(400, -400)
  ttl.goto(-400, -400)
  ttl.penup()
  turtle.getscreen().getcanvas().postscript(file = 'Mondrian.eps')
  img = Image.open('Mondrian.eps')
  img.save('Mondrian.jpg')
  #turtle.getscreen().getcanvas().postscript(file = './Mondrian.ps')
  print("CLICK to exit")
  turtle.exitonclick()
  print("done")



main()
