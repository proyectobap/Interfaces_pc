package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

import javax.management.StringValueExp;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.JSONArray;
import org.json.JSONObject;

import controlador.ClienteTFG2;
import modelo.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TablaListaTickets extends JFrame {
	private int b=0;
	public static String ticket_id;
	public static String title;
	public static String desc;
	public static String ticket_status;
	public static String fecha_creacion;
	public static String nombre_cliente;
	public JLabel atras_icon_label;
	
	public TablaListaTickets() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		getContentPane().setBackground(new Color(0, 191, 255));
		setSize(988, 450);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Gestion tickets");
		setResizable(false);
		
		//peticion para sacar la tabla con todos los tickets que hay creados en la aplicacion
		/********************************************************************************************************/
		
		String nombres_columnas[]= {"ID_ticket", "Titulo", "descripcion", "estado", "fecha", "id cliente"};//columnas de la tabla 
		DefaultTableModel dtm = new DefaultTableModel(nombres_columnas, 0);
		TableRowSorter sorter = new TableRowSorter(dtm);
		JTable tickets_abiertos = new JTable(dtm);
		
		

		JSONObject pregunta = new JSONObject().put("peticion", "listticket");
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
			Object obj[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), objc.getInt("ticket_status_id"), objc.getString("mod_date")};//filas de la tabla
			
			if (objc.getInt("ticket_status_id")==1) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "abierto";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date"), objc.getInt("ticket_object")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==2) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "asignado";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date"), objc.getInt("ticket_object")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==3) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "En espera (terceros)";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date"), objc.getInt("ticket_object")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==4) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "En espera (Cliente)";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date"), objc.getInt("ticket_object")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==5) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "Solucionado";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date"), objc.getInt("ticket_object")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==6) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "cerrado";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date"), objc.getInt("ticket_object")};
				dtm.addRow(obj1);
			}
			else {
				dtm.addRow(obj);
			}

			
			tickets_abiertos.setRowHeight(30);
			
		}
		
		tickets_abiertos.getAutoscrolls();
		tickets_abiertos.setRowHeight(30);
		tickets_abiertos.setRowSorter(sorter);//ordena la columna de forma ascendente o descendente
		

		//cuando pulsas sobre una fila te abre el ticket que estas seleccionando y te saca toda la informacion completa de dicho ticket
		tickets_abiertos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int row = tickets_abiertos.rowAtPoint(e.getPoint());
				ticket_id= tickets_abiertos.getValueAt(row, 0).toString();
				title= tickets_abiertos.getValueAt(row, 1).toString();
				desc= tickets_abiertos.getValueAt(row, 2).toString();
				ticket_status= tickets_abiertos.getValueAt(row, 3).toString();
				fecha_creacion =  tickets_abiertos.getValueAt(row, 4).toString();
				nombre_cliente = usuario_cliente(Integer.parseInt(tickets_abiertos.getValueAt(row, 5).toString()));
				Ticketindividual ticket_individual = new Ticketindividual(ticket_id, title, desc, ticket_status, fecha_creacion, nombre_cliente);
				ticket_individual.peticionTicketasignados();
				if (ClienteTFG2.tipo>=3) {//si eres usuario con login o sin login, no podras modificar un ticket
					System.out.println(ClienteTFG2.tipo);
					ticket_individual.modificar_btn.setEnabled(true);
				}
				
	
				ticket_individual.setVisible(true);
				dispose();
			}
		});

		

		
		/********************************************************************************************************/

		
		getContentPane().setLayout(null);
		
		tickets_abiertos.setBackground(new Color(255,255,255));
		tickets_abiertos.getRowMargin();
		JScrollPane scrollPane = new JScrollPane(tickets_abiertos);
		scrollPane.setBounds(79, 110, 848, 194);
		getContentPane().add(scrollPane);
		
		JLabel lblListaDeTickets = new JLabel("Lista de Tickets");
		lblListaDeTickets.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblListaDeTickets.setBounds(374, 34, 226, 32);
		getContentPane().add(lblListaDeTickets);
		
		JButton btnAadirTicket = new JButton("a\u00F1adir ticket");
		btnAadirTicket.setEnabled(false);
		if(ClienteTFG2.tipo>2) {
			btnAadirTicket.setEnabled(true);
		}else {
			btnAadirTicket.setEnabled(false);
		}
		btnAadirTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaTickets crearticket=new CreaTickets();
				crearticket.setVisible(true);
				dispose();
			}
		});
		btnAadirTicket.setBounds(799, 377, 128, 40);
		getContentPane().add(btnAadirTicket);
		
		JButton actualizar_btn = new JButton("Actualizar");
		actualizar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaListaTickets nueva_tabla_tickets = new TablaListaTickets();
				nueva_tabla_tickets.setVisible(true);
				dispose();
			}
		});
		actualizar_btn.setBounds(645, 377, 128, 40);
		getContentPane().add(actualizar_btn);
		
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
	
	//parsea el id_cliente por su nombre cuando accedemos al ticket completo
	public String nombre_cliente(int id_tabla_cliente) {
		String nombre_cliente=null;
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
			if (id_tabla_cliente == objc.getInt("user_id")){
				
				nombre_cliente = objc.getString("name");
				return nombre_cliente;
			}
		}
		
		return nombre_cliente;
	}
	
	
    public String usuario_cliente(int id_cliente) {
    	Usuario user = null;
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
			System.out.println(nombre_cliente + " " +  objc.getString("name"));
			String nombre_cliente_tabla=String.valueOf(objc.getString("name"));
			
			
			if (id_cliente == objc.getInt("user_id")){
				user = new Usuario(objc.getInt("user_id"), objc.getString("name"), objc.getString("last_name"), objc.getString("email"));

				return user.toString();
			}
		}
		
		return user.toString();
    }

    

}
