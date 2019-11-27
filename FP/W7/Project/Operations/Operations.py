from Models.Student import Student
from Models.Assignment import Assignment
from Models.Grade import Grade
from Models.DTO import DTO

from Repository.Exception import RepositoryError

from datetime import datetime

from History.TimelineRun import FunctionCall, Operation, Cascade

import operator

from copy import deepcopy

# TODO : pass the sem...


class Operator:
    def __init__(self, timeline, std, asg, grd):
        '''    INIT OPERATIONS ON REPOSITORIES     '''
        self.__stud_repo = std
        self.__assign_repo = asg
        self.__grade_repo = grd
        self.__timeline = timeline

    # STUDENT BEGIN

    def create_student(self, id, name, group):
        '''
        Create a student object
        :param id: int ID of the student
        :param name: string Name
        :param group: int ID of group
        :return: Student object
        '''
        return Student(id, name, group)

    def add_student(self, student):
        '''
        Add a student to Students repo
        :param student: student object
        :return: True if succeded
        :exception:
                - Repository : ID not unique
        '''
        if self.__stud_repo.get(student.getID()) is None:
            self.__stud_repo.append(student)

            undo = FunctionCall(self.remove_student, student.getID())
            redo = FunctionCall(self.add_student, deepcopy(student))

            op = Operation(undo, redo)

            self.__timeline.append(op)

            try:
                self.__stud_repo.backup()
            except:
                pass

            return True
        else:
            raise RepositoryError("ID " + str(student.getID()) + " already in use!")

    def remove_student(self, ID):
        '''
        Remove a student from Repo by ID
        :param ID: int ID
        :return: True if succeded
        :exception:
                - Repository: No records for given ID
        '''
        if self.__stud_repo.get(ID) is None:
            raise RepositoryError("No student for given ID, "+str(ID))
        else:
            CSC = Cascade()

            undo = FunctionCall(self.add_student, deepcopy(self.__stud_repo.get(ID)))
            redo = FunctionCall(self.remove_student, ID)

            self.__stud_repo.remove(ID)
            self.remove_grade_student(ID, CSC) # REMOVE FROM GRADE
            op = Operation(undo, redo)

            CSC.append(op)

            self.__timeline.append(CSC)

            try:
                self.__stud_repo.backup()
            except:
                pass

            return True

    def update_student_name(self, ID, name):
        '''
        Update a student's NAME by given ID
        :param ID: int ID of student
        :param name: string NAME
        :return: None
        :exception:
                - Repository: No records for given ID
        '''
        if self.__stud_repo.get(ID) is not None:
            undo = FunctionCall(self.update_student_name, ID, self.__stud_repo.get(ID).getName())
            redo = FunctionCall(self.update_student_name, ID, name)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            self.__stud_repo.get(ID).setName(name)


            try:
                self.__stud_repo.backup()
            except Exception as E:
                print(E)

        else:
            raise RepositoryError("No student for given ID, "+str(ID))

    def update_student_group(self, ID, group):
        '''
        Update a student's GROUP id
        :param ID: int ID of student
        :param group: new GROUP ID
        :return: None
        :exception:
                - Repository: No records for given ID
        '''
        if self.__stud_repo.get(ID) is not None:
            undo = FunctionCall(self.update_student_group, ID, self.__stud_repo.get(ID).getGroup())
            redo = FunctionCall(self.update_student_group, ID, group)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            self.__stud_repo.get(ID).setGroup(group)

            try:
                self.__stud_repo.backup()
            except:
                pass

        else:
            raise RepositoryError("No student for given ID, "+str(ID))

    def get_student_assignments(self, ID):
        '''
        Get a student assignments list
        :param ID: int ID of student
        :return: list of assignments IDs
        '''
        DB = self.__grade_repo.get_all()

        TMP = []

        for each in DB:
            if each.getStudent() == ID:
                TMP.append(each.getAssignment())

        return TMP

    # STUDENT END

    # ASSIGNMENT BEGIN

    def create_assignment(self, id, descr, deadline):
        '''
        Create an ASSIGNMENT object
        :param id: unique int ID
        :param descr: string Description
        :param deadline: date type Deadline
        :return: Assignment object
        '''
        return Assignment(id, descr, deadline)

    def add_assignment(self, assign):
        '''
        Add an assignment to assignments repo
        :param assign: Assignment object
        :return: True if succeded
        :exception:
                - Repository: ID already in ussage
        '''
        if self.__assign_repo.get(assign.getID()) is None:
            self.__assign_repo.append(assign)

            undo = FunctionCall(self.remove_assignment, assign.getID())
            redo = FunctionCall(self.add_assignment, deepcopy(assign))

            op = Operation(undo, redo)

            self.__timeline.append(op)

            try:
                self.__assign_repo.backup()
            except:
                pass

            return True
        else:
            raise RepositoryError("ID already in use!")

    def remove_assignment(self, ID):
        '''
        Remove an assignment from repo by ID
        :param ID: int ID of assignment
        :return: True if succeded
        :exception:
                - Repository: No records for given ID
        '''
        if self.__assign_repo.get(ID) is None:
            raise RepositoryError("No assignment for given ID!")
        else:
            self.__assign_repo.remove(ID)
            #.remove_grade_assign(ID) # remove from grade repo

            CSC = Cascade()

            undo = FunctionCall(self.add_assignment, deepcopy(self.__assign_repo.get(ID)))
            redo = FunctionCall(self.remove_assignment, ID)

            self.__assign_repo.remove(ID)
            self.remove_grade_assign(ID, CSC)  # REMOVE FROM GRADE
            op = Operation(undo, redo)

            CSC.append(op)

            self.__timeline.append(CSC)

            try:
                self.__assign_repo.backup()
            except:
                pass

            return True

    def update_assignment_descr(self, ID, desc):
        '''
        Update assignment description by ID
        :param ID: int ID of assignment
        :param desc: new string Description
        :return: None
        :exception:
                - Repository: No records for given ID
        '''
        if self.__assign_repo.get(ID) is not None:
            undo = FunctionCall(self.update_assignment_descr, ID, self.__assign_repo.get(ID).getDescription())
            redo = FunctionCall(self.update_assignment_descr, ID, desc)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            self.__assign_repo.get(ID).setDescription(desc)

            try:
                self.__assign_repo.backup()
            except:
                pass

        else:
            raise RepositoryError("No assignment for given ID")

    def update_assignment_deadline(self, ID, date):
        '''
        Update assignment's deadline by ID
        :param ID: int ID of assignment
        :param date: date type Deadline
        :return: None
        :exception:
                - Repository: No records for given ID
        '''
        if self.__assign_repo.get(ID) is not None:
            undo = FunctionCall(self.update_assignment_deadline, ID, self.__assign_repo.get(ID).getDeadline())
            redo = FunctionCall(self.update_assignment_deadline, ID, date)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            self.__assign_repo.get(ID).setDeadline(date)

            try:
                self.__assign_repo.backup()
            except:
                pass

        else:
            raise RepositoryError("No assignment for given ID")

    def allocate_assign_group(self, ID, target):
        '''
        Allocate an assignment for an entire group by IDs (no conflicts)
        :param ID: int ID of assignment
        :param target: int ID of group
        :return: None
        '''
        TMP = self.__stud_repo.get_all()

        CSC = Cascade()

        undo = FunctionCall(self.remove_grade_assign, ID, CSC)
        redo = FunctionCall(self.allocate_assign_group, ID, target)

        op = Operation(undo, redo)

        CSC.append(op)

        self.__timeline.append(op)

        for each in TMP:
            if each.getGroup() == target:
                link = self.create_grade(each.getID(), ID)

                if self.__grade_repo.get(link.getID()) is None:
                    self.__grade_repo.append(link)

        try:
            self.__grade_repo.backup()
        except:
            pass

    def allocate_assign_student(self, ID, target):
        '''
        Allocate an assignment for a student by ID
        :param ID: int ID of assignment
        :param target: int ID of student
        :return: None
        '''
        link = self.create_grade(target, ID)

        if self.__grade_repo.get(link.getID()) is None:
            self.__grade_repo.append(link)

            #print(link.getID())
            undo = FunctionCall(self.remove_grade_obj, link.getID())
            redo = FunctionCall(self.allocate_assign_student, ID, target)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            try:
                self.__grade_repo.backup()
            except:
                pass

            return True
        else:
            raise RepositoryError("ID already in use!")

    # ASSIGNMENT END

    # GRADE BEGIN
    def create_grade(self, studid, asgid, grade=-1):
        '''
        Create a Grade object
        :param studid: int ID of student
        :param asgid: int ID of assignment
        :param grade: optional int Grade
        :return: Grade object
        '''
        return Grade(studid, asgid, grade)

    def remove_grade_assign(self, ID, CSC):
        '''
        Remove grades for given assignment ID
        :param ID: int ID of assignment
        :return: None
        '''
        TMP = self.__grade_repo.get_all()

        i = 0
        while i < len(TMP):
            if TMP[i].getAssignment() == ID:
                redo = FunctionCall(self.remove_grade_obj, TMP[i].getID())
                undo = FunctionCall(self.insert_grade, deepcopy(TMP[i]))

                CSC.append(Operation(undo, redo))

                del TMP[i]
            else:
                i += 1

        try:
            self.__grade_repo.backup()
        except:
            pass

    def remove_grade_student(self, ID, CSC):
        '''
        Remove grades for given student ID
        :param ID: int ID of student
        :return: None
        '''

        TMP = self.__grade_repo.get_all()

        i = 0
        while i < len(TMP):
            if TMP[i].getStudent() == ID:
                redo = FunctionCall(self.remove_grade_obj, TMP[i].getID())
                undo = FunctionCall(self.insert_grade, deepcopy(TMP[i]))

                CSC.append(Operation(undo, redo))

                del TMP[i]

                # not needed anymore at this level
                #self.remove_grade_obj(TMP[i].getID())
            else:
                i += 1

        try:
            self.__grade_repo.backup()
        except:
            pass

    def remove_grade_obj(self, ID):
        '''
        Remove grade by concaternated ID
        :param ID: ID
        :return: object
        '''
        TMP = self.__grade_repo.get_all()

        i = 0
        while i < len(TMP):
            if TMP[i].getID() == ID:
                del TMP[i]
                break
            else:
                i += 1

        try:
            self.__grade_repo.backup()
        except:
            pass

    # GRADE END

    # STATISTICS BEGIN
    def get_studs_assign_alpha(self, ID):
        '''
        Get a list of students for an assignment sorted alphabetically
        :param ID: int Assign ID
        :return: list
        '''

        #TODO: sort & filter

        DB = self.__grade_repo

        TMP = []

        def crt(x):
            if x.getAssignment() == ID:
                return True
            else:
                return False

        def name_criteria(x, y):
            return self.__stud_repo.get(x.getStudent()).getName() <= self.__stud_repo.get(y.getStudent()).getName()

        # for each in DB:
        #     if each.getAssignment() == ID:
        #         stud = self.__stud_repo.get(each.getStudent())
        #         TMP.append(DTO(stud, stud.getName()))

        TMP = self.__grade_repo.filter(self.__grade_repo, crt)

        #TMP.sort(key=lambda x : x.second)


        TMP3 = self.__grade_repo.sort(TMP, name_criteria)

        TMP2 = []

        for each in TMP3:
            stud = self.__stud_repo.get(each.getStudent())
            TMP2.append(DTO(stud, stud.getName()))

        return TMP2

    def get_studs_assign_average(self, ID):
        '''
        Get a list of student by assignment, sorted by grade
        :param ID: Assignment ID
        :return: list
        '''
        DB = self.__grade_repo

        TMP = []

        def filter_logic(x):
            if x.getAssignment() == ID:
                return True
            else:
                return False

        def grade_criteria(x, y):
            return x.getGrade >= y.getGrade

        def ascending_fnc(x, y):
            return x <= y

        # for each in DB:
        #     if each.getAssignment() == ID:
        #         stud = self.__stud_repo.get(each.getStudent())
        #         TMP.append(DTO(stud, stud.getName()))

        TMP = self.__grade_repo.filter(self.__grade_repo, filter_logic)

        # OLD INSTRUCTION
        #TMP.sort(key=lambda x : x.second)

        # NEW INSTRUCTION
        TMP = self.__grade_repo.sort(TMP, grade_criteria) #TODO: sort & filter

        TMP2 = []

        # DTO LOAD
        for each in TMP:
            stud = self.__stud_repo.get(each.getStudent())
            TMP2.append(DTO(each.getGrade, stud))

        return TMP2

    def get_late_students(self):
        '''
        Get a list of students with late assignments
        :return: list
        '''
        DB = self.__grade_repo

        #TODO: FILTER

        today = datetime.now()

        def crt(x):
            today = datetime.now()

            if self.__assign_repo.get(x.getAssignment()).getDeadline() <= today and x.getGrade == -1:
                return True
            else:
                return False

        # for each in DB:
        #     if self.__assign_repo.get(each.getAssignment()).getDeadline() <= today:
        #         if each.getGrade == -1:
        #             std = self.__stud_repo.get(each.getStudent())
        #             TMP.append(DTO(std))

        TMP = self.__grade_repo.filter(self.__grade_repo, crt)

        i = 0

        while i < len(TMP):
            std = self.__stud_repo.get(TMP[i].getStudent())
            TMP[i] = DTO(std)
            i += 1

        return TMP

    def get_best_students(self):
        '''
        Get a list of students with best school situation
        :return: dictionary
        '''
        DB = self.__grade_repo

        #TODO: FILTER

        TMP2 = {}

        today = datetime.now()

        def crt(x):
            today = datetime.now()

            if self.__assign_repo.get(x.getAssignment()).getDeadline() <= today:
                if x.getGrade >= 5:
                    return True
                else:
                    return False
            else:
                return False

        TMP = self.__grade_repo.filter(self.__grade_repo, crt)

        for each in TMP:
            if self.__assign_repo.get(each.getAssignment()).getDeadline() <= today:
                if each.getGrade > -1:
                    if each.getStudent() in TMP2.keys():
                        TMP2[each.getStudent()] += each.getGrade
                        TMP2[each.getStudent()] /= 2.0
                    else:
                        TMP2.update({each.getStudent(): each.getGrade})
                else:
                    del TMP2[each.getStudent()]

        TMP = sorted(TMP2.items(), key=operator.itemgetter(1), reverse=True)


        TEMPO = []

        for each in TMP:
            TEMPO.append(DTO(each[0], each[1]))

        return TEMPO

    def get_assign_average(self):
        '''
        Get a list assign sorted by average
        :return: dictionary
        '''
        DB = self.__grade_repo.get_all()

        TMP = {}

        for each in DB:
            if each.getGrade > -1:
                if each.getAssignment() in TMP.keys():
                    TMP[each.getAssignment()] += each.getGrade
                    TMP[each.getAssignment()] /= 2.0
                else:
                    TMP.update({each.getAssignment(): each.getGrade})

        TMP = sorted(TMP.items(), key=operator.itemgetter(1), reverse=True)

        # sort&filter

        TEMPO = []

        for each in TMP:
            TEMPO.append(DTO(each[0], each[1]))

        return TEMPO
    # STATISTICS END

    def set_grade(self, aid, sid, grd):
        '''
        Set a grade by Stud ID and Assginment ID
        :param aid: int
        :param sid: int
        :param grd: float
        :return:
        '''
        grade = self.__grade_repo.get(str(sid)+"::"+str(aid))

        if self.__assign_repo.get(aid) is None:
            raise RepositoryError("This assignment doesn't exist")

        if grade is None:
            undo = FunctionCall(self.set_grade, aid, sid, -1)
            redo = FunctionCall(self.set_grade, aid, sid, grd)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            self.__grade_repo.append(self.create_grade(sid, aid, grd))
        else:
            undo = FunctionCall(self.set_grade, aid, sid, grade.getGrade)
            redo = FunctionCall(self.set_grade, aid, sid, grd)

            op = Operation(undo, redo)

            self.__timeline.append(op)

            grade.getGrade = grd

    def insert_grade(self, gradeOBJ):
        '''
        Insert a grade object in grades repository
        :param gradeOBJ: grade
        :return: boolean
        '''

        if self.__assign_repo.get(gradeOBJ.getID()) is None:
            self.__grade_repo.append(gradeOBJ)

        else:
            raise RepositoryError("This assignment already exists")
