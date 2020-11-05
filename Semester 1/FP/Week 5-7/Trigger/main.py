from Models.Student import *
from Models.Assignment import *
from Repositories.studentRepository import *
from Repositories.assignmentsRepository import *
from Trigger.UI import *

def startApp():
    mUI = Interface()
    mUI.start()

'''def test_append():
    studRepo = studentRepository()
    assignRepo = assignmentsRepository()

    ## assign
    stud1 = Student(1, "Armand", 912)
    stud2 = Student(2, "Elizabeth", 913)

    ## directly append to repo
    studRepo.add(stud1)
    studRepo.add(stud2)
    studRepo.add(Student(3, "Vladimir", 911))
    studRepo.add(Student(4, "Andrea", 914))
    studRepo.add(Student(5, "Maurice", 912))
    studRepo.add(Student(5, "Cara", 915))
    studRepo.add(Student(6, "Ivan", 915))
    studRepo.add(Student(7, "Locke", 911))
    studRepo.add(Student(8, "Katrina", 914))
    studRepo.add(Student(9, "Paul", 914))
    studRepo.add(Student(10, "Peter", 912))
    studRepo.add(Student(1, "Angela", 913))

    ## list (returned for Controller)
    TMP = studRepo.list()

    def printList(lst):
        for all in lst:
            print(all.to_string())
    printList(TMP)
    #### display

    ## update records
    assert studRepo.updateById(3, Student(3, "Andreas", 912))
    assert studRepo.update(1, "Angela")

    print("\n")

    ## remove records
    #studRepo.removeById(3)
    #studRepo.remove(5)

    TMP = studRepo.list()

    def printList(lst):
        for all in lst:
            print(all.to_string())

    printList(TMP)

    print('\n')

    assign1 = Assignment(1, "someDesc", "24.10.2018")
    assign2 = Assignment(2, "someDesc2", "25.10.2018")
    grp911 = studRepo.list(911)

    assignRepo.assignToID(1, assign1)

    assignRepo.assignToGroup(grp911, assign1)

    TMP = assignRepo.getAssignedToID(1)
    for all in TMP:
        print(all.to_string())

    print('\n')

    TMP = assignRepo.getAssignedToID(1)
    for all in TMP:
        print(all.to_string())

    print('\n')

    #assert assignRepo.remove(assign1)
    #assert assignRepo.removeID(1, assign1)
    #assert assignRepo.removeGroup(911, assign1)
    assignRepo.updateID(1, assign1, assign2)
    assignRepo.updateGroup(911, assign1, assign2)

    TMP = assignRepo.getAssignedToID(1)
    print("Assigned to ID last:")
    for all in TMP:
        print(all.to_string())

    print('\n')

    TMP = assignRepo.getAssignedToGroup(911)
    print("Assigned to group last:")
    for all in TMP:
        print(all.to_string())

    print('\n')'''


startApp()
