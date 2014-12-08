package searchGUI;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import client.communication;
import client_communicator.GetFields_params;
import design.Project;
import design.field;

public class ParamPanel extends JPanel {

	private String[] _paramNames;
	private ArrayList<JTextField> _textFields;
	private SearchHandler handler;
	private ArrayList<JRadioButton> projectButtons;
	public ArrayList<JRadioButton> fieldButtons = new ArrayList<JRadioButton>();
	
	public JPanel addProjects(ArrayList<Project> projects, String host, String port)
	{
		JPanel proj = new JPanel();
		proj.setLayout(new BoxLayout(proj, BoxLayout.X_AXIS));
		
		//loop through all our projects
		for(int i = 0; i<projects.size();i++)
		{
			JPanel feel = new JPanel();
			feel.add(new JLabel("<html><b>"+projects.get(i).getTitle()+"</b></html>"));
			feel.setLayout(new BoxLayout(feel, BoxLayout.Y_AXIS));
			feel.setSize(800, 100);
			
			communication comm = new communication(host, port);
			GetFields_params f = new GetFields_params();
			f.setProject_id(projects.get(i).getId());
			List<field> fields = comm.GetFields(f).getFields();
			
			for(int j = 0; j< fields.size(); j++)
			{
				JRadioButton butt = new JRadioButton(fields.get(j).getTitle());
				feel.add(butt);
				fieldButtons.add(butt);
				
			}
			proj.add(feel);
			
		}
		return proj;
	}
	
	public SearchHandler getHandler() {
		return handler;
	}

	public void setHandler(SearchHandler handler) {
		this.handler = handler;
	}

	public ArrayList<JRadioButton> getProjectButtons() {
		return projectButtons;
	}

	public void setProjectButtons(ArrayList<JRadioButton> projectButtons) {
		this.projectButtons = projectButtons;
	}

	public ParamPanel() {
		super();
		
		setBorder(BorderFactory.createTitledBorder("Parameters"));
		
		setLayout(new GridBagLayout());
	}
}
