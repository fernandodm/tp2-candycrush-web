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
          
       <h4>Crear nivel:</h4>
          
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
             	<stripes:text name="nivelEnConstruccion.tablero.alto" />
           	</td>
           	
           	 <td>Columnas</td>
           	 
           	 <td>
             	<stripes:text name="nivelEnConstruccion.tablero.ancho" />
           	 </td>
           	 
          </tr>
          
          <tr>
          
          	<td> Cantidad de movimiento </td>
          	<td> 
          		<stripes:text name="nivelEnConstruccion.cantidadMovimientos" />
          	</td>
          
          	<td> Cantidad de puntos </td>
          	<td> 
          		<stripes:text name="nivelEnConstruccion.puntajeMinimo" />
          	</td>
          
          </tr>
          
      </table>
      
       <div>
            	<table id="main" >
            	<br></br>
            		<tr>
                		<th width="40%">Objetivos</th>
                		<th width="8%">Editar</th>
                		<th width="8%">Borrar</th>
       				</tr>
       				
       				<td width="40%"> </td>
       				
       				<!--Boton no terminado, falta el editar y ver lo comentado />-->
       				<td width="10%">
                	    	<stripes:link beanclass="action.ConfigurarActionBean" event="editar" style="background-color:none;padding:0px;text-decoration: none;">
                	    		<!--<stripes:param name="idSocioSeleccionado" value="${socio.id}" />-->
            	            	<image src="imagenes/editar.png"/ width = "20" height = "20">
            	            </stripes:link>
        	        </td>
        	        
        	        <!--Boton no terminado, falta el eliminar y ver lo comentado />-->
        	        <td width="10%">
                	    	<stripes:link beanclass="action.ConfigurarActionBean" event="eliminar" style="background-color:none;padding:0px;text-decoration: none;">
                	    		<!--<stripes:param name="idSocioSeleccionado" value="${socio.id}" />-->
            	            	<image src="imagenes/eliminar1.PNG" width = "20" height = "20">
            	            </stripes:link>
        	        </td>
       				
       				</table>
       </div>
      
      
	</stripes:form>
</body>
</html>