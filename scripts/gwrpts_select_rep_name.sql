SELECT
  this_.gw_rpts_sequence       AS gw_rpts_sequence1_0_0_,
  this_.gw_rpts_blob           AS gw_rpts_blob2_0_0_,
  this_.gw_rpts_create_date    AS gw_rpts_create_dat3_0_0_,
  this_.gw_rpts_job_parameters AS gw_rpts_job_parame4_0_0_,
  this_.gw_rpts_last_accessed  AS gw_rpts_last_acces5_0_0_,
  this_.gw_rpts_mime           AS gw_rpts_mime6_0_0_,
  this_.gw_rpts_object_name    AS gw_rpts_object_nam7_0_0_,
  this_.gw_rpts_sid            AS gw_rpts_sid8_0_0_,
  this_.gw_rpts_times_accessed AS gw_rpts_times_acce9_0_0_
FROM gw_rpts this_
WHERE this_.gw_rpts_object_name = 'GURPDED'