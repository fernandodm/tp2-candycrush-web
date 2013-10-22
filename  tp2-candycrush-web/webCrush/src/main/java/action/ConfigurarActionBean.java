package action;

import java.util.ArrayList;
import java.util.List;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;

@SessionScope
public class ConfigurarActionBean extends BaseActionBean {
	
	private MundoAppModel mundo;
	//Entity maneja todo lo del ID
	private Integer id;
	private String dificultad;
	
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
	
	@HandlesEvent("agregarExpPorColor")
	public Resolution agregarExpPorColor(){
		
		agregarDificultad();	
		
		return new ForwardResolution(ExplosionesPorColorActionBean.class);
	}
	
	@HandlesEvent("agregarGrandesExplosiones")
	public Resolution agregarGrandesExplosiones(){
		
		agregarDificultad();	
		
		return new ForwardResolution(GrandesExplosionesActionBean.class);
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
		
	public Objetivo getObjetivo(){
		
		return this.mundo.objetivosDelNivel().get(id); 
		
	}
	
	@HandlesEvent("editarObjetivo")
	public Resolution editarObjetivo(){
		
		this.getContext().getRequest().getSession().setAttribute("objetivo",this.getObjetivo());
		
		return new ForwardResolution(EditarGrandesExplosionesActionBean.class);
		
	}
	
	@HandlesEvent("agregarNivel")
	public Resolution agregarNivel(){
			
			boolean falloValidation = false;

			ValidationErrors errors = new ValidationErrors();
			
			if(mundo.getNivelEnConstruccion().getNombre() == null){
				errors.add("mundo.nivelEnConstruccion.nombre", new SimpleError("Ingrese un nombre para el nivel"));
				falloValidation = true;
			}
			
			if(mundo.getNivelEnConstruccion().getTablero().getAlto() == null ||
			(int)mundo.getNivelEnConstruccion().getTablero().getAlto() < 3){
				errors.add("mundo.nivelEnConstruccion.tablero.alto", new SimpleError("Filas mínimas = 3"));
				falloValidation = true;
			}
					
			if(mundo.getNivelEnConstruccion().getTablero().getAncho() == null ||
			mundo.getNivelEnConstruccion().getTablero().getAncho() < 3){
				errors.add("mundo.nivelEnConstruccion.tablero.ancho", new SimpleError("Columnas mínimas = 3"));
				falloValidation = true;
			}
					
			if(mundo.getNivelEnConstruccion().getCantidadMovimientos() == null ||
		        (int)mundo.getNivelEnConstruccion().getCantidadMovimientos() < 1)
		        	errors.add("mundo.nivelEnConstruccion.cantidadMovimientos", new SimpleError("Movimientos mínimos = 1"));
		    
			if(mundo.getNivelEnConstruccion().getPuntajeMinimo() == null){
				errors.add("mundo.nivelEnConstruccion.puntajeMinimo", new SimpleError("Ingrese un puntaje mínimo"));
				falloValidation = true;
			}
			
			if(this.mundo.objetivosDelNivel().size() == 0){
				errors.add("mundo.nivelEnConstruccion.objetivosDelNivel", new SimpleError("Debe agregar por lo menos un objetivo"));
		        falloValidation = true;
			}
				
			if(falloValidation){
		        this.getContext().setValidationErrors(errors);
		        return new ForwardResolution(ConfigurarActionBean.class);
			}
			
			this.mundo.getNiveles().add(this.mundo.getNivelEnConstruccion());
			mundo.setNivelEnConstruccion(new Nivel());
			return this.view();
		}

}
