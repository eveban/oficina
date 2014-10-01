package br.com.officinasp.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Modelo;

@FacesConverter(value = "ConversorModelo", forClass = Modelo.class)
public class ConversorModelo implements Converter {

	public static DAO<Modelo> dao = new DAO<Modelo>(Modelo.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		String id = value;
		//return dao.buscaPorId(id);
		return id;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null && !"".equals(value)) {
			return "";
		}
		Modelo modelo = (Modelo) value;
		return String.valueOf(modelo.getId());
	}

}
