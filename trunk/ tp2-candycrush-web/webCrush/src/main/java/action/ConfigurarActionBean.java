package action;

import appModel.MundoAppModel;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;

@SessionScope
public class ConfigurarActionBean extends BaseActionBean {
	
	private MundoAppModel mundo;
	
	public MundoAppModel getMundo() {
		return mundo;
	}

	public void setMundo(MundoAppModel mundo) {
		this.mundo = mundo;
	}

	
	@Before
	public void ejecutar(){
		mundo = (MundoAppModel) this.getContext().getRequest().getSession().getAttribute("mundo");

		
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("configurar.jsp");
	}
}
