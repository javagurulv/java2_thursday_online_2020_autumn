SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

use adventure_db;
create table if not exists events (
    id bigint unsigned auto_increment ,
    name varchar (50) not null unique,
    kind varchar (30) not null,
    duration int not null,
    max_guys int not null,
    min_guys int not null,
    route varchar (50) not null,
    detail varchar (256) not null,
    primary key (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Bike trip to Jurmala-12', 'bike trip', 12, 10, 6, 'Riga Jurmala', 'Bike trip. Lunch. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Bike trip to Jurmala-16', 'bike trip', 16, 10, 6, 'Riga Jurmala', 'Bike trip. Lunch. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Bike trip to Jurmala-8', 'bike trip', 8, 10, 6, 'Riga Jurmala', 'Bike trip. Lunch. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Bus trip to Sigulga and Cesis', 'bus trip', 12, 25, 16, 'Riga Sigulda Cesis', 'Bus trip. Lunch. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Bus trip to Bauska', 'bus trip', 12, 25, 12, 'Riga Bauska', 'Bus trip to Bauska. Bauska castle. Lunch. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Motorcycle trip to Jurmala', 'motorcycle trip', 8, 4 , 4, 'Riga Jurmala', 'Motorcycle trip. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Motorcycle trip to the forest', 'motorcycle trip', 8, 10 , 6, 'Riga Saulkrasti', 'Motorcycle trip. Dinner');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Walking trip to the Daugavgrivas Cietoksnis', 'walking trip', 5,10, 6, 'Riga Center Daugavgriva', 'Walking trip to the Daugavgrivas Cietoksnis. Brunch');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Walking trip. Jurmala beach', 'walking trip', 6, 8, 4, 'Riga Jurmala', 'Walking trip. Jurmala beach. Lunch. Dinner. ');
insert into events (name, kind, duration, max_guys, min_guys, route, detail) values
    ('Walking trip to the Riga center', 'walking trip', 4, 8, 6, 'Riga Center', '"Walking trip to the Riga center. Brunch');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;