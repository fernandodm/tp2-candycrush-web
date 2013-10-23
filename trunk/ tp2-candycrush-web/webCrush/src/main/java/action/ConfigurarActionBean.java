package action;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.constraint.HasSubstring;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;


public class ConfigurarActionBean extends BaseActionBean {
	
	private MundoAppModel mundo;
	private Integer id;
	private String dificultad;
	private String filtro;
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public MundoAppModel getMundo() {
		return mundo;
	}

	public void setMundo(MundoAppModel mundo) {
		this.mundo = mundo;
	}

	
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void ejecutar(){
		mundo = (MundoAppModel) this.getContext().getRequest().getSession().getAttribute("mundo");

	}
	
	@DefaultHandler
	public Resolution view() {
		
		return new ForwardResolution("configurar.jsp");
	}
	
	public ForwardResolution validarQueHayaDificultad(){
		ValidationErrors errors = new ValidationErrors();
			errors.add("dificultad", new SimpleError("Seleccione una dificultad antes para poder agregar un objetivo"));
		
        this.getContext().setValidationErrors(errors);
        return new ForwardResolution(ConfigurarActionBean.class);	
	}
	
	
	@HandlesEvent("agregarExpPorColor")
	public Resolution agregarExpPorColor(){

		if(dificultad == null)
			return validarQueHayaDificultad();
		else{
			agregarDificultad();	
			return new ForwardResolution(ExplosionesPorColorActionBean.class);
		}

	}
	
	@HandlesEvent("agregarGrandesExplosiones")
	public Resolution agregarGrandesExplosiones(){	
		if(dificultad == null)
			return validarQueHayaDificultad();
		else{
			agregarDificultad();	
			return new ForwardResolution(GrandesExplosionesActionBean.class);
		}

	}
	
	public void setearDificultad(Dificultad dif){
		List<String> colores = new ArrayList<String>();
		mundo.getNivelEnConstruccion().setDificultad(dif);
		colores = mundo.getNivelEnConstruccion().getDificultad().getColores();
		this.getContext().getRequest().getSession().setAttribute("colores",colores);
	}
	
	public void agregarDificultad() {
		if(dificultad.equals("FACIL")){
			setearDificultad(Dificultad.FACIL);
		}
		if(dificultad.equals("NORMAL")){
			setearDificultad(Dificultad.NORMAL);
		}	
		if(dificultad.equals("DIFICIL")){
			setearDificultad(Dificultad.DIFICIL);
		}
	}

	
	@HandlesEvent("eliminar") 
	public Resolution eliminar() { 
		this.mundo.eliminarObjetivo(this.getObjetivo()); 
		return this.view(); 
		}
	
	@HandlesEvent("eliminarNivel")
    public Resolution eliminarNivel() {
    	this.mundo.getMundo().getNiveles().remove(this.getNivel());
  
    	return this.view();
    }
	
		
	public Objetivo getObjetivo(){
		return this.mundo.objetivosDelNivel().get(id);
	}

	public Nivel getNivel(){
		return this.mundo.getMundo().getNiveles().get(id);
	}
	
	@HandlesEvent("editarObjetivo")
	public Resolution editarObjetivo(){

		
		if(this.getObjetivo().esGrandesExplosiones()){
			this.getContext().getRequest().getSession().setAttribute("objetivo",this.getObjetivo());
			return new ForwardResolution(EditarGrandesExplosionesActionBean.class);
		}
		
		this.getContext().getRequest().getSession().setAttribute("objetivo",this.getObjetivo());
		return new ForwardResolution(EditarExplosionesPorColorActionBean.class);

	}
	
	@HandlesEvent("editarNivel")
	public Resolution editNivel(){
		this.getContext().getRequest().getSession().setAttribute("mundoApp",this.getMundo());
		this.getContext().getRequest().getSession().setAttribute("nivel",this.getNivel());
		this.getContext().getRequest().getSession().setAttribute("idn",this.getId());
		return new ForwardResolution(EditarNivelActionBean.class);
	}
	
	@HandlesEvent("filtrarPorNombre")
	public Resolution filtrarPorNombre(){
		System.out.println(filtro);
		if(filtro == null){
			this.mundo.setNiveles(this.mundo.getMundo().getNiveles());
		}else{
		this.mundo.setNiveles(this.mundo.buscarPorNombre(filtro));
		}

		return this.view();
	}
	
	
	public boolean validarNombreNivel(ValidationErrors errors){
		if(mundo.getNivelEnConstruccion().getNombre() == null){
			errors.add("mundo.nivelEnConstruccion.nombre", new SimpleError("Ingrese un nombre para el nivel"));
			return true;
		}
		return false;
	}
	
	public boolean validarLaDificultad(ValidationErrors errors){
		if(mundo.getNivelEnConstruccion().getDificultad() == null){
			errors.add("dificultad", new SimpleError("Ingrese una dificultad"));
			return true;
		}
		return false;
	}
	
	public boolean validarAlto(ValidationErrors errors){
		if(mundo.getNivelEnConstruccion().getTablero().getAlto() == null ||
		(int)mundo.getNivelEnConstruccion().getTablero().getAlto() < 3){
			errors.add("mundo.nivelEnConstruccion.tablero.alto", new SimpleError("Filas mínimas = 3"));
			return true;
		}
		return false;
	}
	
	public boolean validarAncho(ValidationErrors errors){
		if(mundo.getNivelEnConstruccion().getTablero().getAncho() == null ||
		mundo.getNivelEnConstruccion().getTablero().getAncho() < 3){
			errors.add("mundo.nivelEnConstruccion.tablero.ancho", new SimpleError("Columnas mínimas = 3"));
			return true;
		}
		return false;
	}
	
	public boolean validarCantidadMovimientos(ValidationErrors errors){
		if(mundo.getNivelEnConstruccion().getCantidadMovimientos() == null ||
		        (int)mundo.getNivelEnConstruccion().getCantidadMovimientos() < 1){
		        	errors.add("mundo.nivelEnConstruccion.cantidadMovimientos", new SimpleError("Movimientos mínimos = 1"));
		        	return true;
		}
		return false;
	}
	
	public boolean validarPuntajeMinimo(ValidationErrors errors){
		if(mundo.getNivelEnConstruccion().getPuntajeMinimo() == null){
			errors.add("mundo.nivelEnConstruccion.puntajeMinimo", new SimpleError("Ingrese un puntaje mínimo"));
			return true;
		}
		return false;
	}
	
	public boolean validarObjetivosDelNivel(ValidationErrors errors){
		if(this.mundo.objetivosDelNivel().size() == 0){
			errors.add("mundo.nivelEnConstruccion.objetivosDelNivel", new SimpleError("Debe agregar por lo menos un objetivo"));
	        return  true;
		}
		return false;
	}

	@HandlesEvent("agregarNivel")
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
		        return new ForwardResolution(ConfigurarActionBean.class);
			} else {
			
			this.mundo.getMundo().getNiveles().add(this.mundo.getNivelEnConstruccion());
			mundo.setNivelEnConstruccion(new Nivel());
			return this.view();}
			
			}		

}

