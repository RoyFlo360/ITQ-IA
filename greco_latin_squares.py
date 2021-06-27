letters = []
numbers = []
n = int(input("n: "))
for n in range(1,n+1): 
    if n%2==0 or n%3==0:
        offsets=False
    else:
        offsets = [x for x in range(0,n)]
        offsets.extend([x for x in range(1,n)])
    letters.append(chr(64+n))
    numbers.append(n)
    if offsets :
        for y in range(n):
            for x in range(n):
                let=letters[(x+offsets[y])%n]
                num=numbers[(offsets[y]-x)%n]
                print (let+str(num), end= "  " if num<10 else " ")
            print("\n")     
    else: 
        print("Impossible\n")