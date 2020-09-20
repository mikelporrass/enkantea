package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import businessLogic.BLFacade;
import domain.Bezero;
import domain.Jabea;
import domain.Langile;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class HasieraGUI extends JFrame {

	/**
	* 
	*/

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	public static String NANGorde = null;
	private JPanel contentPane;
	private JTextField textNAN;
	private JPasswordField passwordField;
	private JLabel lblNan;
	private JLabel lblPass;
	private JButton btnSartu;
	private JButton btnErregistratu;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	private static final long serialVersionUID = 1L;

	public HasieraGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 630, 500);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textNAN = new JTextField();
		textNAN.setBounds(57, 32, 116, 22);
		contentPane.add(textNAN);
		textNAN.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(237, 32, 116, 22);
		contentPane.add(passwordField);

		btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String NAN = textNAN.getText();
				String pass = new String(passwordField.getPassword());
				if (!getBusinessLogic().existitzenDa(NAN, pass))
					JOptionPane.showMessageDialog(null, "Erabiltzaile okerra.", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					NANGorde = NAN;
					User user = getBusinessLogic().getUser(NAN);
					System.out.println(user);
					System.out.println(user.getNAN());
					System.out.println(user.getPasahitza());
					System.out.println(user.getClass());
					if (user instanceof Bezero) {
						BezeroGUI bezero = new BezeroGUI();
						bezero.setVisible(true);
					} else if (user instanceof Jabea) {
						JabeGUI jabe = new JabeGUI();
						jabe.setVisible(true);
					} else {
						LangileGUI langile = new LangileGUI();
						langile.setVisible(true);
						
					}

					dispose();

				}
			}
		});
		btnSartu.setBounds(365, 31, 97, 25);
		contentPane.add(btnSartu);

		btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErregistratuGUI erregistratuGUI = new ErregistratuGUI();
				erregistratuGUI.setVisible(true);
				dispose();
			}
		});
		btnErregistratu.setBounds(484, 31, 116, 25);
		contentPane.add(btnErregistratu);

		lblNan = new JLabel("NAN");
		lblNan.setBounds(12, 35, 56, 16);
		contentPane.add(lblNan);

		lblPass = new JLabel("Pass");
		lblPass.setBounds(196, 35, 56, 16);
		contentPane.add(lblPass);
	}
}
