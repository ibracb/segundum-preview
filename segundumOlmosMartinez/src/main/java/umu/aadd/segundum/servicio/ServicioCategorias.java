package umu.aadd.segundum.servicio;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;
import utils.StringUtilidades;

/**
 * Implementación del servicio de categorías.
 */
public class ServicioCategorias implements IServicioCategorias {
	
	private Repositorio<Categoria, String> repoCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);
	
	@Override
	public void cargarJerarquiaCategorias(String ruta) throws JAXBException, RepositorioException {
		JAXBContext contexto = JAXBContext.newInstance(Categoria.class);
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		Categoria categoria = (Categoria) unmarshaller.unmarshal(new File(ruta));
		repoCategorias.add(categoria);
	}

	@Override
	public void modificarCategoria(String idCategoria, String descripcionNueva) throws RepositorioException, EntidadNoEncontrada {
		Categoria categoria = repoCategorias.getById(idCategoria);
		if(descripcionNueva != null && StringUtilidades.isDatoValido(descripcionNueva)) {
			categoria.setDescripcion(descripcionNueva);
		}
		repoCategorias.update(categoria);
	}

	@Override
	public List<Categoria> recuperarCategoriasRaiz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> recuperarDescendientesCategoria(String idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

}
