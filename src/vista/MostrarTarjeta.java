package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.TargetController;
import modelo.Tarjeta;
import utilities.Sesion;


import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.UIManager;

public class MostrarTarjeta extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private Sesion sesion;

	/**
	 * Create the frame.
	 */
	public MostrarTarjeta(List<Tarjeta> tarjetas,Sesion sesion) {
		this.sesion = sesion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTusTarjetas = new JLabel("Tus Tarjetas:");
		lblTusTarjetas.setBounds(10, 11, 76, 14);
		contentPane.add(lblTusTarjetas);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));

		scrollPane.setBounds(0, 57, 434, 204);
		contentPane.add(scrollPane);

		System.out.println("vista.MostrarTarjeta " + tarjetas.size());

		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
		for (Tarjeta tarjeta : tarjetas) {
			JPanel panel = new JPanel();
			
			//panel.setBounds(10, 79, 414, 83);
			panel.setLayout(new GridLayout(4,2));

			JLabel lblNewLabel = new JLabel("Título: ");
			//lblNewLabel.setBounds(10, 11, 46, 14);
			panel.add(lblNewLabel);
			
			JLabel title = new JLabel(tarjeta.title());
			//title.setBounds(66, 11, 169, 14);
			panel.add(title);

			JLabel lblFecha = new JLabel("Fecha:");
			//lblFecha.setBounds(245, 11, 46, 14);
			panel.add(lblFecha);
			
			JLabel date = new JLabel(tarjeta.date().toGMTString());
			//date.setBounds(288, 11, 116, 14);
			panel.add(date);
			
			JLabel lblDescripcin = new JLabel("Descripción:");
			//lblDescripcin.setBounds(10, 43, 63, 14);
			panel.add(lblDescripcin);


			JLabel description = new JLabel(tarjeta.comment());
			System.out.println("vista.MostrarTarjeta "+tarjeta.comment());
			//description.setBounds(83, 43, 321, 29);
			panel.add(description);
			
			JButton btnDetalle = new JButton("Detalle");
			panel.add(btnDetalle);
			btnDetalle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TargetController.callDetalleTarjeta(tarjeta,sesion);
				}
			});
			
			JButton btnBorrar = new JButton("Borrar");
			panel.add(btnBorrar);
			contenedor.add(panel);
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TargetController.borrarTarjeta(tarjeta);
					contenedor.remove(panel);
					contenedor.updateUI();
				}
			});
			
				
		}
		
		scrollPane.setViewportView(contenedor);
		/*
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(320, 21, 89, 23);
		contentPane.add(btnBorrar);
*/
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtrs.setBounds(335, 23, 89, 23);
		contentPane.add(btnAtrs);
	}
}
