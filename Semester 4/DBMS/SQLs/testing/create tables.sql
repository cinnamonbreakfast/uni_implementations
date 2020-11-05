USE Test
GO

CREATE TABLE Cars (
	cid INT PRIMARY KEY IDENTITY(1, 1),
	brand VARCHAR(50),
	make VARCHAR(50),
	capacity int,
	year_of_make DATETIME
)

CREATE TABLE Owners (
	oid INT PRIMARY KEY IDENTITY(1, 1),
	name VARCHAR(50),
	CNP VARCHAR(14),
	own_start DATETIME,
	own_end DATETIME,
	cid INT
)

CREATE TABLE Tires (
	tid INT PRIMARY KEY IDENTITY(1, 1),
	make VARCHAR(50),
	size VARCHAR(50),
	type VARCHAR(50)
)

CREATE TABLE Car_Tire (
	cid INT NOT NULL,
	tid INT NOT NULL,
	FOREIGN KEY (cid) REFERENCES Cars(cid),
	FOREIGN KEY (tid) REFERENCES Tires(tid),
	PRIMARY KEY(cid, tid)
)