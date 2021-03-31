create table items_all
(
    id          MEDIUMINT NOT NULL AUTO_INCREMENT,
    itemName    varchar(255),
    itemtype_id integer(10),
    qty         integer(10),
    price       integer(10),
    description varchar(255),
    primary key (id),
    unique (id)
);

create table item_types
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    type_name varchar(255),
    material varchar(255),
    description varchar(255),
    primary key (id),
    unique (id)
);

create table orders
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    order_id bigint(30),
    primary key (id),
    unique (id)
);

create table order_wares
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    order_id bigint(30),
    item_id int(100),
    primary key (id),
    unique (id)
);
