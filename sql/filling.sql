INSERT INTO PERSON_TYPE(type) VALUES ('admin');
INSERT INTO PERSON_TYPE(type) VALUES ('broker');
INSERT INTO PERSON_TYPE(type) VALUES ('client');
INSERT INTO PERSON_TYPE(type) VALUES ('vip admin');
INSERT INTO PERSON_TYPE(type) VALUES ('vip broker');

INSERT INTO REQUEST_TYPE(type) VALUES ('open brokerage account');
INSERT INTO REQUEST_TYPE(type) VALUES ('close brokerage account');
INSERT INTO REQUEST_TYPE(type) VALUES ('make broker agreement');
INSERT INTO REQUEST_TYPE(type) VALUES ('extend broker agreement');
INSERT INTO REQUEST_TYPE(type) VALUES ('break broker agreement');
INSERT INTO REQUEST_TYPE(type) VALUES ('exchange money to stocks');
INSERT INTO REQUEST_TYPE(type) VALUES ('exchange stocks to money');

INSERT INTO CURRENCY(type) VALUES ('euro');
INSERT INTO CURRENCY(type) VALUES ('ruble');
INSERT INTO CURRENCY(type) VALUES ('dollar');

INSERT INTO VALIDITY(type) VALUES ('month');
INSERT INTO VALIDITY(type) VALUES ('half year');
INSERT INTO VALIDITY(type) VALUES ('year');

-- test data

INSERT INTO ADMIN(name, surname) VALUES ('lol', 'lolov');
INSERT INTO ADMIN(name, surname) VALUES ('lil', 'lilov');
INSERT INTO ADMIN(name, surname) VALUES ('lul', 'lulov');

INSERT INTO CLIENT(name, surname) VALUES ('client1', 'cl1');
INSERT INTO CLIENT(name, surname) VALUES ('client2', 'cl2');
INSERT INTO CLIENT(name, surname) VALUES ('client3', 'cl3');

INSERT INTO BROKER(name, surname) VALUES ('broker1', 'br1');
INSERT INTO BROKER(name, surname) VALUES ('broker2', 'br2');
INSERT INTO BROKER(name, surname) VALUES ('broker3', 'br3');