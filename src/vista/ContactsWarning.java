package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ContactsWarning extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public ContactsWarning() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtencinSeEst = new JLabel("<html>ATENCIÓN: Se está intentando borrar un contacto con el que tiene listas<br/> compartidas. Si desea borrar este contacto de su agenda descomparta<br/>\r\nprimero las listas comunes con él</html>");
		lblAtencinSeEst.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtencinSeEst.setBounds(10, 11, 414, 56);
		contentPane.add(lblAtencinSeEst);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(162, 78, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}
}
