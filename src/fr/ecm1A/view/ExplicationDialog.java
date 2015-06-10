package fr.ecm1A.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreeModel;

@SuppressWarnings("serial")
public class ExplicationDialog extends JDialog {

	private static ExplicationDialog ED;
	private JTree tree;

	private ExplicationDialog(JFrame owner) {
		super(owner);
		setTitle("R\u00E9sultat");
		setLocation(owner.getLocation());
		int hauteur = owner.getHeight() / 2;
		int largeur = owner.getWidth() / 3;
		setSize(new Dimension(largeur, hauteur));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tree = new JTree();
		scrollPane.setViewportView(tree);

		ActionListener escapeClose = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		};
		getRootPane().registerKeyboardAction(escapeClose,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private void chargerLog(TreeModel log) {
		tree.setModel(log);
	}

	public static void showDialog(JFrame owner, TreeModel log) {
		if (ED == null || !ED.isVisible()) {
			ED = new ExplicationDialog(owner);
			ED.chargerLog(log);
			ED.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			ED.setVisible(true);
		}
	}
}
