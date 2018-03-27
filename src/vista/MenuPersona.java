package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import controlador.TargetController;
import modelo.Tarjeta;
import utilities.Sesion;
public class MenuPersona extends JFrame {

	private JPanel contentPane;

	private Sesion sesion;

	/**
	 * Create the frame.
	 */
	public MenuPersona(Sesion ss, List<List<Tarjeta>> listas) {
		this.sesion = ss;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/*JButton btnMostrarTarjetas = new JButton("Mostrar Tarjetas");
		btnMostrarTarjetas.setBounds(173, 227, 114, 23);
		contentPane.add(btnMostrarTarjetas);
		btnMostrarTarjetas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TargetController.callMostrarTarjeta(sesion);
			}
		});*/
		
		JButton btnMostrarLista = new JButton("Mostrar Lista");
		btnMostrarLista.setBounds(173, 227, 114, 23);
		contentPane.add(btnMostrarLista);
		btnMostrarLista.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TargetController.callMostrarTarjeta(sesion);
			}
		});
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(310, 227, 114, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		
	}
}
