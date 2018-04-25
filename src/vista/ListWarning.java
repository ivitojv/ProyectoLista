package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilities.Sesion;
import controlador.*;

import javax.swing.JLabel;
import javax.swing.JButton;

public class ListWarning extends JFrame {

	private JPanel contentPane;
	private Sesion sesion;
	/**
	 * Create the frame.
	 */
	public ListWarning(Sesion ss) {
		sesion = ss;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblestasSeguroDe = new JLabel("¿Estas seguro de que quieres borrar esta lista?");
		lblestasSeguroDe.setBounds(100, 11, 290, 14);
		contentPane.add(lblestasSeguroDe);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(50, 63, 89, 23);
		contentPane.add(btnBorrar);
		btnBorrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ListController.borrarLista(sesion,sesion.lista());
				ListController.callMenu(sesion);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(301, 63, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ListController.callMenu(sesion);
				dispose();
			}
		});
	}
}
