DROP DATABASE IF EXISTS bibliotecasapi;
create database bibliotecasapi;
use bibliotecasapi;
CREATE TABLE bibliotecas
(
    id_biblioteca     bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre      varchar(20) unique,
    localidad varchar(20),
    categoria   varchar(20)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  AUTO_INCREMENT = 1;

CREATE TABLE libros
(
    id_libro bigint      not null auto_increment primary key,
    titulo varchar(20) not null,
    autor varchar(20) not null,
    ano_publicacion int not null,
    prestado      boolean not null,
    id_biblioteca      bigint,
    foreign key (id_biblioteca) references bibliotecas (id_biblioteca)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  AUTO_INCREMENT = 1;

CREATE TABLE users
(
    id_user bigint not null auto_increment primary key,
    username   varchar(50) unique,
    pwd    varchar(50)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  AUTO_INCREMENT = 1;