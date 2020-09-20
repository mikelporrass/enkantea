package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ErregistratuGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	private JPanel contentPane;
	private JTextField textIzena;
	private JPasswordField passwordField;
	private JTextField textNAN;
	private JButton btnErregistratu;
	private JButton btnAtzera;
	private JLabel LabelError;
	private JLabel lblIzena;
	private JLabel lblPasahitza;
	private JLabel lblNan;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textFieldAb1;
	private JTextField textFieldAb2;
	private JTextField textFieldEmail;

	// private JCalendar jCalendar1 = new JCalendar();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ErregistratuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 488);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textIzena = new JTextField();
		textIzena.setBounds(122, 144, 116, 22);
		contentPane.add(textIzena);
		textIzena.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(122, 98, 116, 22);
		contentPane.add(passwordField);

		textNAN = new JTextField();
		textNAN.setBounds(122, 46, 116, 22);
		contentPane.add(textNAN);
		textNAN.setColumns(10);

		 lblIzena = new JLabel("Izena:");
		lblIzena.setBounds(47, 147, 56, 16);
		contentPane.add(lblIzena);

		 lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(47, 101, 80, 16);
		contentPane.add(lblPasahitza);

		 lblNan = new JLabel("NAN:");
		lblNan.setBounds(47, 49, 56, 16);
		contentPane.add(lblNan);

		

		btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NAN = textNAN.getText();
				String pass = new String(passwordField.getPassword());
				String izena = textIzena.getText();
				String ab1=textFieldAb1.getText();
				String ab2=textFieldAb2.getText();;
				String email=textFieldEmail.getText();

				if (NAN.length()>0 && pass.length()>0 && izena.length()>0) {
					if (getBusinessLogic().erregistratu(NAN, pass, izena, ab1, ab2, email)) {
						JOptionPane.showMessageDialog(null, NAN + "Sortu da", "Erregistratu",
								JOptionPane.ERROR_MESSAGE);
						HasieraGUI hasieraGUI = new HasieraGUI();
						hasieraGUI.setVisible(true);
						dispose();
					} else
						LabelError.setText("Erabiltzailea existitzen da");

				} else
					LabelError.setText("Datuak sartu");
			}
		});
		btnErregistratu.setBounds(354, 335, 116, 25);
		contentPane.add(btnErregistratu);

		btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HasieraGUI hasieraGUI = new HasieraGUI();
				hasieraGUI.setVisible(true);
			}
		});
		btnAtzera.setBounds(221, 335, 97, 25);
		contentPane.add(btnAtzera);

		LabelError = new JLabel("");
		LabelError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelError.setForeground(Color.RED);
		LabelError.setBounds(47, 230, 211, 35);
		contentPane.add(LabelError);
		
		lblNewLabel = new JLabel("Abizena1:");
		lblNewLabel.setBounds(284, 49, 75, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Abizena2:");
		lblNewLabel_1.setBounds(284, 101, 75, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(284, 147, 75, 16);
		contentPane.add(lblNewLabel_2);
		
		textFieldAb1 = new JTextField();
		textFieldAb1.setBounds(354, 46, 116, 22);
		contentPane.add(textFieldAb1);
		textFieldAb1.setColumns(10);
		
		textFieldAb2 = new JTextField();
		textFieldAb2.setBounds(354, 98, 116, 22);
		contentPane.add(textFieldAb2);
		textFieldAb2.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(354, 144, 116, 22);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
	}
}
