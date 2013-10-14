package appModel;

import java.util.List;

import net.sourceforge.stripes.validation.Validate;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Mundo;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

public class MundoAppModel {
	
	private String nombreUsuario;
	private Mundo mundo = new Mundo();
	private Nivel nivelEnConstruccion = new Nivel();
	private Dificultad dificultad;
	
	public Nivel getNivelEnConstruccion() {
		return nivelEnConstruccion;
	}
	public void setNivelEnConstruccion(Nivel nivelEnConstruccion) {
		this.nivelEnConstruccion = nivelEnConstruccion;
	}
	public Dificultad getDificultad() {
		return dificultad;
	}
	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Mundo getMundo() {
		return mundo;
	}
	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}
	public void eliminarObjetivo(Objetivo objetivo) {
		nivelEnConstruccion.eliminarObjetivo(objetivo);
	}
	public List<Objetivo> objetivosDelNivel() {
		
		return nivelEnConstruccion.getObjetivos();
	}
	
}
