
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Stripes TLD --%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="sdyn"
	uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<%-- JSTL TLDs --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Objetivo: Grandes explosiones</title>
</head>
<body>


	<table>
		<stripes:form beanclass="action.GrandesExplosionesActionBean" focus="">


			<tr>
				<td>Color</td>

				<td><stripes:select name="objetivo.color">
						<c:set var="n" value="${0}" />
						<c:forEach items="${colores}">
							<option value="${colores.get(n)}">${colores.get(n)}</option>
							<c:set var="n" value="${n+1}" />
						</c:forEach>
					</stripes:select></td>
			</tr>

			<tr>
				<td>Cantidad</td>

				<td><stripes:select name="objetivo.cantidadGrandesExplosiones">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>

					</stripes:select></td>

			</tr>
			<tr>
				<td>
				<stripes:submit name="agregarObjetivo" value="Agregar" />
				</td>
			</tr>

		</stripes:form>
	</table>


</body>
</html>