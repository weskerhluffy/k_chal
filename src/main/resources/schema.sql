create table IF NOT EXISTS  Authority (
id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
name varchar(50));

create table IF NOT EXISTS User (
id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
login varchar(50),
password varchar(255)
);

create table IF NOT EXISTS UserAuthority(
id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
idUser BIGINT,
idAuthority BIGINT);

create table IF NOT EXISTS Game(
id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
state varchar(9) default '_________',
idUserX int,
idUserO int,
idUserWin int,
lastTurn char(1) default '_'
);
