// -----------------------------------------------------
// COMP 249 Part1
// Written by: Imran Ahmed 401729321
// -----------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

	public static void parseFile(Scanner sc , PrintWriter p1 , PrintWriter p2 , PrintWriter p3 , ArrayList<String> v , ArrayList<String> o , ArrayList<String> d , ArrayList<String> t ) {
		
		while(sc.hasNext()) {
			
			String word = sc.next();
			String newWord = word.replaceAll("[^a-zA-Z0-9]", "");
			if (newWord.length() != 0) {
				t.add(newWord);
				
			int counter_vowels = 0;
			
			for (int i = 0 ; i < newWord.length() ; i++) {
				char letter = newWord.charAt(i);
				
				if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U'  ) {
					counter_vowels++;
				}
			}
			
			if (newWord.charAt(0) == 'o' || newWord.charAt(0) == 'O' ) {
				o.add(newWord);
			}
			
			if (counter_vowels > 3) {
				v.add(newWord);
			}
		}
	}
		
		
		for (int i = 0 ; i < t.size(); i++) {
			if (!d.contains(t.get(i))) {
				d.add(t.get(i));
			}
		}
		
		
		//Print to the vowels file
		p1.println("Word count: "+v.size());
		for (int i = 0 ; i < v.size() ; i++) {
			p1.println(v.get(i));
		}
		
		//Print words that start with "o"
		p2.println("Word count: "+o.size());
		for(int i = 0; i < o.size(); i++) {
			p2.println(o.get(i));
		}
		
		//Print distinct words
		p3.println("Word count: "+d.size());
		for (int i = 0 ; i < d.size() ; i++) {
			p3.println(d.get(i));
		}
		
		//Close streams
		p1.close();
		p2.close();
		p3.close();
	}
	
	
	
	public static void main(String[] args) {
		
		/**
		 * Creating the array list objects 
		 */
		ArrayList<String> vowels = new ArrayList<String>();
		ArrayList<String> start_o = new ArrayList<String>();
		ArrayList<String> distinct = new ArrayList<String>();
		ArrayList<String> total = new ArrayList<String>();
		
		
		
		/**
		 * Attempting to open input and output streams
		 */
		Scanner sc = null;
		PrintWriter vowelsfile = null;
		PrintWriter start_0file = null;
		PrintWriter distinctfile = null;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the file name you want to parse ");
		String file = keyboard.nextLine();
		
		try {
			sc = new Scanner(new FileInputStream(file+".txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println(file+".txt was not found. Program will termiante!");
			System.exit(0);
		}
		
		try {
			vowelsfile = new PrintWriter(new FileOutputStream("SubDictionaryVowels.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not open file to write to! Program will terminate");
			System.exit(0);
		}
		
		try {
			start_0file = new PrintWriter(new FileOutputStream("SubDictionaryStart_O.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not open file to write to! Program will terminate");
			System.exit(0);
		}
		
		try {
			distinctfile = new PrintWriter(new FileOutputStream("SubDictionaryDistinct.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not open file to write to! Program will terminate");
			System.exit(0);
		}
		
		/**
		 * Parse the three respective files
		 */
		parseFile(sc, vowelsfile , start_0file, distinctfile , vowels , start_o , distinct , total );
		System.out.println("File was parsed sucesfully!");
		sc.close();
		
		
		
		
		
	}

}
