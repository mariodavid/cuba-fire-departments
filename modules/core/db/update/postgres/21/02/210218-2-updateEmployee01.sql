alter table CFD_EMPLOYEE add constraint FK_CFD_EMPLOYEE_ON_USER foreign key (USER_ID) references SEC_USER(ID);
create index IDX_CFD_EMPLOYEE_ON_USER on CFD_EMPLOYEE (USER_ID);
