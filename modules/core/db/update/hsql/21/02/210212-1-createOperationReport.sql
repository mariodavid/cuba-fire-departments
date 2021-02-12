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
);