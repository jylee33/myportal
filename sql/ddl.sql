drop table if exist member CASCADE;
create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    primary key(id)
);

create table member
(
    id bigint NOT NULL auto_increment,
    name varchar(255),
    primary key(id)
);
