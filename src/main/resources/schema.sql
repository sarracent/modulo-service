create table userinfo
(
    id bigint not null,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    roles varchar(255) not null,
    primary key (id)
);