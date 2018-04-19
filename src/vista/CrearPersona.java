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
	private JTextField textFieldName;
	private JTextField textFieldMail;

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
		
		textFieldName = new JTextField();
		textFieldName.setBounds(114, 21, 310, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(114, 52, 310, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(29, 24, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(29, 96, 395, 14);
		contentPane.add(infLabel);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textFieldName.getText().length() * textFieldMail.getText().length() > 0) {
					if(PersonController.lookForPerson(textFieldName.getText())== null) {
						if(PersonController.add(textFieldName.getText(), textFieldMail.getText()))
							infLabel.setText("Nueva persona creada: "+ textFieldName.getText()+ " Correo: "+textFieldMail.getText());
						else
							infLabel.setText("Correo Electrónico no válido");
					}else
						infLabel.setText("Esa persona ya existe en el sistema");

				}else
					infLabel.setText("Tiene que rellenar el campo nombre y mail");

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
				if(textFieldName.getText().length()>0) {
					if(PersonController.lookForPerson(textFieldName.getText())!= null) {
						PersonController.delete(textFieldName.getText());
						infLabel.setText("Persona borrada: "+ textFieldName.getText());
					}else
						infLabel.setText("Esa persona NO existe en el sistema");

				}else
					infLabel.setText("Tiene que rellenar el campo nombre");
			}
		});
		btnNewButton.setBounds(225, 213, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setBounds(29, 60, 46, 14);
		contentPane.add(lblCorreo);
	}
}
