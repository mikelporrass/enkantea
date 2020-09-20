package gui;


import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Bezero;
import domain.Enkantea;
import domain.EnkanteaContainer;
import domain.Jabea;
import domain.User;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class EskaintzaEginGUI extends JFrame {

	private static Integer i;

	private static final long serialVersionUID = 1L;
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	private JPanel contentPane;
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable();

	private static Vector<Enkantea> enkanteak;

	private DefaultTableModel tableModelUsers;
	private String[] columnUserNames = new String[] { "id","Jabea", "Deskripzioa", "Egungo Prezioa", "Amaiera data" };
	private JButton btnAtzera;
	private JButton btnEskaintzaEgin = new JButton("Eskaintza bat egin");
	private JLabel lblIzena;
	private JLabel lblDeskripzioa;
	private JLabel lblNewLabel;
	private JLabel lblIzenaSartu;
	private JLabel lblDeskripzioaSartu;
	private JLabel labelError;
	private JTextField textdirua;
	private JButton btnGorde;

	private static User user;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EskaintzaEginGUI() {
		// erabiltzailea gordetzen da
				user = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 690, 530);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnEskaintzaEgin.setEnabled(true);
				i = table.getSelectedRow();
				System.out.println(enkanteak.get(i).getEnkanteId());
			}
		});
		scrollPane.setBounds(12, 48, 648, 173);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(table);
		tableModelUsers = new DefaultTableModel(null, columnUserNames);
		table.setModel(tableModelUsers);

		btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);

				if (u instanceof Jabea) {
					JabeGUI jabeGUI = new JabeGUI();
					jabeGUI.setVisible(true);
				} else {
					BezeroGUI bezeroGUI = new BezeroGUI();
					bezeroGUI.setVisible(true);
				}
				dispose();

			}
		});
		btnAtzera.setBounds(105, 255, 97, 25);
		contentPane.add(btnAtzera);

		btnEskaintzaEgin.setEnabled(false);
		btnEskaintzaEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblIzena.setVisible(true);
				lblDeskripzioa.setVisible(true);
				lblNewLabel.setVisible(true);
				lblIzenaSartu.setVisible(true);
				lblDeskripzioaSartu.setVisible(true);
				btnGorde.setVisible(true);
				textdirua.setVisible(true);

				lblIzenaSartu.setText(getBusinessLogic().getEnkanteContainer(enkanteak.get(i)).getJabea().getIzena());
				lblDeskripzioaSartu.setText(enkanteak.get(i).getDeskripzioa());
			}
		});
		btnEskaintzaEgin.setBounds(320, 255, 144, 25);
		contentPane.add(btnEskaintzaEgin);

		lblIzena = new JLabel("Izena");
		lblIzena.setVisible(false);
		lblIzena.setBounds(49, 319, 83, 16);
		contentPane.add(lblIzena);

		lblDeskripzioa = new JLabel("Deskripzioa");
		lblDeskripzioa.setVisible(false);
		lblDeskripzioa.setBounds(35, 377, 97, 16);
		contentPane.add(lblDeskripzioa);

		lblNewLabel = new JLabel("Eskaintza egin");
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(35, 438, 114, 16);
		contentPane.add(lblNewLabel);

		lblIzenaSartu = new JLabel("");
		lblIzenaSartu.setVisible(false);
		lblIzenaSartu.setBounds(158, 319, 114, 16);
		contentPane.add(lblIzenaSartu);

		lblDeskripzioaSartu = new JLabel("");
		lblDeskripzioaSartu.setVisible(false);
		lblDeskripzioaSartu.setBounds(158, 377, 158, 16);
		contentPane.add(lblDeskripzioaSartu);

		textdirua = new JTextField();
		textdirua.setVisible(false);
		textdirua.setBounds(153, 435, 116, 22);
		contentPane.add(textdirua);
		textdirua.setColumns(10);

		btnGorde = new JButton("Eskaintza egin");
		btnGorde.setVisible(false);
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float dirua;
				try {
					dirua = Float.parseFloat(textdirua.getText());

					// float dirua = Float.parseFloat(textdirua.getText());
					User u = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);
					if (u instanceof Bezero) {
						if (((Bezero) u).getDiruZorroa() >= dirua) {
							if (getBusinessLogic().eskaintzaSortu(user, dirua, enkanteak.get(i).getEnkanteId())) {
								;
								System.out.println("Eskaintza egiten" + u.getNAN());
								dispose();
								EskaintzaEginGUI enkanteakIkusiGUI = new EskaintzaEginGUI();
								enkanteakIkusiGUI.setVisible(true);
							} else
								labelError.setText("Diru minimoa baino gehiago jarri");
						} else
							labelError.setText("Ez daukazu dirurik");
					} else {
						if (((Jabea) user).getDiruZorroa() >= dirua) {
							if (getBusinessLogic().eskaintzaSortu(user, dirua, enkanteak.get(i).getEnkanteId())) {
								;
								System.out.println("Eskaintza egiten" + user.getNAN());
								dispose();
								EskaintzaEginGUI enkanteakIkusiGUI = new EskaintzaEginGUI();
								enkanteakIkusiGUI.setVisible(true);
							} else
								labelError.setText("Diru minimoa baino gehiago jarri");
						} else
							labelError.setText("Ez daukazu dirurik");

					}
				} catch (Exception e1) {
					labelError.setText("Zenbakia sartu");
				}
			}

		});
		btnGorde.setBounds(345, 434, 149, 25);
		contentPane.add(btnGorde);

		labelError = new JLabel("");
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelError.setForeground(Color.RED);
		labelError.setBounds(305, 368, 290, 25);
		contentPane.add(labelError);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		enkanteak = getBusinessLogic().getBesteenEnkanteak(gui.HasieraGUI.NANGorde);
		for (Enkantea en : enkanteak) {
			int numCols = tableModelUsers.getColumnCount();
			Object[] row = new Object[numCols];
			row[0] = en.getEnkanteId();
			row[1] = getBusinessLogic().getEnkanteContainer(en).getJabea().getIzena();
			row[2] = en.getDeskripzioa();
			row[3] = en.getEgungoPrezioa();
			row[4] = en.getAmaieraData();
			tableModelUsers.addRow(row);
		}

	}
}
