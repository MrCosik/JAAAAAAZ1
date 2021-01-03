DROP TABLE users;

CREATE TABLE users(
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstName VARCHAR,
    lastName VARCHAR
);

CREATE TABLE roles(
    role_id BIGSERIAL NOT NULL PRIMARY KEY,
    users_fk INT,
    role VARCHAR NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY(users_fk)
        REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);