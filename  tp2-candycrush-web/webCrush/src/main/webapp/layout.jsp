<%@ include file="taglibs.jsp"%>

<stripes:layout-definition>
	<html>
<head>
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="css/cs_basic.css">
</head>
<body background="imagenes/fondoObjetivos.jpg">


	<table>

		<tr>
			<td>Color</td>
				<stripes:errors field="objetivo.color" />
			<td><stripes:select name="objetivo.color">
					<option value="${objetivo.color}">${objetivo.color}</option>
					<c:forEach items="${colores}" var="color">
						<option value="${color}">${color}</option>

					</c:forEach>
				</stripes:select></td>
		</tr>
		<stripes:layout-component name="body">
		</stripes:layout-component>
		<tr>
			<td><stripes:submit name="agregarObjetivo" value="Agregar" class="colorBoton"/></td>
			<td><stripes:submit name="cancelarObjetivo" value="Cancelar" class="colorBoton" /></td>
		</tr>

	</table>

</body>
	</html>
</stripes:layout-definition>
