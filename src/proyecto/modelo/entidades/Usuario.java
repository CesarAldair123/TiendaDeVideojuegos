package proyecto.modelo.entidades;

public class Usuario {
	private int id;
	private String nivel;
	private String nombre;
	private String password;
	public Usuario() {
		
	}
	public Usuario(int id, String nivel, String nombre, String password) {
		super();
		this.id = id;
		this.nivel = nivel;
		this.nombre = nombre;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getNivel() {
		return nivel;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nivel=" + nivel + ", nombre=" + nombre + ", password=" + password + "]";
	}

}
