from Repository.RepositoryModel import Repository
from Repository.Exception import RepositoryError

import pickle # PICKLE RICK

import os

class PickleRepository(Repository):
    def __init__(self, filename, method):
        Repository.__init__(self)

        self.__filename = filename
        self.__method = method # serves for conversion

        self.__loadfile()

    def append(self, obj):
        Repository.append(self, obj)
        self.__save_file()

    def backup(self):
        self.__save_file()

    def __loadfile(self):
        try:
            f = open(self.__filename, "rb")

            if os.path.getsize(self.__filename) > 0:

                CASTRAVETE = pickle.load(f)

                for each in CASTRAVETE:
                    self.append(each)

            f.close()
        # except IOError as IO:
        #     raise RepositoryError(str(IO))
        except Exception as E:
            f = open(self.__filename, "w")
            f.close()

    def __save_file(self):
        try:
            f = open(self.__filename, "wb")
            pickle.dump(self.get_all(), f)

            f.close()

        except Exception as E:
            raise RepositoryError(E)
