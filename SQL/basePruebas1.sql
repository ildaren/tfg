USE mydb;

DELETE FROM trabajadores;
DELETE FROM filial;
DELETE FROM cocheofertado;
DELETE FROM disponibilidad;
DELETE FROM usuario;
DELETE FROM pago;
DELETE FROM reserva;

INSERT INTO filial (`idFilial`, `comunidad`, `ciudad`, `calle`, `numero`) VALUES (1, "Madrid", "Madrid", "Calle Ardemans", 33);
INSERT INTO filial (`idFilial`, `comunidad`, `ciudad`, `calle`, `numero`) VALUES (2, "Cataluña", "Barcelona", "Calle de la Creu", 22);
INSERT INTO filial (`idFilial`, `comunidad`, `ciudad`, `calle`, `numero`) VALUES (3, "Valencia", "Valencia", "Calle General Elio", 22);
INSERT INTO filial (`idFilial`, `comunidad`, `ciudad`, `calle`, `numero`) VALUES (4, "Toledo", "Toledo", "Calle Pintor Rosales", 13);

INSERT INTO trabajadores (`idTrabajador`, `correoElectronico`, `contraseña`, `rol`) VALUES (1, '1', '1', 0);
INSERT INTO trabajadores (`idTrabajador`, `correoElectronico`, `contraseña`, `rol`, `idFilialTrabajador`) VALUES (2, 'alfonso@alquiler24.es', 'filial1', 1, 1);
INSERT INTO trabajadores (`idTrabajador`, `correoElectronico`, `contraseña`, `rol`, `idFilialTrabajador`) VALUES (3, 'victor@alquiler24.es', 'filial2', 1, 2);
INSERT INTO trabajadores (`idTrabajador`, `correoElectronico`, `contraseña`, `rol`, `idFilialTrabajador`) VALUES (4, 'rodrigo@alquiler24.es', 'mecanico', 2, 2);

INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (1, "6767ggh", "Citroen", "C1", 5, 3, "manual", 5, true, 24.5, false, "lleno y lleno", 0, 10000, 1);

INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (1, 1, '2021-03-01', '2021-03-05');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (2, 1, '2021-04-01', '2021-04-15');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (3, 1, '2021-04-22', '2021-05-30');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (4, 1, '2021-05-01', '2021-05-05');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (2, "4656gyt", "Opel", "Corsa", 5, 3, "manual", 5, true, 21.4, true, "lleno y lleno", 0, 10000, 4);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (5, 2, '2021-03-05', '2021-03-09');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (6, 2, '2021-04-07', '2021-04-15');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (7, 2, '2021-04-24', '2021-05-30');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (8, 2, '2021-05-09', '2021-05-15');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (3, "7824tuy", "Audi", "A4", 5, 3, "manual", 5, true, 15.5, false, "Lleno-Lleno", 0, 10000, 1);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (9, 3, '2021-03-05', '2021-03-20');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (10, 3, '2021-05-04', '2021-05-13');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (11, 3, '2021-05-23', '2021-05-28');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (12, 3, '2021-06-01', '2021-06-15');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (4, "7520rug", "BMW", "X3", 5, 3, "automatico", 5, false, 35.5, true, "Coste adicionales", 0, 10000, 3);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (13, 4, '2021-03-05', '2021-03-20');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (14, 4, '2021-05-22', '2021-05-30');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (15, 4, '2021-06-13', '2021-05-20');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (16, 4, '2021-07-01', '2021-07-15');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (5, "7512jkl", "Fiat", "Panda", 5, 3, "manual", 5, true, 29.5, false, "Lleno-Lleno", 0, 10000, 1);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (17, 5, '2021-03-05', '2021-03-20');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (18, 5, '2021-05-04', '2021-05-13');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (19, 5, '2021-05-23', '2021-05-28');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (20, 5, '2021-06-10', '2021-06-15');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (6, "7412qbv", "Honda", "Civic", 5, 3, "manual", 5, true, 20.5, false, "Lleno-Lleno", 0, 10000, 1);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (21, 6, '2021-03-05', '2021-03-20');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (22, 6, '2021-05-08', '2021-05-13');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (23, 6, '2021-05-20', '2021-05-28');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (24, 6, '2021-06-03', '2021-06-12');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (7, "9542abd", "Hyundai", "Kona", 5, 3, "manual", 5, true, 24.5, false, "Lleno-Lleno", 0, 10000, 4);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (25, 7, '2021-05-28', '2021-06-06');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (26, 7, '2021-06-11', '2021-06-16');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (27, 7, '2021-06-24', '2021-06-28');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (28, 7, '2021-07-06', '2021-07-12');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (8, "2546wfv", "Volvo", "V90", 5, 3, "automatico", 5, true, 26.5, false, "Lleno-Lleno", 0, 10000, 1);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (29, 8, '2021-05-20', '2021-06-03');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (30, 8, '2021-06-18', '2021-06-23');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (31, 8, '2021-06-24', '2021-07-04');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (32, 8, '2021-07-20', '2021-07-28');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (9, "6548esc", "Toyota", "Prius", 5, 3, "manual", 5, true, 27.9, false, "Lleno-Lleno", 0, 10000, 2);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (33, 9, '2021-05-29', '2021-06-08');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (34, 9, '2021-06-14', '2021-06-23');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (35, 9, '2021-06-28', '2021-07-09');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (36, 9, '2021-07-20', '2021-07-28');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (10, "3542ecg", "Tesla", "Model X", 5, 3, "manual", 5, false, 22.5, false, "Lleno-Lleno", 0, 10000, 1);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (37, 10, '2021-05-29', '2021-06-10');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (38, 10, '2021-06-14', '2021-06-23');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (39, 10, '2021-06-28', '2021-07-09');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (40, 10, '2021-07-20', '2021-07-28');
    
INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
	VALUES (11, "1423ang", "Suzuki", "Vitara", 5, 3, "manual", 5, true, 21.5, false, "Lleno-Lleno", 0, 10000, 2);
    
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (41, 11, '2021-05-29', '2021-06-08');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (42, 11, '2021-06-14', '2021-06-23');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (43, 11, '2021-06-22', '2021-07-04');
INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (44, 11, '2021-07-14', '2021-07-28');

INSERT INTO usuario (`idUsuario`, `nombre`, `apellidos`, `contrasena`, `correoElectronico`, `dni`, `direccion`, `ciudad`, `codigoPostal`, `fechaCarne`) 
	VALUES (1, "Ignacio", "Garcia Sanchez", "holaMundo12345", "garciaignacio@gmail.com", "78952587S", "Calle francisco silvela", "Madrid", "23032", '2014-04-30');
    
INSERT INTO usuario (`idUsuario`, `nombre`, `apellidos`, `contrasena`, `correoElectronico`, `dni`, `direccion`, `ciudad`, `codigoPostal`, `fechaCarne`) 
	VALUES (2, "Ricardo", "Lopez Crespo", "adios12345", "lopezcrespo99@gmail.com", "75863294W", "Calle de la preu", "Barcelona", "05039", '2020-04-25');
    
INSERT INTO usuario (`idUsuario`, `nombre`, `apellidos`, `contrasena`, `correoElectronico`, `dni`, `direccion`, `ciudad`, `codigoPostal`, `fechaCarne`) 
	VALUES (3, "Jorge", "Diaz Alvarez", "gracias2956", "jorgeda@gmail.com", "49512876F", "Via de la plata", "Lugo", "14054", '2029-11-14');
    
INSERT INTO usuario (`idUsuario`, `nombre`, `apellidos`, `contrasena`, `correoElectronico`, `dni`, `direccion`, `ciudad`, `codigoPostal`, `fechaCarne`) 
	VALUES (4, "Fernando", "Batista Villarrubia", "sinideas4499", "fernandobv@gmail.com", "96453817B", "Calle Ardemans", "Madrid", "28028", '2017-01-23');

INSERT INTO pago (`idPago`, `Titular`, `numTarjeta`, `idUsuarioPago`) VALUES (1, "Ricardo Lopez", "8785456468948545", 2);
INSERT INTO pago (`idPago`, `Titular`, `numTarjeta`, `idUsuarioPago`) VALUES (2, "Fernando Batista", "4754685855864984", 4);
INSERT INTO pago (`idPago`, `Titular`, `numTarjeta`, `idUsuarioPago`) VALUES (3, "Ignacio Garcia", "8754858445984665", 1);

-- 	INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
-- 		VALUES (11, "1423ang", "Suzuki", "Vitara", 5, 3, "manual", 5, true, 21.5, false, "Lleno-Lleno", 0, 10000, 2);
-- 	INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (44, 11, '2021-07-14', '2021-07-28');

INSERT INTO reserva (`idReserva`, `fechaReserva`, `fechaRecogida`, `fechaEntrega`, `precioFinal`, `idCiudadRecogida`, `idCiudadEntrega`, `idCocheReservado`, `idPagoReserva`) 
	VALUES (1, '2021-07-05', '2021-07-14', '2021-07-28', 165.36, 2, 2, 11, 1);
    
-- 	INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
--  	VALUES (1, "6767ggh", "Citroen", "C1", 5, 3, "manual", 5, true, 24.5, false, "lleno y lleno", 0, 10000, 1);
-- 	INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (4, 1, '2021-05-01', '2021-05-05');
    
INSERT INTO reserva (`idReserva`, `fechaReserva`, `fechaRecogida`, `fechaEntrega`, `precioFinal`, `idCiudadRecogida`, `idCiudadEntrega`, `idCocheReservado`, `idPagoReserva`) 
	VALUES (2, '2021-04-27', '2021-05-01', '2021-05-05', 180.36, 1, 2, 1, 2);
    
-- 	INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
-- 		VALUES (6, "7412qbv", "Honda", "Civic", 5, 3, "manual", 5, true, 20.5, false, "Lleno-Lleno", 0, 10000, 1);
-- 	INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (24, 6, '2021-06-03', '2021-06-12');
    
INSERT INTO reserva (`idReserva`, `fechaReserva`, `fechaRecogida`, `fechaEntrega`, `precioFinal`, `idCiudadRecogida`, `idCiudadEntrega`, `idCocheReservado`, `idPagoReserva`) 
	VALUES (3, '2021-05-13', '2021-06-03', '2021-06-12', 155.69, 1, 1, 6, 1);
    
-- 	INSERT INTO cocheofertado (`idCocheOfertado`, `matricula`, `marca`, `modelo`, `numeroPuertas`, `capacidadMaletero`, `cambioMarchas`, `plazas`, `aireAcondicionado`, `precioPorDia`, `enMantenimiento`, `politicaCombustible`, `politicaCancelacion`, `kilometraje`, `idFilialCoche`) 
-- 	 	VALUES (4, "7520rug", "BMW", "X3", 5, 3, "automatico", 5, false, 35.5, true, "Coste adicionales", 0, 10000, 3);
-- 	INSERT INTO disponibilidad (`idDisponibilidad`, `idCocheDisponibilidad`, `fechaNoDisp`, `fechaInicioDisp`) VALUES (16, 4, '2021-07-01', '2021-07-15');
    
INSERT INTO reserva (`idReserva`, `fechaReserva`, `fechaRecogida`, `fechaEntrega`, `precioFinal`, `idCiudadRecogida`, `idCiudadEntrega`, `idCocheReservado`, `idPagoReserva`) 
	VALUES (4, '2021-04-05', '2021-05-01', '2021-05-05', 165.36, 3, 2, 1, 3);