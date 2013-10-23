package action;

import Tp.CandyCrush.GrandesExplosiones;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;



public class GrandesExplosionesEnEditarNivelActionBean extends ObjetivoActionBean{

	private Nivel nivel;
	
	
	
	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarObjetivo(){
		setObjetivo(new GrandesExplosiones());
		setNivel((Nivel) this.getContext().getRequest().getSession().getAttribute("nivel"));
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("grandesExplosionesEnEditarNivel.jsp");
	}
	
	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivo(){
		if(!this.getObjetivo().puedeAgregarObjetivo()){
			if(this.getObjetivo().esExplosionesPorColor())
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