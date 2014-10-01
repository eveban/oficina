package br.com.angelelli.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.angelelli.modelo.Classificacao;

//@FacesConverter(value = "classificaConverter", forClass = Classificacao.class)
public class ClassificacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {

		if (value != null) {
			//return this.getAttributesFrom(component).get(value);
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if (value != null && !"".equals(value)) {
			Classificacao entity = (Classificacao) value;

			if (entity.getId() != 0) {
				//this.addAttribute(component, entity);

				if (entity.getId() != 0) {
					return String.valueOf(entity.getId());
				}
				return (String) value;
			}
		}
		return "";
	}

/*	private void addAttribute(UIComponent component, Classificacao o) {
		this.getAttributesFrom(component).put(o.getId().toString(), o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}*/

}
