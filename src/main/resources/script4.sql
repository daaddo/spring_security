drop database if exists securityDB;

create database if not exists securityDB;

use securityDB;

drop table if exists users;
drop table if exists authorities;
drop table if exists customer;

create table customer (
                          id       int auto_increment primary key,
                          email    varchar(50)  not null UNIQUE,
                          password varchar(500) not null,
                          age      int not null,
                          role     varchar(50)  not null
);


INSERT IGNORE INTO customer (email, password, age, role) VALUES ('test@gmail.com','{custom}sale:tset', 18,'ROLE_USER');
INSERT IGNORE INTO customer (email, password, age, role) VALUES ('admin@gmail.com','{bcrypt}$2a$12$c5e82yyFkbf.J4J188Rns.wx31f0uKe7wtqeu7inbPqIGBNXh0QAW', 40, 'ROLE_ADMIN');
INSERT IGNORE INTO customer (email, password, age, role) VALUES ('gino@gmail.com','{noop}gino', 16, 'ROLE_USER');


create table bank_account (
                              id       int auto_increment primary key,
                              account_number    varchar(50)  not null UNIQUE,
                              balance double not null
);

INSERT IGNORE INTO bank_account (account_number, balance) VALUES ('111', 1000);
INSERT IGNORE INTO bank_account (account_number, balance) VALUES ('333', 2000);