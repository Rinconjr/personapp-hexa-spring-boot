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
	(7654321,'Movistar',147258369);