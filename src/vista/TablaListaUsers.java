package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.management.StringValueExp;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class TablaListaUsers extends JFrame {
	
	private final JPanel contentPanel = new JPanel();
	
	public TablaListaUsers() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		setSize(990, 438);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage("calculadora.png"));
		setTitle("Gestion tickets");
		setResizable(false);
		
		/****/
		
		String nombres_columnas[]= {"id", "Email", "Nombre", "Apellido", "Tipo Usuario"};//columnas de la tabla 
		DefaultTableModel dtm = new DefaultTableModel(nombres_columnas, 0);
		TableRowSorter sorter = new TableRowSorter(dtm);
		JTable lista_usuarios = new JTable(dtm);
		
		

		JSONObject pregunta = new JSONObject().put("peticion", "listusers");
		System.out.println(pregunta);
		/*JSONObject pregunta2 = new JSONObject().put("peticion", "listusertype");
		System.out.println(pregunta2);
		HiloPeticiones hilo2 = new HiloPeticiones(pregunta2);
		hilo2.start();
		try {
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}para saber que tipo de users son*/
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
			if (objc.getInt("user_type")==1) {
				String a= String.valueOf(objc.getInt("user_type"));
				a= "Usuario sin Login";
				Object obj1[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), a};
				dtm.addRow(obj1);
			}else if (objc.getInt("user_type")==2) {
				String a= String.valueOf(objc.getInt("user_type"));
				a= "Usuario con Login";
				Object obj1[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), a};
				dtm.addRow(obj1);
			}else if (objc.getInt("user_type")==3) {
				String a= String.valueOf(objc.getInt("user_type"));
				a= "Técnico";
				Object obj1[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), a};
				dtm.addRow(obj1);
			}else if (objc.getInt("user_type")==4) {
				String a= String.valueOf(objc.getInt("user_type"));
				a= "Supervisor";
				Object obj1[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), a};
				dtm.addRow(obj1);
			}else if (objc.getInt("user_type")==5) {
				String a= String.valueOf(objc.getInt("user_type"));
				a= "Administrador";
				Object obj1[] = {objc.getInt("user_id"), objc.getString("email"), objc.getString("name"), objc.getString("last_name"), a};
				dtm.addRow(obj1);
			}else{
				dtm.addRow(obj);
			}
			
			lista_usuarios.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//ticket individual
				}
			});
			
		}
		lista_usuarios.getAutoscrolls();
		lista_usuarios.setRowHeight(30);
		lista_usuarios.setRowSorter(sorter);//ordena la columna de forma ascendente o descendente
		
		lista_usuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = lista_usuarios.rowAtPoint(e.getPoint());
				String user_id="id del usuario : " + lista_usuarios.getValueAt(row, 0).toString();
				String email="email: " + lista_usuarios.getValueAt(row, 1).toString();
				String nombre="Nombre: " + lista_usuarios.getValueAt(row, 2).toString();
				String apellido="Apellido: " + lista_usuarios.getValueAt(row, 3).toString();
				String tipo_user =lista_usuarios.getValueAt(row, 4).toString();
				Userindividual user_individual = new Userindividual(user_id, email, nombre, apellido, tipo_user);
				user_individual.setVisible(true);
			}
		});
		
		
	
		
		/****/
		getContentPane().setLayout(null);
		
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lista_usuarios.setBackground(new Color(255, 255, 255));
		lista_usuarios.getRowMargin();
		JScrollPane scrollPane = new JScrollPane(lista_usuarios);
		scrollPane.setBounds(81, 97, 868, 214);
		getContentPane().add(scrollPane);
		
		JLabel lblListaDeUsuarios = new JLabel("Lista de Usuarios");
		lblListaDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblListaDeUsuarios.setBounds(372, 34, 265, 37);
		getContentPane().add(lblListaDeUsuarios);
		
		JButton btnNewButton = new JButton("Crear Usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUser2 crearuser=new CrearUser2();
				crearuser.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(671, 348, 125, 47);
		getContentPane().add(btnNewButton);
		
		JButton salir_btn = new JButton("Salir");
		salir_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				dispose();
			}
		});
		salir_btn.setBounds(824, 348, 125, 47);
		getContentPane().add(salir_btn);
		
		JButton actualizar_btn = new JButton("Actualizar");
		actualizar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaListaUsers nueva_tabla_user = new TablaListaUsers();
				nueva_tabla_user.setVisible(true);
				dispose();
			}
		});
		actualizar_btn.setBounds(522, 348, 125, 47);
		getContentPane().add(actualizar_btn);
		
		JButton eliminar_user = new JButton("Eliminar Usuario");
		eliminar_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		eliminar_user.setEnabled(false);
		eliminar_user.setBounds(372, 348, 125, 47);
		getContentPane().add(eliminar_user);
		
		JButton atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal2 ventanaprincipal = new VentanaPrincipal2();
				ventanaprincipal.setVisible(true);
				dispose();
			}
		});
		atras_btn.setBounds(81, 348, 113, 47);
		getContentPane().add(atras_btn);
		
		
	}
	
    private void setEventoMouseClicked(JTable tbl)
    {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
 
        @Override
        public void mouseClicked(MouseEvent e) {
        mouseClicked(e);
        }
        });
    }
	

	
}