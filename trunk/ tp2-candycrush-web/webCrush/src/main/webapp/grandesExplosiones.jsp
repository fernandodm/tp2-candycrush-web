<%@ include file="/taglibs.jsp"%>

<stripes:form beanclass="action.GrandesExplosionesActionBean" focus="">
	<stripes:layout-render name="/layout.jsp"
		title="Nuevo Objetivo: Grandes explosiones">
		<stripes:layout-component name="body">
			<table>

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


			</table>
		</stripes:layout-component>
	</stripes:layout-render>
</stripes:form>