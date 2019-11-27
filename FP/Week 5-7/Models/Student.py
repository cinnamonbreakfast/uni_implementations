from Repositories.exception import *

class Student:
    def __init__(self, ID, NAME, GROUP):
        self.studentID = ID
        self.name = NAME
        self.group = GROUP
        self.asgnGRP = []

    def __eq__(self, stud):
        if self.studentID == stud.getID():
            return True
        return False

    def setID(self, ID):
        self.studentID = ID
    def setName(self, NAME):
        self.name = NAME
    def setGroup(self, GROUP):
        self.group = GROUP

    def getID(self):
        return self.studentID
    def getName(self):
        return self.name
    def getGroup(self):
        return self.group

    def getAssignments(self):
        return self.asgnGRP

    def addAssignment(self, ID):
        for i in self.asgnGRP:
            if i.getID() == ID:
                raise ModelError("Assignment already distributed")

        self.asgnGRP.append(ID)

    def removeAssignment(self, ID):
        i = 0
        while i < len(self.asgnGRP):
            if self.asgnGRP[i] == ID:
                del self.asgnGRP[i]
            else:
                i += 11

    def to_string(self):
        return str(self.getID()) + " " + str(self.getName()) + " " + str(self.getGroup())

