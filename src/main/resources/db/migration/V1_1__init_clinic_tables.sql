CREATE TABLE lekarze
(
    id          SERIAL          NOT NULL,
    name        VARCHAR(20)     NOT NULL,
    surname     VARCHAR(20)     NOT NULL,
    title       VARCHAR(20)     NOT NULL,
    phone       VARCHAR(32)     NOT NULL,
    email       VARCHAR(32)     NOT NULL,
    PRIMARY KEY (id)
);