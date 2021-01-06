DROP TABLE test1;

CREATE TABLE app_user(
id BIGSERIAL NOT NULL PRIMARY KEY ,
username VARCHAR not null,
password VARCHAR not null
);