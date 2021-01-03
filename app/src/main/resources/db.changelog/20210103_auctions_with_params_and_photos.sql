CREATE TABLE auctions (
    auction_id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR,
    description VARCHAR
--     CONSTRAINT parameter_fk FOREIGN KEY(parameter_fk)
--         REFERENCES auction_parameter(auction_id)
--     ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE photos(
    photo_id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR,
    size FLOAT,
    auction_fk INT,
    CONSTRAINT auction_fk FOREIGN KEY(auction_fk)
        REFERENCES auctions(auction_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE parameters(
    parameter_id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR
--     CONSTRAINT auction_fk FOREIGN KEY(auction_fk)
--         REFERENCES auction_parameter(parameter_id)
--     ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE auction_parameter(
    auction_id INT REFERENCES auctions(auction_id) ON UPDATE CASCADE ON DELETE CASCADE,
    parameter_id INT REFERENCES parameters(parameter_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT auction_parameter_pk PRIMARY KEY(auction_id,parameter_id)
);