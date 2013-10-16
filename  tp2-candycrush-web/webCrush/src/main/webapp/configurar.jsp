 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    <%-- Stripes TLD --%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>

<%-- JSTL TLDs --%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
    

<html>
<head>
	
	<title>Configurar niveles</title>
	 
</head>
<body background = "imagenes/fondoConfig.jpg">

<div style ="float: left ; border-right: 1px solid #353232">
	<h4>Bienvenido/a ${mundo.nombreUsuario} ya podes configurar tus niveles!</h4>
		
	<stripes:form beanclass="action.ConfigurarActionBean" focus="">
	
	<h4>Crear nivel:</h4>
	 <table>
          
          <tr>
          	 <td> Nombre</td>
             <td>
             <stripes:text name="nivelEnConstruccion.nombre" />
           	 </td>
          </tr>
          
          <tr>
          	<td> Dificultad</td>
          	<td>
          		<select>
  					<option value="dificultad.FACIL">Facil</option>
  					<option value="dificultad.NORMAL">Normal</option>
  					<option value="dificultad.DIFICIL">Dificil</option>
				</select>
         	</td>      	
          </tr>
          
          <tr>
          	<td> Tablero: Filas </td>
          	
          	<td>
             	<stripes:text name="nivelEnConstruccion.tablero.alto" style ="width: 50px" />
           	</td>
           	
           	 <td>Columnas</td>
           	 
           	 <td>
             	<stripes:text name="nivelEnConstruccion.tablero.ancho" style ="width: 50px"/>
           	 </td>
           	 
          </tr>
          
          <tr>
          
          	<td> Cantidad de movimiento </td>
          	<td> 
          		<stripes:text name="nivelEnConstruccion.cantidadMovimientos" style ="width: 50px"/>
          	</td>
          
          	<td> Cantidad de puntos </td>
          	<td> 
          	
          		<stripes:text name="nivelEnConstruccion.puntajeMinimo" style ="width: 50px"/>
          	</td>
          
          </tr>
          
      </table>
      	
       <div>
            	<table style="margin-top: 30px">
            	
            		<tr>
                		<th width="50%">Objetivos</th>
                		<th width="8%">Editar</th>
                		<th width="8%">Borrar</th>
       				</tr>
       			    <c:set var="n" value="${0}"/>
       				<c:forEach items="${mundo.nivelEnConstruccion.objetivos}" var="objetivo" >
       				
                   	<tr>
                    	<td width="50%">
                    	    <span>${objetivo.descripcion}</span> 
                	    </td> 
            	               				       				
       				<!--Boton no terminado, falta el editar/>-->
       				<td width="10%">
                	    	<stripes:link beanclass="action.ConfigurarActionBean" event="editar">
                	    		<stripes:param name="id" value="${n}"/>
            	            	<image src="imagenes/editar.png" width = "20" height = "20">
            	            </stripes:link>
        	        </td>
        	        
        	        <td width="10%">
                	    	<stripes:link beanclass="action.ConfigurarActionBean" event="eliminar" >
                	    		<stripes:param name= "id" value="${n}" />
            	            	<image src="imagenes/eliminar1.PNG" width = "20" height = "20">
            	            </stripes:link>
        	        </td>
        	        </tr>
        	        <c:set var="n" value="${n+1}"/>
       				</c:forEach>
       				</table>
       				<stripes:submit name="agregarExpPorColor" value="Nuevo Explosiones por color" />
       				<stripes:submit name="agregarGrandesExplosiones" value="Nuevo Grandes explosiones" />
       </div>
      
	</stripes:form>
</div>

<di>
	 <!--ACA VA LA PARTE DE LOS NIVELES CREADOS-->
	<h4>Niveles creados</h4>

</div>

</body>
</html>