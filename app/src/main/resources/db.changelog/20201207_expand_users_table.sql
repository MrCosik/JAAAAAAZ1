--DROP TABLE users;


CREATE TABLE users(
id BIGSERIAL NOT NULL PRIMARY KEY,
username VARCHAR(60) not null,
password VARCHAR(60) not null,
firstName VARCHAR(60),
lastName VARCHAR(60)
);