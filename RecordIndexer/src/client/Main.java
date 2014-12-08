package client;

import java.awt.EventQueue;

import clientGUI.DownloadBatch;
import clientGUI.Indexer;
import clientGUI.LoginFrame;

public class Main {

	public Main(){}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{

				@Override
				public void run() 
				{
					LoginFrame login = new LoginFrame();
					Indexer index = new Indexer();
					DownloadBatch batchFrame = new DownloadBatch("localhost", "8080");
					ClientFacade client = new ClientFacade(batchFrame, login, index, "localhost", "8080");
				}
				
			}
		);
	}
}
