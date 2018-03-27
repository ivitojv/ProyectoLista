package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.TargetController;
import modelo.Tarjeta;
import utilities.Sesion;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class DetalleTarjeta extends JFrame {

	private JPanel contentPane;
	private JComboBox<Integer> date_d = new JComboBox<Integer>();

	/**
	 * Create the frame.
	 */
	public DetalleTarjeta(Tarjeta tarjeta, Sesion sesion) {
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
		
		JTextField title = new JTextField(tarjeta.title());
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


		JTextField description = new JTextField(tarjeta.comment());
		description.setBounds(212, 105, 212, 52);
		System.out.println("vista.MostrarTarjeta "+tarjeta.comment());
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
		
		
		date_d.setBounds(367, 69, 47, 20);
		panel.add(date_d);
		/*
		for(int i = 0; i < 31; i++) {
			date_d.addItem(i+1);
		}
		*/
		date_m.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int lastD = date_d.getSelectedIndex();
				date_d.removeAllItems();
				loadDays((int)date_m.getSelectedItem(),(int)date_y.getSelectedItem());
				if(date_d.getItemCount()>lastD)
					date_d.setSelectedIndex(lastD);
			}
			
		});
		
		date_y.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int lastD = date_d.getSelectedIndex();
				date_d.removeAllItems();
				loadDays((int)date_m.getSelectedItem(),(int)date_y.getSelectedItem());
				if(date_d.getItemCount()>lastD)
					date_d.setSelectedIndex(lastD);
			}
			
		});
		
		JLabel lblYearMonthDay = new JLabel("Year                        Month            Day");
		lblYearMonthDay.setBounds(212, 49, 202, 14);
		panel.add(lblYearMonthDay);
		date_y.setEnabled(false);
		date_m.setEnabled(false);
		date_d.setEnabled(false);
		
		JCheckBox chckbxActivar = new JCheckBox("Activar");
		chckbxActivar.setBounds(96, 68, 97, 23);
		panel.add(chckbxActivar);
		
		JLabel lblAsignadaA = new JLabel("Asignada a:");
		lblAsignadaA.setBounds(0, 168, 70, 14);
		panel.add(lblAsignadaA);
		
		JLabel authorLabel = new JLabel(tarjeta.author().name());
		authorLabel.setBounds(80, 166, 132, 14);
		panel.add(authorLabel);
		
		JCheckBox chckbxFinalizada = new JCheckBox("Finalizada");
		chckbxFinalizada.setBounds(317, 161, 97, 23);
		panel.add(chckbxFinalizada);
		chckbxFinalizada.setSelected(tarjeta.finalizada());
		
		chckbxActivar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxActivar.isSelected()) {
					System.out.println("DetaleTarjeta: ckbx->Selected");
					date_y.setEnabled(true);
					date_m.setEnabled(true);
					date_d.setEnabled(true);
				}else {
					System.out.println("DetaleTarjeta: ckbx->Not selected");
					date_y.setEnabled(false);
					date_m.setEnabled(false);
					date_d.setEnabled(false);
				}
			}
			
		});
		
		if(tarjeta.date()!= null) {
			chckbxActivar.setSelected(true);
			date_y.setEnabled(true);
			date_m.setEnabled(true);
			date_d.setEnabled(true);
			System.out.println(tarjeta.date().getYear());
			date_y.setSelectedIndex(tarjeta.date().getYear()-118);
			date_m.setSelectedIndex(tarjeta.date().getMonth());
			date_d.setSelectedIndex(tarjeta.date().getDate()-1);
		}
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(61, 227, 89, 23);
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("DetalleTarjeta "+title.getText());
				if(title.getText().length() > 0) {
					//System.out.println("DetalleTarjeta "+date.getText());
					if(chckbxActivar.isSelected()) {
						String date = date_y.getSelectedItem()+"-"+date_m.getSelectedItem()+"-"+ date_d.getSelectedItem();
						System.out.println("DetalleTarjeta "+date);
						if(TargetController.mod(tarjeta,title.getText(),date,description.getText(),chckbxFinalizada.isSelected())) {
							infLabel.setText("Tarjeta Guardada");
						}else infLabel.setText("ERROR: fecha fin >= fecha actual");
					}else {
						if(TargetController.mod(tarjeta,title.getText(),"",description.getText(),chckbxFinalizada.isSelected())) {
							System.out.println("DetalleTarjeta "+description.getText());
							infLabel.setText("Tarjeta Guardada");
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
				dispose();
			}
		});
		
	}
	
	private int daysOfMonth(int month) {
		switch(month) {
		case 1:
			return 31;
		case 2:
			return 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		}
		return -1;
	}
	
	private void loadDays (int month, int year){
		int i = 0;
		for(i = 0; i < daysOfMonth(month); i++) {
			date_d.addItem(i+1);
		}
		if(month==2 && year%4 == 0 && (year%100 != 0 || year%400 == 0))
			date_d.addItem(i+1);
	}
}
