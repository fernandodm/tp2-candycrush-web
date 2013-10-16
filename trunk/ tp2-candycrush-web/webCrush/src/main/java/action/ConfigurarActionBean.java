package action;

import java.util.List;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;

@SessionScope
public class ConfigurarActionBean extends BaseActionBean {
	
	private MundoAppModel mundo;
	private Integer id;
	
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

	
	@Before
	public void ejecutar(){
		mundo = (MundoAppModel) this.getContext().getRequest().getSession().getAttribute("mundo");
	}
	
	@DefaultHandler
	public Resolution view() {
		return new ForwardResolution("configurar.jsp");
	}
	
	@HandlesEvent("agregarGrandesExplosiones")
	public Resolution agregarGrandesExplosiones(){
		
		List<String> colores = mundo.getNivelEnConstruccion().getDificultad().getColores();
		
		this.getContext().getRequest().getSession().setAttribute("grandes",mundo.getObjetivo());
		this.getContext().getRequest().getSession().setAttribute("colores",colores);
		return new ForwardResolution("grandesExplosiones.jsp");
		
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
}
