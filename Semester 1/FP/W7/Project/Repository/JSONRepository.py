from Repository.RepositoryModel import Repository
from Repository.Exception import RepositoryError
from Models.Student import Student
from Models.Grade import Grade
from Models.Assignment import Assignment

import json
import os

from datetime import datetime

class JSONRepository(Repository):
    def __init__(self, filename, method):
        Repository.__init__(self)

        self.__filename = filename
        self.__method = method # serves for conversion

        self.__loadfile()

    def append(self, obj):
        Repository.append(self, obj)
        self.__save_file()


    def json_to_obj(self, json):
        try:
            if len(json) is not 0:
                if self.__method == "student":
                    return Student(json["id"], json["name"].strip(), int(json["group"]))
                elif self.__method == "assignment":
                    date = json["date"].strip().split("/")
                    return Assignment(json["id"], json["descr"], datetime(int(date[2]), int(date[1]), int(date[0])))
                elif self.__method == "grade":
                    return Grade(json["stud_id"], json["assign_id"], float(json["grade"]))
                else:
                    raise RepositoryError("Data file is corrupted")
            else:
                raise RepositoryError("Data file is corrupted")
        except IndexError as E:
            raise RepositoryError("Data file corrupted or wrong conversion method")

    def obj_to_dict(self, obj):
        if self.__method == "student":
            return {"id":obj.getID(),"name":str(obj.getName()),"group":obj.getGroup()}
        elif self.__method == "assignment":
            return {"id":obj.getID(),"descr":obj.getDescription(), "date":str(obj.getDeadline().strftime("%d/%m/%Y"))}
        elif self.__method == "grade":
            return {"stud_id":obj.getStudent(),"assign_id":obj.getAssignment(),"grade":obj.getGrade}
        else:
            raise RepositoryError('Invalid conversion method')

    def __loadfile(self):
        try:
            f = open(self.__filename, 'r', encoding='utf-8')

            if os.path.getsize(self.__filename) > 0:
                JS = json.loads(f.read())
                for each in JS:
                    self.append(self.json_to_obj(each))

            f.close()
        except Exception as E:
            f = open(self.__filename, "w")
            f.close()

    def backup(self):
        self.__save_file()

    def __save_file(self):
        try:
            FINAL = []
            f = open(self.__filename, 'w', encoding='utf-8')
            for obj in self.get_all():

                FINAL.append(self.obj_to_dict(obj))

            strs = json.dumps(FINAL, indent=4, sort_keys=True, separators=(',', ': '), ensure_ascii=False)

            f.write(strs)

            f.close()

        except Exception as E:
            raise RepositoryError(str(E))
