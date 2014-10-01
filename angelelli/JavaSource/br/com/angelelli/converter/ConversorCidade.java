package br.com.angelelli.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.angelelli.dao.DAO;
import br.com.angelelli.modelo.Classificacao;

@FacesConverter(value = "conversorClassificacao", forClass = Classificacao.class)
public class ConversorCidade implements Converter {
	public static DAO<Classificacao> dao = new DAO<Classificacao>(Classificacao.class);

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
		Classificacao classificacao = (Classificacao) value;
		return String.valueOf(classificacao.getId());
	}
}
