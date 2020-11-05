CREATE OR ALTER VIEW oneTable
AS
SELECT * FROM Staff

GO

--
CREATE OR ALTER VIEW twoTables
AS
-- INNTER JOIN -> TASK TITLE | NAME | PHONE (Tasks <- Task_Assignments -> Staff)
SELECT title, full_name, phone FROM Staff s
INNER JOIN Tasks_Assignments ta ON ta.delegate = s.eid
INNER JOIN Tasks t ON ta.task = t.task_id;

GO

CREATE OR ALTER VIEW twoTablesGroup
AS
SELECT t.title, s.full_name
FROM Tasks t, Staff s
GROUP BY t.title, s.full_name
GO

SELECT * FROM twoTablesGroup