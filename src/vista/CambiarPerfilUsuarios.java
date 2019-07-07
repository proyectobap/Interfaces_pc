package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import controlador.ClienteTFG2;
import controlador.ManejadorEventos;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CambiarPerfilUsuarios extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	public JSONObject prueba;
	private JTextField nombre_text;
	private JTextField apellido_text;
	private JTextField email_text;
	private JComboBox comboBox;
	private int tipo_usuario;
	private JTextField id_text;
	private JLabel atras_icon_label;
	


	public CambiarPerfilUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(452, 521);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton cambiar_perfil_users = new JButton("Cambiar");
		cambiar_perfil_users.addActionListener(new ActionListener() {
			//cambia los datos del perfil del usuario
			public void actionPerformed(ActionEvent e) {
				if (getComboBox().getSelectedItem().toString()=="Administrador") {
					tipo_usuario=5;
				}else if(getComboBox().getSelectedItem().toString()=="Supervisor") {
					tipo_usuario=4;
				}else if(getComboBox().getSelectedItem().toString()=="Técnico") {
					tipo_usuario=3;
				}else if(getComboBox().getSelectedItem().toString()=="Usuario con Login") {
					tipo_usuario=2;
				}else if(getComboBox().getSelectedItem().toString()=="Usuario sin Login") {
					tipo_usuario=1;
				}
				JSONObject peticion=cambiarPerfilUsuario(getEmail_text().getText(), getNombre_text().getText(), getApellido_text().getText(), 
						tipo_usuario, Integer.parseInt(id_text.getText()));
				
				JSONObject pregunta = peticion;
				System.out.println(pregunta);
				
				HiloPeticiones hilo = new HiloPeticiones(pregunta);
				hilo.start();
				try {
					hilo.join();
				} catch (InterruptedException d) {
					d.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Perfil modificado con exito");
				VentanaPrincipal2 ventanaPrincipal= new VentanaPrincipal2();
				ventanaPrincipal.setVisible(true);
				dispose();
			}
		});
		cambiar_perfil_users.setFont(new Font("Tahoma", Font.BOLD, 14));
		cambiar_perfil_users.setBounds(227, 446, 156, 36);
		getContentPane().add(cambiar_perfil_users);
		
		JLabel nombre_label = new JLabel("Nombre:");
		nombre_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombre_label.setBounds(125, 190, 61, 20);
		getContentPane().add(nombre_label);
		
		JLabel apellido_label = new JLabel("Apellido:");
		apellido_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		apellido_label.setBounds(127, 244, 59, 20);
		getContentPane().add(apellido_label);
		
		JLabel email_label = new JLabel("Email:");
		email_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		email_label.setBounds(145, 294, 41, 20);
		getContentPane().add(email_label);
		
		JLabel user_type_label = new JLabel("Tipo de Usuario:");
		user_type_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		user_type_label.setBounds(74, 343, 112, 20);
		getContentPane().add(user_type_label);
		
		setNombre_text(new JTextField(String.valueOf(TablaListaUsers.nombre)));
		getNombre_text().setBounds(227, 192, 156, 20);
		getContentPane().add(getNombre_text());
		getNombre_text().setColumns(10);
		
		setApellido_text(new JTextField(String.valueOf(TablaListaUsers.apellido)));
		getApellido_text().setBounds(227, 246, 156, 20);
		getContentPane().add(getApellido_text());
		getApellido_text().setColumns(10);
		
		setEmail_text(new JTextField(String.valueOf(TablaListaUsers.email)));
		getEmail_text().setBounds(227, 296, 156, 20);
		getContentPane().add(getEmail_text());
		getEmail_text().setColumns(10);
		
		setComboBox(new JComboBox());
		getComboBox().setBounds(227, 345, 156, 20);
		
		getContentPane().add(getComboBox());
		getComboBox().addItem("Usuario sin Login");
		getComboBox().addItem("Usuario con Login");
		getComboBox().addItem("Técnico");
		getComboBox().addItem("Supervisor");
		getComboBox().addItem("Administrador");
		
		getComboBox().setSelectedItem(TablaListaUsers.tipo_user);//selecciona por defecto el tipo de usuario que es.
		
		JLabel id_user_label = new JLabel("Id del usuario:");
		id_user_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		id_user_label.setBounds(86, 389, 100, 28);
		getContentPane().add(id_user_label);
		
		id_text = new JTextField(String.valueOf(TablaListaUsers.user_id));
		id_text.setBounds(227, 395, 156, 20);
		getContentPane().add(id_text);
		id_text.setColumns(10);
		
		JLabel tituloventana = new JLabel("Modificar Perfil");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 28));
		tituloventana.setBounds(119, 69, 223, 56);
		getContentPane().add(tituloventana);
		
		
		//nos devuelve a la ventana anterior
		Image imagen_salir=new ImageIcon("salir2.png").getImage();
		atras_icon_label = new JLabel(new ImageIcon(imagen_salir.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		atras_icon_label.setBounds(10, 11, 53, 52);
		atras_icon_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				Userindividual userindividual = new Userindividual(TablaListaUsers.user_id, TablaListaUsers.email, 
						TablaListaUsers.nombre, TablaListaUsers.apellido, TablaListaUsers.tipo_user);
				if (ClienteTFG2.tipo>4) {//si el usuario logueado es un Administrador el boton modificar se activara
					System.out.println(ClienteTFG2.tipo);
					userindividual.modificar_btn.setEnabled(true);
				}
				userindividual.setVisible(true);
				dispose();
			
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		getContentPane().add(atras_icon_label);
		
		inicializarComponentes();
		

		
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}
	
	//peticion para cambiar el perfil de cualquier usuario (solo si eres admin)
    public JSONObject cambiarPerfilUsuario(String email, String name, String apellido, int user_type, int user_id) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYUSER");
    	prueba.put("email", email);
    	prueba.put("name", name);
    	prueba.put("last_name", apellido);
    	prueba.put("user_type", user_type);
    	prueba.put("user_id", user_id);

    	return prueba;
    }

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
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
}
