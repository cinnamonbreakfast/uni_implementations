from Operations.Operations import *
from datetime import datetime


class Interface:
    def __init__(self, timeline, studRepo, assignRepo, gradeRepo):
        self.__stud_repo = studRepo
        self.__assign_repo = assignRepo
        self.__grade_repo = gradeRepo
        self.__timeline = timeline

        self.__operator = Operator(self.__timeline, self.__stud_repo, self.__assign_repo, self.__grade_repo)

    def colored(self, msj="", type="NONE"):
        '''
        Print messages with colors
        Input: Message, Message type
        '''
        msj = str(msj)
        colors = {
            "HEADER": '\033[95m',
            "OKBLUE": '\033[94m',
            "OKGREEN": '\033[92m',
            "WARNING": '\033[93m',
            "FAIL": '\033[91m',
            "ENDC": '\033[0m',
        }
        if type in colors.keys():
            return colors[type] + msj + colors["ENDC"]

        return msj

    def print_menu(self):
        print("1 : Manage list of students")
        print("2 : Manage list of assignments")
        print("3 : Manage list of grades")
        print("4 : Statistics")
        print("\n0 : Exit")

    def print_studmenu(self):
        print("1 :: Add student")
        print("2 :: Remove student")
        print("3 :: Update student")
        print("4 :: List students")
        print("5 :: List student assignments")
        print("\n0 :: Back")

    def print_assignmenu(self):
        print("1 :: Add assignment")
        print("2 :: Remove assignment")
        print("3 :: Update assignment")
        print("4 :: List assignment")
        print("5 :: Allocate assignment")
        print("\n0 :: Back")

    def print_grademenu(self):
        print("1 :: Set grade")
        # print("2 :: Catalog")
        print("0 :: Back")

    def print_statsmenu(self):
        print("1 :: Student by Assignment ID sorted")
        print("2 :: Students with late assignments")
        print("3 :: Students by best school situation")
        print("4 :: Assignments by average")
        print("0 :: Back")

    def stud_management(self):
        while True:
            self.print_studmenu()
            m = input("> ")

            if m == '1':
                try:
                    self.__add_student()
                except RepositoryError as R:
                    print(R)
                except ValueError as V:
                    print(V)

            elif m == '2':
                try:
                    self.__remove_student()
                except RepositoryError as R:
                    print(R)
                except ValueError as E:
                    print(E)

            elif m == '3':
                try:
                    self.__update_student()
                except ValueError as V:
                    print(V)
                except RepositoryError as R:
                    print(R)

            elif m == '4':
                self.__print_students()

            elif m == '5':
                self.__print_student_assignments()

            elif m == '0':
                return True
            elif m == "u":
                try:
                    if self.__timeline.undo() == True:
                        print(self.colored("Undo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't undo further.", "FAIL"))
                except Exception as E:
                    print(E)
            elif m == "r":
                try:
                    if self.__timeline.redo() == True:
                       print(self.colored("Redo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't redo further.", "FAIL"))
                except Exception as E:
                    print(E)

            else:
                print("Invalid option")

    def assign_management(self):
        while True:
            self.print_assignmenu()
            m = input("> ")

            if m == '1':
                try:
                    self.__add_assign()
                except RepositoryError as R:
                    print(R)
                except ValueError as V:
                    print(V)

            elif m == '2':
                try:
                    self.__remove_assign()
                except RepositoryError as R:
                    print(R)
                except ValueError as E:
                    print(E)

            elif m == '3':
                try:
                    self.__update_assign()
                except ValueError as V:
                    print(V)
                except RepositoryError as R:
                    print(R)

            elif m == '4':
                self.__print_assigns()

            elif m == '5':
                self.__allocate_assign()

            elif m == '0':
                return True
            elif m == "u":
                try:

                    if self.__timeline.undo() == True:
                        print(self.colored("Undo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't undo further.", "FAIL"))
                except Exception as E:
                    print(E)
            elif m == "r":
                try:
                    if self.__timeline.redo() == True:
                       print(self.colored("Redo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't redo further.", "FAIL"))
                except Exception as E:
                    print(E)

            else:
                print("Invalid option")

    def grade_management(self):
        while True:
            self.print_grademenu()
            m = input("> ")

            if m == '1':
                self.set_grade()
            elif m == '0':
                return True
            elif m == "u":
                try:

                    if self.__timeline.undo() == True:
                        print(self.colored("Undo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't undo further.", "FAIL"))
                except Exception as E:
                    print(E)
            elif m == "r":
                try:
                    if self.__timeline.redo() == True:
                       print(self.colored("Redo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't redo further.", "FAIL"))
                except Exception as E:
                    print(E)
            else:
                print("Invalid option")

    def stats_management(self):
        while True:
            self.print_statsmenu()
            m = input("> ")

            if m == '1':
                self.list_studs_assign()
            elif m == '2':
                self.print_late_students()
            elif m == '3':
                self.print_best_students()
            elif m == '4':
                self.print_assings_by_average()
            elif m == '0':
                return True
            elif m == "u":
                try:
                    if self.__timeline.undo() == True:
                        print(self.colored("Undo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't undo further.", "FAIL"))
                except Exception as E:
                    print(E)
            elif m == "r":
                try:
                    if self.__timeline.redo() == True:
                       print(self.colored("Redo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't redo further.", "FAIL"))
                except Exception as E:
                    print(E)
            else:
                print("Invalid option")

    def start(self):
        '''
        Interface trigger
        '''

        while True:
            self.print_menu()

            m = input("> ")

            if m == '1':
                self.stud_management()
            elif m == '2':
                self.assign_management()
            elif m == '3':
                self.grade_management()
            elif m == '4':
                self.stats_management()
            elif m == '0':
                return True
            elif m == "u":
                try:
                    if self.__timeline.undo() == True:
                        print(self.colored("Undo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't undo further.", "FAIL"))
                except Exception as E:
                    print(E)
            elif m == "r":
                try:
                    if self.__timeline.redo() == True:
                       print(self.colored("Redo action!", "OKGREEN"))
                    else:
                        print(self.colored("Can't redo further.", "FAIL"))
                except Exception as E:
                    print(E)
            else:
                print("Invalid option")

    ### STUDENT BEGIN

    def __add_student(self):
        id = input("ID: ")
        try:
            id = int(id)
        except:
            raise ValueError("Student ID must be an integer number!")

        name = input("Name: ")
        group = input("Group: ")

        try:
            group = int(group)
        except:
            raise ValueError("Student group must be an integer value!")

        # passed

        stud = self.__operator.create_student(id, name, group)

        if self.__operator.add_student(stud) is True:
            print("Student added!")

    def __print_students(self):
        if self.__stud_repo.is_empty() is True:
            print("\nNo records\n")
        else:
            TMP = self.__stud_repo.get_all()

            print("\nID | NAME | GROUP")
            for each in TMP:
                print(str(each))

            print()

    def __remove_student(self):
        id = input("ID: ")

        try:
            id = int(id)
        except:
            raise ValueError("\nStudent ID must be an integer!\n")

        self.__operator.remove_student(id)

        print("\nStudent removed!\n")

    def __update_student(self):
        id = input("ID: ")

        try:
            id = int(id)
        except:
            raise ValueError("\nStudent ID must be an integer!\n")

        print("1 ::: Update student name")
        print("2 ::: Update student group")
        print("0 ::: Cancel")

        m = input("> ")

        if m == '1':
            name = input("New name: ")
            self.__operator.update_student_name(id, name)

            print("\nStudent updated!\n")

        elif m == '2':
            group = input("New group: ")
            try:
                group = int(group)
            except:
                raise ValueError("\nGroup ID must be an integer\n")

            self.__operator.update_student_group(id, group)

            print("\nStudent updated!\n")

        elif m == '0':
            return True
        else:
            print("\nInvalid option\n")

    def __print_student_assignments(self):
        id = input("Student ID: ")

        try:
            id = int(id)
        except:
            print("\nStudent ID must be an integer!\n")

        TMP = self.__operator.get_student_assignments(id)

        if len(TMP) == 0:
            print("\nNo assignment allocated for this student\n")
        else:
            print("\nGRADE | ID | DESCR | DUEDATE")
            for each in TMP:
                grade = self.__grade_repo.get(str(id)+"::"+str(each)).getGrade
                assign = self.__assign_repo.get(each)
                print(grade, assign)

            print()

    ### STUDENT END

    ### ASSSIGNMENT BEGIN

    def __add_assign(self):
        id = input("ID: ")
        try:
            id = int(id)
        except:
            raise ValueError("\nAssignment ID must be an integer number!\n")

        descr = input("Description: ")

        YR = input("Deadline year: ")

        try:
            YR = int(YR)
        except:
            raise ValueError("\nYear must be an integer value!\n")

        MTH = input("Deadline month: ")

        try:
            MTH = int(MTH)
        except:
            raise ValueError("\nMonth must be an integer value!\n")

        DAY = input("Deadline day: ")

        try:
            DAY = int(DAY)
        except:
            raise ValueError("\nDay must be an integer value!\n")

        deadline = datetime(YR, MTH, DAY)

        # passed

        assign = self.__operator.create_assignment(id, descr, deadline)

        if self.__operator.add_assignment(assign) is True:
            print("\nAssignment created!\n")

    def __remove_assign(self):
        id = input("ID: ")

        try:
            id = int(id)
        except:
            raise ValueError("\nAssignment ID must be an integer!\n")

        self.__operator.remove_assignment(id)

        print("\nAssignment removed!\n")

        # REMOVE FROM GRADE ! ! !

    def __update_assign(self):
        id = input("ID: ")

        try:
            id = int(id)
        except:
            raise ValueError("\nAssignment ID must be an integer!\n")

        print("\n1 ::: Update description")
        print("2 ::: Update date")
        print("0 ::: Cancel\n")

        m = input("> ")

        if m == '1':
            descr = input("New description: ")
            self.__operator.update_assignment_descr(id, descr)

            print("\nAssignment description updated!\n")

        elif m == '2':
            YR = input("Deadline year: ")

            try:
                YR = int(YR)
            except:
                raise ValueError("\nYear must be an integer value!\n")

            MTH = input("Deadline month: ")

            try:
                MTH = int(MTH)
            except:
                raise ValueError("\nMonth must be an integer value!\n")

            DAY = input("Deadline day: ")

            try:
                DAY = int(DAY)
            except:
                raise ValueError("\nDay must be an integer value!\n")

            deadline = datetime(YR, MTH, DAY)

            self.__operator.update_assignment_deadline(id, deadline)

            print("\nAssignment date updated!\n")

        elif m == '0':
            return True
        else:
            print("\nInvalid option\n")

    def __print_assigns(self):
        if self.__assign_repo.is_empty() is True:
            print("\nNo records\n")
        else:
            TMP = self.__assign_repo.get_all()

            print("\nID | DESCR | DUEDATE")
            for each in TMP:

                print(str(each))

            print()

    def __allocate_assign(self):
        asgid = input("Assignment ID: ")

        try:
            asgid = int(asgid)
        except:
            raise ValueError("\nAssignment ID must be integer!\n")

        print("Allocate an assignment for a group or a student?")
        print("[g] - group\t\t\t[s] - student")

        forwhat = input("> ")

        if forwhat == 'g':
            targetid = input("Group ID: ")

            try:
                targetid = int(targetid)

                self.__operator.allocate_assign_group(asgid, targetid)
            except:
                raise ValueError("\nGroup ID must be integer!\n")

        elif forwhat == 's':
            targetid = input("Student ID: ")

            try:
                targetid = int(targetid)

                self.__operator.allocate_assign_student(asgid, targetid)
            except:
                raise ValueError("\nStudent ID must be integer!\n")
        else:
            print("\nInvalid option\n")

    ### ASSIGNMENT END

    ### STATISTICS BEGIN

    def list_studs_assign(self):
        asgid = input("Assignment ID: ")

        try:
            asgid = int(asgid)
        except:
            print("\nAssignment ID must be integer\n")
            return True

        print("Alphabetically or by Grade?")
        print("[a] - alphabetically\t\t\t[g] - grade")

        m = input("> ")

        if m == 'a':
            TMP = self.__operator.get_studs_assign_alpha(asgid)

            if len(TMP) == 0:
                print("\nNo records matched this criteria\n")
            else:
                print("ID | Name | Group")
                for each in TMP:
                    print(each.first)
                print()
        elif m == 'g':
            TMP = self.__operator.get_studs_assign_average(asgid)

            if len(TMP) == 0:
                print("\nNo records matched this criteria\n")
            else:
                print("\nGrade | ID | Name | Group")
                for each in TMP:
                    print(str(each.first) + " - " + str(each.second))
                print()
        else:
            print("\nInvalid option\n")
        #except Exception as E:
        #    print(E)
        #    print("Assignment ID must be integer!")

    def print_late_students(self):
        TMP = self.__operator.get_late_students()

        if len(TMP) == 0:
            print("\nNo students with late assignments\n")
        else:
            print("\nID | Name | Group")
            for each in TMP:
                print(each.first)
            print()

    def print_best_students(self):
        TMP = self.__operator.get_best_students()

        if len(TMP) == 0:
            print("\nNo students with best situation...\n")
        else:
            print("\nID | Name | Group | Avg. grade")

            for each in TMP:
                print(str(self.__stud_repo.get(each.first))+" "+self.colored(each.second, "OKGREEN"))
            print()

    def print_assings_by_average(self):
        TMP = self.__operator.get_assign_average()

        if len(TMP) == 0:
            print("\nNo grades yet\n")
        else:
            print("\nID | Desc | Deadline | Avg. grade")

            for each in TMP:
                print(str(self.__assign_repo.get(each.first))+" "+self.colored(each.second, "OKGREEN"))
            print()
            print("Note that this will show only assignments that have a grade!\n")

    # STATISTICS END

    # GRADE BEGIN

    def set_grade(self):
        aid = input("Assign ID: ")

        try:
            aid = int(aid)
        except:
            print("\nAssign ID must be integer\n")
            return True

        sid = input("Student ID: ")

        try:
            sid = int(sid)
        except:
            print("\nStudent ID must be integer\n")
            return True

        grade = input("Grade: ")

        try:
            grade = int(grade)

            if grade <= 0 or grade > 10:
                print("\nGrade must be > 0 or <= 10\n")
                return True
        except:
            print("\nGrade must be float, greater than 0 and less or equal than 10\n")

        try:
            self.__operator.set_grade(aid, sid, grade)

            print("\nGrade set!\n")
        except RepositoryError as R:
            print(R)



    # GRADE END


