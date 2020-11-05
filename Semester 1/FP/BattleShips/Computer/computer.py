from random import randint
from texttable import Texttable

from unittest import TestCase

'''
Strategy (minimax ce n'est pas possible)
1. Find a hit point
2. Next hit points will be up, right, down left
3. Continue in one direction till it reaches a miss point
4. Go back to hit point in opposite direction
5. Profit
'''


class ComputerField:
    def __init__(self):
        self._tiles = []

        self._hidden_map = []

        self._shipinfo = None

        self.size = 8

        self._score = 9

        self.color = None

        self._generate_table()

        # ALGORITHM

        self._last_good_hit = []
        self._good_hit_step = 0
        self._search_dir = 0
        self._good_hit_jmp = 1

    def set_shipinfo(self, ships):
        '''
        Sets the dictionary of ships data
        :param ships: dictionary
        '''
        self._shipinfo = ships

    def set_color_function(self, fnc):
        '''
        Color function refference
        :param fnc: function
        '''
        self.color = fnc

    def string_table(self):
        '''
        Return a string table of game board
        :return: String
        '''
        t = Texttable()

        t.header([' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'])
        for i in range(self.size):
            t.add_row([i] + self._hidden_map[i])

        return t.draw()

    def new_table(self):
        '''
        Clear data for a new game
        :return:
        '''
        self._generate_table()
        # ALGORITHM

        self._last_good_hit = []
        self._good_hit_step = 0
        self._search_dir = 0
        self._good_hit_jmp = 1


    def good_hit(self, x, y):
        '''
        Remember where a good hit started
        :param x: int
        :param y: int
        :return:
        '''
        self._last_good_hit = (x, y)
        self._good_hit_step = 1
        self._search_dir = 0

    def hitting_step(self):
        '''
        Returns in which direction AI is looking
        :return:
        '''
        return self._good_hit_step

    def looking_level(self):
        '''
        Returns how deep AI went looking
        :return:
        '''
        return self._good_hit_jmp

    def get_score(self):
        '''
        Return computer's score
        :return: integer
        '''
        return self._score

    def dir(self):
        return self._search_dir

    def get_a_hit(self):
        '''
        Return two valid coordinates for computer's turn
        :level: integer (how deep to search)
        :return: integer, integer
        '''
        # print("Direction", self._search_dir, self._good_hit_step)

        print(self._good_hit_step, "Jmp pos", self._search_dir)

        if self._good_hit_step > 0:
            if self._search_dir == 0:
                # UP
                x = self._last_good_hit[0] - self._good_hit_jmp
                y = self._last_good_hit[1]
            elif self._search_dir == 1:
                # RIGHT
                x = self._last_good_hit[0]
                y = self._last_good_hit[1] + self._good_hit_jmp
            elif self._search_dir == 2:
                # DOWN
                x = self._last_good_hit[0] + self._good_hit_jmp
                y = self._last_good_hit[1]
            elif self._search_dir == 3:
                # LEFT
                x = self._last_good_hit[0]
                y = self._last_good_hit[1] - self._good_hit_jmp

            # if self._good_hit_step == 2:
            if self._search_dir == 4 or self._good_hit_step == 2:
                self._good_hit_step = 0
                self._search_dir = 0
                self._good_hit_jmp = 1

            return x, y
        else:
            # print("BOIII")
            x = randint(0, 7)
            y = randint(0, 7)
            return y, x

    def valid_move(self, x, y):
        '''
        Checks if a BOOM is a valid BOOM (not outside of gameboard)
        :param x: int
        :param y: int
        :return: bool
        '''
        if x not in range(0, 8) or y not in range(0, 8):
            return False
        else:
            return True

    def change_dir(self):
        '''
        Next direction (up, right, down, left)
        :return:
        '''
        self._search_dir += 1
        self._good_hit_step = 1
        self._good_hit_jmp = 1
        if self._search_dir == 4:
            self._search_dir = 0
            self.stop_looking()

    def deep_looking(self):
        '''
        AI continue looking
        :return:
        '''
        self._good_hit_jmp += 1

    def opposite_dir(self, opt=0):
        '''
        Change search direction
        :param opt:
        :return:
        '''
        self._good_hit_step = 2
        self._good_hit_jmp = 1
        if self._search_dir == 0:
            self._search_dir = 2
        elif self._search_dir == 1:
            self._search_dir = 3
        elif self._search_dir == 2:
            self._search_dir = 0
        elif self._search_dir == 3:
            self._search_dir = 1

    def stop_looking(self):
        '''
        Stop AI from looking for further good tiles in line
        :return:
        '''
        self._good_hit_step = 0
        self._search_dir = 0
        self._good_hit_jmp = 1

    def boom(self, x, y):
        '''
        Simulate and validate a hit coming from player
        :param x: integer
        :param y: integer
        :return: integer (1 = hit, 2 = already hit, 0 = miss)
        '''

        if self.valid_move(x, y) is True:
            if self._tiles[x][y] == '#':
                self._tiles[x][y] = 'X'
                self._hidden_map[x][y] = 'X'

                self._score -= 1
                return 1
            elif self._tiles[x][y] == 'O' or self._tiles[x][y] == 'X':
                return 2
            else:
                self._hidden_map[x][y] = 'O'
                self._tiles[x][y] = 'O'
                return 0
        else:
            return 3

    def _finish_picking(self):
        print(self.color("\nYour opponent is ready to fight!\n", "OKGREEN"))

    def pick(self):
        '''
        Place ships randomly
        '''
        for i in range(1, 4):
            x = randint(0, 7)
            y = randint(0, 7)
            deg = randint(0, 2)

            while self.free_space(x, y, self._shipinfo[i][0], deg) is False:
                x = randint(0, 7)
                y = randint(0, 7)
                deg = randint(0, 2)

            self.place_ship(x, y, self._shipinfo[i][0], deg)

        self._finish_picking()

    def _generate_table(self):
        '''
        Generate tiles for board
        :return: None
        '''
        for i in range(self.size):
            self._tiles.append([' '] * self.size)
            self._hidden_map.append([' '] * self.size)

    def free_space(self, x, y, lng, deg):
        '''
        Checks for empty space to place a ship
        :param x: integer
        :param y: integer
        :param lng: integer (ship size)
        :param deg: integer (angle)
        :return: boolean (free or not)
        '''
        for i in range(lng):
            if deg == 1:
                if y+lng-1 > self.size - 1:
                    if self._tiles[self.size-lng+i][x] == "#":
                        return False
                else:
                    if self._tiles[y+i][x] == "#":
                        return False
            else:
                if x+lng-1 > self.size - 1:
                    if self._tiles[y][self.size-lng+i] == "#":
                        return False
                else:
                    if self._tiles[y][x + i] == "#":
                        return False


        return True

    def place_ship(self, x, y, lng, deg):
        '''
        Place and mark a ship on table.
        :param x: integer
        :param y: integer
        :param lng: integer (size)
        :param deg: integer (angle)
        '''
        for i in range(lng):
            if deg == 1:
                if y+lng-1 > self.size - 1:
                    self._tiles[self.size-lng+i][x] = "#"
                else:
                    self._tiles[y+i][x] = "#"
            else:
                if x+lng-1 > self.size - 1:
                    self._tiles[y][self.size-lng+i] = "#"
                else:
                    self._tiles[y][x + i] = "#"


class ComputerTestCase(TestCase):
    def setUp(self):
        self.computer = ComputerField()

        self.__ships = {
            0: 3,
            1: [4, "battleship", 1],
            2: [3, "cruiser", 1],
            3: [2, "destroyer", 1]
        }

        self.computer.set_shipinfo(self.__ships)

        def fancy_color(self, msj="", type="NONE"):
            _allow_colors = False
            '''
            Return string messages with fancy colors
            Input: Message, Message type
            '''
            colors = {
                "HEADER": '\033[95m',
                "OKBLUE": '\033[94m',
                "OKGREEN": '\033[92m',
                "WARNING": '\033[93m',
                "FAIL": '\033[91m',
                "ENDC": '\033[0m',
            }
            if _allow_colors is True:
                if type in colors.keys():
                    return colors[type] + msj + colors["ENDC"]
                else:
                    return msj
            else:
                return msj

        self.computer.set_color_function(fancy_color)

    def test_randpick(self):
        self.computer.pick()
        self.assertEqual(self.computer.get_score(), 9)

    def test_get_a_hit(self):
        self.assertEqual(len(self.computer.get_a_hit()), 2)

    def test_free_space(self):
        self.assertEqual(self.computer.free_space(1, 1, 1, 0), True)

    def test_place_ship(self):
        self.assertEqual(self.computer.place_ship(1, 1, 3, 0), None)
        self.assertEqual(self.computer.free_space(1, 1, 1, 0), False)

    def test_string_table(self):
        self.computer.place_ship(2, 2, 2, 1)

        self.assertEqual(self.computer.boom(2, 2), 1)

    def tearDown(self):
        self.player = None
        self.__ships = None
