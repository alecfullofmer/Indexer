package clientGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImageView extends JPanel{

	
	public ImageView()
	{
		super();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 400));
		
		
	}
}
