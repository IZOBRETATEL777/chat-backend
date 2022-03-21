create table if not exists users
(
    id       int          NOT NULL AUTO_INCREMENT,
    login    varchar(250) NOT NULL UNIQUE,
    password varchar(250) NOT NULL,
    role     varchar(30)  NOT NULL default 'USER',
    active   boolean      NOT NULL default true,
    PRIMARY KEY (id)
);
