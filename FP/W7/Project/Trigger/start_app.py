'''

S C H E M E

UI -> OPERATIONS
          |
          V
        MODELS  ------->  STUDENT <- GRADE [ID::ID] -> ASSIGNMENT
        REPOSITORY  --->  REPOSITORY -> EXCEPTION

GRADE has concaternated IDs (ID of the student and assignment)

'''

from Repository.RepositoryModel import *
from UI.UI import *
from Operations.Operations import Operator
from datetime import datetime
from History.TimelineRun import *
from Generators.Names import NamesGenerator

import random

from Repository.FileRepository import FileRepository
from Repository.PickleRepository import PickleRepository
from Repository.JSONRepository import JSONRepository

from Trigger.Settings import Settings

need_to_load = False

#try:
print("Loading data...\n")
SETI = Settings("../settings.properties")

stud_repo = None
assign_repo = None
grade_repo = None

if SETI.has_property("repository") is True:
    REPO_TYPE = SETI.get_property("repository")

    if REPO_TYPE == "inmemory":
        stud_repo = Repository()
        assign_repo = Repository()
        grade_repo = Repository()
        need_to_load = True
    elif REPO_TYPE == "file":
        stud_repo = FileRepository(SETI.get_property("students"), "student")
        assign_repo = FileRepository(SETI.get_property("assignments"), "assignment")
        grade_repo = FileRepository(SETI.get_property("grades"), "grade")
    elif REPO_TYPE == "binary":
        stud_repo = PickleRepository(SETI.get_property("students"), "student")
        assign_repo = PickleRepository(SETI.get_property("assignments"), "assignment")
        grade_repo = PickleRepository(SETI.get_property("grades"), "grade")
    elif REPO_TYPE == "json":
        stud_repo = JSONRepository(SETI.get_property("students"), "student")
        assign_repo = JSONRepository(SETI.get_property("assignments"), "assignment")
        grade_repo = JSONRepository(SETI.get_property("grades"), "grade")
    else:
        print("Something went wrong setting repos")

    print("\nLoaded in " + REPO_TYPE + " repo type\n")
else:
    print("No repo type")


# INIT TIMELINE
timeline = Timeline()

# INIT OPERATOR
op = Operator(timeline, stud_repo, assign_repo, grade_repo)

NG = NamesGenerator()
NAMES = NG.gerenate()


def __filler():
    # FILL REPO WITH 100 RANDOM GENERATED STUDENTS
    for i in range(0, 100):
        op.add_student(op.create_student(i + 1, NAMES[i], random.randrange(911, 917)))

    # GENERATE 100 RANDOM ASSIGNMENTS

    for i in range(0, 100):
        op.add_assignment(op.create_assignment(i + 1, "Lorem ipsum.",
                                               datetime(random.randrange(2018, 2020), random.randrange(1, 13),
                                                        random.randrange(1, 28))))

    # ALLOCATE ASSIGN TO 3 RANDOM GROUPS
    for i in range(0, 3):
        op.allocate_assign_group(random.randrange(1, 101), random.randrange(911, 917))

    # ALLOCATE RANDOM ASSIGNMENTS TO ALL STUDENTS
    for i in range(0, 100):
        try:
            op.allocate_assign_student(i + 1, random.randrange(1, 101))
        except:
            pass

    # SET 100 GRADES
    for i in range(0, 100):
        op.set_grade(random.randrange(1, 6), random.randrange(1, 101), random.randrange(1.0, 10.0))

if SETI.has_property("fill") is True:
    if SETI.get_property("fill") == "True":
        __filler()

if need_to_load == True:
    __filler()


UI_mod = Interface(timeline, stud_repo, assign_repo, grade_repo)

UI_mod.start()
# except Exception as E:
#    print("Application couldn't start due to: \n")
#    print(E)
