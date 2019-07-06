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
import modelo.Usuario;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class CreaTickets extends JFrame {

	private JPanel contentPane;
	private JTextField text_nombre;
	private JTextField text_asunto;
	private JButton btnCrearTicket;
	private JTextArea text_descripcion;
	private JComboBox comboBox_estado_ticket;
	int estado_ticket;
	Border border = BorderFactory.createLineBorder(Color.BLUE);
	private JLabel lblCrearTicket;
	private JComboBox comboBox;
	private JLabel atras_icon_label;



	
	public CreaTickets() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setResizable(false);
		setSize(621, 534);
		setUndecorated(true);//quitar bordes
		setLocationRelativeTo(null); //setbounds(x, y, w, h) para las dos primeras
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(0, 191, 255));
		contentPane.setLayout(null);
		
		text_nombre = new JTextField();
		text_nombre.setBorder(border);
		text_nombre.setBounds(276, 130, 280, 20);
		contentPane.add(text_nombre);
		text_nombre.setColumns(10);
		
		JLabel creador_ticket = new JLabel("Titulo de ticket");
		creador_ticket.setFont(new Font("Tahoma", Font.BOLD, 13));
		creador_ticket.setBounds(109, 132, 99, 14);
		contentPane.add(creador_ticket);
		
		JLabel asunto = new JLabel("Asunto");
		asunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		asunto.setBounds(162, 182, 63, 14);
		contentPane.add(asunto);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setFont(new Font("Tahoma", Font.BOLD, 13));
		descripcion.setBounds(132, 319, 76, 14);
		contentPane.add(descripcion);
		
		JLabel cliente = new JLabel("Cliente");
		cliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		cliente.setBounds(162, 228, 46, 14);
		contentPane.add(cliente);
		
		text_asunto =new JTextField();
		text_asunto.setBorder(border);
		text_asunto.setBounds(276, 180, 280, 20);
		contentPane.add(text_asunto);
		text_asunto.setColumns(10);
		
		setText_descripcion(new JTextArea());
		getText_descripcion().setBorder(border);
		getText_descripcion().setLineWrap(true);
		getText_descripcion().setEditable(true);
		getText_descripcion().setBounds(276, 316, 280, 135);
		contentPane.add(getText_descripcion());
		
		setBtnCrearTicket(new JButton("Crear ticket"));
		getBtnCrearTicket().setFont(new Font("Tahoma", Font.BOLD, 13));
		getBtnCrearTicket().setBounds(389, 475, 167, 35);
		btnCrearTicket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {//cuando pulsamos al boton llamamos a la peticion para crear el ticket y crearlo.
				if(getText_nombre().getText().isEmpty() || getText_descripcion().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "te has dejado campos vacios");

				}else {
				
					//parseamos el estado que hayamos elegido para trasnformarlo en un numero que es el que recibe la base de datos.
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
					
					//cogemos del combobox solo el id del cliente
					int id_cliente = idCliente();
					System.out.println("este es el id del cliente " + String.valueOf(getComboBox().getSelectedItem())+" "+ id_cliente);
					
					//peticion para crear el ticket
					JSONObject peticion=crearTicket(getText_nombre().getText(), getText_descripcion().getText(), estado_ticket, id_cliente);
					
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
				
			}
			
		});
		contentPane.add(getBtnCrearTicket());
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(162, 272, 46, 14);
		contentPane.add(lblEstado);
		
		setComboBox_estado_ticket(new JComboBox());
		getComboBox_estado_ticket().setBounds(276, 270, 280, 20);
		getComboBox_estado_ticket().addItem("Abierto");
		getComboBox_estado_ticket().addItem("Asignado");
		getComboBox_estado_ticket().addItem("En espera (terceros)");
		getComboBox_estado_ticket().addItem("En espera (Cliente)");
		getComboBox_estado_ticket().addItem("Solucionado");
		getComboBox_estado_ticket().addItem("cerrado");
		contentPane.add(getComboBox_estado_ticket());
		
		lblCrearTicket = new JLabel("Crear Ticket");
		lblCrearTicket.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblCrearTicket.setBounds(234, 41, 141, 42);
		contentPane.add(lblCrearTicket);
		
		setComboBox(new JComboBox());
		nombreCliente();
		getComboBox().setBounds(276, 226, 280, 20);
		contentPane.add(getComboBox());
		
		Image imagen_salir=new ImageIcon("salir2.png").getImage();
		atras_icon_label = new JLabel(new ImageIcon(imagen_salir.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		atras_icon_label.setBounds(10, 11, 53, 52);
		atras_icon_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				VentanaPrincipal2 ventanaprincipal = new VentanaPrincipal2();
				ventanaprincipal.setVisible(true);
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


	//peticion para poder crear el ticket
    public JSONObject crearTicket(String titulo, String descripcion, int status_ticket, int id_cliente) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","newTicket");
    	prueba.put("title", titulo);
    	prueba.put("desc", descripcion);
    	prueba.put("ticket_status_id", status_ticket);
    	prueba.put("ticket_owner", ClienteTFG2.id_usuario_logueado);//id del que lo crea.
    	prueba.put("ticket_object", id_cliente);//id del usuario cliente.
    	
    	return prueba;
    	
    }

    //metodo para añadir clientes al combobox
    public void nombreCliente() {
		JSONObject pregunta = new JSONObject().put("peticion", "listusers");
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
			Object obj[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), objc.getInt("user_type")};//filas de la tabla
			if (objc.getInt("user_type")==1 || objc.getInt("user_type")==2) {
				Usuario usuario=new Usuario(objc.getInt("user_id"), objc.getString("name"), objc.getString("last_name"), objc.getString("email"));
				getComboBox().addItem(usuario);
				System.out.println(String.valueOf(getComboBox().getSelectedItem()));
				
			}
		}
    }
    
    //metodo para sacar el id del cliente seleccionado en el combobox.
    public int idCliente() {
    	
    	String nombre_cliente=String.valueOf(getComboBox().getSelectedItem());
		JSONObject pregunta = new JSONObject().put("peticion", "listusers");
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		int id_cliente=0;
		for (int i = 0; i < array.length(); i++) {
			JSONObject objc = (JSONObject) array.get(i);
			Object obj[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), objc.getInt("user_type")};//filas de la tabla
			System.out.println(nombre_cliente + " " +  objc.getString("name"));
			String nombre_cliente_tabla=String.valueOf(objc.getString("name"));
			
			Usuario user = (Usuario) comboBox.getSelectedItem();
			
			if (user.id == objc.getInt("user_id")){
				id_cliente=objc.getInt("user_id");
				return id_cliente;
			}
		}
		
		return id_cliente;
    }



	public JTextArea getText_descripcion() {
		return text_descripcion;
	}




	public void setText_descripcion(JTextArea text_descripcion) {
		this.text_descripcion = text_descripcion;
		text_descripcion.setWrapStyleWord(true);
		text_descripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		text_descripcion.setToolTipText("breve descripci\u00F3n");
	}




	public JComboBox getComboBox_estado_ticket() {
		return comboBox_estado_ticket;
	}




	public void setComboBox_estado_ticket(JComboBox comboBox_estado_ticket) {
		this.comboBox_estado_ticket = comboBox_estado_ticket;
	}




	public JComboBox getComboBox() {
		return comboBox;
	}




	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
}
