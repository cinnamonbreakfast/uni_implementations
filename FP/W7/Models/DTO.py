from unittest import TestCase
import operator

class DTO:
    '''    DATA TRANSFER OBJECT (not objectSSSS)     '''
    def __init__(self, val1, val2=None):
        self.__LIST = []
        self._val1 = val1
        self._val2 = val2

    @property
    def first(self):
        ''' RETURN FIRST PROPERTY '''
        return self._val1

    @property
    def second(self):
        ''' RETURN SECOND PROPERTY '''
        return self._val2



