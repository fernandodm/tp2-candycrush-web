<%@ include file="/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar nivel</title>
</head>
<body background="imagenes/fondoObjetivos.jpg">


	<h4>Editar nivel</h4>

	<stripes:form beanclass="action.EditarNivelActionBean" focus="">

		<stripes:errors globalErrorsOnly="true" />

		<table>

			<tr>
				<td>Nombre</td>
				<td><stripes:text name="nivel.nombre" value="${nivel.nombre}" />
				</td>
				<stripes:errors field="nivel.nombre" />
			</tr>
			<tr>
				<td>Dificultad</td>
				<td><stripes:select name="nivel.dificultad">
						<option value="${nivel.dificultad.nombre}">${nivel.dificultad.nombre}</option>
						<option value="FACIL">Facil</option>
						<option value="NORMAL">Normal</option>
						<option value="DIFICIL">Dificil</option>
					</stripes:select></td>
			</tr>
			<tr>
				<td>Tablero: Filas</td>
				<td><stripes:text name="nivel.tablero.alto"
						value="${nivel.tablero.alto}" style="width: 50px" /></td>
				<stripes:errors field="nivel.tablero.alto" />


				<td>Columnas</td>


				<td><stripes:text name="nivel.tablero.ancho"
						value="${nivel.tablero.ancho}" style="width: 50px" /></td>
				<stripes:errors field="nivel.tablero.ancho" />
			</tr>
			<tr>
				<td>Cantidad de movimiento</td>
				<td><stripes:text name="nivel.cantidadMovimientos"
						value="${nivel.cantidadMovimientos}" style="width: 50px" /></td>
				<stripes:errors field="nivel.cantidadMovimientos" />
				<td>Cantidad de puntos</td>

				<td><stripes:text name="nivel.puntajeMinimo"
						value="${nivel.puntajeMinimo}" style="width: 50px" /></td>
				<stripes:errors field="nivel.puntajeMinimo" />
			</tr>

		</table>
	</stripes:form>
	<stripes:form beanclass="action.EditarNivelActionBean" focus="">

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
					<c:forEach items="${nivel.objetivos}" var="objetivo">
						<tr class="alt">
							<td width="50%"><span>${objetivo.descripcion}</span></td>
							<td width="10%"><stripes:link
									beanclass="action.EditarNivelActionBean" event="editarObjetivo">
									<stripes:param name="idn" value="${n}" />
									<image src="imagenes/editar.png" width="20" height="20">
								</stripes:link></td>
							<td width="10%"><stripes:link
									beanclass="action.EditarNivelActionBean" event="eliminar">
									<stripes:param name="idn" value="${n}" />
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

			<stripes:submit name="agregarNivell" value="Terminar" />
			<stripes:submit name="terminarEdicionNivel" value="Cancelar" />
			<stripes:errors field="nivel.objetivos" />
		</div>


	</stripes:form>


</body>
</html>