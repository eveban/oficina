package br.com.angelellirh.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.angelellirh.model.Experiencia;
import br.com.angelellirh.model.Profissional;

@ManagedBean
@ViewScoped
public class ExperienciaMB {

	private Experiencia experiencia = new Experiencia();
	private Profissional profissional = new Profissional();

	public void addExperiencia(Experiencia experiencia) {
		profissional.getExperiencias().add(experiencia);
		experiencia = new Experiencia();
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Experiencia getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}

	
}
