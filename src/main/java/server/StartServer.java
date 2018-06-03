package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remote.RMIInterface;

public class StartServer {

	
	public static void main(String[] args) {
		System.setProperty("java.security.policy", "/home/shumyk/workspace/CourseWork-Hashchuk/src/main/resources/security.policy");
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try {
			String name = "Calculation";
			RMIInterface engine = new ServerOperation();
			RMIInterface stub = (RMIInterface) UnicastRemoteObject.exportObject(engine, 0);
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind(name, stub);
			
			System.out.println("Server calculation bound!");
			
		} catch (Exception e) {
			System.err.println("Server calculation exception:");
			e.printStackTrace();
		}
		
	}
}
