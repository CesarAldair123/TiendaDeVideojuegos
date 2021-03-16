package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.Distribuidora;

public interface DistribuidoraDAO {
	public Distribuidora conseguirDistribuidora(int id);
	public ArrayList<Distribuidora> conseguirDistribuidoras();
	public int agregarDistribuidora(Distribuidora distribuidora);
	public int editarDistribuidora(Distribuidora distribuidora);
	public int eliminarDistribuidora(int id);
}
