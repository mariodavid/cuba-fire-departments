alter table CFD_FIRE_DEPARTMENT add constraint FK_CFD_FIRE_DEPARTMENT_ON_FIRE_CHIEF foreign key (FIRE_CHIEF_ID) references CFD_EMPLOYEE(ID);
create index IDX_CFD_FIRE_DEPARTMENT_ON_FIRE_CHIEF on CFD_FIRE_DEPARTMENT (FIRE_CHIEF_ID);
