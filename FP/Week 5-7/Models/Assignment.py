class Assignment:
    def __init__(self, id, descr, duetime):
        self.__ID = id
        self.__DESCR = descr
        self.__DEADLINE = duetime

    def __eq__(self, asg):
        if self.getID() == asg.getID():
            return True
        return False

    def getID(self):
        return self.__ID
    def getDescription(self):
        return self.__DESCR
    def getDeadline(self):
        return self.__DEADLINE

    def setID(self, id):
        self.__ID = id
    def setDescription(self, desc):
        self.__DESCR = str(desc)
    def setDeadline(self, lolbrouaintded):
        self.__DEADLINE = lolbrouaintded #notYet

    def to_string(self):
        return str(self.getID())+" "+self.getDescription()+" "+str(self.getDeadline())