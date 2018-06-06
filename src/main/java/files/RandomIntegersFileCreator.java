package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomIntegersFileCreator {
	private static final int UPPER_LIMIT = 1000000000;
	private static int amountOfInstances = 50000;
	
	public static void initArrayIntegers(String fileName) {
		
		File randomInts = new File(fileName);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(randomInts));
			Random random = new Random(27);
			
			while (amountOfInstances-- > 0) {
				writer.write(random.nextInt(UPPER_LIMIT) + " ");
			}
			writer.close();
			System.out.println("Array of integers is created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
