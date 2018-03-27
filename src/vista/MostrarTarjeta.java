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
import javax.swing.JComboBox;

public class MostrarTarjeta extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private Sesion sesion;
	private Tarjeta selectedTarget;
	private List<Tarjeta> tarjetas;
	private List<Tarjeta> tShowed;
	private JPanel contenedor;
	private ButtonGroup group = new ButtonGroup();
	/**
	 * Create the frame.
	 */
	public MostrarTarjeta(List<Tarjeta> targets,Sesion sesion) {
		this.sesion = sesion;
		this.tarjetas = targets;
		this.tShowed = targets;
		
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
		
		JLabel infLabel = new JLabel("");
		infLabel.setBounds(98, 11, 227, 14);
		contentPane.add(infLabel);

		scrollPane.setBounds(0, 36, 434, 182);
		contentPane.add(scrollPane);

		System.out.println("vista.MostrarTarjeta " + tarjetas.size());
		
		loadContainer(tShowed);
		
		JComboBox<String> filters = new JComboBox<String>();
		filters.setBounds(335, 8, 99, 20);
		contentPane.add(filters);
		filters.addItem("Ordenación");
		filters.addItem("Ord. A-Z");
		filters.addItem("Ord. Z-A");
		filters.addItem("Ord. Fecha Fin");
		filters.addItem("Finalizadas");
		
		filters.setSelectedIndex(0);
		
		filters.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch((String)filters.getSelectedItem()) {
				case "Ordenación":
					break;
				case "Ord. A-Z":
					tShowed = TargetController.ordFiltT(tarjetas, TargetController.AZ());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				case "Ord. Z-A":
					tShowed = TargetController.ordFiltT(tarjetas, TargetController.ZA());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				case "Ord. Fecha Fin":
					tShowed = TargetController.ordFiltT(tarjetas, TargetController.DATE());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				case "Finalizadas":
					tShowed = TargetController.ordFiltT(tarjetas, TargetController.END());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				default:
					System.out.println("ERROR: MostrarTarjeta-> filtes.actionListener "+ filters.getSelectedItem());
				}
				
			}
			
		});
		
		
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
					infLabel.setText("Debes escoger una tarjeta");
					
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(335, 227, 89, 23);
		contentPane.add(btnBorrar);	
		
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection()!=null) {
					TargetController.borrarTarjeta(selectedTarget);
					contenedor.remove(tShowed.indexOf(selectedTarget));
					tarjetas.remove(tarjetas.indexOf(selectedTarget));
					tShowed.remove(tShowed.indexOf(selectedTarget));
					group.clearSelection();
					contenedor.updateUI();					
				}
				else
					infLabel.setText("Debes escoger una tarjeta");
			}
		});
	}
	private void loadContainer(List<Tarjeta> content) {
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
		for (Tarjeta tarjeta : content) {
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
	}
}
