package vista;
import controlador.*;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnCrearPersona = new JButton("Crear persona");
		btnCrearPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonController.callCrearPersona();
			}
		});
		btnCrearPersona.setBounds(37, 156, 132, 23);
		frame.getContentPane().add(btnCrearPersona);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(37, 117, 387, 14);
		frame.getContentPane().add(infLabel);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PersonController.login(textField.getText()));		
				else infLabel.setText("Esa persona no está en el sistema");
			}
		});
		btnNewButton.setBounds(292, 68, 132, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblElijaUnaOpcin = new JLabel("Login:");
		lblElijaUnaOpcin.setBounds(37, 31, 60, 14);
		frame.getContentPane().add(lblElijaUnaOpcin);
		
		textField = new JTextField();
		textField.setBounds(37, 69, 235, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
	}
}