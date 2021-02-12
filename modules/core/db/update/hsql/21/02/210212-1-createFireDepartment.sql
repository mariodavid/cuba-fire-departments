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
);