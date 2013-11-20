
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<title>Configurar niveles</title>
<link rel="stylesheet" type="text/css" href="css/cs_basic.css">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.css">

<style>
  
  .ui-resizable-helper { border: 2px dotted #00F; }
  
  .ui-dialog .ui-widget-header{background: red; color: black; font-family: arial; }
   
</style>
  <script>
  
  $(function() {
	    $( document ).tooltip();
	  });
  
  $(function() {
    $( "#resizable" ).resizable({
      helper: "ui-resizable-helper"
    });
  });

  //Ventanita dialog
  $(function() {
	  
   	$( "#dialog" ).dialog({
   		height:300,
   		width: 500,
   		autoOpen: false,
   		show: {
       	effect: "bounce",
       	duration: 1000
   		},
   		hide: {
      	effect: "explode",
       	duration: 1000
   		},
   		
   		buttons:{
   			"Cancelar": function(){
   				$(this).dialog("close");
   			},
   		}
   	});   	
   	
   	 $( "#opener" ).click(function() {
        	
    		$("#errorNombre").empty();
    		$("#errorDificultad").empty();
    		$("#errorFilas").empty();
    		$("#errorColumnas").empty();
    		$("#errorMovimientos").empty();
    		$("#errorPuntos").empty();
    		$("#errorObjetivo").empty();
    	
    	 	var ret = true;
    		var nombre = $("#nombre").val();
    		var dificultad = $("#dificultad").val();
    		var fila = $("#filas").val();
    		var columna = $("#columnas").val();
    		var movimiento = $("#movimientos").val();
    		var puntos = $("#puntos").val();
   			var objetivo = $("#objetivo").val();
    		    		
    		if(nombre == ""){
     			$("#errorNombre").html('<h5>*ingrese el nombre del nivel</h5>');
     			ret = false;
    		}
    		if(dificultad == []){
     	  		$("#errorDificultad").html('<h5>*ingrese una dificultad</h5>');
    			ret = false;
    		}
    		if(fila == ""){
     			$("#errorFilas").html('<h5>*ingrese la cantidad de filas</h5>');
     			ret = false;
    		}
    		if(columna == ""){
     	  		$("#errorColumnas").html('<h5>>*ingrese la cantidad de columnas</h5>');
    			ret = false;
    		}
    		if(movimiento == ""){
     			$("#errorMovimientos").html('<h5>*ingrese movimientos mayor a 1</h5>');
     			ret = false;
    		}
    		if(puntos == ""){
     	  		$("#errorPuntos").html('<h5>*ingrese ingrese puntos mayor a 1</h5>');
    			ret = false;
    		}
    		if(objetivo == null){
     			$("#errorObjetivo").html('<h5>*ingrese al menos un objetivo</h5>');
     			ret = false;
    		}
    		
    		//si ret es false abre la el dialog
    		if(!ret){
    			 $( "#dialog" ).dialog( "open" );
    		}
    		
    		return ret;
     });
  });
  ///////////////////////////////////////////////////////	
	
	//Cambiar de posiocion los niveles buscados
	$(function() {
  	  $( "#sortable" ).sortable();
  	  $( "#sortable" ).disableSelection();
  	});
	////////////////////////////////////////////

	//Mover Ventanita
	$(function() {
  	  $( "#draggable" ).draggable();
 	 });
	/////////////////////////////////
	
	
	
	$(function() {
    function runEffect() {
    	$( "#effect" ).effect( "bounce", options,500 );
    };
 
  
    $( "#button" ).click(function() {
      runEffect();
      return true;
    });
  });
	
  </script>

</head>

<body background="imagenes/fondoConfig.jpg">
	<div style="float: right; width: 50%" align="center">

		<h4 style="color: #00557F">Niveles creados</h4>
		<stripes:form beanclass="action.ConfigurarActionBean">

			<div id="resizable" class="datagrid" style="margin-top: 30px">
				<table>
					<thead>
						<tr class="alt">
							<th width="40%">Niveles</th>
							<th width="8%">Editar</th>
							<th width="8%">Borrar</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="m" value="${0}" />
						<c:forEach items="${mundo.mundo.niveles}" var="nivel">
							<tr>
								<td width="40%"><span>${nivel.nombre}</span></td>
								<!--Boton no terminado, falta el editar/>-->
								<td width="10%"><stripes:link
										beanclass="action.ConfigurarActionBean" event="editarNivel">
										<stripes:param name="id" value="${m}" />
										<image src="imagenes/editar.png" width="20" height="20">
									</stripes:link></td>
								<td width="10%"><stripes:link
										beanclass="action.ConfigurarActionBean" event="eliminarNivel">
										<stripes:param name="id" value="${m}" />
										<image src="imagenes/eliminar1.PNG" width="20" height="20">
									</stripes:link></td>
							</tr>
							<c:set var="m" value="${m+1}" />
						</c:forEach>
					<tbody>
				</table>
			</div>

			<!--TABLA BUSCAR  -->
			
			<div id="draggable" class="datagrid" style="margin-top: 30px">

				<stripes:submit name="filtrarPorNombre" value="Filtrar"
					class="colorBoton" />
				<stripes:text name="filtro"
					style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" />
				<table>
					<thead>
						<tr class="alt">
							<th width="40%">Niveles</th>
						</tr>
					</thead>
					<tbody id ="sortable">
						<c:set var="x" value="${0}" />
						<c:forEach items="${mundo.niveles}" var="nivelf">
							<tr>
								<td width="40%" class="ui-state-default"><span>${nivelf.nombre}</span></td>

							</tr>
							<c:set var="x" value="${x+1}" />
						</c:forEach>
					<tbody>
				</table>
			</div>

		<!-- CONFIGURACION -->
		</stripes:form>
	</div>
	<div style="border-right: 1px solid #353232; width: 50%">
		<i><h4 style="color: #00557F">Bienvenido/a
				${mundo.nombreUsuario} ya podes configurar tus niveles!</h4></i>
		<stripes:form beanclass="action.ConfigurarActionBean" focus="">
			<stripes:errors globalErrorsOnly="true" />
			<h4>Crear nivel:</h4>
			<table>
				<tr>
					<td>Nombre</td>
					<td><stripes:text name="mundo.nivelEnConstruccion.nombre" id="nombre"
							style="-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" />
					</td>
					<stripes:errors field="mundo.nivelEnConstruccion.nombre" />
				</tr>
				<tr>
					<td>Dificultad</td>

					<stripes:errors field="dificultad" />
					<td><stripes:select name="dificultad" id="dificultad"
							style="border: 0 none;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;">

							<option value="${mundo.nivelEnConstruccion.dificultad.nombre}">${mundo.nivelEnConstruccion.dificultad.nombre}</option>
							<option value="FACIL">Facil</option>
							<option value="NORMAL">Normal</option>
							<option value="DIFICIL">Dificil</option>
						</stripes:select></td>
				</tr>
				<tr>
					<td>Tablero: Filas</td>
					<td><stripes:text title="la cantidad de filas debe ser mayor a 3." id="filas"
							name="mundo.nivelEnConstruccion.tablero.alto"
							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" />
					</td>
					<stripes:errors field="mundo.nivelEnConstruccion.tablero.alto" />

					<td>Columnas</td>


					<td><stripes:text title="la cantidad de columnas debe ser mayor a 3." id="columnas"
							name="mundo.nivelEnConstruccion.tablero.ancho"
							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" /></td>

					<stripes:errors field="mundo.nivelEnConstruccion.tablero.ancho" />
				</tr>
				<tr>
					<td>Cantidad de movimiento</td> 
					<td><stripes:text title="la cantidad de movimientos debe ser mayor a 0." id="movimientos"
							name="mundo.nivelEnConstruccion.cantidadMovimientos"
							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" /></td>
					<stripes:errors
						field="mundo.nivelEnConstruccion.cantidadMovimientos" />
					<td>Cantidad de puntos</td>

					<td><stripes:text title="la cantidad de puntos debe ser mayor a 0." id="puntos"
							name="mundo.nivelEnConstruccion.puntajeMinimo"
							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" /></td>

					<stripes:errors field="mundo.nivelEnConstruccion.puntajeMinimo" />
				</tr>
			</table>
			<div class="datagrid" style="margin-top: 30px">
				<table>
					<thead>
						<tr>
							<th width="50%">Objetivos</th>
							<th width="8%">Editar</th>
							<th width="8%">Borrar</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="n" value="${0}" />
						<c:forEach items="${mundo.nivelEnConstruccion.objetivos}"
							var="objetivo">
							<tr class="alt">
								<td width="50%"><span id="objetivo">${objetivo.descripcion}</span></td>
								<td width="10%"><stripes:link
										beanclass="action.ConfigurarActionBean" event="editarObjetivo">
										<stripes:param name="id" value="${n}" />
										<image src="imagenes/editar.png" width="20" height="20">
									</stripes:link></td>
								<td width="10%"><stripes:link
										beanclass="action.ConfigurarActionBean" event="eliminar">
										<stripes:param name="id" value="${n}" />
										<image src="imagenes/eliminar1.PNG" width="20" height="20">
									</stripes:link></td>
							</tr>
							<c:set var="n" value="${n+1}" />
						</c:forEach>
					</tbody>
				</table>
				<table>
					<tfoot>
						<tr>
							<td colspan="4"><div id="paging">
									<stripes:submit name="agregarExpPorColor"
										value="Nuevo Explosiones por color" class="colorBoton" />
									<stripes:submit name="agregarGrandesExplosiones"
										value="Nuevo Grandes explosiones" class="colorBoton" />
								</div>
						</tr>
					</tfoot>
				</table>
			</div>
			<stripes:submit name="agregarNivel" value="Crear nivel"
				class="colorBoton" id="opener"/>
			<stripes:errors field="mundo.nivelEnConstruccion.objetivosDelNivel" />
			
		</stripes:form>
	</div>
	
<div id="dialog" title="Error">
 	<h5 id="errorNombre"></h5>
 	<h5 id="errorDificultad"></h5>
 	<h5 id="errorFilas"></h5>
 	<h5 id="errorColumnas"></h5>
 	<h5 id="errorMovimientos"></h5>
 	<h5 id="errorPuntos"></h5>
 	<h5 id="errorObjetivo"></h5>

</div>

</body>
</html>