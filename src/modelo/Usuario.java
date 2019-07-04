package modelo;

public class Usuario {
	
	public int id;
	public String nombre, apellido, correo;
	
	public Usuario(int id, String nombre, String apellido, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}

	@Override
	public String toString() {
		return id+" "+nombre+" "+apellido+" / "+correo;
	}
	
}
