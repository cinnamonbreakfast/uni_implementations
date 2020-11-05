BEGIN TRAN 
UPDATE Tasks SET title='Deadlock1' WHERE task_id>1
WAITFOR DELAY '00:00:02'
UPDATE Staff SET full_name='gotcha' WHERE eid>1
COMMIT TRAN


select * from Tasks
select * from Staff