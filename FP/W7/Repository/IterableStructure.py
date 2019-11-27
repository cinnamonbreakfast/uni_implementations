# from Repository.Exception import RepositoryError

from unittest import TestCase
from Models.Student import Student

from copy import deepcopy


class DataStructure:
    def __init__(self):
        self._LIST = []
        self.current = 0
        self.max = 0

    def append(self, obj):
        '''
        Appends an obj (no verification)
        :param obj: object to insert
        :return:
        '''

        self._LIST.append(obj)
        self.max = len(self._LIST)

    def is_empty(self):
        '''
        Checks if repository is empty
        :return: boolean
        '''
        if len(self._LIST) == 0:
            return True
        else:
            return False

    def sort(self, source, criteria):
        i = 0

        TMP = deepcopy(source)

        while i < len(TMP):
            # print("---------------+")
            # print(str(TMP[i]), str(TMP[i-1]))
            # if (pos == 0 or a[pos] >= a[pos - 1]):
            if i == 0 or criteria(TMP[i-1], TMP[i]) is True:
                i += 1
            else:
                TMP[i], TMP[i-1] = TMP[i-1], TMP[i]
                i-=1
                # if i == -1:
                #     i = 0
            # print(str(TMP[i]), str(TMP[i - 1]))
            # print("----------------")


        '''
        Gnom sort pseudo
        
        
        i = 0
        
        while i < len(LIST)
            if i = 0 && criteria(LIST[i-1], LIST[i] = TRUE:
                i += 1
            else:
                LIST[i], LIST[i+1] = LIST[i+1], LIST[i]
                i -= 1
                
        
        '''

        # TODO : statistica
        # TODO : TEST
        # TODO : sort
        # TODO : search
        # TODO : filtering
        # TODO : validation

        '''
        file read:
        f = open("filename", mthd) # mthd = r+, r, rb(binary)
        
        line = f.readline()
        
        while len(line)>0:
            # do stuff on line
            
            line = f.readline()
            
        f.close()
        
        '''

        return TMP

    def filter(self, source, criteria):
        i = 0

        TMP = []

        while i < len(source):
            if criteria(self[i]) is True:
                TMP.append(self[i])
            i += 1

        return TMP

    def __next__(self):
        if self.current < self.max:
            self.current += 1
            return self._LIST[self.current-1]
        else:
            raise StopIteration

    def __getitem__(self, item):
        if item > self.max:
            raise IndexError
        return self._LIST[item]

    def __delitem__(self, key):
        del self._LIST[key]
        self.max = len(self._LIST)

    def __iter__(self):
        self.current = 0
        return self

    def __len__(self):
        return len(self._LIST)

    def __setitem__(self, key, value):
        self._LIST[key] = value

    def __eq__(self, other):
        if len(other) > len(self._LIST) or len(other) < len(self._LIST):
            return False
        else:
            i = 0
            while i < len(other):
                if self._LIST[i] is not other[i]:
                    return False
                else:
                    i += 1
            return True

class RepositoryTest(TestCase):
    '''
        Covered tests:
            * append
            * get
            * set
            * next (iterative obj test)
            * len
            * remove
            * sort
            * filter
    '''

    def setUp(self):
        self.repo = DataStructure()

    def test_append(self):
        self.repo.append(Student(1, "Name 1", 1))
        self.repo.append(Student(1, "Name 2", 2))

    def test_get(self):
        self.repo.append(Student(1, "Name", 1))

        self.assertEqual(self.repo[0], Student(1, "Name", 1))

    def test_set(self):
        self.repo.append(Student(1, "Name", 1))
        self.repo[0] = Student(1, "Name2", 1)

        self.assertEqual(self.repo[0], Student(1, "Name2", 1))

    def test_next(self):
        with self.assertRaises(StopIteration):
            next(self.repo)

    def test_len(self):
        self.repo.append(Student(1, "Name", 1))

        self.assertEqual(len(self.repo), 1)

    def test_remove(self):
        self.repo.append(Student(1, "Name", 1))

        del self.repo[0]

        with self.assertRaises(Exception):
            self.assertEqual(self.repo[0], Student(1, "Name", 1))

    def test_sort(self):
        x = [2, 3, 4, 5]

        self.repo.append(5)
        self.repo.append(2)
        self.repo.append(3)
        self.repo.append(4)

        def ascendent(x, y):
            return x <= y

        self.repo = self.repo.sort(self.repo, ascendent)

        self.assertEqual(self.repo, x)

    def test_filter(self):
        x = [4, 5]

        self.repo.append(4)
        self.repo.append(2)
        self.repo.append(3)
        self.repo.append(5)

        def great4(x):
            return x >= 4

        self.repo = self.repo.filter(self.repo, great4)

        self.assertEqual(self.repo, x)

    def tearDown(self):
        self.repo = None
