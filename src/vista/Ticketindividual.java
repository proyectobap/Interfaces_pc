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

public class Ticketindividual extends JFrame {

	private JTextField text_estado;
	private JLabel num_ticket;
	private JLabel desc_label;
	private JLabel estado_label;
	private JButton salir_btn;
	private JLabel titulo_ticket_2;
	private JTextArea area_desc_2;
	private JLabel fecha_creacion;
	public JButton modificar_btn;


	public Ticketindividual(String numticket, String titulo_ticket, String area_desc, String estado, String fecha) {

		
		setBounds(100, 100, 686, 461);
		setResizable(false);
		getContentPane().setLayout(null);
		
		setNum_ticket(new JLabel("numero de ticket:"));
		getNum_ticket().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getNum_ticket());
		
		
		setDesc_label(new JLabel("descripcion:"));
		getDesc_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getDesc_label());

		
		setEstado_label(new JLabel("Estado:"));
		getEstado_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getEstado_label());
		
		setText_estado(new JTextField());
		getText_estado().setColumns(10);
		getContentPane().add(getText_estado());
		
		titulo_ticket_2 = new JLabel("titulo: ");
		titulo_ticket_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo_ticket_2.setBounds(12, 37, 224, 32);
		getContentPane().add(titulo_ticket_2);
		
		
		area_desc_2 = new JTextArea();
		area_desc_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		area_desc_2.setEditable(false);
		area_desc_2.setBounds(135, 180, 505, 72);
		getContentPane().add(area_desc_2);
		
		fecha_creacion = new JLabel("fecha: ");
		fecha_creacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		fecha_creacion.setBounds(416, 37, 224, 32);
		getContentPane().add(fecha_creacion);
		
		
		setSalir_btn(new JButton("salir"));
		getContentPane().add(getSalir_btn());
		
		this.num_ticket.setText(numticket);
		this.titulo_ticket_2.setText(titulo_ticket);
		this.area_desc_2.setText(area_desc);
		this.text_estado.setText(estado);
		this.fecha_creacion.setText(fecha);
		
		modificar_btn = new JButton("modificar");
		modificar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		modificar_btn.setEnabled(false);
		modificar_btn.setBounds(517, 291, 123, 39);
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
		salir_btn.setBounds(517, 347, 123, 39);
		salir_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public JLabel getEstado_label() {
		return estado_label;
	}

	public void setEstado_label(JLabel estado_label) {
		this.estado_label = estado_label;
		estado_label.setBounds(416, 98, 61, 32);
	}


	public JLabel getDesc_label() {
		return desc_label;
	}

	public void setDesc_label(JLabel desc_label) {
		this.desc_label = desc_label;
		desc_label.setBounds(12, 174, 224, 32);
	}


	public JLabel getNum_ticket() {
		return num_ticket;
	}

	public void setNum_ticket(JLabel num_ticket) {
		this.num_ticket = num_ticket;
		num_ticket.setBounds(12, 98, 224, 32);
	}

	public JTextField getText_estado() {
		return text_estado;
	}

	public void setText_estado(JTextField text_estado) {
		this.text_estado = text_estado;
		text_estado.setBounds(517, 106, 123, 20);
		text_estado.setEditable(false);
	}
}

