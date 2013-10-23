package action;

import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;


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

	public ForwardResolution validarExplosionesPorColor(){
			ValidationErrors errors = new ValidationErrors();
			if(this.getObjetivo().getColor() == null)
				errors.add("objetivo.color", new SimpleError("Seleccione un color"));
			if(((ExplosionesPorColor) this.getObjetivo()).getCantidad() == null || ((ExplosionesPorColor) this.getObjetivo()).getCantidad() <= 0 )
				errors.add("objetivo.cantidad", new SimpleError("La cantidad del objetivo no puede ser 0 ni nula"));
	        
	        this.getContext().setValidationErrors(errors);
	        return new ForwardResolution("/explosionesPorColor.jsp");	
	}
	
	public ForwardResolution validarGrandesExplosiones(){
		ValidationErrors errors = new ValidationErrors();
		if(this.getObjetivo().getColor() == null)
			errors.add("objetivo.color", new SimpleError("Seleccione un color"));
		
        this.getContext().setValidationErrors(errors);
        return new ForwardResolution("/grandesExplosiones.jsp");	
    }
	
	@HandlesEvent("agregarObjetivo")
	public Resolution agregarObjetivoDesdeEditarNivel(){
		if(!this.getMundoApp().getObjetivo().puedeAgregarObjetivo()){
			if(this.getMundoApp().getObjetivo().esExplosionesPorColor())
				return this.validarExplosionesPorColor();
			else
				return this.validarGrandesExplosiones();
		}
		else{
			getMundoApp().getNivelEnConstruccion().agregarObjetivo(getObjetivo());
			return new ForwardResolution(ConfigurarActionBean.class);
		}
	}
	
	@HandlesEvent("cancelarObjetivo")
	public Resolution cancelarObjetivoDesdeEditarNivel(){
		
		setObjetivo(null);
				
		return new ForwardResolution(ConfigurarActionBean.class);
	}
	
	
}
