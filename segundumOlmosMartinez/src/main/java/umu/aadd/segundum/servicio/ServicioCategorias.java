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
import umu.aadd.segundum.modelo.Usuario;
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

	private Repositorio<Usuario, String> repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);

	/**
	 * {@inheritDoc}
	 * 
	 * La jerarquía de categorías se carga desde un fichero XML ubicado en la ruta
	 * especificada.
	 * 
	 * @throws EntidadNoEncontrada
	 */
	@Override
	public void cargarJerarquiaCategorias(String idUsuario, String ruta)
			throws JAXBException, RepositorioException, EntidadNoEncontrada {
		if (repositorioUsuarios.getById(idUsuario) != null && repositorioUsuarios.getById(idUsuario).isAdministrador()
				&& ruta != null) {
			JAXBContext contexto = JAXBContext.newInstance(Categoria.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			Categoria categoria = (Categoria) unmarshaller.unmarshal(new File(ruta));
			repositorioCategorias.add(categoria);
		}
	}

	@Override
	public void modificarCategoria(String idUsuario, String idCategoria, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {
		if (repositorioUsuarios.getById(idUsuario) == null
				|| !repositorioUsuarios.getById(idUsuario).isAdministrador()) {
			return;
		}
		Categoria categoria = repositorioCategorias.getById(idCategoria);
		if (categoria != null && StringUtilidades.isDatoValido(descripcion)) {
			categoria.setDescripcion(descripcion);
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
		if (repositorioCategorias.getIds().stream().anyMatch(id -> id.equals(idCategoria))) {
			Categoria categoria = repositorioCategorias.getById(idCategoria);
			if (categoria == null) {
				System.err.println("No se puede recuperar la categoría con id " + idCategoria
						+ " porque no se encuentra en el repositorio");
				return null;
			}
			return categoria;
		}
		return null;
	}

	@Override
	public List<Categoria> recuperarTodasCategorias() throws RepositorioException {
		return repositorioCategorias.getAll();
	}

}
