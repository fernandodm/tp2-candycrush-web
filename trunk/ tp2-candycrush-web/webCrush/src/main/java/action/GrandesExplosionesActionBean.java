package action;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.controller.LifecycleStage;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

@SessionScope
public class GrandesExplosionesActionBean extends BaseActionBean{
	
	private GrandesExplosiones objetivo;
	private List<String> colores;
	private MundoAppModel mundoApp;
	
	public MundoAppModel getMundoApp() {
		return mundoApp;
	}

	public void setMundoApp(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}

	public List<String> getColores() {
		return colores;
	}

	public void setColores(List<String> colores) {
		this.colores = colores;
	}

	public GrandesExplosiones getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(GrandesExplosiones objetivo) {
		this.objetivo = objetivo;
	}
	

	@SuppressWarnings("unchecked")
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void ejecutar(){
		objetivo =  (GrandesExplosiones) this.getContext().getRequest().getSession().getAttribute("obj");
		colores =  (List<String>) this.getContext().getRequest().getSession().getAttribute("colores");
		mundoApp = (MundoAppModel) this.getContext().getRequest().getSession().getAttribute("mundo");
	}

	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("grandesExplosiones.jsp");
	}
	
	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivo(){
		mundoApp.getNivelEnConstruccion().agregarObjetivo(objetivo);
		this.setObjetivo(null);
		return new ForwardResolution(ConfigurarActionBean.class);
	}
}
