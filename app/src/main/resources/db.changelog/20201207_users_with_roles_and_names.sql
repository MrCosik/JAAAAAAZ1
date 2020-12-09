--DROP TABLE users;

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstName VARCHAR,
    lastName VARCHAR,
    role VARCHAR

);
--
-- CREATE TABLE roles(
--     id_roles BIGSERIAL NOT NULL,
--     users_id INT,
--     role VARCHAR NOT NULL,
--     CONSTRAINT users_fk FOREIGN KEY (users_id) REFERENCES users(id)
-- );