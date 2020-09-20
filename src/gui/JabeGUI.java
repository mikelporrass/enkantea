package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JabeGUI extends JFrame {

	/**
	 * Launch the application.
	 */

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	private JPanel contentPane;
	private JButton btnSaioaitxi;
	private JButton btnNireEnkanteak;
	private JButton btnEnkanteasortu;
	private JLabel lblJabea;
	private JButton EskaintzaEgin;
	private JButton btnDiruaSartu;
	private JButton btnEnkanteakBistaratu;
	private JButton btnIrabazitakoEnkanteak;
	/**
	 * Create the frame.
	 */
	private static final long serialVersionUID = 1L;
	private JLabel LabelIzena;

	public JabeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 420);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSaioaitxi = new JButton("Saioa itxi");
		btnSaioaitxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HasieraGUI hasieraGUI = new HasieraGUI();
				hasieraGUI.setVisible(true);
			}
		});
		btnSaioaitxi.setBounds(486, 13, 124, 45);
		contentPane.add(btnSaioaitxi);

		lblJabea = new JLabel("Jabea:");
		lblJabea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblJabea.setBounds(213, 13, 66, 56);
		contentPane.add(lblJabea);

		btnNireEnkanteak = new JButton("Nire enkanteak");
		btnNireEnkanteak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NireEnkanteakIkusiGUI nireEnkanteakIkusiGUI = new NireEnkanteakIkusiGUI();
				nireEnkanteakIkusiGUI.setVisible(true);
				dispose();
			}
		});
		btnNireEnkanteak.setBounds(12, 110, 293, 68);
		contentPane.add(btnNireEnkanteak);

		btnEnkanteasortu = new JButton("EnkanteaSortu");
		btnEnkanteasortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EnkanteaSortuGUI enkanteaSortuGUI = new EnkanteaSortuGUI();
				enkanteaSortuGUI.setVisible(true);

			}
		});
		btnEnkanteasortu.setBounds(12, 176, 293, 62);
		contentPane.add(btnEnkanteasortu);

		EskaintzaEgin = new JButton("Eskaintza egin");
		EskaintzaEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EskaintzaEginGUI eskaintzaEginGUI = new EskaintzaEginGUI();
				eskaintzaEginGUI.setVisible(true);
				dispose();

			}
		});
		EskaintzaEgin.setBounds(304, 110, 306, 68);
		contentPane.add(EskaintzaEgin);

		btnDiruaSartu = new JButton("Dirua sartu");
		btnDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiruaSartuGUI diruaSartuGUI = new DiruaSartuGUI();
				diruaSartuGUI.setVisible(true);
			}
		});
		btnDiruaSartu.setBounds(513, 71, 97, 25);
		contentPane.add(btnDiruaSartu);

		btnEnkanteakBistaratu = new JButton("Enkanteak Bistaratu");
		btnEnkanteakBistaratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EnkanteakBistaratuGUI enkanteakBistaratu = new EnkanteakBistaratuGUI();
				enkanteakBistaratu.setVisible(true);
			}
		});
		btnEnkanteakBistaratu.setBounds(304, 176, 306, 62);
		contentPane.add(btnEnkanteakBistaratu);

		btnIrabazitakoEnkanteak = new JButton("Irabazitako enkanteak");
		btnIrabazitakoEnkanteak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				IrabazitakoEnkanteakGUI irabazitakoEnkanteak = new IrabazitakoEnkanteakGUI();
				irabazitakoEnkanteak.setVisible(true);
			}
		});
		btnIrabazitakoEnkanteak.setBounds(12, 238, 598, 62);
		contentPane.add(btnIrabazitakoEnkanteak);
		
		LabelIzena = new JLabel(getBusinessLogic().getUser(gui.HasieraGUI.NANGorde).getIzena());
		LabelIzena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LabelIzena.setBounds(284, 13, 148, 56);
		contentPane.add(LabelIzena);
	}
}
