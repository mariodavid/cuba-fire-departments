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
);