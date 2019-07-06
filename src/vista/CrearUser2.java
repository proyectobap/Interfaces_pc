package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import controlador.ClienteTFG2;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class CrearUser2 extends JFrame {

	private JPanel contentPane;
	private JTextField text_nombre;
	private JTextField text_lastname;
	private JTextField text_email;
	private JButton btnCrearUser;
	private JComboBox comboBox_usertype;
	int tipo_usuario;
	Border border = BorderFactory.createLineBorder(Color.BLUE);
	private JLabel lblCrearUsuario;
	private JLabel atras_icon_label;



	
	public CrearUser2() {
	
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setResizable(false);
		setSize(439, 477);
		setUndecorated(true);//quitar bordes
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(0, 191, 255));
		contentPane.setLayout(null);
		
		text_nombre = new JTextField();
		text_nombre.setBorder(border);
		text_nombre.setBounds(173, 167, 214, 20);
		contentPane.add(text_nombre);
		text_nombre.setColumns(10);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		nombre.setBounds(79, 169, 56, 14);
		contentPane.add(nombre);
		
		JLabel apellido = new JLabel("Apellido:");
		apellido.setFont(new Font("Tahoma", Font.BOLD, 13));
		apellido.setBounds(79, 220, 56, 14);
		contentPane.add(apellido);
		
		JLabel email = new JLabel("Email:");
		email.setFont(new Font("Tahoma", Font.BOLD, 13));
		email.setBounds(98, 274, 37, 14);
		contentPane.add(email);
		
		text_lastname =new JTextField();
		text_lastname.setBorder(border);
		text_lastname.setBounds(173, 218, 214, 20);
		contentPane.add(text_lastname);
		text_lastname.setColumns(10);
		
		text_email = new JTextField();
		text_email.setBorder(border);
		text_email.setBounds(173, 272, 214, 20);
		contentPane.add(text_email);
		text_email.setColumns(10);
		
		setBtnCrearTicket(new JButton("Crear Usuario"));
		getBtnCrearTicket().setFont(new Font("Tahoma", Font.BOLD, 14));
		getBtnCrearTicket().setBounds(173, 407, 214, 37);
		btnCrearUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (getComboBox_usertype().getSelectedItem().toString()=="Administrador") {
					tipo_usuario=5;
				}else if(getComboBox_usertype().getSelectedItem().toString()=="Supervisor") {
					tipo_usuario=4;
				}else if(getComboBox_usertype().getSelectedItem().toString()=="Técnico") {
					tipo_usuario=3;
				}else if(getComboBox_usertype().getSelectedItem().toString()=="Usuario con Login") {
					tipo_usuario=2;
				}
				
				JSONObject peticion=crearUser(getText_email().getText(), getText_nombre().getText(), getText_lastname().getText(), tipo_usuario);
				
				JSONObject pregunta = peticion;
				System.out.println(pregunta);
				
				HiloPeticiones hilo = new HiloPeticiones(pregunta);
				hilo.start();
				try {
					hilo.join();
				} catch (InterruptedException d) {
					d.printStackTrace();
				}
				
				Sleep(2000);
				System.out.println("id que le paso al usuario nuevo " + ClienteTFG2.id_user);
				JSONObject peticion2=crearLogin(getText_nombre().getText(), getText_nombre().getText(), ClienteTFG2.id_user);
				
				JSONObject pregunta2 = peticion2;
				System.out.println(pregunta2);
				
				HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
				hilo2.start();
				try {
					hilo2.join();
				} catch (InterruptedException d) {
					d.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "user y login creado con exito");
				TablaListaUsers tabla_users= new TablaListaUsers();
				tabla_users.setVisible(true);
				dispose();
				
				if(getComboBox_usertype().getSelectedItem().toString()=="Usuario sin Login") {
					tipo_usuario=1;
					
					JSONObject peticion_sinlogin=crearUser(getText_email().getText(), getText_nombre().getText(), getText_lastname().getText(), tipo_usuario);
					
					JSONObject pregunta_sinlogin = peticion_sinlogin;
					System.out.println(pregunta_sinlogin);
					
					HiloPeticiones hilo_sinlogin = new HiloPeticiones(pregunta_sinlogin);
					hilo_sinlogin.start();
					try {
						hilo_sinlogin.join();
					} catch (InterruptedException d) {
						d.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "user creado con exito");
					TablaListaUsers tabla_users2= new TablaListaUsers();
					tabla_users2.setVisible(true);
					dispose();
				}

				
			}

			private void Sleep(int i) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		contentPane.add(getBtnCrearTicket());
		
		JLabel lblEstado = new JLabel("Tipo de Usuario:");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(32, 330, 103, 14);
		contentPane.add(lblEstado);
		
		setComboBox_usertype(new JComboBox());
		getComboBox_usertype().setBounds(173, 328, 214, 20);
		getComboBox_usertype().addItem("Usuario sin Login");
		getComboBox_usertype().addItem("Usuario con Login");
		getComboBox_usertype().addItem("Técnico");
		getComboBox_usertype().addItem("Supervisor");
		getComboBox_usertype().addItem("Administrador");
		contentPane.add(getComboBox_usertype());
		
		lblCrearUsuario = new JLabel("Crear Usuario");
		lblCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblCrearUsuario.setBounds(136, 59, 186, 48);
		contentPane.add(lblCrearUsuario);
		
		Image imagen_salir=new ImageIcon("salir2.png").getImage();
		atras_icon_label = new JLabel(new ImageIcon(imagen_salir.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		atras_icon_label.setBounds(10, 11, 53, 52);
		atras_icon_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				TablaListaUsers tabla_users= new TablaListaUsers();
				tabla_users.setVisible(true);
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
	}
	

	public JButton getBtnCrearTicket() {
		return btnCrearUser;
	}




	public void setBtnCrearTicket(JButton btnCrearTicket) {
		this.btnCrearUser = btnCrearTicket;
	}
	
	public JTextField getText_nombre() {
		return text_nombre;
	}




	public void setText_nombre(JTextField text_nombre) {
		this.text_nombre = text_nombre;
	}




	public JTextField getText_lastname() {
		return text_lastname;
	}




	public void setText_lastname(JTextField text_lastname) {
		this.text_lastname = text_lastname;
	}




	public JTextField getText_email() {
		return text_email;
	}




	public void setText_email(JTextField text_email) {
		this.text_email = text_email;
	}


	
    public JSONObject crearUser(String email, String name, String lastname, int user_type) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","newUser");
    	prueba.put("email", email);
    	prueba.put("name", name);
    	prueba.put("lastname", lastname);
    	prueba.put("user_type", user_type);
    	
    	
    	return prueba;
    	
    }
    
    //crear el login del usuario que vamos a crear
    public JSONObject crearLogin(String loginname, String shdw_password, int user_id) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","NEWLOGIN");
    	prueba.put("login_name", loginname);
    	prueba.put("shdw_passwd", shdw_password);
    	prueba.put("user_id", user_id);

    	
    	
    	return prueba;
    	
    }




	public void setText_descripcion(JTextArea text_descripcion) {
	}




	public JComboBox getComboBox_usertype() {
		return comboBox_usertype;
	}




	public void setComboBox_usertype(JComboBox comboBox_usertype) {
		this.comboBox_usertype = comboBox_usertype;
	}
}
