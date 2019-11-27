
'''
Generate all sequences of n parentheses that close correctly. Example: for n=4 there are two
solutions: (()) and ()().

'''
n = 0
# while True:
#     n = input("Number of parentheses: ")
#
#     try:
#         n = int(n)
#
#         if n == 0:
#             break
#
#         if n%2 == 1:
#             print("Nu merge asa frt")
#             continue
#
#         break
#     except:
#         print("Integer required!")


def solution(x, n):
    return len(x) == n

def solutionFound(x):
    s = ""
    for i in x:
        if i == 1:
            s = s +")"
        else:
            s = s +"("

    print(s)

def consistent(x, n):
    open = 0
    close = 0

    for i in x:
        if i == 1:
            close += 1
        else:
            open += 1

    if close > open:
        return False

    if open > n/2:
        return False

    return True

def back(n, x, chosen):
    if len(x) > 0:
        if not chosen and len(x) <= n and x[-1] < 1:
            x[-1] += 1
            back(n, x, consistent(x, n))

        if chosen:
            if solution(x, n):
                solutionFound(x)
            else:
                back(n, x+[-1], False)
        else:
            if len(x) > 0:
                return True     # done
            else:
                x.pop()

        back(n, x, False)


x = [-1]

# back(n, x, False)

print("  A B C D E F")
print("1 . . . . . .")
print("2 . . x . . .")
print("3 . . . . . .")




def fibo(n):
    if n == 0 or n == 1:
        return 1

    '''
    Recursive step progresses toward the simple case
    '''
    return fibo(n - 2) + fibo(n - 1)

def ifb(x):
    f1 = 1
    f2 = 1
    y = 0
    for i in range(x-1):
        y = f1 + f2
        f1, f2 = f2, y

    return y

def pali(x):
    if len(x) == 0:
        return True
    return x[0] == x[-1] and pali(x[1:-1])

#   0  1  2  3  4  5  6   7   8   9   10
#   1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89

a = 1
b = [str(a), 1]
c = {str(a): a, a: b}





print("pla" ,str(a) in c)

money = (70, 100)

S = 140

A = 0
p = len(money)
monede = [0, 0, 0, 0, 0]
up = 1
p -= up

while A < S:
    if A+money[p] > S:
        if p == 0:
            up += 1
            p = len(money) - up
            monede = [0, 0, 0, 0, 0]
            A = 0
        else:
            p -= 1
    else:
        A += money[p]
        monede[p] += 1

    print(A)

    # BC 1
    # WC N
    # AVG n(n+1)/2n
    # OVERALL n

m = 5
c = m - m / 3 * 3

print(c)


lst = [
    ["w", 44],
    ["a", 12],
    ["r", 55],
    ["e", 4]
]

lst = sorted(lst, key=lambda a : a[0])

print(lst)

lst = sorted(lst, key=lambda a : a[1])

print(lst)














# tzeapa