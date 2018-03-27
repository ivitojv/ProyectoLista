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
				PersonController.add();
				infLabel.setText("Nueva persona creada: "+getText());
			}
		});
		btnAadir.setBounds(227, 213, 89, 23);
		contentPane.add(btnAadir);
		JButton btnCancelar = new JButton("Atrás");
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 213, 89, 23);
		contentPane.add(btnCancelar);
	}
	public String getText() { return textField.getText();}

}
