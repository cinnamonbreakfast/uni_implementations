from configuration import Configuration
from state import State
from problem import Problem
from controller import Controller
from time import time
from math import numpy as np

class UI:
    
    def __init__(self):
        self.__init_config = Configuration(np.zeros((4,4), dtype=int))
        self.__p = Problem(self.__init_config)
        self.__contr = Controller(self.__p)
    
    def printMainMenu(self):
        print("0 : Exit")
        print("1 : Change size")
        print("2 : DFS")
        print("3 : Greedy")
    
    def change_size(self):
        try:
            n = int(input('Input new board size:'))
            self.__init_config = Configuration(np.zeros((n, n), dtype=int))
            self.__p = Problem(self.__init_config)
            self.__contr = Controller(self.__p)
        except:
            print('Invalid number, size remains 4')

    def dfs(self):
        (state, message) = self.__contr.dfs()
        print(state)
        print(message)

    def greedy(self):
        (state, message) = self.__contr.greedy()
        print(state)
        print(message)
   
    def run(self):
        runM=True
        self.printMainMenu()
        while runM:        
            try: 
                command = int(input(">>"))
                if command == 0:
                    runM = False
                elif command == 1:
                    self.change_size()
                elif command == 2:
                    self.dfs()
                elif command == 3:
                    self.greedy()          
            except:
                print('invalid command')

def main():
    ui = UI()
    ui.run()


main()