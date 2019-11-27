class FunctionCall:
    def __init__(self, function, *params):
        '''
        Function object + params stocked in Timeline
        :param function:
        :param params:
        '''
        self._func = function
        self._params = params

    def call(self):
        '''
        Call function with parameters
        :return:
        '''
        self._func(*self._params)

class Cascade:
    ''' Cascade of dependent actions '''
    def __init__(self):
        self.__FUNCS = []

    def append(self, func):
        '''
        Append dependent operaion to cascade
        :param func: operation
        :return: -
        '''
        self.__FUNCS.append(func)

    def undo(self):
        '''
        Undo the entire cascade
        :return:
        '''
        for each in self.__FUNCS:
            each.undo()

    def redo(self):
        '''
        Redo the entire cascade
        :return:
        '''

        for each in self.__FUNCS:
            each.redo()

class Operation:
    '''
    Set of Undo+Redo for an action
    '''
    def __init__(self, undo, redo):
        self._undoFunc = undo
        self._redoFunc = redo

    def undo(self):
        '''
        Undo action call
        :return:
        '''
        self._undoFunc.call()

    def redo(self):
        '''
        Redo action call
        :return:
        '''
        self._redoFunc.call()

class Timeline:
    '''
    HISTORY LIST
    '''
    def __init__(self):
        self._operations = []
        self._index = -1
        self._active = False

    def clear(self):
        '''
        Clear history actions
        :return:
        '''
        self._operations = []
        self._index = -1
        self._active = False

    def append(self, operation):
        '''
        Append operation or cascade to history
        :param operation: operation/cascade
        :return:
        '''
        #self._operations = self._operations[:self._index]
        if self._active == False:
            self._index += 1
            self._operations = self._operations[:self._index]
            self._operations.append(operation)
        #print(self._index, "das")

    # def __test(self):
    #     try:
    #         print(self._operations[10].__FUNCS)
    #         #print(each)
    #     except:
    #         pass

    def undo(self):
        '''
        Undo in history
        :return:
        '''
        #for each in self._operations:
        #    print(each)
        #print(self._index)
        self._active = True
        if self._index < 0:
            return False

        self._operations[self._index].undo()
        self._index -= 1
        self._active = False

        return True

    def redo(self):
        '''
        Redo in history
        :return:
        '''
        self._active = True

        if self._index >= len(self._operations) - 1:
            return False

        self._index += 1
        self._operations[self._index].redo()
        self._active = False
        return True
