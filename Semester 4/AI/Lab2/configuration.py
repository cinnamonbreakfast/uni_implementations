from copy import deepcopy
from math import numpy as np

class Configuration:
    '''
    holds a configurations of frogs
    '''
    def __init__(self, values):
        self.__size = len(values)
        self.__values = copy.deepcopy(values)
    
    def get_size(self):
        return self.__size
    
    def get_values(self):
        return copy.deepcopy(self.__values)
    
    def set_value(self, i, j, value):
        self.__values[i][j] = value
    
    def next_configuration(self):
        offsprings = []
        for line in range(self.__size):
            for col in range(self.__size):
                if self.__values[line][col] == 0:
                    new_config = Configuration(self.__values)
                    new_config.set_value(line, col, 1)
                    if self.check(new_config):
                        offsprings.append(new_config)
        
        return offsprings
    
    def __str__(self):
        s = ''
        for line in self.__values:
            s += str(line) + '\n'
        return s

    def check(self, config):
        table = config.get_values()

        lines_sum = np.sum(table, axis=1)
        for s in lines_sum:
            if s > 1:
                return False

        cols_sum = np.sum(table, axis=0)
        for s in cols_sum:
            if s > 1:
                return False

        size = config.get_size()
        for offset in range(-size + 1, size):
            s = sum(table.diagonal(offset))
            if s > 1:
                return False

        table = np.rot90(table)
        for offset in range(-size + 1, size):
            s = sum(table.diagonal(offset))
            if s > 1:
                return False

        return True