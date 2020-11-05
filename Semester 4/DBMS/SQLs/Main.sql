DROP TABLE IF EXISTS DBInfo;
CREATE TABLE DBInfo 
(
	id int IDENTITY(1, 1) NOT NULL,
	current_ver int,
	CONSTRAINT pk_VER_id PRIMARY KEY CLUSTERED (id)
);

INSERT INTO DBInfo VALUES(0);

GO

CREATE OR ALTER PROCEDURE DBSetVer
	@version int
AS
BEGIN
	DECLARE @actualVer INT;
	SET @actualVer = (SELECT V.current_ver FROM DBInfo V);
	DECLARE @runcmd VARCHAR(2000);

	IF @version <= 7 AND @version >=0
		IF @version > @actualVer
		BEGIN
			PRINT 'upgrade'
			--- demanded version is higher than the actual
			WHILE @version > @actualVer
			BEGIN
				SET @actualVer = @actualVer + 1
				SET @runcmd = 'up' + CAST(@actualVer AS VARCHAR(5))
				PRINT @runcmd
				EXEC @runcmd
			END
		END
		ELSE
		BEGIN
			PRINT 'downgrade'
			WHILE @version < @actualVer
			BEGIN
				--IF @version != 0
				--BEGIN
					SET @runcmd = 'down' + CAST(@actualVer AS VARCHAR(5))
					PRINT @runcmd
					EXEC @runcmd
				--END
				SET @actualVer = @actualVer - 1
			END
		END
		ELSE
		BEGIN
			PRINT 'Version has to be between 0 and 7'
			RETURN
		END
	
		UPDATE DBInfo SET current_ver = @version
	END

GO

EXEC DBSetVer 3;
SELECT current_ver FROM DBInfo

-- UPDATE DBInfo SET current_ver = 0

EXEC down1
GO
EXEC down2
GO
EXEC down3
GO
EXEC down4
GO
EXEC down5
GO
EXEC down6
GO
EXEC down7
GO
