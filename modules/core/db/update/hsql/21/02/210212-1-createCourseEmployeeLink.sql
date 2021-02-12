create table CFD_COURSE_EMPLOYEE_LINK (
    COURSE_ID varchar(36) not null,
    EMPLOYEE_ID varchar(36) not null,
    primary key (COURSE_ID, EMPLOYEE_ID)
);
