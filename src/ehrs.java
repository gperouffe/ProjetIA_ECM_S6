import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fr.ecm1A.model.SystemeExpert;
import fr.ecm1A.model.TableModelBDF;
import java.awt.GridLayout;


public class ehrs {

	private JFrame frame;
	private JTextField txtKop;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenu mnSauvegarder;
	private JMenuItem mntmBdf;
	private JMenu mnCharger;
	private JMenuItem mntmBdf_1;
	private JSeparator separator;
	private JMenu mnHu;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ehrs window = new ehrs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ehrs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 20));
		
		JLabel lblTableau = new JLabel("Tableau");
		lblTableau.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTableau, BorderLayout.NORTH);
		
		txtKop = new JTextField();
		txtKop.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					table.getModel().setValueAt(txtKop.getText(), table.getModel().getRowCount(), 0);
				}
			}
		});
		txtKop.setText("kop");
		panel.add(txtKop, BorderLayout.SOUTH);
		txtKop.setColumns(10);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(1, 0, 20, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new TableModelBDF());
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mnSauvegarder = new JMenu("Sauvegarder");
		mnFichier.add(mnSauvegarder);
		
		mntmBdf = new JMenuItem("BDF");
		mntmBdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				int reponse = jfc.showOpenDialog(null);
				if (reponse== JFileChooser.APPROVE_OPTION)
					SystemeExpert.getInstance().getBdf().open(jfc.getSelectedFile().getPath());
				
			}
		});
		mnSauvegarder.add(mntmBdf);
		
		mnCharger = new JMenu("Charger");
		mnFichier.add(mnCharger);
		
		mntmBdf_1 = new JMenuItem("BDF");
		mnCharger.add(mntmBdf_1);
		
		separator = new JSeparator();
		mnFichier.add(separator);
		
		mnHu = new JMenu("HU");
		mnFichier.add(mnHu);
	}

}
