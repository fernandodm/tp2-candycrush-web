package action;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.controller.LifecycleStage;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

@SessionScope
public abstract class ObjetivoActionBean extends BaseActionBean {
	private Objetivo objetivo;
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

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public  void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	
	public abstract void iniciarObjetivo();
	
	@SuppressWarnings("unchecked")
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void ejecutar(){
		setColores((List<String>) this.getContext().getRequest().getSession().getAttribute("colores"));
		setMundoApp((MundoAppModel) this.getContext().getRequest().getSession().getAttribute("mundo"));
	}
	
	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivo(){
		getMundoApp().getNivelEnConstruccion().agregarObjetivo(getObjetivo());
		
		setObjetivo(null);
				
		return new ForwardResolution(ConfigurarActionBean.class);
	}
	
}
