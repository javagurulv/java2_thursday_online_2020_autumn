create table items_all (
   itemName varchar(255),
    itemType integer (10),
    qty integer (10),
   description varchar(255),
   id MEDIUMINT NOT NULL AUTO_INCREMENT,
   primary key (id),
   unique (id)
);

--ALTER TABLE BOOKSHELF AUTO_INCREMENT=1001;