from texttable import Texttable

class Board:
    def __init__(self):
        self._table = []

        self._generate()

    def _generate(self):
        for i in range(8):
            self._table.append([' ']*8)

    def get_tile(self, x, y):
        return self._table[x][y]

    def set_tile(self, x, y, obj):
        self._table[x][y] = obj


    def __str__(self):
        return self.text()

    def valid_star(self, x, y, a):
        if a == 1:
            return (x-1, y)
        elif a == 2:
            return (x-1, y+1)
        elif a == 3:
            return (x, y + 1)
        elif a == 4:
            return (x + 1, y + 1)
        elif a == 5:
            return (x + 1, y)
        elif a == 6:
            return (x + 1, y - 1)
        elif a == 7:
            return (x, y - 1)
        elif a == 8:
            return (x-1, y - 1)

    def check_trace(self, x, y, xx, yy):
        if self._table[xx][yy] == "*":
            # print("wat")
            return False

        if x == xx:
            if "*" in self._table[x][min(y, yy):max(y,yy)]:
                # print("wat1")
                return False
        elif y == yy:
            if x < xx:
                for i in range(xx-x):
                    if self._table[x+i][y] == "*":
                        # print("wat2")
                        return False
            else:
                for i in range(x-xx):
                    if self._table[x-i][y] == "*":
                        # print("wat3")
                        return False
        else:
            if abs(xx-x) != abs(yy-y):
                return False
            else:
                for i in range(1, abs(xx-x)):
                    if x < xx:
                        if y < yy:
                            if self._table[x+i][y+i] == "*":
                                return False
                        else:
                            if self._table[x+i][y-i] == "*":
                                return False
                    else:
                        if y < yy:
                            if self._table[x-i][y+i] == "*":
                                return False
                        else:
                            if self._table[x-i][y-i] == "*":
                                return False


        return True


    def text(self):
        T = Texttable()

        L = {
            1:['A'],
            2:['B'],
            3:['C'],
            4:['D'],
            5:['E'],
            6:['F'],
            7:['G'],
            8:['H'],
        }

        T.header([' ', 1, 2, 3, 4, 5, 6, 7, 8])

        for i in range(8):
            T.add_row(L[i+1]+self._table[i])

        return T.draw()
