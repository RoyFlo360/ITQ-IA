::----- Pregunta 2

START delta.py -f AND.txt
START delta.py -f OR.txt
START delta.py -f XOR.txt -i 10000

::----- Pregunta 2,a

START delta.py -f AND.txt -n 0.01
START delta.py -f AND.txt -n 0.05
START delta.py -f AND.txt -n 0.1
START delta.py -f AND.txt -n 0.2
START delta.py -f AND.txt -n 0.3
START delta.py -f AND.txt -n 0.4
START delta.py -f AND.txt -n 0.5
START delta.py -f AND.txt -n 0.99

START delta.py -f AND.txt -n 0.01 -d -i 10000
START delta.py -f AND.txt -n 0.05 -d
START delta.py -f AND.txt -n 0.1 -d
START delta.py -f AND.txt -n 0.2 -d
START delta.py -f AND.txt -n 0.3 -d
START delta.py -f AND.txt -n 0.4 -d
START delta.py -f AND.txt -n 0.5 -d
START delta.py -f AND.txt -n 0.99 -d


START delta.py -f OR.txt -n 0.01
START delta.py -f OR.txt -n 0.05
START delta.py -f OR.txt -n 0.1
START delta.py -f OR.txt -n 0.2
START delta.py -f OR.txt -n 0.3
START delta.py -f OR.txt -n 0.4
START delta.py -f OR.txt -n 0.5
START delta.py -f OR.txt -n 0.99

START delta.py -f OR.txt -n 0.01 -d
START delta.py -f OR.txt -n 0.05 -d
START delta.py -f OR.txt -n 0.1 -d
START delta.py -f OR.txt -n 0.2 -d
START delta.py -f OR.txt -n 0.3 -d
START delta.py -f OR.txt -n 0.4 -d
START delta.py -f OR.txt -n 0.5 -d
START delta.py -f OR.txt -n 0.99 -d

::-------- Pregunta 2,b

START deltaInc.py -f AND.txt
START deltaInc.py -f OR.txt

