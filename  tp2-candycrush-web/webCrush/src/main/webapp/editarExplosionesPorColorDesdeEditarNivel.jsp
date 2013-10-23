<%@ include file="/taglibs.jsp"%>

<stripes:form beanclass="action.EditarExplosionesPorColorEnEditarNivelActionBean" focus="">
	<stripes:layout-render name="/layout.jsp"
		title="Editar Objetivo: Explosiones por color">
		<stripes:layout-component name="body">
			<table>
				<tr>
					<td>Cantidad</td>

					<td><stripes:text name="objetivo.cantidad" value="${objetivo.cantidad}"></stripes:text></td>

				</tr>
			</table>

		</stripes:layout-component>
	</stripes:layout-render>
</stripes:form>