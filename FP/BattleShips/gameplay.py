from random import choice


class Gameplay:
    def __init__(self, colors, ptable, player, computer):
        self.__player_table = ptable

        self._player = player
        self._computer = computer

        self._allow_colors = colors

        self.__ships = {
            0: 3,
            1: [4, "battleship", 1],
            2: [3, "cruiser", 1],
            3: [2, "destroyer", 1]
        }

        self._player.set_shipinfo(self.__ships)
        self._computer.set_shipinfo(self.__ships)

        self._player.set_table(self.__player_table)

        self._player.set_color_function(self.fancy_color)
        self._computer.set_color_function(self.fancy_color)

        self._player.set_gameplay(self)

        self.welcome()

    def fancy_color(self, msj="", type="NONE"):
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
        if self._allow_colors is True:
            if type in colors.keys():
                return colors[type] + msj + colors["ENDC"]
            else:
                return msj
        else:
            return msj

    def welcome(self):
        '''
        Welcome message + menu
        :return: None
        '''
        print(self.fancy_color("Welcome to B A T T L E S H I P S!", "OKGREEN"))
        print("1 : Start new game")
        print("0 : Exit game")

        while True:
            cmd = input(":> ")

            if cmd == "1":
                self._computer.new_table()
                self.__player_table.clear_all()

                self.__ships[0] = 3

                self.__ships[1][2] = 1
                self.__ships[2][2] = 1
                self.__ships[3][2] = 1

                self._player.placeship()
            elif cmd == "0":
                return True
            else:
                print("Invalid option.")

    def player_place_ship(self, ids, x, y, deg):
        #TODO : delete - useless
        if self.__player_table.freespace(x, y, self.__ships[ids][0], deg) is True:
            self.__player_table.place_ship(x, y, self.__ships[ids][0], deg)
            self.__ships[0] -= 1

            self.player_placecheck()
        else:
            raise Exception("Cannot overlap ships.")

    def begin(self):
        '''
        Start the battle
        :return:
        '''
        self._computer.pick()

        shot = False

        hit = -1

        player_turn = choice((True, False))

        pcx, pcy = 0, 0

        pos = {
            0: 'A',
            1: 'B',
            2: 'C',
            3: 'D',
            4: 'E',
            5: 'F',
            6: 'G',
            7: 'H',
        }

        test = 0

        while self._computer.get_score() != 0 and self._player.get_score() != 0:
            # print(test, player_turn)

            if player_turn is True:
                print(self.fancy_color(self._computer.string_table(), "FAIL"))
                print(self.fancy_color(self.__player_table.string_table(), "OKGREEN"))

                if shot == True:
                    if hit == 1:
                        print(self.fancy_color("\nYour opponent fired at "+pos[pcy]+str(pcx)+"", "FAIL"))
                    elif hit == 0:
                        print(self.fancy_color("\nYour opponent missed at " + pos[pcy] + str(pcx) + "", "OKGREEN"))
                print(self.fancy_color("Your turn!.\n", "OKGREEN"))

                x, y = self._player.pick_boom()

                her = self._computer.boom(x, y)

                while her == 2:
                    print("\nThat tile was already shot once.\n")

                    x, y = self._player.pick_boom()
                    her = self._computer.boom(x, y)

                player_turn = False
            else:
                test += 1
                shot = True
                pcx, pcy = self._computer.get_a_hit()

                # print(hit, pcx, pcy, self._computer.hitting_step(), self._computer.dir())

                hit = self.__player_table.boom(pcx, pcy)

                while hit == 2 or hit == 3:
                    if hit == 3:
                        if self._computer.hitting_step() == 0:
                            self._computer.opposite_dir()
                        elif self._computer.hitting_step() == 2:
                            self._computer.stop_looking()
                        elif self._computer.hitting_step() == 1:
                            if self._computer.looking_level() > 1:
                                self._computer.opposite_dir()
                            else:
                                self._computer.change_dir()
                    else:
                        if self._computer.hitting_step() == 1:
                            self._computer.change_dir()
                        else:
                            self._computer.stop_looking()

                    pcx, pcy = self._computer.get_a_hit()

                    hit = self.__player_table.boom(pcx, pcy)

                    # print(hit, pcx, pcy, self._computer.hitting_step(), self._computer.dir())

                if hit == 1: # HERE
                    if self._computer.hitting_step() == 1:
                        self._computer.deep_looking()
                    elif self._computer.hitting_step() == 0:
                        self._computer.good_hit(pcx, pcy)
                    elif self._computer.hitting_step() == 2:
                        self._computer.deep_looking()
                elif hit == 0:
                    if self._computer.hitting_step() == 1:
                        if self._computer.looking_level() == 1:
                            self._computer.change_dir()
                        else:
                            self._computer.opposite_dir()

                player_turn = True

        print(self.fancy_color("\n\nG A M E    O V E R\n\n", "FAIL"))

        if self._computer.get_score() == 0:
            print(self.fancy_color("YOU WON!", "OKGREEN"))
            print(self.fancy_color("YOU WON!", "OKGREEN"))
            print(self.fancy_color("YOU WON!", "OKGREEN"))
        else:

            print(self.fancy_color("YOU LOST!", "FAIL"))


        # TODO: this was at # here

        # if self._computer.hitting_step() > 0:
        #     self._computer.good_hit(pcx, pcy)
        # else:
        # self._computer.good_hit(pcx, pcy)


    def player_placecheck(self):
        '''
        Checks if player has finished placing the ships
        :return: boolean
        '''
        if self.__ships[0] == 0:
            self._player.final_notice()
            return True
        return False
