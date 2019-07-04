package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import controlador.ClienteTFG;
import controlador.ClienteTFG2;
import controlador.ManejadorEventos;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Miperfil extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	public JSONObject prueba;
	private JTextField nombre_text;
	private JTextField apellido_text;
	private JTextField email_text;
	private int tipo_usuario;
	public String nombre;
	public String apellido;
	public String email;
	public int id;
	public String user_type;
	private JTextField user_type_text;
	private JLabel modificar_perfil_label;
	private JLabel cambiar_passLabel;
	


	public Miperfil() {
		datosmiperfil();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(445, 601);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal2 ventanaprincipal = new VentanaPrincipal2();
				ventanaprincipal.setVisible(true);
				dispose();
			}
		});
		atras_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		atras_btn.setBounds(219, 506, 156, 36);
		getContentPane().add(atras_btn);
		
		JLabel nombre_label = new JLabel("Nombre:");
		nombre_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombre_label.setBounds(120, 195, 61, 20);
		getContentPane().add(nombre_label);
		
		JLabel apellido_label = new JLabel("Apellido:");
		apellido_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		apellido_label.setBounds(122, 243, 59, 20);
		getContentPane().add(apellido_label);
		
		JLabel email_label = new JLabel("Email:");
		email_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		email_label.setBounds(140, 297, 41, 20);
		getContentPane().add(email_label);
		
		JLabel user_type_label = new JLabel("Tipo de Usuario:");
		user_type_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		user_type_label.setBounds(69, 351, 112, 20);
		getContentPane().add(user_type_label);
		
		setNombre_text(new JTextField(nombre));
		getNombre_text().setBounds(219, 197, 156, 20);
		getNombre_text().setEditable(false);
		getContentPane().add(getNombre_text());
		getNombre_text().setColumns(10);
		
		setApellido_text(new JTextField(apellido));
		getApellido_text().setBounds(219, 245, 156, 20);
		getApellido_text().setEditable(false);
		getContentPane().add(getApellido_text());
		getApellido_text().setColumns(10);
		
		setEmail_text(new JTextField(email));
		getEmail_text().setBounds(219, 299, 156, 20);
		getEmail_text().setEditable(false);
		getContentPane().add(getEmail_text());
		getEmail_text().setColumns(10);
		
		JLabel tituloventana = new JLabel("Mi Perfil");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 23));
		tituloventana.setBounds(180, 92, 97, 28);
		getContentPane().add(tituloventana);
		
		user_type_text = new JTextField(user_type);
		user_type_text.setBounds(219, 353, 156, 20);
		user_type_text.setEditable(false);
		getContentPane().add(user_type_text);
		user_type_text.setColumns(10);
		
		modificar_perfil_label = new JLabel("Modificar mi perfil");
		modificar_perfil_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modificar_perfil_label.setBounds(247, 424, 128, 21);
		modificar_perfil_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CambiarMiPerfil cambiarmiperfil = new CambiarMiPerfil();
				cambiarmiperfil.setVisible(true);
				dispose();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				modificar_perfil_label.setForeground(Color.blue);
				Font font = modificar_perfil_label.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				modificar_perfil_label.setFont(font.deriveFont(attributes));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				modificar_perfil_label.setForeground(Color.black);
				modificar_perfil_label.setFont(new Font("Tahoma", Font.PLAIN, 16));					
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		getContentPane().add(modificar_perfil_label);
		
		cambiar_passLabel = new JLabel("Cambiar mi contrase\u00F1a");
		cambiar_passLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cambiar_passLabel.setBounds(212, 456, 163, 21);
		cambiar_passLabel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				CambiarMiPass cambiarmipass= new CambiarMiPass();
				cambiarmipass.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				cambiar_passLabel.setForeground(Color.blue);
				Font font = cambiar_passLabel.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				cambiar_passLabel.setFont(font.deriveFont(attributes));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				cambiar_passLabel.setForeground(Color.black);
				cambiar_passLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		
		getContentPane().add(cambiar_passLabel);
		
		inicializarComponentes();
		

		
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}
	
	//peticion para cambiar el perfil del usuario logueado
    public JSONObject cambiarMiPerfil(String email, String name, String apellido, int user_type) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYOWNUSER");
    	prueba.put("email", email);
    	prueba.put("name", name);
    	prueba.put("last_name", apellido);
    	prueba.put("user_type", user_type);

    	return prueba;
    }



	public JTextField getEmail_text() {
		return email_text;
	}

	public void setEmail_text(JTextField email_text) {
		this.email_text = email_text;
	}

	public JTextField getApellido_text() {
		return apellido_text;
	}

	public void setApellido_text(JTextField apellido_text) {
		this.apellido_text = apellido_text;
	}

	public JTextField getNombre_text() {
		return nombre_text;
	}

	public void setNombre_text(JTextField nombre_text) {
		this.nombre_text = nombre_text;
	}
	
	public void datosmiperfil() {
		JSONObject pregunta = new JSONObject().put("peticion", "listusers");
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject objc = (JSONObject) array.get(i);
			Object obj[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), objc.getInt("user_type")};//filas de la tabla
			if (objc.getInt("user_id")==ClienteTFG2.id_usuario_logueado) {
				nombre=objc.getString("name");
				apellido=objc.getString("last_name");
				email=objc.getString("email");
				id=objc.getInt("user_id");
				tipo_usuario=objc.getInt("user_type");
				if (tipo_usuario==1) {
					user_type="Usuario sin Login";
				}else if(tipo_usuario==2){
					user_type="Usuario con Login";
				}else if(tipo_usuario==3){
					user_type="Técnico";
				}else if(tipo_usuario==4){
					user_type="Supervisor";
				}else if(tipo_usuario==5){
					user_type="Administrador";
				}
				
			}
					
		}
	}
}
