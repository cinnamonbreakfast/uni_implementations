CREATE TABLE Restaurants (
	RID int identity primary key,
	Name varchar(40),
	Location varchar(40)
)

CREATE TABLE Menus (
	MID int identity primary key,
	Type varchar(30),
	RID int,
	FOREIGN KEY (RID) REFERENCES Restaurants(RID)
)

CREATE TABLE Dishes (
	DID int identity primary key,
	Name varchar(30),
	Price int,
	MID int,
	FOREIGN KEY (MID) REFERENCES Menus(MID)
)

CREATE TABLE Ingredients (
	IID int identity primary key,
	Name varchar(30)
)

CREATE TABLE Quantity (
	DID int,
	IID int,
	Quantity int,
	FOREIGN KEY (DID) REFERENCES Dishes(DID),
	FOREIGN KEY (IID) REFERENCES Ingredients(IID),
	PRIMARY KEY(DID, IID)
)

CREATE TABLE Clients (
	CID int identity primary key,
	Name varchar(32)
)

CREATE TABLE Eats (
	OID int identity primary key,
	RID int,
	CID int,
	DateOut date,
	TimeOut time,
	FOREIGN KEY (RID) REFERENCES Restaurants(RID),
	FOREIGN KEY (CID) REFERENCES Clients(CID)
)