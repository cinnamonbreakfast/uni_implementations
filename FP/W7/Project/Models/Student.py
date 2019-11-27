from unittest import TestCase

class Student:
    def __init__(self, ID, NAME, GROUP):
        '''
        Define STUDENT object
        :param ID: INT
        :param NAME: STR
        :param GROUP: INT
        '''
        self.studentID = ID
        self.name = NAME
        self.group = GROUP
        self.asgnGRP = []

    def __eq__(self, stud):
        '''
        Student logical comparison by ID
        :param stud: Student object
        :return: Boolean
        '''
        if self.studentID == stud.getID():
            return True
        return False

    def setID(self, ID):
        try:
            id = int(ID)
        except:
            raise ValueError("ID must be integer")

        self.studentID = id

    def setName(self, NAME):
        self.name = NAME

    def setGroup(self, GROUP):
        try:
            grp = int(GROUP)
        except:
            raise ValueError("Group ID must be integer")
        self.group = grp

    def getID(self):
        return self.studentID

    def getName(self):
        return self.name

    def getGroup(self):
        return self.group

    def __str__(self):
        return str(self.getID()) + " " + str(self.getName()) + " " + str(self.getGroup())

class StudentTest(TestCase):
    def setUp(self):
        self.asgn = Student(1, "Adrian Minune", 911)

    def test_ID(self):
        with self.assertRaises(Exception):
            self.asgn.setID("a")

        self.assertEqual(self.asgn.getID(), 1)

    def test_Name(self):
        self.asgn.setName(1231132)

        with self.assertRaises(Exception):
            self.assertEqual(self.asgn.setName(), "Adrian Minune")

    def test_Group(self):
        with self.assertRaises(Exception):
            self.asgn.setGroup("a")

        self.assertEqual(self.asgn.getGroup(), 911)

    def tearDown(self):
        self.repo = None
