#!/usr/bin/env python
"""
Using custom colors
===================

Using the recolor method and custom coloring functions.
"""

import numpy as np
from PIL import Image
from os import path
import matplotlib.pyplot as plt
import os
import random

from wordcloud import WordCloud, STOPWORDS, ImageColorGenerator


def grey_color_func(word, font_size, position, orientation, random_state=None,
                    **kwargs):
    return "hsl(0, 0%%, %d%%)" % random.randint(60, 100)


# get data directory (using getcwd() is needed to support running example in generated IPython notebook)
d = path.dirname(__file__) if "__file__" in locals() else os.getcwd()

# read the mask image from root
mask = np.array(Image.open(path.join(d, "starwars.jpg")))

# movie script of "a new hope"
# http://www.imsdb.com/scripts/Star-Wars-A-New-Hope.html
# May the lawyers deem this fair use.
text = open(path.join(d, 'a_new_hope.txt')).read()

# pre-processing the text a little bit
text = text.replace("HAN", "Han")
text = text.replace("LUKE'S", "Luke")

# adding movie script specific stopwords
stopwords = set(STOPWORDS)
stopwords.add("int")
stopwords.add("ext")

# wc = WordCloud(max_words=1000, mask=mask, stopwords=stopwords, margin=10, random_state=1).generate(text)
wc = WordCloud(background_color="black", max_words=33101, mask=mask,
               stopwords=stopwords, random_state=100).generate(text)
# store default colored image
default_colors = wc.to_array()
"""plt.title("Custom colors")
plt.imshow(wc.recolor(color_func=grey_color_func, random_state=3), interpolation="bilinear")
wc.to_file("a_new_hope.png")
plt.axis("off")
plt.figure()"""

plt.title("Default colors")
plt.imshow(default_colors, interpolation="bilinear")
plt.axis("off")
plt.show()

"""# create coloring from image
image_colors = ImageColorGenerator(mask)

# recolor wordcloud and show
plt.title("Default colors")
plt.imshow(wc.recolor(color_func=image_colors), interpolation="bilinear")
plt.axis("off")
plt.show()"""
