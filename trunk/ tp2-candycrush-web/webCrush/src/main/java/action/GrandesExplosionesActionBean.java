package action;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import Tp.CandyCrush.GrandesExplosiones;
import appModel.MundoAppModel;

public class GrandesExplosionesActionBean extends BaseActionBean{
	
	private GrandesExplosiones objetivo;
	private List<String> colores;
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
	@Before
	public void ejecutar(){
		colores = (List<String>) this.getContext().getRequest().getSession().getAttribute("colores");
		objetivo = (GrandesExplosiones) this.getContext().getRequest().getSession().getAttribute("grandes");
	}

}
