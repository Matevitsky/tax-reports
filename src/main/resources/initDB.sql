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
    employee_id         INT AUTO_INCREMENT,
    employee_first_name VARCHAR(64)                NOT NULL,
    employee_last_name  VARCHAR(64)                NOT NULL,
    employee_email      VARCHAR(255)               NOT NULL UNIQUE,
    password            VARCHAR(32)                NOT NULL,
    employee_role       ENUM ('INSPECTOR','ADMIN') NOT NULL,

    PRIMARY KEY (id)
);



create table clients
(
    client_id         INT auto_increment,
    client_first_name VARCHAR(64)  not null,
    client_last_name  VARCHAR(64)  not null,
    client_email      VARCHAR(255) not null,
    password          VARCHAR(32)  not null,
    company_id        INT          not null,
    employee_id       INT          null,
    primary key (client_id),
    foreign key (company_id) references Tax.companies (company_id),
    foreign key (employee_id) references Tax.employees (employee_id)
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



INSERT INTO employees (employee_first_name, employee_last_name, employee_email, password, employee_role)
    VALUE ('inspector', 'inspector', 'inspector@test.tes', '8e96c1fb87ac069c2a39f1ed61b10428', 'INSPECTOR');

INSERT INTO employees (employee_first_name, employee_last_name, employee_email, password, employee_role)
    VALUE ('admin', 'admin', 'admin@test.tes', '8e96c1fb87ac069c2a39f1ed61b10428', 'ADMIN');

INSERT INTO companies(company_name)
    value ('Samsung')
    ON DUPLICATE KEY UPDATE company_id = company_id + 0;

INSERT INTO clients(client_first_name, client_last_name, client_email, password, company_id, employee_id)
    value ('client', 'client', 'test@test.tes', '098f6bcd4621d373cade4e832627b4f6', 1, 1);

