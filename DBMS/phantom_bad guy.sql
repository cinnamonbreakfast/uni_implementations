-- T1

BEGIN TRAN 
WAITFOR DELAY '00:00:03'
EXEC addTask 'Test task phantom', 'phantom', 'phantom', 29007, '2020-06-06', '2020-06-06', 'status', 1
COMMIT TRAN

-- Reset:
DELETE FROM Tasks WHERE task_id>4
select * from Tasks