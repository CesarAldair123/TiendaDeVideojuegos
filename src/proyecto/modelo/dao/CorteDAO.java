package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.Corte;

public interface CorteDAO {
	public Corte conseguirCorte(int id);
	public ArrayList<Corte> conseguirCortes();
	public int agregarCorte(Corte corte);
	public int editarCorte(Corte corte);
	public int eliminarCorta(int id);
}
