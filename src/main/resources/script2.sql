drop database if exists spring_security;

create database if not exists spring_security;

use spring_security;

drop table if exists users;
drop table if exists authorities;
drop table if exists customer;

create table customer (
                          id       int auto_increment primary key,
                          email    varchar(50)  not null,
                          password varchar(500) not null,
                          role     varchar(50)  not null
);


INSERT IGNORE INTO customer (email, password, role) VALUES ('test@gmail.com','{noop}test', 'ROLE_USER');
INSERT IGNORE INTO customer (email, password, role) VALUES ('admin@gmail.com','{bcrypt}$2a$12$c5e82yyFkbf.J4J188Rns.wx31f0uKe7wtqeu7inbPqIGBNXh0QAW', 'ROLE_ADMIN');
INSERT IGNORE INTO customer (email, password, role) VALUES ('gino@gmail.com','{noop}gino', 'ROLE_USER');