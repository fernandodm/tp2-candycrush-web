package action;

import Tp.CandyCrush.GrandesExplosiones;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;



public class GrandesExplosionesEnEditarNivelActionBean extends ObjetivoActionBean{

	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarObjetivo(){
		setObjetivo(new GrandesExplosiones());
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("grandesExplosionesEnEditarNivel.jsp");
	}
	
}