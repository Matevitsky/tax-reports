CREATE DATABASE IF NOT EXISTS Tax
    DEFAULT CHARACTER SET utf8;
USE Tax;


create table client
(
    ID          int auto_increment,
    Name        varchar(64)  not null,
    Email       varchar(255) not null UNIQUE,
    Password    varchar(32)  not null,
    CompanyName varchar(64)  not null,

    PRIMARY KEY (ID)

);


INSERT INTO client(Name, Email, Password, CompanyName)
VALUES ('user', 'user@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'company');


