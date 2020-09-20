package gui;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Enkantea;
import domain.Jabea;
import domain.User;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class EnkanteakBistaratuGUI extends JFrame {



	
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
	private JLabel lblIzenaSartu;
	private JLabel lblDeskripzioaSartu;
	private JLabel labelError;
	private JButton btnAtzera;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	public EnkanteakBistaratuGUI() {
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

			
			}
		});
		scrollPane.setBounds(24, 75, 623, 173);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(table);
		tableModelUsers = new DefaultTableModel(null, columnUserNames);
		table.setModel(tableModelUsers);

		lblIzenaSartu = new JLabel("");
		lblIzenaSartu.setVisible(false);
		lblIzenaSartu.setBounds(158, 319, 114, 16);
		contentPane.add(lblIzenaSartu);

		lblDeskripzioaSartu = new JLabel("");
		lblDeskripzioaSartu.setVisible(false);
		lblDeskripzioaSartu.setBounds(158, 377, 158, 16);
		contentPane.add(lblDeskripzioaSartu);

		labelError = new JLabel("");
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelError.setForeground(Color.RED);
		labelError.setBounds(305, 368, 290, 25);
		contentPane.add(labelError);
		
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
		btnAtzera.setBounds(79, 261, 97, 25);
		contentPane.add(btnAtzera);
		
		lblNewLabel = new JLabel("Enkante guztiak");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(195, 13, 220, 31);
		contentPane.add(lblNewLabel);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		enkanteak = getBusinessLogic().getEnkanteak();
		for (Enkantea en : enkanteak) {
			int numCols = tableModelUsers.getColumnCount();
			Object[] row = new Object[numCols];
			row[0] = en.getEnkanteId();
			//row[1] = en.getJabea().getIzena();
			row[1] = getBusinessLogic().getEnkanteContainer(en).getJabea().getIzena();
			row[2] = en.getDeskripzioa();
			row[3] = en.getEgungoPrezioa();
			row[4] = en.getAmaieraData();
			tableModelUsers.addRow(row);
		}

	}
}
