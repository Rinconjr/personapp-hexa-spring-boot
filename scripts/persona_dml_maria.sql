INSERT INTO 
	`persona_db`.`persona`(`cc`,`nombre`,`apellido`,`genero`,`edad`) 
VALUES
	(123456789,'Pepe','Perez','M',30),
	(987654321,'Pepito','Perez','M',15),
	(321654987,'Pepa','Juarez','F',30),
	(147258369,'Pepita','Juarez','F',10),
	(963852741,'Fede','Perez','M',18);


INSERT INTO 
	`persona_db`.`profesion`(`id`,`nom`,`des`)
VALUES
	(1,'Ingeniero','Ingeniero de Sistemas'),
	(2,'Medico','Medico General'),
	(3,'Abogado','Abogado Penalista'),
	(4,'Ingeniero','Ingeniero Civil'),
	(5,'Ingeniero','Ingeniero Electrico');

INSERT INTO
	`persona_db`.`telefono`(`num`,`oper`,`duenio`)
VALUES
	(1234567,'Claro',123456789),
	(7654321,'Movistar',123456789),
	(9876543,'Tigo',987654321),
	(3216549,'Claro',321654987),
	(1472583,'Movistar',147258369),
	(9638527,'Tigo',963852741);

-- INSERT INTO
-- 	`persona_db`.`estudios`(`id_prof`, `cc_per`, `fecha`, `univer`)
-- VALUES
-- 	(1,123456789,'2010-01-01','Universidad Nacional'),
-- 	(2,123456789,'2015-01-01','Universidad de los Andes'),
-- 	(3,123456789,'2020-01-01','Universidad Javeriana'),
-- 	(4,987654321,'2010-01-01','Universidad Nacional'),
-- 	(5,987654321,'2015-01-01','Universidad de los Andes'),
-- 	(1,321654987,'2010-01-01','Universidad Nacional'),
-- 	(2,321654987,'2015-01-01','Universidad de los Andes'),
-- 	(3,321654987,'2020-01-01','Universidad Javeriana'),
-- 	(4,147258369,'2010-01-01','Universidad Nacional'),
-- 	(5,147258369,'2015-01-01','Universidad de los Andes'),
-- 	(1,963852741,'2010-01-01','Universidad Nacional'),
-- 	(2,963852741,'2015-01-01','Universidad de los Andes'),
-- 	(3,963852741,'2020-01-01','Universidad Javeriana');