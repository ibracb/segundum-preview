package umu.aadd.segundum.servicio;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.repositorio.RepositorioCategoriasAdHoc;
import utils.StringUtilidades;

/**
 * Implementación del servicio de categorías.
 */
public class ServicioCategorias implements IServicioCategorias {

	/**
	 * Repositorio de categorías.
	 */
	private RepositorioCategoriasAdHoc repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);

	/**
	 * {@inheritDoc}
	 * 
	 * La jerarquía de categorías se carga desde un fichero XML ubicado en la ruta
	 * especificada.
	 */
	@Override
	public void cargarJerarquiaCategorias(String ruta) throws JAXBException, RepositorioException {
		if (ruta != null) {
			JAXBContext contexto = JAXBContext.newInstance(Categoria.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			Categoria categoria = (Categoria) unmarshaller.unmarshal(new File(ruta));
			repositorioCategorias.add(categoria);
		}
	}

	@Override
	public void modificarCategoria(String idCategoria, String descripcionNueva)
			throws RepositorioException, EntidadNoEncontrada {
		Categoria categoria = repositorioCategorias.getById(idCategoria);
		if (categoria != null && StringUtilidades.isDatoValido(descripcionNueva)) {
			categoria.setDescripcion(descripcionNueva);
			repositorioCategorias.update(categoria);
		}
	}

	@Override
	public List<Categoria> recuperarCategoriasRaiz() throws RepositorioException {
		return repositorioCategorias.getRaices();
	}

	@Override
	public List<Categoria> recuperarDescendientesCategoria(String idCategoria) throws RepositorioException {
		return repositorioCategorias.getDescendientes(idCategoria);
	}

	@Override
	public Categoria recuperarCategoria(String idCategoria) throws RepositorioException, EntidadNoEncontrada {
		return repositorioCategorias.getById(idCategoria);
	}

	@Override
	public List<Categoria> recuperarTodasCategorias() throws RepositorioException {
		return repositorioCategorias.getAll();
	}

}
