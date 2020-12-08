insert into category(id,name,create_at)values(1,'Deporte',CURRENT_TIMESTAMP());
insert into category(id,name,create_at)values(2,'Tecnologia',CURRENT_TIMESTAMP());
insert into category(id,name,create_at)values(3,'Comic',CURRENT_TIMESTAMP());

insert into product(id,code,name,count,category_id,create_at)values(1,'cod1','Pelota futbol',20,1,CURRENT_TIMESTAMP());
insert into product(id,code,name,count,category_id,create_at)values(2,'cod2','Raqueta tenis',10,1,CURRENT_TIMESTAMP());
insert into product(id,code,name,count,category_id,create_at)values(3,'cod3','Mouse optico',0,2,CURRENT_TIMESTAMP());
insert into product(id,code,name,count,category_id,create_at)values(4,'cod4','Auriculares',15,2,CURRENT_TIMESTAMP());
insert into product(id,code,name,count,category_id,create_at)values(5,'cod5','Revista Dragon Ball Z',50,3,CURRENT_TIMESTAMP());
insert into product(id,code,name,count,category_id,create_at)values(6,'cod6','Pelicula Caballero del zodiaco',5,3,CURRENT_TIMESTAMP());

insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(1,'cod1','Pelota futbol',20,'Deporte',CURRENT_TIMESTAMP(),'CREATED',20);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(2,'cod2','Raqueta tenis',10,'Deporte',CURRENT_TIMESTAMP(),'CREATED',10);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(3,'cod3','Mouse optico',0,'Tecnologia',CURRENT_TIMESTAMP(),'CREATED',0);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(4,'cod4','Auriculares',15,'Tecnologia',CURRENT_TIMESTAMP(),'CREATED',15);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(5,'cod5','Revista Dragon Ball Z',50,'Comic',CURRENT_TIMESTAMP(),'CREATED',50);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(6,'cod6','Pelicula Caballero del zodiaco',5,'Comic',CURRENT_TIMESTAMP(),'CREATED',5);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(7,'cod1','Pelota futbol',40,'Deporte',CURRENT_TIMESTAMP(),'ADD',20);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(8,'cod1','Pelota futbol',35,'Deporte',CURRENT_TIMESTAMP(),'GET',5);
insert into movement(id,product_code,product_name,count,category,create_at,type,diff_count)values(9,'cod1','Pelota futbol',50,'Deporte',CURRENT_TIMESTAMP(),'ADD',15);