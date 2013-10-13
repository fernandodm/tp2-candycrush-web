<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<html>
<body background = "imagenes/fondoHome.jpg">

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
                    	<stripes:text name="mundoApp.nombreUsuario" />
                    </td>
                </tr>
           	</table>
  
           	<stripes:submit name="login" value="Ingresar" />
             	
	</stripes:form>
	
</body>
</html>