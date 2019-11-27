x = []

def solution(x, n):
    return len(x) == n

def solutionFound(x):
    print(x)

def consistent(x, n):
    if len(x) > 2:
       return len(set(x)) == len(x)
    return len(set(x)) == len(x)

def backtrackingIterative(n):

    x = [-1]
    while len(x) > 0:
        chosen = False
        while not chosen and x[-1] < n - 1:
            #print(x, "aici")
            x[-1] += 1
            chosen = consistent(x, n)
        if chosen:
            if solution(x, n):
                solutionFound(x)
            else:
                x.append(-1)
        else:
            x = x[:-1]

backtrackingIterative(4)

'''
0 1 2 3
0 1 3 2
0 2 3 1
0 3 2 1
1 2 3 0
1 3 2 0
2 3 1 0
3 2 1 0
'''