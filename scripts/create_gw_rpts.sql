CREATE TABLE gw_rpts
(
  gw_rpts_sequence decimal(28) primary key not null,
  gw_rpts_object_name varchar(120) not null,
  gw_rpts_sid varchar(8),
  gw_rpts_mime varchar(4),
  gw_rpts_create_date datetime not null,
  gw_rpts_times_accessed int,
  gw_rpts_last_accessed datetime,
  gw_rpts_job_parameters varchar(200),
  gw_rpts_blob longblob
);