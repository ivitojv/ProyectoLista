package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import controlador.TargetController;
import utilities.Sesion;
public class MenuPersona extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Sesion sesion;

	/**
	 * Create the frame.
	 */
	public MenuPersona(Sesion sesion) {
		this.sesion = sesion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTtulo = new JLabel("Título: ");
		lblTtulo.setBounds(20, 24, 46, 14);
		contentPane.add(lblTtulo);
		
		textField = new JTextField();
		textField.setBounds(144, 21, 197, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFechayyyymmdd = new JLabel("Fecha (YYYY-MM-DD): ");
		lblFechayyyymmdd.setBounds(20, 60, 114, 14);
		contentPane.add(lblFechayyyymmdd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 57, 197, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario: ");
		lblComentario.setBounds(20, 99, 70, 14);
		contentPane.add(lblComentario);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(144, 94, 197, 82);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(20, 193, 404, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnAadirTarjeta = new JButton("Añadir Tarjeta");
		btnAadirTarjeta.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() > 0) {
					if(TargetController.add(sesion.person(),textField.getText(),textField_1.getText(),textArea.getText())) {
						System.out.println("MenuPersona "+textArea.getText());
						lblNewLabel.setText("Tarjeta Creada");
					}
					else lblNewLabel.setText("Fallo en el campo fecha");
				}else lblNewLabel.setText("Es necesario que tenga título");
			}
		});
		btnAadirTarjeta.setBounds(20, 227, 114, 23);
		contentPane.add(btnAadirTarjeta);
		
		JButton btnMostrarTarjetas = new JButton("Mostrar Tarjetas");
		btnMostrarTarjetas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TargetController.callMostrarTarjeta(sesion);
			}
		});
		btnMostrarTarjetas.setBounds(163, 227, 114, 23);
		contentPane.add(btnMostrarTarjetas);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(310, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		
	}
}
