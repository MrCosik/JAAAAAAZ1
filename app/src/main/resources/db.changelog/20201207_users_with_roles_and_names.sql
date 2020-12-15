--DROP TABLE users;

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstName VARCHAR,
    lastName VARCHAR
);

CREATE TABLE roles(
    id_roles BIGSERIAL NOT NULL PRIMARY KEY,
    users_id INT,
    role VARCHAR NOT NULL
);