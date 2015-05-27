package fr.ecm1A.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import fr.ecm1A.model.Regle;
import fr.ecm1A.model.SystemeExpert;
import fr.ecm1A.model.TableModelBDFCreerRegle;

@SuppressWarnings("serial")
public class RegleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable condTable;
	private Regle regleTemp;

	/**
	 * Create the dialog.
	 */
	private RegleDialog(JFrame owner) {
		super(owner, false);
		setTitle("Nouvelle R\u00E8gle");
		setLocation(owner.getLocation());
		int hauteur = owner.getHeight() * 93 / 100;
		int largeur = owner.getWidth() / 2;
		setSize(new Dimension(largeur, hauteur));
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		regleTemp = new Regle();
		TableModelBDFCreerRegle tableModel = new TableModelBDFCreerRegle(
				regleTemp);
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				condTable = new JTable(tableModel);
				condTable.getColumnModel().getColumn(2).setPreferredWidth(1);
				condTable.getColumnModel().getColumn(1).setPreferredWidth(1);
				RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						tableModel);
				condTable.setRowSorter(sorter);
				sorter.toggleSortOrder(0);
				scrollPane.setViewportView(condTable);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.EAST);
			{
				PrevisuRegle previsuRegle = new PrevisuRegle(tableModel);
				scrollPane.setViewportView(previsuRegle);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ajouter");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (regleTemp.getConditions().size() > 0
								&& !regleTemp.getConclusion().equals("")) {
							SystemeExpert.getInstance().getBdr()
									.add(new Regle(regleTemp));
							regleTemp.getConditions().clear();
							regleTemp.setConclusion("");
							tableModel.reset();
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
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void showDialog(JFrame owner) {
		RegleDialog dialog = new RegleDialog(owner);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
