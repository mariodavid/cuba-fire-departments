alter table CFD_EMPLOYEE add constraint FK_CFD_EMPLOYEE_ON_AVATAR foreign key (AVATAR_ID) references SYS_FILE(ID);
create index IDX_CFD_EMPLOYEE_ON_AVATAR on CFD_EMPLOYEE (AVATAR_ID);
