package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.Usuario;

public interface UsuarioDAO {
	public Usuario conseguirUsuario(int id, String nombre, String password);
	public ArrayList<Usuario> conseguirUsuarios();
	public int agregarUsuario(Usuario usuario);
	public int editarUsuario(Usuario usuario);
	public int eliminarUsuario(int id);
}
