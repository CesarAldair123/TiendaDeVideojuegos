package proyecto.modelo.entidades;

public class Videojuego {
	private int id;
	private int distribuidoraId;
	private String nombre;
	private String desarrolladora;
	private String consola;
	private String clasificacion;
	private String generos;
	private float precio;
	private int stock;
	private Distribuidora distribuidora;
	public Videojuego() {
		
	}
	public Videojuego(int id, int distribuidoraId, String nombre, String desarrolladora, String consola,
			String clasificacion, String generos, float precio, int stock) {
		super();
		this.id = id;
		this.distribuidoraId = distribuidoraId;
		this.nombre = nombre;
		this.desarrolladora = desarrolladora;
		this.consola = consola;
		this.clasificacion = clasificacion;
		this.generos = generos;
		this.precio = precio;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public int getDistribuidoraId() {
		return distribuidoraId;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDesarrolladora() {
		return desarrolladora;
	}

	public String getConsola() {
		return consola;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public String getGeneros() {
		return generos;
	}

	public float getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDistribuidoraId(int distribuidoraId) {
		this.distribuidoraId = distribuidoraId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDesarrolladora(String desarrolladora) {
		this.desarrolladora = desarrolladora;
	}

	public void setConsola(String consola) {
		this.consola = consola;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setGeneros(String generos) {
		this.generos = generos;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Distribuidora getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(Distribuidora distribuidora) {
		this.distribuidora = distribuidora;
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", distribuidoraId=" + distribuidoraId + ", nombre=" + nombre
				+ ", desarrolladora=" + desarrolladora + ", consola=" + consola + ", clasificacion=" + clasificacion
				+ ", generos=" + generos + ", precio=" + precio + ", stock=" + stock + "]";
	}

}
