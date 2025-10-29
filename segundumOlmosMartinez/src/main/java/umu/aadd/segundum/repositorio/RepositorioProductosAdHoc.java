package umu.aadd.segundum.repositorio;

import java.time.Month;
import java.util.List;
import java.util.Map;

import repositorio.RepositorioException;
import repositorio.RepositorioString;
import umu.aadd.segundum.modelo.Categoria;
import umu.aadd.segundum.modelo.EstadoProducto;
import umu.aadd.segundum.modelo.Producto;

public interface RepositorioProductosAdHoc extends RepositorioString<Producto> {
	public Map<Producto, String> recuperarHistorial(Month mes, int anio) throws RepositorioException;

	public List<Producto> recuperarProductosVenta(Categoria categoria, String descripcion, EstadoProducto estado,
			double precio) throws RepositorioException;
}
