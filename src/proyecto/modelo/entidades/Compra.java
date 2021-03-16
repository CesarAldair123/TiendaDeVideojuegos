package proyecto.modelo.entidades;

import java.sql.Date;
import java.util.ArrayList;

public class Compra {
	private String id;
	private int usuarioId;
	private int distribuidoraId;
	private Date fecha;
	private float total;
	private ArrayList<DetallesCompra> detallesCompra;
	public Compra() {
		
	}
	public Compra(String id, int usuarioId, int distribuidoraId, Date fecha, float total) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.distribuidoraId = distribuidoraId;
		this.fecha = fecha;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public int getDistribuidoraId() {
		return distribuidoraId;
	}

	public void setDistribuidoraId(int distribuidoraId) {
		this.distribuidoraId = distribuidoraId;
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

	public ArrayList<DetallesCompra> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(ArrayList<DetallesCompra> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", usuarioId=" + usuarioId + ", distribuidoraId=" + distribuidoraId + ", fecha="
				+ fecha + ", total=" + total + "]";
	}

}
