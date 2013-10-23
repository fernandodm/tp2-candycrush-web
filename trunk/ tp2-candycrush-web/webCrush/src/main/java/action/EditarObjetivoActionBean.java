package action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public abstract class EditarObjetivoActionBean extends BaseActionBean {
	
	public abstract Resolution agregarObjetivo();
	
	@HandlesEvent("cancelarObjetivo")
	public Resolution cancelarObjetivo(){
		
		this.getContext().getRequest().getSession().setAttribute("objetivo",null);
				
		return new ForwardResolution(ConfigurarActionBean.class);
	}
	
}
