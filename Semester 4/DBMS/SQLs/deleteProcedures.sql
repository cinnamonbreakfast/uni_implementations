USE ITCompany
GO

CREATE OR ALTER PROCEDURE deletePast_Roles
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Past_Roles
		WHERE eid = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deletePast_Roles 5
SELECT * FROM Past_Roles

GO

-------------------- Tasks_Assignments

CREATE OR ALTER PROCEDURE deleteTasks_Assignments
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Tasks_Assignments
		WHERE task = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteTasks_Assignments 5
SELECT * FROM Tasks_Assignments

GO

-------------------- TASKS

CREATE OR ALTER PROCEDURE deleteTasks
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Tasks
		WHERE task_id = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteTasks 5
SELECT * FROM Tasks

GO

-------------------- EMPLOYEES

CREATE OR ALTER PROCEDURE deleteStaff
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Staff
		WHERE eid = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteStaff 5
SELECT * FROM Staff

GO