package clientGUI;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class MyTable extends JTable {

	public MyTable(TableModel model)
	{
		this.setModel(model);
	}
}
