drop table hero;
drop table animal;
create table hero (id int,nombre varchar(20),otros varchar(1000));
create table animal (id int,id_heroe int,nombre varchar(100));
insert into hero values (1,'Spiderman','Lanza telarañas');
insert into hero values (2,'Superman','Vuela y es duro');
insert into hero values (3,'Spiderpig','Hace de todo');
insert into hero values (4,'Ironman','Tiene dinero');
insert into hero values (5,'Bruja','Hechiceria');
insert into animal values (1,1,'Araña');
insert into animal values (2,2,'Jabali');
insert into animal values (3,4,'Jabali');
insert into animal values (4,3,'Murcielago');
insert into animal values (5,5,'Jabali');