
# This does Karatsuba multiplication of two integers represented
# as strings.  String are used to contain arbitrarily large numbers.
# Note that this algorithm only works with numbers thar are length
# of a power of two, however the algorithm could be modified to
# handle arbitary length inputs (greater than 4). See Wikipedia
# def karatsuba(x, y):
#     a, b = splitInTwo(x)
#     c, d = splitInTwo(y)
#
#     if len(a) == 1:
#         return baseCase(a,b,c,d)
#
#     ac = km(a, c)
#     ad = km(a, d)
#     bc = km(b, c)
#     bd = km(b, d)
#
#     return combineTerms(ac, ad, bc, bd)

def karatsuba(x,y):
    if len(str(x)) == 1:
        return x*y
    else:
        m = len(str(x))
        m2 = m // 2

        a = x // 10**(m2)
        b = x % 10**(m2)
        c = y // 10**(m2)
        d = y % 10**(m2)

        z0 = karatsuba(b, d)
        z1 = karatsuba((a+b), (c+d))
        z2 = karatsuba(a, c)

        return (z2 * 10**(2*m2)) + (z1 - z2 - z0) * 10**m2 + z0

def baseCase(a,b,c,d):
    ac = int(a) * int(c)
    ad = int(a) * int(d)
    bc = int(b) * int(c)
    bd = int(b) * int(d)
    return str(100 * ac + 10 * (ad + bc) + bd)

def combineTerms(ac, ad, bc, bd):
    # return 10^n*ac + 10^(n/2)*(ad + bc) + bd

    return '1234'

def appendZeros(s, numZeros):
    for i in range(1, numZeros + 1):
        s = s + '0'

    return s

def addStringsAsInt(x, y):
    return x + y

def splitInTwo(s):
    return s[:len(s)//2], s[len(s)//2:]

def main():
    result = karatsuba('12', '34')
    print(result)


if __name__ == "__main__":
    main()