package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ManejadorEventos;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaPrincipal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JButton btn_crear_ticket = new JButton("CREAR TICKET");
	private final JButton tickets_abiertos = new JButton("VER TICKETS ABIERTOS");
	private final JButton tickets_cerrados = new JButton("VER TICKETS CERRADOS");


	
	//constructor de la ventanaContraseña
	public VentanaPrincipal() {
		
		setBounds(100, 100, 397, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255,178,102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage("calculadora.png"));
		setTitle("Gestion tickets");
		setResizable(false);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		btn_crear_ticket.setForeground(Color.BLUE);
		btn_crear_ticket.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_crear_ticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_crear_ticket.setBounds(63, 48, 254, 93);
		
		contentPanel.add(btn_crear_ticket);
		tickets_abiertos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tickets_abiertos.setForeground(Color.BLUE);
		tickets_abiertos.setFont(new Font("Tahoma", Font.BOLD, 15));
		tickets_abiertos.setBounds(63, 169, 254, 88);
		
		contentPanel.add(tickets_abiertos);
		tickets_cerrados.setForeground(Color.BLUE);
		tickets_cerrados.setFont(new Font("Tahoma", Font.BOLD, 15));
		tickets_cerrados.setBounds(63, 280, 254, 98);
		
		contentPanel.add(tickets_cerrados);
	}
	
	public void gestionarEventos(ManejadorEventos gestionar) {
		
		getBtn_crear_ticket().addActionListener(gestionar);
				
	}
	
	public JButton getBtn_crear_ticket() {
		return btn_crear_ticket;
	}



	public JButton getTickets_abiertos() {
		return tickets_abiertos;
	}



	public JButton getTickets_cerrados() {
		return tickets_cerrados;
	}

}

	
