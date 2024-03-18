

public class SingleLinkedList {
	
	private Node head;
	
	//The method of adding a new element to the list.
	public void add(Object data) {
		if(head==null) {
			Node newNode = new Node(data);
			head = newNode;
		}
		else
		{
			Node temp = head;
			while(temp.getLink()!=null) {
				temp = temp.getLink();
			}
			Node newNode = new Node(data);
			temp.setLink(newNode);
		}
	}
	
	//The method of displaying list.
	public void displayGameAndScore() {
		if(head==null)
			System.out.println("List is empty!");
		else
		{
			
			Node temp = head;
			while(temp!=null) {
				System.out.print(temp.getData()+" ");
				temp = temp.getLink();
			}
		}
		System.out.print("                " + "score: ");
	}
	
	//the method of removing data.
	public void removeByCount(Object dataToDelete, int countOfNumber) {
		if (head==null)
			System.out.println("Linked list is empty");
		else {
		    
		    int count = 1;
			
		    //Checking the head.
		    while(head!=null&&(Integer)head.getData()==(Integer)dataToDelete&&count<=countOfNumber) {
                head=head.getLink();
                count++;
            }
			
			Node temp= head;			
			Node previous = null;
			
			//Checking for the part of after head
			while (temp!=null) {
				if ((Integer)temp.getData()==(Integer)dataToDelete && count<=countOfNumber ) {
					previous.setLink(temp.getLink());
					temp= previous;
					count++;
				}
				previous=temp;
				temp=temp.getLink();
			}
		}
	}
	
	//The method of searching data.
	public boolean search(Object data) {
		if(head==null) {
			System.out.println("List is empty");
			return false;
		}
		else {
			Node temp = head;
			while(temp!=null) {
				if((Integer)temp.getData()==(Integer)data)
					return true;
				temp = temp.getLink();
			}
			return false;
		}
	}
	
	//The method of obtaining the count of similar numbers.
	public int countOfSameNumber(Object data) {
		if (head==null)
			return 0;
		else
		{
			int count = 0;
			Node temp = head;
			while (temp!=null) {
				if((Integer)temp.getData()==(Integer)data) {
					count++;
				}
				temp = temp.getLink();
			}
			return count;
		}
	}
	
	//The method of displaying high score table line by line.
	public void displayLnHighScoreTable() {
		if(head==null)
			System.out.println("List is empty!");
		else
		{
			int count = 0;
			Node temp = head;
			while(temp!=null && count!=10) {
				if (temp.getData() instanceof HighScoreTable) {
					System.out.println(((HighScoreTable)temp.getData()).getNameScore()+" ");
					temp = temp.getLink();
					count++;
				}
				
			}
		}
	}
	
	//The method of sorting list.
	public void sortedList() {
		// Node current will point to head
        Node current = head, index = null;
        Object temp;
 
        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to current
                index = current.link;
 
                while (index != null) {
                    // If current node's data is greater than index's node data, swap the data between them
                	if (current.getData() instanceof HighScoreTable && index.getData() instanceof HighScoreTable) {
                		if (((HighScoreTable)current.getData()).getScore() < ((HighScoreTable)index.getData()).getScore()) {
                            temp = current.data;
                            current.data = index.data;
                            index.data = temp;
                        }
                        index = index.link;
                	}
                }
                current = current.link;
            }
        }	
	}
	//The method for checking if the player is among the best 10 players.
	public boolean checkingHighScore(boolean flag,Object sll) {
		if(head==null)
			System.out.println("List is empty!");
		
		else
		{
			int count = 0;
			Node temp = head;
			while(temp!=null && count!=10) {
				    if(((HighScoreTable)temp.getData()).getNameScore().equals(((HighScoreTable) sll).getNameScore())) {
				    	flag=true;
				    }
					
					temp = temp.getLink();
					count++;
				
				
			}
		}
		return flag;
	}
		
} 

