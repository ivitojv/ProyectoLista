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
import controlador.PersonController;
import controlador.TaskController;
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
		this.listas = lists;
		this.lShowed = lists;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTusListas = new JLabel("Tus Listas:");
		lblTusListas.setBounds(10, 11, 76, 14);
		contentPane.add(lblTusListas);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(98, 11, 184, 14);
		contentPane.add(infLabel);

		scrollPane.setBounds(0, 36, 293, 182);
		contentPane.add(scrollPane);

		System.out.println("vista.MenuPersona " + listas.size());
		
		loadContainer(lShowed);
		
		JComboBox<String> filters = new JComboBox<String>();
		filters.setBounds(292, 8, 129, 20);
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
					System.out.println("ERROR: MostrarTarea-> filtes.actionListener "+ filters.getSelectedItem());
				}
				
			}
			
		});
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtrs.setBounds(303, 227, 118, 23);
		contentPane.add(btnAtrs);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(303, 36, 121, 23);
		contentPane.add(btnMostrar);
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(group.getSelection());
				if(group.getSelection()!=null) {
					ListController.callMostrarLista(sesion,selectedList);
					dispose();
				}else
					infLabel.setText("Debes escoger una lista");
					
			}
		});
		
		
		JButton btnCrearLista = new JButton("Crear lista");
		btnCrearLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListController.callCrearLista(sesion);
				dispose();
			}
		});
		btnCrearLista.setBounds(10, 227, 124, 23);
		contentPane.add(btnCrearLista);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(group.getSelection()!=null) {
					if(ListController.callModificarLista(sesion,selectedList))
						dispose();
					else
						infLabel.setText("Debes ser el autor de la lista");	
				}
				else
					infLabel.setText("Debes escoger una lista");
					
			}
		});
		btnModificar.setBounds(303, 70, 118, 23);
		contentPane.add(btnModificar);
		
		JButton btnCompartir = new JButton("Compartir");
		btnCompartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(group.getSelection()!=null) {
					if(ListController.callCompartirLista(sesion,selectedList)) {
						dispose();
					}else
						infLabel.setText("Debes ser el autor de la lista");				
				}
				else
					infLabel.setText("Debes escoger una lista");
			}
		});
		btnCompartir.setBounds(303, 104, 118, 23);
		contentPane.add(btnCompartir);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(303, 138, 118, 23);
		contentPane.add(btnBorrar);	
		
		JButton btnContactos = new JButton("Contactos");
		btnContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonController.callContactos(sesion);
				dispose();
			}
		});
		btnContactos.setBounds(303, 172, 118, 23);
		contentPane.add(btnContactos);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection()!=null) {
					if(ListController.borrarLista(sesion,selectedList)) {
						contenedor.remove(lShowed.indexOf(selectedList));
						//System.out.println("MostrarTarea Borrar-> "+tShowed.size());
						listas.remove(listas.indexOf(selectedList));
						//System.out.println("MostrarTarea Borrar-> "+tShowed.size());
						group.clearSelection();
						contenedor.updateUI();		
					}else
						infLabel.setText("Debes ser el autor de la lista");	
				}
				else
					infLabel.setText("Debes escoger una lista");
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

