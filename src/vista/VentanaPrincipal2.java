package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import controlador.ClienteTFG;
import controlador.ClienteTFG2;
import controlador.ManejadorEventos;

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

public class VentanaPrincipal2 extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	private JButton btn_crear_ticket;
	private JButton tickets_abiertos;
	private JButton lista_usuarios;
	public JSONObject prueba;
	private JButton salir_btn;
	private JLabel titulo_text;
	private JLabel logo;
	private JLabel miperfil_label;


	public VentanaPrincipal2() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconoapp.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(394, 574);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);//importante para que funciones los botones que metemos
		inicializarComponentes();
		

		
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
		btn_crear_ticket = new JButton("CREAR TICKETS");
		btn_crear_ticket.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_crear_ticket.setBounds(74, 151, 256, 49);
		btn_crear_ticket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreaTickets crearticket=new CreaTickets();
				crearticket.setVisible(true);
				dispose();
				
			}
			
		});
		getContentPane().add(getBtn_crear_ticket());
		
		
		
		tickets_abiertos = new JButton("VER TICKETS");
		tickets_abiertos.setFont(new Font("Tahoma", Font.BOLD, 14));
		tickets_abiertos.setBounds(74, 226, 256, 49);
		tickets_abiertos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TicketsAbiertos listatickets= new TicketsAbiertos();
				//listatickets.setVisible(true);
				TablaListaTickets listatickets = new TablaListaTickets();
				listatickets.setVisible(true);
				dispose();
				
			}
		});
		getContentPane().add(getTickets_abiertos());
		
		lista_usuarios = new JButton("VER USUARIOS");
		lista_usuarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		lista_usuarios.setBounds(74, 305, 256, 49);
		lista_usuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ListaUsuarios list_users= new ListaUsuarios();
				//list_users.setVisible(true);
				TablaListaUsers list_users = new TablaListaUsers();
				list_users.setVisible(true);
				dispose();
			}
		});
		getContentPane().add(lista_usuarios);
		
		salir_btn = new JButton("SALIR");
		salir_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				dispose();
				
			}
		});
		salir_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		salir_btn.setBounds(74, 385, 256, 49);
		getContentPane().add(salir_btn);
		
		titulo_text = new JLabel("Gestion de tickets");
		titulo_text.setFont(new Font("Tahoma", Font.BOLD, 29));
		titulo_text.setBounds(63, 47, 267, 38);
		getContentPane().add(titulo_text);
		

		
		
		miperfil_label = new JLabel("Mi Perfil");
		miperfil_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		miperfil_label.setBounds(267, 512, 63, 24);
		miperfil_label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Miperfil miperfil = new Miperfil();
				miperfil.setVisible(true);
				dispose();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				miperfil_label.setForeground(Color.blue);
				Font font = miperfil_label.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				miperfil_label.setFont(font.deriveFont(attributes));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				miperfil_label.setForeground(Color.black);
				miperfil_label.setFont(new Font("Tahoma", Font.PLAIN, 16));					
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
		getContentPane().add(miperfil_label);
		
		Image img = new ImageIcon("icono_login.png").getImage();
		logo = new JLabel(new ImageIcon(img.getScaledInstance(30, 32, Image.SCALE_SMOOTH)));
		logo.setBounds(218, 495, 48, 49);
		getContentPane().add(logo);
	}

	






	public JButton getBtn_crear_ticket() {
		return btn_crear_ticket;
	}


	public void setBtn_crear_ticket(JButton btn_crear_ticket) {
		this.btn_crear_ticket = btn_crear_ticket;
	}


	public JButton getTickets_abiertos() {
		return tickets_abiertos;
	}


	public void setTickets_abiertos(JButton tickets_abiertos) {
		this.tickets_abiertos = tickets_abiertos;
	}


	public void setTickets_cerrados(JButton tickets_cerrados) {
		this.lista_usuarios = tickets_cerrados;
	}

}


