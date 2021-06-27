n = int(input("n: "))
m = int(input("m: "))
k = int(input("k: "))

if (k % n == 0 and k / n < m) or (k % m == 0 and k / m < n):
    print('YES')
else:
    print('NO')
