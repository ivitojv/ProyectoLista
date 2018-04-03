package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.CheckboxGroup;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utilities.*;
import modelo.*;
import controlador.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CompartirLista extends JFrame {

	private JPanel contentPane;
	private Sesion sesion;
	
	/**
	 * Create the frame.
	 */
	public CompartirLista(Sesion ss) {
		sesion = ss;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<JCheckBox> group = new ArrayList<JCheckBox>();
		
		JLabel lblMarcaConQuien = new JLabel("Des/Marca con quien quieres des/compartir tu lista:");
		lblMarcaConQuien.setBounds(10, 11, 405, 14);
		contentPane.add(lblMarcaConQuien);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 280, 192);
		contentPane.add(scrollPane);
		
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new GridLayout(sesion.person().contactos().size(),1));
		scrollPane.add(contenedor);
		
		for(int i = 0; i < sesion.person().contactos().size(); i++) {
			JCheckBox aux = new JCheckBox(sesion.person().contactos().apply(i).name(), sesion.lista().isSharedTo(sesion.person().contactos().apply(i)));
			contenedor.add(aux);
			group.add(aux);	
		}
		scrollPane.setViewportView(contenedor);
		
		
		JLabel infLabel = new JLabel("");
		infLabel.setBounds(10, 239, 245, 14);
		contentPane.add(infLabel);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(305, 36, 110, 23);
		contentPane.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Person> ppl = new ArrayList<Person>();
				String inf = "";
				for(JCheckBox chckbx:group) {
					if(chckbx.isSelected()) {
						ppl.add(PersonController.lookForPerson(chckbx.getText()));
						System.out.println("CompartirLista: added new share "+chckbx.getText());
						inf += chckbx.getText() + " ";
					}
				}
				ListController.shareList(sesion,ppl);
				
				infLabel.setText("Lista compartida con: "+inf);
			}
		});
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(305, 70, 110, 23);
		contentPane.add(btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListController.callMenu(sesion);
				dispose();
			}
		});
		
		
	}
}
