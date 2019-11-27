from player_commands import Player
from random import randint
from copy import deepcopy

from board import Board

class Gameplay:
    def __init__(self, player, board, test = False):
        self._player = player
        self._board = board

        self._stars = []
        self._B = []
        self._E = []

        self._b = 3

        self._game = True
        self._cheatmode = False

        if test == False:
            self.welcome()

    def welcome_message(self):
        print("Welcome!")
        print("1 : Start game")
        print("0 : Exit")
        #print(self._board.make_text())

    def welcome(self):
        self.welcome_message()
        while True:
            cmd = input("> ")

            if cmd == "1":
                print("\n\n NEW GAME \n\n")
                self.start()

            elif cmd == "0":
                return True

            else:
                print("\nInvalid option.\n")

    def add_star(self, x, y):
        self._stars.append((x, y))

        self._board.set_tile(x, y, "*")

    def place_stars(self):
        j = 1

        good = True

        while j <= 10:
            x = randint(0, 7) # letter
            y = randint(0, 7)

            good = True


            if self._board.get_tile(x, y) == " ":
                for i in range(1, 9):
                    pos = self._board.valid_star(x, y, i)

                    if pos[0] in range(8) and pos[1] in range(8):
                        if self._board.get_tile(pos[0], pos[1]) == "*":

                            good = False
                            break

                if good == True:
                    self.add_star(x, y)
                    j += 1

    def place_E(self):
        while True:
            x = randint(0, 7) # letter
            y = randint(0, 7)

            if self._board.get_tile(x, y) == " ":
                self._board.set_tile(x, y, "E")
                self._E = [x, y]
                return True

    def move_E(self, x, y):
        self._board.set_tile(self._E[0], self._E[1], " ")
        self._board.set_tile(x, y, "E")
        self._E = [x, y]

    def place_B(self, mood = False):
        i = 0

        old_B = deepcopy(self._B)

        if mood == True:
            while i < len(self._B):
                # print(self._B[i][0], self._B[i][1], self._b, i)
                self._board.set_tile(self._B[i][0], self._B[i][1], " ")
                i += 1

        self._B = []

        i = 0

        while True and i != self._b:
            # print("loop")
            x = randint(0, 7) # letter
            y = randint(0, 7)

            if self._board.get_tile(x, y) == " " and (x, y) not in self._B:
                if self._cheatmode == True:
                    self._board.set_tile(x, y, "B")

                self._B.append((x, y))

                i += 1

        # print(self._B)

    def _show_B(self):
        self._cheatmode = True
        for i in range(len(self._B)):
            B = self._B[i]
            self._board.set_tile(B[0], B[1], "B")

    def _gameover(self):
        self._game = False
        print("\n\n\nY O U     L O S T!\n\n\n")

    def _run_command(self, cmd):
        '''
        Takes a command line, validate and execute it.
        :param cmd: string
        :return: None
        '''

        L = {
            0 : ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
            'A' : 0,
            'B' : 1,
            'C' : 2,
            'D' : 3,
            'E' : 4,
            'F' : 5,
            'G' : 6,
            'H' : 7

        }

        line = cmd.split(" ")
        param  = line[1].upper()

        x = 0
        y = 0

        if line[0] == "warp":
            try:
                if param[0] in L.keys():
                    x = L[param[0]]
                else:
                    return 1

                y = int(param[1]) - 1

                if y not in range(8):
                    raise ValueError
            except:
                return 1

            if self._board.check_trace(self._E[0], self._E[1], x, y) == True:
                if (x, y) in self._B:
                    self._gameover()
                self.move_E(x, y)
            else:
                return 2

        elif line[0] == "fire":
            try:
                if param[0] in L.keys():
                    x = L[param[0]]
                else:
                    return 1

                y = int(param[1]) - 1

                if y not in range(8):
                    raise ValueError
            except:
                return 1

            if self._board.get_tile(x, y) == "B" or (x, y) in self._B:
                if abs(self._E[0]-x) <= 1 and abs(self._E[1]-y) <= 1:
                    i = 0

                    self._b -= 1

                    while i < len(self._B):
                        if self._B[i] == (x, y):
                            del self._B[i]
                            self._board.set_tile(x, y, " ")
                        else:
                            i += 1

                    self.place_B(True)

                    return 5
                else:
                    return 4

        else:
            return False

        return 3

    def start(self):
        self.place_stars()
        self.place_E()
        self.place_B()

        if self._b == 0:
            print("\n\n\nY O U     W O N!")
            print("Y O U     W O N!")
            print("Y O U     W O N!")



        while True and self._game:
            print(self._board)

            cmd = input("> ").strip()

            if cmd == "cheat":
                print("Come on, lil cheater...")
                self._show_B()

            elif cmd.find(' ') != -1:
                msg = self._run_command(cmd)

                if msg == 1:
                    print("\nInvalid coordinates(A-H, 1-8).\n")
                elif msg == 2:
                    print("\nCant go that way.\n")
                elif msg == 3:
                    print("\nWarping...\n")
                elif msg == 4:
                    print("\nCannot fire that far.\n")
                elif msg == 5:
                    print("\nGood hit!.\n")
                elif msg == False:
                    print("\nInvalid command.\n")


            else:
                print("\nInvalid command.\n")





def test():
    print("\n\n\n\nRUN IN TEST MODE\n\n\n\n")
    P = Player()
    B = Board()
    GP = Gameplay(P, B, True)

    GP.place_stars()
    GP.place_E()
    GP.place_B()

    L = {
        0: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
        'A': 0,
        'B': 1,
        'C': 2,
        'D': 3,
        'E': 4,
        'F': 5,
        'G': 6,
        'H': 7

    }

    i = 0

    while True and i < 10:
        x = randint(0, 7)
        y = randint(0, 7)
        if B.get_tile(x, y) == " ":
            print("Assert " + str(i+1) + " ok")
            assert GP._run_command("warp "+L[0][x]+str(y))


            print(GP._run_command("warp "+L[0][x]+str(y)))

            # print(B)

            i += 1


if __name__ == "__main__":
    test()






