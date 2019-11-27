from random import randint

from unittest import TestCase

class PlayerField:
    def __init__(self):
        self._score = 0
        self._shipinfo = None
        self._gameplay = None
        self._table = None
        self.color = None

        self._shipdata = {
            1: None,
            2: None,
            3: None
        }

    def get_score(self):
        '''
        Return player's score
        :return:
        '''
        return self._score

    def add_score(self, x):
        '''
        Apply score during ships placement stage
        :param x: integer (score to add)
        :return:
        '''
        self._score += x

    def set_shipinfo(self, ships):
        '''
        Set a dictionary full of ships data
        :param ships: dictionary
        :return:
        '''
        self._shipinfo = ships

    def set_table(self, table):
        '''
        Set table class object
        :param table: object
        :return:
        '''
        self._table = table

    def set_color_function(self, fnc):
        '''
        Set a refference to main string-color function.
        :param fnc: function
        :return:
        '''
        self.color = fnc

    def set_gameplay(self, gmp):
        '''
        Set a refference to gameplay object
        :param gmp: object
        :return:
        '''
        self._gameplay = gmp

    def append_shipdata(self, idd, data):
        '''
        Save info about player's ships in tuples
        :param idd: integer (ship id)
        :param data: tuple
        :return:
        '''
        self._shipdata[idd] = data

    def get_shipdata(self, id):
        '''
        Return the tuple data of a placed ship
        :param id: integer (ship id)
        :return: tuple
        '''
        return self._shipdata[id]

    def print_menu(self):
        print("1 : (re)Place ship")
        print("2 : Clear table")

    def print_placeshipfirts(self):
        print("\n\n/!\ The anchor point is represented by first\nblock of the ship. If the placement\ncauses")
        print("the ship to reach out of tablegame, the ship\nwill be automatically placed at the border")
        print("of the table.\n\n")

    def final_notice(self):
        '''
        Player has finished placing the ships. Start or edit map.
        :return:
        '''
        print("\n START to begin the battle.")
        print("Or CLEAR to place some ships again.\n")

        while True:
            cmd = input("> ").strip()

            if cmd == "CLEAR" or cmd == "clear":
                self._table.clear_all()

                self._shipdata = {
                    1: None,
                    2: None,
                    3: None
                }

                self._shipinfo[0] = 3

                self._shipinfo[1][2] = 1
                self._shipinfo[2][2] = 1
                self._shipinfo[3][2] = 1

                self.placeship()

            elif cmd == "test":
                self._table.test()

            elif cmd == "START" or cmd == "start":
                self._gameplay.begin()

                return True

            else:
                print("\nInvalid option.\n")

    def print_placeship(self):
        '''
        Display available ships
        :return:
        '''
        if self._shipinfo[0] is not 0:
            print("Available ships: ")

            for e in self._shipinfo.keys():
                if e is not 0 and self._shipinfo[e][2] is not 0:
                    print("\t"+str(e)+"\tx"+str(self._shipinfo[e][2])+" "+self._shipinfo[e][1])
        else:
            print(self.color("\nYou don't have any ship left to be placed.\n"))
            print(self._shipinfo[0])

    def placeship(self):
        '''
        Ships placement menu
        :return: None
        '''
        poschange = False

        self.print_placeshipfirts()
        pos = {
            'A': 0,
            'B': 1,
            'C': 2,
            'D': 3,
            'E': 4,
            'F': 5,
            'G': 6,
            'H': 7,
            'a': 0,
            'b': 1,
            'c': 2,
            'd': 3,
            'e': 4,
            'f': 5,
            'g': 6,
            'h': 7
        }

        while True:
            poschange = False
            print(self.color(self._table.string_table(), "HEADER"))
            print()

            if self._gameplay.player_placecheck() is True:
                return True

            self.print_placeship()

            shipID = input("Ship ID: ")

            if shipID == "R" or shipID == "random":

                print(self.color("R A N D O M", "OKGREEN"))

                self.randapick()

                continue

            try:
                shipID = int(shipID)

                if shipID not in self._shipinfo.keys():
                    print(self.color("\nInvalid ship id.\n"))
                    continue

                if self._shipinfo[shipID][2] == 0:
                    print("Changing "+self._shipinfo[shipID][1]+" position.")
                    poschange = True
            except:
                print(self.color("\nInvalid ship id.\n"))
                continue

            x = input("x-coord: ")

            if x not in pos.keys():
                print(self.color("\nInvalid x-coord. X can be between A and H.\n"))
                continue
            else:
                x = pos[x]

            y = input("y-coord: ")

            try:
                y = int(y)

                if y < 0 or y > 7:
                    print(self.color("\nInvalid y-coord. Y can be between 0 and 7.\n"))
                    continue
            except:
                print(self.color("\nInvalid y-coord. Y can be between 0 and 7.\n"))
                continue

            deg = input("deg(0/90): ")

            try:
                deg = int(deg)
                if deg == 90:
                    deg = 1
            except:
                print(self.color("\nInvalid ship degree. Degree can be 0 or 90\n", "FAIL"))
                continue

            if deg not in range(2):
                print(self.color("\nInvalid ship degree. Degree can be 0 or 90\n"))
                continue

            if poschange is True:
                old_ship = self.get_shipdata(shipID)

                self._table.clear_old(old_ship[0], old_ship[1], self._shipinfo[shipID][0], deg)
                self._table.place_ship(x, y, self._shipinfo[shipID][0], deg)

                self.append_shipdata(shipID, (x, y, deg))
            else:
                if self._table.free_space(x, y, self._shipinfo[shipID][0], deg) is True:
                    self._table.place_ship(x, y, self._shipinfo[shipID][0], deg)

                    self._shipinfo[0] -= 1
                    self._shipinfo[shipID][2] -= 1

                    self.add_score(self._shipinfo[shipID][0])

                    self.append_shipdata(shipID, (x, y, deg))
                else:
                    print("Cannot overlap ships.")

    def decrement_score(self):
        '''
        Decrement player's score when a ship is hit
        :return:
        '''
        self._score -= 1

    def randapick(self):
        '''
        Randomly place ships
        :return:
        '''
        for i in range(1, 4):
            x = randint(0, 7)
            y = randint(0, 7)
            deg = randint(0, 2)

            print(self._shipinfo[i][1], self._shipinfo[i][2])

            while self._table.free_space(x, y, self._shipinfo[i][0], deg) is False:
                x = randint(0, 7)
                y = randint(0, 7)
                deg = randint(0, 2)

            if self._shipinfo[i][2] == 1:
                self._table.place_ship(x, y, self._shipinfo[i][0], deg)

        self._shipinfo[0] = 0

        self.add_score(9)

        self._shipinfo[1][2] = 0
        self._shipinfo[2][2] = 0
        self._shipinfo[3][2] = 0

    def pick_boom(self):
        '''
        Pick a tile to hit menu.
        :return: x, y
        '''
        while True:
            pos = {
                'A': 0,
                'B': 1,
                'C': 2,
                'D': 3,
                'E': 4,
                'F': 5,
                'G': 6,
                'H': 7,
                'a': 0,
                'b': 1,
                'c': 2,
                'd': 3,
                'e': 4,
                'f': 5,
                'g': 6,
                'h': 7
            }

            coords = input("tile: ").strip()

            if len(coords) != 2:
                print(self.color("\nInvalid tile id. Example: A1 or a1.\n"))
                continue

            # coords = coords.split("")

            x = coords[0]

            if x not in pos.keys():
                print(self.color("\nInvalid x-coord. X can be between A and H.\n"))
                continue

            x = pos[x]

            y = coords[1]

            try:
                y = int(y)

                if y < 0 or y > 7:
                    print(self.color("\nInvalid y-coord. Y can be between 0 and 7.\n"))
                    continue
            except:
                print(self.color("\nInvalid y-coord. Y can be between 0 and 7.\n"))
                continue

            # x = input("x-coord: ")
            #
            # if x not in pos.keys():
            #     print(self.color("\nInvalid x-coord. X can be between A and H.\n"))
            #     continue
            #
            # x = pos[x]
            #
            # y = input("y-coord: ")
            #
            # try:
            #     y = int(y)
            #
            #     if y < 0 or y > 7:
            #         print(self.color("\nInvalid y-coord. Y can be between 0 and 7.\n"))
            #         continue
            # except:
            #     print(self.color("\nInvalid y-coord. Y can be between 0 and 7.\n"))
            #     continue
            #
            return y, x


