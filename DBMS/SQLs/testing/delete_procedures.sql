CREATE OR ALTER PROCEDURE deleteCar_Tire
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Car_Tire
		WHERE cid = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteCar_Tire 5
SELECT * FROM Car_Tire
GO

---------------- Delete tires

CREATE OR ALTER PROCEDURE deleteTires
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Tires
		WHERE tid = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteTires 5
SELECT * FROM Tires
GO

---------------- Delete owners

CREATE OR ALTER PROCEDURE deleteOwners
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Owners
		WHERE oid = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteOwners 5
SELECT * FROM Owners
GO

---------------- Delete cars

CREATE OR ALTER PROCEDURE deleteCars
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		DELETE FROM Cars
		WHERE cid = @i
		SET @i = @i + 1
	END
END
GO

EXECUTE deleteCars 5
SELECT * FROM Cars
GO

--- RESET

DBCC CHECKIDENT ('Cars', RESEED, 0)
GO

DBCC CHECKIDENT ('Owners', RESEED, 0)
GO

DBCC CHECKIDENT ('Tires', RESEED, 0)
GO