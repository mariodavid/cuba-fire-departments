alter table CFD_EMPLOYEE add column MECHANIC boolean ^
update CFD_EMPLOYEE set MECHANIC = false where MECHANIC is null ;
alter table CFD_EMPLOYEE alter column MECHANIC set not null ;
