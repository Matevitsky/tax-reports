CREATE DATABASE IF NOT EXISTS Tax
    DEFAULT CHARACTER SET utf8;
USE Tax;

DROP TABLE IF EXISTS reports;

DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS inspectors;
DROP TABLE IF EXISTS companies;


CREATE TABLE companies
(
    company_id   INT AUTO_INCREMENT,
    company_name VARCHAR(255) UNIQUE NOT NULL,

    PRIMARY KEY (company_id)

);

CREATE TABLE inspectors
(
    inspector_id INT AUTO_INCREMENT,
    first_name   VARCHAR(64)                         NOT NULL,
    last_name    VARCHAR(64)                         NOT NULL,
    email        VARCHAR(255)                        NOT NULL UNIQUE,
    password     VARCHAR(32)                         NOT NULL,
    role         ENUM ('CLIENT','INSPECTOR','ADMIN') NOT NULL,
    client_id    INT,

    PRIMARY KEY (inspector_id)
);


CREATE TABLE clients
(
    client_id    INT AUTO_INCREMENT,
    first_name   VARCHAR(64)                         NOT NULL,
    last_name    VARCHAR(64)                         NOT NULL,
    email        VARCHAR(255)                        NOT NULL UNIQUE,
    password     VARCHAR(32)                         NOT NULL,
    role         ENUM ('CLIENT','INSPECTOR','ADMIN') NOT NULL,
    company_id   INT                                 NOT NULL,
    inspector_id INT,

    PRIMARY KEY (client_id),
    FOREIGN KEY (company_id) REFERENCES companies (company_id),
    FOREIGN KEY (inspector_id) REFERENCES inspectors (inspector_id)

);


CREATE TABLE reports
(
    report_id           INT AUTO_INCREMENT,
    tittle              VARCHAR(64)  NOT NULL,
    content             VARCHAR(255) NOT NULL,
    report_status       ENUM ( 'NEW','IN_PROGRESS', 'DECLINED','ACCEPTED'),
    cancellation_reason VARCHAR(255),
    client_id           INT,

    PRIMARY KEY (report_id),
    FOREIGN KEY (client_id) REFERENCES clients (client_id)

);







