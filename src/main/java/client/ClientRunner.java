package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import files.RandomIntegersFileCreator;
import remote.RMIInterface;
import remote.Task;

public class ClientRunner {
	private static RMIInterface remote;
	
	public static void main(String args[]) {
		System.setProperty("java.security.policy", "/home/shumyk/workspace/CourseWork-Hashchuk/src/main/resources/security.policy");
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try {
			String name = "Calculation";
			Registry registry = LocateRegistry.getRegistry(1099);
			remote = (RMIInterface) registry.lookup(name);
			System.out.println("Connected to server!");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Wainting for instructions...");
				String input = reader.readLine();
				String[] partsOfInput = input.split(" ");
				
				switch (partsOfInput[0]) {
					case "init":
						if (equals(partsOfInput.length, 2)) {
							RandomIntegersFileCreator.initArrayIntegers(partsOfInput[1]);;
						} else {
							System.err.println("Please provide path for file!");
						}
						break;
					case "ping":
						System.out.println(remote.ping());
						break;
					case "echo":
						if (isBigger(partsOfInput.length, 1)) {
							String total = "";
							for (String chunk: Arrays.copyOfRange(partsOfInput, 1, partsOfInput.length)) {
								total = total.concat(" ").concat(chunk);
							}
							System.out.println(remote.echo(total));
							
						} else {
							System.err.println("Please provide message to server!");
						}
						break;
					case "process":
						if(equals(partsOfInput.length, 3)){
							runTask(partsOfInput[1], partsOfInput[2]);
						} else {
							System.err.println("Please provide paths to files!");
						}
						break;
					case "show":
						if (equals(partsOfInput.length, 2)){
							System.out.println(new Scanner(new File(partsOfInput[1])).nextLine());
						} else {
							System.err.println("Provide please path to one file!");
						}
						break;
					case "exit":
					case "0":
					case "quit":
						return;
					default:
						System.err.println("Invalid instruction!");
						break;
				}
			}
			
		} catch (Exception e) {
			System.err.println("Calculation exception:");
			e.printStackTrace();
		}
	}
	
	private static void runTask(String fileToSort, String outputFile) throws RemoteException {
		System.out.println("Start operation");
		Date startDate = new Date();
		Task<File> task = new ClientOperation(new File(fileToSort), new File(outputFile));
		remote.executeTask(task);
		long timeDiff = new Date().getTime() - startDate.getTime();
		
		System.out.println("Time needed for processing operation: "
							+ TimeUnit.MILLISECONDS.toSeconds(timeDiff) 
							+ " seconds ( " + timeDiff + " milliseconds) ");
	}
	
	private static boolean isBigger(int first, int second) {
		return first > second ? true : false;
	}
	
	private static boolean equals(int first, int second) {
		return first == second ? true : false;
	}
}
