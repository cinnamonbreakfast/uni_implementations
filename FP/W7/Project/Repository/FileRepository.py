from Repository.RepositoryModel import Repository
from Repository.Exception import RepositoryError
from Models.Student import Student
from Models.Grade import Grade
from Models.Assignment import Assignment

from datetime import datetime

class FileRepository(Repository):
    def __init__(self, filename, method):
        Repository.__init__(self)

        self.__filename = filename
        self.__method = method # serves for conversion

        self.__loadfile()

    def append(self, obj):
        Repository.append(self, obj)
        self.__save_file()


    def string_to_obj(self, line):
        try:
            if len(line) is not 0:
                sline = line.split(",")
                if len(sline) == 3:
                    if self.__method == "student":
                        return Student(int(sline[0].strip()), sline[1].strip(), int(sline[2].strip()))
                    elif self.__method == "assignment":
                        date = sline[2].strip().split("/")
                        return Assignment(int(sline[0].strip()), sline[1], datetime(int(date[2]), int(date[1]), int(date[0])))
                    elif self.__method == "grade":
                        return Grade(int(sline[0].strip()), int(sline[1].strip()), float(sline[2].strip()))
                    else:
                        raise RepositoryError("Data file is corrupted")
            else:
                raise RepositoryError("Data file is corrupted")
        except IndexError as E:
            raise RepositoryError("Data file corrupted or wrong conversion method")

    def obj_to_string(self, obj):
        if self.__method == "student":
            return str(obj.getID())+","+str(obj.getName())+","+str(obj.getGroup())
        elif self.__method == "assignment":
            return str(obj.getID())+","+obj.getDescription()+","+str(obj.getDeadline().strftime("%d/%m/%Y"))
        elif self.__method == "grade":
            return str(obj.getStudent())+","+str(obj.getAssignment())+","+str(obj.getGrade)
        else:
            raise RepositoryError('Invalid conversion method')

    def __loadfile(self):
        try:
            f = open(self.__filename, "r+")



            line = f.readline()
            while len(line) > 2:

                self.append(self.string_to_obj(line))

                line = f.readline()

            f.close()
        except:
            f = open(self.__filename, "w")
            f.close()

    def backup(self):
        self.__save_file()

    def __save_file(self):
        try:
            f = open(self.__filename, "w")
            for obj in self.get_all():

                f.write(self.obj_to_string(obj)+"\n")

            f.close()

        except Exception as E:
            raise RepositoryError(str(E))
