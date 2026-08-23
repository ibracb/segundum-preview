package umu.aadd.segundum.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import servicio.FactoriaServicios;
import umu.aadd.segundum.dto.ProductoDTO;
import umu.aadd.segundum.servicio.IServicioProductos;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ControladorDetallesProducto implements Serializable {

    private ProductoDTO producto;

    private IServicioProductos servicioProductos;

    @PostConstruct
    public void init() {
        servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);

        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (id != null) {
            try {
                producto = servicioProductos.recuperarProductoDTO(id);
            } catch (Exception e) {
                producto = null;
            }
        }
    }

    public ProductoDTO getProducto() {
        return producto;
    }
}