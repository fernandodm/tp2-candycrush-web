package action;


import Tp.CandyCrush.GrandesExplosiones;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;


public class EditarGrandesExplosionesEnEditarNivelActionBean extends EditarObjetivoActionBean {
	private GrandesExplosiones objetivo;
	
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarObjetivo(){
		setObjetivo((GrandesExplosiones) this.getContext().getRequest().getSession().getAttribute("objetivo"));
	}
	
	public GrandesExplosiones getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(GrandesExplosiones objetivo) {
		this.objetivo = objetivo;
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("editarGrandesExplosionesDesdeEditarNivel.jsp");
	}
	
	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivo(){
		this.getContext().getRequest().getSession().setAttribute("objetivo",null);
		return new ForwardResolution(EditarNivelActionBean.class);
	}
	
	@HandlesEvent("cancelarObjetivo")
	public Resolution cancelarObjetivo(){
		
		this.getContext().getRequest().getSession().setAttribute("objetivo",null);
				
		return new ForwardResolution(EditarNivelActionBean.class);
	}

	
}
