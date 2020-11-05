from Player.player import PlayerField
from Table.playerTable import TableGame
from Computer.computer import ComputerField

from gameplay import Gameplay


_human_player = PlayerField()
_human_table = TableGame(_human_player)
_computer_player = ComputerField()

allow_colors = True

_game_brain = Gameplay(allow_colors, _human_table, _human_player, _computer_player)