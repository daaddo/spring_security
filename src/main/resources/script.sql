drop database if exists spring_security;

create database if not exists spring_security;

use spring_security;

create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);


create unique index ix_auth_username on authorities (username,authority);


INSERT IGNORE INTO users (username, password, enabled) VALUES ('test','{noop}test', true);
INSERT IGNORE INTO users (username, password, enabled) VALUES ('admin','{bcrypt}$2a$12$c5e82yyFkbf.J4J188Rns.wx31f0uKe7wtqeu7inbPqIGBNXh0QAW', true);
INSERT IGNORE INTO users (username, password, enabled) VALUES ('gino','{noop}gino', true);

INSERT IGNORE INTO authorities (username, authority) VALUES ('test','ROLE_USER');
INSERT IGNORE INTO authorities (username, authority) VALUES ('gino','ROLE_USER');
INSERT IGNORE INTO authorities (username, authority) VALUES ('admin','ROLE_ADMIN');