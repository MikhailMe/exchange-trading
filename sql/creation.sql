DROP TABLE PASSPORT CASCADE;
DROP TABLE AGREEMENT CASCADE;
DROP TABLE BROKERAGE_ACCOUNT CASCADE;
DROP TABLE REQUEST CASCADE;
DROP TABLE ADMIN CASCADE;
DROP TABLE BROKER CASCADE;
DROP TABLE CLIENT CASCADE;

CREATE TABLE PASSPORT
(
    id     BIGSERIAL PRIMARY KEY NOT NULL,
    series INT                   NOT NULL,
    number INT                   NOT NULL
);

CREATE TABLE AGREEMENT
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    validity   VARCHAR(10)           NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE,
    end_date   TIMESTAMP WITH TIME ZONE
);

CREATE TABLE BROKERAGE_ACCOUNT
(
    id            BIGSERIAL PRIMARY KEY NOT NULL,
    number        BIGINT                NOT NULL,
    money         BIGINT                NOT NULL,
    currency      VARCHAR(10),
    creation_date TIMESTAMP WITH TIME ZONE
);

CREATE TABLE REQUEST
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    date         TIMESTAMP WITH TIME ZONE,
    status       BOOLEAN,
    request_type VARCHAR(30)
);

CREATE TABLE BROKER
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(30)           NOT NULL,
    surname     VARCHAR(30)           NOT NULL,
    person_type VARCHAR(10)           NOT NULL
);

CREATE TABLE ADMIN
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(30)           NOT NULL,
    surname     VARCHAR(30)           NOT NULL,
    person_type VARCHAR(10)           NOT NULL
);

CREATE TABLE CLIENT
(
    id                   BIGSERIAL PRIMARY KEY NOT NULL,
    name                 VARCHAR(30)           NOT NULL,
    surname              VARCHAR(30)           NOT NULL,
    person_type          VARCHAR(10)           NOT NULL,
    broker_id            bigint                NOT NULL REFERENCES BROKER (id),
    passport_id          BIGINT                NOT NULL REFERENCES PASSPORT (id),
    agreement_id         BIGINT                NOT NULL REFERENCES AGREEMENT (id),
    brokerage_account_id BIGINT                NOT NULL REFERENCES BROKERAGE_ACCOUNT (id)
);