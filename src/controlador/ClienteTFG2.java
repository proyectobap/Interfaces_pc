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
		/* Inicializa las claves de cifrado asim�trico y abre una conexi�n con el servidor */
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
        /* Intercambia las claves con el servidor y hace una prueba de conexi�n para  */
        /* comprobar la validez de las claves                                         */
        /******************************************************************************/
        try {
            
            intercambioClaves();
            enc.setCifradorAsimetrico(Cipher.getInstance("RSA/ECB/PKCS1Padding"));
            enc.setCifradorSimetrico(Cipher.getInstance("AES/GCM/NoPadding"));
            
            String check = enc.asymetricDecript((String) entrada.readObject());
            enviar(enc.asymetricEncrypt(check));
            
            if (entrada.readInt() == 206) {
                System.out.println("Comunicaci�n OK");
                System.out.print("Recibiendo clave simetrica... ");
                String claveVolatil = (String)entrada.readObject();
                enc.setClaveSimetrica(Base64.getDecoder().decode(claveVolatil.getBytes()));
                enc.inicializacionClaveSimetrica();
                System.out.println("OK");
            } else {
                System.err.println("Comunicaci�n fall�");
                System.exit(0);
            }
            
        } catch (Exception e) {
        	// Controlar
        }
        
        /******************************************************************************/
        /* Env�a al servidor las credenciales para obtener un token de conexi�n si    */ 
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
            	tipo = tipo_user.getInt("id");
            	System.out.println(tipo);
            	
            }
            
            if (respuesta.getInt("response") == 400) {
            	login_incorrecto=true;
            	System.out.println("Login incorrecto");
    			JOptionPane.showMessageDialog(null, "Usuario o contrase�a incorrectos");

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
					
					// Esta petici�n devuelve un JSONObject con la informaci�n requerida
					// Enviadla donde necesit�is para continuar con el programa
					
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
		System.out.println("Cerrando conexi�n...");
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
        interfaz2.respuesta(rr);
        
        /*System.out.println(respuesta.getInt("response"));
        
        content = new JSONArray();
        content = respuesta.getJSONArray("content");
        
        for (int i = 0; i < content.length(); i++) {
        	System.out.println(content.getJSONObject(i).toString());
        }*/
		
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
