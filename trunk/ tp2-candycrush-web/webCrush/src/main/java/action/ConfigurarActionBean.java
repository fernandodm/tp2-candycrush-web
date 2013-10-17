package action;

import java.util.ArrayList;
import java.util.List;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.GrandesExplosiones;
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

@SessionScope
public class ConfigurarActionBean extends BaseActionBean {
	
	private MundoAppModel mundo = new MundoAppModel();
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

	
	@HandlesEvent("agregarGrandesExplosiones")
	public Resolution agregarGrandesExplosiones(){
		
		
		List<String> colores = new ArrayList<String>();
		if(dificultad.equals("FACIL")){
			mundo.getNivelEnConstruccion().setDificultad(Dificultad.FACIL);
			colores = mundo.getNivelEnConstruccion().getDificultad().getColores();
		}
		if(dificultad.equals("NORMAL")){
			mundo.getNivelEnConstruccion().setDificultad(Dificultad.NORMAL);
			colores = mundo.getNivelEnConstruccion().getDificultad().getColores();
		}	
		if(dificultad.equals("DIFICIL")){
			mundo.getNivelEnConstruccion().setDificultad(Dificultad.DIFICIL);
			colores = mundo.getNivelEnConstruccion().getDificultad().getColores();
		}	
		
		this.getContext().getRequest().getSession().setAttribute("obj",mundo.getObjetivo());
		this.getContext().getRequest().getSession().setAttribute("colores",colores);
		return new ForwardResolution(GrandesExplosionesActionBean.class);
		
	}
	
	@HandlesEvent("eliminar")
    public Resolution eliminar() {
    	this.mundo.eliminarObjetivo(this.getObjetivo());
    	return this.view();
    }
		
	public Objetivo getObjetivo(){
		
		List<Objetivo> objs = this.mundo.objetivosDelNivel();
		for(Objetivo each : objs){
			if(each.equals(objs.get(id))){
				return each;
			}
		}
		
		throw new RuntimeException("No existe el objetivo seleccionado"); 
	}
	
	
	@HandlesEvent("agregarNivel")
	public Resolution agregarNivel(){
		
		this.mundo.getNiveles().add(this.mundo.getNivelEnConstruccion());
		mundo.setNivelEnConstruccion(new Nivel());
		return this.view();
		
	}
	
}
