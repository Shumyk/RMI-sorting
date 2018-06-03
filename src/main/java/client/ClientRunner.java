package client;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import remote.RMIInterface;
import remote.Task;

public class ClientRunner {

	public static void main(String args[]) {
		System.setProperty("java.security.policy", "/home/shumyk/workspace/CourseWork-Hashchuk/src/main/resources/security.policy");
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try {
			String name = "Calculation";
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIInterface remote = (RMIInterface) registry.lookup(name);
			
			Date startDate = new Date();
			Task<File> task = new ClientOperation(new File("/home/shumyk/workspace/CourseWork-Hashchuk/src/main/resources/integersList.txt"));
			remote.executeTask(task);
			long timeDiff = new Date().getTime() - startDate.getTime();
			
			System.out.println("Time needed for processing operation: "
								+ TimeUnit.MILLISECONDS.toSeconds(timeDiff) 
								+ " seconds ( " + timeDiff + " milliseconds) ");
			
		} catch (Exception e) {
			System.err.println("Calculation exception:");
			e.printStackTrace();
		}
	}
}
