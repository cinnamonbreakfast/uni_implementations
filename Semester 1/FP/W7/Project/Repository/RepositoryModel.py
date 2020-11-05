from Repository.Exception import RepositoryError

from Repository.IterableStructure import DataStructure

from unittest import TestCase
from Models.Student import Student

#import pickle

class Repository(DataStructure):
    def __init__(self):
        DataStructure.__init__(self)



    def get_all(self):
        '''
        Return entire repository raw data
        :return: list
        '''
        return self

    def is_empty(self):
        '''
        Checks if repository is empty
        :return: boolean
        '''
        if len(self) == 0:
            return True
        else:
            return False

    def get(self, obj_id):
        '''
        Look for an object in Repo by ID (unique)
        :param obj_id: object ID in repo
        :return: Object or None if not found
        '''

        for each in self:
            if each.getID() == obj_id:
                return each

    def remove(self, ID):
        '''
        Remove object by unique ID in repository
        :param ID:
        :return:
        '''
        i = 0

        while i < len(self):
            if self[i].getID() == ID:
                del self[i]
            else:
                i += 1



class RepositoryTest(TestCase):
    def setUp(self):
        self.repo = Repository()

    def test_Append(self):
        with self.assertRaises(RepositoryError):
            self.repo.append(Student(1, "Name 1", 1))
            self.repo.append(Student(1, "Name 2", 2))

    def test_get(self):
        self.repo.append(Student(1, "Name", 1))

        self.assertEqual(self.repo.get(1), Student(1, "Name", 1))

    def test_remove(self):
        self.repo.append(Student(1, "Name", 1))

        self.repo.remove(1)

        with self.assertRaises(Exception):
            self.assertEqual(self.repo.get(1), Student(1, "Name", 1))

    def tearDown(self):
        self.repo = None