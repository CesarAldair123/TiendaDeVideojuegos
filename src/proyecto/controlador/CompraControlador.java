package proyecto.controlador;

import proyecto.modelo.entidades.Usuario;

public interface CompraControlador{
	int inicializar();
	void filtrarPorConsola();
	void filtrarPorGenero();
	void filtrarPorNombre();
	void agregar();
	void cancelar();
	void procesarCompra();
	void setUsuario(Usuario usuario);
}
