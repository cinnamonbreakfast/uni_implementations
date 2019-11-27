class Settings:
    def __init__(self, file):
        self.__basefile = file
        self.__repository = None
        self.__SETTINGS = {}

        self.__REPOSITORY = ""
        self.__STUDENTS = ""
        self.__GRADES = ""
        self.__ASSIGNMENTS = ""

        self.__loadfile()
        self._split_attributes()

    def __loadfile(self):
        try:
            f = open(self.__basefile, "r")

            line = f.readline()
            while len(line) > 0:
                if line[0] is not "#":
                    try:
                        pp = line.split("=")

                        if len(pp) > 1:
                            realval = pp[1].strip().replace('"', "")

                            if realval.find("#") is not -1:
                                realval = realval[:realval.find("#")]

                            self.__SETTINGS.update({pp[0].strip(): realval})

                    except Exception as E:
                        raise Exception("Invalid settings file"+str(E))

                line = f.readline()

            f.close()
        except IOError:
            raise Exception("Cannot find settings file!")

    def has_property(self, prop):
        if prop in self.__SETTINGS.keys():
            if self.__SETTINGS[prop] is not "":
                return True
        else:
            return False

    def get_property(self, prop):
        return self.__SETTINGS[prop]

    def _split_attributes(self):
        if "repository" in self.__SETTINGS.keys():
            self.__REPOSITORY = self.__SETTINGS["repository"]

            if self.__REPOSITORY is not "inmemory":
                if "students" in self.__SETTINGS.keys():
                    self.__STUDENTS = self.__SETTINGS["students"]
                else:
                    raise Exception("No file set for students repo")

                if "grades" in self.__SETTINGS.keys():
                    self.__GRADES = self.__SETTINGS["grades"]
                else:
                    raise Exception("No file set for grades repo")

                if "assignments" in self.__SETTINGS.keys():
                    self.__ASSIGNMENTS = self.__SETTINGS["assignments"]
                else:
                    raise Exception("No file set for assignments repo")
        else:
            raise Exception("No repo method set")
