package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
import java.awt.event.MouseAdapter;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class Ticketindividual extends JFrame {

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
	private Border border = BorderFactory.createLineBorder(Color.BLUE);
	private JLabel asignar_ticket_label;
	private JLabel cliente_label;
	private JTextField nombre_cliente_text;
	private JComboBox comboBox_asignarTicket;
	private JButton asignar_btn;
	private JLabel ticket_asignado_label;
	private JLabel eliminar_btn;
	private JLabel atras_icon_label;
	private JLabel añadir_evento_label;
	private JLabel mostrar_eventos_label;
	private JTable table_eventos;
	private DefaultTableModel dtm;
	private TableRowSorter sorter;
	private JScrollPane scrollPane;
	
	public static String id_evento;
	public static String descripcion;
	public static String tipo_evento;
	public static String mod_date;
	public static String is_done;
	public static String tiempo_realizado;
	private JLabel añadir_elemento_label;
	private JLabel mostrar_elementos_label;
	private JLabel eliminar_elemento_btn;
	private JLabel añadir_software_label;
	private JLabel elemento_software_text;
	private JLabel eliminar_software_label;


	public Ticketindividual(String numticket, String titulo_ticket, String area_desc, String estado, String fecha, String nombre_cliente) {

		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setSize(1152, 667);
		setLocationRelativeTo(null); //setbounds(x, y, w, h) para las dos primeras
		setUndecorated(true);//quitar borde
		setResizable(false);
		getContentPane().setLayout(null);
		
		setNum_ticket(new JLabel("Numero de ticket:"));
		getNum_ticket().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getNum_ticket());
		
		
		setDesc_label(new JLabel("Descripcion:"));
		getDesc_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getDesc_label());

		
		setEstado_label(new JLabel("Estado:"));
		getEstado_label().setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(getEstado_label());
		
		setText_estado(new JTextField());
		getText_estado().setColumns(10);
		getContentPane().add(getText_estado());
		
		titulo_ticket_2 = new JLabel("Titulo: ");
		titulo_ticket_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo_ticket_2.setBounds(172, 109, 53, 32);
		getContentPane().add(titulo_ticket_2);
		
		setArea_desc_2(new JTextArea());
		getArea_desc_2().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getArea_desc_2().setBorder(border);
		getArea_desc_2().setLineWrap(true);//no se salga del textarea y pase a la siguiente linea
		getArea_desc_2().setEditable(false);
		getArea_desc_2().setBounds(726, 116, 365, 100);
		getContentPane().add(getArea_desc_2());
		
		fecha_creacion = new JLabel("Fecha: ");
		fecha_creacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		fecha_creacion.setBounds(165, 195, 53, 32);
		getContentPane().add(fecha_creacion);
		
		titulo_text = new JTextField();
		titulo_text.setBounds(259, 117, 256, 20);
		titulo_text.setEditable(false);
		getContentPane().add(titulo_text);
		titulo_text.setColumns(10);
	
	
		num_ticket_text = new JTextField();
		num_ticket_text.setBounds(259, 160, 256, 20);
		num_ticket_text.setEditable(false);
		getContentPane().add(num_ticket_text);
		num_ticket_text.setColumns(10);
	
	
		fecha_text = new JTextField();
		fecha_text.setBounds(259, 203, 256, 20);
		fecha_text.setEditable(false);
		getContentPane().add(fecha_text);
		fecha_text.setColumns(10);
		
		nombre_cliente_text = new JTextField();
		nombre_cliente_text.setEditable(false);
		nombre_cliente_text.setBounds(259, 297, 256, 20);
		getContentPane().add(nombre_cliente_text);
		nombre_cliente_text.setColumns(10);
		
		//cogemos la informacion de la tabla y se la pasamos al ticket individual 
		this.num_ticket_text.setText(numticket);
		this.titulo_text.setText(titulo_ticket);
		this.getArea_desc_2().setText(area_desc);
		this.text_estado.setText(estado);
		this.fecha_text.setText(fecha);
		this.nombre_cliente_text.setText(nombre_cliente);
		
		//podremos modificar el ticket pulsando en este boton
		modificar_btn = new JButton("Modificar ticket");
		modificar_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		modificar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificarticket modificaticket = new Modificarticket(num_ticket_text.getText(), titulo_text.getText(), getArea_desc_2().getText(), text_estado.getText(), 
						fecha_text.getText(), nombre_cliente_text.getText());
				modificaticket.setVisible(true);
				dispose();
			}
		});
		modificar_btn.setEnabled(false);
		modificar_btn.setBounds(936, 581, 155, 39);
		getContentPane().add(modificar_btn);
		
		tituloventana = new JLabel("Detalle del ticket");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 32));
		tituloventana.setBounds(454, 27, 272, 52);
		getContentPane().add(tituloventana);
		
		asignar_ticket_label = new JLabel("Asignar ticket:");
		asignar_ticket_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		asignar_ticket_label.setBounds(612, 238, 99, 20);
		getContentPane().add(asignar_ticket_label);
		
		cliente_label = new JLabel("Cliente:");
		cliente_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		cliente_label.setBounds(165, 298, 53, 14);
		getContentPane().add(cliente_label);
		
		setComboBox_asignarTicket(new JComboBox());
		getComboBox_asignarTicket().setBounds(726, 238, 256, 20);
		nombreCliente();
		getContentPane().add(getComboBox_asignarTicket());
		
		//podremos asignar el ticket a un tecnico o supervisor
		asignar_btn = new JButton("Asignar");
		asignar_btn.setEnabled(false);
		if(ClienteTFG2.tipo>2) {//si no eres tecnico, supervisor, o admin, no podras asignar tickets
			asignar_btn.setEnabled(true);
		}else {
			asignar_btn.setEnabled(false);
		}
		asignar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				asignar();
				
			}
		});
		asignar_btn.setBounds(1002, 238, 89, 20);
		getContentPane().add(asignar_btn);
		
		setTicket_asignado_label(new JLabel(""));
		getTicket_asignado_label().setBounds(612, 281, 378, 20);
		getContentPane().add(getTicket_asignado_label());
		
		Image imagen_delete=new ImageIcon("delete.png").getImage();
		eliminar_btn = new JLabel(new ImageIcon(imagen_delete.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		if(ClienteTFG2.tipo<3) {
			eliminar_btn.setVisible(false);
		}else {
			eliminar_btn.setVisible(true);

		}
		eliminar_btn.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				eliminar_asignacion();
				
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
			
		
		eliminar_btn.setBounds(1002, 278, 46, 23);
		getContentPane().add(eliminar_btn);
		
		Image imagen_salir=new ImageIcon("salir2.png").getImage();
		atras_icon_label = new JLabel(new ImageIcon(imagen_salir.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		atras_icon_label.setBounds(10, 11, 53, 52);
		atras_icon_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				TablaListaTickets tablatickets = new TablaListaTickets();
				tablatickets.setVisible(true);
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
		
		//podremos añadir eventos al ticket para hacer un seguimiento mucho mas eficiente
		añadir_evento_label = new JLabel("+ A\u00F1adir Evento");
		añadir_evento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		añadir_evento_label.setBounds(110, 401, 115, 21);
		if(ClienteTFG2.tipo<3) {
			añadir_evento_label.setEnabled(false);
    		
		}else {
			añadir_evento_label.setEnabled(true);

		}
		añadir_evento_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(ClienteTFG2.tipo<3) {
					añadir_evento_label.setEnabled(false);
		    		JOptionPane.showMessageDialog(null, "no tienes permiso para añadir Eventos");
				}else {
				CrearEvento creaEvento= new CrearEvento(Integer.parseInt(num_ticket_text.getText()));
				creaEvento.setVisible(true);
				dispose();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				añadir_evento_label.setForeground(Color.blue);
				Font font = añadir_evento_label.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				añadir_evento_label.setFont(font.deriveFont(attributes));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				añadir_evento_label.setForeground(Color.black);
				añadir_evento_label.setFont(new Font("Tahoma", Font.BOLD, 14));					
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		getContentPane().add(añadir_evento_label);
		
		
		
		mostrar_eventos_label = new JLabel("New label");
		mostrar_eventos_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		mostrar_eventos_label.setVisible(false);
		mostrar_eventos_label.setBounds(290, 403, 272, 17);
		getContentPane().add(mostrar_eventos_label);
		
		String nombres_columnas[]= {"ID Evento", "Descripcion", "Tipo de Evento", "Fecha Creacion", "Resuelto", "Tiempo"};//columnas de la tabla 
		dtm = new DefaultTableModel(nombres_columnas, 0);
		sorter = new TableRowSorter(dtm);
		table_eventos = new JTable(dtm);
		table_eventos.setBounds(569, 317, 573, 63);
		table_eventos.setVisible(false);
		getContentPane().add(table_eventos);
		
		scrollPane = new JScrollPane(table_eventos);
		scrollPane.setBounds(109, 448, 982, 105);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);
		
		//podremos añadir elementos hardware en caso de que sea necesario, al ticket
		añadir_elemento_label = new JLabel("+ A\u00F1adir Elemento Hardware");
		añadir_elemento_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		añadir_elemento_label.setBounds(612, 312, 201, 14);
		if(ClienteTFG2.tipo<3) {
			añadir_elemento_label.setEnabled(false);
		}else {
			añadir_elemento_label.setEnabled(true);
		}
		añadir_elemento_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(ClienteTFG2.tipo<3) {
					añadir_elemento_label.setEnabled(false);
		    		JOptionPane.showMessageDialog(null, "no tienes permiso para añadir elementos");

				}else {
					if(mostrar_elementos_label.getText().equals("no hay elementos") || mostrar_elementos_label.getText().isEmpty()) {
					CrearElementoHardware crearelemento = new CrearElementoHardware(Integer.parseInt(num_ticket_text.getText()));
					crearelemento.setVisible(true);
					dispose();
					}else {
			    		JOptionPane.showMessageDialog(null, "ya tienes elementos hardware asignados");

					}
				}
			
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				añadir_elemento_label.setForeground(Color.blue);
				Font font = añadir_elemento_label.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				añadir_elemento_label.setFont(font.deriveFont(attributes));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				añadir_elemento_label.setForeground(Color.black);
				añadir_elemento_label.setFont(new Font("Tahoma", Font.BOLD, 14));					
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		getContentPane().add(añadir_elemento_label);
		
		mostrar_elementos_label = new JLabel("");
		mostrar_elementos_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mostrar_elementos_label.setVisible(false);
		mostrar_elementos_label.setBounds(650, 326, 370, 28);
		getContentPane().add(mostrar_elementos_label);
		
		Image imagen_delete_element=new ImageIcon("delete.png").getImage();
		eliminar_elemento_btn = new JLabel(new ImageIcon(imagen_delete.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		eliminar_elemento_btn.setBounds(1002, 334, 35, 32);
		if(ClienteTFG2.tipo<3) {
			eliminar_elemento_btn.setVisible(false);
		}else {
			eliminar_elemento_btn.setVisible(true);

		}
		eliminar_elemento_btn.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				eliminar_asignacion_elementos(Integer.parseInt(num_ticket_text.getText()));
				
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
		getContentPane().add(eliminar_elemento_btn);
		
		añadir_software_label = new JLabel("+ A\u00F1adir Elemento Software");
		añadir_software_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		añadir_software_label.setBounds(612, 365, 201, 14);
		if(ClienteTFG2.tipo<3) {
			añadir_software_label.setEnabled(false);
		}else {
			añadir_software_label.setEnabled(true);
		}
		añadir_software_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(ClienteTFG2.tipo<3) {
					añadir_software_label.setEnabled(false);
		    		JOptionPane.showMessageDialog(null, "no tienes permiso para añadir elementos");

				}else {
					if(elemento_software_text.getText().equals("no hay elementos") || elemento_software_text.getText().isEmpty()) {
			    		
						CrearElementoSoftware crearelemento = new CrearElementoSoftware(Integer.parseInt(num_ticket_text.getText()));
						crearelemento.setVisible(true);
						dispose();

					}else {
						JOptionPane.showMessageDialog(null, "ya tienes elemento software asignado");
					}
				}
			
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				añadir_software_label.setForeground(Color.blue);
				Font font = añadir_software_label.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				añadir_software_label.setFont(font.deriveFont(attributes));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				añadir_software_label.setForeground(Color.black);
				añadir_software_label.setFont(new Font("Tahoma", Font.BOLD, 14));					
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}
			
		});
		getContentPane().add(añadir_software_label);
		
		elemento_software_text = new JLabel("");
		elemento_software_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		elemento_software_text.setVisible(false);
		elemento_software_text.setBounds(650, 390, 293, 30);
		getContentPane().add(elemento_software_text);
		
		Image imagen_delete_element_software=new ImageIcon("delete.png").getImage();
		eliminar_software_label = new JLabel(new ImageIcon(imagen_delete.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		eliminar_software_label.setBounds(1002, 390, 35, 32);
		
		if(ClienteTFG2.tipo<3) {
			eliminar_software_label.setVisible(false);
		}else {
			eliminar_software_label.setVisible(true);

		}
		eliminar_software_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				eliminar_asignacion_elementos_software(Integer.parseInt(num_ticket_text.getText()));
				
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
		getContentPane().add(eliminar_software_label);
		
		//mostrareventosticket(Integer.parseInt(num_ticket_text.getText()));
		peticionElementosasignados(Integer.parseInt(num_ticket_text.getText()));
		peticionTicketasignados();//da informacion de si el ticket esta asignado a algun usuario.
		mostrareventosticket(Integer.parseInt(num_ticket_text.getText()));
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
		estado_label.setBounds(165, 238, 53, 32);
	}


	public JLabel getDesc_label() {
		return desc_label;
	}

	public void setDesc_label(JLabel desc_label) {
		this.desc_label = desc_label;
		desc_label.setBounds(628, 109, 83, 32);
	}


	public JLabel getNum_ticket() {
		return num_ticket;
	}

	public void setNum_ticket(JLabel num_ticket) {
		this.num_ticket = num_ticket;
		num_ticket.setBounds(92, 152, 130, 32);
	}

	public JTextField getText_estado() {
		return text_estado;
	}

	public void setText_estado(JTextField text_estado) {
		this.text_estado = text_estado;
		text_estado.setBounds(259, 246, 256, 20);
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
	
	
	/***************************************************************************************/
    //metodo para añadir supervisores y tecnicos al combobox
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
			if (objc.getInt("user_type")==3 || objc.getInt("user_type")==4) {
				Usuario usuario=new Usuario(objc.getInt("user_id"), objc.getString("name"), objc.getString("last_name"), objc.getString("email"));
				getComboBox_asignarTicket().addItem(usuario);
				
				
			}
		}
    }
    
	/***************************************************************************************/

    //metodo para sacar el id del cliente seleccionado en el combobox.
    public int idUserAsignar() {
    	
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
			Usuario user = (Usuario) comboBox_asignarTicket.getSelectedItem();
			if (user.id == objc.getInt("user_id")){
				id_cliente=objc.getInt("user_id");
				return id_cliente;
			}
		}
		
		return id_cliente;
    }
    
	/***************************************************************************************/

    //metodo para sacar el id del Cliente.
    public int idCliente() {
    	
    	String nombre=nombre_cliente_text.getText();
    	String[]id =nombre.split(" ");
    	int id_cliente=Integer.parseInt(id[0]);
    	System.out.println("id de nuestro cliente del ticket " + id_cliente);
    	return id_cliente;

    }
    
	//peticion para asignar un ticket a un supervisor o tecnico
    public JSONObject peticion_asignar_ticket(int ticket_id, int id_usuario_asignado) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","ASSIGNTECH");
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("assigned_tech", id_usuario_asignado);

    	
    	return prueba;
    	
    }
    
	/***************************************************************************************/

	//peticion para asignar un ticket a un supervisor o tecnico
    public JSONObject eliminar_asignacion_ticket(int ticket_id, int id_usuario_asignado) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","DELETETECHASSIGNED");
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("assigned_tech", id_usuario_asignado);

    	
    	return prueba;
    	
    }
    
	/***************************************************************************************/

    public void asignar() {
		JSONObject peticion=informacion_ticket_asignados();
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		System.out.println("longitud asignaciones  " + ClienteTFG2.id_user_asignado);
    	if (ClienteTFG2.id_user_asignado>0) {
    		JOptionPane.showMessageDialog(null, "ya tienes un user asignado");

    	}else {
	    	int id_ticket = Integer.parseInt(num_ticket_text.getText());
			JSONObject peticion2=peticion_asignar_ticket(id_ticket, idUserAsignar());
			
			JSONObject pregunta2 = peticion2;
			System.out.println(pregunta2);
			
			HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
			hilo2.start();
			try {
				hilo2.join();
			} catch (InterruptedException d) {
				d.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "ticket Asignado con exito");
			ticket_asignado_label.setText("ticket asignado a: "+ String.valueOf(comboBox_asignarTicket.getSelectedItem()));
			text_estado.setText("Asignado");
			modificarticket_estadoAsignado(2);
    	}
    }
    
	/***************************************************************************************/

    //metodo para eliminar una asignacion.
    public void eliminar_asignacion() {
		JSONObject peticion=informacion_ticket_asignados();
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		
    	if(ClienteTFG2.id_user_asignado==0) {
    		modificarticket_estadoAsignado(1);
    		JOptionPane.showMessageDialog(null, "No hay usuarios asignados");
    		text_estado.setText("abierto");
    		
    	}else {
	    	int id_ticket = Integer.parseInt(num_ticket_text.getText());
			JSONObject peticion2=eliminar_asignacion_ticket(id_ticket, ClienteTFG2.id_user_asignado);
			
			JSONObject pregunta2 = peticion2;
			System.out.println(pregunta2);
			
			HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
			hilo2.start();
			try {
				hilo2.join();
			} catch (InterruptedException d) {
				d.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Asignacion eliminada con exito");
			modificarticket_estadoAsignado(1);
			text_estado.setText("abierto");
			peticionTicketasignados();
    	}
		
    }
    
	/***************************************************************************************/

    //ver informacion de ticket asignados
    public JSONObject informacion_ticket_asignados() {
    	int ticket_id = Integer.parseInt(num_ticket_text.getText());
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","TECHRELATION");
    	prueba.put("ticket_id", ticket_id);
    	return prueba;
    	
    }
    
	/***************************************************************************************/

    public void peticionTicketasignados() {
    	
		JSONObject peticion = informacion_ticket_asignados();
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		if(ClienteTFG2.id_user_asignado>0) {
			JSONObject pregunta2 = new JSONObject().put("peticion", "listusers");
			System.out.println(pregunta2);

			HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
			hilo2.start();
			try {
				hilo2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			JSONArray array = hilo2.respueta.getJSONArray("content");
			for (int i = 0; i < array.length(); i++) {
				JSONObject objc = (JSONObject) array.get(i);
				Object obj[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), objc.getInt("user_type")};//filas de la tabla
				String nombre_cliente_tabla=String.valueOf(objc.getString("name"));
				
				
				if (ClienteTFG2.id_user_asignado == objc.getInt("user_id")){
					Usuario user = new Usuario(ClienteTFG2.id_user_asignado, objc.getString("name"), objc.getString("last_name"),objc.getString("email"));
					ticket_asignado_label.setText("Ticket asignado a: " + user.toString());
				}
			}
		}else if(ClienteTFG2.id_user_asignado==0){
			ticket_asignado_label.setText("Ticket asignado a: no hay users");
		}
		

    }
    
	/***************************************************************************************/

    public void modificarticket_estadoAsignado(int estado) {

		
		JSONObject peticion=modificarticket(getTitulo_text().getText(), getArea_desc_2().getText(), estado, Integer.parseInt(getNum_ticket_text().getText()), idCliente());
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		
		
    }
    
	/***************************************************************************************/

	//peticion para poder Modificar el ticket
    public JSONObject modificarticket(String titulo, String descripcion, int estado, int id_ticket, int id_cliente) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYTICKET");
    	prueba.put("title", titulo);
    	prueba.put("desc", descripcion);
    	prueba.put("ticket_status_id", estado);
    	prueba.put("ticket_owner", ClienteTFG2.id_usuario_logueado);//id del que lo modifica
    	prueba.put("ticket_object", id_cliente);//id del cliente
    	prueba.put("ticket_id", id_ticket);
    	
    	return prueba;
    	
    }
    
	/***************************************************************************************/

    //metodo para añadir supervisores y tecnicos al combobox
    public String nombreuserAsignado() {
    	Usuario usuario=null;
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
			if (ClienteTFG2.id_user_asignado==0) {
				String respuesta="no hay users asignados";
				return respuesta;
			}
			
			if (ClienteTFG2.id_user_asignado==objc.getInt("user_id")) {
				usuario=new Usuario(objc.getInt("user_id"), objc.getString("name"), objc.getString("last_name"), objc.getString("email"));
				return usuario.toString();
				
			}
		}
		return usuario.toString();
    }
    
	/***************************************************************************************/
    

	//peticion para mostrar si hay eventos del ticket
    public JSONObject eventosticket(int id_ticket) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","LISTEVENTS");
    	prueba.put("ticket_id", id_ticket);
    	
    	return prueba;
    	
    }
    
	/***************************************************************************************/
    //metodo para mostrar eventos si es que hay, en el ticket.
    public void mostrareventosticket(int id_ticket) {
 
		JSONObject pregunta = eventosticket(id_ticket);
		System.out.println(pregunta);

		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JSONArray array = hilo.respueta.getJSONArray("content");
		
		System.out.println("estado de eventos: " + ClienteTFG2.lista_eventos);
		if(ClienteTFG2.lista_eventos==true) {
			mostrar_eventos_label.setText("- Este ticket tiene Eventos");
			mostrar_eventos_label.setVisible(true);

			
			for (int i = 0; i < array.length(); i++) {
				
				JSONObject objc = (JSONObject) array.get(i);
				Object obj[] = {objc.getString("event_desc"), objc.getInt("event_id"), objc.getInt("event_type"), objc.getString("mod_date")};//filas de la tabla
				
				if (objc.getInt("event_type")==1) {
					String tipo_evento= "Seguimiento";
					Object obj1[] = {objc.getInt("event_id"), objc.getString("event_desc"), tipo_evento, objc.getString("mod_date")};
					dtm.addRow(obj1);
				}else if(objc.getInt("event_type")==2) {
					String tipo_evento= "Tarea";
					Object obj1[] = {objc.getInt("event_id"), objc.getString("event_desc"), tipo_evento, objc.getString("mod_date"), objc.getBoolean("is_done"), objc.getInt("time")};
					dtm.addRow(obj1);
				}else if(objc.getInt("event_type")==3) {
					String tipo_evento= "Solucion";
					Object obj1[] =  {objc.getInt("event_id"), objc.getString("event_desc"), tipo_evento, objc.getString("mod_date")};
					dtm.addRow(obj1);
				}else if(objc.getInt("event_type")==4) {
					String tipo_evento= "Borrado";
					Object obj1[] =  {objc.getInt("event_id"), objc.getString("event_desc"), tipo_evento, objc.getString("mod_date")};
					//dtm.addRow(obj1);
				}
				table_eventos.setRowHeight(30);
			}
			
			scrollPane.setVisible(true);
			table_eventos.getAutoscrolls();
			table_eventos.setRowHeight(30);
			table_eventos.setRowSorter(sorter);//ordena la columna de forma ascendente o descendente
			table_eventos.setVisible(true);
			table_eventos.getTableHeader().setReorderingAllowed(false);
			
			table_eventos.getColumnModel().getColumn(0).setMaxWidth(0);//con estas cuatro lineas ocultamos la columna de id del evento ya que el cliente no tiene por que saberla
			table_eventos.getColumnModel().getColumn(0).setMinWidth(0);
			table_eventos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
			table_eventos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
			
			table_eventos.getColumnModel().getColumn(5).setMaxWidth(0);//con estas cuatro lineas ocultamos la columna de id del evento ya que el cliente no tiene por que saberla
			table_eventos.getColumnModel().getColumn(5).setMinWidth(0);
			table_eventos.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
			table_eventos.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

			
			table_eventos.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					int row = table_eventos.rowAtPoint(e.getPoint());
					if(table_eventos.getValueAt(row, 2).toString()!="Tarea") {
						id_evento= table_eventos.getValueAt(row, 0).toString();
						descripcion= table_eventos.getValueAt(row, 1).toString();
						tipo_evento= table_eventos.getValueAt(row, 2).toString();
						mod_date= table_eventos.getValueAt(row, 3).toString();
						String tiempo="";
						String resolve="";
						ModificarEvento modificarevento =  new ModificarEvento(id_evento, tipo_evento, descripcion, resolve, tiempo);
						modificarevento.setVisible(true);
						dispose();
						
					}else {
						id_evento= table_eventos.getValueAt(row, 0).toString();
						descripcion= table_eventos.getValueAt(row, 1).toString();
						tipo_evento= table_eventos.getValueAt(row, 2).toString();
						mod_date= table_eventos.getValueAt(row, 3).toString();
						is_done= table_eventos.getValueAt(row, 4).toString();
						tiempo_realizado = table_eventos.getValueAt(row, 5).toString();
						ModificarEvento modificarevento =  new ModificarEvento(id_evento, tipo_evento, descripcion, is_done, tiempo_realizado);
						modificarevento.setVisible(true);
						dispose();
					}


					
				}
			});
			
		}else if(ClienteTFG2.lista_eventos==false){
			mostrar_eventos_label.setVisible(false);
		}	
		

    }
    
	//peticion para ver informacion de elementos asignados
    public JSONObject elementosasignados(int id_ticket) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","ELEMENTRELATION");
    	prueba.put("ticket_id", id_ticket);
    	
    	return prueba;
    	
    }
	//peticion para ver informacion de elementos asignados
    public JSONObject peticiondetalleshardware(int element_id) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","ELEMENTDETAILS");
    	prueba.put("element_id", element_id);

    	
    	return prueba;
    	
    }
    
    //metodo para ver el elemento asignado
    public void peticionElementosasignados(int id_ticket) {
    	
		JSONObject peticion=elementosasignados(id_ticket);
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		JSONArray array = hilo.respueta.getJSONArray("content");
		for (int i = 0; i < array.length(); i++) {
			
			JSONObject objc = (JSONObject) array.get(i);
			Object obj[] = {objc.getInt("element_id")};//filas de la tabla
			
			JSONObject peticion2=peticiondetalleshardware(objc.getInt("element_id"));
			
			JSONObject pregunta2 = peticion2;
			System.out.println(pregunta2);
			
			HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
			
			hilo2.start();
			try {
				hilo2.join();
			} catch (InterruptedException d) {
				d.printStackTrace();
			}
			JSONArray array2 = hilo2.respueta.getJSONArray("content");
			for (int x = 0; x < array2.length(); x++) {
				JSONObject objct = (JSONObject) array2.get(x);
				Object objt[] = {objct.getInt("element_id"), objct.getString("internal_name"), objct.getInt("element_type")};//filas de la tabla
				if (objct.getInt("element_type")==1) {
					String tipo_elemento="Hardware";
					mostrar_elementos_label.setText("- " + objct.getInt("element_id")+" "+ objct.getString("internal_name") + " " + objct.getString("brand")+
							" " + objct.getString("model")+" "+ tipo_elemento);//brand model
					mostrar_elementos_label.setVisible(true);
				}else if(objct.getInt("element_type")==2) {
					String tipo_elemento="Software";
					elemento_software_text.setText("- " + objct.getInt("element_id")+" "+ objct.getString("internal_name") + " " +  objct.getString("version")+ 
							" " + tipo_elemento);
					elemento_software_text.setVisible(true);
				}
			}
		}
		

    }
    
	//peticion para ver informacion de elementos asignados
    public JSONObject peticionEliminar_elemento(int ticket_id, int element_id) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","DELETEELEMENTASSIGNED");
    	prueba.put("ticket_id", ticket_id);
    	prueba.put("element_id", element_id);

    	
    	return prueba;
    	
    }
    
    //metodo para eliminar un elemento
    public void eliminar_asignacion_elementos(int ticket_id) {
    	String elemento=mostrar_elementos_label.getText();
    	String [] id_elemento =elemento.split(" ");
    	int id_elemento_separado= Integer.parseInt(id_elemento[1]);
    	
		JSONObject peticion=peticionEliminar_elemento(ticket_id, id_elemento_separado);
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Elemento eliminado");
		mostrar_elementos_label.setText("no hay elementos");

		
	}
    
    //metodo para eliminar un elemento
    public void eliminar_asignacion_elementos_software(int ticket_id) {
    	String elemento=elemento_software_text.getText();
    	String [] id_elemento =elemento.split(" ");
    	int id_elemento_separado= Integer.parseInt(id_elemento[1]);
    	
		JSONObject peticion=peticionEliminar_elemento(ticket_id, id_elemento_separado);
		
		JSONObject pregunta = peticion;
		System.out.println(pregunta);
		
		HiloPeticiones hilo = new HiloPeticiones(pregunta);
		hilo.start();
		try {
			hilo.join();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Elemento eliminado");
		elemento_software_text.setText("no hay elementos");

		
	}
		
    

    
    

	public JComboBox getComboBox_asignarTicket() {
		return comboBox_asignarTicket;
	}

	public void setComboBox_asignarTicket(JComboBox comboBox_asignarTicket) {
		this.comboBox_asignarTicket = comboBox_asignarTicket;
	}

	public JLabel getTicket_asignado_label() {
		return ticket_asignado_label;
	}

	public void setTicket_asignado_label(JLabel ticket_asignado_label) {
		this.ticket_asignado_label = ticket_asignado_label;
		ticket_asignado_label.setFont(new Font("Tahoma", Font.BOLD, 14));
	}

	public JTextArea getArea_desc_2() {
		return area_desc_2;
	}

	public void setArea_desc_2(JTextArea area_desc_2) {
		this.area_desc_2 = area_desc_2;
	}
}

