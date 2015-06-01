package fr.ecm1A.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import fr.ecm1A.model.Fait;
import fr.ecm1A.model.Regle;
import fr.ecm1A.model.SystemeExpert;
import fr.ecm1A.model.TableModelBDFCreerRegle;

@SuppressWarnings("serial")
public class RegleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable condTable;
	private Regle regleTemp;
	private TableModelBDFCreerRegle tableModel;
	private static RegleDialog RD;
	private JTextField txtRechercher;
	private int indexRegleAModif = -1;
	private JTextField txtNouveauFait;

	/**
	 * Create the dialog.
	 */
	private RegleDialog(JFrame owner, Regle regle) {
		super(owner, false);
		regleTemp = regle;
		setTitle("Editeur de R\u00E8gles");
		setLocation(owner.getLocation());
		int hauteur = owner.getHeight() * 93 / 100;
		int largeur = owner.getWidth() * 2 / 3;
		setSize(new Dimension(largeur, hauteur));
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		tableModel = new TableModelBDFCreerRegle();
		TableRowSorter<TableModelBDFCreerRegle> sorter = new TableRowSorter<TableModelBDFCreerRegle>(
				tableModel);
		sorter.toggleSortOrder(0);

		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		condTable = new JTable(tableModel);
		condTable.getColumnModel().getColumn(2).setPreferredWidth(1);
		condTable.getColumnModel().getColumn(1).setPreferredWidth(1);
		condTable.setRowSorter(sorter);
		scrollPane.setViewportView(condTable);

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		txtNouveauFait = new JTextField();
		txtNouveauFait.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					if (!txtNouveauFait.getText().equals("")) {
						SystemeExpert.getInstance().getBdf().add(new Fait(txtNouveauFait.getText()));
						txtNouveauFait.setText("");
					}
			}
		});
		txtNouveauFait.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNouveauFait.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtNouveauFait.setText("Nouveau Fait");
			}
		});
		txtNouveauFait.setText("Nouveau fait");
		panel_3.add(txtNouveauFait);
		txtNouveauFait.setColumns(10);

		txtRechercher = new JTextField();
		panel_3.add(txtRechercher);
		txtRechercher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				RowFilter<TableModelBDFCreerRegle, Integer> filter = RowFilter
						.regexFilter(".*(?i)(?U)^" + txtRechercher.getText()
								+ ".*");
				sorter.setRowFilter(filter);
			}
		});
		txtRechercher.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtRechercher.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtRechercher.setText("Recherche");
			}
		});
		txtRechercher.setText("Rechercher");
		txtRechercher.setColumns(10);

		JScrollPane scrollPane1 = new JScrollPane();
		contentPanel.add(scrollPane1, BorderLayout.EAST);

		PrevisuRegle previsuRegle = new PrevisuRegle(tableModel);
		scrollPane1.setViewportView(previsuRegle);

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		buttonPane.setLayout(new GridLayout(1, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		buttonPane.add(panel_1);

		JButton btnEffacerSelec = new JButton("Effacer S\u00E9lection");
		btnEffacerSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.reset();
			}
		});
		panel_1.add(btnEffacerSelec);

		JPanel panel = new JPanel();
		FlowLayout flowLayout1 = (FlowLayout) panel.getLayout();
		flowLayout1.setAlignment(FlowLayout.RIGHT);
		buttonPane.add(panel);
		JButton okButton = new JButton("Enregistrer");
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regleTemp = tableModel.getRegle();
				if (regleTemp.getConditions().size() > 0
						&& !regleTemp.getConclusion().equals("")) {
					if (indexRegleAModif == -1) {
						SystemeExpert.getInstance().getBdr()
								.add(new Regle(regleTemp));
					} else {
						SystemeExpert.getInstance().getBdr()
								.set(indexRegleAModif, new Regle(regleTemp));
						indexRegleAModif = -1;
					}
					regleTemp.getConditions().clear();
					regleTemp.setConclusion("");
					tableModel.setRegle(regleTemp);
				} else {
					JOptionPane
							.showMessageDialog(
									getContentPane(),
									"Veuillez vérifier que tous les champs sont renseignés.",
									"Règle incomplète",
									JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton cancelButton = new JButton("Fermer");
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.reset();
				setVisible(false);
				dispose();
			}
		});

	}

	private void chargerRegle(Regle regle) {
		regleTemp = regle;
		tableModel.setRegle(regleTemp);

	}

	public static void showDialog(JFrame owner) {
		if (RD == null || !RD.isVisible()) {
			RD = new RegleDialog(owner, new Regle());
			RD.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			RD.setVisible(true);
		}
	}

	public static void showDialog(JFrame owner, Regle regle) {
		if (RD == null) {
			RD = new RegleDialog(owner, regle);
			RD.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			RD.setVisible(true);
		} else if (!RD.isVisible()) {
			RD.chargerRegle(regle);
			RD.setVisible(true);
		} else {
			RD.chargerRegle(regle);
		}
	}

	public static void showDialog(JFrame owner, int indexAModif) {
		showDialog(owner, SystemeExpert.getInstance().getBdr().get(indexAModif));
		RD.indexRegleAModif = indexAModif;
	}

}
