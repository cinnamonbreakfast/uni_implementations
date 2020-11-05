USE Practic
GO

CREATE TABLE Restaurant (
	ID int identity primary key,
	Location varchar(32),
	Name varchar(32),
);

CREATE TABLE Menu (
	ID int identity primary key,
	Type varchar(32),
	RID int,
	CONSTRAINT fk_rmenu FOREIGN KEY (RID) REFERENCES Restaurant(ID)
)

CREATE TABLE Dish (
	ID int identity primary key,
	Name varchar(32),
	Price int,
	MID int,
	CONSTRAINT fk_dmenu FOREIGN KEY (MID) REFERENCES Menu(ID)
)

CREATE TABLE Ingredient (
	ID int identity primary key,
	Name varchar(32)
)

CREATE TABLE Recipe (
	ID int identity primary key,
	Quantity int,
	DID int,
	IID int,
	CONSTRAINT fk_dish FOREIGN KEY (DID) REFERENCES Dish(ID),
	CONSTRAINT fk_ingredient FOREIGN KEY (IID) REFERENCES Ingredient(ID)
)

CREATE TABLE Client (
	ID int identity primary key,
	Name varchar(32)
)

CREATE TABLE Eat (
	ID int identity primary key,
	CID int,
	RID int,
	Date datetime,
	Time time
	CONSTRAINT fk_eatclient FOREIGN KEY (CID) REFERENCES Client(ID),
	CONSTRAINT fk_eatrestaurant FOREIGN KEY (RID) REFERENCES Restaurant(ID)
)