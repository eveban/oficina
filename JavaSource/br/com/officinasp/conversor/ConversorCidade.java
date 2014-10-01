package br.com.officinasp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.officinasp.dao.DAO;
import br.com.officinasp.modelo.Cidade;

@FacesConverter(value = "conversorCidade", forClass = Cidade.class)
public class ConversorCidade implements Converter {
	public static DAO<Cidade> dao = new DAO<Cidade>(Cidade.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}		
		int id = Integer.parseInt(value);
		return dao.buscaPorId(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null ) {
			return null;
		}
		Cidade cidades = (Cidade) value;
		return String.valueOf(cidades.getId());
	}
}
