from unittest import TestCase

class Grade:
    def __init__(self, studid, assignid, grade):
        '''
        Define GRADE object, concat. student ID and assignment ID
        :param studid: int ID of student
        :param assignid: int ID of assignment
        :param grade: int Grade
        '''
        self.__id = str(studid)+"::"+str(assignid)
        self.__grade = grade

    def __eq__(self, asg):
        '''
        Grade logical comparison by ID
        :param stud: Grade object
        :return: Boolean
        '''
        if self.getID() == asg.getID():
            return True
        return False

    def getID(self):
        '''
        Return the ID of a grade (concat.)
        :return: string
        '''
        return self.__id

    def getStudent(self):
        studid = self.__id.split("::")
        return int(studid[0])

    def getAssignment(self):
        '''
        Get the ID of assignment
        :return: int
        '''
        studid = self.__id.split("::")
        return int(studid[1])

    @property
    def getGrade(self):
        '''
        Get grde as float
        :return: float
        '''
        return self.__grade

    @getGrade.setter
    def getGrade(self, grade):
        '''
        Set grade
        :param grade: float
        :return:
        '''
        try:
            grd = float(grade)
        except:
            raise ValueError("Grade must be float")
        self.__grade = grd

    def __str__(self):
        # return str(self.getID())+" "+str(self.getGrade)
        return str(self.getID())+" "+str("{0:.2f}".format(round(self.getGrade,2)))

class GradeTest(TestCase):
    def setUp(self):
        self.grade = Grade(1, 1, 9)

    def test_ID(self):
        self.assertEqual(self.grade.getID(), "1::1")

    def test_Grade(self):
        with self.assertRaises(Exception):
            self.grade.getGrade = 'a'

        self.assertEqual(self.grade.getGrade, 9)

    def test_Assignment(self):
        self.assertEqual(self.grade.getAssignment(), 1)

    def test_Student(self):
        self.assertEqual(self.grade.getStudent(), 1)

    def tearDown(self):
        self.repo = None
