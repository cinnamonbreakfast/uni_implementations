from texttable import Texttable

from unittest import TestCase
from Player.player import PlayerField


class TableGame:
    def __init__(self, player):
        self._tiles = []
        self._playerOBJ = player

        self.size = 8

        self._generate_table()

    def free_space(self, x, y, lng, deg):
        '''
        Check if tiles are free to place a ship.
        :param x: integer
        :param y: integer
        :param lng: integer (size)
        :param deg: integer (angle)
        :return: boolean
        '''
        #print("player ", x, y, lng)
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

    def clear_old(self, x, y, lng, deg):
        '''
        Clear an old position of a ship when placing it again.
        :param x: integer
        :param y: integer
        :param lng: integer (size)
        :param deg: integer (angle)
        :return:
        '''
        for i in range(lng):
            if deg == 1:
                if y+lng-1 > self.size - 1:
                    self._tiles[self.size-lng+i][x] = " "
                else:
                    self._tiles[y+i][x] = " "
            else:
                if x+lng-1 > self.size - 1:
                    self._tiles[y][self.size-lng+i] = " "
                else:
                    self._tiles[y][x + i] = " "

    def clear_all(self):
        '''
        Clear the board
        :return:
        '''
        for i in range(self.size):
            for j in range(0, self.size):
                self._tiles[i][j] = " "

    def place_ship(self, x, y, lng, deg):
        '''
        Place a ship on a given position & angle.
        :param x: integer
        :param y: integer
        :param lng: integer (size)
        :param deg: integer (angle)
        :return:
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

    def _generate_table(self):
        '''
        Generate tiles for board
        :return: None
        '''
        for i in range(self.size):
            self._tiles.append([' '] * self.size)

    def string_table(self):
        '''
        Return a string table of game board
        :return: String
        '''
        t = Texttable()

        t.header([' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'])
        for i in range(self.size):
            t.add_row([i] + self._tiles[i])

        return t.draw()

    def __str__(self):
        '''
        Table as string
        :return: string
        '''
        return self.string_table()

    def valid_move(self, x, y):
        if x not in range(0, 8) or y not in range(0, 8):
            return False
        else:
            return True

    def boom(self, x, y):
        '''
        Check and mark a hit coming from computer.
        :param x: integer
        :param y: integer
        :return: integer (1 = hit, 2 = already hit, 0 = miss)
        '''
        # print("attempt "+self._tiles[x][y], x, y)
        if self.valid_move(x, y) is True:
            if self._tiles[x][y] == '#':
                self._tiles[x][y] = 'X'

                self._playerOBJ.decrement_score()
                return 1
            elif self._tiles[x][y] == 'O' or self._tiles[x][y] == 'X':
                return 2
            else:
                self._tiles[x][y] = 'O'
                return 0
        else:
            return 3

class PlayerTableTest(TestCase):
    def setUp(self):
        self.player = PlayerField()
        self.table = TableGame(self.player)

    def test_free_space(self):
        self.assertEqual(self.table.free_space(1, 1, 1, 0), True)

    def test_clear(self):
        self.assertEqual(self.table.clear_old(1, 1, 1, 0), None)

    def test_clear_all(self):
        self.assertEqual(self.table.clear_all(), None)

    def test_place_ship(self):
        self.assertEqual(self.table.place_ship(1, 1, 3, 0), None)
        self.assertEqual(self.table.free_space(1, 1, 1, 0), False)

    def test_string_table(self):
        self.table.place_ship(2, 2, 2, 1)

        self.assertEqual(self.table.boom(2, 2), 1)

    def test_valid(self):
        self.assertEqual(self.table.valid_move(2, 3), True)
        self.assertEqual(self.table.valid_move(2, -1), False)

    def tearDown(self):
        self.table = None
        self.player = None
