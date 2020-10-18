USE LAB5
GO

	---------------------------------------------------__________
CREATE TABLE Students ( -------------------------------| Ta
	Sid INT PRIMARY KEY IDENTITY, ---------------------| *aid
	Name VARCHAR(50),								-- |
	SCS INT UNIQUE, -----------------------------------| a2
	SGroup INT										-- |
)												    -- |
													-- |_________
CREATE TABLE Course ( ---------------------------------| Tb
	Cid INT PRIMARY KEY IDENTITY, ---------------------| *bid
	Title VARCHAR(50),								-- |
	Credit INT ----------------------------------------| b2
)													-- |
													-- |_________
CREATE TABLE Enrollment ( -----------------------------| Tc
	Eid INT PRIMARY KEY IDENTITY, ---------------------| *cid
	Sid INT FOREIGN KEY REFERENCES Students(Sid), -----| ^aid
	Cid INT FOREIGN KEY REFERENCES Course(Cid) --------| ^bid
)

