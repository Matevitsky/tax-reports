CREATE DATABASE IF NOT EXISTS Tax
    DEFAULT CHARACTER SET utf8;
USE Tax;



DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS companies;



CREATE TABLE companies
(
    company_id   INT AUTO_INCREMENT,
    company_name VARCHAR(255) UNIQUE NOT NULL,

    PRIMARY KEY (company_id)

);

CREATE TABLE employees
(
    employee_id   INT AUTO_INCREMENT,
    first_name    VARCHAR(64)                NOT NULL,
    last_name     VARCHAR(64)                NOT NULL,
    email         VARCHAR(255)               NOT NULL UNIQUE,
    password      VARCHAR(32)                NOT NULL,
    employee_role ENUM ('INSPECTOR','ADMIN') NOT NULL,

    PRIMARY KEY (id)
);



CREATE TABLE clients
(
    client_id    INT AUTO_INCREMENT,
    first_name   VARCHAR(64)  NOT NULL,
    last_name    VARCHAR(64)  NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(32)  NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    inspector_id INT,


    PRIMARY KEY (client_id),
    FOREIGN KEY (company_name) REFERENCES companies (company_name),
    FOREIGN KEY (inspector_id) REFERENCES employees (employee_id)
);

CREATE TABLE requests
(
    request_id INT AUTO_INCREMENT,
    client_id  INT NOT NULL,

    PRIMARY KEY (request_id),
    FOREIGN KEY (client_id) REFERENCES employees (employee_id)
);


CREATE TABLE reports
(
    report_id        INT AUTO_INCREMENT,
    tittle           VARCHAR(64)  NOT NULL,
    content          VARCHAR(255) NOT NULL,
    report_status    ENUM ( 'NEW','IN_PROGRESS', 'DECLINED','ACCEPTED'),
    reason_to_reject VARCHAR(255) NOT NULL,
    client_id        INT,


    PRIMARY KEY (report_id),
    FOREIGN KEY (client_id) REFERENCES employees (employee_id)


);



INSERT INTO employees (first_name, last_name, email, password, employee_role)
    VALUE ('inspector', 'inspector', 'inspector@test.tes', '8e96c1fb87ac069c2a39f1ed61b10428', 'INSPECTOR');

INSERT INTO employees (first_name, last_name, email, password, employee_role)
    VALUE ('admin', 'admin', 'admin@test.tes', '8e96c1fb87ac069c2a39f1ed61b10428', 'ADMIN');

INSERT INTO companies(company_name)
    value ('Samsung')
    ON DUPLICATE KEY UPDATE company_id = company_id + 0;

INSERT INTO clients(first_name, last_name, email, password, company_name, inspector_id)
    value ('client', 'client', 'test3@test.tes', '098f6bcd4621d373cade4e832627b4f6', 'Samsung', 1);

