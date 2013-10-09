<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
 

<html>
<body>

	<title> Ingreasar a Candy Crush </title>
	<img src="imagenes/candycrush.jpg" >
	<h2> Bienvenidos al Candy Crush </h2>
	
	<stripes:form beanclass="action.HomeActionBean" focus="">
            <table width="30%">
                <tr>
                    <td align="right">Ingrese su nombre:</td>
                    <td align="left">
                    	<stripes:text name="nombreUsuario" />
                    </td>
                </tr>
           	</table>
	</stripes:form>
</body>
</html>