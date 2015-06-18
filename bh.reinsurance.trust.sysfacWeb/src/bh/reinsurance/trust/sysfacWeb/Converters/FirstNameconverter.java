package bh.reinsurance.trust.sysfacWeb.Converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import al.assu.trust.GestionImageSinistre.domain.User;
import bh.reinsurance.trust.sysfacWeb.beans.MapBean;

@FacesConverter("NameConverter")
public class FirstNameconverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (!arg2.trim().equals("")) {
			try {

				MapBean helper = arg0.getApplication().evaluateExpressionGet(
						arg0, "#{MapBean}", MapBean.class);

				return helper.Finduserbyname(Integer.parseInt(arg2));

			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid theme."));
			}
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {

			return String.valueOf(((User) arg2).getId());

		} else {
			return null;
		}
	}

}
