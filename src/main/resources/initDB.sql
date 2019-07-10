CREATE DATABASE IF NOT EXISTS Tax
    DEFAULT CHARACTER SET utf8;
USE Tax;
DROP TABLE IF EXISTS clients;


create table clients
(
    ID          int auto_increment,
    FirstName   varchar(64)  not null,
    LastName    varchar(64)  not null,
    Email       varchar(255) not null UNIQUE,
    Password    varchar(32)  not null,
    CompanyName varchar(64)  not null,

    PRIMARY KEY (ID)

);


INSERT INTO clients(FirstName, LastName, Email, Password, CompanyName)
VALUES ('client', 'surname', 'user@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'company');


