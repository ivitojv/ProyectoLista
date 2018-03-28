package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import controlador.ListController;
import utilities.*;

public class ModificarLista extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Sesion sesion;
	
	/**
	 * Create the frame.
	 */
	public ModificarLista(Sesion ss) {
		this.sesion = ss;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoNombre = new JLabel("Nuevo nombre:");
		lblNuevoNombre.setBounds(10, 11, 127, 14);
		contentPane.add(lblNuevoNombre);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(10, 74, 200, 14);
		contentPane.add(infLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 43, 200, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()>0) {					
					if(ListController.mod(sesion,textField.getText()))
						infLabel.setText("Lista modificada");
					else
						infLabel.setText("Existe una lista con ese nombre");
				}else
					infLabel.setText("Rellene el campo nombre");
			}
		});
		btnGuardar.setBounds(232, 42, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListController.callMenu(sesion);
				dispose();
			}
		});
		btnAtrs.setBounds(335, 42, 89, 23);
		contentPane.add(btnAtrs);
	}
}
