from math import numpy as np

class Controller:
    
    def __init__(self, problem):
        self.__problem = problem
    
    def DFS(self, root):
        # add a node to visited
        # check whether the configuration is good or not
        #           if it's good, return it
        #       else check the queens positions
        #           if there are all the queens, take another node
        #               else take another node and find the configs and add them to the stack
        viz = []
        vis_stack = [self.__problem.get_init_state()]
        
        while len(vis_stack) > 0:
            node = vis_stack.pop()
            viz = viz + [node]

            if self.__problem.is_solution(node):
                return node, "solution"

            config = node.get_values()[-1]
            if np.sum(config.get_values()) > config.get_size():
                continue
            
            for child_node in self.__problem.expand(node):
                if child_node not in viz:
                    vis_stack.append(child_node)

    def greedy(self, root):
        # generate, apply heuristic function and keep only the best solution
        
        viz = []
        to_visit = [self.__problem.get_init_state()]
        node = None
        
        while len(to_visit) > 0:
            node = to_visit.pop(0)
            viz = viz + [node]
            
            if self.__problem.is_solution(node):
                return node, 'solution'

            config = node.get_values()[-1]
            if np.sum(config.get_values()) > config.get_size():
                continue
            
            children = []
            for child_node in self.__problem.expand(node):
                if child_node not in viz:
                    children.append(child_node)

            children = [[node, self.__problem.heuristic(node)] for node in children]
            children.sort(key=lambda pair: pair[1])

            if len(children) > 0:
                to_visit = copy.deepcopy([children[0][0]]) + to_visit

        return node, '!solution'