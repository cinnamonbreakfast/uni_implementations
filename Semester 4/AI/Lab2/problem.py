from state import State
from copy import deepcopy
from math import numpy as np

class Problem:
    
    def __init__(self, initial):
        self.__initial_config = initial
        self.__initial_state = State()
        self.__initialState.setValues([self.__initial_config])
        self.__final_state = State()

    def expand(self, currentState):
        new_states = []

        currCfg = currentState.getStateValues()[-1]

        for x in currCfg.nextConfiguration():
            new_states.append(currentState.add_config(x))

        return new_states
    
    def get_init_state(self):
        return copy.deepcopy(self.__initial_state)
    

    def heuristics(self, state):
        # return the error of the configuration (check on line, col and diagonals)
        errors = 0

        config = state.getStateValues()[-1]
        table = config.getStateValues()

        sum_on_lines = np.sum(table, axis=1)
        for s in sum_on_lines:
            if s > 1:
                errors += s

        sum_on_cols = np.sum(table, axis=0)
        for s in sum_on_cols:
            if s > 1:
                errors += s

        size = config.getSize()
        for offset in range(-size+1, size):
            s = sum(table.diagonal(offset))
            if s > 1:
                errors += s

        table = np.rot90(table)
        for offset in range(-size+1, size):
            s = sum(table.diagonal(offset))
            if s > 1:
                errors += s

        return errors

    def isSolution(self, state):
        config = state.get_values()[-1]
        table = config.get_values()
        size = config.get_size()
        
        table_sum = np.sum(table)
        if table_sum != size:
            return False
        
        sum_on_lines = np.sum(table, axis=1)
        for s in sum_on_lines:
            if s != 1:
                return False
            
        sum_on_cols = np.sum(table, axis=0)
        for s in sum_on_lines:
            if s != 1:
                return False
            
        for offset in range(-size+1, size):
            s = sum(table.diagonal(offset))
            if s > 1:
                return False
        
        table = np.rot90(table)
        for offset in range(-size+1, size):
            s = sum(table.diagonal(offset))
            if s > 1:
                return False
        
        return True
        