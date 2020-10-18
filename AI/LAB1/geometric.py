# -*- coding: utf-8 -*-
"""
Created on Wed Mar  4 20:47:00 2020

@author: cande
"""

import numpy as np

forms = []
m = 0
n = 0


def read():
    global m, n
    '''
    
    read the matrix, the forms and all the sizes n shit
    
    
    '''
    f = open("geom.in", "r")
    line = f.readline()
        
    sizing = line.split()
    m = int(sizing[0])
    n = int(sizing[1])
    
    line = f.readline()
    
    fms = int(line)
    
    for i in range(fms):
        sz = f.readline().split()
        h = int(sz[0])
        w = int(sz[1])
        
        matrix = []
        
        for j in range(h):
            matrix.append([])
        
        
        for j in range(h):
            vals = f.readline().split()
            for e in range(w):
                matrix[j].append(int(vals[e]))
                
        forms.append(matrix)
    print("Done reading")
            
read()

for i in range(len(forms)):
    print()
    for j in range(len(forms[i])):
        print(forms[i][j])
        
def random_arrange(form):
    global m, n
    layer = []
    for i in range(m):
        layer.append([])
        for j in range(n):
            layer[i].append(0)
        
    #print(layer)
        
    #print(m, n)
        
    x = np.random.randint(0, m)
    y = np.random.randint(0, n)
    
    h = len(form)
    w = len(form[0])
    
    #print(h, w)
    
    #print("starts fitting")
    
    while True:
        if x + h - 1 < m and y + w - 1 < n:
            for i in range(h):
                for j in range(w):
                    layer[x+i][y+j] = form[i][j]
            return layer
        else:
            #print("Fail")
            x = np.random.randint(0, m)
            y = np.random.randint(0, n)
    #print("Done arranging")
    return layer
        
#print(random_arrange(forms[0]))
            
def check_overlap(layers):
    #print("check")
    s = 0
    for i in range(m):
        s = 0
        for j in range(n):
            s = 0
            for k in range(len(layers)):
                s += layers[k][i][j]
            if s > 1:
                return False
    return True                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

def randfit(trials):
    layers = []
    t = trials
    while t:
        #print("trial", trials)
        layers = []
        for i in range(len(forms)):
            #print("arranging")
            layers.append(random_arrange(forms[i]))
        if check_overlap(layers):
            print("Found")
            print("Layers:")
            
            for i in range(len(layers)):
                for j in range(m):
                    print(layers[i][j])
                print()
            break
        t -= 1
        

    
    
    