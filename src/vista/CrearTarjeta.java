package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.TargetController;
import controlador.ListController;
import utilities.Sesion;

public class CrearTarjeta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Sesion sesion;



	public CrearTarjeta(Sesion ss) {
		this.sesion = ss;
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
		textField.setBounds(202, 21, 197, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFechayyyymmdd = new JLabel("Fecha Fin(YYYY-MM-DD): ");
		lblFechayyyymmdd.setBounds(20, 60, 172, 14);
		contentPane.add(lblFechayyyymmdd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(202, 57, 197, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario: ");
		lblComentario.setBounds(20, 99, 114, 14);
		contentPane.add(lblComentario);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(202, 94, 197, 82);
		contentPane.add(textArea);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(20, 193, 404, 23);
		contentPane.add(infLabel);
		
		JButton btnAadirTarjeta = new JButton("Añadir Tarjeta");
		btnAadirTarjeta.setBounds(20, 227, 114, 23);
		contentPane.add(btnAadirTarjeta);
		btnAadirTarjeta.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() > 0) {
					if(TargetController.add(sesion,textField.getText(),textField_1.getText(),textArea.getText())) {
						System.out.println("CrearTarjeta "+textArea.getText());
						infLabel.setText("Tarjeta Creada");
					}
					else infLabel.setText("Fallo en el campo fecha. Pista: fecha fin >= fecha actual");
				}else infLabel.setText("Es necesario que tenga título");
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(310, 227, 114, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ListController.callMostrarLista(sesion.lista(), sesion);
				dispose();
			}
		});
	}

}
