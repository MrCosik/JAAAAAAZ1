DROP TABLE app_user;

CREATE TABLE app_user(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstname VARCHAR,
    lastname VARCHAR
);

CREATE TABLE role(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR NOT NULL,
    user_id INT,
    CONSTRAINT user_id FOREIGN KEY(user_id)
        REFERENCES app_user(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);
