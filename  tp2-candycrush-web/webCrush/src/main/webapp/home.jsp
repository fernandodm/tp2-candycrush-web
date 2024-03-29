<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>

<html>
<head>
<title>Ingresar a Candy Crush</title>
<link rel="stylesheet" type="text/css" href="css/cs_basic.css">
</head>
<body background="imagenes/fondoHome.jpg">

	<img src="imagenes/candycrush.jpg">
	<i><h2>Bienvenidos al Candy Crush</h2></i>
	<i>El gran juego para organizar tus niveles</i>
	<br></br>

	<stripes:form beanclass="action.HomeActionBean"
		focus="mundoApp.nombreUsuario">
		<stripes:errors globalErrorsOnly="true" />
		<table width="30%">
			<tr>
				<stripes:errors field="mundoApp.nombreUsuario" />
				<td align="right">Ingrese su nombre:</td>
				<td align="left"><stripes:text name="mundoApp.nombreUsuario" />
				</td>
			</tr>
		</table>

		<stripes:submit name="login" value="Ingresar" class="colorBoton" />

	</stripes:form>

</body>
</html>