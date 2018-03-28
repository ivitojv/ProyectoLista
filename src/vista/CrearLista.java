package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import utilities.*;

import controlador.ListController;

public class CrearLista extends JFrame {

	private JPanel contentPane;
	private Sesion sesion;

	/**
	 * Create the frame.
	 */
	public CrearLista(Sesion ss) {
		this.sesion = ss;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(202, 21, 208, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("");
		
		JLabel lblNewLabel = new JLabel("Nombre nueva lista:");
		lblNewLabel.setBounds(29, 24, 123, 14);
		contentPane.add(lblNewLabel);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(29, 96, 381, 14);
		contentPane.add(infLabel);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()>0) {
					if(ListController.lookForList(sesion, textField.getText()) == null) {
						ListController.addLista(sesion,textField.getText());
						infLabel.setText("Nueva lista creada: "+ textField.getText());
					}else
						infLabel.setText("Existe una lista con el mismo nombre");

				}else
					infLabel.setText("Tiene que rellenar el campo nombre");

			}
		});
		btnAadir.setBounds(236, 213, 89, 23);
		contentPane.add(btnAadir);
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ListController.callMenu(sesion);
				dispose();
			}
		});
		btnAtras.setBounds(335, 213, 89, 23);
		contentPane.add(btnAtras);
	}

}
