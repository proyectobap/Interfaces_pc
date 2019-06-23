package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import controlador.ClienteTFG;
import controlador.ClienteTFG2;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class TablaListaTickets extends JFrame {
	private int b=0;

	
	public TablaListaTickets() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		setSize(990, 409);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Gestion tickets");
		setResizable(false);
		
		/********************************************************************************************************/
		
		String nombres_columnas[]= {"ID_ticket", "Titulo", "descripcion", "estado", "fecha"};//columnas de la tabla 
		DefaultTableModel dtm = new DefaultTableModel(nombres_columnas, 0);
		TableRowSorter sorter = new TableRowSorter(dtm);
		JTable tickets_abiertos = new JTable(dtm);
		
		

		JSONObject pregunta = new JSONObject().put("peticion", "listticket");
		System.out.println(pregunta);
		/*JSONObject pregunta2 = new JSONObject().put("peticion", "listticketstatus");
		System.out.println(pregunta2);
		HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
		hilo2.start();
		try {
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
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
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==2) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "asignado";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==3) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "En espera (terceros)";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==4) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "En espera (Cliente)";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==5) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "Solucionado";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date")};
				dtm.addRow(obj1);
			}
			else if (objc.getInt("ticket_status_id")==6) {
				String a= String.valueOf(objc.getInt("ticket_status_id"));
				a= "cerrado";
				Object obj1[] = {objc.getInt("ticket_id"), objc.getString("title"), objc.getString("desc"), a, objc.getString("mod_date")};
				dtm.addRow(obj1);
			}
			else {
				dtm.addRow(obj);
			}

			
			tickets_abiertos.setRowHeight(30);
		}
		
		tickets_abiertos.setRowSorter(sorter);//ordena la columna de forma ascendente o descendente
		sorter.setRowFilter(RowFilter.numberFilter(ComparisonType.BEFORE, 10, 0));//muestra los diez primeros

		tickets_abiertos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int row = tickets_abiertos.rowAtPoint(e.getPoint());
				String ticket_id="numero de ticket: " + tickets_abiertos.getValueAt(row, 0).toString();
				String title="titulo: " + tickets_abiertos.getValueAt(row, 1).toString();
				String desc=tickets_abiertos.getValueAt(row, 2).toString();
				String ticket_status=tickets_abiertos.getValueAt(row, 3).toString();
				String fecha_creacion = "fecha de creacion: " + tickets_abiertos.getValueAt(row, 4).toString();
				Ticketindividual ticket_individual = new Ticketindividual(ticket_id, title, desc, ticket_status, fecha_creacion);
				if (ClienteTFG2.tipo>=3) {
					System.out.println(ClienteTFG2.tipo);
					ticket_individual.modificar_btn.setEnabled(true);
				}
				ticket_individual.setVisible(true);
			}
		});

		

		
		/********************************************************************************************************/

		
		getContentPane().setLayout(null);
		
		tickets_abiertos.setBackground(new Color(255,255,255));
		tickets_abiertos.getRowMargin();
		JScrollPane scrollPane = new JScrollPane(tickets_abiertos);
		scrollPane.setBounds(79, 71, 848, 194);
		getContentPane().add(scrollPane);
		
		JLabel lblListaDeTickets = new JLabel("Lista de Tickets");
		lblListaDeTickets.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblListaDeTickets.setBounds(363, 25, 259, 32);
		getContentPane().add(lblListaDeTickets);
		
		JButton btnAadirTicket = new JButton("a\u00F1adir ticket");
		btnAadirTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaTickets crearticket=new CreaTickets();
				crearticket.setVisible(true);
				dispose();
			}
		});
		btnAadirTicket.setBounds(644, 313, 128, 40);
		getContentPane().add(btnAadirTicket);
		
		JButton salir_btn = new JButton("Salir");
		salir_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	System.exit(0);
				dispose();
			}
		});
		salir_btn.setBounds(799, 313, 128, 40);
		getContentPane().add(salir_btn);
		
		JButton atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal2 ventanaprincipal = new VentanaPrincipal2();
				ventanaprincipal.setVisible(true);
				dispose();
			}
		});
		atras_btn.setBounds(79, 313, 128, 40);
		getContentPane().add(atras_btn);
		
		JButton actualizar_btn = new JButton("Actualizar");
		actualizar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaListaTickets nueva_tabla_tickets = new TablaListaTickets();
				nueva_tabla_tickets.setVisible(true);
				dispose();
			}
		});
		actualizar_btn.setBounds(485, 313, 128, 40);
		getContentPane().add(actualizar_btn);
		
		
	}
	

    

}
