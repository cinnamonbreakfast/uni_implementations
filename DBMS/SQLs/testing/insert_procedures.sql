CREATE OR ALTER PROCEDURE insertCars
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	DECLARE @make VARCHAR(32)

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		SET @make = 'Car ' + CONVERT(varchar(5), @i)

		INSERT INTO Cars(brand, make, capacity, year_of_make)
		VALUES(@make, @make, 3500, GETDATE())
		SET @i = @i + 1

	END
END
GO

EXECUTE insertCars 5
SELECT * FROM Cars
GO

---------------- INSERT OWNERS

CREATE OR ALTER PROCEDURE insertOwners
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	DECLARE @name VARCHAR(32)

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		SET @name = 'Owner ' + CONVERT(varchar(5), @i)

		INSERT INTO Owners(name, CNP, own_start, own_end, cid)
		VALUES(@name, '1234567890123', GETDATE(), GETDATE(), @i)
		SET @i = @i + 1

	END
END
GO

EXECUTE insertOwners 5
SELECT * FROM Owners
GO

---------------- INSERT TIRES

CREATE OR ALTER PROCEDURE insertTires
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT
	DECLARE @make VARCHAR(32)

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN
		SET @make = 'Tire ' + CONVERT(varchar(5), @i)

		INSERT INTO Tires(make, size, type)
		VALUES(@make, '220/10/15', 'Slick')
		SET @i = @i + 1

	END
END
GO

EXECUTE insertTires 5
SELECT * FROM Tires
GO

---------------- INSERT CAR_TIRES

CREATE OR ALTER PROCEDURE insertCar_Tire
@noRows INT
AS
BEGIN
	-- counter
	DECLARE @i INT

	SET @i = 1

	WHILE @i <= @noRows
	BEGIN

		INSERT INTO Car_Tire
		VALUES(@i, @i)
		SET @i = @i + 1

	END
END
GO

EXECUTE insertCar_Tire 5
SELECT * FROM Car_Tire
GO