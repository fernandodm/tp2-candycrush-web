package action;

import Tp.CandyCrush.ExplosionesPorColor;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.controller.LifecycleStage;

@SessionScope
public class ExplosionesPorColorActionBean extends ObjetivoActionBean{
	
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarObjetivo(){
		super.setObjetivo(new ExplosionesPorColor());
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("explosionesPorColor.jsp");
	}

}
