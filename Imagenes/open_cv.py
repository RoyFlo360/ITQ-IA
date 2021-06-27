import cv2 as cv
import time
import numpy as np
import time
import sys
import math

img = cv.imread('/home/xorvus/Downloads/1544987412232-w.png')
height, width, n_channels = img.shape

chars_by_density = [
        ' ',
        ' ',
        '-',
        '+',
        ':',
        '=',
        '1',
        'O',
        '9',
        '8',
        'H',
        'Q',
        '$',
        '#',
        '@',
        '@',
        # '脹',
        # '錘',
        ]

def get_char(intensity):
    divisions = len(chars_by_density)
    range_len = math.ceil(256 / divisions)
    # print(range_len)
    for i in range(1, divisions + 1):
        # print(range_len * (i - 1))
        if intensity >= range_len * (i - 1) and intensity < range_len * i + 1:
            return chars_by_density[i - 1]
    print(intensity)
    sys.exit(0)

def is_grayscale(img):
    return len(img.shape) < 3

def saturated(sum_value):
    if sum_value > 255:
        sum_value = 255
    if sum_value < 0:
        sum_value = 0
    return sum_value

def monochromize(sum_value):
    # i, j, k = sum_value
    offset = 100
    # if i > offset or j > offset or k > offset:
    if sum_value > offset:
        return 255
    else:
        return 0

def sharpen(img):
    if is_grayscale(img):
        height, width = img.shape
    else:
        img = cv.cvtColor(img, cv.CV_8U)
        height, width, n_channels = img.shape

    result = np.zeros(img.shape, img.dtype)

    for j in range(1, height - 1):
        for i in range(1, width - 1):
            if is_grayscale(img):
                sum_value = 5 * img[j, i] - img[j + 1, i] - img[j - 1, i] \
                            - img[j, i + 1] - img[j, i - 1]
                result[j, i] = saturated(sum_value)
            else:
                for k in range(0, n_channels):
                    sum_value = 5 * img[j, i, k] - img[j + 1, i, k]  \
                                - img[j - 1, i, k] - img[j, i + 1, k]\
                                - img[j, i - 1, k]
                    # result[j, i, k] = saturated(sum_value)
                    # print(img[j, i])
                    # result[j, i, k] = monochromize(img[j, i, k])
    return result

def black_white(img):
    if is_grayscale(img):
        height, width = img.shape
    else:
        img = cv.cvtColor(img, cv.CV_8U)
        height, width, n_channels = img.shape

    result = np.zeros(img.shape, img.dtype)

    for j in range(1, height - 1):
        for i in range(1, width - 1):
            pixel = np.ndarray.tolist(img[j, i]) 
            mean_value = int((img[j, i, 0] + img[j, i, 1] + img[j, i, 2])/ 3)      
            result[j, i] = mean_value
                # print(img[j, i, k])
    return result

def black_white(img):
    if is_grayscale(img):
        height, width = img.shape
    else:
        img = cv.cvtColor(img, cv.CV_8U)
        height, width, n_channels = img.shape

    result = np.zeros(img.shape, img.dtype)

    for j in range(1, height - 1):
        for i in range(1, width - 1):
            pixel = np.ndarray.tolist(img[j, i]) 
            mean_value = int((img[j, i, 0] + img[j, i, 1] + img[j, i, 2])/ 3)      
            result[j, i] = mean_value
                # print(img[j, i, k])
    return result

def ascii(img):
    if is_grayscale(img):
        height, width = img.shape
    else:
        img = cv.cvtColor(img, cv.CV_8U)
        height, width, n_channels = img.shape

    result = np.zeros(img.shape, img.dtype)
    res_char = []

    char_space_y = 35
    char_space_x = 20
    # char_space_y = 11
    # char_space_x = 7
    for j in range(1, height - 1, char_space_y):
        char_row = []
        for i in range(1, width - 1, char_space_x):
            summation = 0
            squares_in_char = 0
            for j_intra in range(char_space_y * (j - 1) + 1, char_space_y * j + 1):
                for i_intra in range(char_space_x * (i - 1) + 1, char_space_x * i + 1):
                    summation += np.uint8((int(img[j, i, 0]) + int(img[j, i, 1]) + int(img[j, i, 2]))/3)      
                    # print(np.uint8((int(img[j, i, 0]) + int(img[j, i, 1]) + int(img[j, i, 2]))/3))
                    # time.sleep(1)
                    squares_in_char += 1

            # print(summation)
            # print(squares_in_char)
            mean = summation / squares_in_char
            char_row.append(get_char(mean))
        res_char.append(char_row)
    print_ascii_img(res_char)

def print_ascii_img(img_list):
    for j in img_list:
        for i in j:
            print(i, end='')
        print()

cv.namedWindow("Input", cv.WINDOW_AUTOSIZE)
cv.namedWindow("Output", cv.WINDOW_AUTOSIZE)
t = round(time.time())
cv.imshow("Input", img)
dst0 = ascii(img)
t = (time.time() - t)
print("Hand written function time passed in seconds: %s" % t)
cv.imshow("Output", dst0)
cv.imwrite('/mnt/schooldata/itq/ia/opencv/test.jpg', dst0);
