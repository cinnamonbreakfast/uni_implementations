-- T1

BEGIN TRAN
WAITFOR DELAY '00:00:03'
UPDATE Tasks SET title='Some task?' WHERE task_id>1
COMMIT TRAN

-- Reset:
UPDATE Tasks SET title='Some task' WHERE task_id>1