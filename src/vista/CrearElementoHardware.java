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
import javax.swing.JTextField;

public class CrearElementoHardware extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	public JSONObject prueba;
	public int tipo_evento;
	private JLabel atras_icon_label;
	private JTextField nombre_hardware_text;
	private JTextField marca_text;
	private JTextField sn_hardware_text;
	private JTextField modelo_hardware_text;
	private JTextField txtHardware;

	public CrearElementoHardware(int ticket_id) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(584, 553);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton cambiar_pass_btn = new JButton("Crear Elemento");
		cambiar_pass_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				newElementoHardware(nombre_hardware_text.getText(), sn_hardware_text.getText(), marca_text.getText(), modelo_hardware_text.getText());//se crea
				asignarElemento(ticket_id, listadoElementos());//se asigna el elemento al ticket

			}
		});
		cambiar_pass_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cambiar_pass_btn.setBounds(322, 446, 149, 36);
		getContentPane().add(cambiar_pass_btn);
		
		vertiposElementos();
		
		JLabel tituloventana = new JLabel("Crear Elemento");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 28));
		tituloventana.setBounds(186, 43, 218, 36);
		getContentPane().add(tituloventana);
		
		JLabel tipo_evento_label = new JLabel("Tipo de Elemento:");
		tipo_evento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		tipo_evento_label.setBounds(69, 168, 125, 14);
		getContentPane().add(tipo_evento_label);
		
		JLabel nombre_label = new JLabel("Nombre:");
		nombre_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombre_label.setBounds(133, 260, 61, 26);
		getContentPane().add(nombre_label);
		
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
		
		nombre_hardware_text = new JTextField();
		nombre_hardware_text.setBounds(253, 265, 218, 20);
		getContentPane().add(nombre_hardware_text);
		nombre_hardware_text.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(148, 215, 46, 14);
		getContentPane().add(lblMarca);
		
		marca_text = new JTextField();
		marca_text.setBounds(253, 214, 218, 20);
		getContentPane().add(marca_text);
		marca_text.setColumns(10);
		
		JLabel sn_label = new JLabel("Numero de Serie:");
		sn_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		sn_label.setBounds(74, 373, 120, 14);
		getContentPane().add(sn_label);
		
		sn_hardware_text = new JTextField();
		sn_hardware_text.setBounds(253, 372, 218, 20);
		getContentPane().add(sn_hardware_text);
		sn_hardware_text.setColumns(10);
		
		JLabel modelo_label = new JLabel("Modelo :");
		modelo_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		modelo_label.setBounds(133, 318, 61, 20);
		getContentPane().add(modelo_label);
		
		modelo_hardware_text = new JTextField();
		modelo_hardware_text.setBounds(253, 320, 218, 20);
		getContentPane().add(modelo_hardware_text);
		modelo_hardware_text.setColumns(10);
		
		txtHardware = new JTextField();
		txtHardware.setEditable(false);
		txtHardware.setText("Hardware");
		txtHardware.setBounds(253, 167, 218, 20);
		getContentPane().add(txtHardware);
		txtHardware.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Hardware");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(224, 90, 163, 36);
		getContentPane().add(lblNewLabel);
		inicializarComponentes();
		

		
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}
	

    
	//peticion para ver los tipos de Elementos
    public JSONObject peticionTiposElmentos() {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","LISTELEMENTS");
    	return prueba;
    	
    }
	//peticion para ver los lista de elementos
    public JSONObject peticionListaElmentos() {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","LISTELEMENTS");
    	return prueba;
    	
    }
	//peticion para crear Elemento Hardware
    public JSONObject peticioncrearHardware(String internal_name, String sn, String brand, String model) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","NEWHARDWARE");
    	prueba.put("internal_name", internal_name);
    	prueba.put("S/N", sn);
    	prueba.put("brand", brand);
    	prueba.put("model", model);
    	
    	return prueba;
    	
    }
    
	//peticion para crear Elemento Software
    public JSONObject peticioncrearSoftware(String internal_name, String developer, String version) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","NEWSOFTWARE");
    	prueba.put("internal_name", internal_name);
    	prueba.put("developer", developer);
    	prueba.put("version", version);
    	return prueba;
    }
	//peticion para asignar un ticket a un elemento
    public JSONObject peticionasginarElemento(int ticket_id, int element_id) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","ASSIGNELEMENT");
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("element_id", element_id);
    	
    	return prueba;
    	
    }
    
    //metodo para asignar elemento a ticket
    public void asignarElemento(int ticket_id, int element_id) {
		JSONObject pregunta = peticionasginarElemento(ticket_id, element_id);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		JOptionPane.showMessageDialog(null, "elemento asignado con exito");
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
    
    //metodo para ver tipos de elementos
    public void vertiposElementos() {
		JSONObject pregunta = peticionTiposElmentos();
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
    
    //metodo para crear ELEMENTO SOFTWARE
    public void newElementoSoftware(String internal_name, String developer, String version) {
		JSONObject pregunta = peticioncrearSoftware(internal_name, developer, version);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		JOptionPane.showMessageDialog(null, "Elemento Hardware creado con exito");
		
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
    public void newElementoHardware(String internal_name, String sn, String brand, String model) {
		JSONObject pregunta = peticioncrearHardware(internal_name, sn, brand, model);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		JOptionPane.showMessageDialog(null, "Elemento creado con exito");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}


    }
    
    //lista elementos y cogemos su id para poder asignarlo
    public int listadoElementos() {
    	int id_elemento=0;
		JSONObject pregunta = peticionListaElmentos();
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
			Object obj[] = {objc.getString("internal_name"), objc.getInt("element_id")};//filas de la tabla
			if (objc.getString("internal_name").equals(nombre_hardware_text.getText())) {
				id_elemento=objc.getInt("element_id");
				return id_elemento;
			}
		}
		
		return id_elemento;
		

    }
    
    
}

