	DECLARE @testBeginTime DATETIME
	DECLARE @testEndTime DATETIME
	SET @testBeginTime = GETDATE()

	DELETE FROM Staff
	DELETE FROM Tasks
	DELETE FROM Tasks_Assignments
	DELETE FROM Past_Roles

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

		SELECT @tableName = t.Name FROM Tables t
		WHERE @tableID = t.TableID

		PRINT 'Table: ' + @tableName

		SET @startTime = GETDATE()
		PRINT 'start ' + CONVERT(varchar(100), @startTime)

		DECLARE @command varchar(150)

		SET @command = 'insert' +  @tableName + ' ' + CONVERT(VARCHAR(4), @noRows)
		PRINT @command

		EXECUTE @command
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