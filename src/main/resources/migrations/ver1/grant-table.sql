--liquibase formatted sql

--changeset farhan:1
GRANT ALL ON ALL TABLES IN SCHEMA public to userapp;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public to userapp;


