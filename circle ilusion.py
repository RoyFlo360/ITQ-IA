import turtle
import time
from math import sin, pi
from random import random
"""
The code can be run with 5 optional arguments: population, resolution, loops, flip and lines.
population is the number of turtles
resolution is the time resolution (number of animation frames per repetition)
loops determines how many times the turtles loop back on themselves. The default of 1 gives a standard circle, other odd numbers give that number of loops in the string of turtles, while even numbers give a string of turtles disconnected at the ends, but still with the illusion of curved motion.
flip if non-zero causes the turtles to flip direction for their return trip (as suggested by aslum so that they are never moving backwards). As default they keep a fixed direction to avoid the visual distraction at the endpoints.
lines if non-zero displays the lines on which the turtles move, for consistency with the example image in the question.
"""

def circle_dance(population=11, resolution=480, loops=1, flip=0, lines=0):
    population = int(population)
    resolution = int(resolution)
    radius = 250
    screen = turtle.Screen()
    screen.tracer(0)
    if lines:
        arrange_lines(population, radius)
    turtles = [turtle.Turtle() for i in range(population)]
    for i in range(population):
        dancer = turtles[i]
        make_dancer(dancer, i, population)
    animate(turtles, resolution, screen, loops, flip, radius)


def arrange_lines(population, radius):
    artist = turtle.Turtle()
    for n in range(population):
        artist.penup()
        artist.setposition(0, 0)
        artist.setheading(n / population * 180)
        artist.forward(-radius)
        artist.pendown()
        artist.forward(radius * 2)
    artist.hideturtle()


def make_dancer(dancer, i, population):
    dancer.setheading(i / population * 180)
    dancer.color(random_turtle_colour())
    dancer.penup()
    dancer.shape('turtle')
    dancer.turtlesize(2)


def random_turtle_colour():
    return random() * 0.9, 0.5 + random() * 0.5, random() * 0.7


def animate(turtles, resolution, screen, loops, flip, radius):
    delay = 4 / resolution      # 4 seconds per repetition
    while True:
        for step in range(resolution):
            timer = time.perf_counter()
            phase = step / resolution * 2 * pi
            draw_dancers(turtles, phase, screen, loops, flip, radius)
            elapsed = time.perf_counter() - timer
            adjusted_delay = max(0, delay - elapsed)
            time.sleep(adjusted_delay)


def draw_dancers(turtles, phase, screen, loops, flip, radius):
    population = len(turtles)
    for i in range(population):
        individual_phase = (phase + i / population * loops * pi) % (2*pi)
        dancer = turtles[i]
        if flip:
            if pi / 2 < individual_phase <= 3 * pi / 2:
                dancer.settiltangle(180)
            else:
                dancer.settiltangle(0)
        distance = radius * sin(individual_phase)
        dancer.setposition(0, 0)
        dancer.forward(distance)
    screen.update()


if __name__ == '__main__':
    import sys
    circle_dance(*(float(n) for n in sys.argv[1:]))
