INSERT INTO Students
	VALUES
		('Student 1', 1, 1),
		('Student 2', 2, 1),
		('Student 3', 3, 1),
		('Student 4', 4, 2),
		('Student 5', 5, 2),
		('Student 6', 6, 2),
		('Student 7', 7, 3),
		('Student 8', 8, 3),
		('Student 9', 9, 3)

INSERT INTO Course
	VALUES
		('MAP', 6),
		('DB', 6),
		('PLF', 5)

INSERT INTO Enrollment
	VALUES
		(1, 1),
		(2, 2),
		(3, 1),
		(4, 3),
		(5, 3),
		(6, 2),
		(7, 2),
		(8, 1),
		(9, 1)

-- a. Clustered index scan
SELECT * FROM Students ORDER BY Sid

-- b. Clustered index seek
SELECT * FROM Students WHERE Sid > 2

-- c. Nonclustered index scan
DROP INDEX IF EXISTS index_Students on Students
CREATE NONCLUSTERED INDEX index_Students ON Students(SCS)

SELECT SCS FROM Students ORDER BY SCS

-- d. Nonclustered index seek
SELECT SCS FROM Students WHERE SCS > 4

-- e. Lookup
SELECT SCS, Name FROM Students ORDER BY SCS

------ B.

-- 0.0032919
CREATE NONCLUSTERED INDEX index_Student ON Students(SGroup)
SELECT S.SGroup FROM Students S
WHERE S.SGroup > 2

-- 0.0032897
DROP INDEX IF EXISTS index_Student ON Students
SELECT S.Name FROM Students S
WHERE S.SGroup > 2

------ B.

GO
CREATE OR ALTER VIEW testView
AS
	SELECT c.Credit, s.SGroup, E.eid FROM Enrollment e
	INNER JOIN Course c ON c.Cid = e.Cid 
	INNER JOIN Students s ON s.Sid = e.Sid WHERE c.Credit > 5 AND s.SGroup >= 2 AND e.Eid > 0
GO

CREATE NONCLUSTERED INDEX index2_Student ON Students(SGroup)
CREATE NONCLUSTERED INDEX index2_Course ON Course(Credit)
CREATE NONCLUSTERED INDEX index2_Enrollment ON Enrollment(Eid)

SELECT * FROM testView -- .00124629

DROP INDEX IF EXISTS index2_Student ON Students
DROP INDEX IF EXISTS index2_Course ON Course

SELECT * FROM testView