SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Query
-- -----------------------------------------------------
USE `mydb` ;
SELECT * FROM reserva;
SELECT * FROM cocheofertado;
SELECT * FROM pago;
SELECT * FROM filial;
SELECT * FROM disponibilidad;
SELECT * FROM usuario;

SELECT fechaRecogida, fechaEntrega, idCocheReservado FROM reserva, pago WHERE idReserva=1 AND idUsuarioPago=2 AND idPago=idPagoReserva;

SELECT idCocheOfertado, matricula, marca, numeroPuertas, capacidadMaletero, cambioMarchas, plazas, aireCondicionado, precioPorDia, enMantenimiento, politicaCombustible, politicaCancelacion, kilometraje, modelo
	FROM cocheOfertado co, modelo m
    WHERE co.idCocheOfertado = 1 AND co.idModelo = m.idModelo;

SELECT co.idReserva, co.fechaReserva, co.fechaRecogida, co.fechaEntrega, co.precioFinal, co.idCocheReservado, fie.comunidad, fie.ciudad, fir.comunidad, fir.ciudad
	FROM reserva co, filial fie, filial fir
    WHERE co.idCiudadEntrega = fie.idFilial AND co.idCiudadRecogida = fir.idFilial AND co.idReserva = 1;
    
SELECT re.idReserva
	FROM reserva re, pago p
	WHERE re.idPagoReserva = p.idPago AND p.idUsuarioPago = 1
    LIMIT 0,5;
    
SELECT co.idCocheOfertado
	FROM cocheofertado co, disponibilidad d
	WHERE co.idCocheOfertado = d.idCocheDisponibilidad AND co.idFilialCoche = 1 AND NOT (('2021-05-02' > d.fechaNoDisp AND '2021-05-02' < d.fechaInicioDisp) OR ('2021-05-06' > d.fechaNoDisp AND '2021-05-06'< d.fechaInicioDisp))
    LIMIT 0,5;
    
SELECT co.idCocheOfertado FROM cocheofertado co, disponibilidad d
WHERE co.idCocheOfertado = d.idCocheDisponibilidad AND co.idFilialCoche = 1 AND d.idCocheDisponibilidad NOT IN (
	SELECT idCocheDisponibilidad
		FROM disponibilidad
		WHERE (('2021-05-02' > d.fechaNoDisp AND '2021-05-02' < d.fechaInicioDisp) OR ('2021-05-06' > d.fechaNoDisp AND '2021-05-06'< d.fechaInicioDisp))
) LIMIT 0,5;


SELECT DISTINCT idCocheDisponibilidad, idDisponibilidad
	FROM disponibilidad
	WHERE idCocheDisponibilidad = 11 AND idDisponibilidad != 44 AND idDisponibilidad NOT IN (
		SELECT idDisponibilidad
			FROM disponibilidad
			WHERE (('2021-07-23' > fechaNoDisp AND '2021-07-23' < fechaInicioDisp) OR ('2021-07-28' > fechaNoDisp AND '2021-07-28'< fechaInicioDisp)));
    
SELECT *
	FROM disponibilidad
	WHERE idCocheDisponibilidad = 11 AND idDisponibilidad != 44 AND (('2021-06-20' > fechaNoDisp AND '2021-06-20' < fechaInicioDisp) OR ('2021-07-28' > fechaNoDisp AND '2021-07-28'< fechaInicioDisp) OR ('2021-06-20' < fechaNoDisp AND '2021-07-28'> fechaInicioDisp));

SELECT DISTINCT co.*
	FROM cocheofertado co, disponibilidad d
	WHERE co.idFilialCoche = ? AND co.enMantenimiento = 0 AND co.idCocheOfertado NOT IN (
		SELECT idCocheDisponibilidad
			FROM disponibilidad d
			WHERE ((? > d.fechaNoDisp AND ? < d.fechaInicioDisp) OR (? > d.fechaNoDisp AND ? < d.fechaInicioDisp)))
	LIMIT ?,?;


DELETE FROM reserva WHERE idReserva=1;

SELECT *
	FROM cocheOfertado
	WHERE idCocheOfertado = 1;

DELETE FROM disponibilidad 
	WHERE '2021-04-02' = fechaNoDisp AND '2021-04-14' = fechaInicioDisp AND idCocheDisponibilidad = 1;
    
SELECT * FROM pago;