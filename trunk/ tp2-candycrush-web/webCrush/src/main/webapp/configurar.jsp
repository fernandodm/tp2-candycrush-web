 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<html>
<head>
	
	<title>Configurar niveles</title>
	 
</head>
<body background = "imagenes/fondoConfig.jpg">
	<h4>Bienvenido/a ${mundo.nombreUsuario} ya podes configurar tus niveles!</h4>
	
	<stripes:form beanclass="action.ConfigurarActionBean" focus="">
	 <table>
          
       <td align="right">Crear nivel:</td>
          
          <tr>
          	 <td align="right"> Nombre</td>
             <td align = "left">
             <stripes:text name="nivelEnConstruccion.nombre" />
           	 </td>
          </tr>
          
          <tr>
          	<td align = "right"> Dificultad</td>
          	<td align = "left">
          		<select>
  					<option value="dificultad.FACIL">Facil</option>
  					<option value="dificultad.NORMAL">Normal</option>
  					<option value="dificultad.DIFICIL">Dificil</option>
				</select>
         	</td>
          </tr>
          
      </table>
          
 	<table width = "70%">
     	<tr>
           	 <th align="right">Niveles creados:</th>
         </tr>
     </table>
	</stripes:form>
</body>
</html>