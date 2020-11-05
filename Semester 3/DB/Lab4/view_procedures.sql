USE Test
GO

CREATE OR ALTER VIEW oneTable
AS
SELECT * FROM Cars
GO

------

CREATE OR ALTER VIEW twoTables
AS
SELECT c.brand, c.make FROM Cars c
INNER JOIN Car_Tire ct ON ct.cid = c.cid
INNER JOIN Tires t ON ct.tid = t.tid;

GO

------

CREATE OR ALTER VIEW twoTablesGroup
AS
SELECT c.brand, o.name
FROM Cars c, Owners o
GROUP BY c.brand, o.name
GO

SELECT * FROM twoTablesGroup