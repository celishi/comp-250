def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

print(gcd(5, 25))
print(5%25)
print(25%5)