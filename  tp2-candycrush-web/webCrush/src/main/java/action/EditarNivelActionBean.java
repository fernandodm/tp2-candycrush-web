package action;

import java.util.ArrayList;
import java.util.List;

import appModel.MundoAppModel;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

public class EditarNivelActionBean extends BaseActionBean {

	private Nivel nivel;
	private Integer idn;
	private MundoAppModel mundoApp;

	public MundoAppModel getMundoApp() {
		return mundoApp;
	}

	public void setMundoApp(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}

	public Integer getIdn() {
		return idn;
	}

	public void setIdn(Integer idn) {
		this.idn = idn;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarEdicion(){
		this.setMundoApp((MundoAppModel) this.getContext().getRequest().getSession().getAttribute("mundoApp"));
		this.setNivel((Nivel) this.getContext().getRequest().getSession().getAttribute("nivel"));
		this.setIdn((Integer) this.getContext().getRequest().getSession().getAttribute("idn"));
	}

	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("editarNivel.jsp");
	}
	
	public boolean validarNombreNivel(ValidationErrors errors){
		if(this.getNivel().getNombre() == null){
			errors.add("nivel.nombre", new SimpleError("Ingrese un nombre para el nivel"));
			return true;
		}
		return false;
	}
	
	public boolean validarLaDificultad(ValidationErrors errors){
		if(this.getNivel().getDificultad() == null){
			errors.add("dificultad", new SimpleError("Ingrese una dificultad"));
			return true;
		}
		return false;
	}
	
	public boolean validarAlto(ValidationErrors errors){
		if(this.getNivel().getTablero().getAlto() == null ||
		(int)this.getNivel().getTablero().getAlto() < 3){
			errors.add("nivel.tablero.alto", new SimpleError("Filas mínimas = 3"));
			return true;
		}
		return false;
	}
	
	public boolean validarAncho(ValidationErrors errors){
		if(this.getNivel().getTablero().getAncho() == null ||
		this.getNivel().getTablero().getAncho() < 3){
			errors.add("nivel.tablero.ancho", new SimpleError("Columnas mínimas = 3"));
			return true;
		}
		return false;
	}
	
	public boolean validarCantidadMovimientos(ValidationErrors errors){
		if(this.getNivel().getCantidadMovimientos() == null ||
		        (int)this.getNivel().getCantidadMovimientos() < 1){
		        	errors.add("nivel.cantidadMovimientos", new SimpleError("Movimientos mínimos = 1"));
		        	return true;
		}
		return false;
	}
	
	public boolean validarPuntajeMinimo(ValidationErrors errors){
		if(this.getNivel().getPuntajeMinimo() == null){
			errors.add("nivel.puntajeMinimo", new SimpleError("Ingrese un puntaje mínimo"));
			return true;
		}
		return false;
	}
	
	public boolean validarObjetivosDelNivel(ValidationErrors errors){
		if(this.getNivel().getObjetivos().size() == 0){
			errors.add("nivel.objetivos", new SimpleError("Debe agregar por lo menos un objetivo"));
	        return  true;
		}
		return false;
	}
	

	@HandlesEvent("agregarNivell")
	public Resolution agregarNivel(){
			
			boolean val = false;

			ValidationErrors errors = new ValidationErrors();
			
			val = this.validarNombreNivel(errors) || val;
			val = this.validarLaDificultad(errors) || val;
			val = this.validarAlto(errors) || val;
			val = this.validarAncho(errors) || val;
			val = this.validarCantidadMovimientos(errors) || val;		
			val = this.validarPuntajeMinimo(errors) || val;
			val = this.validarObjetivosDelNivel(errors) || val; 
		    		
			if(val){
		        this.getContext().setValidationErrors(errors);
		        return new ForwardResolution(EditarNivelActionBean.class);
			} else {
			
				this.getMundoApp().getNiveles().get(this.getIdn()).setNombre(this.getNivel().getNombre());
				
				return new ForwardResolution(ConfigurarActionBean.class);
			}
			
		}

	@HandlesEvent("terminarEdicionNivel")
	public Resolution terminarEdicionNivel(){
		this.getContext().getRequest().getSession().setAttribute("nivel",null);
		return new ForwardResolution(ConfigurarActionBean.class);
	}

	@HandlesEvent("agregarExpPorColor")
	public Resolution agregarExpPorColor(){

		if(this.nivel.getDificultad() == null)
			return validarQueHayaDificultad();
		else{
			agregarDificultad();	
			return new ForwardResolution(ExplosionesPorColorActionBean.class);
		}
		
	}
	
	public ForwardResolution validarQueHayaDificultad(){
		ValidationErrors errors = new ValidationErrors();
			errors.add("dificultad", new SimpleError("Seleccione una dificultad antes para poder agregar un objetivo"));
		
        this.getContext().setValidationErrors(errors);
        return new ForwardResolution(ConfigurarActionBean.class);	
	}
	
	@HandlesEvent("agregarGrandesExplosiones")
	public Resolution agregarGrandesExplosiones(){
		
		this.agregarDificultad();	
		
		return new ForwardResolution(GrandesExplosionesActionBean.class);
	}
	
	public void setearDificultad(Dificultad dif){
		List<String> colores = new ArrayList<String>();
		this.getNivel().setDificultad(dif);
		colores = this.getNivel().getDificultad().getColores();
		this.getContext().getRequest().getSession().setAttribute("colores",colores);
	}
	
	public void agregarDificultad() {
		if(this.getNivel().getDificultad().equals("FACIL")){
			setearDificultad(Dificultad.FACIL);
		}
		if(this.getNivel().getDificultad().equals("NORMAL")){
			setearDificultad(Dificultad.NORMAL);
		}	
		if(this.getNivel().getDificultad().equals("DIFICIL")){
			setearDificultad(Dificultad.DIFICIL);
		}
	}
	
	@HandlesEvent("eliminar") 
	public Resolution eliminar() { 
		this.getNivel().getObjetivos().remove(this.getObjetivo()); 
		return this.view(); 
		}
	
	

	public Objetivo getObjetivo(){
		
		if(this.getNivel().getObjetivos().size() == 1){
			return this.getNivel().getObjetivos().get(0);	
		}
		
		return this.getNivel().getObjetivos().get(idn);
	}

	@HandlesEvent("editarObjetivo")
	public Resolution editarObjetivo(){

		
		if(this.getObjetivo().esGrandesExplosiones()){
			this.getContext().getRequest().getSession().setAttribute("objetivo",this.getObjetivo());
			return new ForwardResolution(EditarGrandesExplosionesActionBean.class);
		}
		
		this.getContext().getRequest().getSession().setAttribute("objetivo",this.getObjetivo());
		return new ForwardResolution(EditarExplosionesPorColorEnEditarNivelActionBean.class);

	}
	
	
}
