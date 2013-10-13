package action;

import appModel.MundoAppModel;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;


@SessionScope
public class HomeActionBean extends BaseActionBean{
	
	private MundoAppModel mundoApp = new MundoAppModel();
	
	public MundoAppModel getMundoApp() {
		return mundoApp;
	}


	public void setMundo(MundoAppModel mundo) {
		this.mundoApp = mundo;
	}


//	@DefaultHandler
//	public Resolution view() {
//		return new ForwardResolution("home.jsp");
//	}
	
	@HandlesEvent("login")
	public Resolution login(){
		
		this.getContext().getRequest().getSession().setAttribute("mundo", mundoApp);
		
		return new ForwardResolution(ConfigurarActionBean.class);
		
	}	
}
