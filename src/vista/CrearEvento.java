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

import controlador.ClienteTFG2;
import controlador.ManejadorEventos;
import modelo.Usuario;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class CrearEvento extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	public JSONObject prueba;
	public JComboBox comboBox;
	public int tipo_evento;
	public JTextArea textArea;
	private JLabel atras_icon_label;

	public CrearEvento(int ticket_id) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(918, 327);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton cambiar_pass_btn = new JButton("Crear Evento");
		cambiar_pass_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString()=="Tarea") {
					tipo_evento=2;
					newTarea(textArea.getText(), ticket_id);
				}
				else {
					if(comboBox.getSelectedItem().toString()=="Seguimiento"){
						tipo_evento=1;
					}else if(comboBox.getSelectedItem().toString()=="Solucion"){
						tipo_evento=3;
					}else if(comboBox.getSelectedItem().toString()=="Borrado"){
						tipo_evento=4;
		
					}
					newEvento(textArea.getText(), ticket_id, tipo_evento);
				}

	
			}
		});
		cambiar_pass_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cambiar_pass_btn.setBounds(715, 231, 149, 36);
		getContentPane().add(cambiar_pass_btn);
		
		vertiposeventos();
		JButton atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Apéndice de método generado automáticamente
				Ticketindividual ticketindividual = new Ticketindividual(TablaListaTickets.ticket_id, TablaListaTickets.title, 
						TablaListaTickets.desc, TablaListaTickets.ticket_status, TablaListaTickets.fecha_creacion, TablaListaTickets.nombre_cliente);
				if (ClienteTFG2.tipo>2) {
					ticketindividual.modificar_btn.setEnabled(true);
				}else {
					ticketindividual.modificar_btn.setEnabled(false);
				}
				ticketindividual.setVisible(true);
				dispose();
			}
		});
		atras_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		atras_btn.setBounds(518, 231, 149, 36);
		getContentPane().add(atras_btn);
		
		JLabel tituloventana = new JLabel("Crear Evento");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 28));
		tituloventana.setBounds(348, 31, 188, 36);
		getContentPane().add(tituloventana);
		
		JLabel tipo_evento_label = new JLabel("Tipo de Evento:");
		tipo_evento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		tipo_evento_label.setBounds(83, 155, 109, 14);
		getContentPane().add(tipo_evento_label);
		
		JLabel descripcionEvento_label = new JLabel("Descripcion:");
		descripcionEvento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		descripcionEvento_label.setBounds(425, 149, 83, 26);
		getContentPane().add(descripcionEvento_label);
		
		comboBox = new JComboBox();
		comboBox.setBounds(202, 154, 174, 20);
		comboBox.addItem("Tarea");
		comboBox.addItem("Seguimiento");
		comboBox.addItem("Solucion");
		getContentPane().add(comboBox);
		
		textArea = new JTextArea();
		textArea.setBounds(518, 152, 346, 68);
		getContentPane().add(textArea);
		
		Image imagen_salir=new ImageIcon("salir2.png").getImage();
		atras_icon_label = new JLabel(new ImageIcon(imagen_salir.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		atras_icon_label.setBounds(10, 11, 53, 52);
		atras_icon_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				Ticketindividual ticketindividual = new Ticketindividual(TablaListaTickets.ticket_id, TablaListaTickets.title, 
						TablaListaTickets.desc, TablaListaTickets.ticket_status, TablaListaTickets.fecha_creacion, TablaListaTickets.nombre_cliente);
				if (ClienteTFG2.tipo>2) {
					ticketindividual.modificar_btn.setEnabled(true);
				}else {
					ticketindividual.modificar_btn.setEnabled(false);
				}
				ticketindividual.setVisible(true);
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
	
	//peticion para crear un evento
    public JSONObject crearEvento(String event_desc, int ticket_id, int event_type) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","NEWEVENT");
    	prueba.put("event_desc", event_desc);
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("event_type", event_type);

    	return prueba;
    	
    }
	//peticion para crear una tarea
    public JSONObject peticionCrearTarea(String event_desc, int ticket_id, int time, boolean is_done) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","NEWTASK");
    	prueba.put("event_desc", event_desc);
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("time", time);
    	prueba.put("is_done", is_done);
    	
    	return prueba;
    	
    }
    
	//peticion para ver los tipos de eventos que existen
    public JSONObject TiposEventos() {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","LISTEVENTTYPE");
    	return prueba;
    	
    }
    
    public void vertiposeventos() {
		JSONObject pregunta = TiposEventos();
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
	
    }
    
    //metodo para crear un Evento
    public void newEvento(String event_desc, int ticket_id, int event_type) {
		JSONObject pregunta = crearEvento(event_desc, ticket_id, event_type);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		JOptionPane.showMessageDialog(null, "Evento Creado con exito");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		Ticketindividual ticketindividual = new Ticketindividual(TablaListaTickets.ticket_id, TablaListaTickets.title, 
				TablaListaTickets.desc, TablaListaTickets.ticket_status, TablaListaTickets.fecha_creacion, TablaListaTickets.nombre_cliente);
		
		if (ClienteTFG2.tipo>2) {
			ticketindividual.modificar_btn.setEnabled(true);
		}else {
			ticketindividual.modificar_btn.setEnabled(false);
		}
		ticketindividual.setVisible(true);
		
		dispose();

		
	
    }
    //metodo donde hacemos la peticion para crear una tarea
    public void newTarea(String event_desc, int ticket_id) {
		JSONObject pregunta = peticionCrearTarea(event_desc, ticket_id, 0, false);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		JOptionPane.showMessageDialog(null, "Tarea Creada con exito");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		Ticketindividual ticketindividual = new Ticketindividual(TablaListaTickets.ticket_id, TablaListaTickets.title, 
				TablaListaTickets.desc, TablaListaTickets.ticket_status, TablaListaTickets.fecha_creacion, TablaListaTickets.nombre_cliente);
		
		if (ClienteTFG2.tipo>2) {
			ticketindividual.modificar_btn.setEnabled(true);
		}else {
			ticketindividual.modificar_btn.setEnabled(false);
		}
		ticketindividual.setVisible(true);
		
		dispose();

		
	
    }
}
