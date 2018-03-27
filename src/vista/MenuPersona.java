package vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import controlador.ListController;
import controlador.TargetController;
import modelo.*;
import utilities.Sesion;
public class MenuPersona extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private Sesion sesion;
	private ListaT selectedList;
	private List<ListaT> listas;
	private List<ListaT> lShowed;
	private JPanel contenedor;
	private ButtonGroup group = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public MenuPersona(Sesion ss, List<ListaT> lists) {
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
		/*
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
		*/
		this.listas = lists;
		this.lShowed = lists;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTusTarjetas = new JLabel("Tus Listas:");
		lblTusTarjetas.setBounds(10, 11, 76, 14);
		contentPane.add(lblTusTarjetas);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(98, 11, 184, 14);
		contentPane.add(infLabel);

		scrollPane.setBounds(0, 36, 434, 182);
		contentPane.add(scrollPane);

		System.out.println("vista.MenuPersona " + listas.size());
		
		loadContainer(lShowed);
		
		JComboBox<String> filters = new JComboBox<String>();
		filters.setBounds(305, 8, 129, 20);
		contentPane.add(filters);
		filters.addItem("Ordenación");
		filters.addItem("Ord. A-Z");
		filters.addItem("Ord. Z-A");
		
		filters.setSelectedIndex(0);
		
		filters.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch((String)filters.getSelectedItem()) {
				case "Ordenación":
					break;
				case "Ord. A-Z":
					lShowed = ListController.ordFiltL(listas, ListController.AZ());
					loadContainer(lShowed);
					group.clearSelection();
					break;
				case "Ord. Z-A":
					lShowed = ListController.ordFiltL(listas, ListController.ZA());
					loadContainer(lShowed);
					group.clearSelection();
					break;
				default:
					System.out.println("ERROR: MostrarTarjeta-> filtes.actionListener "+ filters.getSelectedItem());
				}
				
			}
			
		});
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtrs.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtrs);
		
		JButton btnDetalle = new JButton("Mostrar");
		btnDetalle.setBounds(232, 227, 89, 23);
		contentPane.add(btnDetalle);
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(group.getSelection());
				if(group.getSelection()!=null)
					TargetController.callMostrarTarjeta(selectedList,sesion);
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
					ListController.borrarLista(selectedList);
					contenedor.remove(lShowed.indexOf(selectedList));
					//System.out.println("MostrarTarjeta Borrar-> "+tShowed.size());
					listas.remove(listas.indexOf(selectedList));
					//System.out.println("MostrarTarjeta Borrar-> "+tShowed.size());
					group.clearSelection();
					contenedor.updateUI();					
				}
				else
					infLabel.setText("Debes escoger una tarjeta");
			}
		});
	}
	private void loadContainer(List<ListaT> content) {
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
		for (ListaT lts : content) {
			JPanel panel = new JPanel();
			
			//panel.setBounds(10, 79, 414, 83);
			panel.setLayout(new GridLayout(1,1));
			
			JRadioButton rdbt = new JRadioButton(lts.name());
			panel.add(rdbt);
			rdbt.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					selectedList = lts;
					System.out.println(lts.name());
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

