--------------------------------------------------------
-- rename table ACT_HI_PROCVARIABLE -> ACT_HI_VARINST --

create table ACT_HI_VARINST (
    ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    TASK_ID_ varchar(64),
    NAME_ varchar(255) not null,
    VAR_TYPE_ varchar(100),
    REV_ integer,
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint,
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    primary key (ID_)
);

-- copy old values --
insert into ACT_HI_VARINST
	select ID_, PROC_INST_ID_, NULL, NULL, NAME_, VAR_TYPE_, REV_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_ FROM ACT_HI_PROCVARIABLE;

-- drop old indices --
drop index ACT_IDX_HI_PROCVAR_PROC_INST;
drop index ACT_IDX_HI_PROCVAR_NAME_TYPE;

-- drop old table --
drop table ACT_HI_PROCVARIABLE;

-- create new indices --
create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST(PROC_INST_ID_)
create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST(NAME_, VAR_TYPE_);

-- not migrating --
--   -    DUEDATE_ timestamp null,
--   +    DUEDATE_ timestamp,

--------------------------------------------
-- change column types in ACT_HI_TASKINST --

alter table ACT_HI_TASKINST 
    alter column OWNER_ varchar(255);

alter table ACT_HI_TASKINST 
    alter column ASSIGNEE_ varchar(255);


----------------------------------------
-- change column types in ACT_RU_TASK --

alter table ACT_RU_TASK 
    alter column OWNER_ varchar(255);

alter table ACT_RU_TASK 
    alter column ASSIGNEE_ varchar(255);


------------------------------------------------
-- change column types in ACT_RU_IDENTITYLINK --

alter table ACT_RU_IDENTITYLINK 
    alter column GROUP_ID_ varchar(255);

alter table ACT_RU_IDENTITYLINK 
    alter column USER_ID_ varchar(255);


----------------------------------------------
-- revert introduction of new history level --

update ACT_GE_PROPERTY
  set VALUE_ = VALUE_ - 1,
      REV_ = REV_ + 1
  where NAME_ = 'historyLevel' and VALUE_ >= 2;