package umu.aadd.segundum.servicio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			if (!repositorioCategorias.getIds().contains(categoria.getId())) {
				repositorioCategorias.add(categoria);
			}
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
	
	@Override
	public List<Categoria> recuperarCategoriasOrdenadasJerarquicamente() throws RepositorioException {
	    List<Categoria> todas = recuperarTodasCategorias();
	    Map<String, List<Categoria>> hijosPorPadre = new HashMap<>();
	    List<Categoria> raices = new ArrayList<>();
	    for (Categoria c : todas) {
	        String idPadre = getIdPadre(c.getRuta());
	        if (idPadre == null) {
	            raices.add(c);
	        } else {
	            hijosPorPadre.computeIfAbsent(idPadre, k -> new ArrayList<>()).add(c);
	        }
	    }
	    List<Categoria> resultado = new ArrayList<>();
	    for (Categoria raiz : raices) {
	        agregarConHijos(raiz, hijosPorPadre, resultado);
	    }
	    return resultado;
	}
	
	/**
	 * Agrega una categoría y sus hijos a la lista de resultado de manera recursiva.
	 *
	 * @param categoria     La categoría a agregar.
	 * @param hijosPorPadre Mapa que relaciona los identificadores de las categorías
	 *                      con sus hijos.
	 * @param resultado     Lista donde se agregan las categorías en orden
	 *                      jerárquico.
	 */
	private void agregarConHijos(Categoria categoria, Map<String, List<Categoria>> hijosPorPadre, List<Categoria> resultado) {
	    resultado.add(categoria);
	    List<Categoria> hijos = hijosPorPadre.get(categoria.getId());
	    if (hijos != null) {
	        for (Categoria hijo : hijos) {
	            agregarConHijos(hijo, hijosPorPadre, resultado);
	        }
	    }
	}

	/**
	 * Obtiene el identificador del padre de una categoría a partir de su ruta.
	 *
	 * @param ruta La ruta de la categoría.
	 * @return El identificador del padre, o null si no hay padre.
	 */
	private String getIdPadre(String ruta) {
	    String[] partes = ruta.split("\\|");
	    if (partes.length < 3) {
	        return null;
	    }
	    return partes[partes.length - 2];
	}

}
