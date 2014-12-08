package searchGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import client.communication;
import client_communicator.SearchTuple;
import client_communicator.Search_params;
import client_communicator.Search_result;
import client_communicator.ValidateUser_Params;
import client_communicator.ValidateUser_Result;
import design.Project;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame  {

	private JFrame imageFrame;
	private String username;
	private String password;
	private String host;
	private String port;
	private JTextField searchInput;
	private ArrayList<JRadioButton> fieldButtons;
	private JButton search;
	private JButton selectImage;
	private JPanel resultPanel;
	private JPanel selectPanel;
	private JComboBox<String> images;
	private ParamPanel params;
	private ValidatePanel validatePanel;
	private communication comm;
	private boolean loggedIn = false;
	private List<SearchTuple> tuples;
	private String url;
	
	
	public SearchFrame()
	{
		super();
		setTitle("Search Fields");
		selectPanel = new JPanel();
		selectPanel.setLayout(new FlowLayout());
		selectPanel.add(new JLabel("Select Image: "));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		images = new JComboBox<String>();
		add(Box.createRigidArea(MyConstants.DOUBLE_VSPACE));
		setMinimumSize(new Dimension(800,400));
		imageFrame = new JFrame();
		imageFrame.setVisible(false);
		search = new JButton("SEARCH");
		selectImage = new JButton("View Image");
		resultPanel = new JPanel();
		resultPanel.setLayout(new FlowLayout());
		params = new ParamPanel();
		
		validatePanel = new ValidatePanel();
		add(validatePanel);
		
		validatePanel.login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				comm = new communication(validatePanel.getHost(), validatePanel.getPort());
				ValidateUser_Params userParams = new ValidateUser_Params();
				userParams.setPassword(validatePanel.getPassword());
				userParams.setUsername(validatePanel.getUsername());
				
				
				try
				{
					//log in user
					ValidateUser_Result userResult = comm.ValidateUser(userParams);
					if(userResult != null && userResult.getInvalid() == null)
					{
						username = validatePanel.getUsername();
						password = validatePanel.getPassword();
						host = validatePanel.getHost();
						port = validatePanel.getPort();
						validatePanel.login.setEnabled(false);
						validatePanel.hostField.setEnabled(false);
						validatePanel.portField.setEnabled(false);
						validatePanel.usernameField.setEnabled(false);
						validatePanel.passwordField.setEnabled(false);
						validatePanel.revalidate();
						
						if(!loggedIn)
						{
							url = "http://"+validatePanel.getHost() +":"+ validatePanel.getPort() + "/Records/";
							Border edge = BorderFactory.createLoweredBevelBorder();
							loggedIn = true;
							ArrayList<Project> projects = buildProjects(comm.GetProjects().getProjects());
							JPanel projView = params.addProjects(projects, host, port);
							projView.setAlignmentY(RIGHT_ALIGNMENT);
							projView.revalidate();
							add(projView);
							
							fieldButtons = params.fieldButtons;
							JPanel s = new JPanel();
							s.setSize(new Dimension(800,10));
							s.add(Box.createRigidArea(MyConstants.DOUBLE_HSPACE));
							
							s.setBorder(edge);
							s.add(new JLabel("Enter Values to search for (use comma): "));
							searchInput = new JTextField(52);
							s.add(searchInput);
							s.add(search);
							s.add(Box.createRigidArea(new Dimension(0,70)));
							s.setMaximumSize(getPreferredSize());
							add(s);
							add(resultPanel);
							pack();
						}
						
						
					}
					
				}
				catch (Exception wrong)
				{
					wrong.printStackTrace();
					
				}
			}
		}
		);
		
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] fields = new String[1];
				
				for(int i=0;i<fieldButtons.size();i++)
				{
					if(fieldButtons.get(i).isSelected())
					{
						StringBuilder sb = new StringBuilder();
						sb.append(i+1);
						fields[0] = sb.toString();
					}
				}
				
				Search_params params = new Search_params();
				params.setUsername(username);
				params.setPassword(password);
			
				String[] values = searchInput.getText().split(",");
				
				
				params.setFields(fields);
				params.setValues(values);
				
				try
				{
					
					Search_result result = comm.Search(params);
				//result is not null but the query is not finding anything with field_id = 1
					//and value = fox
							
					if(result != null && result.isEmpty() == false)
					{
						images.removeAllItems();
						images.setMaximumRowCount(100);
						tuples = result.getTuples();
						
												
						for(SearchTuple tup : tuples)
						{
							String str = tup.buildUrl(host, port).substring(url.length());
							boolean there = false;
							ComboBoxModel<String> model = images.getModel();
							for(int i=0;i<model.getSize();i++)
							{
								if(model.getElementAt(i).equals(str))
								{
									
									there = true;
								}
							}
							if(!there)
							{
								images.addItem(str);
								
							}
						}
					}
				}
				catch (Exception wrong)
				{
					wrong.printStackTrace();
				}
				selectPanel.add(images);
				selectPanel.add(selectImage);
				add(selectPanel);
				revalidate();
			}
			
			
		}
				
		);
		
		selectImage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String temp = "";
				try
				{
					System.out.println(url + images.getSelectedItem());
					InputStream input = new URL((String) url+images.getSelectedItem()).openStream();
					
					temp = input.toString();
				
					System.out.println("My URL = " + input);
				
					BufferedImage image = ImageIO.read(input);
					
					JFrame f = new JFrame();
				
					ImageIcon icon = new ImageIcon(image);
				
					JPanel j = new JPanel();
					j.add(new JLabel(icon));
					j.revalidate();
					f.add(new JScrollPane(j));
					f.pack();
					f.setVisible(true);
					pack();
					
				}
				catch (Exception wrong)
				{
					
					wrong.printStackTrace();
					System.out.println("Desired URL is: "+temp);
				}
			}
		}
		);
		pack();
		
		
		
		
		
		
		
		
		
		
	}
	
	public ArrayList<Project> buildProjects(List<String> projects)
	{
		ArrayList<Project> proj = new ArrayList<Project>();
		
		for(int i = 0; i<projects.size(); i = i+2)
		{
			
			Project p = new Project();
			p.setId(Integer.parseInt(projects.get(i)));
			
			p.setTitle(projects.get(i + 1));
			proj.add(p);
		}
		
		return proj;
	}

}
