package proyecto.modelo.entidades;

public class Distribuidora {
	private int id;
	private String nombre;
	private String telefono;
	private String direccion;
	private String cuentaBancaria;
	public Distribuidora() {
		
	}
	public Distribuidora(int id, String nombre, String telefono, String direccion, String cuentaBancaria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.cuentaBancaria = cuentaBancaria;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	@Override
	public String toString() {
		return "Distribuidora [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", cuentaBancaria=" + cuentaBancaria + "]";
	}

}
