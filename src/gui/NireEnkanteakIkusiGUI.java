package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Enkantea;
import domain.Eskaintza;
import javax.swing.JLabel;
import java.awt.Font;

public class NireEnkanteakIkusiGUI extends JFrame {

	
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	private JPanel contentPane;
	
	private JButton btnAtzera;
	
	private JTable tableEnkanteak=new JTable();
	private JTable tableEskaintzak = new JTable();
	
	private static Vector<Enkantea> nireEnkanteak;
	private static Vector<Eskaintza> nireEnkanteEskaintzak;
	
	private DefaultTableModel tableModelEnkanteak;
	private DefaultTableModel tableModelEskaintzak;
	
	private JScrollPane scrollPane_1 = new JScrollPane();
	private JScrollPane scrollPane = new JScrollPane();
	
	
	
	private String[] columnUserNames2 = new String[] {
			"Izena","Prezioa"
	};

	private String[] columnUserNames = new String[] {
			"id","Deskripzioa", "Egungo Prezioa", "Amaiera data"
	};
	/**
	 * Create the frame.
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblNireEnkanteak = new JLabel("Nire enkanteak");
	private final JLabel lblEskaintzak = new JLabel("Eskaintzak");
	public NireEnkanteakIkusiGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 818, 384);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableEnkanteak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				tableModelEskaintzak.setRowCount(0);
				int i = tableEnkanteak.getSelectedRow();
				Integer enkanteId = (Integer) tableModelEnkanteak.getValueAt(i,0);
		
				nireEnkanteEskaintzak = getBusinessLogic().getNireEnkantearenEskaintzak(gui.HasieraGUI.NANGorde,enkanteId);
				
				for (Eskaintza es : nireEnkanteEskaintzak) {
					int numCols = tableModelEnkanteak.getColumnCount();
					Object[] row = new Object[numCols];
					row[0] = getBusinessLogic().getEskaintzaContainer(es).getEskaintzailea().getIzena();
					row[1] = es.getPrezioa();
					tableModelEskaintzak.addRow(row);
				}
				
				
				
			}
		});
		scrollPane.setBounds(12, 49, 408, 173);
		contentPane.add(scrollPane);

		scrollPane_1.setBounds(515, 49, 273, 173);
		contentPane.add(scrollPane_1);
		
		//tableEnkanteak = new JTable();
		scrollPane.setViewportView(tableEnkanteak);
		tableModelEnkanteak=new DefaultTableModel(null,columnUserNames);
		tableEnkanteak.setModel(tableModelEnkanteak);
		
		//tableEskaintzak = new JTable();
		scrollPane_1.setViewportView(tableEskaintzak);
		tableModelEskaintzak=new DefaultTableModel(null,columnUserNames2);
		tableEskaintzak.setModel(tableModelEskaintzak);
		
		btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JabeGUI jabeGUI = new JabeGUI();
				jabeGUI.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(169, 255, 97, 25);
		contentPane.add(btnAtzera);
		lblNireEnkanteak.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNireEnkanteak.setBounds(57, 13, 209, 23);
		
		contentPane.add(lblNireEnkanteak);
		lblEskaintzak.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEskaintzak.setBounds(525, 13, 115, 22);
		
		contentPane.add(lblEskaintzak);
		
		tableEnkanteak.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableEnkanteak.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableEnkanteak.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableEnkanteak.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		
		nireEnkanteak = getBusinessLogic().getNireEnkanteak(gui.HasieraGUI.NANGorde);
		for (Enkantea en : nireEnkanteak) {
			int numCols = tableModelEnkanteak.getColumnCount();
			Object[] row = new Object[numCols];
			row[0] = en.getEnkanteId();
			row[1] = en.getDeskripzioa();
			row[2] = en.getEgungoPrezioa();
			row[3] = en.getAmaieraData();
			tableModelEnkanteak.addRow(row);
		}
	}
	
}
