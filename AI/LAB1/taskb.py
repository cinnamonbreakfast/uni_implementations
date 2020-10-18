# -*- coding: utf-8 -*-
"""
Created on Mon Mar  2 18:43:52 2020

@author: Andrew Candet
"""

import matplotlib.pyplot as plt
import numpy as np

def distribs():
    case = -1
    upr = 0
    lwr = 0
    
    while True:
        print("1. Uniform distribution")
        print("2. Normal distribution")
        print("0. Exit")
        
        case = int(input("> "))
        if case != 0:
            lwr = int(input("Interval lower bound: "))
            upr = int(input("Interval upper bound: "))
        
        if case == 0:
            print("EXIT APP")
            return
        elif case == 1: # Uniform distribution
            uniform_distr = np.random.uniform(lwr, upr, size=20)

            plt.plot(uniform_distr, 'bo')
            plt.ylabel("Uniform distribution")
            plt.show()

        elif case == 2: # Normal distribution
            m = (upr + lwr)/2
            sig = upr - m
            
            normal_distr = np.random.normal(m, sig, size=20)

            plt.plot(normal_distr, 'ro')
            plt.ylabel("Normal distribution")
            plt.show()
          



    