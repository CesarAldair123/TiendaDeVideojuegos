package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.Videojuego;

public interface VideojuegoDAO {
	public Videojuego conseguirVideojuego(int id);
	public ArrayList<Videojuego> conseguirVideojuegos();
	public int agregarVideojuego(Videojuego videojuego);
	public int editarVideojuego(Videojuego videojuego);
	public int eliminarVideojuego(int id);
	public int checarStock(int id);
}
