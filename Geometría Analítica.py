def menu():
    print("Menú: \n")
    print("Areas:\n")
    print("1.Area del circulo.")
    print("2.Area del triangulo.")
    print("3.Area del cuadrado.")
    print("4.Area del rectangulo.\n")
    print("Volúmenes")
    print("5.Volumen del cubo.")
    print("6.Volumen de la pirámide.\n")
    print("7.Salir.")


def circle():
    radio = int(input("Ingrese el radio del circulo: "))
    area = 3.14 * radio ** 2
    answer = ("El área del circulo de radio", radio, "es", area)
    return answer



def triangle():
    base = int(input("Ingrese la base del triangulo: "))
    altura = int(input("ingrese la altura del triangulo: "))
    area = .5 * base * altura
    answer = ("El area del triangulo de base", base, "y altura", altura, "es", area)
    return answer


def square():
    lado = int(input("Ingrese el lado del cuadrado: "))
    area = lado ** 2
    answer = ("El area del cuadrado de lado", lado, "es", area)
    return answer


def rectangle():
    base = int(input("Ingrese la base del rectangulo: "))
    altura = int(input("ingrese la altura del rectangulo: "))
    area = base * altura
    answer = ("El area del rectangulo de base", base, "y altura", altura, "es", area)
    return answer


def cube():
    a = int(input("Ingrese la longitud de la cara del cubo:"))
    volumen = a ** 3
    answer = ("El volumen del cubo de longitud", a, "es:", volumen)
    return answer


def pyramid():
    b = int(input("Ingrese la base del triangulo de base: "))
    a = int(input("ingrese la altura del triangulo de base: "))
    h = int(input("Ingrese la longitud de la altura de la pirámide: "))
    Ab = .5 * b * a
    volumen = 0.333333 * Ab * h
    answer = ("El volumen de la piramide de base", Ab, "y altura", h, "es", volumen)
    return answer


while True:
    menu()
    function = int(input("Escoja una opción:\n"))
    if function != 7:
        response = {1: circle(),
                    2: triangle(),
                    3: square(),
                    4: rectangle(),
                    5: cube(),
                    6: pyramid(),
                    }
        answer = response[function]
        print(answer)
    else:
        break
