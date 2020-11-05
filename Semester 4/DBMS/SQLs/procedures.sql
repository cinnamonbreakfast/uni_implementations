USE ITCompany;
GO

--   +------------------------+
--   | modify the type of a   |
--   | column + rev procedure |
--   +------------------------+

-- Set Tasks.due_date to varchar(25)
CREATE OR ALTER PROCEDURE up1
AS
BEGIN
	ALTER TABLE Tasks
	ALTER COLUMN due_date varchar(25)
END
GO

EXEC up1
GO

-- Set Tasks.due_date to date
CREATE OR ALTER PROCEDURE down1
AS
BEGIN
	ALTER TABLE Tasks
	ALTER COLUMN due_date date
END
GO

EXEC down1
GO

--   +------------------------+
--   | remove a column		  |
--   +------------------------+

-- Remove Rooms.rname column
CREATE OR ALTER PROCEDURE up2
AS
BEGIN
	ALTER TABLE Rooms
	DROP COLUMN rname
END
GO

EXEC up2
GO

-- Add Rooms.rname column of type varchar(32)
CREATE OR ALTER PROCEDURE down2
AS
BEGIN
	ALTER TABLE Rooms
	ADD rname varchar(32)
END
GO

EXEC down2
GO

--   +------------------------+
--   | remove a constraint	  |
--   +------------------------+

-- Add DEFAULT constraint for Employed date via Staff
CREATE OR ALTER PROCEDURE up3
AS
BEGIN
	ALTER TABLE Staff
	ADD CONSTRAINT employed DEFAULT (getdate()) FOR employed_on
END
GO

EXEC up3
GO

-- Remove DEFAULT constraint for Employed date via Staff
CREATE OR ALTER PROCEDURE down3
AS
BEGIN
	ALTER TABLE Staff
	DROP CONSTRAINT employed
END
GO

EXEC down3
GO

--   +------------------------+
--   | remove a primary key	  |
--   +------------------------+

-- Add primary key prop. to Room.rid column
CREATE OR ALTER PROCEDURE up4
AS
BEGIN
	ALTER TABLE Rooms
	ADD CONSTRAINT pk_RID PRIMARY KEY(rid)
END
GO

EXEC up4
GO

-- Remove primary key prop. from Room.rid column
CREATE OR ALTER PROCEDURE down4
AS
BEGIN
	ALTER TABLE Rooms
	DROP CONSTRAINT pk_RID
END
GO

EXEC down4
GO

--   +------------------------+
--   | remove a foreign key	  |
--   +------------------------+

-- Add foreign key prop. to Room.dept column
CREATE OR ALTER PROCEDURE up5
AS
BEGIN
	ALTER TABLE Rooms
	ADD CONSTRAINT fk_DEPT FOREIGN KEY(dept) REFERENCES Departments(did)
END
GO

EXEC up5
GO

-- Remove foreign key prop. from Room.dept column
CREATE OR ALTER PROCEDURE down5
AS
BEGIN
	ALTER TABLE Rooms
	DROP CONSTRAINT fk_DEPT
END
GO

EXEC down5
GO

--   +------------------------+
--   | remove a candidate key |
--   +------------------------+

-- Add candidate key CNP-EID
CREATE OR ALTER PROCEDURE up6
AS
BEGIN
	ALTER TABLE Staff
	ADD CONSTRAINT ck_Staff UNIQUE(eid, CNP)
END
GO

EXEC up6
GO

-- Remove foreign key prop. from Room.dept column
CREATE OR ALTER PROCEDURE down6
AS
BEGIN
	ALTER TABLE Staff
	DROP CONSTRAINT ck_Staff
END
GO

EXEC down6
GO

--   +------------------------+
--   | remove a foreign key	  |
--   +------------------------+

-- Add foreign key prop. to Room.dept column
CREATE OR ALTER PROCEDURE up7
AS
BEGIN
	
	CREATE TABLE DBInfo_tst
	(
		id INT IDENTITY(1, 1) NOT NULL,
		ver int DEFAULT 0
	)
END
GO

EXEC up7
GO

-- Remove foreign key prop. from Room.dept column
CREATE OR ALTER PROCEDURE down7
AS
BEGIN
	DROP TABLE IF EXISTS DBInfo_tst
END
GO

EXEC down7
GO