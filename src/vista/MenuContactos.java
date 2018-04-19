package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import utilities.*;
import controlador.*;
import modelo.Person;
import javax.swing.JTextField;

public class MenuContactos extends JFrame {

	private JPanel contentPane;
	private Sesion sesion;
	private JTextField textFieldAdd;
	private ArrayList<JCheckBox> group;
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel contenedor;
	/**
	 * Create the frame.
	 */
	public MenuContactos(Sesion ss) {
		sesion = ss;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldAdd = new JTextField();
		textFieldAdd.setBounds(10, 36, 280, 20);
		contentPane.add(textFieldAdd);
		textFieldAdd.setColumns(10);
		
		
		JLabel lblNewContact = new JLabel("Añade nuevos contactos:");
		lblNewContact.setBounds(10, 11, 405, 14);
		contentPane.add(lblNewContact);
		
		
		scrollPane.setBounds(10, 93, 280, 135);
		contentPane.add(scrollPane);
		
		loadContainer();
		
		JLabel infLabel = new JLabel("");
		infLabel.setBounds(10, 239, 405, 14);
		contentPane.add(infLabel);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(305, 171, 110, 23);
		contentPane.add(btnBorrar);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Person> ppl = new ArrayList<Person>();
				String inf = "";
				for(JCheckBox chckbx:group) {
					if(chckbx.isSelected()) {
						ppl.add(PersonController.lookForPerson(chckbx.getText()));
						System.out.println("MenuContactos: delete person "+chckbx.getText());
						inf += chckbx.getText() + " ";
					}
				}
				if(PersonController.deleteContact(sesion,ppl)) {
					loadContainer();
					infLabel.setText("Contactos borrados: "+inf);
				}else
					infLabel.setText("");
			}
		});
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(305, 205, 110, 23);
		contentPane.add(btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListController.callMenu(sesion);
				dispose();
			}
		});
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(305, 35, 110, 23);
		contentPane.add(btnAadir);	
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldAdd.getText().length() > 0)
					if(PersonController.addContact(sesion, textFieldAdd.getText())) {
						infLabel.setText(textFieldAdd.getText()+ " añadido a tus contactos");
						loadContainer();
					}else
						infLabel.setText("Esta persona no ha sido encontrada en el sistema");
				else
					infLabel.setText("Introduzca algo en el campo");
			}
		});
		
		JLabel lblSeleccionaLosContactos = new JLabel("Selecciona los contactos que desees borrar:");
		lblSeleccionaLosContactos.setBounds(10, 67, 405, 14);
		contentPane.add(lblSeleccionaLosContactos);
		
		
	}
	
	private void loadContainer() {
		contenedor = new JPanel();
		group = new ArrayList<JCheckBox>();
		contenedor.setLayout(new GridLayout(sesion.person().contactos().size(),1));
		
		for(int i = 0; i < sesion.person().contactos().size(); i++) {
			JCheckBox aux = new JCheckBox(sesion.person().contactos().apply(i).name(), false);
			contenedor.add(aux);
			group.add(aux);
		}
		scrollPane.setViewportView(contenedor);
		
	}

}
