-- Initialize the database with the following mySQL commands

CREATE DATABASE FOO;
USE FOO;
CREATE TABLE `users`
(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `name`   varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `salary` float                                   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 108
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
