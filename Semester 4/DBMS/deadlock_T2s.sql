BEGIN TRAN 
UPDATE Tasks SET title='Deadlock2' WHERE task_id>1
WAITFOR DELAY '00:00:02'
UPDATE Staff SET full_name='pikachu' WHERE eid>1
COMMIT TRAN

SET DEADLOCK_PRIORITY HIGH
BEGIN TRAN 
UPDATE Tasks SET title='Deadlock2' WHERE task_id>1
WAITFOR DELAY '00:00:02'
UPDATE Staff SET full_name='pikachu' WHERE eid>1
COMMIT TRAN