package proyecto.controlador;

import proyecto.modelo.entidades.Usuario;

public interface VentaControlador  {
	int inicializar();
	void filtrarPorConsola();
	void filtrarPorGenero();
	void filtrarPorNombre();
	void agregar();
	void cancelar();
	void procesarVenta();
	void setUsuario(Usuario usuario);
}
