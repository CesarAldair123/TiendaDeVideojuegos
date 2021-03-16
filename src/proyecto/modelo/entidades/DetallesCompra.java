package proyecto.modelo.entidades;

public class DetallesCompra {
	private String compraId;
	private int videojuegoId;
	private int cantidad;
	private float monto;
	private Videojuego videojuego;
	public DetallesCompra() {
		
	}
	public DetallesCompra(String compraId, int videojuegoId, int cantidad, float monto) {
		super();
		this.compraId = compraId;
		this.videojuegoId = videojuegoId;
		this.cantidad = cantidad;
		this.monto = monto;
	}

	public String getCompraId() {
		return compraId;
	}

	public int getVideojuegoId() {
		return videojuegoId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public float getMonto() {
		return monto;
	}

	public void setVentaId(String ventaId) {
		this.compraId = ventaId;
	}

	public void setVideojuegoId(int videojuegoId) {
		this.videojuegoId = videojuegoId;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	@Override
	public String toString() {
		return "DetallesCompra [ventaId=" + compraId + ", videojuegoId=" + videojuegoId + ", cantidad=" + cantidad
				+ ", monto=" + monto + "]";
	}
}
