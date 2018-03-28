package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CrearPersona extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public CrearPersona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(114, 21, 123, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("");
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(29, 24, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(29, 96, 208, 14);
		contentPane.add(infLabel);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()>0) {
					if(PersonController.lookForPerson(textField.getText())== null) {
						PersonController.add(textField.getText());
						infLabel.setText("Nueva persona creada: "+ textField.getText());
					}else
						infLabel.setText("Esa persona ya existe en el sistema");

				}else
					infLabel.setText("Tiene que rellenar el campo nombre");

			}
		});
		btnAadir.setBounds(114, 213, 89, 23);
		contentPane.add(btnAadir);
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtras.setBounds(335, 213, 89, 23);
		contentPane.add(btnAtras);
		
		JButton btnNewButton = new JButton("Borrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()>0) {
					if(PersonController.lookForPerson(textField.getText())!= null) {
						PersonController.delete(textField.getText());
						infLabel.setText("Persona borrada: "+ textField.getText());
					}else
						infLabel.setText("Esa persona NO existe en el sistema");

				}else
					infLabel.setText("Tiene que rellenar el campo nombre");
			}
		});
		btnNewButton.setBounds(225, 213, 89, 23);
		contentPane.add(btnNewButton);
	}
}
