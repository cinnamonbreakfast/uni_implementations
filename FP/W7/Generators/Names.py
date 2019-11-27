import random

class NamesGenerator:
    def __init__(self):
        '''
        GENERATE NAMES
        '''
        self.list = []

        f = open("../names.txt")
        self.NAMES = f.read().splitlines()
        f.close()

        self.FAMILY = [
            "JAMES",
            "JOHN",
            "ROBERT",
            "MICHAEL",
            "WILLIAM",
            "DAVID",
            "RICHARD",
            "CHARLES",
            "JOSEPH",
            "THOMAS",
            "CHRISTOPHER",
            "DANIEL",
            "PAUL",
            "MARK",
            "DONALD",
            "GEORGE",
            "KENNETH",
            "STEVEN",
            "EDWARD",
            "BRIAN",
            "RONALD",
            "ANTHONY",
            "KEVIN",
            "JASON",
            "MATTHEW",
            "GARY",
            "TIMOTHY",
            "JOSE",
            "LARRY",
            "JEFFREY",
            "FRANK",
            "SCOTT",
            "ERIC",
            "STEPHEN",
            "ANDREW",
            "RAYMOND",
            "GREGORY",
            "JOSHUA",
            "JERRY",
            "DENNIS",
            "WALTER",
            "PATRICK",
            "PETER",
            "HAROLD",
            "DOUGLAS",
            "HENRY",
            "CARL",
            "ARTHUR",
            "RYAN",
            "ROGER",
            "JOE",
            "JUAN",
            "JACK",
            "ALBERT",
            "JONATHAN",
            "JUSTIN",
            "TERRY",
            "GERALD",
            "KEITH",
            "SAMUEL",
            "WILLIE",
            "RALPH",
            "LAWRENCE",
            "NICHOLAS",
            "ROY",
            "BENJAMIN",
            "BRUCE",
            "BRANDON",
            "ADAM",
            "HARRY",
            "FRED",
            "WAYNE",
            "BILLY",
            "STEVE",
            "LOUIS",
            "JEREMY",
            "AARON",
            "RANDY",
            "HOWARD",
            "EUGENE",
            "CARLOS",
            "RUSSELL",
            "BOBBY",
            "VICTOR",
            "MARTIN",
            "ERNEST",
            "PHILLIP",
            "TODD",
            "JESSE",
            "CRAIG",
            "ALAN",
            "SHAWN",
            "CLARENCE",
            "SEAN",
            "PHILIP",
            "CHRIS",
            "JOHNNY",
            "EARL",
            "JIMMY",
            "ANTONIO"
        ]

        '''self.NAMES = [
            "MARY",
            "PATRICIA",
            "LINDA",
            "BARBARA",
            "ELIZABETH",
            "JENNIFER",
            "MARIA",
            "SUSAN",
            "MARGARET",
            "DOROTHY",
            "LISA",
            "NANCY",
            "KAREN",
            "BETTY",
            "HELEN",
            "SANDRA",
            "DONNA",
            "CAROL",
            "RUTH",
            "SHARON",
            "MICHELLE",
            "LAURA",
            "SARAH",
            "KIMBERLY",
            "DEBORAH",
            "JESSICA",
            "SHIRLEY",
            "CYNTHIA",
            "ANGELA",
            "MELISSA",
            "BRENDA",
            "AMY",
            "ANNA",
            "REBECCA",
            "VIRGINIA",
            "KATHLEEN",
            "PAMELA",
            "MARTHA",
            "DEBRA",
            "AMANDA",
            "STEPHANIE",
            "CAROLYN",
            "CHRISTINE",
            "MARIE",
            "JANET",
            "CATHERINE",
            "FRANCES",
            "ANN",
            "JOYCE",
            "DIANE",
            "ALICE",
            "JULIE",
            "HEATHER",
            "TERESA",
            "DORIS",
            "GLORIA",
            "EVELYN",
            "JEAN",
            "CHERYL",
            "MILDRED",
            "KATHERINE",
            "JOAN",
            "ASHLEY",
            "JUDITH",
            "ROSE",
            "JANICE",
            "KELLY",
            "NICOLE",
            "JUDY",
            "CHRISTINA",
            "KATHY",
            "THERESA",
            "BEVERLY",
            "DENISE",
            "TAMMY",
            "IRENE",
            "JANE",
            "LORI",
            "RACHEL",
            "MARILYN",
            "ANDREA",
            "KATHRYN",
            "LOUISE",
            "SARA",
            "ANNE",
            "JACQUELINE",
            "WANDA",
            "BONNIE",
            "JULIA",
            "RUBY",
            "LOIS",
            "TINA",
            "PHYLLIS",
            "NORMA",
            "PAULA",
            "DIANA",
            "ANNIE",
            "LILLIAN",
            "EMILY",
            "ROBIN"
        ]'''

    def gerenate(self):
        '''
        RETURN A LIST FULL OF VALID STRING NAMES
        :return: list of strings
        '''

        for i in range(0, 100):
            self.list.append(self.FAMILY[random.randrange(0, 100)]+" "+self.NAMES[random.randrange(0, 100)])

        return self.list
