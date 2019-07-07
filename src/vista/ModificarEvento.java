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
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class ModificarEvento extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	public JSONObject prueba;
	public int tipo_evento;
	public JTextArea textArea;
	private JTextField tipo_evento_text;
	private JTextField id_evento_text;
	private JLabel atras_icon_label;
	private JCheckBox resuelto_checkbox;
	private JButton modificarEvento_btn;
	private JScrollPane scroll;
	private JTextField tiempo_text;
	private JLabel minutos_label;

	public ModificarEvento(String id_evento, String tipo, String desc, String is_done, String tiempo_realizado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(644, 649);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		modificarEvento_btn = new JButton("Aplicar Cambios");
		modificarEvento_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		modificarEvento_btn.setBounds(419, 539, 149, 36);
		getContentPane().add(modificarEvento_btn);
		
		modificarEvento_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(tipo_evento_text.getText().equals("Tarea")) {
					int tipo_evento=2;
					if(resuelto_checkbox.isSelected()) {
						if(tiempo_text.getText().isEmpty()) {
							tiempo_text.setText("0");
							modificarTarea(Integer.parseInt(id_evento_text.getText()), Integer.parseInt(tiempo_text.getText()), true);
							modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
						}else {
						modificarTarea(Integer.parseInt(id_evento_text.getText()), Integer.parseInt(tiempo_text.getText()), true);
						modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
						}
					}else {
						modificarTarea(Integer.parseInt(id_evento_text.getText()), Integer.parseInt(tiempo_text.getText()), false);
						modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
						
					}
					
					
				}else if(tipo_evento_text.getText().equals("Seguimiento")){
					int tipo_evento=1;
					modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
					

				}else {
					System.out.println("nada");
				}
				
	
			}
		});
		
		
		JLabel tituloventana = new JLabel("Evento/Tarea");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 28));
		tituloventana.setBounds(234, 45, 198, 36);
		getContentPane().add(tituloventana);
		
		JLabel tipo_evento_label = new JLabel("Tipo de Evento:");
		tipo_evento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		tipo_evento_label.setBounds(102, 239, 109, 14);
		getContentPane().add(tipo_evento_label);
		
		JLabel descripcionEvento_label = new JLabel("Descripcion:");
		descripcionEvento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		descripcionEvento_label.setBounds(128, 293, 83, 26);
		getContentPane().add(descripcionEvento_label);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scroll = new JScrollPane(textArea);    
        scroll.setBounds(new Rectangle(221, 295, 346, 132));
		getContentPane().add(scroll);
		
		tipo_evento_text = new JTextField();
		tipo_evento_text.setBounds(221, 238, 346, 20);
		
		getContentPane().add(tipo_evento_text);
		tipo_evento_text.setColumns(10);
		
		JButton eliminar_btn = new JButton("Eliminar Evento");
		eliminar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipo_evento_text.getText().equals("Tarea")) {
					int tipo_evento=4;
					if(resuelto_checkbox.isSelected()) {
						modificarTarea(Integer.parseInt(id_evento_text.getText()), 5, true);
						modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
						JOptionPane.showMessageDialog(null, "Tarea borrada");

					}else {
						modificarTarea(Integer.parseInt(id_evento_text.getText()), 5, false);
						modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
						JOptionPane.showMessageDialog(null, "Tarea borrada");

					}
					
					
				}else if(tipo_evento_text.getText().equals("Seguimiento")){
					int tipo_evento=4;
					modificarEvento(textArea.getText(), Integer.parseInt(TablaListaTickets.ticket_id), tipo_evento, Integer.parseInt(id_evento_text.getText()));
					JOptionPane.showMessageDialog(null, "evento borrado");

				}else {
					System.out.println("nada");
				}
			}
		});
		eliminar_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		eliminar_btn.setBounds(234, 539, 149, 36);
		getContentPane().add(eliminar_btn);
		
		resuelto_checkbox = new JCheckBox("resuelto");
		resuelto_checkbox.setFont(new Font("Tahoma", Font.BOLD, 14));
		resuelto_checkbox.setSelected(false);
		resuelto_checkbox.setVisible(false);
		resuelto_checkbox.setBounds(471, 461, 97, 23);
		if(is_done=="true") {
			resuelto_checkbox.setSelected(true);
			resuelto_checkbox.setVisible(true);

		}else if(is_done=="false"){
			resuelto_checkbox.setSelected(false);
			resuelto_checkbox.setVisible(true);

		}else {
			resuelto_checkbox.setSelected(false);
			resuelto_checkbox.setVisible(false);
		}
		
		getContentPane().add(resuelto_checkbox);
		
		JLabel id_evento_label = new JLabel("ID_Evento:");
		id_evento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		id_evento_label.setBounds(128, 188, 83, 14);
		getContentPane().add(id_evento_label);
		
		id_evento_text = new JTextField();
		id_evento_text.setBounds(221, 187, 346, 20);
		id_evento_text.setEditable(false);
		getContentPane().add(id_evento_text);
		id_evento_text.setColumns(10);
		
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
		
		this.id_evento_text.setText(id_evento);
		this.tipo_evento_text.setText(tipo);
		this.textArea.setText(desc);
		
		
		JLabel tiempo_label = new JLabel("Tiempo en realizar la tarea:");
		tiempo_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		tiempo_label.setBounds(26, 465, 185, 14);
		if(tipo_evento_text.getText().equals("Tarea")) {
			tiempo_label.setVisible(true);
		}else {
			tiempo_label.setVisible(false);
		}
		getContentPane().add(tiempo_label);
		
		tiempo_text = new JTextField();
		tiempo_text.setBounds(221, 464, 51, 20);
		if(tipo_evento_text.getText().equals("Tarea")) {
			tiempo_text.setVisible(true);
			tiempo_text.setText(tiempo_realizado);
		}else {
			tiempo_text.setVisible(false);
		}
		getContentPane().add(tiempo_text);
		tiempo_text.setColumns(10);
		
		minutos_label = new JLabel("Minutos.");
		minutos_label.setHorizontalAlignment(SwingConstants.LEFT);
		if(tipo_evento_text.getText().equals("Tarea")) {
			minutos_label.setVisible(true);
			
		}else {
			minutos_label.setVisible(false);
		}
		minutos_label.setBounds(282, 467, 60, 14);
		getContentPane().add(minutos_label);
		
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
    
	//peticion para crear una tarea
    public JSONObject peticionModificarEvento(String event_desc, int ticket_id, int event_type, int event_id) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYEVENT");
    	prueba.put("event_desc", event_desc);
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("event_type", event_type);
    	prueba.put("event_id", event_id);
   
    	
    	return prueba;
    	
    }
	//peticion para crear una tarea
    public JSONObject peticionModificarTarea(int event_id, int time, boolean is_done) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYTASK");
    	prueba.put("event_id", event_id);
    	prueba.put("time", time);
    	prueba.put("is_done", is_done);
    	
    	return prueba;
    	
    }
    //metodo para crear un Evento
    public void modificarEvento(String event_desc, int ticket_id, int event_type, int event_id) {
		JSONObject pregunta = peticionModificarEvento(event_desc, ticket_id, event_type, event_id);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		
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
		JOptionPane.showMessageDialog(null, "Modificacion con exito");
		dispose();

		
	
    }
    
    //metodo donde hacemos la peticion para modificar tarea
    public void modificarTarea(int event_id, int time, boolean is_done) {
		JSONObject pregunta = peticionModificarTarea(event_id, time, is_done);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		/*Ticketindividual ticketindividual = new Ticketindividual(TablaListaTickets.ticket_id, TablaListaTickets.title, 
				TablaListaTickets.desc, TablaListaTickets.ticket_status, TablaListaTickets.fecha_creacion, TablaListaTickets.nombre_cliente);
		
		if (ClienteTFG2.tipo>2) {
			ticketindividual.modificar_btn.setEnabled(true);
		}else {
			ticketindividual.modificar_btn.setEnabled(false);
		}
		ticketindividual.setVisible(true);*/
		
		
	
    }
}

