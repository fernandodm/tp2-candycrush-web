
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<title>Configurar niveles</title>
<link rel="stylesheet" type="text/css" href="css/cs_basic.css">
</head>
<body background="imagenes/fondoConfig.jpg">
	<div style="float: left; border-right: 1px solid #353232; width: 50%">
		<i><h4 style="color: #00557F">Bienvenido/a
				${mundo.nombreUsuario} ya podes configurar tus niveles!</h4></i>
		<stripes:form beanclass="action.ConfigurarActionBean" focus="">
			<stripes:errors globalErrorsOnly="true" />
			<h4>Crear nivel:</h4>
			<table>
				<tr>
					<td>Nombre</td>
					<td><stripes:text name="mundo.nivelEnConstruccion.nombre"
							style="-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" />
					</td>
					<stripes:errors field="mundo.nivelEnConstruccion.nombre" />
				</tr>
				<tr>
					<td>Dificultad</td>

					<stripes:errors field="dificultad" />
					<td><stripes:select name="dificultad" style="border: 0 none;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;">

							<option value="${mundo.nivelEnConstruccion.dificultad.nombre}">${mundo.nivelEnConstruccion.dificultad.nombre}</option>
							<option value="FACIL">Facil</option>
							<option value="NORMAL">Normal</option>
							<option value="DIFICIL">Dificil</option>
						</stripes:select></td>
				</tr>
				<tr>
					<td>Tablero: Filas</td>
					<td><stripes:text
							name="mundo.nivelEnConstruccion.tablero.alto"
							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" />
					</td>
					<stripes:errors field="mundo.nivelEnConstruccion.tablero.alto" />


					<td>Columnas</td>



					<td><stripes:text
							name="mundo.nivelEnConstruccion.tablero.ancho"

							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" /></td>

					<stripes:errors field="mundo.nivelEnConstruccion.tablero.ancho" />
				</tr>
				<tr>
					<td>Cantidad de movimiento</td>
					<td><stripes:text
							name="mundo.nivelEnConstruccion.cantidadMovimientos"
							style="width: 50px;-webkit-border-radius: 20px;padding: 2px;font: bold 12px Arial,Helvetica,Sans-serif;" /></td>
					<stripes:errors
						field="mundo.nivelEnConstruccion.cantidadMovimientos" />
					<td>Cantidad de puntos</td>

					<td><stripes:text
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
								<td width="50%"><span>${objetivo.descripcion}</span></td>
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
<<<<<<< .mine

			<stripes:submit name="agregarNivel" value="Crear nivel"  class="colorBoton" />
=======
			<stripes:submit name="agregarNivel" value="Crear nivel"
				class="colorBoton" />
>>>>>>> .r40
			<stripes:errors field="mundo.nivelEnConstruccion.objetivosDelNivel" />

		</stripes:form>
	</div>
	<div>

		<h4 style="color: #00557F">Niveles creados</h4>
		<stripes:form beanclass="action.ConfigurarActionBean">
			<div>
				<table style="margin-top: 30px">
					<tr>
						<th width="35%">Niveles</th>
						<th width="8%">Editar</th>
						<th width="8%">Borrar</th>
					</tr>
					<c:set var="m" value="${0}" />
					<c:forEach items="${mundo.niveles}" var="nivel">
						<tr>
							<td width="35%"><span>${nivel.nombre}</span></td>
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
				</table>
			</div>
		</stripes:form>
	</div>
</body>
</html>