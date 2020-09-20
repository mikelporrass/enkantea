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

public class LangileGUI extends JFrame {

	private JPanel contentPane;

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
	
	private JButton btnSesioaitxi;
	private JLabel lblLangilea ;
	private JButton btnEnkanteaItxi;
	private JLabel LabelIzena;
	private static final long serialVersionUID = 1L;
	public LangileGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 440);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnSesioaitxi = new JButton("Sesioa itxi");
		btnSesioaitxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HasieraGUI hasieraGUI=new HasieraGUI();
				hasieraGUI.setVisible(true);
			}
		});
		btnSesioaitxi.setBounds(487, 23, 97, 44);
		contentPane.add(btnSesioaitxi);
		
		lblLangilea = new JLabel("Langilea:");
		lblLangilea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLangilea.setBounds(192, 33, 80, 21);
		contentPane.add(lblLangilea);
		
		 btnEnkanteaItxi = new JButton("Enkantea Itxi");
		btnEnkanteaItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EnkanteaItxiGUI enkanteaItxiGUI=new EnkanteaItxiGUI();
				enkanteaItxiGUI.setVisible(true);
			}
		});
		btnEnkanteaItxi.setBounds(159, 156, 217, 93);
		contentPane.add(btnEnkanteaItxi);
		
		LabelIzena = new JLabel(getBusinessLogic().getLangile(gui.HasieraGUI.NANGorde).getIzena());
		LabelIzena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LabelIzena.setBounds(284, 13, 148, 56);
		contentPane.add(LabelIzena);
	}

}
