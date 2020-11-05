from unittest import TestCase
from datetime import datetime

class Assignment:
    def __init__(self, id, descr, duetime):
        '''
        Define ASSIGNMENT object by ID, Description and Date (date type)
        :param id: int
        :param descr: str
        :param duetime: date
        '''
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
        try:
            idd = int(id)
        except:
            raise ValueError("ID must be integer")
        self.__ID = idd
    def setDescription(self, desc):
        self.__DESCR = str(desc)
    def setDeadline(self, lolbrouaintded):
        self.__DEADLINE = lolbrouaintded #notYet

    def __str__(self):
        return str(self.getID())+" [ "+self.getDescription()+" ] "+str(self.getDeadline().strftime("%d/%m/%Y"))

class AssignmentTest(TestCase):
    def setUp(self):
        self.asgn = Assignment(1, "Lorem ipsum", datetime(2018, 9, 4))

    def test_ID(self):
        with self.assertRaises(Exception):
            self.asgn.setID("a")

        self.assertEqual(self.asgn.getID(), 1)

    def test_Description(self):
        self.asgn.setDescription(1231132)

        with self.assertRaises(Exception):
            self.assertEqual(self.asgn.getDescription(), "Lorem lorem")

    def test_Deadline(self):
        self.assertEqual(self.asgn.getDeadline(), datetime(2018, 9, 4))

    def tearDown(self):
        self.repo = None


