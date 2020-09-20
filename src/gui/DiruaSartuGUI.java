package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bezero;
import domain.Jabea;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private static final long serialVersionUID = 1L;
	private static BLFacade appFacadeInterface;
	private JTextField texDiruaSartu;
	private JLabel LabelError = new JLabel("");
	private JLabel lblDiruaSartuu;
	private JLabel lblDiruaSartu;
	private JButton btnAtzera;
	private JButton btnSartu;
	private JLabel LabelDirua;
	private JLabel lblDaukadanDirua;
	private static User user;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	/**
	 * Create the frame.
	 */
	private static float dirua;

	public DiruaSartuGUI() {
		// erabiltzailea gordetzen da
		user = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 394);

		
		 if (user instanceof Jabea) 
			 LabelDirua =new JLabel (Float.toString(((Jabea)user).getDiruZorroa())); 
		 else 
			 LabelDirua = new JLabel(Float.toString(((Bezero)user).getDiruZorroa())); 
		 
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDiruaSartuu = new JLabel("Dirua Sartu");
		lblDiruaSartuu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiruaSartuu.setBounds(208, 13, 122, 32);
		contentPane.add(lblDiruaSartuu);

		// User user = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);

		lblDiruaSartu = new JLabel("Dirua sartu");
		lblDiruaSartu.setBounds(58, 146, 112, 27);
		contentPane.add(lblDiruaSartu);

		texDiruaSartu = new JTextField();
		texDiruaSartu.setBounds(182, 148, 116, 22);
		contentPane.add(texDiruaSartu);
		texDiruaSartu.setColumns(10);

		btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtzera.setBounds(85, 261, 97, 25);
		contentPane.add(btnAtzera);

		btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					float diruZorroa = Float.parseFloat(texDiruaSartu.getText());		
					if (diruZorroa > 0) {
						getBusinessLogic().diruaSartu(user, diruZorroa);
						LabelDirua.setText(Float.toString(dirua + diruZorroa));
						texDiruaSartu.setText("");
						dispose();
						DiruaSartuGUI diruaSartuGUI = new DiruaSartuGUI();
						diruaSartuGUI.setVisible(true);
						JOptionPane.showMessageDialog(null, LabelDirua.getText() + " sartu dituzu", "Login Error",
								JOptionPane.INFORMATION_MESSAGE);

					} else
						LabelError.setText("0 baino gehiago sartu");
				} catch (Exception e1) {
					LabelError.setText("Zenbakia Sartu");
				}
			}
		});
		btnSartu.setBounds(261, 261, 97, 25);
		contentPane.add(btnSartu);

		LabelError.setForeground(Color.RED);
		LabelError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelError.setBounds(58, 201, 188, 27);
		contentPane.add(LabelError);

		lblDaukadanDirua = new JLabel("Daukadan Dirua:");
		lblDaukadanDirua.setBounds(55, 62, 115, 16);
		contentPane.add(lblDaukadanDirua);

		//LabelDirua = new JLabel("");
		LabelDirua.setBounds(169, 62, 97, 16);
		contentPane.add(LabelDirua);
	}
}
