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

public class CreaTickets extends JFrame {

	private JPanel contentPane;
	private JTextField text_nombre;
	private JTextField text_asunto;
	private JTextField text_cliente;
	private JButton btnCrearTicket;
	private JTextArea text_descripcion;
	private JComboBox comboBox_estado_ticket;
	int estado_ticket;
	Border border = BorderFactory.createLineBorder(Color.BLUE);
	private JButton atras_btn;



	
	public CreaTickets() {
		setResizable(false);
		setBounds(100, 100, 829, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(0, 191, 255));
		contentPane.setLayout(null);
		
		text_nombre = new RedondearCampos(80);
		text_nombre.setBorder(border);
		text_nombre.setBounds(132, 52, 172, 20);
		contentPane.add(text_nombre);
		text_nombre.setColumns(10);
		
		JLabel creador_ticket = new JLabel("Nombre");
		creador_ticket.setFont(new Font("Tahoma", Font.BOLD, 13));
		creador_ticket.setBounds(32, 54, 76, 14);
		contentPane.add(creador_ticket);
		
		JLabel asunto = new JLabel("Asunto");
		asunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		asunto.setBounds(363, 54, 63, 14);
		contentPane.add(asunto);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setFont(new Font("Tahoma", Font.BOLD, 13));
		descripcion.setBounds(32, 179, 95, 14);
		contentPane.add(descripcion);
		
		JLabel cliente = new JLabel("Cliente");
		cliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		cliente.setBounds(363, 108, 46, 14);
		contentPane.add(cliente);
		
		text_asunto =new RedondearCampos(80);
		text_asunto.setBorder(border);
		text_asunto.setBounds(456, 52, 214, 20);
		contentPane.add(text_asunto);
		text_asunto.setColumns(10);
		
		text_cliente = new RedondearCampos(80);
		text_cliente.setBorder(border);
		text_cliente.setBounds(456, 106, 214, 20);
		contentPane.add(text_cliente);
		text_cliente.setColumns(10);
		
		setText_descripcion(new JTextArea());
		getText_descripcion().setBorder(border);
		getText_descripcion().setLineWrap(true);
		getText_descripcion().setEditable(true);
		getText_descripcion().setBounds(132, 176, 538, 70);
		contentPane.add(getText_descripcion());
		
		setBtnCrearTicket(new JButton("Crear ticket"));
		getBtnCrearTicket().setFont(new Font("Tahoma", Font.BOLD, 13));
		getBtnCrearTicket().setBounds(503, 294, 167, 37);
		btnCrearTicket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {//cuando pulsamos al boton llamamos a la peticion para crear el ticket y crearlo.
				
				if (getComboBox_estado_ticket().getSelectedItem().toString()=="Abierto") {
					estado_ticket=1;
				}else if(getComboBox_estado_ticket().getSelectedItem().toString()=="Asignado") {
					estado_ticket=2;
				}else if(getComboBox_estado_ticket().getSelectedItem().toString()=="En espera (terceros)") {
					estado_ticket=3;
				}else if(getComboBox_estado_ticket().getSelectedItem().toString()=="En espera (Cliente)") {
					estado_ticket=4;
				}else if(getComboBox_estado_ticket().getSelectedItem().toString()=="Solucionado") {
					estado_ticket=5;
				}else if(getComboBox_estado_ticket().getSelectedItem().toString()=="cerrado") {
					estado_ticket=6;
				}
				
				JSONObject peticion=crearTicket(getText_nombre().getText(), getText_descripcion().getText(), estado_ticket);
				
				JSONObject pregunta = peticion;
				System.out.println(pregunta);
				
				HiloPeticiones hilo = new HiloPeticiones(pregunta);
				hilo.start();
				try {
					hilo.join();
				} catch (InterruptedException d) {
					d.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "ticket creado con exito");
				VentanaPrincipal2 ventanaprincipal= new VentanaPrincipal2();
				ventanaprincipal.setVisible(true);
				dispose();
				
			}
			
		});
		contentPane.add(getBtnCrearTicket());
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(32, 108, 46, 14);
		contentPane.add(lblEstado);
		
		setComboBox_estado_ticket(new JComboBox());
		getComboBox_estado_ticket().setBounds(132, 106, 172, 20);
		getComboBox_estado_ticket().addItem("Abierto");
		getComboBox_estado_ticket().addItem("Asignado");
		getComboBox_estado_ticket().addItem("En espera (terceros)");
		getComboBox_estado_ticket().addItem("En espera (Cliente)");
		getComboBox_estado_ticket().addItem("Solucionado");
		getComboBox_estado_ticket().addItem("cerrado");
		contentPane.add(getComboBox_estado_ticket());
		
		atras_btn = new JButton("Atras");
		atras_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal2 ventanaprincipal= new VentanaPrincipal2();
				ventanaprincipal.setVisible(true);
				dispose();
			}
		});
		atras_btn.setBounds(132, 294, 167, 35);
		contentPane.add(atras_btn);
	}




	public JButton getBtnCrearTicket() {
		return btnCrearTicket;
	}




	public void setBtnCrearTicket(JButton btnCrearTicket) {
		this.btnCrearTicket = btnCrearTicket;
	}
	
	public JTextField getText_nombre() {
		return text_nombre;
	}




	public void setText_nombre(JTextField text_nombre) {
		this.text_nombre = text_nombre;
	}




	public JTextField getText_asunto() {
		return text_asunto;
	}




	public void setText_asunto(JTextField text_asunto) {
		this.text_asunto = text_asunto;
	}




	public JTextField getText_cliente() {
		return text_cliente;
	}




	public void setText_cliente(JTextField text_cliente) {
		this.text_cliente = text_cliente;
	}


	//peticion para poder crear el ticket
    public JSONObject crearTicket(String titulo, String descripcion, int status_ticket) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","newTicket");
    	prueba.put("title", titulo);
    	prueba.put("desc", descripcion);
    	prueba.put("ticket_status_id", status_ticket);
    	prueba.put("ticket_owner", 2);
    	prueba.put("ticket_object", 1);
    	
    	return prueba;
    	
    }




	public JTextArea getText_descripcion() {
		return text_descripcion;
	}




	public void setText_descripcion(JTextArea text_descripcion) {
		this.text_descripcion = text_descripcion;
		text_descripcion.setWrapStyleWord(true);
		text_descripcion.setFont(new Font("Monospaced", Font.PLAIN, 13));
		text_descripcion.setToolTipText("breve descripci\u00F3n");
	}




	public JComboBox getComboBox_estado_ticket() {
		return comboBox_estado_ticket;
	}




	public void setComboBox_estado_ticket(JComboBox comboBox_estado_ticket) {
		this.comboBox_estado_ticket = comboBox_estado_ticket;
	}
}
