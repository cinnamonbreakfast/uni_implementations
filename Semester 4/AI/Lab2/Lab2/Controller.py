import numpy


class Controller:
    def __init__(self, problem):
        self._problem = problem

    def BestFS(self, root):
        visited = []
        toVisit = [root]

        while len(toVisit) > 0:
            node = toVisit.pop(0)
            visited.append(node)
            self._problem._initialState.addValue(node)
            if numpy.sum(numpy.sum(node.getValues(), axis=1)) == node.getSize():
                return node

            aux = []
            for child in self._problem.expand(node):
                if child not in visited:
                    aux.append(child)

            aux = [[child, self._problem.heuristics(child)] for child in aux]
            aux.sort(key=lambda x:x[1])
            aux = [child[0] for child in aux]
            toVisit = aux[:] + toVisit

    def DFS(self, root):
        visited = []
        toVisit = [root]
        while len(toVisit) > 0: #TODO you add a state instead of a configuration! de aia crapa
            node = toVisit.pop()
            visited.append(node)
            self._problem._initialState.addValue(node)
            if self._problem.isSolution(node) and node != root:
                return node

            for child in self._problem.expand(node):
                if child not in visited:
                    toVisit.append(child)