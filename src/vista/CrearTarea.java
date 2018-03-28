package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.TaskController;
import controlador.ListController;
import utilities.*;

public class CrearTarea extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitle;
	private JTextField textFieldFecha;
	private Sesion sesion;



	public CrearTarea(Sesion ss) {
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
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(202, 21, 206, 20);
		contentPane.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblFechayyyymmdd = new JLabel("Fecha Fin: ");
		lblFechayyyymmdd.setBounds(20, 66, 75, 14);
		contentPane.add(lblFechayyyymmdd);
		/*
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(202, 57, 197, 20);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		*/
		JComboBox<Integer> date_y = new JComboBox<Integer>();
		date_y.setBounds(202, 63, 81, 20);
		contentPane.add(date_y);
		for(int i = 0; i < 100; i++) {
			date_y.addItem(i+2018);
		}
		
		
		JComboBox<Integer> date_m = new JComboBox<Integer>();
		date_m.setBounds(293, 63, 47, 20);
		contentPane.add(date_m);
		for(int i = 0; i < 12; i++) {
			date_m.addItem(i+1);
		}
		
		JComboBox<Integer> date_d = new JComboBox<Integer>();
		date_d.setBounds(361, 63, 47, 20);
		contentPane.add(date_d);
		for(int i = 0; i < 31; i++) {
			date_d.addItem(i+1);
		}
		
		date_y.setEnabled(false);
		date_m.setEnabled(false);
		date_d.setEnabled(false);
		
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
		lblYearMonthDay.setBounds(202, 47, 202, 14);
		contentPane.add(lblYearMonthDay);
		
		JCheckBox chckbxActivar = new JCheckBox("Activar");
		chckbxActivar.setBounds(101, 62, 97, 23);
		contentPane.add(chckbxActivar);	
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
		
		JLabel lblComentario = new JLabel("Comentario: ");
		lblComentario.setBounds(20, 99, 114, 14);
		contentPane.add(lblComentario);
		
		JTextArea comments = new JTextArea();
		comments.setBounds(202, 94, 206, 82);
		contentPane.add(comments);
		
		JLabel infLabel = new JLabel("");
		infLabel.setForeground(Color.red);
		infLabel.setBounds(20, 193, 404, 23);
		contentPane.add(infLabel);
		
		JButton btnAadirTarea = new JButton("Añadir Tarea");
		btnAadirTarea.setBounds(20, 227, 114, 23);
		contentPane.add(btnAadirTarea);
		btnAadirTarea.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textFieldTitle.getText().length() > 0) {
					if(chckbxActivar.isSelected()) {
						String date = date_y.getSelectedItem()+"-"+date_m.getSelectedItem()+"-"+ date_d.getSelectedItem();
						if(TaskController.add(sesion,textFieldTitle.getText(),date,comments.getText())) {
							infLabel.setText("Tarea Creada");
						}
						else infLabel.setText("Fallo en el campo fecha. Pista: fecha fin >= fecha actual");
					}else {
						if(TaskController.add(sesion,textFieldTitle.getText(),"",comments.getText()))
							infLabel.setText("Tarea Creada");
					}
				}else infLabel.setText("Es necesario que tenga título");
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(310, 227, 114, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ListController.callMostrarLista(sesion,sesion.lista());
				dispose();
			}
		});
	}

}
