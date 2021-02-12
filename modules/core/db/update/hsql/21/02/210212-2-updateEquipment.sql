-- alter table CFD_EQUIPMENT add column FIRE_DEPARTMENT_ID varchar(36) ^
-- update CFD_EQUIPMENT set FIRE_DEPARTMENT_ID = <default_value> ;
-- alter table CFD_EQUIPMENT alter column FIRE_DEPARTMENT_ID set not null ;
alter table CFD_EQUIPMENT add column FIRE_DEPARTMENT_ID varchar(36) not null ;
