// -----------------------------------------------------
// COMP 249 Part2
// Written by: Imran Ahmed 401729321
// -----------------------------------------------------


import java.util.Scanner;

interface Watchable{
	public String isOntime(TvShow S);
}

public class TvShow implements Cloneable, Watchable {
		
		String showID;
		String showName;
		double StartTime;
		double endTime;
		
		/**
		 * Default Constructor 
		 */
		public TvShow() {
			showID = "";
			showName = "";
			StartTime = 0;
			endTime = 0;
		}
		/**
		 * Parametrized constructor of TvShow class
		 * @param showID
		 * @param showName
		 * @param StartTime
		 * @param endTime
		 */
		public TvShow(String showID, String showName, double StartTime , double endTime) {
			this.showID = showID;
			this.showName = showName;
			this.StartTime = StartTime;
			this.endTime = endTime;
		}
		
		/**
		 * Copy constructor of TvShow class
		 * @param s
		 * @param showID
		 */
		public TvShow(TvShow s, String showID) {
			this.showID = showID;
			showName = s.showName;
			StartTime = s.StartTime;
			endTime = s.endTime;
		}
		
		/**
		 * Mutator method of show Name
		 */
		public void setShowName(String name) {
			showName = name;
		}
		
		/**
		 * Accessor method of show name
		 */
		public String getShowName() {
			return showName;
		}
		
		/**
		 * Mutator method of ShowID
		 * @param showID
		 */
		public void setShowID(String showID) {
			this.showID = showID;
		}
		
		/**
		 * Accessor method of ShowID
		 * @return
		 */
		public String getShowID() {
			return showID;
		}
		
		/**
		 * Accesor method of StartTime
		 * @return
		 */
		public double getStartTime() {
			return StartTime;
		}
		
		/**
		 * Mutator method of StartTime
		 * @param startTime
		 */
		public void setStartTime(double startTime) {
			StartTime = startTime;
		}
		
		/**
		 * Accessor method of EndTime
		 * @return
		 */
		public double getEndTime() {
			return endTime;
		}
		
		/**
		 * Mutator method of endTime
		 * @param endTime
		 */
		public void setEndTime(double endTime) {
			this.endTime = endTime;
		}
		
		/**
		 * Clone method for TvShow class
		 */
		public TvShow clone() {
			System.out.println("Enter a Show ID :");
			Scanner key = new Scanner(System.in);
			String ID = key.nextLine();
			return new TvShow(this, ID);
		}
		
		/**
		 * ToString method
		 */
		public String toString() {
			return "The TVShow "+showName+ " in "+showID+ " Starts at "+StartTime+" and ends at "+endTime;
		}
		
		/**
		 * Equals method
		 */
		public boolean equals(Object x) {
			if (x == null || this.getClass() != x.getClass()) {
				return false;
			}
			else {
				TvShow s = (TvShow)x;
				
				return (s.showName.equals(this.showName) && s.StartTime == this.StartTime && s.endTime == this.endTime);
			}
		}
		
		/**
		 * isOnTime method
		 */
		public String isOntime(TvShow s) {
			if (this.StartTime == s.StartTime && this.endTime == s.endTime) {
				return "Same Time"; 
			}
			else if ((s.StartTime <= this.StartTime && this.StartTime < s.endTime && s.endTime <= this.endTime) || (this.StartTime <= s.StartTime && s.StartTime < this.endTime && this.endTime <= s.endTime)) {
				return "Some Overlap"; 
			}
			else
				return "Diffrent Time"; 
		}
		
}



