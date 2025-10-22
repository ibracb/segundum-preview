package umu.aadd.segundum.repositorio;

import java.util.List;

import umu.aadd.segundum.modelo.Categoria;

public interface RepositorioCategoriasAdHoc {
	
	public List<Categoria> getRaices();
	
	public List<Categoria> getDescendientes(String idCategoria);
	
}
