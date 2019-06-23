package vista;

import org.json.JSONObject;

import controlador.RespuestaHilo;

public class HiloPeticiones extends Thread implements RespuestaHilo {
	
	public JSONObject respueta = null;
	private JSONObject pregunta;
	
	public HiloPeticiones(JSONObject pregunta) {
		this.pregunta = pregunta;
	}
	
	@Override
	public void run() {
		Login.conexion.setInstruccion(pregunta, this);
		while (this.respueta == null) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void respuesta(JSONObject respuestajson) {
		this.respueta = respuestajson;
		
	}

}
