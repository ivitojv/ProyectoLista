package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.TaskController;
import controlador.ListController;
import modelo.*;
import utilities.*;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class DetalleTarea extends JFrame {

	private JPanel contentPane;
	private Sesion sesion;

	/**
	 * Create the frame.
	 */
	public DetalleTarea(Tarea tarea, Sesion ss) {
		this.sesion = ss;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Título: ");
		lblNewLabel.setBounds(0, 1, 212, 52);
		//lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JTextField title = new JTextField(tarea.title());
		title.setBounds(212, 1, 212, 37);
		//title.setBounds(66, 11, 169, 14);
		panel.add(title);

		JLabel lblFecha = new JLabel("Fecha Fin:");
		lblFecha.setBounds(0, 53, 59, 52);
		//lblFecha.setBounds(245, 11, 46, 14);
		panel.add(lblFecha);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(0, 105, 212, 52);
		//lblDescripcin.setBounds(10, 43, 63, 14);
		panel.add(lblDescripcin);


		JTextField description = new JTextField(tarea.comment());
		description.setBounds(212, 105, 212, 52);
		System.out.println("vista.MostrarTarea "+tarea.comment());
		//description.setBounds(83, 43, 321, 29);
		panel.add(description);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(0, 191, 424, 20);
		panel.add(infLabel);
		
		JComboBox<Integer> date_y = new JComboBox<Integer>();
		date_y.setBounds(212, 69, 81, 20);
		panel.add(date_y);
		for(int i = 0; i < 100; i++) {
			date_y.addItem(i+2018);
		}
		
		
		JComboBox<Integer> date_m = new JComboBox<Integer>();
		date_m.setBounds(303, 69, 47, 20);
		panel.add(date_m);
		for(int i = 0; i < 12; i++) {
			date_m.addItem(i+1);
		}
		
		JComboBox<Integer> date_d = new JComboBox<Integer>();
		date_d.setBounds(367, 69, 47, 20);
		panel.add(date_d);
		for(int i = 0; i < 31; i++) {
			date_d.addItem(i+1);
		}
		
		date_m.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int lastD = date_d.getSelectedIndex();
				date_d.removeAllItems();
				int days = DateManage.daysOfMonth((int)date_m.getSelectedItem(),(int)date_y.getSelectedItem());
				for(int i = 0; i < days; i++) {
					date_d.addItem(i+1);
				}
				if(days > lastD)
					date_d.setSelectedIndex(lastD);
			}
			
		});
		
		date_y.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int lastD = date_d.getSelectedIndex();
				date_d.removeAllItems();
				int days = DateManage.daysOfMonth((int)date_m.getSelectedItem(),(int)date_y.getSelectedItem());
				for(int i = 0; i < days; i++) {
					date_d.addItem(i+1);
				}
				if(days > lastD)
					date_d.setSelectedIndex(lastD);
			}
			
		});
		
		JLabel lblYearMonthDay = new JLabel("Year                        Month            Day");
		lblYearMonthDay.setBounds(212, 49, 202, 14);
		panel.add(lblYearMonthDay);
		
		JLabel lblAsignadaA = new JLabel("Creada por:");
		lblAsignadaA.setBounds(0, 166, 75, 14);
		panel.add(lblAsignadaA);
		
		JLabel authorLabel = new JLabel(tarea.author().name());
		authorLabel.setBounds(80, 166, 132, 14);
		panel.add(authorLabel);
		
		JCheckBox chckbxFinalizada = new JCheckBox("Finalizada");
		chckbxFinalizada.setBounds(317, 161, 97, 23);
		panel.add(chckbxFinalizada);
		chckbxFinalizada.setSelected(tarea.finalizada());
		
		JCheckBox chckbxActivar = new JCheckBox("Activar");
		chckbxActivar.setBounds(96, 68, 97, 23);
		panel.add(chckbxActivar);	
		chckbxActivar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxActivar.isSelected()) {
					System.out.println("DetaleTarea: ckbx->Selected");
					date_y.setEnabled(true);
					date_m.setEnabled(true);
					date_d.setEnabled(true);
				}else {
					System.out.println("DetaleTarea: ckbx->Not selected");
					date_y.setEnabled(false);
					date_m.setEnabled(false);
					date_d.setEnabled(false);
				}
			}
			
		});
		
		if(tarea.date()!= null) {
			chckbxActivar.setSelected(true);
			date_y.setEnabled(true);
			date_m.setEnabled(true);
			date_d.setEnabled(true);
			System.out.println(tarea.date().get(Calendar.YEAR));
			date_y.setSelectedIndex(tarea.date().get(Calendar.YEAR)-2018);
			date_m.setSelectedIndex(tarea.date().get(Calendar.MONTH));
			
			date_d.removeAllItems();
			int days = DateManage.daysOfMonth((int)date_m.getSelectedItem(),(int)date_y.getSelectedItem());
			for(int i = 0; i < days; i++) {
				date_d.addItem(i+1);
			}
			
			date_d.setSelectedIndex(tarea.date().get(Calendar.DAY_OF_MONTH)-1);
			
		}else {
			date_y.setEnabled(false);
			date_m.setEnabled(false);
			date_d.setEnabled(false);
		}
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(61, 227, 89, 23);
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("DetalleTarea "+title.getText());
				if(title.getText().length() > 0) {
					//System.out.println("DetalleTarea "+date.getText());
					if(chckbxActivar.isSelected()) {
						String date = date_y.getSelectedItem()+"-"+date_m.getSelectedItem()+"-"+ date_d.getSelectedItem();
						System.out.println("DetalleTarea "+date);
						if(TaskController.mod(sesion,tarea,title.getText(),date,description.getText(),chckbxFinalizada.isSelected())) {
							infLabel.setText("Tarea Guardada");
						}else infLabel.setText("ERROR: fecha fin >= fecha actual");
					}else {
						if(TaskController.mod(sesion,tarea,title.getText(),"",description.getText(),chckbxFinalizada.isSelected())) {
							System.out.println("DetalleTarea "+description.getText());
							infLabel.setText("Tarea Guardada");
						}
					}				
				}else infLabel.setText("Es necesario que tenga título");
			}
		});
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(272, 227, 89, 23);
		contentPane.add(btnAtras);

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListController.callMostrarLista(sesion,sesion.lista());
				dispose();
			}
		});	
	}
}
