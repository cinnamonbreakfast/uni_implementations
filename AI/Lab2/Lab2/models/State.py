class State:
    def __init__(self):
        self._values = []

    def addValue(self, newValue):
        self._values.append(newValue)

    def getValues(self):
        return self._values[:]

    def __str__(self):
        result = ''
        for i in range(len(self._values)):
            result = 'Move ' + str(i) + '\n' + str(self._values[i]) + '\n' + '\t---------------------\n'

        return result
