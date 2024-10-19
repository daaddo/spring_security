drop database if exists spring_security;

create database if not exists spring_security;

use spring_security;

drop table if exists users;
drop table if exists authorities;
drop table if exists customer;

create table customer (
                          id       int auto_increment primary key,
                          email    varchar(50)  not null UNIQUE,
                          password varchar(500) not null,
                          role     varchar(50)  not null
);


INSERT IGNORE INTO customer (email, password, role) VALUES ('test@gmail.com','{noop}test', 'ROLE_USER');
INSERT IGNORE INTO customer (email, password, role) VALUES ('admin@gmail.com','sale:nimda', 'ROLE_ADMIN');
INSERT IGNORE INTO customer (email, password, role) VALUES ('gino@gmail.com','{noop}gino', 'ROLE_USER');


create table bank_account (
                              id       int auto_increment primary key,
                              account_number    varchar(50)  not null UNIQUE,
                              balance double not null
);

INSERT IGNORE INTO bank_account (account_number, balance) VALUES ('111', 1000);
INSERT IGNORE INTO bank_account (account_number, balance) VALUES ('333', 2000);