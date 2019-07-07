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

import controlador.ClienteTFG2;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TablaListaUsers extends JFrame {
	
	private final JPanel contentPanel = new JPanel();
	public static int id;
	public static String user_id;
	public static String email;
	public static String nombre;
	public static String apellido;
	public static String tipo_user;
	private JLabel atras_icon_label;
	
	public TablaListaUsers() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		getContentPane().setBackground(new Color(0, 191, 255));
		setSize(990, 438);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Gestion tickets");
		setResizable(false);
		
		//hacemos la peticion para que nos devuelva una tabla con todos los usuarios que tiene la aplicacion registrados
		/************************************************************************************************************/
		
		String nombres_columnas[]= {"id", "Email", "Nombre", "Apellido", "Tipo Usuario"};//columnas de la tabla 
		DefaultTableModel dtm = new DefaultTableModel(nombres_columnas, 0);
		TableRowSorter sorter = new TableRowSorter(dtm);
		JTable lista_usuarios = new JTable(dtm);
		
		

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
			//parseamos los tipos de usuario para que salgan en palabras y no en numero.
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
		
		//si apretamos en una fila nos abrira una ventana y nos mostrara la informacion del usuario, pudiendo modificarla en caso de ser admin
		lista_usuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = lista_usuarios.rowAtPoint(e.getPoint());
				user_id= lista_usuarios.getValueAt(row, 0).toString();
				email= lista_usuarios.getValueAt(row, 1).toString();
				nombre= lista_usuarios.getValueAt(row, 2).toString();
				apellido= lista_usuarios.getValueAt(row, 3).toString();
				tipo_user = lista_usuarios.getValueAt(row, 4).toString();
				Userindividual user_individual = new Userindividual(user_id, email, nombre, apellido, tipo_user);
				if (ClienteTFG2.tipo>4) {//si el usuario logueado es un Administrador el boton modificar se activara
					System.out.println(ClienteTFG2.tipo);
					user_individual.modificar_btn.setEnabled(true);
				}
				user_individual.setVisible(true);
				dispose();
			}
		});
		
		
		/************************************************************************************************************/
		getContentPane().setLayout(null);
		
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lista_usuarios.setBackground(new Color(255, 255, 255));
		lista_usuarios.getRowMargin();
		JScrollPane scrollPane = new JScrollPane(lista_usuarios);
		scrollPane.setBounds(65, 97, 868, 214);
		getContentPane().add(scrollPane);
		
		JLabel lblListaDeUsuarios = new JLabel("Lista de Usuarios");
		lblListaDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblListaDeUsuarios.setBounds(383, 24, 248, 37);
		getContentPane().add(lblListaDeUsuarios);
		
		//este boton estara habilitado en caso de ser Administrador
		JButton btnNewButton = new JButton("Crear Usuario");
		if(ClienteTFG2.tipo>4) {//solamente podran crear usuarios los administradores
			btnNewButton.setEnabled(true);
		}else {
			btnNewButton.setEnabled(false);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUser2 crearuser=new CrearUser2();
				crearuser.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(810, 348, 125, 47);
		getContentPane().add(btnNewButton);
		
		JButton actualizar_btn = new JButton("Actualizar");
		actualizar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaListaUsers nueva_tabla_user = new TablaListaUsers();
				nueva_tabla_user.setVisible(true);
				dispose();
			}
		});
		actualizar_btn.setBounds(658, 348, 125, 47);
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