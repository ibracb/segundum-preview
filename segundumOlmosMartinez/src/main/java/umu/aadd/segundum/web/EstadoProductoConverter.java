package umu.aadd.segundum.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import umu.aadd.segundum.modelo.EstadoProducto;

/**
 * Conversor JSF para la enumeración EstadoProducto.
 */
@FacesConverter(forClass = EstadoProducto.class)
public class EstadoProductoConverter implements Converter<EstadoProducto> {
    
	@Override
    public EstadoProducto getAsObject(FacesContext context, UIComponent component, String value) {
        return value != null ? EstadoProducto.valueOf(value) : null;
    }
	
    @Override
    public String getAsString(FacesContext context, UIComponent component, EstadoProducto value) {
        return value != null ? value.name() : "";
    }
}