CREATE DATABASE IF NOT EXISTS Tax
    DEFAULT CHARACTER SET utf8;
USE Tax;
DROP TABLE IF EXISTS clients;


CREATE TABLE clients
(
    client_id    INT AUTO_INCREMENT,
    first_name   varchar(64)  NOT NULL,
    last_name    varchar(64)  NOT NULL,
    email        varchar(255) NOT NULL UNIQUE,
    password     varchar(32)  NOT NULL,
    company_name varchar(64)  NOT NULL,

    PRIMARY KEY (client_id)

);


CREATE TABLE reports
(
    report_id           INT AUTO_INCREMENT,
    tittle              VARCHAR(64)  NOT NULL,
    content             VARCHAR(255) NOT NULL,
    report_status       VARCHAR(64),
    cancellation_reason VARCHAR(255)
);

INSERT INTO clients(first_name, last_name, email, password, company_name)
VALUES ('client', 'surname', 'user@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'company');


