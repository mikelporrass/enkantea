package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Enkantea;

public class EnkanteaItxiGUI extends JFrame {

	
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}
	private  Integer  i;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable();

	private static Vector<Enkantea> enkanteak;

	private DefaultTableModel tableModelEnkanteak;
	private String[] columnUserNames = new String[] { "Id","jabea", "Deskripzioa", "Itxita", "Amaiera data" };
	private JLabel lblIzenaSartu;
	private JLabel labelError;
	private JButton btnAtzera;
	private JButton btnItxi;
	private JLabel LabelItxi  = new JLabel("");

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EnkanteaItxiGUI() {
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
				 i = table.getSelectedRow();
				 //enkanteId = (Integer) tableModelEnkanteak.getValueAt(i,0);
				System.out.println(enkanteak.get(i).getEnkanteId());
	
				
			}
		});
		scrollPane.setBounds(12, 75, 648, 161);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(table);
		tableModelEnkanteak = new DefaultTableModel(null, columnUserNames);
		table.setModel(tableModelEnkanteak);

		lblIzenaSartu = new JLabel("");
		lblIzenaSartu.setVisible(false);
		lblIzenaSartu.setBounds(158, 319, 114, 16);
		contentPane.add(lblIzenaSartu);

		labelError = new JLabel("");
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelError.setForeground(Color.RED);
		labelError.setBounds(305, 368, 290, 25);
		contentPane.add(labelError);
		
		btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
					LangileGUI langileGUI = new LangileGUI();
					langileGUI.setVisible(true);
			
			
			}
			
		});
		btnAtzera.setBounds(79, 261, 97, 25);
		contentPane.add(btnAtzera);
		
		btnItxi = new JButton("Itxi");
		btnItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getBusinessLogic().enkanteaItxi(enkanteak.get(i).getEnkanteId())) {
					dispose();
					EnkanteaItxiGUI enkanteaItxiGUI = new EnkanteaItxiGUI();
					enkanteaItxiGUI.setVisible(true);
				}
				else
					LabelItxi.setText("itxita dago");	
				
			}
		});
		btnItxi.setBounds(328, 261, 97, 25);
		contentPane.add(btnItxi);
		
		
		LabelItxi.setBounds(177, 377, 224, 25);
		contentPane.add(LabelItxi);
		
		JLabel lblNewLabel = new JLabel("Pasatutako enkanteak");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(190, 37, 235, 16);
		contentPane.add(lblNewLabel);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(15);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		enkanteak = getBusinessLogic().getPasatutakoEnkanteak();
		for (Enkantea en : enkanteak) {
			int numCols = tableModelEnkanteak.getColumnCount();
			Object[] row = new Object[numCols];
			row[0] = en.getEnkanteId();
			row[1] = getBusinessLogic().getEnkanteContainer(en).getJabea().getIzena();
			row[2] = en.getDeskripzioa();
			row[3] = en.getItxita();
			row[4] = en.getAmaieraData();
			tableModelEnkanteak.addRow(row);
		}

	}
}
