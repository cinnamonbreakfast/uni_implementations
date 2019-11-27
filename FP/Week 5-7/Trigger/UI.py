from Models.Student import *
from Models.Assignment import *

'''

            S T R U C T U R E

STUDENT :: ID ; NAME ; GROUP ; ASSIGNS
ASSIGNS :: ID ; DESC ; DEADLINE
GRADE   :: assignID, studentID, grade

'''

class Interface:
    def __init__(self):
        self.__run = True
        self.__menu = {
            1: self.__add_stud,
            2: self.__remove_stud,
            3: self.__update_stud,
            4: self.__list_stud,
            5: self.__add_assign,
            6: self.__remove_assign,
            7: self.__update_assign,
            8: self.__list_assign,
            9: self.__attr_assign
        }

        self.__SR = studentRepository()
        self.__AR = assignmentsRepository()

    def print_menu(self):
        print("1 : Add student")
        print("2 : Remove student")
        print("3 : Update student")
        print("4 : List student")
        print("5 : Add assignment")
        print("6 : Remove assignment")
        print("7 : Update assignment")
        print("8 : List assignment")
        print("9 : Attr assignment")
        print("0 : Exit")

    def __call_action(self, action):
        '''
        Call a function for menu
        :param action: integer
        '''
        self.__menu[action]()

    def start(self):
        ''' T R I G G E R '''

        while self.__run == True:
            self.print_menu()
            cmd = input("> ")

            if cmd == "0":
                print("Close application . . .")
                return

            try:
                cmd = int(cmd)

                if cmd in self.__menu.keys():
                    self.__call_action(cmd)
                else:
                    print("Invalid option")
            except:
                print("Invalid option")
