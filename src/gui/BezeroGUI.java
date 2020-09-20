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


public class BezeroGUI extends JFrame {

	

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

	/**
	 * Create the frame.
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDiruasartu;
	private JButton btnSesioaitxi;
	private JLabel lblNewLabel;
	private JButton EskaintzaEgin;
	private JButton btnSortuenkantea;
	private JButton btnEnkanteakBistaratu;
	private JButton btnIrabazitakoEnkanteak;
	private JLabel LabelIzena;
	
	public BezeroGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 430);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		 btnDiruasartu = new JButton("Dirua sartu");
		btnDiruasartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiruaSartuGUI diruaSartuGUI = new DiruaSartuGUI();
				diruaSartuGUI.setVisible(true);
			}
		});
		btnDiruasartu.setBounds(516, 74, 97, 25);
		contentPane.add(btnDiruasartu);

		 btnSesioaitxi = new JButton("Sesioa itxi");
		btnSesioaitxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HasieraGUI hasieraGUI = new HasieraGUI();
				hasieraGUI.setVisible(true);
			}
		});
		btnSesioaitxi.setBounds(506, 13, 108, 48);
		contentPane.add(btnSesioaitxi);

		 lblNewLabel = new JLabel("Bezeroa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(214, 15, 68, 41);
		contentPane.add(lblNewLabel);

		 EskaintzaEgin = new JButton("Eskaintza egin");
		EskaintzaEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EskaintzaEginGUI enkanteakIkusiGUI = new EskaintzaEginGUI();
				enkanteakIkusiGUI.setVisible(true);

			}
		});
		EskaintzaEgin.setBounds(12, 111, 295, 90);
		contentPane.add(EskaintzaEgin);

		 btnSortuenkantea = new JButton("Enkantea Sortu");
		btnSortuenkantea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				EnkanteaSortuGUI enkanteaSortuGUI = new EnkanteaSortuGUI();
				enkanteaSortuGUI.setVisible(true);
			}
		});
		btnSortuenkantea.setBounds(305, 112, 308, 89);
		contentPane.add(btnSortuenkantea);

		 btnEnkanteakBistaratu = new JButton("Enkanteak Bistaratu");
		btnEnkanteakBistaratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EnkanteakBistaratuGUI enkanteakBistaratu = new EnkanteakBistaratuGUI();
				enkanteakBistaratu.setVisible(true);

			}
		});
		btnEnkanteakBistaratu.setBounds(305, 198, 308, 90);
		contentPane.add(btnEnkanteakBistaratu);

		 btnIrabazitakoEnkanteak = new JButton("Irabazitako enkanteak");
		btnIrabazitakoEnkanteak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				IrabazitakoEnkanteakGUI irabazitakoEnkanteak = new IrabazitakoEnkanteakGUI();
				irabazitakoEnkanteak.setVisible(true);
			}
		});
		btnIrabazitakoEnkanteak.setBounds(12, 198, 295, 90);
		contentPane.add(btnIrabazitakoEnkanteak);
		LabelIzena = new JLabel(getBusinessLogic().getUser(gui.HasieraGUI.NANGorde).getIzena());
		LabelIzena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LabelIzena.setBounds(288, 7, 148, 56);
		contentPane.add(LabelIzena);
	}
}
