<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<html>
<body background = "http://www.fondosok.com/walls/1734/fondo-azul-y-flores_1024x768.jpg">

	<title> Ingreasar a Candy Crush </title>
	<img src="imagenes/candycrush.jpg" >
	<i><h2> Bienvenidos al Candy Crush </h2></i>
	 <i>El gran juego para organizar tus niveles</i>
	<br></br>
	
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
	
	<stripes:link beanclass="action.ConfigurarActionBean" >
	   	<image src="imagenes/boton_ingresar.png" alt="Configurar" />
	</stripes:link>
	
</body>
</html>