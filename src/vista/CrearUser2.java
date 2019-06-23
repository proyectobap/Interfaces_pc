package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import controlador.ClienteTFG2;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
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
	private JButton atras_btn;



	
	public CrearUser2() {
		setResizable(false);
		setBounds(100, 100, 411, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(0, 191, 255));
		contentPane.setLayout(null);
		
		text_nombre = new RedondearCampos(80);
		text_nombre.setBorder(border);
		text_nombre.setBounds(132, 52, 214, 20);
		contentPane.add(text_nombre);
		text_nombre.setColumns(10);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		nombre.setBounds(32, 54, 76, 14);
		contentPane.add(nombre);
		
		JLabel apellido = new JLabel("Apellido");
		apellido.setFont(new Font("Tahoma", Font.BOLD, 13));
		apellido.setBounds(32, 103, 63, 14);
		contentPane.add(apellido);
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Tahoma", Font.BOLD, 13));
		email.setBounds(32, 163, 46, 14);
		contentPane.add(email);
		
		text_lastname =new RedondearCampos(80);
		text_lastname.setBorder(border);
		text_lastname.setBounds(132, 101, 214, 20);
		contentPane.add(text_lastname);
		text_lastname.setColumns(10);
		
		text_email = new RedondearCampos(80);
		//text_email.setBorder(border);
		text_email.setBounds(132, 161, 214, 20);
		contentPane.add(text_email);
		text_email.setColumns(10);
		
		setBtnCrearTicket(new JButton("Crear Usuario"));
		getBtnCrearTicket().setFont(new Font("Tahoma", Font.BOLD, 14));
		getBtnCrearTicket().setBounds(132, 291, 214, 37);
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
				}else if(getComboBox_usertype().getSelectedItem().toString()=="Usuario sin Login") {
					tipo_usuario=1;
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
				JOptionPane.showMessageDialog(null, "user creado con exito");
				dispose();
				
			}
			
		});
		contentPane.add(getBtnCrearTicket());
		
		JLabel lblEstado = new JLabel("User_type");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(32, 223, 76, 14);
		contentPane.add(lblEstado);
		
		setComboBox_usertype(new JComboBox());
		getComboBox_usertype().setBounds(132, 221, 214, 20);
		getComboBox_usertype().addItem("Usuario sin Login");
		getComboBox_usertype().addItem("Usuario con Login");
		getComboBox_usertype().addItem("Técnico");
		getComboBox_usertype().addItem("Supervisor");
		getComboBox_usertype().addItem("Administrador");
		contentPane.add(getComboBox_usertype());
		
		atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaListaUsers tabla_users= new TablaListaUsers();
				tabla_users.setVisible(true);
				dispose();
			}
		});
		atras_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		atras_btn.setBounds(132, 359, 214, 37);
		contentPane.add(atras_btn);
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




	public void setText_descripcion(JTextArea text_descripcion) {
	}




	public JComboBox getComboBox_usertype() {
		return comboBox_usertype;
	}




	public void setComboBox_usertype(JComboBox comboBox_usertype) {
		this.comboBox_usertype = comboBox_usertype;
	}
}
