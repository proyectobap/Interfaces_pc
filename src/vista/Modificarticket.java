package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import controlador.ClienteTFG2;
import controlador.ManejadorEventos;
import modelo.Usuario;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Modificarticket extends JFrame {

	private JTextField text_estado;
	private JLabel num_ticket;
	private JLabel desc_label;
	private JLabel estado_label;
	private JLabel titulo_ticket_2;
	private JTextArea area_desc_2;
	private JLabel fecha_creacion;
	public JButton modificar_btn;
	private JTextField titulo_text;
	private JTextField num_ticket_text;
	private JTextField fecha_text;
	private JLabel tituloventana;
	private JComboBox comboBox;
	public int estado_ticket;
	private Border border = BorderFactory.createLineBorder(Color.BLUE);
	private JLabel cliente_label;
	private JTextField nombre_cliente_text;
	private JComboBox comboBox_clientes;
	private JLabel atras_icon_label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;


	public Modificarticket(String numticket, String titulo_ticket, String area_desc, String estado, String fecha, String nombre_cliente) {

		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setSize(1133, 567);
		setLocationRelativeTo(null); //setbounds(x, y, w, h) para las dos primeras
		setUndecorated(true);//quitar borde
		setResizable(false);
		getContentPane().setLayout(null);
		
		setNum_ticket(new JLabel("numero de ticket:"));
		getNum_ticket().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getNum_ticket());
		
		
		setDesc_label(new JLabel("descripcion:"));
		getDesc_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getDesc_label());

		
		setEstado_label(new JLabel("Estado Actual:"));
		getEstado_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getEstado_label());
		
		setText_estado(new JTextField());
		getText_estado().setColumns(10);
		getContentPane().add(getText_estado());
		
		titulo_ticket_2 = new JLabel("titulo: ");
		titulo_ticket_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo_ticket_2.setBounds(179, 145, 46, 32);
		getContentPane().add(titulo_ticket_2);
		
		
		setArea_desc_2(new JTextArea());
		getArea_desc_2().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getArea_desc_2().setBorder(border);
		getArea_desc_2().setLineWrap(true);//no se salga del textarea y pase a la siguiente linea
		getArea_desc_2().setBounds(739, 152, 295, 285);
		getContentPane().add(getArea_desc_2());
		
		fecha_creacion = new JLabel("Fecha:");
		fecha_creacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		fecha_creacion.setBounds(179, 228, 46, 32);
		getContentPane().add(fecha_creacion);
		
		titulo_text = new JTextField();
		titulo_text.setBounds(260, 153, 262, 20);
		getContentPane().add(titulo_text);
		titulo_text.setColumns(10);
	
	
		num_ticket_text = new JTextField();
		num_ticket_text.setBounds(260, 193, 262, 20);
		num_ticket_text.setEditable(false);
		getContentPane().add(num_ticket_text);
		num_ticket_text.setColumns(10);
	
	
		fecha_text = new JTextField();
		fecha_text.setBounds(260, 236, 262, 20);
		getContentPane().add(fecha_text);
		fecha_text.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(260, 329, 262, 20);
		getComboBox().addItem("Abierto");
		getComboBox().addItem("Asignado");
		getComboBox().addItem("En espera (terceros)");
		getComboBox().addItem("En espera (Cliente)");
		getComboBox().addItem("Solucionado");
		getComboBox().addItem("cerrado");
		getContentPane().add(comboBox);
		
		nombre_cliente_text = new JTextField();
		nombre_cliente_text.setBounds(260, 367, 262, 20);
		getContentPane().add(nombre_cliente_text);
		nombre_cliente_text.setColumns(10);
		
		setComboBox_clientes(new JComboBox());
		getComboBox_clientes().setBounds(260, 407, 262, 20);
		nombreCliente();
		getContentPane().add(getComboBox_clientes());
		
		this.num_ticket_text.setText(numticket);
		this.titulo_text.setText(titulo_ticket);
		this.getArea_desc_2().setText(area_desc);
		this.text_estado.setText(estado);
		this.fecha_text.setText(fecha);
		this.nombre_cliente_text.setText(nombre_cliente);
		
		modificar_btn = new JButton("modificar");
		modificar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarticket();
			}
		});
		modificar_btn.setBounds(911, 459, 123, 39);
		getContentPane().add(modificar_btn);
		
		tituloventana = new JLabel("Modificar ticket");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 32));
		tituloventana.setBounds(456, 44, 262, 52);
		getContentPane().add(tituloventana);
		
		cliente_label = new JLabel("Cliente Actual:");
		cliente_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		cliente_label.setBounds(125, 368, 100, 14);
		getContentPane().add(cliente_label);
		
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
		
		lblNewLabel = new JLabel("Nuevo Estado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 330, 102, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Cambiar Cliente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(112, 408, 113, 14);
		getContentPane().add(lblNewLabel_1);
		

		

		

		

		


		inicializarComponentes();
				
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}



	public JLabel getEstado_label() {
		return estado_label;
	}

	public void setEstado_label(JLabel estado_label) {
		this.estado_label = estado_label;
		estado_label.setBounds(125, 271, 100, 32);
	}


	public JLabel getDesc_label() {
		return desc_label;
	}

	public void setDesc_label(JLabel desc_label) {
		this.desc_label = desc_label;
		desc_label.setBounds(612, 145, 82, 32);
	}


	public JLabel getNum_ticket() {
		return num_ticket;
	}

	public void setNum_ticket(JLabel num_ticket) {
		this.num_ticket = num_ticket;
		num_ticket.setBounds(102, 185, 123, 32);
	}

	public JTextField getText_estado() {
		return text_estado;
	}

	public void setText_estado(JTextField text_estado) {
		this.text_estado = text_estado;
		text_estado.setBounds(260, 279, 262, 20);
		text_estado.setEditable(false);
	}
	
	public JTextField getTitulo_text() {
		return titulo_text;
	}

	public void setTitulo_text(JTextField titulo_text) {
		this.titulo_text = titulo_text;
	}

	public JTextField getNum_ticket_text() {
		return num_ticket_text;
	}

	public void setNum_ticket_text(JTextField num_ticket_text) {
		this.num_ticket_text = num_ticket_text;
	}

	public JTextField getFecha_text() {
		return fecha_text;
	}

	public void setFecha_text(JTextField fecha_text) {
		this.fecha_text = fecha_text;
	}
	
	//peticion para poder Modificar el ticket
    public JSONObject modificarticket(String titulo, String descripcion, int status_ticket, int id_ticket, int id_cliente) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYTICKET");
    	prueba.put("title", titulo);
    	prueba.put("desc", descripcion);
    	prueba.put("ticket_status_id", status_ticket);
    	prueba.put("ticket_owner", ClienteTFG2.id_usuario_logueado);//id del que lo modifica
    	prueba.put("ticket_object", id_cliente);//id del cliente
    	prueba.put("ticket_id", id_ticket);
    	
    	return prueba;
    	
    }
    
    public void modificarticket() {
		if (getComboBox().getSelectedItem().toString()=="Abierto") {
			estado_ticket=1;
		}else if(getComboBox().getSelectedItem().toString()=="Asignado") {
			estado_ticket=2;
		}else if(getComboBox().getSelectedItem().toString()=="En espera (terceros)") {
			estado_ticket=3;
		}else if(getComboBox().getSelectedItem().toString()=="En espera (Cliente)") {
			estado_ticket=4;
		}else if(getComboBox().getSelectedItem().toString()=="Solucionado") {
			estado_ticket=5;
		}else if(getComboBox().getSelectedItem().toString()=="cerrado") {
			estado_ticket=6;
		}
		
		JSONObject peticion=modificarticket(getTitulo_text().getText(), getArea_desc_2().getText(), estado_ticket, Integer.parseInt(getNum_ticket_text().getText()), idCliente_combobox());
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "ticket modificado con exito");
		TablaListaTickets listaticket= new TablaListaTickets();
		listaticket.setVisible(true);
		dispose();
    }

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JTextArea getArea_desc_2() {
		return area_desc_2;
	}

	public void setArea_desc_2(JTextArea area_desc_2) {
		this.area_desc_2 = area_desc_2;
	}
	

	    public int idCliente() {
	    	int id_cliente=0;
	    	String nombre_cliente=nombre_cliente_text.getText();
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
						
				if (nombre_cliente.equals(objc.getString("name"))){
					id_cliente=objc.getInt("user_id");
					System.out.println("id del cliente despues de modificar " + id_cliente);
					return id_cliente;
				}
			}
			
			return id_cliente;
	    
	}
	    
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
				getComboBox_clientes().addItem(usuario);
				System.out.println(String.valueOf(getComboBox().getSelectedItem()));
				
			}
		}
    }
    
    //metodo para sacar el id del cliente seleccionado en el combobox.
    public int idCliente_combobox() {
    	
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
			
			Usuario user = (Usuario) comboBox_clientes.getSelectedItem();
			
			if (user.id == objc.getInt("user_id")){
				id_cliente=objc.getInt("user_id");
				return id_cliente;
			}
		}
		
		return id_cliente;
    }

	public JComboBox getComboBox_clientes() {
		return comboBox_clientes;
	}

	public void setComboBox_clientes(JComboBox comboBox_clientes) {
		this.comboBox_clientes = comboBox_clientes;
	}
}
