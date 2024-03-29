package clientGUI;

import javax.swing.table.AbstractTableModel;

import batch.BatchState;
import client_communicator.DownloadBatch_result;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {

		private DownloadBatch_result result;
		private BatchState batchState;
		
		public TableModel(DownloadBatch_result result, BatchState batch)
		{
			this.result = result;
			batchState = batch;
		}
		
		@Override
		public int getColumnCount() {
			return result.getFields().size() + 1;
		}

		@Override
		public String getColumnName(int column) {

			if(column == 0)
			{
				return "Record Number";
			}
			else
			{
				return result.getFields().get(column - 1).getTitle();
			}
		}

		@Override
		public int getRowCount() {
			return result.getProj().getRecords_per_image();
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column > 0)
				return true;
			else
				return false;
		}

		
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			if(columnIndex == 0)
				return null;
			return batchState.getValue(rowIndex, columnIndex - 1);
		}


		@Override
		public void setValueAt(Object value, int row, int column)
		{
			if (column == 0)
				return;
			batchState.addValue((String)value, row, column-1);
			this.fireTableCellUpdated(row, column);	
		}

	

}
