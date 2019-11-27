
'''
Generate all sequences of n parentheses that close correctly. Example: for n=4 there are two
solutions: (()) and ()().

'''
n = 0
while True:
    n = input("Number of parentheses: ")

    try:
        n = int(n)

        if n%2 == 1:
            print("Nu")
            continue

        break
    except:
        print("Integer required!")


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

def backtrackingIterative(n):

    x = [-1]
    while len(x) > 0:
        chosen = False
        while not chosen and len(x) <= n and x[-1] < 1:
            x[-1] += 1
            chosen = consistent(x, n)

        if chosen:
            if solution(x, n):
                solutionFound(x)
            else:
                x.append(-1)
        else:
            x = x[:-1]  # pop


backtrackingIterative(n)

