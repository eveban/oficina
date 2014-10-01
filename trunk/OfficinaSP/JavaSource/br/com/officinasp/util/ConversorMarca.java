package br.com.officinasp.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Marca;

@FacesConverter(value = "ConversorMarca", forClass = Marca.class)
public class ConversorMarca implements Converter {

	public static DAO<Marca> dao = new DAO<Marca>(Marca.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		int id = Integer.parseInt(value);
		return dao.buscaPorId(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return "";
		}
		Marca marcas = (Marca) value;
		return String.valueOf(marcas.getId());
	}
}
