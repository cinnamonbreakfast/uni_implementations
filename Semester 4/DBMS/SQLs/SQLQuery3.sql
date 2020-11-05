CREATE OR ALTER PROCEDURE increase (@TYPE as VARCHAR(40))
AS
BEGIN
	UPDATE Dishes AS D SET D.Price = (D.Price + D.Price*0.1)
	WHERE D.MID IN (SELECT M.MID FROM Menus M WHERE M.Type = @TYPE)
END

CREATE OR ALTER VIEW maxi
AS
SELECT C.Type FROM Menus C WHERE C.MID IN (SELECT MAX(MID) as M FROM
(SELECT D.MID, COUNT(D.DID) AS 'Count' FROM Dishes D GROUP BY D.MID) AS Shit)