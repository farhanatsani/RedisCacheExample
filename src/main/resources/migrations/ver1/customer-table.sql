--liquibase formatted sql

--changeset farhan:1
create table customer (
    id int primary key not null,
    name varchar(60),
    phone_no varchar(30),
    address varchar (125)
);  
----rollback drop table customer;

--changeset farhan:2
create sequence customer_sequence;

--changeset farhan:3
insert into customer(id, name, phone_no, address) values (nextval('customer_sequence'), 'name 1', '08111', 'Jl. Jalan');
insert into customer(id, name, phone_no, address) values (nextval('customer_sequence'), 'name 2', '08222', 'Jl. Jalan');