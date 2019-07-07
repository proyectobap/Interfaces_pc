package vista;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.json.JSONObject;


import controlador.ClienteTFG2;
import controlador.EncryptModule;
import controlador.ManejadorEventos;
import controlador.RespuestaLogin;


public class Login extends JFrame implements RespuestaLogin {
	
	public static ClienteTFG2 conexion = null;
	
	
	private final long serialVersionUID = 1L;
	//primero declaramos las etiquetas cajas de texto y los botones necesarios
	private JLabel etiqueta1, etiqueta2, logo, etiqueta3, etiqueta4;
	private JTextField cajaTexto1;
	private JPasswordField cajaTexto2;
	private JButton boton1;
	private JButton salir;
	private EncryptModule enc;
	private String pass;
	private String user;
	private boolean a = true;

	/*Este metodo, cuando hacemos la peticion para el login y es correcto, se ejecutara
	 * y abrira nuestra ventana principal de la aplicación.
	 *  */
	@Override
	public void respuesta() {
		System.out.println("LOGEADO!");
		
		JOptionPane.showMessageDialog(null, "Contraseña correcta!");
		VentanaPrincipal2 ventanaprincipal2= new VentanaPrincipal2();
		ventanaprincipal2.setVisible(true);
		dispose();
	}
	
	
	//metodo para comprobar la contraseña
	
	public void comprobarDatos() {

		user=String.valueOf(cajaTexto1.getText());
		pass=String.valueOf(cajaTexto2.getPassword());
		//condiciones para no dejar campos vacios
		if(user.isEmpty() && pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "introduzca usuario y contraseña");
		}
		else if (user.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Usuario vacio, por favor introduce el nombre de usuario");

		}else if(pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "introduzca una contraseña");

		}else {//si no hay campos vacios pasa aqui, donde ya comprobamos el login
		enc = new EncryptModule();
		conexion = new ClienteTFG2(user, pass, enc, this);
		conexion.getHilo().start();
		}

	}
		


	public Login() {
		//Ventana principal
		setSize(350, 500);//tamaño ventana
		setLocationRelativeTo(null); //setbounds(x, y, w, h) para las dos primeras
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("login");
		setIconImage(Toolkit.getDefaultToolkit().getImage("../ProyectoFinal/src/pass.png"));
		setLayout(null);//importante para que funciones los botones que metemos
		setUndecorated(true);//quitar bordes
		inicializarComponentes();
		
		setVisible(true);//siempre en la ultima linea
		

		
	}
	
	public void inicializarComponentes() {

		Image img = new ImageIcon("icono_login.png").getImage();
		logo = new JLabel(new ImageIcon(img.getScaledInstance(80, 90, Image.SCALE_SMOOTH)));
		logo.setBounds(138, 90, 90, 90);
		add(logo);
		
		//damos color a la ventana
		getContentPane().setBackground(new Color(0, 191, 255));
		
		//inicializamos las etiquetas y cajas de texto
		Image img_name_user = new ImageIcon("name_user.png").getImage();
		setEtiqueta1(new JLabel(new ImageIcon(img_name_user.getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
		getEtiqueta1().setBounds(60, 200, 100, 30);
		getEtiqueta1().setFont(new Font("Dialog", Font.BOLD, 14));
		getEtiqueta1().setForeground(new Color(13,81,33));
		add(getEtiqueta1());
		
		setCajaTexto1(new RedondearCampos(80));
		getCajaTexto1().setBounds(140, 200, 100, 30);
		getCajaTexto1().setFont(new Font("Dialog", Font.BOLD, 16));//fuente
		getCajaTexto1().setForeground(new Color(13,81,33));//color letra
		add(getCajaTexto1());
		
		Image img_lock = new ImageIcon("lock.png").getImage();
		setEtiqueta2(new JLabel(new ImageIcon(img_lock.getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
		getEtiqueta2().setBounds(60, 240, 100, 30);
		getEtiqueta2().setFont(new Font("Dialog", Font.BOLD, 14));
		getEtiqueta2().setForeground(new Color(13,81,33));
		add(getEtiqueta2());
		
		setCajaTexto2(new Redondearcontraseña(80));
		getCajaTexto2().setBounds(140, 240, 100, 30);
		getCajaTexto2().setFont(new Font("Dialog", Font.BOLD, 16));//fuente
		getCajaTexto2().setForeground(new Color(13,81,33));//color letra
		add(getCajaTexto2());
		
		Image img_enter = new ImageIcon("enter2.png").getImage();
		setEtiqueta3(new JLabel(new ImageIcon(img_enter.getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
		getEtiqueta3().setBounds(60, 280, 100, 30);
		getEtiqueta3().setFont(new Font("Dialog", Font.BOLD, 14));
		getEtiqueta3().setForeground(new Color(13,81,33));
		add(getEtiqueta3());
		
		setBoton1(new RedondearBoton("Entrar"));
		getBoton1().setBounds(140, 280, 100, 30);
		getBoton1().setBackground(Color.WHITE);
		getBoton1().setFont(new Font("Dialog", Font.BOLD, 12));
		getBoton1().setForeground(new Color(13,81,33));//color letra
		getBoton1().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				getBoton1().setBackground(Color.green);				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				getBoton1().setBackground(Color.WHITE);				
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
		boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comprobarDatos();
				
			}
		});
		add(getBoton1());
		
		Image img_salir = new ImageIcon("salir2.png").getImage();
		setEtiqueta4(new JLabel(new ImageIcon(img_salir.getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
		getEtiqueta4().setBounds(60, 400, 100, 30);
		getEtiqueta4().setFont(new Font("Dialog", Font.BOLD, 14));
		getEtiqueta4().setForeground(new Color(13,81,33));
		add(getEtiqueta4());
		
		setSalir(new RedondearBoton("Salir"));
		getSalir().setBounds(140, 400, 100, 30);
		getSalir().setBackground(Color.WHITE);
		getSalir().setFont(new Font("Dialog", Font.BOLD, 12));
		getSalir().setForeground(new Color(13,81,33));//color letra				
		salir.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Apéndice de método generado automáticamente
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				salir.setBackground(Color.red);				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				salir.setBackground(Color.WHITE);				
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
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				dispose();
				
			}
			

		});
		
		add(getSalir());
	}

	
	//getter y setter
	public JLabel getEtiqueta1() {
		return etiqueta1;
	}

	public void setEtiqueta1(JLabel etiqueta1) {
		this.etiqueta1 = etiqueta1;
	}

	public JLabel getEtiqueta2() {
		return etiqueta2;
	}

	public void setEtiqueta2(JLabel etiqueta2) {
		this.etiqueta2 = etiqueta2;
	}

	public JTextField getCajaTexto1() {
		return cajaTexto1;
	}

	public void setCajaTexto1(JTextField cajaTexto1) {
		this.cajaTexto1 = cajaTexto1;
	}

	public JPasswordField getCajaTexto2() {
		return cajaTexto2;
	}

	public void setCajaTexto2(JPasswordField cajaTexto2) {
		this.cajaTexto2 = cajaTexto2;
	}

	public JButton getBoton1() {
		return boton1;
	}

	public void setBoton1(JButton boton1) {
		this.boton1 = boton1;
	}


	public JLabel getEtiqueta3() {
		return etiqueta3;
	}


	public void setEtiqueta3(JLabel etiqueta3) {
		this.etiqueta3 = etiqueta3;
	}


	public JButton getSalir() {
		return salir;
	}


	public void setSalir(JButton salir) {
		this.salir = salir;
	}


	public JLabel getEtiqueta4() {
		return etiqueta4;
	}


	public void setEtiqueta4(JLabel etiqueta4) {
		this.etiqueta4 = etiqueta4;
	}
	
  

}
