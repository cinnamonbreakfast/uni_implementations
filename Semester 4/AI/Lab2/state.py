from configuration import Configuration

class State:
    '''
    holds a PATH of configurations
    '''
    def __init__(self):
        self.__values = []
        
    def add_config(self, configuration):
        aux = State()
        aux.set_values(self.__values + [configuration])
        return aux
        
    def set_values(self, values):
        self.__values = copy.deepcopy(values)
        
    def get_values(self):
        return copy.deepcopy(self.__values)
    
    def __str__(self):
        s = ''
        for v in self.__values:
            s += str(v) + '\n'
        return s