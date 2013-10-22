package action;

import appModel.MundoAppModel;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;


@SessionScope
public class HomeActionBean extends BaseActionBean{
	
	private MundoAppModel mundoApp = new MundoAppModel();

	public void setMundoApp(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}


	public MundoAppModel getMundoApp() {
		return mundoApp;
	}


	public void setMundo(MundoAppModel mundo) {
		this.mundoApp = mundo;
	}
	
	@HandlesEvent("login")
	public Resolution login(){
		
		if(this.mundoApp.getNombreUsuario()!= null && !this.mundoApp.getNombreUsuario().contains(" ")){

		this.getContext().getRequest().getSession().setAttribute("mundo", mundoApp);
		}
		else{
			ValidationErrors errors = new ValidationErrors();
            errors.add("mundoApp.nombreUsuario", new SimpleError("Ingrese un nombre de usuario válido sin dejar espacios"));
            
            this.getContext().setValidationErrors(errors);
            return new ForwardResolution("/home.jsp");
		}
		return new ForwardResolution(ConfigurarActionBean.class);
	}	
}
