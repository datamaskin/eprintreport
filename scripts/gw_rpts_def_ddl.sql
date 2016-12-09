CREATE TABLE gw_rpts_def
(
  gw_rpts_def_object_name VARCHAR(120) PRIMARY KEY NOT NULL,
  gw_rpts_def_object_desc VARCHAR(300),
  gw_rpts_def_maintained_dept VARCHAR(30),
  gw_rpts_def_maintained_coll VARCHAR(2),
  gw_rpts_def_retention_days INT,
  gw_rpts_def_userid VARCHAR(30),
  gw_rpts_def_activity_date DATETIME
);