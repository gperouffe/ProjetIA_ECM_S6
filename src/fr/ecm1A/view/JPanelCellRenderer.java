package fr.ecm1A.view;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class JPanelCellRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof JPanel) {
			JPanel pan = (JPanel) value;
			if (pan.getPreferredSize().height > 0)
				table.setRowHeight(row, pan.getPreferredSize().height);
			return pan;
		} else {
			return super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
		}
	}
}
