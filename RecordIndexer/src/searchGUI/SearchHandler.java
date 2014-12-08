package searchGUI;

import java.util.List;

import client.communication;
import client_communicator.GetProjects_result;
import client_communicator.ValidateUser_Params;
import client_communicator.ValidateUser_Result;

public class SearchHandler {
	
	private String host;
	private String port;
	private String username;
	private String password;
	private communication comm;
	
	public SearchHandler()
	{
	
	}

	public boolean validate(String host, String port, String username, String password)
	{
		this.setHost(host);
		this.setPort(port);
		this.setPassword(password);
		this.setUsername(username);
		comm = new communication(host, port);
		ValidateUser_Params params = new ValidateUser_Params();
		params.setPassword(password);
		params.setUsername(username);
		
		try
		{
			ValidateUser_Result result = comm.ValidateUser(params);
			if(result == null || result.getInvalid() != null)
			{
				return false;
			}
			else
				return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public List<String> getProjects()
	{
		try
		{
			GetProjects_result resultList = comm.GetProjects();
			return resultList.getProjects();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
