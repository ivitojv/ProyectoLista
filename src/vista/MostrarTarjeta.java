package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.TargetController;
import modelo.Tarjeta;
import utilities.Sesion;


import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class MostrarTarjeta extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private Sesion sesion;
	private Tarjeta selectedTarget;
	private List<Tarjeta> tarjetas;

	/**
	 * Create the frame.
	 */
	public MostrarTarjeta(List<Tarjeta> targets,Sesion sesion) {
		this.sesion = sesion;
		this.tarjetas = targets;
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
		
		JLabel label = new JLabel("");
		label.setBounds(173, 11, 251, 14);
		contentPane.add(label);

		scrollPane.setBounds(0, 36, 434, 182);
		contentPane.add(scrollPane);

		System.out.println("vista.MostrarTarjeta " + tarjetas.size());
		
		ButtonGroup group = new ButtonGroup();

		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
		for (Tarjeta tarjeta : tarjetas) {
			JPanel panel = new JPanel();
			
			//panel.setBounds(10, 79, 414, 83);
			panel.setLayout(new GridLayout(1,1));
			
			JRadioButton rdbt = new JRadioButton(tarjeta.title());
			panel.add(rdbt);
			rdbt.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					selectedTarget = tarjeta;
					System.out.println(tarjeta.title());
				}

				@Override
				public void focusLost(FocusEvent arg0) {
				}
				
			});
			group.add(rdbt);
			
			contenedor.add(panel);
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
		btnAtrs.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtrs);
		
		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.setBounds(232, 227, 89, 23);
		contentPane.add(btnDetalle);
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(group.getSelection());
				if(group.getSelection()!=null)
					TargetController.callDetalleTarjeta(selectedTarget,sesion);
				else
					label.setText("Debes escoger una tarjeta");
					
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(335, 227, 89, 23);
		contentPane.add(btnBorrar);	
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection()!=null) {
					TargetController.borrarTarjeta(selectedTarget);
					contenedor.remove(tarjetas.indexOf(selectedTarget));
					group.clearSelection();
					tarjetas = TargetController.getTarjetas(sesion);
					contenedor.updateUI();					
				}
				else
					label.setText("Debes escoger una tarjeta");
			}
		});
	}
}
