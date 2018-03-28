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
import javax.swing.border.EmptyBorder;

import controlador.TaskController;
import modelo.*;
import utilities.Sesion;


import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class MostrarLista extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private Sesion sesion;
	private Tarea selectedTask;
	private List<Tarea> tareas;
	private List<Tarea> tShowed;
	private JPanel contenedor;
	private ButtonGroup group = new ButtonGroup();
	/**
	 * Create the frame.
	 */
	public MostrarLista(List<Tarea> tasks,Sesion ss) {
		this.sesion = ss;
		this.tareas = tasks;
		this.tShowed = tasks;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTusTareas = new JLabel("Tus Tareas:");
		lblTusTareas.setBounds(10, 11, 76, 14);
		contentPane.add(lblTusTareas);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(98, 11, 184, 14);
		contentPane.add(infLabel);

		scrollPane.setBounds(0, 36, 434, 182);
		contentPane.add(scrollPane);

		System.out.println("vista.MostrarTarea " + tareas.size());
		
		loadContainer(tShowed);
		
		JComboBox<String> filters = new JComboBox<String>();
		filters.setBounds(305, 8, 129, 20);
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
					tShowed = TaskController.ordFiltT(tareas, TaskController.AZ());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				case "Ord. Z-A":
					tShowed = TaskController.ordFiltT(tareas, TaskController.ZA());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				case "Ord. Fecha Fin":
					tShowed = TaskController.ordFiltT(tareas, TaskController.DATE());
					loadContainer(tShowed);
					group.clearSelection();
					break;
				case "Finalizadas":
					tShowed = TaskController.ordFiltT(tareas, TaskController.END());
					loadContainer(tShowed);
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
		btnAtrs.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtrs);
		
		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.setBounds(232, 227, 89, 23);
		contentPane.add(btnDetalle);
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(group.getSelection());
				if(group.getSelection()!=null) {
					TaskController.callDetalleTarea(sesion,selectedTask);
					dispose();
				}else
					infLabel.setText("Debes escoger una tarea");
					
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(335, 227, 89, 23);
		contentPane.add(btnBorrar);	
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection()!=null) {
					contenedor.remove(tShowed.indexOf(selectedTask));
					//System.out.println("MostrarTarea Borrar-> "+tShowed.size());
					tareas.remove(tareas.indexOf(selectedTask));
					//System.out.println("MostrarTarea Borrar-> "+tShowed.size());
					TaskController.borrarTarea(sesion,selectedTask);
					group.clearSelection();
					contenedor.updateUI();					
				}
				else
					infLabel.setText("Debes escoger una tarea");
			}
		});
		
		JButton btnCrearTarea = new JButton("Crear tarea");
		btnCrearTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskController.callCrearTarea(sesion);
				dispose();
			}
		});
		btnCrearTarea.setBounds(109, 227, 113, 23);
		contentPane.add(btnCrearTarea);
		
	}
	private void loadContainer(List<Tarea> content) {
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
		for (Tarea tarea : content) {
			JPanel panel = new JPanel();
			
			//panel.setBounds(10, 79, 414, 83);
			panel.setLayout(new GridLayout(1,1));
			
			JRadioButton rdbt = new JRadioButton(tarea.title());
			panel.add(rdbt);
			rdbt.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					selectedTask = tarea;
					System.out.println(tarea.title());
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
