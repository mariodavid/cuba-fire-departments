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
);