
CREATE TABLE logs (
	id int identity,
	called varchar(35) NOT NULL,
	target_table varchar(35) NOT NULL,
	executed_on date NOT NULL
)
go

CREATE OR ALTER PROCEDURE makeLog(@called varchar(35), @target varchar(35))
AS
	INSERT INTO logs(called, target_table, executed_on) VALUES(@called, @target, GETDATE())
GO

EXEC makeLog 'test', 'table'
GO

CREATE OR ALTER PROCEDURE addEmployee(
	@fullName VARCHAR(32),
	@email varchar(32),
	@CNP varchar(25),
	@employedOn date,
	@phone varchar(15),
	@srole varchar(25),
	@dept int)
AS
	IF(@fullname = null)
	BEGIN
		RAISERROR('Fullname is null', 16, 1)
	END
	IF(@email = null)
	BEGIN
		RAISERROR('Email is null', 16, 1)
	END
	IF(@CNP = null)
	BEGIN
		RAISERROR('CNP is null', 16, 1)
	END
	IF(@employedOn = null)
	BEGIN
		RAISERROR('Employ date is null', 16, 1)
	END
	IF(@phone = null)
	BEGIN
		RAISERROR('Phone number is null', 16, 1)
	END
	IF(@srole = null)
	BEGIN
		RAISERROR('SRole is null', 16, 1)
	END
	IF(@dept = 0)
	BEGIN
		RAISERROR('Dept id is 0', 16, 1)
	END

	INSERT Staff(full_name, email, cnp, employed_on, phone, srole, dept) VALUES(@fullName, @email, @CNP, @employedOn, @phone, @srole, @dept)
	EXEC makeLog 'addEmployee', 'Staff'
GO

EXEC addEmployee 'Candet Andrei', 'candetandrei@gmail.com', '2131231232', '2020-06-05', '0740000000', 'Cool guy', 1
SELECT * FROM Staff
GO

CREATE OR ALTER PROCEDURE addTask(	@title varchar(32),
							@brief varchar(255),
							@repo_dir varchar(255),
							@creator int,
							@due_date varchar(25),
							@date_created date,
							@status varchar(32),
							@task_priority int)
AS
	IF(@title = null)
	BEGIN
		RAISERROR('Task title null', 16, 1)
	END
	IF(@brief = null)
	BEGIN
		RAISERROR('brief is null', 16, 1)
	END
	IF(@due_date = null)
	BEGIN
		RAISERROR('Due date is null', 16, 1)
	END
	IF(@creator = 0)
	BEGIN
		RAISERROR('Creator id is 0', 16, 1)
	END
	IF(@date_created = null)
	BEGIN
		RAISERROR('Date created is null', 16, 1)
	END
	IF(@status = null)
	BEGIN
		RAISERROR('Status is null', 16, 1)
	END
	
	INSERT INTO Tasks (title, brief, repo_dir, creator, due_date, date_created, status, task_priority)
	VALUES(@title, @brief, @repo_dir, @creator, @due_date, @date_created, @status, @task_priority)
	EXEC makeLog 'addTask', 'Tasks'
GO

EXEC addTask 'Test task', 'brief', 'repo dir', 29007, '2020-06-06', '2020-06-06', 'status', 1
SELECT * FROM Tasks
GO

CREATE OR ALTER PROCEDURE addAssign(@full_name varchar(35), @task_title varchar(35))
AS
	IF(@full_name = null)
	BEGIN
		RAISERROR('Full name null', 16, 1)
	END
	IF(@task_title = null)
	BEGIN
		RAISERROR('Task title null', 16, 1)
	END

	DECLARE @eid int = (SELECT eid FROM Staff WHERE full_name = @full_name)
	DECLARE @tid int = (SELECT task_id FROM Tasks WHERE title = @task_title)
	IF(@eid IS NULL OR @tid IS NULL)
	BEGIN
		RAISERROR('Error gettin id', 14, 1)
	END

	IF EXISTS(SELECT * FROM Tasks_Assignments T WHERE delegate = @eid AND task = @tid)
	BEGIN
		RAISERROR('An assignment is already made', 16, 1)
	END

	INSERT INTO Tasks_Assignments (delegate, task) VALUES(@eid, @tid)
	EXEC makeLog 'addAssign', 'Tasks_Assignments'

GO

EXEC addAssign 'Candet Andrei', 'Test task'
SELECT * FROM Tasks_Assignments
GO

CREATE OR ALTER PROCEDURE addRollbackScenario
AS
	BEGIN TRAN
	BEGIN TRY
		EXEC addEmployee 'Candet Andrei', 'candetandrei@gmail.com', '2131231232', '2020-06-05', '0740000000', 'Cool guy', 1
		EXEC addTask 'Test task', 'brief', 'repo dir', 29007, '2020-06-06', '2020-06-06', 'status', 1
		EXEC addAssign 'Candet Andrei', 'Test task'
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		RETURN
	END CATCH
GO