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
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class CambiarMiPass extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	public JSONObject prueba;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;


	public CambiarMiPass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(474, 377);//tamaño ventana
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);//quitar bordes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);//importante para que funciones los botones que metemos
		
		JLabel new_pass_label = new JLabel("Nueva contrase\u00F1a:");
		new_pass_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		new_pass_label.setBounds(100, 148, 130, 28);
		getContentPane().add(new_pass_label);
		
		JLabel confirm_pass_label = new JLabel("confirmar contrase\u00F1a:");
		confirm_pass_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirm_pass_label.setBounds(78, 179, 152, 28);
		getContentPane().add(confirm_pass_label);
		
		setPasswordField(new JPasswordField());
		getPasswordField().setBounds(248, 154, 149, 20);
		getContentPane().add(getPasswordField());
		
		setPasswordField_1(new JPasswordField());
		getPasswordField_1().setBounds(248, 185, 149, 20);
		getContentPane().add(getPasswordField_1());
		
		JButton cambiar_pass_btn = new JButton("Cambiar");
		cambiar_pass_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				

				if(isPasswordCorrect(getPasswordField().getPassword(),getPasswordField_1().getPassword())==true) {
					
					System.out.println(getPasswordField().getText());
					System.out.println(getPasswordField_1().getText());

					JSONObject peticion=cambiar_pass(getPasswordField().getText());
					
					JSONObject pregunta = peticion;
					System.out.println(pregunta);
					
					HiloPeticiones hilo = new HiloPeticiones(pregunta);
					hilo.start();
					try {
						hilo.join();
					} catch (InterruptedException d) {
						d.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "pass cambiada con exito");
					VentanaPrincipal2 ventanaprincipal= new VentanaPrincipal2();
					ventanaprincipal.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "las contraseñas no son iguales");

				}
			}
		});
		cambiar_pass_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cambiar_pass_btn.setBounds(248, 245, 149, 36);
		getContentPane().add(cambiar_pass_btn);
		
		JButton atras_btn = new JButton("Atras");
		atras_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Miperfil miperfil = new Miperfil();
				miperfil.setVisible(true);
				dispose();
			}
		});
		atras_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		atras_btn.setBounds(248, 292, 149, 36);
		getContentPane().add(atras_btn);
		
		JLabel tituloventana = new JLabel("Cambiar mi contrase\u00F1a");
		tituloventana.setFont(new Font("Tahoma", Font.BOLD, 23));
		tituloventana.setBounds(100, 65, 309, 36);
		getContentPane().add(tituloventana);
		inicializarComponentes();
		

		
	}
	
	public void inicializarComponentes() {
		
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
	}
	
	//peticion para cambiar la pass del propio usuario que esta logueado
    public JSONObject cambiar_pass(String newpass) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("peticion","MODIFYOWNPASSWORD");
    	prueba.put("shdw_passwd", newpass);

    	return prueba;
    	
    }

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}

	public void setPasswordField_1(JPasswordField passwordField_1) {
		this.passwordField_1 = passwordField_1;
	}
	
	//metodo para comparar el texto de dos JPasswordField
	private boolean isPasswordCorrect(char[] pass1,char[] pass2) { 
		boolean valor = true; 
		int puntero = 0; 
		if (pass1.length != pass2.length){ 
		valor = false; 
		} 
		else{ 
		while((valor)&&(puntero < pass1.length)){ 
		if (pass1[puntero] != pass2[puntero]){ 
		valor = false; 
		} 
		puntero++; 
		} 
		} 
		return valor; 
		}
}
