USE ITCompany
GO

-- RESET IDENTITY
DELETE FROM Tasks_Assignments
DELETE FROM Past_Roles
DELETE FROM Staff
DELETE FROM Tasks

DBCC CHECKIDENT ('Staff', RESEED, 0)
GO

DBCC CHECKIDENT ('Tasks', RESEED, 0)
GO

--DBCC CHECKIDENT ('Tasks_Assignments', RESEED, 0)
--GO

DBCC CHECKIDENT ('Past_Roles', RESEED, 0)
GO

-- RESET IDENTITY END

CREATE OR ALTER PROCEDURE insertStaff
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	DECLARE @name VARCHAR(32)

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		SET @name = 'Employee ' + CONVERT(varchar(5), @i)

		INSERT INTO Staff(full_name, email, CNP, employed_on, phone, srole, dept)
		VALUES(@name, @name, @name, GETDATE(), '0', '0', 1)
		SET @i = @i + 1

	END
END
GO

EXECUTE insertStaff 5
SELECT * FROM Staff


GO
---------------------------------------------------------------- TASKS

CREATE OR ALTER PROCEDURE insertTasks
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	DECLARE @title VARCHAR(32)

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		SET @title = 'Task ' + CONVERT(varchar(5), @i)

		INSERT INTO Tasks(title, brief, repo_dir, creator, due_date, date_created, status, task_priority)
		VALUES(@title, @title, @title, 1, GETDATE(), GETDATE(), 1, 1)
		SET @i = @i + 1

	END
END
GO

EXECUTE insertTasks 5
SELECT * FROM Tasks


GO
---------------------------------------------------------------- DEPARTMENT

CREATE OR ALTER PROCEDURE insertTasks_Assignments
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		INSERT INTO Tasks_Assignments(delegate, task)
		VALUES(@i, @i)
		SET @i = @i + 1

	END
END
GO

EXECUTE insertTasks_Assignments 5
SELECT * FROM Tasks_Assignments


GO
---------------------------------------------------------------- DEPARTMENT

CREATE OR ALTER PROCEDURE insertPast_Roles
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	DECLARE @role VARCHAR(32)

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		SET @role = 'Role ' + CONVERT(varchar(5), @i)

		INSERT INTO Past_Roles(eid, roleof, dept)
		VALUES(@i, @role, 1)
		SET @i = @i + 1

	END
END
GO

EXECUTE insertPast_Roles 5
SELECT * FROM Past_Roles


GO
