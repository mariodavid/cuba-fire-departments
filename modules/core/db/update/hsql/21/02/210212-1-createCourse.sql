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
);