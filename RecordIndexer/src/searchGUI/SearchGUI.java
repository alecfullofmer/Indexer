package searchGUI;

import java.awt.EventQueue;

public class SearchGUI {

	public SearchGUI(){}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{

				@Override
				public void run() 
				{
					SearchFrame search = new SearchFrame();
					search.setVisible(true);
				}
				
			}
		);
	}
	
}
