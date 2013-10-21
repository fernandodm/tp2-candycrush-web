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

@SessionScope
public class ConfigurarActionBean extends BaseActionBean {
	
	private MundoAppModel mundo;
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
	
	
	@HandlesEvent("agregarNivel")
	public Resolution agregarNivel(){
		
		this.mundo.getNiveles().add(this.mundo.getNivelEnConstruccion());
		mundo.setNivelEnConstruccion(new Nivel());
		return this.view();
		
	}
	
}
