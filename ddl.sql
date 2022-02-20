create table products
(
    id bigserial not null primary key,
    name varchar(255) not null,
    price decimal(10, 2) not null,
    version int not null default 0,
    status varchar(20) not null default 'ACTIVE'
);
