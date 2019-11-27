from Repositories.exception import *

class studentRepository:
    def __init__(self):
        self.LIST_OF = []

    def add(self, stud, pos=-1):
        '''
        Add a student to the list
        :param stud: object
        :param pos: [integer]
        :return: -
        '''
        if isNum(pos):
            if pos>=0:
                self.LIST_OF.insert(pos, stud)
            else:
                self.LIST_OF.append(stud)
        else:
            raise RepositoryError("Invalid position")

    def updateById(self, id, stud):
        '''
        Update an student by ID (keep old ID)
        :param id: integer
        :param stud: object
        :return:
        '''
        found = False
        for s in self.LIST_OF:
            if s.getID() == id:
                s.setID = stud.getID()
                s.setName = stud.getName()
                s.setGroup = stud.getGroup()
                found = True

        if found == True:
            return True
        else:
            raise RepositoryError("Student not found by ID "+str(id))

    def update(self, id, name, group=-0):
        '''
        Update data for student by ID
        :param id: integer
        :param name: string
        :param group: integer
        :return: -
        '''
        if id > len(self.LIST_OF):
            raise RepositoryError("No record in repository by position "+str(id))
        else:
            self.get(id).setName(name)

            if isNum(group):
                if group>0:
                    self.LIST_OF[id].setGroup(group)
                return True
            else:
                raise RepositoryError("Invalid group id, expected integer value")

    def removeById(self, id):
        '''
        Remove by ID
        :param id: integer
        :return: -
        '''
        if not isNum(id):
            raise RepositoryError("Invalid ID value, integer expected")

        found = False
        i = 0

        while i < len(self.LIST_OF):
            if self.LIST_OF[i].getID() == id:
                del self.LIST_OF[i]
                found = True
            else:
                i+=1

        if found == True:
            return True
        else:
            raise RepositoryError("No records in students repository by ID "+str(id))

    def remove(self, id):
        if not isNum(id):
            raise RepositoryError("Invalid ID value, integer expected")

        if id > len(self.LIST_OF):
            raise RepositoryError("Given index is greater than repository records")

        del self.LIST_OF[id]
        return True

    def list(self, group=0):
        '''
        List, optional by group id
        :param group: integer
        :return: list
        '''
        TMP = []
        if not group == 0:
            for data in self.LIST_OF:
                if data.getGroup() == group:
                    TMP.append(data)
        else:
            for data in self.LIST_OF:
                TMP.append(data)

        return TMP

    def findById(self, id):
        '''
        Returns a boolean value if repository contains a student by a given ID.
        :param id: integer
        :return: boolean
        '''

        for data in self.LIST_OF:
            if data.getID()==id:
                return True
        return False

    def get(self, pos):
        if isNum(pos):
            if pos>len(self.LIST_OF) or pos<0 and -pos>len(self.LIST_OF):
                raise RepositoryError("Index out of Repository array")
            else:
                return self.LIST_OF[pos]
        else:
            raise RepositoryError("Invalid position")

    def getByID(self, ID):
        '''
        Get a student by ID
        :param ID: integer
        :return: object
        '''
        if isNum(ID):
            for e in self.LIST_OF:
                if e.getID() == ID:
                    return e

            raise RepositoryError("No such student with id "+str(ID))
        else:
            raise RepositoryError("Invalid ID (integer expected)")


def isNum(val):
    try:
        int(val)
        return True
    except:
        return False