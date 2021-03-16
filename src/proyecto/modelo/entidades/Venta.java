package proyecto.modelo.entidades;

import java.sql.Date;
import java.util.ArrayList;

public class Venta {
	private String id;
	private int usuarioId;
	private Date fecha;
	private float total;
	private ArrayList<DetallesVenta> detallesVenta;
	public Venta() {
		
	}
	public Venta(String id, int usuarioId, Date fecha, float total) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.fecha = fecha;
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getTotal() {
		return total;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public ArrayList<DetallesVenta> getDetallesVenta() {
		return detallesVenta;
	}
	public void setDetallesVenta(ArrayList<DetallesVenta> detallesVenta) {
		this.detallesVenta = detallesVenta;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", usuarioId=" + usuarioId + ", fecha=" + fecha + ", total=" + total + "]";
	}
	
}
