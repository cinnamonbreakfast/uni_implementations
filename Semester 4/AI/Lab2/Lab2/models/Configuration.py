import numpy


class Configuration:
    def __init__(self, size):
        self._size = size
        self._matrix = numpy.zeros((size, size), dtype=int)

    def getValues(self):
        return self._matrix[:]

    def getSize(self):
        return self._size

    def setValues(self, newValues):
        self._matrix = newValues

    def nextConfig(self, row, column):

        nextC = self.getValues()
        if nextC[row][column] != 1:
            nextC[row][column] = 1

            newConfig = Configuration(self._size)
            newConfig.setValues(nextC)
            return newConfig
        return None