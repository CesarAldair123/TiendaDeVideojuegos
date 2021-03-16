package proyecto.modelo.entidades;

import java.sql.Date;

public class Corte {
	private int id;
	private int usuarioId;
	private Date fecha;
	private float monto;
	public Corte() {
		
	}
	public Corte(int id, int usuarioId, Date fecha, float monto) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.fecha = fecha;
		this.monto = monto;
	}
	public int getId() {
		return id;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getMonto() {
		return monto;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	@Override
	public String toString() {
		return "Corte [id=" + id + ", usuarioId=" + usuarioId + ", fecha=" + fecha + ", monto=" + monto + "]";
	}
	
}
