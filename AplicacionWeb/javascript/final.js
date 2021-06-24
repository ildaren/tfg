function pago(id) {

	if (usuario.idUsuario) {

		cocheSeleccionado = coches[id];
		document.getElementById('detallesReserva').style.display='none';
		document.getElementById('Reservas').style.display='none';
		document.getElementById("BarraNav").style.display='block';
		document.getElementById("InicioS").style.display='none';
		document.getElementById("LogIn").style.display='none';
		document.getElementById("datosPago").style.display='block';

		var foot = document.getElementById("Foot");
		foot.style.marginTop = 300;

		document.getElementById("numTarjeta").value = "";
		document.getElementById("titularTarjeta").value = "";
		document.getElementById("fechaExp").value = "";
		document.getElementById("cvv").value = "";

		var recogida = document.getElementById("recogida").value;
		var devolucion = document.getElementById("devolucion").value;

		var dateR = new Date(recogida);
		var dateD = new Date(devolucion);

		var days = (dateD - dateR)/(60 * 60 * 24 * 1000);

		var pPrecio = document.getElementById("precioAPagar");
		pPrecio.removeChild(pPrecio.childNodes[0]);
		var textPrecio = document.createTextNode("A pagar: " + (days * coches[id].precioDia));
		pPrecio.appendChild(textPrecio);

		fetch("http://localhost:3000/filial", {
			method: 'GET',
		})
		.then (res => res.json())
		.then (data => {
			
			var selectFilial = document.getElementById("filialEntrega");
			var i = 0;
			while (i < data.length) {
				var option = document.createElement("option");
				option.id = data[i].idFilial;
				option.text = data[i].ciudad + ", " + data[i].calle;
				selectFilial.add(option);
				i++;
			}
		})

		var tablaCoche = document.getElementById("tablaCochePago")
		while (tablaCoche.hasChildNodes()) {
			tablaCoche.removeChild(tablaCoche.childNodes[0]);
		}
		
		//Crear la primera hilera 
		var hileraUno = document.createElement("tr");
					
		//Crear primera celda, que es la de la imagen que ocupara las cinco hileras
		var columUno = document.createElement("td");
		columUno.rowSpan = 5;
		columUno.style.width="15%";
		var imagen = document.createElement("img");
		imagen.className = "imagenCoche";
		if ((coches[id].modelo).localeCompare("V90") == 0) imagen.src = "https://cdn.motor1.com/images/mgl/kjZXB/s3/2021-volvo-v90.jpg";
		else if (coches[id].modelo.localeCompare("C1") == 0) imagen.src = "https://img.motor16.com/modelos/citroen-c1.jpg";
		else if (coches[id].modelo.localeCompare("Corsa") == 0) imagen.src = "https://d1eip2zddc2vdv.cloudfront.net/dphotos/9889171-1592418302.jpeg";
		else if (coches[id].modelo.localeCompare("A4") == 0) imagen.src = "https://cdn.autobild.es/sites/navi.axelspringer.es/public/media/image/2021/01/rivales-audi-a4-2021-2188573.jpg";
		else if (coches[id].modelo.localeCompare("X3") == 0) imagen.src = "https://st.motortrend.com/uploads/sites/5/2019/11/2020-BMW-X3M-Competition-front-three-quarter-in-motion-2.jpg";
		else if (coches[id].modelo.localeCompare("Panda") == 0) imagen.src = "https://www.diariomotor.com/imagenes/2019/11/fiat-panda-2019.jpg";
		else if (coches[id].modelo.localeCompare("Civic") == 0) imagen.src = "https://hips.hearstapps.com/es.h-cdn.co/cades/contenidos/9051/new-civic-hatchback-101.jpg?resize=480:*";
		else if (coches[id].modelo.localeCompare("Kona") == 0) imagen.src = "https://cdn.drivek.it/configurator-covermobile/cars/es/$original$/HYUNDAI/KONA-ELECTRIC/40189_SUV-5-DOORS/hyundai-kona-electric-2021-mobile-cover.jpg";
		else if (coches[id].modelo.localeCompare("Prius") == 0) imagen.src = "https://cdn.motor1.com/images/mgl/rPjvY/s1/guia-de-compra-toyota-prius-hybrid-i-awd.jpg";
		else if (coches[id].modelo.localeCompare("Model X") == 0) imagen.src = "https://forococheselectricos.com/wp-content/uploads/2021/01/Tesla-Model-X.jpg";
		else if (coches[id].modelo.localeCompare("Vitara") == 0) imagen.src = "https://d1eip2zddc2vdv.cloudfront.net/dphotos/9889121-1592417620.jpeg";
		
		columUno.appendChild(imagen);
		hileraUno.appendChild(columUno);

		//Crear segunda celda, marca y modelo del coche
		var columDos = document.createElement("td");
		var marcaModelo = document.createTextNode(coches[id].marca + " " + coches[id].modelo);
		columDos.appendChild(marcaModelo);
		hileraUno.appendChild(columDos);

		//Crear tercera celda, vacia
		var columTres = document.createElement("td");
		hileraUno.appendChild(columTres);

		//Crear cuarta celda, matricula
		var columCuatro = document.createElement("td");
		var matricula = document.createTextNode("Matricula: " + coches[id].matricula);
		columCuatro.appendChild(matricula);
		hileraUno.appendChild(columCuatro);

		// agrega la hilera al final de la tabla (al final del elemento tblbody)
		tablaCoche.appendChild(hileraUno);
		
		//Creo la segunda hilera
		var hileraDos = document.createElement("tr");

		//Creo segunda celda, puertas
		var columDos = document.createElement("td");
		var puertas = document.createTextNode("Puertas: " + coches[id].numeroPuertas);
		columDos.appendChild(puertas);
		hileraDos.appendChild(columDos);

		//Crear tercera celda, maletero
		var columTres = document.createElement("td");
		var maletero = document.createTextNode("Maletero: " + coches[id].capacidadMaletero);
		columTres.appendChild(maletero);
		hileraDos.appendChild(columTres);

		//Crear cuarta celda, cancelacion
		var columCuatro = document.createElement("td");
		var cancelacion;
		if (coches[id].politicaCancelacion == 0) {
			cancelacion = document.createTextNode("Politica de cancelación: gratuita");
		}
		else {
			cancelacion = document.createTextNode("Politica de cancelación: " + coches[id].politicaCancelacion + "%");
		}
		columCuatro.appendChild(cancelacion);
		hileraDos.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraDos);

		//Creo la tercera hilera
		var hileraTres = document.createElement("tr");

		//Creo segunda celda, aire acondicionado
		var columDos = document.createElement("td");
		var aire;
		
		if (coches[id].aireAcondicionado) {
			aire = document.createTextNode("Aire acondicionado: si");
		}
		else {
			aire = document.createTextNode("Aire acondicionado: no");
		}
		columDos.appendChild(aire);
		hileraTres.appendChild(columDos);

		//Crear tercera celda, marchas
		var columTres = document.createElement("td");
		var marchas = document.createTextNode("Cambio de marcha: " + coches[id].cambioMarchas);
		columTres.appendChild(marchas);
		hileraTres.appendChild(columTres);

		//Crear cuarta celda, cancelacion
		var columCuatro = document.createElement("td");
		var combustible = document.createTextNode("Politica de combustible: " + coches[id].politicaCombustible);
		columCuatro.appendChild(combustible);
		hileraTres.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraTres);

		//Creo la cuarta hilera
		var hileraCuatro = document.createElement("tr");

		//Creo segunda celda, plazas
		var columDos = document.createElement("td");
		var plazas = document.createTextNode("Plazas: " + coches[id].plazas);
		columDos.appendChild(plazas);
		hileraCuatro.appendChild(columDos);

		//Crear tercera celda, vacio
		var columTres = document.createElement("td");
		hileraCuatro.appendChild(columTres);

		//Crear cuarta celda, kilometraje
		var columCuatro = document.createElement("td");
		var kilometraje = document.createTextNode("Kilometros: " + coches[id].kilometraje);
		columCuatro.appendChild(kilometraje);
		hileraCuatro.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraCuatro);

		//Creo la quinta hilera
		var hileraCinco = document.createElement("tr");

		//Creo segunda celda, precio
		var columDos = document.createElement("td");
		columDos.id = "precioD";
		var precio = document.createTextNode("Pecio/dia: " + coches[id].precioDia);
		columDos.appendChild(precio);
		hileraCinco.appendChild(columDos);

		//Crear tercera celda, vacio
		var columTres = document.createElement("td");
		hileraCinco.appendChild(columTres);

		//Crear cuarta celda, kilometraje
		var columCuatro = document.createElement("td");
		hileraCinco.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraCinco);

		return false;
	}
	else {
		swal("Se debe inicar sesion para realizar una reserva.");

		return false;
	}
}

function detalleReserva(id) {
			
	var reserva = reservas[id];
	document.getElementById('detallesReserva').style.display='block';
	document.getElementById('Reservas').style.display='none';
	document.getElementById("BarraNav").style.display='block';
	document.getElementById("InicioS").style.display='none';
	document.getElementById("LogIn").style.display='none';

	var numeroR = document.getElementById("numeroR");
	var reservaF = document.getElementById("reservaF");
	var recogidaL = document.getElementById("recogidaL");
	var entregaL = document.getElementById("entregaL");
	var recogidaF = document.getElementById("recogidaF");
	var entregaF = document.getElementById("entregaF");

	numeroR.innerHTML = "Numero de reserva: " + reserva.idReserva;
	reservaF.value = reserva.fechaReserva;
	entregaF.value = reserva.fechaEntrega;
	recogidaF.value = reserva.fechaRecogida;
	reserva.hrefFilialEntrega.substr(41, reserva.hrefFilialEntrega.length);

	fetch("http://localhost:3000/filial/" + reserva.hrefFilialEntrega.substr(41, reserva.hrefFilialEntrega.length), {
		method: 'GET',
	})
	.then (res => res.json())
	.then (data => {
		while (entregaL.hasChildNodes()) {
			entregaL.removeChild(entregaL.childNodes[0]);
		}
		var option = document.createElement("option");
		option.text = data.ciudad + ", " + data.calle;
		entregaL.appendChild(option);
	})

	fetch("http://localhost:3000/filial/" + reserva.hrefFilialRecogida.substr(41, reserva.hrefFilialRecogida.length), {
		method: 'GET',
	})
	.then (res => res.json())
	.then (data => {
		while (recogidaL.hasChildNodes()) {
			recogidaL.removeChild(recogidaL.childNodes[0]);
		}
		var option = document.createElement("option");
		option.text = data.ciudad + ", " + data.calle;
		recogidaL.appendChild(option);
	})

	var today = new Date();

	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	recogidaF.min = yyyy + "-" + mm + "-" + dd;
	entregaF.min = yyyy + "-" + mm + "-" + dd;

	var yyyyR = reserva.fechaRecogida.substr(0, 4);
	var mmR = reserva.fechaRecogida.substr(5, 2);
	mmR = mmR - 1;
	if (mmR < 10) mmR = "0" + mmR;
	var ddR = reserva.fechaRecogida.substr(8, 9);

	var dateR = new Date(yyyyR, mmR, ddR);
	var mod = document.getElementById("Modificar");
	var rem = document.getElementById("Cancelar");

	if (dateR > today) {
		mod.style.display = 'block';
		rem.style.display = 'block';
	}
	else {
		mod.style.display = 'none';
		rem.style.display = 'none';
	}

	fetch("http://localhost:3000/filial/" + reserva.hrefCoche.substr(41, reserva.hrefCoche.length), {
		method: 'GET',
	})
	.then (res => res.json())
	.then (data => {

		var tablaCoche = document.getElementById("tablaCocheReserva")
		while (tablaCoche.hasChildNodes()) {
			tablaCoche.removeChild(tablaCoche.childNodes[0]);
		}
		
		//Crear la primera hilera 
		var hileraUno = document.createElement("tr");
					
		//Crear primera celda, que es la de la imagen que ocupara las cinco hileras
		var columUno = document.createElement("td");
		columUno.rowSpan = 5;
		columUno.style.width="15%";
		var imagen = document.createElement("img");
		imagen.className = "imagenCoche";
		if ((data.modelo).localeCompare("V90") == 0) imagen.src = "https://cdn.motor1.com/images/mgl/kjZXB/s3/2021-volvo-v90.jpg";
		else if (data.modelo.localeCompare("C1") == 0) imagen.src = "https://img.motor16.com/modelos/citroen-c1.jpg";
		else if (data.modelo.localeCompare("Corsa") == 0) imagen.src = "https://d1eip2zddc2vdv.cloudfront.net/dphotos/9889171-1592418302.jpeg";
		else if (data.modelo.localeCompare("A4") == 0) imagen.src = "https://cdn.autobild.es/sites/navi.axelspringer.es/public/media/image/2021/01/rivales-audi-a4-2021-2188573.jpg";
		else if (data.modelo.localeCompare("X3") == 0) imagen.src = "https://st.motortrend.com/uploads/sites/5/2019/11/2020-BMW-X3M-Competition-front-three-quarter-in-motion-2.jpg";
		else if (data.modelo.localeCompare("Panda") == 0) imagen.src = "https://www.diariomotor.com/imagenes/2019/11/fiat-panda-2019.jpg";
		else if (data.modelo.localeCompare("Civic") == 0) imagen.src = "https://hips.hearstapps.com/es.h-cdn.co/cades/contenidos/9051/new-civic-hatchback-101.jpg?resize=480:*";
		else if (data.modelo.localeCompare("Kona") == 0) imagen.src = "https://cdn.drivek.it/configurator-covermobile/cars/es/$original$/HYUNDAI/KONA-ELECTRIC/40189_SUV-5-DOORS/hyundai-kona-electric-2021-mobile-cover.jpg";
		else if (data.modelo.localeCompare("Prius") == 0) imagen.src = "https://cdn.motor1.com/images/mgl/rPjvY/s1/guia-de-compra-toyota-prius-hybrid-i-awd.jpg";
		else if (data.modelo.localeCompare("Model X") == 0) imagen.src = "https://forococheselectricos.com/wp-content/uploads/2021/01/Tesla-Model-X.jpg";
		else if (data.modelo.localeCompare("Vitara") == 0) imagen.src = "https://d1eip2zddc2vdv.cloudfront.net/dphotos/9889121-1592417620.jpeg";
		columUno.appendChild(imagen);
		hileraUno.appendChild(columUno);

		//Crear segunda celda, marca y modelo del coche
		var columDos = document.createElement("td");
		var marcaModelo = document.createTextNode(data.marca + " " + data.modelo);
		columDos.appendChild(marcaModelo);
		hileraUno.appendChild(columDos);

		//Crear tercera celda, vacia
		var columTres = document.createElement("td");
		hileraUno.appendChild(columTres);

		//Crear cuarta celda, matricula
		var columCuatro = document.createElement("td");
		var matricula = document.createTextNode("Matricula: " + data.matricula);
		columCuatro.appendChild(matricula);
		hileraUno.appendChild(columCuatro);

		// agrega la hilera al final de la tabla (al final del elemento tblbody)
		tablaCoche.appendChild(hileraUno);
		
		//Creo la segunda hilera
		var hileraDos = document.createElement("tr");

		//Creo segunda celda, puertas
		var columDos = document.createElement("td");
		var puertas = document.createTextNode("Puertas: " + data.numeroPuertas);
		columDos.appendChild(puertas);
		hileraDos.appendChild(columDos);

		//Crear tercera celda, maletero
		var columTres = document.createElement("td");
		var maletero = document.createTextNode("Maletero: " + data.capacidadMaletero);
		columTres.appendChild(maletero);
		hileraDos.appendChild(columTres);

		//Crear cuarta celda, cancelacion
		var columCuatro = document.createElement("td");
		var cancelacion;
		if (data.politicaCancelacion == 0) {
			cancelacion = document.createTextNode("Politica de cancelación: gratuita");
		}
		else {
			cancelacion = document.createTextNode("Politica de cancelación: " + data.politicaCancelacion + "%");
		}
		columCuatro.appendChild(cancelacion);
		hileraDos.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraDos);

		//Creo la tercera hilera
		var hileraTres = document.createElement("tr");

		//Creo segunda celda, aire acondicionado
		var columDos = document.createElement("td");
		var aire;
		
		if (data.aireAcondicionado) {
			aire = document.createTextNode("Aire acondicionado: si");
		}
		else {
			aire = document.createTextNode("Aire acondicionado: no");
		}
		columDos.appendChild(aire);
		hileraTres.appendChild(columDos);

		//Crear tercera celda, marchas
		var columTres = document.createElement("td");
		var marchas = document.createTextNode("Cambio de marcha: " + data.cambioMarchas);
		columTres.appendChild(marchas);
		hileraTres.appendChild(columTres);

		//Crear cuarta celda, cancelacion
		var columCuatro = document.createElement("td");
		var combustible = document.createTextNode("Politica de combustible: " + data.politicaCombustible);
		columCuatro.appendChild(combustible);
		hileraTres.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraTres);

		//Creo la cuarta hilera
		var hileraCuatro = document.createElement("tr");

		//Creo segunda celda, plazas
		var columDos = document.createElement("td");
		var plazas = document.createTextNode("Plazas: " + data.plazas);
		columDos.appendChild(plazas);
		hileraCuatro.appendChild(columDos);

		//Crear tercera celda, vacio
		var columTres = document.createElement("td");
		hileraCuatro.appendChild(columTres);

		//Crear cuarta celda, kilometraje
		var columCuatro = document.createElement("td");
		var kilometraje = document.createTextNode("Kilometros: " + data.kilometraje);
		columCuatro.appendChild(kilometraje);
		hileraCuatro.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraCuatro);

		//Creo la quinta hilera
		var hileraCinco = document.createElement("tr");

		//Creo segunda celda, precio
		var columDos = document.createElement("td");
		columDos.id = "precioD";
		var precio = document.createTextNode("Pecio/dia: " + data.precioDia);
		columDos.appendChild(precio);
		hileraCinco.appendChild(columDos);

		//Crear tercera celda, vacio
		var columTres = document.createElement("td");
		hileraCinco.appendChild(columTres);

		//Crear cuarta celda, kilometraje
		var columCuatro = document.createElement("td");
		hileraCinco.appendChild(columCuatro);

		//Agrego la hilera a la tabla
		tablaCoche.appendChild(hileraCinco);
	})
	return false;
}

function misReservas() {

	document.getElementById("Reservas").style.display='block';
	document.getElementById("BarraNav").style.display='block';
	document.getElementById("InicioS").style.display='none';
	document.getElementById("LogIn").style.display='none';
	document.getElementById("Foot").style.display='block';

	document.getElementById("detallesReserva").style.display='none';
	document.getElementById("datosPago").style.display='none';
	document.getElementById("Enviar").style.display='none';
	document.getElementById("CandelarMod").style.display='none';
	document.getElementById("Modificar").style.display='block';
	document.getElementById("Cancelar").style.display='block';
	var posiciones = [];
	fetch("http://localhost:3000/usuario/" + usuario.idUsuario + "/reserva", {
		method: 'GET',
	})
	.then (res => res.json())
	.then (data => {
		
		
		reservas = data.reservas;

		var foot = document.getElementById("Foot");
		if (reservas.length == 0) foot.style.marginTop = 620;
		else if (reservas.length == 1) foot.style.marginTop = 520;
		else if (reservas.length == 2) foot.style.marginTop = 420;
		else foot.style.marginTop = 0;

		posiciones.length = reservas.length;
		var ano = document.getElementById("selectAno");
		var yyyy = ano.options[ano.selectedIndex].text;

		var ul = document.getElementById("listaReservas");

		while (ul.hasChildNodes()) {
			ul.removeChild(ul.childNodes[0]);
		}

		for (var n = 0; n < reservas.length; n++) {
			var añoDeReserva = (reservas[n].fechaReserva).substr(0, 4);
			if (añoDeReserva.localeCompare(yyyy) == 0) {
				var reserva = reservas[n];
				var dl = document.createElement("dl");
				var tabla = document.createElement("table");
				tabla.className = "tabla container";
				
				// Crea primera hilera de la tabla
				var hileraUno = document.createElement("tr");
			
				//Crear primera celda, que es la de la imagen que ocupara las cinco hileras
				var columUno = document.createElement("td");
				var numeroReserva = document.createTextNode("Nº de reserva: " + reserva.idReserva);
				columUno.appendChild(numeroReserva);
				hileraUno.appendChild(columUno);

				//Crear segunda celda, vacia
				var columDos = document.createElement("td");
				hileraUno.appendChild(columDos);

				//Crear tercera celda, con el icono
				var columTres = document.createElement("td");
				columTres.rowSpan = "2";
				columTres.className = "icono";
				columTres.style.paddingLeft = "0";
				var a = document.createElement("a");
				a.id = n;
				a.addEventListener("click", function() {
					detalleReserva(this.id);
				});
				var icono = document.createElement("i");
				icono.className = "bi bi-box-arrow-in-right";
				a.appendChild(icono);
				columTres.appendChild(a);
				hileraUno.appendChild(columTres);

				//Agregar hilera a la tabla
				tabla.appendChild(hileraUno);

				//Crear segunda hilera de la tabla
				var hileraDos = document.createElement("tr");

				//Crear primera celda, fecha de la reserva
				var columUno = document.createElement("td");
				var fechaReserva = document.createTextNode("Fecha de reserva: " + reserva.fechaReserva);
				columUno.appendChild(fechaReserva);
				hileraDos.appendChild(columUno);

				//Crear segunda celda, fecha de recogida
				var columDos = document.createElement("td");
				var columHref = new Object();
				columHref.columna = columDos;
				columHref.idFilial = reserva.hrefFilialRecogida.substr(41, reserva.hrefFilialRecogida.length);
				posiciones[n] = columHref;

				fetch("http://localhost:3000/filial/" + reserva.hrefFilialRecogida.substr(41, reserva.hrefFilialRecogida.length), {
					method: 'GET',
				})
				.then (response => response.json())
				.then (dataFilial => {
					
					for (var i = 0; i < posiciones.length; i++) {
						if (posiciones[i].idFilial.localeCompare("" + dataFilial.idFilial) == 0) {
							var lugarRecogida = document.createTextNode("Lugar de recogida: " + dataFilial.ciudad + ", " + dataFilial.calle + ", " + dataFilial.numero);
							posiciones[i].columna.appendChild(lugarRecogida);
						}
					}
				})

				hileraDos.appendChild(columDos);

				tabla.appendChild(hileraDos);
				dl.appendChild(tabla);
				ul.appendChild(dl);
			}
			else {
				var encontrado = false;
				var select = document.getElementById("selectAno");
					
				for (var i = 0; (i < select.length) && (!encontrado); i++) {
					if (añoDeReserva.localeCompare(select.options[i].text)) encontrado = true;
				}

				if (!encontrado) {
					var option = document.createElement("option");
					option.text = añoDeReserva;
					select.appendChild(option);
				}
			}
		}
	})
    return false;
}

function cerrarSesion() {
	document.getElementById("LogIn").style.display='none';
    document.getElementById("InicioS").style.display='block';
    document.getElementById("BarraNav").style.display='block';
	document.getElementById("Reservas").style.display='none';
	document.getElementById("detallesReserva").style.display='none';
	document.getElementById("datosPago").style.display='none';
	document.getElementById("Foot").style.display='block';

	document.getElementById("InicioRegistro").style.display='block';
	document.getElementById("Usuario").style.display='none';
	
	usuario = "";
}

function inicio() {
	document.getElementById("LogIn").style.display='none';
    document.getElementById("InicioS").style.display='block';
    document.getElementById("BarraNav").style.display='block';
	document.getElementById("Reservas").style.display='none';
	document.getElementById("detallesReserva").style.display='none';
	document.getElementById("datosPago").style.display='none';
	document.getElementById("Foot").style.display='block';

	var foot = document.getElementById("Foot");
	foot.style.marginTop = 700;

    return false;
}

function login() {
    document.getElementById("LogIn").style.display='block';
    document.getElementById("InicioS").style.display='none';
    document.getElementById("BarraNav").style.display='none';
	document.getElementById("Reservas").style.display='none';
	document.getElementById("detallesReserva").style.display='none';
	document.getElementById("datosPago").style.display='none';
	document.getElementById("Foot").style.display='none';

    return false;
}

function registro() {
    document.getElementById("LogIn").style.display='block';
    document.getElementById("InicioS").style.display='none';
    document.getElementById("BarraNav").style.display='none';
	document.getElementById("Reservas").style.display='none';
	document.getElementById("detallesReserva").style.display='none';
	document.getElementById("datosPago").style.display='none';
	document.getElementById("Foot").style.display='none';

    var $loginMsg = $('.loginMsg'),
		$signupMsg = $('.signupMsg'),
		$frontboxL = $('.frontbox .login');
		$frontboxS = $('.frontbox .signup');

    $loginMsg.toggleClass("visibility");
    $signupMsg.toggleClass("visibility");
    
    $frontboxL.toggleClass("hide");
    $frontboxS.toggleClass("hide");

    return false;
}

sesionIniciada = false;

$(document).ready(function(){

	var today = new Date();

	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	document.getElementById("recogida").min = yyyy + "-" + mm + "-" + dd;
	document.getElementById("recogida").value = yyyy + "-" + mm + "-" + dd;

	fetch("http://localhost:3000/filial", {
		method: 'GET',
	})
	.then (res => res.json())
	.then (data => {
		
		var selectFilial = document.getElementById("inputState");
		var i = 0;
		while (i < data.length) {
			var option = document.createElement("option");
			option.id = data[i].idFilial;
			option.text = data[i].ciudad + ", " + data[i].calle;
			selectFilial.add(option);
			i++;
		}
	})

    var $loginMsg = $('.loginMsg'),
		$signupMsg = $('.signupMsg'),
		$frontboxL = $('.frontbox .login');
		$frontboxS = $('.frontbox .signup');

	$('#switch1').on('click', function() {
		$loginMsg.toggleClass("visibility");
		$signupMsg.toggleClass("visibility");
		
		$frontboxL.toggleClass("hide");
		$frontboxS.toggleClass("hide");
	})

	$('#switch2').on('click', function() {
		$loginMsg.toggleClass("visibility");
		$signupMsg.toggleClass("visibility");
		
		$frontboxL.toggleClass("hide");
		$frontboxS.toggleClass("hide");
	})

    $('#login').on('click', function () {
		
		var url = new URL("http://localhost:3000/usuario")
		var dni = document.getElementById("dni").value
		var pass = document.getElementById("contrasena").value
		//params = {dni:"78952587S", contrasena:"holaMundo12345"}
		var params = {dni:dni, contrasena:pass}
		Object.keys(params).forEach(key => url.searchParams.append(key, params[key]))
		fetch(url, {
			method: 'GET',
		})
		.then(res => {
			if (res.status == 200) {
				res.json().then(data => {
					usuario = data;
					sesionIniciada = true;

					document.getElementById("dni").value = "";
					document.getElementById("contrasena").value = "";

                    document.getElementById("LogIn").style.display='none';
                    document.getElementById("InicioRegistro").style.display='none';
                    document.getElementById("Usuario").style.display='block';
					document.getElementById("InicioS").style.display='block';
                    document.getElementById("BarraNav").style.display='block';
					document.getElementById("datosPago").style.display='none';
					document.getElementById("Foot").style.display='block';
                    document.getElementById("usuario").innerHTML = usuario.nombre + " " + usuario.apellidos;
				})
			}
			else {
				res.json().then(data => {
					swal(data.error)
				})
			}
		})
  	})

	$('#Pagar').on('click', function() {

		var numeroT = document.getElementById("numTarjeta").value;
		var titularT = document.getElementById("titularTarjeta").value;

		var filialEntrega =  document.getElementById("filialEntrega");
		var hrefFilialEntrega = filialEntrega.options[filialEntrega.selectedIndex].id;
		var filialRecogida =  document.getElementById("inputState");
		var hrefFilialRecogida = filialRecogida.options[filialRecogida.selectedIndex].id;
		var recogidaF = document.getElementById("recogida").value;
		var entregaF = document.getElementById("devolucion").value;
		

		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();
		var reservaF = yyyy + "-" + mm + "-" + dd;

		var hrefCoche = cocheSeleccionado.idCoche;

		var dateR = new Date(recogidaF);
		var dateD = new Date(entregaF);

		var days = (dateD - dateR)/(60 * 60 * 24 * 1000);

		var precioDia = (days * cocheSeleccionado.precioDia);
		
		var pago = new Object();
		pago.titular = titularT;
		pago.numTarjeta = numeroT;

		var reserva = new Object();
		reserva.fechaEntrega = entregaF;
		reserva.fechaRecogida = recogidaF;
		reserva.fechaReserva = reservaF;
		reserva.hrefCoche = hrefCoche;
		reserva.hrefFilialEntrega = hrefFilialEntrega;
		reserva.hrefFilialRecogida = hrefFilialRecogida;
		reserva.precioFinal = precioDia;

		var pagoReserva = new Object();
		pagoReserva.pago = pago;
		pagoReserva.reserva = reserva;

		fetch("http://localhost:3000/usuario/" + usuario.idUsuario + "/reserva", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(pagoReserva)
		})
		.then(res => res.json())
		.then(data => {
			swal(data.error).then(() => {

				misReservas();
			})
		})
	})

	$('#CancelarPago').on('click', function() {

		document.getElementById("numTarjeta").value = "";
		document.getElementById("titularTarjeta").value = "";
		document.getElementById("fechaExp").value = "";
		document.getElementById("cvv").value = "";

		document.getElementById('detallesReserva').style.display='none';
		document.getElementById('Reservas').style.display='none';
		document.getElementById("BarraNav").style.display='block';
		document.getElementById("InicioS").style.display='block';
		document.getElementById("LogIn").style.display='none';
		document.getElementById("datosPago").style.display='none';
		document.getElementById("Foot").style.display='block';
	})

	$('#signup').on('click', function() {

		var usuario = new Object();
		usuario.nombre = document.getElementById("nombreR").value;
		usuario.apellidos = document.getElementById("apellidosR").value;
		usuario.correoElectronico = document.getElementById("emailR").value;
		usuario.dni = document.getElementById("dniR").value;
		usuario.ciudad = document.getElementById("ciudadR").value;
		usuario.direccion = document.getElementById("direccionR").value;
		usuario.codigoPostal = document.getElementById("codigoPostalR").value
		usuario.fechaCarne = document.getElementById("fechaCarneR").value
		usuario.contrasena = document.getElementById("passR").value

		fetch("http://localhost:3000/usuario", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(usuario)
		})
		.then(res => res.json())
		.then(data => {
			swal(data.error).then(() => {

				document.getElementById("nombreR").value = "";
				document.getElementById("apellidosR").value = "";
				document.getElementById("emailR").value = "";
				document.getElementById("dniR").value = "";
				document.getElementById("ciudadR").value = "";
				document.getElementById("direccionR").value = "";
				document.getElementById("codigoPostalR").value = "";
				document.getElementById("fechaCarneR").value = "";
				document.getElementById("passR").value = "";
				var $loginMsg = $('.loginMsg'),
					$signupMsg = $('.signupMsg'),
					$frontboxL = $('.frontbox .login');
					$frontboxS = $('.frontbox .signup');

				$loginMsg.toggleClass("visibility");
				$signupMsg.toggleClass("visibility");
				
				$frontboxL.toggleClass("hide");
				$frontboxS.toggleClass("hide");
			})
		})
	})

	$('#Enviar').on('click', function() {

		//PUT http://localhost:8080/apirest/api/usuario/2/reserva/1 http/1.1
		//Content-Type: application/json

		//{"fechaRecogida":"2021-07-10","fechaEntrega":"2021-07-28","precioFinal":160.36,"hrefFilialEntrega":"1"}
		var fechaRecogida = document.getElementById("recogidaF").value;
		var fechaEntrega = document.getElementById("entregaF").value;
		var selectedFilial =  document.getElementById("entregaL");
		var hrefFilialEntrega = selectedFilial.options[selectedFilial.selectedIndex].id;
		var precioFinal = document.getElementById("precioD").textContent;
		var reserva = new Object();
		reserva.fechaRecogida = fechaRecogida;
		reserva.fechaEntrega = fechaEntrega;
		reserva.precioFinal = precioFinal.substr(11, precioFinal.length);
		reserva.hrefFilialEntrega = hrefFilialEntrega;
		var idReserva = document.getElementById("numeroR").innerHTML.substr(19, document.getElementById("numeroR").innerHTML.length);
		console.log(reserva);

		fetch("http://localhost:3000/usuario/" + usuario.idUsuario + "/reserva/" + idReserva, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reserva)
		})
		.then(res => res.json())
		.then(data => {
			swal(data.error).then(() => {
				misReservas();
			})
		})
		document.getElementById("recogidaF").readOnly = true;
		document.getElementById("entregaF").readOnly = true;
		document.getElementById("entregaL").disabled = true;
	})

	$('#Cancelar').on('click', function() {

		swal({
			title: "¿Estas seguro?",
			text: "¡Una vez cancelada no podras recuperar la reserva!",
			buttons: true,
			dangerMode: true,
		})
		.then((willDelete) => {
			if (willDelete) {

				var idReserva = document.getElementById("numeroR").innerHTML.substr(19, document.getElementById("numeroR").innerHTML.length);
				fetch("http://localhost:3000/usuario/" + usuario.idUsuario + "/reserva/" + idReserva, {
					method: 'DELETE',
				})
				.then(res => res.json())
				.then(data => {
					swal(data.error).then(() => {
						misReservas();
					})
				})
			}
		});
		document.getElementById("recogidaF").readOnly = true;
		document.getElementById("entregaF").readOnly = true;
		document.getElementById("entregaL").disabled = true;		
	})

	$('#CandelarMod').on('click', function() {

		document.getElementById("Reservas").style.display='block';
		document.getElementById("BarraNav").style.display='block';
    	document.getElementById("InicioS").style.display='none';
		document.getElementById("LogIn").style.display='none';
		document.getElementById("Foot").style.display='block';
		document.getElementById("detallesReserva").style.display='none';
		document.getElementById("Enviar").style.display='none';
		document.getElementById("CandelarMod").style.display='none';
		document.getElementById("Modificar").style.display='block';
		document.getElementById("Cancelar").style.display='block';
	})

	$('#Modificar').on('click', function() {
		document.getElementById("Enviar").style.display='block';
		document.getElementById("Modificar").style.display='none';
		document.getElementById("Cancelar").style.display='none';
		document.getElementById("CandelarMod").style.display='block';
		document.getElementById("Foot").style.display='block';

		document.getElementById("recogidaF").readOnly = false;
		document.getElementById("entregaF").readOnly = false;
		document.getElementById("entregaL").disabled = false;

		var today = new Date();

		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();

		document.getElementById("recogidaF").min = yyyy + "-" + mm + "-" + dd;

		fetch("http://localhost:3000/filial", {
			method: 'GET',
		})
		.then (res => res.json())
		.then (data => {

			while (entregaL.hasChildNodes()) {
				entregaL.removeChild(entregaL.childNodes[0]);
			}
			
			var i = 0;
			while (i < data.length) {

				if (data[i].calle.localeCompare())
				var option = document.createElement("option");
				option.id = data[i].idFilial;
				option.text = data[i].ciudad + ", " + data[i].calle;
				entregaL.add(option);
				i++;
			}
		})
	})

	$('#busqueda').on('click', function() {
		
		var fechaInicio = document.getElementById("recogida").value
		var fechaFinal = document.getElementById("devolucion").value
		if (fechaFinal.localeCompare("") == 0) {
			swal("Debe seleccionar una fecha de devolucion.")
		}
		else {
			var selectFilial = document.getElementById("inputState");
		
			var url = new URL("http://localhost:3000/filial/" + selectFilial.options[selectFilial.selectedIndex].id + "/oferta")
			//params = {dni:"78952587S", contrasena:"holaMundo12345"}
			params = {fechaInicio:fechaInicio, fechaFinal:fechaFinal}
			Object.keys(params).forEach(key => url.searchParams.append(key, params[key]))
			fetch(url, {
				method: 'GET',
			})
			.then(res => res.json())
			.then(data => {
				coches = data.cochesOfertados;

				//Comprobacion que numero de plazas buscamos
				var mayor = 0;
				if (document.getElementById("plazas8").checked) mayor = 8;

				if (document.getElementById("plazas6").checked) mayor = 6;

				if (document.getElementById("plazas4").checked) mayor = 5;
				
				if (document.getElementById("plazas5").checked) mayor = 4;
				
				

				//Comprobacion que numero de puertas buscamos
				var puertasMenor;
				var puertasmayor;
				if (document.getElementById("puertas23").checked && !document.getElementById("puertas45").checked) {
					puertasMenor = 2; puertasmayor = 3;
				}
				else if (!document.getElementById("puertas23").checked && document.getElementById("puertas45").checked) {
					puertasMenor = 4; puertasmayor = 5;
				}
				else {
					puertasMenor = 2;
					puertasmayor = 5;
				}

				//Comprobacion que tipo de marchas buscamos
				var marchasAuto = false;
				var marchasManual = false;
				if (document.getElementById("marchasAuto").checked) marchasAuto = true;
				if (document.getElementById("marchasManual").checked) marchasManual = true;

				//Comprobacion que otros datos buscamos
				var aireAcond = false;
				var cancelacionPol = false;
				var combustiblePol = false;
				var kilometrajePol = false;
				if (document.getElementById("aire").checked) aireAcond = true;
				if (document.getElementById("cancelacion").checked) cancelacionPol = true;
				if (document.getElementById("combustible").checked) combustiblePol = true;
				if (document.getElementById("kilometraje").checked) kilometrajePol = true;

				var ul = document.getElementById("list");

				while (ul.hasChildNodes()) {
					ul.removeChild(ul.childNodes[0]);
				}

				var cochesCumpleCond = 0;

				for (var n = 0; n < coches.length; n++) {

					var cumpleCond = true;

					//Comprobacion de las plazas
					if (coches[n].plazas >= mayor) {
						cumpleCond = true;

						//Comprobacion de las puertas
						if (coches[n].numeroPuertas >= puertasMenor && coches[n].numeroPuertas <= puertasmayor) {
							cumpleCond = true;

							//Comprobacion marchas
							
							if ((marchasAuto && !marchasManual && !((coches[n].cambioMarchas).localeCompare("automatico") == 0)) || (marchasManual && !marchasAuto && !((coches[n].cambioMarchas).localeCompare("manual") == 0 ))) {
								
								cumpleCond = false;
							}
							else {
								if (aireAcond && !coches[n].aireAcondicionado) cumpleCond = false;
								if (cancelacionPol && !(coches[n].politicaCancelacion == 0)) cumpleCond = false;
								if (combustiblePol && !((coches[n].politicaCombustible).localeCompare("Lleno-Lleno") == 0)) cumpleCond = false;
								if (kilometrajePol && !(coches[n].kilometraje == 0)) cumpleCond = false; 
							}
						}
						else cumpleCond = false;
					}
					else cumpleCond = false;

					if (cumpleCond) {
						
						cochesCumpleCond++;
						var dl = document.createElement("dl");
						var tabla = document.createElement("table");
						tabla.className = "tabla container";
						
						// Crea primera hilera de la tabla
						var hileraUno = document.createElement("tr");
					
						//Crear primera celda, que es la de la imagen que ocupara las cinco hileras
						var columUno = document.createElement("td");
						columUno.rowSpan = 5;
						columUno.style.width="15%";
						var imagen = document.createElement("img");
						imagen.className = "imagenCoche";
						if ((coches[n].modelo).localeCompare("V90") == 0) imagen.src = "https://cdn.motor1.com/images/mgl/kjZXB/s3/2021-volvo-v90.jpg";
						else if (coches[n].modelo.localeCompare("C1") == 0) imagen.src = "https://img.motor16.com/modelos/citroen-c1.jpg";
						else if (coches[n].modelo.localeCompare("Corsa") == 0) imagen.src = "https://d1eip2zddc2vdv.cloudfront.net/dphotos/9889171-1592418302.jpeg";
						else if (coches[n].modelo.localeCompare("A4") == 0) imagen.src = "https://cdn.autobild.es/sites/navi.axelspringer.es/public/media/image/2021/01/rivales-audi-a4-2021-2188573.jpg";
						else if (coches[n].modelo.localeCompare("X3") == 0) imagen.src = "https://st.motortrend.com/uploads/sites/5/2019/11/2020-BMW-X3M-Competition-front-three-quarter-in-motion-2.jpg";
						else if (coches[n].modelo.localeCompare("Panda") == 0) imagen.src = "https://www.diariomotor.com/imagenes/2019/11/fiat-panda-2019.jpg";
						else if (coches[n].modelo.localeCompare("Civic") == 0) imagen.src = "https://hips.hearstapps.com/es.h-cdn.co/cades/contenidos/9051/new-civic-hatchback-101.jpg?resize=480:*";
						else if (coches[n].modelo.localeCompare("Kona") == 0) imagen.src = "https://cdn.drivek.it/configurator-covermobile/cars/es/$original$/HYUNDAI/KONA-ELECTRIC/40189_SUV-5-DOORS/hyundai-kona-electric-2021-mobile-cover.jpg";
						else if (coches[n].modelo.localeCompare("Prius") == 0) imagen.src = "https://cdn.motor1.com/images/mgl/rPjvY/s1/guia-de-compra-toyota-prius-hybrid-i-awd.jpg";
						else if (coches[n].modelo.localeCompare("Model X") == 0) imagen.src = "https://forococheselectricos.com/wp-content/uploads/2021/01/Tesla-Model-X.jpg";
						else if (coches[n].modelo.localeCompare("Vitara") == 0) imagen.src = "https://d1eip2zddc2vdv.cloudfront.net/dphotos/9889121-1592417620.jpeg";
						columUno.appendChild(imagen);
						hileraUno.appendChild(columUno);

						//Crear segunda celda, marca y modelo del coche
						var columDos = document.createElement("td");
						var marcaModelo = document.createTextNode(coches[n].marca + " " + coches[n].modelo);
						columDos.appendChild(marcaModelo);
						hileraUno.appendChild(columDos);

						//Crear tercera celda, vacia
						var columTres = document.createElement("td");
						hileraUno.appendChild(columTres);

						//Crear cuarta celda, matricula
						var columCuatro = document.createElement("td");
						var matricula = document.createTextNode("Matricula: " + coches[n].matricula);
						columCuatro.appendChild(matricula);
						hileraUno.appendChild(columCuatro);

						//Crear quinta celda, icono
						var columCinco = document.createElement("td");
						columCinco.rowSpan = "5";
						columCinco.className = "icono";
						var a = document.createElement("a");
						a.id = n;
						a.addEventListener("click", function() {
							pago(this.id);
						});
						var icono = document.createElement("i");
						icono.className = "bi bi-box-arrow-in-right";
						a.appendChild(icono);
						columCinco.appendChild(a);
						hileraUno.appendChild(columCinco);

						// agrega la hilera al final de la tabla (al final del elemento tblbody)
						tabla.appendChild(hileraUno);
						
						//Creo la segunda hilera
						var hileraDos = document.createElement("tr");

						//Creo segunda celda, puertas
						var columDos = document.createElement("td");
						var puertas = document.createTextNode("Puertas: " + coches[n].numeroPuertas);
						columDos.appendChild(puertas);
						hileraDos.appendChild(columDos);

						//Crear tercera celda, maletero
						var columTres = document.createElement("td");
						var maletero = document.createTextNode("Maletero: " + coches[n].capacidadMaletero);
						columTres.appendChild(maletero);
						hileraDos.appendChild(columTres);

						//Crear cuarta celda, cancelacion
						var columCuatro = document.createElement("td");
						var cancelacion;
						if (coches[n].politicaCancelacion == 0) {
							cancelacion = document.createTextNode("Politica de cancelación: gratuita");
						}
						else {
							cancelacion = document.createTextNode("Politica de cancelación: " + coches[n].politicaCancelacion + "%");
						}
						columCuatro.appendChild(cancelacion);
						hileraDos.appendChild(columCuatro);

						//Agrego la hilera a la tabla
						tabla.appendChild(hileraDos);

						//Creo la tercera hilera
						var hileraTres = document.createElement("tr");

						//Creo segunda celda, aire acondicionado
						var columDos = document.createElement("td");
						var aire;
						
						if (coches[n].aireAcondicionado) {
							aire = document.createTextNode("Aire acondicionado: si");
						}
						else {
							aire = document.createTextNode("Aire acondicionado: no");
						}
						columDos.appendChild(aire);
						hileraTres.appendChild(columDos);

						//Crear tercera celda, marchas
						var columTres = document.createElement("td");
						var marchas = document.createTextNode("Cambio de marcha: " + coches[n].cambioMarchas);
						columTres.appendChild(marchas);
						hileraTres.appendChild(columTres);

						//Crear cuarta celda, cancelacion
						var columCuatro = document.createElement("td");
						var combustible = document.createTextNode("Politica de combustible: " + coches[n].politicaCombustible);
						columCuatro.appendChild(combustible);
						hileraTres.appendChild(columCuatro);

						//Agrego la hilera a la tabla
						tabla.appendChild(hileraTres);

						//Creo la cuarta hilera
						var hileraCuatro = document.createElement("tr");

						//Creo segunda celda, plazas
						var columDos = document.createElement("td");
						var plazas = document.createTextNode("Plazas: " + coches[n].plazas);
						columDos.appendChild(plazas);
						hileraCuatro.appendChild(columDos);

						//Crear tercera celda, vacio
						var columTres = document.createElement("td");
						hileraCuatro.appendChild(columTres);

						//Crear cuarta celda, kilometraje
						var columCuatro = document.createElement("td");
						var kilometraje = document.createTextNode("Kilometros: " + coches[n].kilometraje);
						columCuatro.appendChild(kilometraje);
						hileraCuatro.appendChild(columCuatro);

						//Agrego la hilera a la tabla
						tabla.appendChild(hileraCuatro);

						//Creo la quinta hilera
						var hileraCinco = document.createElement("tr");

						//Creo segunda celda, precio
						var columDos = document.createElement("td");
						var precio = document.createTextNode("Pecio/dia: " + coches[n].precioDia);
						columDos.appendChild(precio);
						hileraCinco.appendChild(columDos);

						//Crear tercera celda, vacio
						var columTres = document.createElement("td");
						hileraCinco.appendChild(columTres);

						//Crear cuarta celda, kilometraje
						var columCuatro = document.createElement("td");
						hileraCinco.appendChild(columCuatro);

						//Agrego la hilera a la tabla
						tabla.appendChild(hileraCinco);

						//Agregar la tabla al dl
						dl.appendChild(tabla);
						ul.appendChild(dl);
					}
					
				}

				var foot = document.getElementById("Foot");
				if (cochesCumpleCond == 0) foot.style.marginTop = 700;
				else if (cochesCumpleCond == 1) foot.style.marginTop = 463;
				else if (cochesCumpleCond == 2) foot.style.marginTop = 225;
				else foot.style.marginTop = 0;
			})
		}
		
	})
	/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
	var dropdown = document.getElementsByClassName("dropdown-btn");
	var i;

	for (i = 0; i < dropdown.length; i++) {
		dropdown[i].addEventListener("click", function() {
		this.classList.toggle("active");
		var dropdownContent = this.nextElementSibling;
		if (dropdownContent.style.display === "block") {
		dropdownContent.style.display = "none";
		} else {
		dropdownContent.style.display = "block";
		}
		});
	}

	var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
            content.style.display = "none";
            } else {
            content.style.display = "block";
            }
        });
    }
})