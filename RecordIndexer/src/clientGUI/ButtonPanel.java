package clientGUI;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	public JButton zoomIn;
	public JButton zoomOut;
	public JButton invert;
	public JButton toggle;
	public JButton save;
	public JButton submit;
	
	public ButtonPanel()
	{
//		this.setLayout(new FlowLayout());
		 this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom Out");
		invert = new JButton("Invert Image");
		toggle = new JButton("Toggle Highlights");
		save = new JButton("Save");
		submit = new JButton("Submit");
		
		this.add(zoomIn);
		this.add(zoomOut);
		this.add(invert);
		this.add(toggle);
		this.add(save);
		this.add(submit);
	}

}
