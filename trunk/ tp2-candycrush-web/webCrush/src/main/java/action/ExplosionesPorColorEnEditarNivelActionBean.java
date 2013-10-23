package action;

import Tp.CandyCrush.ExplosionesPorColor;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;


public class ExplosionesPorColorEnEditarNivelActionBean extends ObjetivoActionBean{
	
	private Nivel nivel;
	
	
	
	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarObjetivo(){
		super.setObjetivo(new ExplosionesPorColor());
		setNivel((Nivel) this.getContext().getRequest().getSession().getAttribute("nivel"));
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("explosionesPorColorEnEditarNivel.jsp");
	}
	
	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivo(){
		if(!this.getNivel().getObjetivo().puedeAgregarObjetivo()){
			if(this.getNivel().getObjetivo().esExplosionesPorColor())
				return this.validarExplosionesPorColor();
			else
				return this.validarGrandesExplosiones();
		}
		else{
			this.getNivel().agregarObjetivo(getObjetivo());
			return new ForwardResolution(EditarNivelActionBean.class);
		}
	}

}