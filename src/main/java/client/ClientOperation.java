package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

import remote.Task;

public class ClientOperation implements Task<File>, Serializable{

	private static final long serialVersionUID = -8644449876119311593L;
	private File fileToSort;
	private File outputFile;
	
	public ClientOperation(File file, File output) {
		this.fileToSort = file;
		this.outputFile = output;
	}

	public File execute() {
		Integer[] intArray;
		
		try {
			// creating another instance of Scanner specially to define length of array.
			Scanner scannerForTokensAmount = new Scanner(new FileInputStream(fileToSort));
			intArray = new Integer[new StringTokenizer(scannerForTokensAmount.nextLine(), " ").countTokens()];
			scannerForTokensAmount.close();

			// Filling array with integers from file.
			Scanner	scan = new Scanner(new FileInputStream(fileToSort));
			for (int i = 0; i < intArray.length && scan.hasNextInt(); i++) {
				intArray[i] = scan.nextInt();
			}
	 		scan.close();
			
			System.out.println("List has elements: " + intArray.length);
			
			
			// Sorting our array of integers using insertion method.
	        int temp;
	        for (int i = 0; i < intArray.length; i++) {
	            for(int j = i ; j > 0 ; j--){
	                if(intArray[j] < intArray[j-1]){
	                    temp = intArray[j];
	                    intArray[j] = intArray[j-1];
	                    intArray[j-1] = temp;
	                }
	            }
	        }

	        // Writing sorted array into new file.
	        FileWriter writer = new FileWriter(outputFile);
	        for (int i = 0; i < intArray.length; i++) {
				writer.write(intArray[i] + " ");
			}
	        writer.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return outputFile;
	}

}
