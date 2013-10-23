package action;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import Tp.CandyCrush.ExplosionesPorColor;

public class EditarExplosionesPorColorActionBean extends EditarObjetivoActionBean {
	private ExplosionesPorColor objetivo;
	
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarObjetivo(){
		setObjetivo((ExplosionesPorColor) this.getContext().getRequest().getSession().getAttribute("objetivo"));
	}
	
	public ExplosionesPorColor getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(ExplosionesPorColor objetivo) {
		this.objetivo = objetivo;
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("editarExplosionesPorColor.jsp");
	}

	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivo(){
		this.getContext().getRequest().getSession().setAttribute("objetivo",null);
		return new ForwardResolution(ConfigurarActionBean.class);
	}

	@HandlesEvent("agregarObjetivoDesdeEditarNivel")
	public Resolution agregarObjetivoDesdeEditarNivel(){
		this.getContext().getRequest().getSession().setAttribute("objetivo",null);
		return new ForwardResolution(EditarNivelActionBean.class);
	}
	
}
