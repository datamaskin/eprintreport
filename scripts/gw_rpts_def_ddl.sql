CREATE TABLE GW_RPTS_DEF
(
  GW_RPTS_DEF_OBJECT_NAME VARCHAR2(120) PRIMARY KEY NOT NULL,
  GW_RPTS_DEF_OBJECT_DESC VARCHAR2(300),
  GW_RPTS_DEF_MAINTAINED_DEPT VARCHAR2(30),
  GW_RPTS_DEF_MAINTAINED_COLL VARCHAR2(2),
  GW_RPTS_DEF_RETENTION_DAYS NUMBER(6),
  GW_RPTS_DEF_USERID VARCHAR2(30),
  GW_RPTS_DEF_ACTIVITY_DATE DATE
);