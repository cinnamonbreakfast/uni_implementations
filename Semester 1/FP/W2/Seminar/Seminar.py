'''
Created on Oct 7, 2016

@author: Arthur

2017.09.30 Changed to menu-driven, added observations
2018.10.07 Updated representation for student entity
'''

"""
Write an application which manages a list of students. 
Each student has a unique id (string), a name (string) and a grade (integer). 
The application will have a menu-driven user interface and will provide the following features:
    
    1. Add a student 
        - adds the student with the given id, name and grade to the list. 
        - error if giving existing id, the name or grade fields not given or empty
    
    2. Delete a student 
        - deletes the student with the given id from the list
        - error if non-existing id given  
        
    3. Show all students 
        - shows all students
    
    4. Show students whose grade is > than given one 

    5. exit
        - exit the program

    Observations:
        - When starting the program, it already has data entered!
        - We have two types of functions: those for the UI and those for functionalities
        - We have specification for non-UI functions
        - Each function does one thing only, and communicates via parameters and return value
        - The program reports errors to the user. It can report errors from non-UI functions too!
        - You can crash the program by providing incorrect input
        - Make sure you understand the representation of student
        - We reuse functions (e.g. __showStudents) for several functionalities. Less code to write and test!
        - We can develop this program in a feature-driven manner by going through functionalities 
        - We don't use global variables!
"""


'''
    Function to work with a student
'''
def createStudent(studentId, studentName, studentGrade):
    if len(studentId) == 0 or len(studentName) == 0 or studentGrade < 1 or studentGrade > 10:
        return None
    return (studentId, studentName, studentGrade)

def getId(student):
    return student[0]

def getName(student):
    return student[1]

def getGrade(student):
    return student[2]

def toString(student):
    """
    Build the string representation for a student
    input: s - the student
    output: The string
    """
    return student[0] + " name " + student[1] + " has grade " + str(student[2])

'''
    User interface functions
'''

def __printMenu():
    menuString = '\nMenu:\n'
    menuString += '\t 1 - Add student\n'
    menuString += '\t 2 - Delete student\n'
    menuString += '\t 3 - Show all students\n'
    menuString += '\t 4 - Filter students by grade\n'
    menuString += '\t 0 - Exit\n'
    print(menuString)

def start():
    '''
    Entry point into the program
    '''
    studentList = []
    
    '''
    We add a few students so that we do not start from scratch
    '''
    testInit(studentList)

    stop = False
    while stop == False:
        __printMenu()
        command = input("Enter command: ")
        if command == '1':
            __addSubmenu(studentList)
        elif command == '2':
            __deleteSubmenu(studentList)
        elif command == '3':
            __showStudents(studentList, -1)
        elif command == '4':
            grade = int(input("Grade to filter by:"))
            __showStudents(studentList, grade)
        elif command == '0':
            stop = True
        else:
            print("Invalid command!") 

def __addSubmenu(studentList):
    '''
    Adding a student
    '''
    studentId = input("Enter student id:")
    studentName = input("Enter student name:")
    studentGrade = int(input("Enter student grade:"))

    
    student = createStudent(studentId, studentName, studentGrade)
    if student == None:
        print("Invalid input. Student was not added")
        return False
    
    addStudent(studentList, student)
    return True

def __deleteSubmenu(studentList):
    '''
    Delete a student
    '''
    studentId = input("Enter student id:")

    if deleteStudent(studentList, studentId) == False:
        print("Invalid input. No student was deleted")

def __showStudents(studentList, grade):
    '''
    Filter students
    '''
    sublist = gradesGreaterThan(studentList, grade)
    print(listToString(sublist))  

def listToString(studentList):
    """
    Build the string representation of a list of students
    input: studentList - the list of students
    output: The string
    """
    res = ""
    for s in studentList:
        res += toString(s)
        res += "\n"
    return res

def findById(studentList, studentID):
    """
    Searches for a student, by id.
    Input: studentList - the list of students
           studentID - a string representing the id of the student
    Output: pos - the position of the student with the given id,
                  -1 if there is no student with the given id
    """
    pos = -1
    for i in range(0, len(studentList)):
        s = studentList[i]
        if getId(s) == studentID:
            pos = i
            break
    return pos

def addStudent(studentList, student):
    """
    Adds the student 'student' to the list of students studentList, if there is no other student
    with the same id.
    Input: studentList - the list of students
           student - a tuple that represents the student'student data
    Output: studentList' - a list of students, studentList' = studentList U {student} (student is added to the list studentList)
            Returns true if the student was added and false, otherwise.
    """
    pos = findById(studentList, getId(student))
    if pos == -1:  # if another student with this id does not exist => add
        studentList.append(student)
        return True
    return False

def deleteStudent(studentList, studentID):
    """
    Deletes the student with the given id from the list studentList
    Input: studentList - the list of students
           studentID - a string, representing the id of the student which must be deleted
    Output: True - if the student was correctly removed
            False - otherwise
    """
    # search the index of the student with the given id
    pos = findById(studentList, studentID)
    if pos == -1:  # a student with the given id does not exist
        return False
    else:
        studentList.pop(pos)
        return True

def gradesGreaterThan(studentList, grade):
    """
    Identifies the students having grades greater than 'grade'.
    Input: studentList - the list of students
    Output: gradesList - a list containing those students from studentList which have grades
                greater than or equal to 'grade'
    """
    gradesList = []
    for student in studentList:
        if getGrade(student) >= grade:  
            gradesList.append(student)            
    return gradesList

"""
Here be tests !
"""
def testInit(studentList):
    studentList.append(createStudent('1', "Pop Ioana", 10))
    studentList.append(createStudent('2', "Man Ionel", 5))
    studentList.append(createStudent('3', "Marian Sofia", 9))
    studentList.append(createStudent('4', "Boca Mihaela", 6))
    studentList.append(createStudent('5', "Popa Adela", 5))
    studentList.append(createStudent('6', "Costin Daniel", 7))
    studentList.append(createStudent('7', "Zaharia Vasile", 8))
    studentList.append(createStudent('8', "Mihnea Loredana", 9))

def testAddStudent():
    pass

def testDeleteStudent():
    pass

def testFindById():
    pass

def testGradesGreaterThan():
    pass

def runAllTests():
    studentList = []
    testInit(studentList)
    testAddStudent()
    testDeleteStudent()
    testFindById()
    testGradesGreaterThan()

start()

