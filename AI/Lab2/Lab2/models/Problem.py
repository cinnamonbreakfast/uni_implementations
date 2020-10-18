from copy import deepcopy

import numpy

from models.State import State

class Problem:
    def __init__(self, initial):
        self._initialConfig = initial
        self._initialState = State()
        self._initialState.addValue(deepcopy(self._initialConfig))

    def expand(self, currentState):
        expansion = []
        currentConfig = currentState.getValues()[-1]

        for row in range(currentConfig.getSize()):
            for column in range(currentConfig.getSize()):
                nextConfig = currentConfig.nextConfig(row, column)
                if nextConfig != None:
                    expansion.append(nextConfig)

        return expansion

    def isSolution(self, state):
        config = state.getValues()[-1]

        if len(set(numpy.sum(config.getValues(), axis=1))) != 1:
            return False

        if len(set(numpy.sum(config.getValues(), axis=0))) != 1:
            return False

        for i in range(config.getSize()):
            if numpy.sum(numpy.diagonal(config.getValues(), i)) > 1:
                return False
            if i > 0:
                if numpy.sum(numpy.diagonal(config.getValues(), -i)) > 1:
                    return False

        return True


    def getRoot(self):
        return self._initialState

    def heuristics(self, config):
        values = config.getValues()
        rowConflicts = 0
        columns = numpy.sum(values, axis=1)
        for elem in columns:
            if elem > 1:
                rowConflicts += 1

        columnConflicts = 0
        rows = numpy.sum(values, axis=0)
        for elem in rows:
            if elem > 1:
                columnConflicts += 1

        diagonalConflicts = 0
        for i in range(config.getSize()):
            diagonal = numpy.sum(numpy.diagonal(values, i))
            if diagonal > 1:
                diagonalConflicts += 1
            if i > 0:
                diagonal = numpy.sum(numpy.diagonal(values, -i))
                if diagonal > 1:
                    diagonalConflicts += 1

        return rowConflicts + columnConflicts + diagonalConflicts
