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
);