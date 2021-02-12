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
);