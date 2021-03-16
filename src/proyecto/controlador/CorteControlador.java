package proyecto.controlador;

import proyecto.modelo.entidades.Usuario;

public interface CorteControlador {
	int inicializar();
	void generarCorte();
	void setUsuario(Usuario usuario);
}
