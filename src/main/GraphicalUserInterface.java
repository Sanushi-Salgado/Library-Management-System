package main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import javax.swing.event.*;
import javax.swing.table.*;

public class GraphicalUserInterface extends JFrame {

	GraphicalUserInterface() {
		final JTextField t1 = new JTextField(15);
		setTitle("Library Items");
		JLabel l1 = new JLabel("Search");
		String columnNames[] = { "ISBN", "TYPE", "TITLE", "SECTOR", "PUBLICATION DATE", "AVAILABILITY" };


		URL urlG = getClass().getResource("/resources/Libya-Old-Green-Flag.png" );
		URL urlR = getClass().getResource("/resources/Red_flag.png" );
		Icon greenFlag = new ImageIcon(urlG, "Green Flag");
		Icon redFlag = new ImageIcon(urlR, "Red Flag");


		final Object[][] rowData = {};
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
			// Returning the Class of each column will allow different
			// renders to be used based on Class
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};


		// Get item information
		WestminsterLibraryManager.libraryItems.forEach(item -> {
			String isbn = item.getIsbn();
			String type = item.getType().toLowerCase();
			String title = item.getTitle().toLowerCase();
			String sector = item.getSector().toLowerCase();
			DateTime publicationDate = item.getPublicationDate();
			String pdate = publicationDate.getDate();

            model.addRow(new Object[] { isbn, type, title, sector, pdate, greenFlag });
            int row = model.getRowCount();

			// Checking the availability of the item
			// So need to check if the item is in the borrowed items list
			WestminsterLibraryManager.borrowedItems.forEach(borrowedItem -> {
				if (borrowedItem.getIsbn().equals(isbn)) {
                    // Since the item is in the borrowed items list it's currently not available to
                    // borrow
                    // So we need to display a red flag in the table
                    model.setValueAt(redFlag, (row - 1), 5);
                }
			});
		});


		final TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(model);

		JTable table1 = new JTable(model);

		table1.setRowSorter(sorter1);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		JScrollPane js = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(l1);
		add(t1);
		add(js);


		t1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(t1.getText().trim());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(t1.getText().trim());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(t1.getText().trim());
			}

			public void search(String s) {
				if (s.length() == 0) {
					sorter1.setRowFilter(null);
				} else {
					sorter1.setRowFilter(RowFilter.regexFilter(s.toLowerCase()));
				}
			}
		});
		setSize(500, 600);
		setVisible(true);

	}

}
