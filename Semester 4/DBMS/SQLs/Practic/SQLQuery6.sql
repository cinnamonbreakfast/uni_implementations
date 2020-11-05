USE Practic
GO


-- increase procedure

CREATE OR ALTER PROCEDURE clujnapocize
@type varchar(32)
AS
BEGIN
	UPDATE Dish SET Price = Price+Price*0.1
	WHERE MID IN (SELECT ID FROM Menu WHERE Type = @type)
END
GO

execute clujnapocize 'Drinks'




-- view

CREATE OR ALTER VIEW large
AS
	(SELECT DISTINCT R.Name, COUNT(R.Name) as 'Dishes' FROM Restaurant R, Menu M
	LEFT JOIN Dish
	ON MID = M.ID
	WHERE R.ID = M.RID
	GROUP BY R.Name)
GO

SELECT * FROM large

CREATE OR ALTER FUNCTION getWhereMaxMenu() RETURNS TABLE
RETURN
	SELECT c.Name FROM Eat e
	LEFT JOIN  Client c
	ON c.ID = e.CID
	WHERE e.RID IN
	
GO

SELECT * FROM getWhereMaxMenu()

