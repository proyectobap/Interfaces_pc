package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import controlador.ClienteTFG;
import controlador.ClienteTFG2;
import controlador.ManejadorEventos;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Userindividual extends JFrame {

	private JTextField user_type_text;
	private JLabel id_user;
	private JLabel email_user;
	private JLabel tipo_user;
	private JLabel nombre_user;
	private JLabel apellido_user;
	public JButton modificar_btn;
	private JTextField nombre_text;
	private JTextField apellido_text;
	private JTextField id_text;
	private JTextField email_text;
	private JLabel tituloventana;


	public Userindividual(String id, String email, String nombre, String apellido, String tipo) {

		
		setSize(462, 588);//tamaño ventana
		setUndecorated(true);//quitar bordes
		setLocationRelativeTo(null); //setbounds(x, y, w, h) para las dos primeras
		setResizable(false);
		getContentPane().setLayout(null);
		
		setNum_ticket(new JLabel("Id del usuario:"));
		getNum_ticket().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getNum_ticket());
		
		
		setDesc_label(new JLabel("email: "));
		getDesc_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getDesc_label());

		
		setEstado_label(new JLabel("Tipo de Usuario:"));
		getEstado_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getEstado_label());
		
		setText_estado(new JTextField());
		getText_estado().setColumns(10);
		getContentPane().add(getText_estado());
		
		nombre_user = new JLabel("Nombre:");
		nombre_user.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombre_user.setBounds(99, 184, 94, 32);
		getContentPane().add(nombre_user);
		
		apellido_user = new JLabel("Apellido:");
		apellido_user.setFont(new Font("Tahoma", Font.BOLD, 14));
		apellido_user.setBounds(99, 227, 111, 32);
		getContentPane().add(apellido_user);
		
		nombre_text = new JTextField();
		nombre_text.setBounds(215, 192, 190, 20);
		nombre_text.setEditable(false);
		getContentPane().add(nombre_text);
		nombre_text.setColumns(10);
	
	
		apellido_text = new JTextField();
		apellido_text.setBounds(215, 235, 190, 20);
		apellido_text.setEditable(false);
		getContentPane().add(apellido_text);
		apellido_text.setColumns(10);
	
	
		id_text = new JTextField();
		id_text.setBounds(215, 325, 190, 20);
		id_text.setEditable(false);
		getContentPane().add(id_text);
		id_text.setColumns(10);
	
	
		email_text = new JTextField();
		email_text.setBounds(215, 368, 190, 20);
		email_text.setEditable(false);
		getContentPane().add(email_text);
		email_text.setColumns(10);
		
		this.id_text.setText(id);
		this.nombre_text.setText(nombre);
		this.user_type_text.setText(tipo);
		this.apellido_text.setText(apellido);
		this.email_text.setText(email);
		

		
		modificar_btn = new JButton("modificar");
		modificar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CambiarPerfilUsuarios cambiarperfil= new CambiarPerfilUsuarios();
				cambiarperfil.setVisible(true);
				dispose();
			}
		});
		modificar_btn.setEnabled(false);
		modificar_btn.setBounds(215, 411, 190, 39);
		getContentPane().add(modificar_btn);
		
		JButton atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TablaListaUsers tablalistausers=new TablaListaUsers();
				tablalistausers.setVisible(true);
				dispose();
			}
		});
		atras_btn.setBounds(215, 465, 190, 39);
		getContentPane().add(atras_btn);
		
		tituloventana = new JLabel("Perfil de Usuario");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 23));
		tituloventana.setBounds(136, 88, 202, 39);
		getContentPane().add(tituloventana);
		

		


		inicializarComponentes();
				
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}


	public JLabel getEstado_label() {
		return tipo_user;
	}

	public void setEstado_label(JLabel estado_label) {
		this.tipo_user = estado_label;
		estado_label.setBounds(43, 270, 132, 32);
	}


	public JLabel getDesc_label() {
		return email_user;
	}

	public void setDesc_label(JLabel desc_label) {
		this.email_user = desc_label;
		desc_label.setBounds(116, 360, 94, 32);
	}


	public JLabel getNum_ticket() {
		return id_user;
	}

	public void setNum_ticket(JLabel num_ticket) {
		this.id_user = num_ticket;
		num_ticket.setBounds(55, 317, 155, 32);
	}

	public JTextField getText_estado() {
		return user_type_text;
	}
	

	public JTextField getNombre_text() {
		return nombre_text;
	}

	public void setNombre_text(JTextField nombre_text) {
		this.nombre_text = nombre_text;
		

	}

	public JTextField getApellido_text() {
		return apellido_text;
	}

	public void setApellido_text(JTextField apellido_text) {
		this.apellido_text = apellido_text;
		
	}

	public JTextField getId_text() {
		return id_text;
	}

	public void setId_text(JTextField id_text) {
		this.id_text = id_text;
		

	}

	public JTextField getEmail_text() {
		return email_text;
	}

	public void setEmail_text(JTextField email_text) {
		this.email_text = email_text;
		

	}

	public void setText_estado(JTextField text_estado) {
		this.user_type_text = text_estado;
		text_estado.setBounds(215, 278, 190, 20);
		text_estado.setEditable(false);
	}
}
