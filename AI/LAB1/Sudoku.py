# -*- coding: utf-8 -*-
"""
Created on Tue Mar  3 17:06:55 2020

@author: Andrew Candet
"""

import numpy as np
from copy import deepcopy
from math import sqrt

class Table():
    table = []
    n = 0
    f = ""
    
    matrix = []
    
    def __init__(self, file = "", copytable = None, nsize = 0):
        self.f = file
        if (copytable is not None and nsize != 0):
            self.table = deepcopy(copytable)
            self.n = deepcopy(nsize)
           
    '''
    Init from file, mandatory action
    '''
    def read(self, filename = ""):
        if(self.f == ""):
            if(filename == ""):
                raise ValueError("Sourcefile not mentioned.")
            file = open(filename, "r")
        else:
            file = open(self.f, "r")
            
        self.n = int(file.readline())
        
        for i in range(self.n):
            self.table.append([])
            
            line = file.readline().split()
            
            for val in line:
                self.table[i].append(int(val))
                
    def check_sum(self):
        sn = int(sqrt(self.n))
        for i in range(self.n):
            self.matrix.append([])
            
        for i in range(0, self.n):
            for j in range(0, self.n):
                self.matrix[(i//sn)*sn+j//sn].append(self.table[i][j])
                
        for i in range(self.n):
            if sum(self.matrix[i]) != self.n:
                return False
            
                
    '''
    Returns line as list
    '''
    def get_line(self, y):
        return self.table[y]
    
    '''
    Returns col as list
    '''
    def get_col(self, x):
        col = []
        
        for i in range(self.n):
            col.append(self.table[i][x])
            
        return col
    
    def get_table(self):
        return self.table
        
    def get_size(self):
        return self.n
    
    def get(self, x, y):
        return self.table[y][x]
    
    def set(self, x, y, v):
        self.table[y][x] = v
                
    def __str__(self):
        retstr = ""
        for i in range(self.n):
            for j in range(self.n):
                retstr += " " + str(self.table[i][j])
            retstr += "\n"
        
        return retstr
        

def check_line(line):
    for i in range(len(line)):
        if line[i] in line[i+1:]:
            return False
    
    return True

def check_col(col):
    for i in range(len(col)):
        if col[i] in col[i+1:]:
            return False
    
    return True

def check_solution(table):
    for i in range(table.get_size()):
        #print(table.get_line(i), check_col(table.get_line(i)))
        if not check_col(table.get_col(i)) or not check_line(table.get_line(i)) or table.check_sum():
            return False
    return True
    
def find_solution(tbl):
    table = Table(copytable = tbl.get_table(), nsize = tbl.get_size())

    for i in range(table.get_size()):
        for j in range(table.get_size()):
            if table.get(i, j) == 0:
                table.set(i, j, np.random.randint(1, 1 + table.get_size()))
                
    return table
    
def start_sudoku():
    table = Table("sudoku.in")
    table.read()
    print(table)
    
    atp = int(input("Trials: "))
    
    for i in range(atp):
        tmp = find_solution(table)
        if check_solution(tmp):
            print("Attempt", i, '/', atp)
            print(tmp)
            break
            
    print('Done')
    
    #print(table.get_line(2))
    #print(table.get_col(2))
                

