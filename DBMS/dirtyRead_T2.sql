--- T2

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
BEGIN TRAN
SELECT * FROM Tasks
WAITFOR DELAY '00:00:07'
SELECT * FROM Tasks
COMMIT TRAN

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
SELECT * FROM Tasks
WAITFOR DELAY '00:00:07'
SELECT * FROM Tasks
COMMIT TRAN