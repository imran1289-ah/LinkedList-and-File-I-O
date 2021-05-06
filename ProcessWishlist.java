// -----------------------------------------------------
// COMP 249 Part2
// Written by: Imran Ahmed 401729321
// -----------------------------------------------------

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;




public class ProcessWishlist {

	public static void main(String[] args)  {
		
		
		ShowList L1 = new ShowList();
		ShowList L2 = null;
		
	    Scanner sc = null;
	    Scanner sc1 = null;
		
	
		Scanner keyboard = new Scanner(System.in);
		
		
		/**
		 * Opening the TvGuide.txt for reading
		 */
		try {
			sc = new Scanner(new FileInputStream("TVGuide.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Could not open TvGuide.txt for reading. Program will terminate");
			System.exit(0);
		}
		
		
		
		/**
		 * Inserting the tv shows from the file into one of the show lists
		 */
		while(sc.hasNext()) {
			String IDShow = sc.next();
		
			String ShowName = sc.next();
			sc.next();
		
			String StartTime = sc.next();
		
			sc.next();
			String EndTime = sc.next();
			
			double start = Double.parseDouble(StartTime);
			double end = Double.parseDouble(EndTime);
			
			TvShow tv = new TvShow(IDShow, ShowName, start , end);
			
			
			if (!L1.containsTvShow(tv)) {
				L1.addToStart(tv);
			}
			
			
		}
		
		sc.close();
		
		L1.showListContents();
		
		
	
		//TvShow tv1 = new TvShow("ABC2030","The_Goldbergs",20.3,21.0);
		
		//L1.findShow(tv1);
		
		
		
		System.out.println("Enter the name of the file of shows you would want to watch");
		String name = keyboard.nextLine();
		
		
		/**
		 * Opening InterestTv.txt for reading  
		 */
		try {
			sc1 = new Scanner(new FileInputStream(name+".txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Could not open Interest.txt for reading. Program will terminate");
			System.exit(0);
		}
		
		
		/**
		 * ArrayList Watching and Wishlist which holds Watching and WishList Shows
		 */
		ArrayList<String> Watching = new ArrayList<String>();
		ArrayList<String> WishList = new ArrayList<String>();
		
		Boolean isWishlist = false;
		Boolean isWatching = false;
		
		/**
		 * Reading the file interest.txt and getingn the shows watchign and wishlist
		 */
		while (sc1.hasNext()) {
			String Line = sc1.nextLine();
			
			if(Line.equals("Watching")) {
				isWishlist = false;
				isWatching = true;
			}
			else if (Line.equals("Wishlist")) {
				isWatching = false;
				isWishlist = true;
			}
			else if (isWishlist) {
				WishList.add(Line);
			}
			else
				Watching.add(Line);
		}
		
		sc1.close();
		
		
		/**
		 * Displaying the shows the user is watching
		 */
		System.out.println("Here is the list of shows you are watching");
		for (int i = 0 ; i < Watching.size(); i++) {
			System.out.println(Watching.get(i));
		}
		
		/**
		 * Displaying the shows the user wants to watch
		 */
		System.out.println("Here is a list of shows you would like to watch");
		for (int i = 0 ; i < WishList.size(); i++) {
			System.out.println(WishList.get(i));
		}
		
		
		/**
		 * Checking if the shows in the wishlist are watchable 
		 */
		for (int i = 0 ; i < WishList.size(); i++) {
			
			String IsWatchable = "";
			
			for (int j = 0 ; j < Watching.size(); j++) {
				
				String WishListShow = WishList.get(i);
				String WatchingShow = Watching.get(j);
				
				TvShow WishListObject = L1.find(WishListShow).getShow();
				TvShow WatchingObject = L1.find(WatchingShow).getShow();
				
				IsWatchable = WishListObject.isOntime(WatchingObject);
				
				if (IsWatchable.equals("Diffrent Time")) {
					continue;
				}
				else if (IsWatchable.equals("Some Overlap")) {
					break;
				}
				else if (IsWatchable.equals("Same Time")) {
					break;
				}
			}
			
			if (IsWatchable.equals("Some Overlap")) {
				System.out.println("User cannot watch "+WishList.get(i)+ " since he is not finished watching another show");
			}
			else if (IsWatchable.equals("Same Time")) {
				System.out.println("User cannot watch "+WishList.get(i)+" since he will watch another show at that time");
			}
			else
				System.out.println("User can watch "+WishList.get(i)+" since he is not watching any other show");
		}
		
		
		/**
		 * Prompting the user to enter some ShowID
		 */
		System.out.println("Enter a Show ID to find");
		String id = keyboard.nextLine();
		
		L1.findX(id);
		
		System.out.println("Enter a Show ID to find");
		id = keyboard.nextLine();
		
		L1.findX(id);
		
		System.out.println("Enter a Show ID to find");
		id = keyboard.nextLine();
		
		L1.findX(id);
		
		
		/**
		 * Testing the methods
		 */
		TvShow tv = new TvShow("CNN","News",21.00,22.00);
		
		System.out.println("Attempting to add the a tv show called cnn in the 2nd index");
		
		try {
			L1.insertAt(tv, 2);
		}
		catch(NoSuchElementException e) {
			String Message = e.getMessage();
			System.out.println(Message);
			System.exit(0);
		}
		
		System.out.println("Here is the new ShowList");
		
		L1.showListContents();
		
		System.out.println("Attempting to delete shownode at index 5");
		
		try {
			L1.deleteAtIndex(5);
		}
		catch(NoSuchElementException e) {
			String Message = e.getMessage();
			System.out.println(Message);
			System.exit(0);
		}
		
		System.out.println("Here is the new ShowList");
		
		L1.showListContents();
		
		
		System.out.println("Attempting to repalce a tvshow at index 3 with Nick");
		
		
		TvShow replace = new TvShow("Nick","Cartoon",8.00 , 9.00);
		
		try {
			L1.replaceAtIndex(replace, 3);
		}
		catch(NoSuchElementException e) {
			String Message = e.getMessage();
			System.out.println(Message);
			System.exit(0);
		}
		
		System.out.println("Here is the new ShowList");
		
		L1.showListContents();
		
		
		System.out.println("Attempting to create a copy of the show list");
		
		L2 = new ShowList(L1);
		
		L2.showListContents();
		
		
		System.out.println("Enter a show id and check if it contains ");
		String show = keyboard.nextLine();
		
		if(L2.contains(show)) {
			System.out.println(show+" has been found in list 2");
		}
		else
			System.out.println(show+" was not found in list 2");
		
		
		System.out.println("Deleting the first show of list 2");
		
		
		L2.deleteFromStart();
		
		System.out.println("Here is list 2");
		
		L2.showListContents();
		
		
		
		
		
		
		
	
		
		
		
		
		

	}

}
