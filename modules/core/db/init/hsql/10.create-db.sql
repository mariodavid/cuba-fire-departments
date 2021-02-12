-- begin CFD_FIRE_DEPARTMENT
create table CFD_FIRE_DEPARTMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    STREET varchar(255),
    HOUSENUMBER varchar(255),
    CITY varchar(255),
    POSTAL_CODE varchar(255),
    TYPE_ varchar(50) not null,
    --
    primary key (ID)
)^
-- end CFD_FIRE_DEPARTMENT
-- begin CFD_EMPLOYEE
create table CFD_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FIRST_NAME varchar(255),
    BIRTHDATE date,
    FIRE_DEPARTMENT_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CFD_EMPLOYEE
-- begin CFD_OPERATION_REPORT
create table CFD_OPERATION_REPORT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    OPERATION_ID varchar(36) not null,
    TITLE varchar(255) not null,
    SUMMARY varchar(255),
    CREATOR_ID varchar(36) not null,
    CONTENT longvarchar not null,
    CLASSIFICATION varchar(50) not null,
    --
    primary key (ID)
)^
-- end CFD_OPERATION_REPORT
-- begin CFD_COURSE_TOPIC_ATTACHMENT
create table CFD_COURSE_TOPIC_ATTACHMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(255),
    FILE_ID varchar(36) not null,
    COURSE_TOPIC_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CFD_COURSE_TOPIC_ATTACHMENT
-- begin CFD_OPERATION_TYPE
create table CFD_OPERATION_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end CFD_OPERATION_TYPE
-- begin CFD_COURSE
create table CFD_COURSE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STARTS_AT timestamp not null,
    ENDS_AT timestamp not null,
    COURSE_LEADER_ID varchar(36) not null,
    TOPIC_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CFD_COURSE
-- begin CFD_EQUIPMENT
create table CFD_EQUIPMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    TYPE_ varchar(50) not null,
    FIRE_DEPARTMENT_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CFD_EQUIPMENT
-- begin CFD_COURSE_TOPIC
create table CFD_COURSE_TOPIC (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255) not null,
    SUMMARY longvarchar,
    LEVEL_ varchar(50) not null,
    CONTENT longvarchar,
    --
    primary key (ID)
)^
-- end CFD_COURSE_TOPIC
-- begin CFD_OPERATION
create table CFD_OPERATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    RESPONSIBLE_DEPARTMENT_ID varchar(36) not null,
    OPERATION_LEAD_ID varchar(36),
    STARTS_AT timestamp not null,
    ENDS_AT timestamp,
    TYPE_ID varchar(36),
    --
    primary key (ID)
)^
-- end CFD_OPERATION
-- begin CFD_MAINTENANCE
create table CFD_MAINTENANCE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EQUIPMENT_ID varchar(36) not null,
    SCHEDULED_AT date not null,
    PERFORMED_AT timestamp,
    PERFORMED_BY_ID varchar(36),
    STATUS varchar(50) not null,
    --
    primary key (ID)
)^
-- end CFD_MAINTENANCE
-- begin CFD_COURSE_EMPLOYEE_LINK
create table CFD_COURSE_EMPLOYEE_LINK (
    COURSE_ID varchar(36) not null,
    EMPLOYEE_ID varchar(36) not null,
    primary key (COURSE_ID, EMPLOYEE_ID)
)^
-- end CFD_COURSE_EMPLOYEE_LINK
