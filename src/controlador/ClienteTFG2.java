package controlador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class ClienteTFG2 extends Thread{
    
    private String respuestaEnc;
    public EncryptModule enc;
    
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket servidor;
    public boolean login_incorrecto;
    
    private RespuestaLogin interfaz;
    private RespuestaHilo interfaz2;
    
    private JSONObject pregunta;
    private JSONObject respuesta;
	private JSONObject peticion;
	
	private String userName = "";
	private String passWord = "";
	private final Thread conexion;
	private int retry = 15;
	public boolean running = true;
	public static int tipo;
	public static int id_user;
	public static int id_usuario_logueado;
	public static int id_ticket;
	public static int id_user_asignado;
	public static boolean lista_eventos=false;
	public static int lista_eventos_numero;
	
	
	public ClienteTFG2(String userName, String passWord, EncryptModule enc, RespuestaLogin interfaz) {
		this.userName = userName;
		this.passWord = passWord;
		this.enc = enc;
		this.interfaz = interfaz;
		conexion = new Thread(this, "nombreDelHilo");
	}
	
	public Thread getHilo() {
		return conexion;
	}
	
	public void setInstruccion(JSONObject peticion, RespuestaHilo interfaz2) {
		this.peticion = peticion;
		this.interfaz2 = interfaz2;
		this.conexion.interrupt();
	}
	
	@Override
	public void run() {

		/***********************************************************************************/
		/* Inicializa las claves de cifrado asimétrico y abre una conexión con el servidor */
		/***********************************************************************************/
		
        try {
        	
            enc.setSecureRandom(new SecureRandom());
            enc.initializeKey();
            servidor = new Socket("proyectobap.ddns.net",35698);
            salida = new ObjectOutputStream(servidor.getOutputStream());
            entrada = new ObjectInputStream(servidor.getInputStream());
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        
        /******************************************************************************/
        /* Intercambia las claves con el servidor y hace una prueba de conexión para  */
        /* comprobar la validez de las claves                                         */
        /******************************************************************************/
        try {
            
            intercambioClaves();
            enc.setCifradorAsimetrico(Cipher.getInstance("RSA/ECB/PKCS1Padding"));
            enc.setCifradorSimetrico(Cipher.getInstance("AES/GCM/NoPadding"));
            
            String check = enc.asymetricDecript((String) entrada.readObject());
            enviar(enc.asymetricEncrypt(check));
            
            if (entrada.readInt() == 206) {
                System.out.println("Comunicación OK");
                System.out.print("Recibiendo clave simetrica... ");
                String claveVolatil = (String)entrada.readObject();
                enc.setClaveSimetrica(Base64.getDecoder().decode(claveVolatil.getBytes()));
                enc.inicializacionClaveSimetrica();
                System.out.println("OK");
            } else {
                System.err.println("Comunicación falló");
                System.exit(0);
            }
            
        } catch (Exception e) {
        	// Controlar
        }
        
        /******************************************************************************/
        /* Envía al servidor las credenciales para obtener un token de conexión si    */ 
        /* fuesen validas                                                             */
        /******************************************************************************/
        
        try {
        	
            enviar(enc.symetricEncrypt(userName+","+passWord));
            
            respuestaEnc = (String) entrada.readObject();
            respuesta = new JSONObject(enc.symetricDecript(respuestaEnc));
            if (respuesta.getInt("response") == 200) {
            	login_incorrecto=false;
            	System.out.println("Login Correcto");
            	interfaz.respuesta();
            	JSONArray tipo_usuario_login = respuesta.getJSONArray("content");
            	JSONObject tipo_user = tipo_usuario_login.getJSONObject(0);
            	tipo = tipo_user.getInt("user_type");
            	id_usuario_logueado = tipo_user.getInt("user_id");
            	System.out.println("id del usuario logueado: "+id_usuario_logueado);
            	System.out.println("tipo de usuario que se a logueado: " + tipo);
            	
            }
            
            if (respuesta.getInt("response") == 400) {
            	login_incorrecto=true;
            	System.out.println("Login incorrecto");
    			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");

            	this.cerrarConexion();
            	System.exit(0);
            }
         

        } catch (Exception e) {
            // Controlar
        }
        
        /******************************************************************************/
        
        while (running) {
        	
        	try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				try {
					
					// Esta petición devuelve un JSONObject con la información requerida
					// Enviadla donde necesitéis para continuar con el programa
					
					if (peticion.getString("peticion").equalsIgnoreCase("exit")) {
						retry = 0;
					} else {
						retry = 15;
					}
					
					peticion(peticion);
					
					continue;
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
        	
        	if (retry > 0) {
        		retry--;
        	} else {
        		break;	        		
        	}
        	
        }
                
        /******************************************************************************/
        
        try {
			cerrarConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
	/******************************************************************************/
	
	public void cerrarConexion() throws Exception {
		System.out.println("Cerrando conexión...");
		servidor.close();
		salida.close();
		entrada.close();
	}
	
	/******************************************************************************/
	
	public void peticion(JSONObject peticion) throws Exception {
		
		System.out.println("paso 1");
		enviar(enc.symetricEncrypt(peticion.toString()));
		System.out.println("paso 2");
        if (peticion.getString("peticion").equalsIgnoreCase("exit")) {
            System.out.println("Desconectando...");
            this.cerrarConexion();
            System.exit(0);
        }
        
        respuestaEnc = (String) entrada.readObject();
        JSONObject rr = new JSONObject(enc.symetricDecript(respuestaEnc));
        System.out.println(rr.toString());
    	JSONArray tipo_usuario_login = rr.getJSONArray("content");
    	System.out.println("imprimir peticion "+peticion.toString());
    	if (peticion.getString("peticion").equalsIgnoreCase("newUser")){
        	JSONObject tipo_user = tipo_usuario_login.getJSONObject(0);
        	id_user = tipo_user.getInt("id");
        	System.out.println("id del nuevo user" + id_user);
    	}
    	if (peticion.getString("peticion").equalsIgnoreCase("newTicket")){
        	JSONObject tipo_user = tipo_usuario_login.getJSONObject(0);
        	id_ticket = tipo_user.getInt("id");
        	System.out.println("id del nuevo ticket" + id_ticket);
    	}
    	if (peticion.getString("peticion").equalsIgnoreCase("TECHRELATION")){
    		System.out.println("longitud del content "+ tipo_usuario_login.length());
    		if(tipo_usuario_login.length()==0) {
    			id_user_asignado=0;
    		}else {
	    		for (int i=0; i < tipo_usuario_login.length(); i++) {
	        	JSONObject tipo_user = tipo_usuario_login.getJSONObject(i);
	        	id_user_asignado = tipo_user.getInt("assigned_tech");
	    		}
    		}
    	}
    	if (peticion.getString("peticion").equalsIgnoreCase("LISTEVENTS")){
    		System.out.println("longitud del content "+ tipo_usuario_login.length());
    		if(tipo_usuario_login.length()!=0) {
    			lista_eventos=true;
    			lista_eventos_numero=1;
    		}else if(tipo_usuario_login.length()==0){
    			
    			lista_eventos=false;

    		}
    	}
  
        interfaz2.respuesta(rr);
        
        
		
	}
	
	/******************************************************************************/
	
	private void intercambioClaves() throws 
    InvalidKeySpecException, 
    NoSuchAlgorithmException, 
    IOException, 
    ClassNotFoundException {
		
		System.out.print("Enviando clave publica propia... ");
		salida.writeObject(enc.getPublicKeySpec().getModulus());
		salida.flush();
		salida.writeObject(enc.getPublicKeySpec().getPublicExponent());
		salida.flush();
		System.out.println("OK");
		
		System.out.print("Recibiendo clave publica del servidor... ");
		BigInteger modulo = (BigInteger) entrada.readObject();
		BigInteger exponente = (BigInteger) entrada.readObject();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		enc.setServerPublicKey(keyFactory.generatePublic(new RSAPublicKeySpec(modulo,exponente)));
		System.out.println("OK");
		
	}
	    
	/******************************************************************************/    
    
    private void enviar(Object mensaje) throws IOException {
        salida.writeObject(mensaje);
        salida.flush();
    }
    
    
    /******************************************************************************/
    
    

    
}
