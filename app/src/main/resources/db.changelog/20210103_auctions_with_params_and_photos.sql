CREATE TABLE auction (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR,
    description VARCHAR,
    created_by INT,
    CONSTRAINT created_by FOREIGN KEY(created_by)
        REFERENCES app_user(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE photo(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR,
    position INT,
    auction_id INT,
    CONSTRAINT auction_id FOREIGN KEY(auction_id)
        REFERENCES auction(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE parameter(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR
);

CREATE TABLE section(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR
);

CREATE TABLE category(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR ,
    section_id INT,
    CONSTRAINT section_id FOREIGN KEY(section_id)
        REFERENCES section(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE auction_parameter(
    auction_id INT REFERENCES auction(id) ON UPDATE CASCADE ON DELETE CASCADE,
    parameter_id INT REFERENCES parameter(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT auction_parameter_pk PRIMARY KEY(auction_id,parameter_id)
);

CREATE TABLE auction_category(
    auction_id INT REFERENCES auction(id) ON UPDATE CASCADE ON DELETE CASCADE,
    category_id INT REFERENCES parameter(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT auction_category_pk PRIMARY KEY(auction_id,category_id)
);