package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Jabea;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import com.toedter.calendar.JCalendar;
import java.awt.Font;
import java.awt.Color;

public class EnkanteaSortuGUI extends JFrame {

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	private JPanel contentPane;
	private JTextField txtDeskripzioa;
	private JTextField txtPrezioMin;
	private JCalendar jCalendar = new JCalendar();
	private Date BukaerakoData = null;
	private JLabel LabelError = new JLabel("");
	private JTextField LabelData;
	private JButton btnAtzera;
	private JButton btnSortu;
	private JLabel lblDeskripzioa;
	private JLabel lblPrezioMinimoa;
	private JLabel lblData;
	
	private static User user;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	private static final long serialVersionUID = 1L;

	public EnkanteaSortuGUI() {

		// erabiltzailea gordetzen da
		user = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 464);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtDeskripzioa = new JTextField();
		txtDeskripzioa.setBounds(142, 66, 116, 22);
		contentPane.add(txtDeskripzioa);
		txtDeskripzioa.setColumns(10);

		txtPrezioMin = new JTextField();
		txtPrezioMin.setBounds(142, 125, 116, 22);
		contentPane.add(txtPrezioMin);
		txtPrezioMin.setColumns(10);

		btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//User u = getBusinessLogic().getUser(gui.HasieraGUI.NANGorde);

				if (user instanceof Jabea) {
					JabeGUI jabeGUI = new JabeGUI();
					jabeGUI.setVisible(true);
				} else {
					BezeroGUI bezeroGUI = new BezeroGUI();
					bezeroGUI.setVisible(true);
				}
				dispose();
			}
		});
		btnAtzera.setBounds(161, 305, 97, 25);
		contentPane.add(btnAtzera);

		btnSortu = new JButton("Sortu");
		btnSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deskripzioa = txtDeskripzioa.getText();
				if (deskripzioa.length() > 0 && BukaerakoData != null) {
					try {
						float prezioMin = Float.parseFloat(txtPrezioMin.getText());
						// Date amaierakoData = null;
						if (BukaerakoData.after(new Date())) {
							getBusinessLogic().enkanteaSortu(user,deskripzioa, prezioMin, BukaerakoData);
							JOptionPane.showMessageDialog(null, "Enkantea sortuta", "Enkantea sortuta",
									JOptionPane.ERROR_MESSAGE);
							dispose();
							JabeGUI jabeaGUI = new JabeGUI();
							jabeaGUI.setVisible(true);
						} else
							LabelError.setText("Iraganeko data aukeratu");
					} catch (Exception e1) {
						LabelError.setText("Prezioa zenbakia izan behar da");
					}
				} else
					LabelError.setText("Datuak sartu");
			}
		});
		btnSortu.setBounds(278, 305, 97, 25);
		contentPane.add(btnSortu);

		lblDeskripzioa = new JLabel("Deskripzioa");
		lblDeskripzioa.setBounds(40, 69, 88, 16);
		contentPane.add(lblDeskripzioa);

		lblPrezioMinimoa = new JLabel("Prezio minimoa");
		lblPrezioMinimoa.setBounds(40, 128, 88, 16);
		contentPane.add(lblPrezioMinimoa);

		lblData = new JLabel("Bukaerako data");
		lblData.setBounds(40, 174, 97, 16);
		contentPane.add(lblData);

		jCalendar.setBounds(334, 13, 184, 155);
		contentPane.add(jCalendar);

		LabelError.setForeground(Color.RED);
		LabelError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelError.setBounds(40, 227, 335, 34);
		contentPane.add(LabelError);

		LabelData = new JTextField();
		LabelData.setBounds(142, 171, 233, 22);
		contentPane.add(LabelData);
		LabelData.setColumns(10);

		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings("deprecation")
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("calendar")) {
					BukaerakoData = trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					LabelData.setText(BukaerakoData.toGMTString());
					System.out.print(BukaerakoData);

				}
			}

		});
	}

	private Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
}
