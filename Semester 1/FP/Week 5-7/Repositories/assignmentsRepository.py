from Repositories.exception import *
from copy import copy

class assignmentRepo:
    def __init__(self):
        pass

class assignmentsRepository:
    def __init__(self):
        self.AS_LIST = {}
        self.GROUP = {}
        self.ASSZ = {}

    def list(self):
        '''
        Return a list of the assignments
        :return: list
        '''
        TMP = []

        for e in self.ASSZ.keys():
            TMP.append(self.ASSZ[e])

        return TMP

    def add(self, asg):
        '''
        Add an assignment
        :param asg: object
        :return: none
        '''
        if not asg.getID() in self.ASSZ.keys():
            self.ASSZ.update({asg.getID(): asg})
        else:
            raise RepositoryError("Assignment already exists")

    def get(self, ID):
        '''
        Return the assignment as object by ID
        :param ID: integer
        :return: object
        '''
        if ID in self.ASSZ.keys():
            return self.ASSZ[ID]
        else:
            raise RepositoryError("No such assignment by ID "+str(ID))

    def find(self, ID):
        '''
        Find an assignment by ID
        :param ID: integer
        :return: boolean
        '''
        if ID in self.ASSZ.keys():
            return True
        else:
            return False

    def removeAsg(self, asgID):
        '''
        Remove assignment by ID
        :param asgID: integer
        :return: -
        '''
        if asgID in self.ASSZ.keys():
            self.remove(self.ASSZ[asgID])
            del self.ASSZ[asgID]
        else:
            raise RepositoryError("No assignment found by given ID")

    def remove(self, assign):
        '''
        Remove assignment by comparing objects
        :param assign: object
        :return: -
        '''
        i = 0
        for e in self.AS_LIST.keys():
            while i < len(self.AS_LIST[e]):
                if self.AS_LIST[e][i] == assign:
                    del self.AS_LIST[e][i]
                else:
                    i += 1

        i = 0

        for e in self.GROUP.keys():
            while i < len(self.GROUP[e]):
                if self.GROUP[e][i] == assign:
                    del self.GROUP[e][i]
                else:
                    i += 1

    def removeID(self, id, assign):
        '''
        Remove assignment for student
        :param id: integer
        :param assign: object
        :return: -
        '''
        if id in self.AS_LIST.keys():
            i = 0
            while i < len(self.AS_LIST[id]):
                if self.AS_LIST[id][i] == assign:
                    del self.AS_LIST[id][i]
                else:
                    i += 1
        else:
            raise RepositoryError("Assignment not assigned here")

    def removeGroup(self, grid, assign):
        '''
        Remove assignment for group by group ID
        :param grid: integer
        :param assign: object
        :return: -
        '''
        if grid in self.GROUP.keys():
            i = 0
            while i < len(self.GROUP[grid]):
                if self.GROUP[grid][i] == assign:
                    del self.GROUP[grid][i]
                else:
                    i += 1
        else:
            raise RepositoryError("Assignment not assigned here")


    def assignToID(self, id, assign):
        '''
        Assign an assignment to a student by ID.
        :param id: INTEGER
        :param assign: INTEGER
        :return: boolean
        '''
        if id in self.AS_LIST.keys():
            for asg in self.AS_LIST[id]:
                if asg == assign:
                    raise RepositoryError("Assignment already assigned to this student")

            self.AS_LIST[id].append(assign)
        else:
            self.AS_LIST.update({id : [assign]})

        return True

    def debo(self):
        print(self.AS_LIST)
        print(self.GROUP)

    def getAssignedToID(self, id):
        '''
        Return a copy of list of assignments for a student by ID
        :param id: integer
        :return: list
        '''
        if id in self.AS_LIST.keys():
            return copy(self.AS_LIST[id])
        else:
            raise RepositoryError("No assignments for this ID")

    def getAssignedToGroup(self, groupID):
        '''
        Return a copy of list of assignments for a group by ID
        :param groupID: integer
        :return: list
        '''
        if groupID in self.GROUP.keys():
            return copy(self.GROUP[groupID])
        else:
            raise RepositoryError("No assignments for this group")

    def assignToGroup(self, group, assi):
        '''
        Assign to a group, refference needed!!!
        :param group: list
        :return: boolean
        '''
        groupID = group[0].getGroup()

        if groupID in self.GROUP.keys():
            self.GROUP[groupID].append(assi)
        else:
            self.GROUP.update({groupID : [assi]})

        for each in group:
            if each.getID() in self.AS_LIST.keys():
                for asg in self.AS_LIST[each.getID()]:
                    if not asg == assi:
                        self.AS_LIST[each.getID()].append(assi)
            else:
                self.AS_LIST.update({each.getID(): [assi]})

    def updateID(self, id, asg1, asg2):
        '''
        Replace an assignment by other
        :param id: integer
        :param asg1: object
        :param asg2: object
        :return: -
        '''
        if id in self.AS_LIST.keys():
            i = 0
            while i < len(self.AS_LIST[id]):
                if self.AS_LIST[id][i] == asg1:
                    self.AS_LIST[id][i] = asg2
                else:
                    i += 1
        else:
            raise RepositoryError("This assignment isn't assigned here")

    def updateGroup(self, gid, asg1, asg2):
        '''
        Replace an assignment for a group by other
        :param gid: integer
        :param asg1: object
        :param asg2: object
        :return:
        '''
        if gid in self.GROUP.keys():
            i = 0
            while i < len(self.GROUP[gid]):
                if self.GROUP[gid][i] == asg1:
                    self.GROUP[gid][i] = asg2
                else:
                    i += 1
        else:
            raise RepositoryError("This assignment isn't assigned to this group")

