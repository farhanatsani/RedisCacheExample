--liquibase formatted sql

--changeset farhan:1
create table parameter (
    id int primary key not null,
    key varchar (30),
    description text,
    param_group varchar (30),
    value text,
    value_type varchar (30),
    create_date_time timestamp,
    update_date_time timestamp
);

--changeset farhan:2
create sequence parameter_id_seq;


