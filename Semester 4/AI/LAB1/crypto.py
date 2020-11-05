# -*- coding: utf-8 -*-
"""
Created on Wed Mar  4 14:55:11 2020

@author: Andrew Candet
"""
import numpy as np

words = []
letters = []
assigns = []

tr = 0

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

def map(word, translate):
    ret = 0
    #print(ret)
    for e in word:
        ret = (ret<<4) + translate[letters.index(e)]
        
        #print(hex(ret), "#", hex(translate[letters.index(e)]))
        
    return ret

def check(trans):
    n = len(words)
    s = 0
    #s += list(map(words[:n-1], trans))
    for i in range(n - 1):
        s += map(words[i], trans)
        
    return map(words[n-1], trans) == s
        



def start_crypto():
    global tr, letters, assigns, words
    f = open("crypto1.in", "r")
    
    inp = f.readline().split()
    
    for each in inp:
        words.append(each)
        for e in each:
            if e not in letters:
                letters.append(e)
    
    tr = int(input("Trials: "))   

    for t in range(tr):
        while(len(assigns) != len(letters)):
            rn = np.random.randint(0, 16)
            while rn in assigns:
                rn = np.random.randint(0, 16)
            assigns.append(rn)
        
        if check(assigns):
            print(letters)
            print(assigns)
            break
        assigns = []   
    
    

    
    

    