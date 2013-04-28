CREATE TABLE t_callId (
  id       INT PRIMARY KEY
  IDENTITY (1, 1)     NOT NULL,
  callId   INT UNIQUE NOT NULL,
  callTime DATETIME
)


