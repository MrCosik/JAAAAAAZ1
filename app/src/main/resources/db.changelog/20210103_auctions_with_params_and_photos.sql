CREATE TABLE section
(
    id    BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR   NOT NULL
);

CREATE TABLE category
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    section_id INT       ,
    title      VARCHAR   NOT NULL,
    CONSTRAINT section_id FOREIGN KEY (section_id)
        REFERENCES section (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE auction
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    category_id INT       ,
    created_by  INT       NOT NULL,
    title       VARCHAR   NOT NULL,
    description VARCHAR   NOT NULL,
    price       INT       NOT NULL,
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES app_user (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES category (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE photo
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    auction_id INT,
    link       VARCHAR,
    position   INT,
    CONSTRAINT auction_id FOREIGN KEY (auction_id)
        REFERENCES auction (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE parameter
(
    id  BIGSERIAL NOT NULL PRIMARY KEY,
    key VARCHAR
);

CREATE TABLE auction_parameter
(
    auction_id   INT REFERENCES auction (id) ON UPDATE CASCADE ON DELETE CASCADE,
    parameter_id INT REFERENCES parameter (id) ON UPDATE CASCADE ON DELETE CASCADE,
    value        VARCHAR,
    CONSTRAINT auction_parameter_pk PRIMARY KEY (auction_id, parameter_id)
);
