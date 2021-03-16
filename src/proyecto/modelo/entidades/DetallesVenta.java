package proyecto.modelo.entidades;

public class DetallesVenta {
	private String ventaId;
	private int videojuegoId;
	private int cantidad;
	private float monto;
	private Videojuego videojuego;
	public DetallesVenta() {
		
	}
	public DetallesVenta(String ventaId, int videojuegoId, int cantidad, float monto) {
		super();
		this.ventaId = ventaId;
		this.videojuegoId = videojuegoId;
		this.cantidad = cantidad;
		this.monto = monto;
	}

	public String getVentaId() {
		return ventaId;
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
		this.ventaId = ventaId;
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
		return "DetallesVenta [ventaId=" + ventaId + ", videojuegoId=" + videojuegoId + ", cantidad=" + cantidad
				+ ", monto=" + monto + "]";
	}
}
