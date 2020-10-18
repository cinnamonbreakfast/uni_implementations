# -*- coding: utf-8 -*-
"""
Created on Thu Mar  5 16:00:48 2020

@author: cande
"""

import taskb
import Sudoku
import crypto
import geometric

while True:
    print("1 : Distributions")
    print("2 : Sudoku")
    print("3 : Crypto")
    print("4 : Geometric")
    
    pr = int(input("Problem: "))
    
    if pr == 1:
        taskb.distribs()
    elif pr == 2:
        Sudoku.start_sudoku()
    elif pr == 3:
        crypto.start_crypto()
    elif pr == 4:
        trials = int(input("Trials: "))
        
        geometric.randfit(trials)