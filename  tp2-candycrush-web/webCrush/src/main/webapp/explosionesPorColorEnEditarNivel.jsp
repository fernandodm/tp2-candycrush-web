<%@ include file="/taglibs.jsp"%>

<stripes:form beanclass="action.ExplosionesPorColorEnEditarNivelActionBean" focus="">
	<stripes:layout-render name="/layoutDesdeNivel.jsp"
		title="Nuevo Objetivo: Explosiones por color">
		<stripes:layout-component name="body">
			<table>
				<tr>
					<td>Cantidad</td>

					<td><stripes:text name="objetivo.cantidad"></stripes:text></td>
						<stripes:errors field="objetivo.cantidad" />
				</tr>
			</table>

		</stripes:layout-component>
	</stripes:layout-render>
</stripes:form>