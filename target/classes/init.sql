-- Initialize the database with the following mySQL commands

CREATE DATABASE FOO;
USE FOO;
CREATE TABLE users
(
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    salary float(10) DEFAULT NULL,
    PRIMARY KEY(id, name)
);
