package action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

public class EditarNivelActionBean extends BaseActionBean {

	private Nivel nivel;

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	@Before(stages=LifecycleStage.BindingAndValidation)
	public void iniciarEdicion(){
		this.setNivel((Nivel) this.getContext().getRequest().getSession().getAttribute("nivel"));
	}

	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("editarNivel.jsp");
	}

	@HandlesEvent("terminarEdicionNivel")
	public Resolution terminarEdicionNivel(){
		this.getContext().getRequest().getSession().setAttribute("nivel",null);
		return new ForwardResolution(ConfigurarActionBean.class);
	}

	@HandlesEvent("agregarExpPorColor")
	public Resolution agregarExpPorColor(){
		
		this.agregarDificultad();	
		
		return new ForwardResolution(ExplosionesPorColorActionBean.class);
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
	return this.getNivel().getObjetivos().get(this.getNivel().getId());
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
	
	
}
