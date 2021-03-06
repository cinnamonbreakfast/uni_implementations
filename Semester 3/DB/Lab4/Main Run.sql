USE Test
GO

INSERT INTO Tables(Name) VALUES
	('Cars'),
	('Owners'),
	('Tires'),
	('Car_Tire')
GO

-- insert views

INSERT INTO Views(Name)
VALUES
	('oneTable'),
	('twoTables'),
	('twoTablesGroup')
GO

INSERT INTO Tests(Name)
VALUES
	('insertCars'),
	('insertOwners'),
	('insertTires'),
	('insertCar_Tire'),
	('view1'),
	('view2'),
	('view3'),
	('deleteCar_Tire'),
	('deleteTires'),
	('deleteOwners'),
	('deleteCars')
GO

INSERT INTO TestTables(TestID,TableID,Position,NoOfRows)
VALUES
	(1,1,1,1000),
	(1,2,2,1000),
	(1,3,3,1000),
	(1,4,4,1000),
	(3,4,5,1000),
	(3,3,6,1000),
	(3,2,7,1000),
	(3,1,8,1000)
GO

INSERT INTO TestViews(TestID,ViewID)
VALUES
	(2,1),	
	(2,2),
	(2,3)
GO

CREATE OR ALTER PROCEDURE testing
AS
BEGIN
	SET NOCOUNT ON

	DECLARE @testBeginTime DATETIME
	DECLARE @testEndTime DATETIME
	SET @testBeginTime = GETDATE()

	DELETE FROM Cars
	DELETE FROM Owners
	DELETE FROM Tires
	DELETE FROM Car_Tire

	DELETE FROM TestRunTables
	DELETE FROM TestRunViews
	DELETE FROM TestRuns

	DECLARE @testID INT
	DECLARE @tableID INT
	DECLARE @tableID2 INT
	DECLARE @position INT
	DECLARE @noRows INT
	DECLARE @tableName VARCHAR(50)
	DECLARE @tableName2 VARCHAR(50)
	DECLARE @startTime DATETIME
	DECLARE @endTime DATETIME
	DECLARE @testCount INT
	DECLARE @viewPos INT
	DECLARE @viewID INT
	DECLARE @viewName VARCHAR(50)


	SET @testCount = 1
	SET @position = 1
	SET @testID = 1
	SET @viewPos = 1

	WHILE @position <= 4
	BEGIN
		SELECT @tableID = tt.TableID, @noRows = tt.NoOfRows FROM TestTables tt 
		WHERE tt.Position = @position AND tt.TestID = @testID
		SELECT @tableName = t.Name FROM Tables t WHERE @tableID = t.TableID 

		SELECT @tableName = t.Name FROM Tables t
		WHERE @tableID = t.TableID

		PRINT 'Table: ' + @tableName

		SET @startTime = GETDATE()
		PRINT 'start ' + CONVERT(varchar(100), @startTime)

		DECLARE @command varchar(150)

		SET @command = 'insert' +  @tableName + ' ' + CONVERT(VARCHAR(4), @noRows)
		PRINT @command

		EXECUTE (@command)
		SET @endTime = GETDATE()
		PRINT 'end' + CONVERT(VARCHAR(100), @endTime)

		DECLARE @description VARCHAR(50)
		SET @description = 'INSERT INTO' + @tableName
		INSERT INTO TestRuns
		VALUES(@description, @startTime, @endTime)

		SELECT TOP 1 @testCount = tr.TestRunID FROM TestRuns tr ORDER BY tr.TestRunID DESC

		INSERT INTO TestRunTables
		VALUES (@testCount, @tableID, @startTime, @endTime)

		SET @position = @position + 1
	END

	--- TEST 2

	SET @testID = 2
	WHILE @viewPos <= 3
	BEGIN
		SELECT @viewID = tv.ViewId FROM TestViews tv WHERE tv.TestID = @testID AND tv.ViewID = @viewPos
		SELECT @viewName = v.Name FROM Views v where v.ViewID = @viewID

		SET @startTime = GETDATE()
		DECLARE @commandView VARCHAR(100)

		SET @commandView = 'SELECT * FROM ' + @viewName
		PRINT @commandView
		EXECUTE (@commandView)
		SET @endTime = GETDATE()

		SET @viewPos = @viewPos + 1
		SET @description = 'view' + @viewName

		SELECT TOP 1 @testCount = tr.TestRunID FROM TestRuns tr ORDER BY tr.TestRunID DESC
		INSERT INTO TestRunViews
		VALUES(@testCount,@viewID,@startTime,@endTime)
	END

	SET @testID = 3

	WHILE @position >= 5 AND @position <= 9
	BEGIN
		SELECT @tableID2 = tt.TableID, @noRows = tt.NoOfRows FROM TestTables tt  WHERE tt.Position = @position AND tt.TestID = @testID
		SELECT @tableName2 = t.Name FROM Tables t WHERE @tableID2 = t.TableID 

		SET @startTime = GETDATE()
		
		DECLARE @commandDel VARCHAR(150)
		SET @commandDel = 'delete' + @tableName2 + ' ' + CONVERT(VARCHAR(5), @noRows)
		PRINT @commandDel
		EXECUTE (@commandDel)

		SET @endTime = GETDATE()

		DECLARE @descriptionDel VARCHAR(50)
		SET @descriptionDel = 'DELETE FROM ' + @tableName2
		
		INSERT INTO TestRuns
		VALUES(@descriptionDel, @startTime, @endTime)

		SELECT TOP 1 @testCount = tr.TestRunID FROM TestRuns tr
		ORDER BY tr.TestRunID DESC

		INSERT INTO TestRunTables
		VALUES(@testCount, @tableID2, @startTime, @endTime)

		SET @position = @position + 1
	END

	SET @testEndTime = GETDATE()

	UPDATE TestRuns
	SET StartAt = @testBeginTime, EndAt = @testEndTime

END

EXEC testing

SELECT * FROM Cars
SELECT * FROM Owners
SELECT * FROM Tires
SELECT * FROM Car_Tire

SELECT * FROM Tests
SELECT * FROM Tables
SELECT * FROM TestTables
SELECT * FROM TestViews
SELECT * FROM Views
SELECT * FROM TestRuns
SELECT * FROM TestRunTables
SELECT * FROM TestRunViews


DELETE FROM Cars
DELETE FROM Owners
DELETE FROM Tires
DELETE FROM Car_Tire
 
DELETE FROM TestViews
DELETE FROM TestTables
DELETE FROM Views
DELETE FROM Tests
DELETE FROM Tables
DELETE FROM TestRunTables
DELETE FROM TestRuns
DELETE FROM TestRunViews

--- RESET

DBCC CHECKIDENT ('Views', RESEED, 0)
GO

DBCC CHECKIDENT ('Tests', RESEED, 0)
GO

DBCC CHECKIDENT ('Tables', RESEED, 0)
GO


DBCC CHECKIDENT ('Cars', RESEED, 0)
GO

DBCC CHECKIDENT ('Owners', RESEED, 0)
GO

DBCC CHECKIDENT ('Tires', RESEED, 0)
GO