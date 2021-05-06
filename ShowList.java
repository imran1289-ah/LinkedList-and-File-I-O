// -----------------------------------------------------
// COMP 249 Part2
// Written by: Imran Ahmed 401729321
// -----------------------------------------------------
public class ShowList {
	
	public class ShowNode implements Cloneable{
		private TvShow Show;
		private ShowNode next;
		
		/**
		 * Default constructor of the innerclass
		 */
		public ShowNode() {
			Show = null;
			next = null;
		}
		/**
		 * Parametrized constructor of the ShowNode class
		 * @param Show
		 * @param next
		 */
		public ShowNode(TvShow Show, ShowNode next) {
			this.Show = Show;
			this.next = next;
		}
		
		/**
		 * Copy Constructor of the ShowNode class
		 * @param sn
		 */
		public ShowNode(ShowNode sn) {
			Show = sn.Show.clone();
			next = sn.next;
		}
		
		/**
		 * Clone method of the ShowNode class
		 */
		public Object clone() {
			try {
				
				ShowNode sn = (ShowNode)super.clone();
				
				sn.Show = (TvShow)Show.clone();
				sn.next = (ShowNode)next.clone();
				
				return sn;
			}
			catch(CloneNotSupportedException e) {
				return null;
			}
		}
		
		/**
		 * Accessor method for Show
		 * @return
		 */
		public TvShow getShow() {
			return Show;
		}
		/**
		 * Mutator method to set Show
		 * @param S
		 */
		public void setShow(TvShow S) {
			Show = S;
		}
		
		/**
		 * Accesor method of the ShowNode
		 * @return
		 */
		public ShowNode getNode() {
			return next;
		}
		
		/**
		 * Mutator method of the ShowNode
		 * @param next
		 */
		public void setNode(ShowNode next) {
			this.next = next;
		}
	}
	
	private ShowNode head;
	private int size;
	
	/**
	 * Default constructor of the ShowList object
	 */
	public ShowList() {
		head = null;
		size = 0;
	}
	
	/**
	 * Copy constructor of the ShowList object
	 */
	public ShowList (ShowList L) {
		
		if (L.head == null) {
			head = null;
			return;
		}
		
		ShowNode t1 = L.head;
		ShowNode t2 = null;
		ShowNode t3 = null;
		
		while (t1!=null) {
			t3 = new ShowNode(t1.Show.clone(), null);
			
			if (head == null) {
				head = t2 = t3;
			}
			else {
				t2.next = t3;
				t2 = t3;
			}
			t1 = t1.next;
			
		}
		t2 = t3 = null;
	
	}
	
	/**
	 * Method addtoStart which adds a tv show at the start of the list
	 */
	public void addToStart(TvShow tv) {
		head = new ShowNode(tv,head);
		size++;
	}
	
	/**
	 * Mehtod insertAt which adds a tv show at the index given by the user
	 * @throws NoSuchElementException 
	 */
	public boolean insertAt(TvShow s , int index) throws NoSuchElementException  {
		
		if (index < 0 || index > size-1) {
			throw new NoSuchElementException("Invalid Index! Program will terminate");
		}
		
		if (head == null) {
			head = new ShowNode(s,head);
			size++;
			return true;
		}
		
		if (index == 0) {
			head = new ShowNode(s,head);
			size++;
			return true;
		}
		
		ShowNode t = head;
		int nodes = 1;
		
		while(t.next!=null & nodes < index ) {
			t = t.next;
			nodes++;
		}
		
		 t.next	= new ShowNode(s,t.next);
		size++;
		return true;
	}
	
	/**
	 * Method deleteFromIndex which deletes a tv show at a given index by the user
	 */
	public boolean deleteAtIndex(int index) throws NoSuchElementException {
		
		if (index < 0 || index > size -1 ) {
			throw new NoSuchElementException("Invalid Index! Program will terminate");
		}
		
		if (head == null) {
			return false;
		}
		
		if (index == 0) {
			head = head.next;
			size--;
			return true;
		}
		
		ShowNode t = head;
		int nodes = 1;
		
		while(t.next!=null & nodes < index ) {
			t = t.next;
			nodes++;
		}
		
		t.next = t.next.next;
		size--;
		return true;
	}
	
	/**
	 * Method deleteFromStart which deletes a tv show at the start of the list
	 */
	public boolean deleteFromStart() {
		if (head != null) {
			head = head.next;
			size--;
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * Method replaceAtIndex which will replace a tv show with the tv show at the index given 
	 */
	public boolean replaceAtIndex(TvShow s , int index) throws NoSuchElementException{
		
		if (index < 0 || index > size -1 ) {
			throw new NoSuchElementException("Invalid Index! Program will terminate");
		}
		
		if (head == null) {
			head = new ShowNode(s, head);
			return true;
		}
		
		if (index == 0) {
			head = new ShowNode(s,head.next);
			return true;
		}
		
		ShowNode t = head;
		int nodes = 0;
		
		while (t!=null & index > nodes) {
			 t = t.next;
			 nodes++;
		}
		
		t.Show.showName = s.showName;
		t.Show.showID = s.showID;
		t.Show.StartTime = s.StartTime;
		t.Show.endTime = s.endTime;
		return true; 
	
	}
	
	/**
	 * Method find which find the node that contains the ShowID
	 * @param ShowID
	 * @return
	 */
	
	//This method is very deadly and causes a Privacy leak. This method allows a privacy leak since it returns a pointer to a showNode and the ShowNode defined
	// in the class is a private showNode, thus leaking its privacy and making it public for anyone to use. It will return a pointer and the user can do anything to the 
	//list with this pointer such a deleteing and modifying nodes.
	public ShowNode find(String ID) {
		
		
		if (head == null) {
			return null;
		}
		
		ShowNode t = head;
		int iterations = 0;
		
		while(t!=null) {
			
			
			if (t.Show.getShowID().equals(ID)) {
				//System.out.println("It took "+iterations+" tries to find the ShowID");
				return t;
			}
			
			t = t.next;
			iterations++;
		}
		return null;
		
		
		
	}
	
	
	/**
	 * Method called contains which checks if a given ShowID is found
	 */
	public boolean contains(String ShowID) {
		
		ShowNode n = find(ShowID);
		
		if (n == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Method called equals which checks if two show list are equals , which means they have the same shows
	 */
	public boolean equals(ShowList l) {
		
		if (this.size > l.size || l.size > this.size) {
			return false;
		}
		
		ShowNode t1 = head;
		ShowNode t2 = l.head;
		
		if (t1 == null) {
			return false;
		}
		if (t2 == null) {
			return false;
		}
		
		while (t1 != null && t2 != null) {
			if (t1.Show.showName.equals(t2.Show.showName) && t1.Show.StartTime == t2.Show.StartTime && t1.Show.endTime == t2.Show.endTime) {
				t1 = t1.next;
				t2 = t2.next;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Content of the list
	 */
	public void showListContents() {
		ShowNode t = head;
		
		if (t == null) {
			System.out.println("Empty Show list");
		}
		else {
			while(t!=null) {
				System.out.println(t.Show);
				System.out.println("|");
				System.out.println("v");
				t = t.next;
			}
		}
		System.out.println("null");
		
	}
	
	/**
	 * Find method which returns the number of iterations 
	 * @param ID
	 * @return
	 */
	public ShowNode findX(String ID) {
		
		if (head == null) {
			return null;
		}
		
		ShowNode t = head;
		int iterations = 0;
		
		while(t!=null) {
			
			
			if (t.Show.getShowID().equals(ID)) {
				System.out.println("It took "+iterations+" tries to find the ShowID");
				return t;
			}
			
			t = t.next;
			iterations++;
		}
		System.out.println("Unable to find the ShowID from the list");
		return null;
	}
	
		/**
		 * Find Method which checks if there is a show
		 */
		//This method cause privacy leaks as it leaks a pointer to a tvshow, which is private.
		public TvShow findShow(TvShow tv) {
		
		if (head == null) {
			return null;
		}
		
		ShowNode t = head;
		int iterations = 0;
		
		while(t!=null) {
			
			
			if (t.Show.equals(tv)) {
				return t.getShow();
			}
			
			t = t.next;
			iterations++;
		}
		return null;
	}
		
		/**
		 * Contains method if the list contains a tvshow
		 */
		public boolean containsTvShow(TvShow s) {
			
			TvShow n = findShow(s);
			
			if (n == null)
				return false;
			else
				return true;
		}
		
	
	
	
	
	
	

}
