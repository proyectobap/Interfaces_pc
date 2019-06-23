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
	private JButton salir_btn;
	private JLabel nombre_user;
	private JLabel apellido_user;


	public Userindividual(String id, String email, String nombre, String apellido, String tipo) {

		
		setBounds(100, 100, 582, 346);
		setResizable(false);
		getContentPane().setLayout(null);
		
		setNum_ticket(new JLabel("numero de ticket:"));
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
		
		nombre_user = new JLabel("titulo: ");
		nombre_user.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombre_user.setBounds(12, 37, 224, 32);
		getContentPane().add(nombre_user);
		
		apellido_user = new JLabel("fecha: ");
		apellido_user.setFont(new Font("Tahoma", Font.BOLD, 14));
		apellido_user.setBounds(281, 37, 295, 32);
		getContentPane().add(apellido_user);
		
		
		setSalir_btn(new JButton("salir"));
		getContentPane().add(getSalir_btn());
		
		this.id_user.setText(id);
		this.nombre_user.setText(nombre);
		this.user_type_text.setText(tipo);
		this.apellido_user.setText(apellido);
		this.email_user.setText(email);
		
		JButton modificar_btn = new JButton("modificar");
		modificar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		modificar_btn.setEnabled(false);
		modificar_btn.setBounds(277, 250, 123, 39);
		getContentPane().add(modificar_btn);


		inicializarComponentes();
				
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}

	public JButton getSalir_btn() {
		return salir_btn;
	}

	public void setSalir_btn(JButton salir_btn) {
		this.salir_btn = salir_btn;
		salir_btn.setBounds(410, 250, 123, 39);
		salir_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public JLabel getEstado_label() {
		return tipo_user;
	}

	public void setEstado_label(JLabel estado_label) {
		this.tipo_user = estado_label;
		estado_label.setBounds(281, 98, 132, 32);
	}


	public JLabel getDesc_label() {
		return email_user;
	}

	public void setDesc_label(JLabel desc_label) {
		this.email_user = desc_label;
		desc_label.setBounds(12, 174, 224, 32);
	}


	public JLabel getNum_ticket() {
		return id_user;
	}

	public void setNum_ticket(JLabel num_ticket) {
		this.id_user = num_ticket;
		num_ticket.setBounds(12, 98, 224, 32);
	}

	public JTextField getText_estado() {
		return user_type_text;
	}

	public void setText_estado(JTextField text_estado) {
		this.user_type_text = text_estado;
		text_estado.setBounds(410, 106, 123, 20);
		text_estado.setEditable(false);
	}
}
